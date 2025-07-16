package webdriver.utilities;
import static org.junit.Assert.fail;

import java.io.*;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.Enumeration;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.Stream;
import java.util.zip.ZipEntry;
import java.util.zip.ZipFile;

/** Covers reading Files and Input Streams.
 * Basic Java file functions.
 * Search for file using partial file name and return absolute path.
 * Write file/stream to a list or String that can be tested (e.g., with assert contains).
 * Get files from zip directory and convert to list.
 * There is a delete utility in the utils directory.
 */
public class FileUtility {

  public void deleteFilesInDirectory(String directoryPath) {
    if (directoryPath.contains("Downloads")) {
      System.out.println("Deleting Files in Directory: " + directoryPath);
      File directory = getFile(directoryPath);
      System.out.println("Directory contains: " + directory.list().length + " items");
      int deletedFileCount = 0;
      if (directory.list().length > 0) {
        for (File file : directory.listFiles()) {
          if (!file.isDirectory()) {
            if (file.getName().contains(".log") || file.getName().contains(".zip")) {
              System.out.println("Deleting File: " + file.getName());
              //file.delete();
              deletedFileCount++;
            }
          }
        }
        System.out.println(deletedFileCount + " files deleted (.log or .zip only)");
      }
    } else {
      System.out.println("Error: Directory path must contain 'Downloads'");
    }
  }

  public String convertListOfStringsToSingleString(List<String> list) {
    return String.join("", list);
  }

  public String getFilesFromDirectoryAndReturnTargetFilePath(String directoryPath, String partialFileName, String fileSuffix) {
    String fullFilePath = null;
    if (directoryPath != null) {
      File directory = new File(directoryPath);
      File[] files = directory.listFiles();
      for (File file : files) {
        if (file.getName().contains(partialFileName) && file.getName().contains(fileSuffix)) {
          String fullFileName = file.getName();
          System.out.println(fullFileName);
          fullFilePath = file.getAbsolutePath();
          System.out.println("Absolute Path: " + fullFilePath);
          break;
        }
      }
    } else {
      fail("Path to Saved Files directory not set in driver.properties");
    }
    return fullFilePath;
  }

  public String[] parseFileRowIntoArray(String row) {
    String[] dataArray;
    dataArray = row.split(",");
    return dataArray;
  }

  public File[] getFileNamesFromZipDirectory(String path) {
    File file = new File(path);
    File[] fileNames = file.listFiles();
    return fileNames;
  }

  public File getFile(String path) {
    File file = new File(path);
    return file;
  }

  public void readAndPrintFile(File file) throws IOException {
    BufferedReader buffer = null;
    try {
      FileReader reader = new FileReader(file);
      buffer = new BufferedReader(reader);
      String fileLine;
      while ((fileLine = buffer.readLine()) != null) {
        System.out.println(fileLine);
      }
      buffer.close();
    } catch (Exception e) {
      e.printStackTrace();
    } finally {
      buffer.close();
    }
  }

  public void writeToFile(File file, String string) throws IOException {
    BufferedWriter bwriter = null;
    try {
      FileWriter writer = new FileWriter(file);
      bwriter = new BufferedWriter(writer);
      bwriter.write(string);
    } catch (Exception e) {
      e.printStackTrace();
    } finally {
      bwriter.close();
    }
  }

  public List<String> convertFileLinesToList(String filePath) throws IOException {
    List<String> list = Files.readAllLines(Paths.get(filePath));
    return list;
  }

  public String convertFileToString(String filePath) {
    String fileAsString = null;
    try {
      fileAsString = Files.readString(Paths.get(filePath));
      System.out.println(fileAsString);
    } catch (IOException e) {
      e.printStackTrace();
    }
    return fileAsString;
  }

  public List<String> convertInputStreamLinesToList(InputStream inputStream) {
    List<String> list = null;
    try (Stream<String> stream = new BufferedReader(new InputStreamReader(inputStream)).lines()) {
      list = stream.collect(Collectors.toList());
    } catch (Exception e) {
      e.printStackTrace();
    }
    return list;
  }

  public List<String> convertFileInZipDirectoryToList(String zipDirectoryPath, String zipPartialFileName) throws IOException {
    List<String> list = null;
    if (zipDirectoryPath != null) {
      InputStream inputStream = null;
      final ZipFile zipDirectory = new ZipFile(zipDirectoryPath);
      System.out.println("Iterating over zip file : " + zipDirectory);
      try {
        Enumeration<? extends ZipEntry> entries = zipDirectory.entries();
        while (entries.hasMoreElements()) {
          ZipEntry entry = entries.nextElement();
          if (entry.getName().contains(zipPartialFileName)) {
            System.out.println("File Name: " + entry.getName());
            inputStream = zipDirectory.getInputStream(entry);
            break;
          }
        }
        list = convertInputStreamLinesToList(inputStream);
      } catch (Exception e) {
        e.printStackTrace();
      } finally {
        inputStream.close();
      }
    } else {
      fail("Path to zip directory is null");
    }
    return list;
  }

}
