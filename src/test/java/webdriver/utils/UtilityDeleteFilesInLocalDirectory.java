package webdriver.utils;

import java.io.IOException;
import webdriver.globalstatic.SetupStatic;
import webdriver.utilities.FileUtility;

/** This utility deletes files in local directory,
 * such as where files are automatically downloaded (Downloads).
 * As a safeguard, the path to the directory must contain 'Downloads'.
 * Additionally, only zip (.zip) and log (.log) files will be deleted.
 * This path is set on webdriver.properties file as the 'path_to_saved_files_directory' property.
 */
public class UtilityDeleteFilesInLocalDirectory extends SetupStatic {

  //static FileUtility filer = new FileUtility();

  public static void main(String[] args) throws IOException {
    //System.out.println(setupSavedFilesDirectoryPath());
    //filer.deleteFilesInDirectory(setupSavedFilesDirectoryPath());
  }

}

