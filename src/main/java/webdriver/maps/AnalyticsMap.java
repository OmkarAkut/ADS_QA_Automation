package webdriver.maps;

import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import webdriver.maps.mapbuilder.MapConfig;

/*
This map file includes all elements found on the Analytics SubTabs.
Section 1: Analytics Refresh Scenarios
Section 2: Customize Analytics
Section 3: Customize Analytics Sessions
Section 4: Analytics Server Desktop
Section 5: Analytics Server Desktop Sessions
 */

public class AnalyticsMap extends MapConfig {

    /******Analytics Refresh Scenarios******/

    @FindBy(xpath = "//*[contains(@onclick, 'anaexfd.htm') and @class='listhelpLnk']")
    private WebElement  analyticsRefreshScenariosPageHelpLink;
    public WebElement getAnalyticsRefreshScenariosPageHelpLink() {return analyticsRefreshScenariosPageHelpLink;}

    @FindBy(xpath = "//*[contains(@id,'analytics_analyticsextracts_tabId')]/descendant::span[contains(text(),'New')]")
    private WebElement analyticsRefreshScenariosButtonNew;
    public WebElement getAnalyticsRefreshScenariosButtonNew() {return analyticsRefreshScenariosButtonNew;}
    @FindBy(xpath = "//*[contains(@id,'analytics_analyticsextracts_tabId')]/descendant::span[contains(text(),'Edit')]")
    private WebElement analyticsRefreshScenariosButtonEdit;
    public WebElement getAnalyticsRefreshScenariosButtonEdit() {return analyticsRefreshScenariosButtonEdit;}
    @FindBy(xpath = "//*[contains(@id,'analytics_analyticsextracts_tabId')]/descendant::span[contains(text(),'Copy')]")
    private WebElement analyticsRefreshScenariosButtonCopy;
    public WebElement getAnalyticsRefreshScenariosButtonCopy() {return analyticsRefreshScenariosButtonCopy;}
    @FindBy(xpath = "//*[contains(@id,'analytics_analyticsextracts_tabId')]/descendant::span[contains(text(),'Delete')]")
    private WebElement analyticsRefreshScenariosButtonDelete;
    public WebElement getAnalyticsRefreshScenariosButtonDelete() {return analyticsRefreshScenariosButtonDelete;}
    /******End Analytics Refresh Scenarios******/

    /******Customize Analytics******/
    @FindBy(xpath = "//*[contains(@id,'analytics_customizeanalytics_tabId-body')]/descendant::span[contains(text(),'New')]")
    private WebElement customizeAnalyticsButtonNew;
    public WebElement getCustomizeAnalyticsButtonNew() {return customizeAnalyticsButtonNew;}
    @FindBy(xpath = "//*[contains(@id,'analytics_customizeanalytics_tabId-body')]/descendant::span[contains(text(),'Continue')]")
    private WebElement customizeAnalyticsButtonContinue;
    public WebElement getCustomizeAnalyticsButtonContinue() {return customizeAnalyticsButtonContinue;}
    @FindBy(xpath = "//*[contains(@id,'analytics_customizeanalytics_tabId-body')]/descendant::span[contains(text(),'Close')]")
    private WebElement customizeAnalyticsButtonClose;
    public WebElement getCustomizeAnalyticsButtonClose() {return customizeAnalyticsButtonClose;}
    /******End Customize Analytics******/

    /******Customize Analytics Sessions******/
    @FindBy(xpath = "//*[contains(@id,'analytics_customizeanalyticssessions_tabId-body')]/descendant::span[contains(text(),'Continue')]")
    private WebElement customizeAnalyticsSessionsButtonContinue;
    public WebElement getCustomizeAnalyticsSessionsButtonContinue() {return customizeAnalyticsSessionsButtonContinue;}
    @FindBy(xpath = "//*[contains(@id,'analytics_customizeanalyticssessions_tabId-body')]/descendant::span[contains(text(),'Close')]")
    private WebElement customizeAnalyticsSessionsButtonClose;
    public WebElement getCustomizeAnalyticsSessionsButtonClose() {return customizeAnalyticsSessionsButtonClose;}
    /******End Customize Analytics Sessions******/

    /******Analytics Server Desktop******/
    @FindBy(xpath = "//*[contains(@id,'analytics_analyticsserverdesktop_tabId-body')]/descendant::span[contains(text(),'New')]")
    private WebElement analyticsServerDesktopButtonNew;
    public WebElement getAnalyticsServerDesktopButtonNew() {return analyticsServerDesktopButtonNew;}
    @FindBy(xpath = "//*[contains(@id,'analytics_analyticsserverdesktop_tabId-body')]/descendant::span[contains(text(),'Continue')]")
    private WebElement analyticsServerDesktopButtonContinue;
    public WebElement getAnalyticsServerDesktopButtonContinue() {return analyticsServerDesktopButtonContinue;}
    @FindBy(xpath = "//*[contains(@id,'analytics_analyticsserverdesktop_tabId-body')]/descendant::span[contains(text(),'Close')]")
    private WebElement analyticsServerDesktopButtonClose;
    public WebElement getAnalyticsServerDesktopButtonClose() {return analyticsServerDesktopButtonClose;}
    /******End Analytics Server Desktop******/

    /******Analytics Server Desktop Sessions******/
    @FindBy(xpath = "//*[contains(@id,'analytics_analyticsserverdesktopsessions_tabId-body')]/descendant::span[contains(text(),'Continue')]")
    private WebElement analyticsServerDesktopSessionsButtonContinue;
    public WebElement getAnalyticsServerDesktopSessionsButtonContinue() {return analyticsServerDesktopSessionsButtonContinue;}
    @FindBy(xpath = "//*[contains(@id,'analytics_analyticsserverdesktopsessions_tabId-body')]/descendant::span[contains(text(),'Close')]")
    private WebElement analyticsServerDesktopSessionsButtonClose;
    public WebElement getAnalyticsServerDesktopSessionsButtonClose() {return analyticsServerDesktopSessionsButtonClose;}
    /******Analytics Server Desktop Sessions******/
}
