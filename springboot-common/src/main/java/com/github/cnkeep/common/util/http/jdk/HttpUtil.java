package com.github.cnkeep.common.util.http.jdk;

import javax.net.ssl.HostnameVerifier;
import javax.net.ssl.HttpsURLConnection;
import javax.net.ssl.SSLSession;
import java.io.BufferedInputStream;
import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.net.HttpURLConnection;
import java.net.URL;

/**
 * 描述~
 *
 * @Author <a href="zhangleili924@gmail.com">LeiLi.Zhang</a>
 * @Version 0.0.0
 * @Date 2019/3/12
 */
public final class HttpUtil {
    /**
     * 创建HttpURLConnection </br>
     * <pre>
     * 1. 请求头添加：
     *      Content-Type：application/json;charset=utf-8
     *      Accept：application/json;charset=utf-8
     *
     *
     *  使用：
     *  HttpURLConnection conn = HttpUtil.buildPostConnection(url);
     *  try {
     *      conn.connect();
     *      try (OutputStreamWriter out = new OutputStreamWriter(conn.getOutputStream())) {
     *          out.write(requestContent);
     *      }
     *      StringBuilder response = new StringBuilder();
     *      BufferedReader reader = new BufferedReader(new InputStreamReader(conn.getInputStream()));
     *      String tmp;
     *      System.out.println("********** Response **********");
     *      while ((tmp = reader.readLine()) != null) {
     *          response.append(tmp);
     *      }
     *
     *      return response.toString();
     *  } finally {
     *      conn.disconnect();
     *  }
     * </pre>
     * @param url
     * @return
     * @throws IOException
     */
    public static HttpURLConnection buildPostConnection(String url) throws IOException {
        HttpURLConnection conn = (HttpURLConnection) new URL(url).openConnection();
        conn.setDoOutput(true);
        conn.setDoInput(true);

        //POST方式
        conn.setRequestMethod("POST");
        //请求和响应报文格式指定为application/json;charset=utf-8
        conn.setRequestProperty("Content-Type", "application/json;charset=utf-8");
        conn.setRequestProperty("Accept", "application/json;charset=utf-8");
        return conn;
    }

    private static int BUFFER_SIZE = 1024;

    /**
     * 下载图片
     *
     * @param destUrl
     * @return
     */
    public static byte[] loadFile(String destUrl) throws Exception {
        ByteArrayOutputStream fos = null;
        BufferedInputStream bis = null;
        HttpURLConnection httpUrl = null;
        URL url = null;
        byte[] buf = new byte[BUFFER_SIZE];
        int size = 0;
        try {
            trustAllHttpsCertificates();
            HostnameVerifier hv = new HostnameVerifier() {
                public boolean verify(String urlHostName, SSLSession session) {
                    return true;
                }
            };
            HttpsURLConnection.setDefaultHostnameVerifier(hv);

            url = new URL(destUrl);
            httpUrl = (HttpURLConnection) url.openConnection();
            httpUrl.connect();
            bis = new BufferedInputStream(httpUrl.getInputStream());
            fos = new ByteArrayOutputStream();
            while ((size = bis.read(buf)) != -1) {
                fos.write(buf, 0, size);
            }
            return fos.toByteArray();
        } finally {
            try {
                if (fos != null) {
                    fos.close();
                }
                if (bis != null) {
                    bis.close();
                }
                if (httpUrl != null) {
                    httpUrl.disconnect();
                }
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
    }

    private static void trustAllHttpsCertificates() throws Exception {
        javax.net.ssl.TrustManager[] trustAllCerts = new javax.net.ssl.TrustManager[1];
        javax.net.ssl.TrustManager tm = new miTM();
        trustAllCerts[0] = tm;
        javax.net.ssl.SSLContext sc = javax.net.ssl.SSLContext.getInstance("SSL");
        sc.init(null, trustAllCerts, null);
        javax.net.ssl.HttpsURLConnection.setDefaultSSLSocketFactory(sc.getSocketFactory());
    }

    static class miTM implements javax.net.ssl.TrustManager, javax.net.ssl.X509TrustManager {
        public java.security.cert.X509Certificate[] getAcceptedIssuers() {
            return null;
        }
        public boolean isServerTrusted(java.security.cert.X509Certificate[] certs) {
            return true;
        }
        public boolean isClientTrusted(java.security.cert.X509Certificate[] certs) {
            return true;
        }
        public void checkServerTrusted(java.security.cert.X509Certificate[] certs, String authType)
                throws java.security.cert.CertificateException {
            return;
        }
        public void checkClientTrusted(java.security.cert.X509Certificate[] certs, String authType)
                throws java.security.cert.CertificateException {
            return;
        }
    }

    public static void main(String[] args) throws Exception {
        byte[] bytes = loadFile("https://cdnoffice.lizhi.fm/user/avatar_132.jpg");
        System.out.println(bytes.length);
    }
}
