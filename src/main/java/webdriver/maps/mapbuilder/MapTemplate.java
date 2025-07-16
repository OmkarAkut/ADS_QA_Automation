package webdriver.maps.mapbuilder;

import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

public class MapTemplate extends MapConfig {
    //Harris Logo - sample object map patter for a single element
    @FindBy(xpath = "//*[@id=\"component-1018\"]")
    private WebElement harrisLogo;
    public WebElement getHarrisLogo() {return harrisLogo;}
}
