package webdriver.utilities;

import java.util.List;
import org.openqa.selenium.WebElement;

public class Print {

  public void printDataFormattedListFromWebElementList(String listName, List<WebElement> list) {
    System.out.println("public String[] " + listName + " = {");
    for (WebElement option : list) {
      System.out.println("   " + '"' + option.getText() + '"' + ",");
    }
    System.out.println("};");
  }

  public void printDataFormattedListFromStringList(String listName, List<String> list) {
    System.out.println("public String[] " + listName + " = {");
    for (String option : list) {
      System.out.println("   " + '"' + option + '"' + ",");
    }
    System.out.println("};");
  }

  public void printList(List list) {
    for (Object object : list) {
      System.out.println(object);
    }
  }
}
