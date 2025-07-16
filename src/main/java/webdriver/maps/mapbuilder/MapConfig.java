package webdriver.maps.mapbuilder;

import org.openqa.selenium.WebDriver;

public abstract class MapConfig {
    protected WebDriver driver;
    protected int DEFAULT_TIMEOUT_SECONDS = 30;

    protected void setWebDriver(WebDriver driver){
        this.driver = driver;
    }
}
