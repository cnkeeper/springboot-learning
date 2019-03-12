package util.file;

import org.junit.Assert;
import org.junit.Test;

public class FileTypeTest {

    @Test
    public void dotTest(){
        String fileName = "my.conf";
        String fileSuffix = fileName.substring(fileName.lastIndexOf("."));
        Assert.assertTrue("出错了！"+fileSuffix,".conf".equals(fileSuffix));
    }
}