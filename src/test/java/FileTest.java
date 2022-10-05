import com.opencsv.CSVReader;
import org.junit.jupiter.api.Test;

import java.io.File;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.nio.charset.StandardCharsets;
import java.util.List;
import java.util.zip.ZipEntry;
import java.util.zip.ZipFile;
import java.util.zip.ZipInputStream;

public class FileTest {

    ClassLoader cl = FileTest.class.getClassLoader();

    @Test
    void zipTest() throws Exception {

        ZipFile zf = new ZipFile(new File("src/test/resources/example_csv.zip"));
        ZipInputStream is = new ZipInputStream(cl.getResourceAsStream("example_csv.zip"));
        ZipEntry entry;
        while((entry = is.getNextEntry()) != null) {
            org.assertj.core.api.Assertions.assertThat(entry.getName()).isEqualTo(entry.getName());
            try (InputStream inputStream = zf.getInputStream(entry);
                 CSVReader reader = new CSVReader(new InputStreamReader(inputStream, StandardCharsets.UTF_8))) {
                List<String[]> content = reader.readAll();
                String[] x = content.get(0);
                String y = x[0];
                System.out.println(y);
            }
        }
    }
}