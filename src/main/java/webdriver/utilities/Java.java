package webdriver.utilities;

import java.math.BigDecimal;
import java.math.RoundingMode;
import java.text.DecimalFormat;
import java.text.NumberFormat;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;
import java.util.Random;
import java.util.regex.Matcher;
import java.util.regex.Pattern;
import java.util.stream.Collectors;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;

public class Java {

  public String convertListOfStringToSingleString(List<String> list) {
    return String.join("", list);
  }

  public List javaMakeListOfWebElements(WebElement listElement, String optionTags) {
    List<WebElement> list = listElement.findElements(By.tagName(optionTags));
    return list;
  }

  public List<String> javaMakeListOfStringsFromElementText(List<WebElement> list) {
    ArrayList<String> actualList = new ArrayList<>();
    for (WebElement webelement : list) {
      actualList.add(webelement.getText());
    }
    return actualList;
  }

  public List<String> javaMakeListOfStringsFromElementText(List<WebElement> list, String selectionAttribute, String selectionValue, String elementPathToText) {
    ArrayList<String> actualList = new ArrayList<>();
    for (WebElement webelement : list) {
      if (webelement.getAttribute(selectionAttribute).contains(selectionValue)) {
        try {
          actualList.add(webelement.findElement(By.xpath(elementPathToText)).getText());
        } catch (Throwable e) {
          continue;
        }
      }
    }
    return actualList;
  }

  public double convertStringToDoubleWithTwoDecimals(String string){
    double myDouble = Double.parseDouble(string);
    NumberFormat nf = NumberFormat.getNumberInstance();
    nf.setMaximumFractionDigits(2);
    if(myDouble >= 0){
      nf.setRoundingMode(RoundingMode.FLOOR);
    } else {
      nf.setRoundingMode(RoundingMode.CEILING);
    }
    String formattedString = nf.format(myDouble);
    double formattedDouble = Double.parseDouble(formattedString);
    return formattedDouble;
  }

  public double roundDoubleToTwoDecimals(double dValue){
    double formattedDecimalValue = (double)Math.round(dValue * 100000d)/100000d;
    return formattedDecimalValue;
  }

  public double roundDouble(Double vDouble, int significantDigits) {
    NumberFormat nf = NumberFormat.getNumberInstance();
    nf.setMaximumFractionDigits(significantDigits);
    if (vDouble >= 0) {
      nf.setRoundingMode(RoundingMode.FLOOR);
    } else {
      nf.setRoundingMode(RoundingMode.CEILING);
    }
    String formattedString = nf.format(vDouble);
    double formattedDouble = Double.parseDouble(formattedString);
    return formattedDouble;
  }

  public double truncateDouble(Double startDouble, int significantDigits) {
    NumberFormat nf = NumberFormat.getNumberInstance();
    nf.setMaximumFractionDigits(significantDigits);
    String formattedString = nf.format(startDouble);
    double formattedDouble = Double.parseDouble(formattedString);
    return formattedDouble;
  }

  public List<String> javaSortListOfStringsWithComparator(
          List<String> list, Comparator<String> comparator, boolean printout) {
//        Comparator<String> sortBySubStringsYearThenMonth = Comparator.comparing((String s) -> s.substring(4, 8))
//                .thenComparing((String s) -> s.substring(0, 2));
    list.sort(comparator);
    if(printout){
      System.out.println("Sorted List");
      for (String item : list) {
        System.out.println(item);
      }
    }
    return list;
  }

  public List<String> javaListMakeCopy(List<String> listToCopy) {
    List<String> newList = listToCopy.stream().collect(Collectors.toList());
    return newList;
  }

  public ArrayList<String> javaListConvertWebElementsToStrings(List<WebElement> list, boolean printout) throws InterruptedException {
    ArrayList<String> availableListStrings = new ArrayList();
    for(WebElement webelement : list){
      if(printout){
        System.out.println(webelement.getText());
      }
      availableListStrings.add(webelement.getText());
    }
    return availableListStrings;
  }

  /** Returns a random number up to, but not including, the user set upperLimit. Starts with and includes the value 1. */
  public static int javaGetRandomNumber(int upperLimit, boolean printout) {
    Random rand = new Random();
    int randomNumber = rand.nextInt(upperLimit);
    if(randomNumber == 0){
      randomNumber = 1;
    }
    if(printout){
      System.out.println("Generated random number: " + randomNumber);
    }
    return randomNumber;
  }

  public static double javaGetRandomDoubleAndSetDecimalPlaces(double min, double max, int decimalPlaces, boolean printout) {
    double d = (Math.random() * ((max - min) + 1)) + min;
    BigDecimal bd = new BigDecimal(d).setScale(decimalPlaces, RoundingMode.DOWN);
    double rounded = bd.doubleValue();
    if (printout) {
      System.out.println("Original random value: " + d);
      System.out.println("Original value - after setting decimal places and rounding down:" + rounded);
    }
    return rounded;
  }

  public static String getRandomStringAndSetDecimalPlaces(double min, double max, int decimalPlaces, boolean printout) {
    String random = Double.toString(javaGetRandomDoubleAndSetDecimalPlaces(min, max, decimalPlaces, printout));
    return random;
  }

  public static String getRandomStringWithNoDecimalPlaces(double min, double max, int decimalPlaces, boolean printout) {
    DecimalFormat df = new DecimalFormat("#");
    Double dbl = javaGetRandomDoubleAndSetDecimalPlaces(min, max, decimalPlaces, printout);
    String random = df.format(dbl);
    return random;
  }

  public static double getRandomDoubleAndSetDecimalPlaces(double min, double max, int decimalPlaces, String fieldLabel, boolean printout) {
    double d = (Math.random() * ((max - min) + 1)) + min;
    BigDecimal bd = new BigDecimal(d).setScale(decimalPlaces, RoundingMode.DOWN);
    double rounded = bd.doubleValue();
    if (printout) {
      System.out.println("Initial generated random value for " + fieldLabel + ": " + d);
      System.out.println("Final random value for " + fieldLabel + " after setting decimal places and rounding down: " + rounded);
    }
    return rounded;
  }

  public static String getRandomStringAndSetDecimalPlaces(double min, double max, int decimalPlaces, String fieldLabel, boolean printout) {
    String random = Double.toString(getRandomDoubleAndSetDecimalPlaces(min, max, decimalPlaces, fieldLabel, printout));
    return random;
  }

  public String javaGetCurrentDate(String dateFormat) {
    DateTimeFormatter dtf = DateTimeFormatter.ofPattern(dateFormat);
    LocalDateTime now = LocalDateTime.now();
    System.out.println(dtf.format(now));
    return dtf.format(now);
  }

  public String[] javaStringSplitIntoArray(String string, String splitOnPattern, boolean printout) {
    String[] splitArray = string.split(splitOnPattern);
    if (printout) {
      for (String str : splitArray) {
        System.out.println("Values after splitting: " + str);
      }
    }
    return splitArray;
  }
}
