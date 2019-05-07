package com.github.cnkeep.web.util.file;

import com.github.cnkeep.common.test.util.file.ZipUtil;
import org.junit.Test;

import java.io.IOException;
import java.nio.file.Path;
import java.nio.file.Paths;

public class ZipUtilTest {

    @Test
    public void zipTest() throws IOException {
        Path path = Paths.get("G:\\company\\dir");
        Path destPath = Paths.get(path.getParent()+"test.zip");
        ZipUtil.zip(path, destPath.toString());
        ZipUtil.unzip(destPath,Paths.get("G:\\"));
    }



    @Test
    public void replaceTest() {
        String root = "G:\\company";
        String path = "G:\\company\\dir";
        System.out.println(path.replace(root,""));
    }

}