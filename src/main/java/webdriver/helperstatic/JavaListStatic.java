package webdriver.helperstatic;

import static org.junit.Assert.fail;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;
import java.util.stream.Collectors;
import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.WebDriverWait;

public class JavaListStatic extends JavaStatic {

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
        	Thread.sleep(500);
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
        	//Shilpa 22.08.2022 updated xpath
            optionsContainer = driver.findElement(By.xpath("//div[text()='" + firstOptionText + "']/.."));
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
                    Thread.sleep(4000);
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
        ArrayList<String> availableListStrings = new ArrayList();
        
       
        for(WebElement webelement : list){
            if(printout){
                System.out.println(webelement.getText() + ",");
            }
            
           //venkat Adding javascript 15.09.2022
            ((JavascriptExecutor)driver).executeScript("arguments[0].scrollIntoView(true);", webelement);
            Thread.sleep(500);
            availableListStrings.add(webelement.getText());
        }
        return availableListStrings;
    }

}
