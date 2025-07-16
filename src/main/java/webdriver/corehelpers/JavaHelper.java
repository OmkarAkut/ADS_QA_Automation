package webdriver.corehelpers;

import static org.junit.Assert.fail;

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
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebElement;
import webdriver.core.Login;

public class JavaHelper extends Login {

    public void compareDoubleValues(double actualValue, double expectedValue) {
        System.out.println("The Actual Value is: " + actualValue);
        System.out.println("The Expected Value is: " + expectedValue);
        if (actualValue == expectedValue) {
            System.out.println("The expected Total Change value is equal to the actual Total Change value.");
        } else {
            System.out.println("ERROR: The expected Total Change value is not equal to the actual Total Change value.");
        }
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

    public String getNumbersFromStringWithRegex(String string) throws InterruptedException {
        Pattern p = Pattern.compile("\\d+"); //regex pattern
        Matcher m = p.matcher(string);    //string to search
        String numbers = null;
        while(m.find()) {
            numbers = m.group();
        }
        return numbers;
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

    public static double getRandomDoubleAndSetDecimalPlaces(double min, double max, int decimalPlaces, boolean printout) {
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
        String random = Double.toString(getRandomDoubleAndSetDecimalPlaces(min, max, decimalPlaces, printout));
        return random;
    }

    public static String getRandomStringWithNoDecimalPlaces(double min, double max, int decimalPlaces, boolean printout) {
        DecimalFormat df = new DecimalFormat("#");
        Double dbl = getRandomDoubleAndSetDecimalPlaces(min, max, decimalPlaces, printout);
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

    public static String getRandomStringWithNoDecimalPlaces(double min, double max, int decimalPlaces, String fieldLabel, boolean printout) {
        DecimalFormat df = new DecimalFormat("#");
        Double dbl = getRandomDoubleAndSetDecimalPlaces(min, max, decimalPlaces, fieldLabel, printout);
        String random = df.format(dbl);
        return random;
    }

    public static String javaGetCurrentDate(String dateFormat) {
        DateTimeFormatter dtf = DateTimeFormatter.ofPattern(dateFormat);
        LocalDateTime now = LocalDateTime.now();
        return dtf.format(now);
    }

    public void javaPrintList(List<String> list) {
        for (String item : list) {
            System.out.print(item + ", ");
        }
    }

    public List<WebElement> javaMakeListOfWebElements(WebElement listElement, String optionTags) {
        List<WebElement> list = listElement.findElements(By.tagName(optionTags));
        return list;
    }

    public List<WebElement> javaMakeListOfWebElements2(WebElement listContainerElement, String partialXpath) {
        List<WebElement> list = listContainerElement.findElements(By.xpath("." + partialXpath));
        return list;
    }

    public List<String> javaMakeListOfStrings(List<WebElement> list) {
        ArrayList<String> stringList = new ArrayList<>();
        for (WebElement webelement : list) {
            stringList.add(webelement.getText());
        }
        return stringList;
    }

    public List<String> javaMakeListOfStrings(List<WebElement> list, String partialXpath) {
        ArrayList<String> stringList = new ArrayList<>();
        for (WebElement webelement : list) {
            stringList.add(webelement.findElement(By.xpath("." + partialXpath)).getText());
        }
        return stringList;
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

    public WebElement javaGetListContainerElementFromFirstOptionText(String firstOptionText) {
        WebElement optionsContainer = null;
        try {
            optionsContainer =
                    driver.findElement(By.xpath("//div/div/ul/li[text()='" + firstOptionText + "']/.."));
        } catch (Exception e) {
            fail("Options container element not found");
        }
        return optionsContainer;
    }

    public WebElement javaGetListContainerElementFromAnyOptionText(String optionText) {
        WebElement optionsContainer = null;
        try {
            optionsContainer =
                    driver.findElement(By.xpath("//div/div/ul/li[text()='" + optionText + "']/.."));
        } catch (Exception e) {
            fail("Options container element not found");
        }
        return optionsContainer;
    }

    public List<String> javaMakeListOfStringsFromOptionTextAndTagName(String anyOptionText, String listItemsTag) {
        ArrayList<String> actualList = new ArrayList<>();
        WebElement optionsContainer = null;
        try {
            optionsContainer =
                    driver.findElement(By.xpath("//ul/descendant::*[text()='" + anyOptionText + "']/.."));
        } catch (Exception e) {
            fail("Options container element not found");
        }
        List<WebElement> list = optionsContainer.findElements(By.tagName(listItemsTag));
        for (WebElement webelement : list) {
            try {
                actualList.add(webelement.getText());
            } catch (Throwable e) {
                continue;
            }
        }
        return actualList;
    }

    //if optionsAttribute is set to "" (empty), then default is "tr"
    public List<String> javaMakeListOfStringsFromElementOptions(String firstOptionText, String optionsAttribute) {
        ArrayList<String> actualList = new ArrayList<>();
        WebElement optionsContainer = null;
        try {
            optionsContainer = driver.findElement(By.xpath("//div/div[text()='" + firstOptionText + "']/.."));
        } catch (Exception e) {
            fail("Options container element not found");
        }
        if (optionsAttribute.equals("") || optionsAttribute.equals(null)) {
            optionsAttribute = "tr";
        }
        List<WebElement> list = javaMakeListOfWebElements(optionsContainer, optionsAttribute);
        for (WebElement webelement : list) {
            if (webelement.getAttribute("class").contains("x-grid-row")) {
                try {
                    actualList.add(webelement.findElement(By.xpath(".//td/div")).getText());
                } catch (Throwable e) {
                    continue;
                }
            } else {
                continue;
            }
        }
        return actualList;
    }

    public List<String> javaMakeListOfStringsFromElementOptions(String firstOptionText, String optionsAttribute, int subOptionTagIndex)
            throws InterruptedException {
        ArrayList<String> actualList = new ArrayList<>();
        WebElement optionsContainer = null;
        boolean loop = true;
        int counter = 0;
        while (loop) {
            try {
                optionsContainer = driver.findElement(By.xpath("//table/descendant::tr/td/div[text()='" + firstOptionText + "']/ancestor::tbody"));
                loop = false;
            } catch (Throwable e) {
                if (counter < 30) {
                    Thread.sleep(1000);
                    counter++;
                } else {
                    fail("Options container element not found");
                }
            }
        }
        if (optionsAttribute.equals("") || optionsAttribute.equals(null)) {
            optionsAttribute = "tr";
        }
        List<WebElement> list = javaMakeListOfWebElements(optionsContainer, optionsAttribute);
        for (WebElement webelement : list) {
            if (webelement.getAttribute("class").contains("x-grid-row")) {
                try {
                    actualList.add(webelement.findElement(By.xpath(".//td["+subOptionTagIndex+"]/div")).getText());
                } catch (Throwable e) {
                    continue;
                }
            } else {
                continue;
            }
        }
        return actualList;
    }

    public List<String> javaSortListOfStringsWithComparator(List<String> list, Comparator<String> comparator, boolean printout) {
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

    public List<String> javaMakeSeparateCopyOfListOfStrings(List<String> listToCopy) {
        List<String> newList = listToCopy.stream().collect(Collectors.toList());
        return newList;
    }

    public ArrayList<String> javaListConvertListOfWebElementsToStrings(List<WebElement> list, boolean printout) throws InterruptedException {
        ArrayList<String> availableListStrings = new ArrayList<String>();
        for(WebElement webelement : list){
            if(printout){
                System.out.println(webelement.getText() + ",");
            }
            //Shilpa 10.07.2022
            ((JavascriptExecutor) driver).executeScript("arguments[0].scrollIntoView(true);", webelement);
            Thread.sleep(500); 
            availableListStrings.add(webelement.getText());
            System.out.println(webelement.getText());
        }
        return availableListStrings;
    }
}
