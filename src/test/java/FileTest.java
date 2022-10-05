import org.junit.jupiter.api.Test;

import java.io.File;
import java.io.InputStream;
import java.util.zip.ZipEntry;
import java.util.zip.ZipFile;
import java.util.zip.ZipInputStream;

public class FileTest {

    ClassLoader cl = FileTest.class.getClassLoader();

    @Test
    void zipTest() throws Exception {

//        try (InputStream is = cl.getResourceAsStream("exemple_zip.zip")) {
//            assert is != null;
//            try (ZipInputStream zis = new ZipInputStream(is)) {
//                ZipEntry entry;
//
//                while ((entry = zis.getNextEntry()) != null) {
//                    String entryName = entry.getName();
////                    byte[] fileSource = is.readAllBytes();
////                    InputStream isFile = cl.getResourceAsStream(file);
//                    System.out.println(entryName);
//
//                }
//            }
//        }
        ZipFile zf = new ZipFile(new File("src/test/resources/exemple_zip.zip"));
        ZipInputStream is = new ZipInputStream(cl.getResourceAsStream("exemple_zip.zip"));
        ZipEntry entry;
        while((entry = is.getNextEntry()) != null) {
            org.assertj.core.api.Assertions.assertThat(entry.getName()).isEqualTo(entry.getName());
            try (InputStream inputStream = zf.getInputStream(entry)) {
                // проверки/
                System.out.println("");
            }
        }
    }
}