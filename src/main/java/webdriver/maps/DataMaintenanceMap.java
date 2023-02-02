package webdriver.maps;

import java.util.List;

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
    
    //Shilpa
    @FindBy(xpath = "//label[text()='Encounter Population for Assign/Remove']//following::div[contains(@id,'displayfield')]")
    private static WebElement getPopulationValue;
    public static WebElement getPopulationValue() {return getPopulationValue;}
    
    @FindBy(xpath = "//label[text()='Service Model']//following::table[4]//tr[contains(@class,'x-grid-row treeWhite')]/td/div")
    private  List<WebElement> getServiceModelList;
    public List<WebElement> getServiceModelList() {return getServiceModelList;}
    
    @FindBy(xpath = "//span[text()='Filter']//parent::button")
    private WebElement encounterButtonFilter;
    public WebElement getencounterButtonFilter() {return encounterButtonFilter;}
    
    @FindBy(xpath = "//span[text()='Edit']//parent::button")
    private WebElement encounterButtonEdit;
    public WebElement getencounterButtonEdit() {return encounterButtonEdit;}
    
    @FindBy(xpath = "(//button/span[text()='Services'])[1]")
	private static WebElement getServicesTabEncounter;
	public static WebElement getServicesTabEncounter() {return getServicesTabEncounter;}
	
	@FindBy(xpath = "//div[contains(@class,'x-toolbar x-docked x-toolbar-footer')]//span[text()='Assign']")
	private static WebElement getAssignButtonEncounter;
	public static WebElement getAssignButtonEncounter() {return getAssignButtonEncounter;}
	
	@FindBy(xpath = "(//div[contains(@id,'commontbar')]//span[text()='New']//parent::button)[1]")
	private static WebElement getLoadDataNewButton;
	public static WebElement getLoadDataNewButton() {return getLoadDataNewButton;}
	
	@FindBy(xpath = "(//div[contains(@id,'commontbar')]//span[text()='Filter']//parent::button)[1]")
	private static WebElement getLoadDataFilterButton;
	public static WebElement getLoadDataFilterButton() {return getLoadDataFilterButton;}
	
	@FindBy(name = "chargeMasterCode")
	private static WebElement getChargeMaster;
	public static WebElement getChargeMaster() {return getChargeMaster;}
	
	@FindBy(name = "priceListId")
	private static WebElement getPriceListMaster;
	public static WebElement getPriceListMaster() {return getPriceListMaster;}
	
	@FindBy(name = "logLocation")
	private static WebElement getLogLoc;
	public static WebElement getLogLoc() {return getLogLoc;}
	
	@FindBy(xpath = "//div[contains(@class,'x-toolbar x-docked x-toolbar-footer')]//span[text()='Save & Create New']")
	private static WebElement getSaveandCreateNewButton;
	public static WebElement getSaveandCreateNewButton() {return getSaveandCreateNewButton;}
	
	@FindBy(xpath = "//div[contains(@class,'x-toolbar x-docked x-toolbar-footer')]//span[text()='Calculate']//parent::button")
	private static WebElement getCalculateButton;
	public static WebElement getCalculateButton() {return getCalculateButton;}
	
	@FindBy(xpath = "//h1[text()='Price Items']//following::span[text()='Filter']//parent::button")
	private static WebElement getPriceItemFilterButton;
	public static WebElement getPriceItemFilterButton() {return getPriceItemFilterButton;}
	
	@FindBy(xpath = "//h1[text()='Price Items']//following::span[text()='Clear Filter']//parent::button")
	private static WebElement getPriceItemClearFilterButton;
	public static WebElement getPriceItemClearFilterButton() {return getPriceItemClearFilterButton;}
	
	@FindBy(xpath = "//span[text()='Department Code']//following::div[contains(@id,'dynamicGrid')]//table//tr[2]/td[11]/div")
	private static WebElement getPriceItemValue;
	public static WebElement getPriceItemValue() {return getPriceItemValue;}
	
	@FindBy(xpath = "//span[text()='Department Code']//following::div[contains(@id,'dynamicGrid')]//table//tr[2]/td[5]/div")
	private static WebElement getPriceItemDeptCode;
	public static WebElement getPriceItemDeptCode() {return getPriceItemDeptCode;}
}
