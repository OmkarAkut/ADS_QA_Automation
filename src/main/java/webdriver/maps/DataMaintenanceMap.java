package webdriver.maps;

import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import webdriver.maps.mapbuilder.MapConfig;

/*
This map file includes all elements found on the Data Maintenance Subtabs.
Section 1: Maintain Data
Section 2: Load Data
Section 3: Utilities
 */

public class DataMaintenanceMap extends MapConfig {

    /******Maintain Data******/
    @FindBy(xpath = "//*[contains(text(),'Tree')]")
    private WebElement dataMaintenanceTree;
    public WebElement getDataMaintenanceTree() {return dataMaintenanceTree;}

    @FindBy(xpath = "//*[contains(@class,'grid-cell-treecolumn')]/*[contains(text(),'Contracting')]/img[1]")
    private WebElement dataMaintenanceTreeExpanderContracting;
    public WebElement getDataMaintenanceTreeExpanderContracting() {return dataMaintenanceTreeExpanderContracting;}

    @FindBy(xpath = "//*[contains(@class,'grid-cell-treecolumn')]/*[contains(text(),'Costing')]/img[1]")
    private WebElement dataMaintenanceTreeExpanderCosting;
    public WebElement getDataMaintenanceTreeExpanderCosting() {return dataMaintenanceTreeExpanderCosting;}

    @FindBy(xpath = "//*[contains(@class,'grid-cell-treecolumn')]/*[contains(text(),'Episode')]/img[1]")
    private WebElement dataMaintenanceTreeExpanderEpisode;
    public WebElement getDataMaintenanceTreeExpanderEpisode() {return dataMaintenanceTreeExpanderEpisode;}

    @FindBy(xpath = "//*[contains(@class,'grid-cell-treecolumn')]/*[contains(text(),'General')]/img[1]")
    private WebElement dataMaintenanceTreeExpanderGeneral;
    public WebElement getDataMaintenanceTreeExpanderGeneral() {return dataMaintenanceTreeExpanderGeneral;}

    @FindBy(xpath = "//*[contains(text(),'A - Z')]")
    private WebElement dataMaintenanceAZ;
    public WebElement getDataMaintenanceAZ() {return dataMaintenanceAZ;}

    @FindBy(xpath = "//div[contains(@class,'x-box-item x-btn-default')]/em/button/span[text()='Calculate']")
    private WebElement maintainDataPageButtonCalculate;
    public WebElement getMaintainDataPageButtonCalculate() {return maintainDataPageButtonCalculate;}

    /******End Maintain Data******/

    /******Load Data******/
    @FindBy(xpath = "//*[contains(text(),'New')]")
    private WebElement dataMaintenanceLoadDataButtonNew;
    public WebElement getDataMaintenanceLoadDataButtonNew() {return dataMaintenanceLoadDataButtonNew;}

    @FindBy(xpath = "//*[contains(text(),'Continue')]")
    private WebElement dataMaintenanceLoadDataButtonContinue;
    public WebElement getDataMaintenanceLoadDataButtonContinue() {return dataMaintenanceLoadDataButtonContinue;}

    @FindBy(xpath = "//*[contains(text(),'Close')]")
    private WebElement dataMaintenanceLoadDataButtonClose;
    public WebElement getDataMaintenanceLoadDataButtonClose() {return dataMaintenanceLoadDataButtonClose;}
    /******End Load Data******/

    /******Utilities******/
    private String utilityElement = "//div[contains(@id,'dataview')]/div[text()='";
    public String getUtilityElementXpath() {return utilityElement;}
     /*


    @FindBy(xpath = "//div[contains(@id,'dataview')]/div[text()='Encounters With No EFRs Report']")
    @FindBy(xpath = "//div[contains(@id,'dataview')]/div[text()='Allocate HCPCS and Bundled Charges']")
    @FindBy(xpath = "//div[contains(@id,'dataview')]/div[text()='Reset Encounter APC Charges']")
    @FindBy(xpath = "//div[contains(@id,'dataview')]/div[text()='Match Encounter ID']")*/

    @FindBy(xpath = "//*[contains(@class,'itemWrap') and text()='Encounters With No EFRs Report']")
    private WebElement getUtilitiesPageEncountersWithNoEfrsReport;
    public WebElement getUtilitiesPageEncountersWithNoEfrsReport() {return getUtilitiesPageEncountersWithNoEfrsReport;}

    @FindBy(xpath = "//*[contains(@class,'itemWrap') and text()='Encounters With Negative Charge Balance Report']")
    private WebElement getUtilitiesPageEncountersWithNegativeChargeBalanceReport;
    public WebElement getUtilitiesPageEncountersWithNegativeChargeBalanceReport() {return getUtilitiesPageEncountersWithNegativeChargeBalanceReport;}

    @FindBy(xpath = "//*[contains(@class,'itemWrap') and text()='Encounters With Zero Charge Balance Report']")
    private WebElement getUtilitiesPageEncountersWithZeroChargeBalanceReport;
    public WebElement getUtilitiesPageEncountersWithZeroChargeBalanceReport() {return getUtilitiesPageEncountersWithZeroChargeBalanceReport;}

    @FindBy(xpath = "//*[contains(@class,'itemWrap') and text()='Encounters With No Charges Report']")
    private WebElement getUtilitiesPageEncountersWithNoChargesReport;
    public WebElement getUtilitiesPageEncountersWithNoChargesReport() {return getUtilitiesPageEncountersWithNoChargesReport;}

    //*[@class='itemWrap' and text()='Encounters With No Charges Report']
    /******End Utilities******/
}
