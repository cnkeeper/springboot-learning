package com.github.cnkeep.common.util.file;

import lombok.AccessLevel;
import lombok.NoArgsConstructor;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.List;
import java.util.stream.Collectors;
import java.util.zip.CRC32;
import java.util.zip.ZipEntry;
import java.util.zip.ZipFile;
import java.util.zip.ZipOutputStream;

/**
 * 描述: 文件压缩工具, base on JDK1.8
 *
 * @Author <a href="zhangleili924@gmail.com">LeiLi.Zhang</a>
 * @Version 0.0.0
 * @Date 2019/2/28
 */
@NoArgsConstructor(access = AccessLevel.PRIVATE)
public class ZipUtil {

    private final static String ZIP_SUFFIX = ".zip";

    /**
     * 文件 or 目录压缩
     *
     * @param srcPath     压缩源文件 or 源目录
     * @param destZipPath 生成压缩文件的绝对路径位置，为null时为源文件同级位置
     * @throws IOException
     */
    public static void zip(Path srcPath, String... destZipPath) throws IOException {
        String destPath = (null == destZipPath || destZipPath.length == 0) ?
                srcPath.getParent().toString() + File.separator + srcPath.getFileName() + ZIP_SUFFIX
                : destZipPath[0];

        try (ZipOutputStream zipOutputStream = new ZipOutputStream(Files.newOutputStream(Paths.get(destPath)))) {
            compress(zipOutputStream, "", srcPath);
        }
    }

    /**
     * 解压文件
     * @param srcPath
     * @param destPath
     * @throws IOException
     */
    public static void unzip(Path srcPath,final Path destPath) throws IOException {
        if (Files.notExists(srcPath)||!Files.isDirectory(destPath)) {
            throw new FileNotFoundException(srcPath + "所指文件不存在");
        }

        if(Files.notExists(destPath)){
            Files.createDirectory(destPath);
        }
        ZipFile zf = new ZipFile(srcPath.toFile());
        zf.stream().forEach(entry -> {
            try {
                uncompressZipEntry(zf,destPath, entry);
            } catch (IOException e) {
                e.printStackTrace();
            }
        });
        zf.close();
    }


    private static void uncompressZipEntry(ZipFile zf, Path destPath, ZipEntry entry) throws IOException {
        if (entry.isDirectory()) {
            Files.createDirectories(Paths.get(destPath.toString(), entry.getName()));
        } else {
            Path path = destPath.resolve(entry.getName());
            if(Files.notExists(path.getParent())){
                Files.createDirectories(path.getParent());
            }
            try(InputStream is = zf.getInputStream(entry)) {
                Files.copy(is, destPath.resolve(entry.getName()));
            }
        }
    }

    /**
     * 递归压缩文件夹
     *
     * @param zipOutputStream
     * @param parentPath
     * @param path
     * @throws IOException
     */
    private static void compress(ZipOutputStream zipOutputStream, String parentPath, Path path) throws IOException {
        if (path.toFile().isFile()) {
            zipOutputStream.putNextEntry(generateZipEntry(parentPath, path));
            Files.copy(path, zipOutputStream);
        } else {
            List<Path> list = Files.list(path).collect(Collectors.toList());
            for (Path tempPath : list) {
                compress(zipOutputStream, parentPath + path.getFileName() + File.separator, tempPath);
            }
        }
    }

    private static ZipEntry generateZipEntry(String parentPath, Path path) throws IOException {
        ZipEntry zipEntry = new ZipEntry(parentPath + path.getFileName().toString());
        zipEntry.setSize(Files.size(path));
        zipEntry.setCrc(getCRC32(path));
        return zipEntry;
    }

    /**
     * 生成CRC32 校验和
     *
     * @param path
     * @return
     * @throws IOException
     */
    private static long getCRC32(Path path) throws IOException {
        byte[] buffer = new byte[8192];
        CRC32 crc32 = new CRC32();
        try (InputStream fileInputStream = Files.newInputStream(path)) {
            int length;
            while ((length = fileInputStream.read(buffer)) != -1) {
                crc32.update(buffer, 0, length);
            }
            return crc32.getValue();
        }
    }
}
