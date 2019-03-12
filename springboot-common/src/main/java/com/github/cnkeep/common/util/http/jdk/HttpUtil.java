package com.github.cnkeep.common.util.http.jdk;

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
}
