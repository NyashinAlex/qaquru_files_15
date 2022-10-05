import com.codeborne.pdftest.PDF;
import com.codeborne.xlstest.XLS;
import com.monitorjbl.xlsx.StreamingReader;
import com.opencsv.CSVReader;
import net.sf.jxls.transformer.Workbook;
import org.junit.jupiter.api.Test;

import java.io.File;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.nio.charset.StandardCharsets;
import java.util.List;
import java.util.zip.ZipEntry;
import java.util.zip.ZipFile;
import java.util.zip.ZipInputStream;

import static org.assertj.core.api.Assertions.assertThat;

public class FileTest {

    ClassLoader cl = FileTest.class.getClassLoader();

    @Test
    void zipTest() throws Exception {

        ZipFile zf = new ZipFile(new File("src/test/resources/example.zip"));
        ZipInputStream is = new ZipInputStream(cl.getResourceAsStream("example.zip"));
        ZipEntry entry;
        while((entry = is.getNextEntry()) != null) {
//            org.assertj.core.api.Assertions.assertThat(entry.getName()).isEqualTo(entry.getName());
            if (entry.getName().equals("example_csv.csv")) {
                try (InputStream inputStream = zf.getInputStream(entry);
                     CSVReader reader = new CSVReader(new InputStreamReader(inputStream, StandardCharsets.UTF_8))) {
                    List<String[]> content = reader.readAll();
                    String[] x = content.get(0);
                    String y = x[0];
                    System.out.println(y);
                }
            } else if (entry.getName().equals("example_xlsx.xlsx")) {
                try (InputStream inputStream = zf.getInputStream(entry)){
//                    Workbook workbook = StreamingReader.builder()
//                            .rowCacheSize(0)
//                            .bufferSize(0)
//                            .open(inputStream);

                    XLS xls = new XLS(inputStream);
//                    assertThat(
//                            xls.excel.getSheetAt(0)
//                                    .getRow(5)
//                                    .getCell(5)
//                                    .getStringCellValue()
//                    ).isEqualTo("Dulce");
                    System.out.println("xls");
                }
            } else if (entry.getName().equals("example_pdf.pdf")) {
                try (InputStream inputStream = zf.getInputStream(entry)){
                    PDF pdf = new PDF(inputStream);
                    System.out.println("pdf");
                }
            }
        }
    }
}