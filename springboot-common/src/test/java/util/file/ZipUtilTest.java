package util.file;

import com.github.cnkeep.common.util.file.ZipUtil;
import org.junit.Test;

import java.io.IOException;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.text.MessageFormat;

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

    @Test
    public void test(){
        long currentTimeMillis = System.currentTimeMillis();
        System.out.println(currentTimeMillis);
        String binaryString = Long.toBinaryString(currentTimeMillis);
        System.out.println(MessageFormat.format("time:{0}, \n binaryStr:{1}, \n len={2}",currentTimeMillis,binaryString,binaryString.length()));
    }
}