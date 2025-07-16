package webdriver.templates;

import static org.junit.Assert.assertTrue;

import java.io.File;
import java.io.IOException;
import java.util.Enumeration;
import java.util.zip.ZipEntry;
import java.util.zip.ZipFile;
import org.junit.Ignore;
import org.junit.Test;
import webdriver.globalstatic.DriverStatic;
import webdriver.scripts.regression.generalcalculations.GeneralCalculationsData;
import webdriver.utilities.FileUtility;

public class JavaTemplate extends DriverStatic {

  FileUtility filer = new FileUtility();

  @Test
  public void test00PrintOut() {
    //System.out.println(GeneralCalculationsData.getMedicalServiceAssignmentSql);
  }


  @Ignore
  @Test
  public void test00RemoveExistingReports() throws IOException {
    // filer.getFileNamesFromZipDirectory("C:\\Users\\eo89116\\Downloads");
    for (File file : filer.getFileNamesFromZipDirectory("C:\\Users\\eo89116\\Downloads")) {
      if (file.getName().contains(".zip")) {
        System.out.println(file.getName());
        System.out.println(file.getAbsolutePath());

        ZipFile zip = new ZipFile(file.getAbsolutePath());
        System.out.println(zip.getName());
        Enumeration<? extends ZipEntry> entries = zip.entries();

        while (entries.hasMoreElements()) {
          ZipEntry entry = entries.nextElement();
          String name = entry.getName();

          System.out.println(name);
          assertTrue(
              name.contains(
                  "automationappadmin1_Encounters With Zero Charge Balance Report_03102020052259.zip"));
          assertTrue(name.matches("^[^0-9]*[0-9]{6}[^\\d]*$"));
        }
      }
    }
  }

  @Ignore
  @Test
  public void test01Regex() {
    String name =
        "automationappadmin1_Encounters With Zero Charge Balance Report_03102020052259.zip";
    assertTrue(
        name.contains(
            "automationappadmin1_Encounters With Zero Charge Balance Report_03102020052259.zip"));
    assertTrue(name.matches("^(.*\\d{13}.*)$"));
  }
}
