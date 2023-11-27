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

    @FindBy(xpath = "//span[contains(@id,'dynamicwindow')][text()='New Terminal Server Session']")
    private WebElement dataMaintenanceNewTerminalPopUp;
    public WebElement dataMaintenanceNewTerminalPopUp() {return dataMaintenanceNewTerminalPopUp;}
    
    @FindBy(xpath = "//span[text()='Open']//parent::button")
    private WebElement dataMaintenanceOpenButton;
    public WebElement dataMaintenanceOpenButton() {return dataMaintenanceOpenButton;}
    //*[@class='itemWrap' and text()='Encounters With No Charges Report']
    /******End Utilities******/
    
//    Omkar 17/8/2023 : xpath changes for 11.2
    //Shilpa
//    @FindBy(xpath = "//label[text()='Encounter Population for Assign/Remove']//following::div[contains(@id,'displayfield')]")
    @FindBy(xpath = "//span[text()='Encounter Population for Assign/Remove']")
    private static WebElement getPopulationValue;
    public static WebElement getPopulationValue() {return getPopulationValue;}
    
    @FindBy(xpath = "//label[text()='Service Model']//following::table[4]//tr[contains(@class,'x-grid-row treeWhite')]/td/div")
    private  List<WebElement> getServiceModelList;
    public List<WebElement> getServiceModelList() {return getServiceModelList;}
    
//    Omkar 16/8/2023 : xpath changes for 11.2
//    @FindBy(xpath = "//span[text()='Filter']//parent::button")
//    @FindBy(xpath = "//span[text()='Filter']//parent::span")
    //Shilpa update xpath for 11.2 on 11.23.2023
  @FindBy(xpath = "(//span[text()='Filter']//parent::span)[1]")

    private WebElement encounterButtonFilter;
    public WebElement getencounterButtonFilter() {return encounterButtonFilter;}
    
//    Omkar 16/8/2023 : xpath changes for 11.2
//    @FindBy(xpath = "//span[text()='Edit']//parent::button")
    @FindBy(xpath = "//span[text()='Edit']//parent::span")
    private WebElement encounterButtonEdit;
    public WebElement getencounterButtonEdit() {return encounterButtonEdit;}
    
//    Omkar 18/8/2023 : xpath changes for 11.2
//    @FindBy(xpath = "(//button/span[text()='Services'])[1]")
    @FindBy(xpath = "(//span[text()='Services'])[1]")
	private static WebElement getServicesTabEncounter;
	public static WebElement getServicesTabEncounter() {return getServicesTabEncounter;}
	
	@FindBy(xpath = "//div[contains(@class,'x-toolbar x-docked x-toolbar-footer')]//span[text()='Assign']")
	private static WebElement getAssignButtonEncounter;
	public static WebElement getAssignButtonEncounter() {return getAssignButtonEncounter;}
	
//	Omkar 16/8/2023 : xpath changes for 11.2
//	@FindBy(xpath = "(//div[contains(@id,'commontbar')]//span[text()='New']//parent::button)[1]")
	@FindBy(xpath = "(//div[contains(@id,'commontbar')]//span[text()='New']//parent::span)[1]")
	private static WebElement getLoadDataNewButton;
	public static WebElement getLoadDataNewButton() {return getLoadDataNewButton;}
	
//	Omkar 16/8/2023 : xpath changes for 11.2
//	@FindBy(xpath = "(//div[contains(@id,'commontbar')]//span[text()='Filter']//parent::button)[1]")
	@FindBy(xpath = "(//div[contains(@id,'commontbar')]//span[text()='Filter']//parent::span)[1]")
	private static WebElement getLoadDataFilterButton;
	public static WebElement getLoadDataFilterButton() {return getLoadDataFilterButton;}
	
//	Omkar 16/8/2023 : xpath changes for 11.2
//	@FindBy(xpath = "(//div[contains(@id,'commontbar')]//span[text()='Delete']//parent::button)[1]")
	@FindBy(xpath = "(//div[contains(@id,'commontbar')]//span[text()='Delete']//parent::span)[1]")
	private static WebElement getLoadDataDeleteButton;
	public static WebElement getLoadDataDeleteButton() {return getLoadDataDeleteButton;}
	
//	Omkar 16/8/2023 : xpath changes for 11.2
//	@FindBy(xpath = "(//div[contains(@id,'commontbar')]//span[text()='Edit']//parent::button)[1]")
	@FindBy(xpath = "(//div[contains(@id,'commontbar')]//span[text()='Edit']//parent::span)[1]")
	private static WebElement getLoadDataEditButton;
	public static WebElement getLoadDataEditButton() {return getLoadDataEditButton;}
	
	@FindBy(name = "chargeMasterCode")
	private static WebElement getChargeMaster;
	public static WebElement getChargeMaster() {return getChargeMaster;}
	
	@FindBy(xpath = "//span[text()='Price List Master']//following::input[@name='priceListId']")
	private static WebElement getPriceListMaster;
	public static WebElement getPriceListMaster() {return getPriceListMaster;}
	
	@FindBy(name = "logLocation")
	private static WebElement getLogLoc;
	public static WebElement getLogLoc() {return getLogLoc;}
	
	@FindBy(xpath = "//div[contains(@class,'x-toolbar x-docked x-toolbar-footer')]//span[text()='Save & Create New']")
	private static WebElement getSaveandCreateNewButton;
	public static WebElement getSaveandCreateNewButton() {return getSaveandCreateNewButton;}
	
//	Omkar 16/8/2023 : xpath changes for 11.2
//	@FindBy(xpath = "//div[contains(@class,'x-toolbar x-docked x-toolbar-footer')]//span[text()='Calculate']//parent::button")
//	@FindBy(xpath = "//div[contains(@class,'x-toolbar x-docked x-toolbar-footer')]//span[text()='Calculate']//parent::span")
	//Shilpa update xpath for 11.2 on 11.27.2023
	@FindBy(xpath = "(//span[text()='Calculate']/../../..)[3]")
	private static WebElement getCalculateButton;
	public static WebElement getCalculateButton() {return getCalculateButton;}
	
	//Shilpa added xpath for 11.2 on 11.27.2023
		@FindBy(xpath = "//span[text()='Save & Continue']/../../..")
		private static WebElement getSaveContinueButton;
		public static WebElement getSaveContinueButton() {return getSaveContinueButton;}
	
//	Omkar 16/8/2023 : xpath changes for 11.2
//	@FindBy(xpath = "//h1[text()='Price Items']//following::span[text()='Filter']//parent::button")
	@FindBy(xpath = "//h1[text()='Price Items']//following::span[text()='Filter']//parent::span")
	private static WebElement getPriceItemFilterButton;
	public static WebElement getPriceItemFilterButton() {return getPriceItemFilterButton;}
	
//	Omkar 16/8/2023 : xpath changes for 11.2
//	@FindBy(xpath = "//h1[text()='Price Items']//following::span[text()='Clear Filter']//parent::button")
	@FindBy(xpath = "//h1[text()='Price Items']//following::span[text()='Clear Filter']//parent::span")
	private static WebElement getPriceItemClearFilterButton;
	public static WebElement getPriceItemClearFilterButton() {return getPriceItemClearFilterButton;}
	
	@FindBy(xpath = "//span[text()='Department Code']//following::div[contains(@id,'dynamicGrid')]//table//tr[2]/td[11]/div")
	private static WebElement getPriceItemValue;
	public static WebElement getPriceItemValue() {return getPriceItemValue;}
	
	@FindBy(xpath = "//span[text()='Department Code']//following::div[contains(@id,'dynamicGrid')]//table//tr/td[5]/div")
	private static List<WebElement> getPriceItemDeptCode;
	public static List<WebElement> getPriceItemDeptCode() {return getPriceItemDeptCode;}

	
	@FindBy(xpath = "//div[contains(@id,'dynamicGrid')]//table//tr/td[5]/div")
	private static List<WebElement> getEncounterItemList;
	public static List<WebElement> getEncounterItemList() {return getEncounterItemList;}
	
	@FindBy(xpath = "//div[contains(@id,'dynamicGrid')]//table//tr/td[5]/div(//div[contains(@id,'boundlist')]/ul/li[text()='Admission Source Code']/..)")
	private static WebElement getPopulationFieldDropdownOptions;
	public static WebElement getPopulationFieldDropdownOptions() {return getPopulationFieldDropdownOptions;}
	
//	Omkar 16/8/2023 : xpath changes for 11.2
//	@FindBy(xpath = "//span[text()='Add']//parent::button")
	@FindBy(xpath = "//span[text()='Add']//parent::span")
	private static WebElement getPopulationAddButton;
	public static WebElement getPopulationAddButton() {return getPopulationAddButton;}
	
//	Omkar 16/8/2023 : xpath changes for 11.2
//	@FindBy(xpath = "//span[text()='Show Size']//parent::button")
	@FindBy(xpath = "//span[text()='Show Size']//parent::span")
	private static WebElement getPopulationShowSizeButton;
	public static WebElement getPopulationShowSizeButton() {return getPopulationShowSizeButton;}
	
}
