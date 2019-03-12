package util.file;

import org.junit.Test;

import java.io.IOException;
import java.nio.file.*;
import java.nio.file.attribute.BasicFileAttributes;

import static java.lang.System.out;

/**
 * 描述: Files 示例
 *
 * @Author <a href="zhangleili924@gmail.com">LeiLi.Zhang</a>
 * @Version 0.0.0
 * @Date 2019/2/28
 */
public class FilesTest {
    /**
     * 递归遍历文件目录
     * @throws IOException
     */
    @Test
    public void walkFileTreeTest() throws IOException {
        Path filePath = Paths.get("G:/company");
        Files.walkFileTree(filePath, new SimpleFileVisitor<Path>() {
            @Override
            public FileVisitResult visitFile(Path file, BasicFileAttributes attrs) throws IOException {
                out.println(file);
                return FileVisitResult.CONTINUE;
            }

            @Override
            public FileVisitResult postVisitDirectory(Path dir, IOException exc) throws IOException {
                out.println(dir);
                return FileVisitResult.CONTINUE;
            }
        });
    }


    /**
     * 遍历当前文件夹目录，不递归
     * @throws IOException
     */
    @Test
    public void listTest() throws IOException {
        Path filePath = Paths.get("G:/company");
        Files.list(filePath).forEach(System.out::println);

        // 与上面的一样效果
        Files.newDirectoryStream(filePath).forEach(System.out::println);
    }


    /**
     * 快捷的文件复制
     * @throws IOException
     */
    @Test
    public void copyTest() throws IOException {
        Path scrPath = Paths.get("G:/company/dir/00.txt");
        //文件不用提前创建，假如文件已经存在会报错，可以使用StandardCopyOption.REPLACE_EXISTING
        //这里不建议使用Paths.get(...)因为其依赖于不同的系统，建议使用path.resolve(...)
        Files.copy(scrPath,Paths.get("G:/","00_copy.txt"),StandardCopyOption.REPLACE_EXISTING);
    }
}
