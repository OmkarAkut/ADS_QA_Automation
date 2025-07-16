package webdriver.maps.mapbuilder;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.PageFactory;

public class BuildMap {

    //@SuppressWarnings("unchecked")
    public static <T> T getInstance(WebDriver driver, Class<T> clazz){
        T genericPage = PageFactory.initElements(driver, clazz);
        MapConfig page = (MapConfig)genericPage;
        page.setWebDriver(driver);
        return (T)page;
    }
}
