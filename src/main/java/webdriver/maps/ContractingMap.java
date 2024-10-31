package webdriver.maps;

import java.util.HashMap;
import java.util.List;

import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

import webdriver.maps.mapbuilder.MapConfig;

public class ContractingMap extends MapConfig {

	// ===== Contracting Tab > Contractual Allowance Export Page ===== //

//	@FindBy(xpath = "//div[contains(@id,'contractualallowances')]/descendant::div[contains(@id,'rownumberer') and @class='x-column-header-inner']/span[@class='x-column-header-text']")
//Shilpa update xpath for 11.2 on 2.1.2024
	@FindBy(xpath = "//div[contains(@id,'dynamicGrid')]/descendant::div[contains(@id,'rownumberer') and @class='x-column-header-text']/span/../..")
	private WebElement contractualAllowanceExportPageTableCornerCell;
	public WebElement getContractualAllowanceExportPageTableCornerCell() {return contractualAllowanceExportPageTableCornerCell;}

	@FindBy(xpath = "//*[contains(@onclick, 'mdconalls.htm') and @class='listhelpLnk']")
	private WebElement contractualAllowanceExportPageHelpLink;
	public WebElement getContractualAllowanceExportPageHelpLink() {return contractualAllowanceExportPageHelpLink;}

	@FindBy(xpath = "//div[contains(@id, 'contractualallowanceslayout')]/descendant::span[text()='New']")
	private WebElement contractualAllowanceExportPageButtonNew;
	public WebElement getContractualAllowanceExportPageButtonNew() {return contractualAllowanceExportPageButtonNew;}

	@FindBy(xpath = "//div[contains(@id, 'contractualallowanceslayout')]/descendant::span[text()='Edit']")
	private WebElement contractualAllowanceExportPageButtonEdit;
	public WebElement getContractualAllowanceExportPageButtonEdit() {return contractualAllowanceExportPageButtonEdit;}

	@FindBy(xpath = "//div[contains(@id, 'contractualallowanceslayout')]/descendant::span[text()='Filter']")
	private WebElement contractualAllowanceExportPageButtonFilter;
	public WebElement getContractualAllowanceExportPageButtonFilter() {return contractualAllowanceExportPageButtonFilter;}

	@FindBy(xpath = "//div[contains(@id, 'contractualallowanceslayout')]/descendant::span[text()='Clear Filter']")
	private WebElement contractualAllowanceExportPageButtonClearFilter;
	public WebElement getContractualAllowanceExportPageButtonClearFilter() {return contractualAllowanceExportPageButtonClearFilter;}

	@FindBy(xpath = "//div[contains(@id, 'contractualallowanceslayout')]/descendant::span[text()='Delete']")
	private WebElement contractualAllowanceExportPageButtonDelete;
	public WebElement getContractualAllowanceExportPageButtonDelete() {return contractualAllowanceExportPageButtonDelete;}

	// ===== End Contractual Allowance Export Page ===== //
	
	// ===== Contracting Tab > APC Fee Schedule Page ===== //
	@FindBy(xpath = "(//h1[text()='APC Fee Schedule Masters']//following::span[text()='Edit']//parent::span)[1]")
	private static WebElement apcFeeScheduleMastersPageEditButton;
	public static WebElement apcFeeScheduleMastersPageEditButton() {return apcFeeScheduleMastersPageEditButton;}

	// ===== END Contracting Tab > APC Fee Schedule Page ===== //
	
	// ===== Contracting Tab > APC Allocation Page ===== //

	@FindBy(xpath = "//*[contains(@onclick, 'mdumfd.htm') and @class='listhelpLnk']")
	private WebElement apcAllocationPageHelpLink;
	public WebElement getApcAllocationPageHelpLink() {return apcAllocationPageHelpLink;}

	@FindBy(xpath = "//*[text()='APC Allocation']/ancestor::div[contains(@id,'contracting_apcallocation')]/descendant::div[contains(@id,'dataview')]/div[text()='Encounters With No Charges Report']")
	private WebElement apcAllocationPageDataViewEncountersWithNoChargesReport;
	public WebElement getApcAllocationPageDataViewEncountersWithNoChargesReport() {return apcAllocationPageDataViewEncountersWithNoChargesReport;}

	@FindBy(xpath = "//*[text()='APC Allocation']/ancestor::div[contains(@id,'contracting_apcallocation')]/descendant::div[contains(@id,'dataview')]/div[text()='Encounters With Zero Charge Balance Report']")
	private WebElement apcAllocationPageDataViewEncountersWithZeroChargeBalanceReport;
	public WebElement getApcAllocationPageDataViewEncountersWithZeroChargeBalanceReport() {return apcAllocationPageDataViewEncountersWithZeroChargeBalanceReport;}

	@FindBy(xpath = "//*[text()='APC Allocation']/ancestor::div[contains(@id,'contracting_apcallocation')]/descendant::div[contains(@id,'dataview')]/div[text()='Encounters With Negative Charge Balance Report']")
	private WebElement apcAllocationPageDataViewEncountersWithNoEfrsReport;
	public WebElement getApcAllocationPageDataViewEncountersWithNoEfrsReport() {return apcAllocationPageDataViewEncountersWithNoEfrsReport;}

	@FindBy(xpath = "//*[text()='APC Allocation']/ancestor::div[contains(@id,'contracting_apcallocation')]/descendant::div[contains(@id,'dataview')]/div[text()='Encounters With No EFRs Report']")
	private WebElement apcAllocationPageDataViewEncountersWithNegativeChargeBalanceReport;
	public WebElement getApcAllocationPageDataViewEncountersWithNegativeChargeBalanceReport() {return apcAllocationPageDataViewEncountersWithNegativeChargeBalanceReport;}

	@FindBy(xpath = "//*[text()='APC Allocation']/ancestor::div[contains(@id,'contracting_apcallocation')]/descendant::div[contains(@id,'dataview')]/div[text()='Allocate HCPCS and Bundled Charges']")
	private WebElement apcAllocationPageDataViewAllocateHcpcsAndBundledCharges;
	public WebElement getApcAllocationPageDataViewAllocateHcpcsAndBundledCharges() {return apcAllocationPageDataViewAllocateHcpcsAndBundledCharges;}

	@FindBy(xpath = "//*[text()='APC Allocation']/ancestor::div[contains(@id,'contracting_apcallocation')]/descendant::div[contains(@id,'dataview')]/div[text()='Reset Encounter APC Charges']")
	private WebElement apcAllocationPageDataViewResetEncounterApcCharges;
	public WebElement getApcAllocationPageDataViewResetEncounterApcCharges() {return apcAllocationPageDataViewResetEncounterApcCharges;}

	@FindBy(xpath = "//*[text()='APC Allocation']/ancestor::div[contains(@id,'contracting_apcallocation')]/descendant::div[contains(@id,'dataview')]/div[text()='Match Encounter ID']")
	private WebElement apcAllocationPageDataViewMatchEncounterId;
	public WebElement getApcAllocationPageDataViewMatchEncounterId() {return apcAllocationPageDataViewMatchEncounterId;}

	// ===== End APC Allocation Page ===== //

	
//	Omkar 21/06/2023 : xpath changes for 11.2 
//	public static String getContractingName = "(//td[contains(@class,'x-grid-cell-treecolumn')]/*[text()='Contracting'])[1]";
	public static String getContractingName = "//td[contains(@class,'x-grid-cell-treecolumn')]//span[text()='Contracting']";

	//	Omkar 14/04/2023 : xpath changes for 11.2 (done for episodes)
	//	@FindBy(xpath = "//div[contains(@class,'horzOverflow ')]//div[contains(@id,'acommontbar')]//span[text()='New']//parent::button")
	@FindBy(xpath = "//div[contains(@class,'horzOverflow ')]//div[contains(@id,'acommontbar')]//span[text()='New']/parent::span")
	public   WebElement NewContractModelButton1;

	public   WebElement getNewContractModelButton() {
		return NewContractModelButton1;
	}

	//	Omkar 26/5/2023 : xpath changes for 11.2
	//	@FindBy(xpath = "//div[contains(@class,'x-window-closable ')]//div[contains(@id,'window')]//span[text()='New Contract Model']")
	@FindBy(xpath = "//div[contains(@class,'x-window-closable ')]//div[contains(@id,'window')]//div[text()='New Contract Model']")
	public static WebElement getNewContractModelPopUp;
	public static WebElement getNewContractModelPopUp() { return getNewContractModelPopUp;}

	@FindBy(xpath = "//div[contains(@class,'contractFrmCls')]//following::input[contains(@id,'textfield')]")
	public static WebElement getContractModelNameInput;
	public static WebElement getContractModelNameInput() {return getContractModelNameInput;}

	//	Omkar 26/5/2023 : xpath changes for 11.2
	//	@FindBy(xpath = "//div[contains(@class,'contractFrmCls')]//following::table//following::span[text()='Add Providers']//parent::button")
	@FindBy(xpath = "//div[contains(@class, 'x-panel x-panel-default')]//span[text()='Add Providers']")
	public static WebElement getContractModelAddProviderBtn;
	public static WebElement getContractModelAddProviderBtn() {return getContractModelAddProviderBtn;}

	//	Omkar 26/5/2023 : xpath changes for 11.2
	//	@FindBy(xpath = "//div[contains(@class,'x-window-header-draggable')]//following::span[text()='Add Providers']//parent::div")
	@FindBy(xpath = "//div[text()='Add Providers']/..")
	public static WebElement getContractModelAddProviderPopup;
	public static WebElement getContractModelAddProviderPopup() {return getContractModelAddProviderPopup;}

//	Omkar 26/6/2023 : xpath changes for 11.2
//	@FindBy(xpath = "//div[contains(@class,'contractFrmCls')]//following::table//following::span[text()='Save & Close']//parent::button")
	//shilpa updated xpath 10/12/2023 for 11.2
//	@FindBy(xpath = "//div[contains(@class,'contractFrmCls')]//following::table//following::span[text()='Save & Close']")
	@FindBy(xpath = "//div[contains(@class,'contractFrmCls')]//following::a//following::span[text()='Save & Close']")
	private static WebElement getSaveContractModel;
	public WebElement getSaveContractModel() {return getSaveContractModel;}

//	Omkar 22/6/2023 : xpath changes for 11.2
//	@FindBy(xpath = "//div[contains(@class,'x-window-header-draggable')]//following::span[text()='Select']//parent::button")
	@FindBy(xpath = "//span[(@class ='x-btn-button x-btn-button-default-small x-btn-text  x-btn-icon x-btn-icon-left x-btn-button-center ')]//span[text()='Select']")
	private static WebElement getSelectItem;
	public static WebElement getSelectItem() {return getSelectItem;}

	//	Omkar 18/5/2023 : xpath changes for 11.2
	//	@FindBy(xpath = "(//div[contains(@id,'dynamicwindow')])//div[contains(@class,'multiSelPneCls')]//following::span[text()='Remove']//parent::button")
	@FindBy(xpath = "//div[contains(@class,'multiSelPneCls')]//following::span[text()='Remove']/..")
	private static WebElement getRemoveItem;
	public WebElement getRemoveItem() {return getRemoveItem;}

	//	Omkar 18/5/2023 : xpath changes for 11.2
	//	@FindBy(xpath = "(//div[contains(@id,'dynamicwindow')])//div[contains(@class,'multiSelPneCls')]//following::span[text()='Apply']//parent::button")
	@FindBy(xpath = "//div[contains(@class,'multiSelPneCls')]//following::span[text()='Apply']/..")
	private static WebElement getApplySelections;
	public WebElement getApplySelections() {return getApplySelections;}

	@FindBy(xpath = "//div[contains(@class,'contractFrmCls')]//ul/li")
	private static WebElement getProviderText;
	public static WebElement getProviderText() {return getProviderText;}
//shilpa updated xpath 11.2  10/11/2023
	@FindBy(xpath = "((//div[contains(@id,'adynamicgrid')]//following::div[contains(@class,'x-panel-body')]//table//tr/td[1]/div))")
	private static List<WebElement> getCostingModelElementList;
	public static List<WebElement> getCostingModelElementList() {return getCostingModelElementList;}

//	Omkar 26/6/2023 : xpath changes for 11.2
//	@FindBy(xpath = "//div[contains(@class,'modalLibBarCls')]/div//span[text()='New']//parent::button")
	@FindBy(xpath = "//div[contains(@class,'modalLibBarCls')]/div//span[text()='New']")
	private static WebElement getNewContractFolderBtn;
	public WebElement getNewContractFolderBtn() {return getNewContractFolderBtn;}

//	Omkar 26/6/2023 : xpath changes for 11.2
//	@FindBy(xpath = "//div[contains(@class,'modalLibBarCls')]/div//span[text()='Rename']//parent::button")
	@FindBy(xpath = "//div[contains(@class,'modalLibBarCls')]/div//span[text()='Rename']")
	private static WebElement getRenameContractFolderBtn;
	public WebElement getRenameContractFolderBtn() {return getRenameContractFolderBtn;}

//	Omkar 26/6/2023 : xpath changes for 11.2
//	@FindBy(xpath = "//div[contains(@class,'modalLibBarCls')]/div//span[text()='Delete']//parent::button")
	@FindBy(xpath = "//div[contains(@class,'modalLibBarCls')]/div//span[text()='Delete']")
	private static WebElement getDeleteContractFolderBtn;
	public WebElement getDeleteContractFolderBtn() {return getDeleteContractFolderBtn;}

//	Omkar 26/6/2023 : xpath changes for 11.2
//	@FindBy(xpath = "//div[contains(@class,'modalLibBarCls')]/div//span[text()='New']//parent::button")
	@FindBy(xpath = "//div[contains(@class,'modalLibBarCls')]/div//span[text()='New']")
	private static WebElement getNewFolderPopUp;
	public WebElement getNewFolderPopUp() {return getNewFolderPopUp;}

	//	Omkar 14/04/2023 : xpath changes for 11.2
	//	@FindBy(xpath = "//div[contains(@class,'x-window-header-text-container')]//following::td[contains(@id,'textfield')]/input")
	@FindBy(xpath = "//div[contains(@class,'x-form-item-body')]//following::div[contains(@id,'textfield')]/input")
	private  WebElement getNewFolderNameInput;
	public  WebElement getNewFolderNameInput() {return getNewFolderNameInput;}

	//	Omkar 14/04/2023 : xpath changes for 11.2
	//	@FindBy(xpath = "//div[contains(@class,'x-window-header-text-container')]//following::span[text()='Save & Close']")
	@FindBy(xpath = "//div[contains(@class,'x-box-target')]//following::span[text()='Save & Close']")
	private static  WebElement getNewFolderNameSave;
	public static  WebElement getNewFolderNameSave() {return getNewFolderNameSave;}
	
	@FindBy(xpath = "//span[text()='Rename']/../../..")
	private static  WebElement getNewFolderRename;
	public static  WebElement getNewFolderRename() {return getNewFolderRename;}

//	Omkar 4/8/2023 : xpath changes for 11.2
//	@FindBy(xpath = "(//td[contains(@class,'x-grid-cell-treecolumn')]/*[text()='Contracting']/img[@class='x-tree-elbow-end-plus x-tree-expander'])")
	@FindBy(xpath = "//div[@id='modelFoldertree-body']//td[contains(@class,'x-grid-cell-treecolumn')]//span[text()='Contracting']/../div[contains(@class,'x-tree-elbow')]")
	private static WebElement getContractingTreeExpand;
	public WebElement getContractingTreeExpand() {return getContractingTreeExpand;}

	//	Omkar 2/5/2023 : xpath changes for 11.2
	//	@FindBy(xpath = "//div[contains(@class,'horzOverflow')]/div//span[text()='Filter']//parent::button")
	@FindBy(xpath = "//div[contains(@class,'horzOverflow')]/div//span[text()='Filter']//parent::span")
	private static WebElement ContractModelButtonFilter;
	public static WebElement getContractModelButtonFilter() {return ContractModelButtonFilter;}

	//	Omkar 2/5/2023 : xpath changes for 11.2
	//	@FindBy(xpath = "//div[contains(@id,'headercontainer')]//div[text()='Remove']")
	@FindBy(xpath ="//div[contains(@id,'filter')]//span[text()='Remove']/parent::span")
	private WebElement ContractModelRemoveFilterButton;
	public WebElement getContractModelRemoveFilterButton() {return ContractModelRemoveFilterButton;}

	@FindBy(xpath = "//div[contains(@id,'toolbar')]//span[text()='Apply Filter']")
	private static WebElement ContractModelApplyFilterButton;
	public static WebElement getContractModelApplyFilterButton() {return ContractModelApplyFilterButton;}

	//	Omkar 2/5/2023 : xpath changes for 11.2
	//	@FindBy(xpath = "//div[contains(@id,'filtergrid')]//div[text()='Edit']//parent::td")
	@FindBy(xpath = "//div[contains(@id,'filtergrid')]//span[text()='Edit']//parent::span")
	private WebElement ContractModelEditFilterButton;
	public WebElement getContractModelEditFilterButton() {return ContractModelEditFilterButton;}

	//	Omkar 2/5/2023 : xpath changes for 11.2
	//	@FindBy(xpath = "//span[text()='Update']//parent::button")
	@FindBy(xpath = "//span[text()='Update']/parent::span")
	private WebElement ContractModelUpdateFilterButton;
	public WebElement getContractModelUpdateFilterButton() {return ContractModelUpdateFilterButton;}

//	Omkar 26/6/2023 : xpath changes for 11.2
//	@FindBy(xpath = "//div[contains(@class,'horzOverflow')]/div//span[text()='Clear Filter']//parent::button")
	//shilpa 10/11/2023 : xpath changes for 11.2
	@FindBy(xpath = "//span[text()='Clear Filter']")
	private WebElement ContractModelClearFilter;
	public WebElement getContractModelClearFilter() {return ContractModelClearFilter;}

	@FindBy(xpath = "//div[contains(@class,'areaModelTitle')]")
	private WebElement ContractModelHeader;
	public WebElement getContractModelHeader() {return ContractModelHeader;}
	
//	Omkar 26/6/2023 : xpath changes for 11.2
//	@FindBy(xpath = "//div[contains(@class,'horzOverflow')]/div//span[text()='Copy']//parent::button")
	@FindBy(xpath = "//div[contains(@class,'horzOverflow')]/div//span[text()='Copy']")
	private WebElement ContractModelButtonCopy;
	public WebElement getContractModelButtonCopy() {return ContractModelButtonCopy;}
	
	@FindBy(xpath = "//span[text()='Save As']")
	private WebElement ContractModelButtonSaveAs;
	public WebElement getContractModelButtonSaveAs() {return ContractModelButtonSaveAs;}

//	Omkar 26/6/2023 : xpath changes for 11.2
//	@FindBy(xpath = "//div[contains(@class,'horzOverflow')]/div//span[text()='Paste']//parent::button")
	@FindBy(xpath = "//div[contains(@class,'horzOverflow')]/div//span[text()='Paste']")
	private WebElement ContractModelButtonPaste;
	public WebElement getContractModelButtonPaste() {return ContractModelButtonPaste;}

	@FindBy(xpath = "//div[contains(@class,'contractFrmCls ')]//input[contains(@id,'textfield')]")
	private WebElement ContractModelPastePopup;
	public WebElement getContractModelPastePopup() {return ContractModelPastePopup;}

	@FindBy(xpath = "//div[contains(@class,'contractFrmCls ')]//input[contains(@id,'textfield')]")
	private WebElement ContractModelPasteNameInput;
	public WebElement getContractModelPasteNameInput() {return ContractModelPasteNameInput;}

	//	Omkar 14/04/2023 : xpath changes for 11.2
	//	@FindBy(xpath = "//span[text()='Save & Close']//parent::button")
	@FindBy(xpath = "//span[text()='Save & Close']//parent::span")
	private  WebElement ContractModelSaveCopy;
	public  WebElement getContractModelSaveCopy() {return ContractModelSaveCopy;}

	//Shilpa 10/31/2024 : added xpath for warning message 
	@FindBy(xpath = "//span[text()='Continue']")
	private  WebElement ContractModelContinue;
	public  WebElement getContractModelContinue() {return ContractModelContinue;}
	
	//Shilpa 10/31/2024 : added xpath for Delete button for PAtient responsibility pane
		@FindBy(xpath = "//div[contains(@id,'patientfinancialresponsibilitymaingrid')]//following::div[contains(@class,'acommonDelBtn ')]")
		private  WebElement ContractModelDeletebtnPatient;
		public  WebElement getContractModelDeletebtnPatient() {return ContractModelDeletebtnPatient;}
	
//	Omkar 26/6/2023 : xpath changes for 11.2
//	@FindBy(xpath = "//div[contains(@class,'horzOverflow')]/div//span[text()='Delete']//parent::button")
	@FindBy(xpath = "//div[contains(@class,'horzOverflow')]/div//span[text()='Delete']")
	private WebElement ContractModelDeleteButton;
	public WebElement getContractModelDeleteButton() {return ContractModelDeleteButton;}

	//shilpa updated xpath 12/10/2023 for 11.2
	@FindBy(xpath = "//div//h1[text()=' Warning ']")
	private WebElement ContractModelDeletePopUp;
	public WebElement getContractModelDeletePopUp() {return ContractModelDeletePopUp;}

	@FindBy(xpath = "(//div[contains(@class,'x-toolbar-footer')]//following::span[text()='Cancel'])[1]")
	private WebElement ContractModelCancelButtonInPopUp;
	public WebElement getContractModelCancelButtonInPopUp() {return ContractModelCancelButtonInPopUp;}

	@FindBy(xpath = "(//div[contains(@class,'x-toolbar-footer')]//following::span[text()='Delete'])[1]")
	private WebElement ContractModelDeleteButtonInPopUp;
	public WebElement getContractModelDeleteButtonInPopUp() {return ContractModelDeleteButtonInPopUp;
	}

	@FindBy(xpath = "//*[contains(@id,'button')][text()='Remove']")
	private WebElement ContractModelButtonColumnsToDisplayModalRemove;
	public WebElement getContractModelButtonColumnsToDisplayModalRemove() {return ContractModelButtonColumnsToDisplayModalRemove;}

//	Omkar 21/6/2023 : xpath changes for 11.2
//	@FindBy(xpath = "//div[contains(@class,'horzOverflow')]/div//span[text()='Export']//parent::button")
	@FindBy(xpath = "//div[contains(@class,'horzOverflow')]/div//span[text()='Export']")
	private WebElement ContractModelExportButton;
	public WebElement getContractModelExportButton() {return ContractModelExportButton;}

//	Omkar 21/6/2023 : xpath changes for 11.2
//	@FindBy(xpath = "//div[contains(@class,'horzOverflow')]/div//span[text()='Import']//parent::button")
	@FindBy(xpath = "//div[contains(@class,'horzOverflow')]/div//span[text()='Import']")
	private WebElement ContractModelImportButton;
	public WebElement getContractModelImportButton() {return ContractModelImportButton;}

//	Omkar 21/6/2023 : xpath changes for 11.2
//	@FindBy(xpath = "//div[contains(@id,'exportwindow')]//span[text()='Export Data']")
	@FindBy(xpath = "//div[contains(@id,'exportwindow')]//div[text()='Export Data']")
	private WebElement ContractModelExportPopUp;
	public WebElement getContractModelExportPopUp() {return ContractModelExportPopUp;}

	@FindBy(xpath = "//div[contains(@id,'importwindow')]//span[text()='Import Data']")
	private WebElement ContractModelImportPopUp;
	public WebElement getContractModelImportPopUp() {return ContractModelImportPopUp;}

//	Omkar 21/6/2023 : xpath changes for 11.2
//	@FindBy(xpath = "//div[contains(@id,'exportwindow')]//span[text()='Select']//parent::button")
	@FindBy(xpath = "//div[contains(@id,'exportwindow')]//span[text()='Select']")
	private WebElement ContractModelSelectFileButton;
	public WebElement getContractModelSelectFileButton() {return ContractModelSelectFileButton;}

//	Omkar 21/6/2023 : xpath changes for 11.2
//	@FindBy(xpath = "//div[contains(@id,'importwindow')]//span[text()='Select']//parent::button")
	//@FindBy(xpath = "//div[contains(@id,'importwindow')]//span[text()='Select']")
	//Shilpa update xpath for 11.2 on 11.16.2023
	@FindBy(xpath = "//div[contains(@id,'importwindow')]//span[text()='Select']/../../..")
	private WebElement ContractModelImportSelectFileButton;
	public WebElement getContractModelImportSelectFileButton() {return ContractModelImportSelectFileButton;}

//	Omkar 21/6/2023 : xpath changes for 11.2
//	@FindBy(xpath = "//div[contains(@class,'x-closable')]//div[contains(@id,'dynamicwindow')]//span[text()='Add Contract Model']")
	@FindBy(xpath = "//div[contains(@class,'x-closable')]//div[contains(@id,'dynamicwindow')]//div[text()='Add Contract Model']")
	private WebElement ContractModelInExportPopUp;
	public WebElement getContractModelInExportPopUp() {return ContractModelInExportPopUp;}

//	Omkar 21/6/2023 : xpath changes for 11.2
//	@FindBy(xpath = "//div[contains(@class,'x-closable')]//div[contains(@id,'dynamicwindow')]//span[text()='Apply']//parent::button")
	@FindBy(xpath = "//div[contains(@class,'x-closable')]//div[contains(@id,'dynamicwindow')]//span[text()='Apply']")
	private static WebElement ContractModelApplyInExportPopUp;
	public static WebElement getContractModelApplyInExportPopUp() {return ContractModelApplyInExportPopUp;}

//	Omkar 21/6/2023 : xpath changes for 11.2
//	@FindBy(xpath = "//div[contains(@class,'x-closable')]//div[contains(@id,'dynamicwindow')]//span[text()='Apply']//parent::button")
	@FindBy(xpath = "//div[contains(@class,'x-closable')]//div[contains(@id,'dynamicwindow')]//span[text()='Apply']")
	private WebElement ContractModelExportFileSharedLoc;
	public WebElement getContractModelExportFileSharedLoc() {return ContractModelExportFileSharedLoc;}
	
	@FindBy(xpath = "(//div[contains(@class,'calcLog ')]//input[@name='hostLocation']//parent::div)[2]")
	private WebElement ContractModelDownloadFileSharedLoc;
	public WebElement getContractModelDownloadFileSharedLoc() {return ContractModelDownloadFileSharedLoc;}
	
	@FindBy(xpath = "(//li[text()='<SFTP_SERVER>/PATH/TO/CALC_LOGS_SHARED_DIRECTORY2/'])")
	private WebElement ContractModelCalcFileSharedLocOption;
	public WebElement getContractModelCalcFileSharedLocOption() {return ContractModelCalcFileSharedLocOption;}

	@FindBy(xpath = "(//input[@name='logLocation'])[2]")
	private WebElement ContractModelCalcFilename;
	public WebElement getContractModelCalcFilename() {return ContractModelCalcFilename;}
	
	@FindBy(xpath = "(//input[@name='logLocation'])")
	private WebElement ContractCalcFilename;
	public WebElement getContractCalcFilename() {return ContractCalcFilename;}
	
//	@FindBy(xpath = "//span[text()='Continue']/../../..")
	//Shilpa xpath update for 11.2 on 24.5.2024
	//Shilpa xpath update for 11.2 on 10.22.2024
	@FindBy(xpath = "(//span[text()='Continue']/../../..)")
	private WebElement ContractModelCalcContinueBtn;
	public WebElement getContractModelCalcContinueBtn() {return ContractModelCalcContinueBtn;}
	
	@FindBy(xpath = "//li[text()='<SFTP_SERVER>/PATH/TO/EXPORT_LOGS_SHARED_DIRECTORY/']")
	private WebElement ContractModelExportFileSharedLocOption;
	public WebElement getContractModelExportFileSharedLocOption() {return ContractModelExportFileSharedLocOption;}

	//	Omkar 25/4/2023 : xpath changes for 11.2
	//	@FindBy(xpath = "//div[contains(@class,'x-toolbar-footer')]//div[contains(@class,'x-toolbar-item')]//span[text()='Export']//parent::button")
	@FindBy(xpath = "//div[contains(@class,'x-toolbar-footer')]//descendant::span[text()='Export']")
	private WebElement ContractModelExportButtonInExportPopUp;
	public WebElement getContractModelExportButtonInExportPopUp() {return ContractModelExportButtonInExportPopUp;}

	//	Omkar 25/4/2023 : xpath changes for 11.2
	//	@FindBy(xpath = "//div[contains(@class,'x-toolbar-footer')]//div[contains(@class,'x-toolbar-item')]//span[text()='Import']//parent::button")
	@FindBy(xpath = "//div[contains(@class,'x-toolbar-footer')]//descendant::span[text()='Import']")
	private WebElement ContractModelImportButtonInExportPopUp;
	public WebElement getContractModelImportButtonInExportPopUp() {return ContractModelImportButtonInExportPopUp;}

//	Omkar 21/6/2023 : xpath changes for 11.2
//	@FindBy(xpath = "//span[contains(@id,'importexportstat;us')][text()='Import/Export Status']")
	@FindBy(xpath = "//div[contains(@id,'importexportstatus')][text()='Import/Export Status']")
	private WebElement ContractModelImportExportstatusPage;
	public WebElement getContractModelImportExportstatusPage() {return ContractModelImportExportstatusPage;}

	@FindBy(xpath = "//span[contains(@id,'importexportstatus')][text()='Import/Export Status']")
	private WebElement ContractModelRiskLimiterEditAvailableService;
	public WebElement getContractModelRiskLimiterEditAvailableService() {return ContractModelRiskLimiterEditAvailableService;}

	@FindBy(xpath = "//div[contains(@class,'x-toolbar-item')]//span[text()='Continue & Close']")
	private WebElement ContractModelRiskLimiterContinueCloseBtn;
	public WebElement getContractModelRiskLimiterContinueCloseBtn() {return ContractModelRiskLimiterContinueCloseBtn;}

	@FindBy(xpath = "//div[contains(@class,'x-toolbar x-docked x-toolbar-footer')]//span[text()='Cancel & Close']")
	//Shilpa update xpath for 11.2 on 11.27.2023
	//Shilpa update xpath for 11.2 on 3.1.2024
	private static WebElement ContractModelRiskLimiterCancelCloseBtn;
	public static WebElement getContractModelRiskLimiterCancelCloseBtn() {return ContractModelRiskLimiterCancelCloseBtn;}
	
	@FindBy(xpath = "//div[contains(@id,'rolesForm')]//following::span[text()='Cancel & Close']")
	private static WebElement userRoleCancelCloseBtn;
	public static WebElement userRoleCancelCloseBtn() {return userRoleCancelCloseBtn;}
	
	@FindBy(xpath = "//div[text()='GL Adjustment and Reclassification Calculation Scenario']//following::span[text()='Cancel & Close']")
	private static WebElement gLCancelCloseBtn;
	public static WebElement getgLCancelCloseBtn() {return gLCancelCloseBtn;}
	
	@FindBy(xpath = "//div[contains(@id,'messagebox')]//span[text()='Cancel & Close']/../../..")
	private static WebElement WarningCancelCloseBtn;
	public static WebElement getWarningCancelCloseBtn() {return WarningCancelCloseBtn;}

	@FindBy(xpath = "//div[contains(@class,'x-toolbar x-docked x-toolbar-footer')]//span[text()='Close']")
	private static WebElement getCloseBtn;
	public static WebElement getCloseBtn() {return getCloseBtn;}

	@FindBy(xpath = "//div[contains(@class,'x-toolbar x-docked x-toolbar-footer')]//span[text()='Close & Display']")
	private static WebElement getCloseandDisplayButton;
	public static WebElement getCloseandDisplayButton() {return getCloseandDisplayButton;}

	@FindBy(xpath = "//div[contains(@id,'messagebox')]//span[text()='Cancel & Close']")
	private static WebElement ContractModelRiskLimiterMessageBoxCancelCloseBtn;
	public static WebElement getContractModelRiskLimiterMessageBoxCancelCloseBtn() {return ContractModelRiskLimiterMessageBoxCancelCloseBtn;}

	//	Omkar 25/4/2023 : xpath changes for 11.2
	//	@FindBy(xpath = "//div[contains(@class,'x-toolbar-item')]//span[text()='Cancel & Close']")
//	@FindBy(xpath = "//span[contains(@class,'x-btn-button-center ')]//span[text()='Cancel & Close']")
//	Shilpa 1/3/2024 : xpath changes for 11.2
//	@FindBy(xpath = "//div[contains(@id,'messagebox')]//button[contains(@class,'x-btn-center')]//span[text()='Cancel & Close']")
	//Shilpa xpath update for 11.2 on 10.31.2024 
	@FindBy(xpath = "//div[contains(@id,'messagebox')]//span[contains(@class,'x-btn-button')]//span[text()='Cancel & Close']")

	private WebElement ContractModelRiskLimiterMessageBox;
	public WebElement getContractModelRiskLimiterMessageBox() {return ContractModelRiskLimiterMessageBox;}

	@FindBy(xpath = "//div[text()='Unpublished Contract Task List']")
	private WebElement ContractModelLeftPane;
	public WebElement getContractModelLeftPane() {return ContractModelLeftPane;}

//	Omkar 30/5/2023 : xpath changes for 11.2
//	@FindBy(xpath = "//span[text()='Service Model']")
	@FindBy(xpath = "//div[text()='Service Model']")
	private WebElement ContractServiceModel;
	public WebElement getContractServiceModel() {return ContractServiceModel;}

//	Omkar 30/5/2023 : xpath changes for 11.2
//	@FindBy(xpath = "//span[text()='Pricing Method']")
	@FindBy(xpath = "//div[text()='Pricing Method']")
	private WebElement ContractPricingMethod;
	public WebElement getContractPricingMethod() {return ContractPricingMethod;}

//	Omkar 30/5/2023 : xpath changes for 11.2
//	@FindBy(xpath = "//span[text()='Risk Limiter Model']")
	@FindBy(xpath = "//div[text()='Risk Limiter Model']")
	private WebElement ContractRiskLimiterModel;
	public WebElement getContractRiskLimiterModel() {return ContractRiskLimiterModel;}

	@FindBy(xpath = "//div[contains(@id,'feeForServicePaymentPanel')]//div[contains(@class,'toolbar')]//span[text()='Filter']")
	private static WebElement ContractFeeForServicePaymentFilter;
	public static WebElement getContractFeeForServicePaymentFilter() {return ContractFeeForServicePaymentFilter;}

	@FindBy(xpath = "//div[contains(@class,'x-title-text')][text()='Service Model']/../following-sibling::div")
	private static WebElement ContractFeeForServicePaymentFilterServiceModel;
	public static WebElement getContractFeeForServicePaymentFilterServiceModel() {return ContractFeeForServicePaymentFilterServiceModel;}

	@FindBy(xpath = "//label[contains(text(),'Services')]")
	private static WebElement ContractFeeForServicePaymentServices;
	public WebElement getContractFeeForServicePaymentServices() {return ContractFeeForServicePaymentServices;}

	@FindBy(xpath = "//div[contains(@id,'feeForServicePaymentPanel')]//label[contains(@class,'multiSelectorLabel ')]//following-sibling::label[text()='Service Model']")
	private static WebElement ContractFeeForServicePaymentServiceModel;
	public WebElement getContractFeeForServicePaymentServiceModel() {return ContractFeeForServicePaymentServiceModel;}

	@FindBy(xpath = "//div[contains(text(),'Edit Price for')]")
	private static WebElement ContractEditPricePopUp;
	public WebElement getContractEditPricePopUp() {return ContractEditPricePopUp;}

//	@FindBy(xpath = "//span[contains(@id,'medicareinpatientpps')]//following::span[text()='General']//following::span[text()='Select'][1]")
	//Shilpa updated 24.4.2024
	@FindBy(xpath = "//span[contains(@id,'medicareinpatientpps')]//following::div[text()='General']//following::span[text()='Select'][1]")
	private static WebElement ContractEditPricePopUpDischargeStatus;
	public WebElement getContractEditPricePopUpDischargeStatus() {return ContractEditPricePopUpDischargeStatus;}

//	@FindBy(xpath = "//span[text()='Selector on: Discharge Status Code for Transfers']")
	@FindBy(xpath = "//div[text()='Selector on: Discharge Status Code for Transfers']")
	private static WebElement ContractEditPricePopUpDischargeStatusPopUp;
	public WebElement getContractEditPricePopUpDischargeStatusPopUp() {return ContractEditPricePopUpDischargeStatusPopUp;}

//	@FindBy(xpath = "//span[text()='Selector on: Discharge Status Code for Transfers']//following::label[text()='Select All']/parent::td/input")
	//Shilpa updated 24.4.2024
	@FindBy(xpath = "//div[text()='Selector on: Discharge Status Code for Transfers']//following::label[text()='Select All']/preceding::input[1]")
	private static WebElement ContractEditPricePopUpDischargeStatusSelectAll;
	public WebElement getContractEditPricePopUpDischargeStatusSelectAll() {return ContractEditPricePopUpDischargeStatusSelectAll;}

//	@FindBy(xpath = "//span[text()='Selector on: Discharge Status Code for Transfers']//following::span[text()='Apply']")
	//Shilpa updated xpath for 11.2 on 24.4.2024
	@FindBy(xpath = "//div[text()='Selector on: Discharge Status Code for Transfers']//following::span[text()='Apply']")

	private static WebElement ContractEditPricePopUpDischargeStatusApply;
	public WebElement getContractEditPricePopUpDischargeStatusApply() {return ContractEditPricePopUpDischargeStatusApply;}

	@FindBy(xpath = "(//div[contains(@id,'boundlist')])/ul/div")
	private static List<WebElement> ContractEditPricePopUpDischargeStatusItemCount;
	public static List<WebElement> getContractEditPricePopUpDischargeStatusItemCount() {return ContractEditPricePopUpDischargeStatusItemCount;}

	@FindBy(xpath = "//span[@class='x-btn-icon pagging-tbar-last-button-multiselect']")
	private static WebElement ContractEditPricePopUpDischargeStatusLastPage;
	public static WebElement getContractEditPricePopUpDischargeStatusLastPage() {return ContractEditPricePopUpDischargeStatusLastPage;}

	@FindBy(xpath = "//input[@name='algorithmName']")
	private static WebElement ContractEditPricePopUpDischargeStatusMedicareYearDrpdwn;
	public static WebElement getContractEditPricePopUpDischargeStatusMedicareYearDrpdwn() {return ContractEditPricePopUpDischargeStatusMedicareYearDrpdwn;}

//	@FindBy(xpath = "//div[contains(@id,'boundlist')]/ul/li[text()='Oct 1, 2022 - Sept 30, 2023']/..")
	//Shilpa update xpath for 11.2 on 24.4.2024
	@FindBy(xpath = "//div[contains(@class,'boundlist')]/ul/li[text()='Oct 1, 2022 - Sept 30, 2023']/..")
	private static WebElement ContractEditPricePopUpDischargeStatusMedicareYearDrpdwnList;
	public static WebElement getContractEditPricePopUpDischargeStatusMedicareYearDrpdwnList() {return ContractEditPricePopUpDischargeStatusMedicareYearDrpdwnList;}

	@FindBy(xpath = "(//label[text()='Services Receiving Add On Technology Payment ']//following::span[text()='Select'])[1]")
	private static WebElement ContractEditPricePopUpAddPaymentServicesSelectButton;
	public static WebElement getContractEditPricePopUpAddPaymentServicesSelectButton() {return ContractEditPricePopUpAddPaymentServicesSelectButton;}

	@FindBy(xpath = "(//label[text()='Services/Charges excluded from Outlier Calculations ']//following::span[text()='Select'])[1]")
	private static WebElement ContractEditPricePopUpCostPaymentServicesSelectButton;
	public static WebElement getContractEditPricePopUpCostPaymentServicesSelectButton() {return ContractEditPricePopUpCostPaymentServicesSelectButton;}

	@FindBy(xpath = "//*[text()='Add Services']")
	private static WebElement ContractEditPricePopUpAddServices;
	public static WebElement getContractEditPricePopUpAddServices() {return ContractEditPricePopUpAddServices;}

	@FindBy(xpath = "//div[contains(@class,'x-toolbar-footer')]//span[text()='Save & Close']")
	private static WebElement ContractFeeForServicePaymentSave;
	public static WebElement getContractFeeForServicePaymentSave() {return ContractFeeForServicePaymentSave;}

	@FindBy(xpath = "(//div[contains(@class,'x-toolbar-footer')]//following::span[text()='Continue'])[1]")
	private static WebElement ContractFeeForServicePaymentWarningPopUpContinueButton;
	public static WebElement getContractFeeForServicePaymentWarningPopUpContinueButton() {return ContractFeeForServicePaymentWarningPopUpContinueButton;}

//	Omkar 26/6/2023 : xpath changes for 11.2
//	@FindBy(xpath = "//div[contains(@class,'horzOverflow')]/div//span[text()='Calculate']//parent::button")
//	@FindBy(xpath = "//div[contains(@class,'horzOverflow')]/div//span[text()='Calculate']//parent::button")
	//Shilpa 24.4.2024 xpath changes for 11.2
	@FindBy(xpath = "//div[contains(@class,'horzOverflow')]/div//span[text()='Calculate']")

	private static WebElement ContractFeeForServicePaymentCalculateButton;
	public static WebElement getContractFeeForServicePaymentCalculateButton() {return ContractFeeForServicePaymentCalculateButton;}

	@FindBy(xpath = "//span[text()='Apply']")
	private static  WebElement ContractFeeForServicePaymentApply;
	public  static WebElement getContractFeeForServicePaymentApply() {return ContractFeeForServicePaymentApply;}

//	Omkar 10/8/2023 : xpath changes for 11.2
//	@FindBy(xpath = "//div[contains(@class,'x-btn windowbtn')]//span[text()='Cancel']")
	@FindBy(xpath = "//span[contains(@class,'x-btn-button')]//span[text()='Cancel']")
	private static WebElement ContractCalculationCloseViewDialog;
	public static WebElement getContractCalculationCloseViewDialog() {return ContractCalculationCloseViewDialog;}

//	Omkar 26/6/2023 : xpath changes for 11.2
//	@FindBy(xpath = "//label[text()='Psych Combined Comorbidity Mapping File']//following::span[text()='Select']//parent::button")
//	Shilpa 16/10/2023 : xpath changes for 11.2
	@FindBy(xpath = "//input[@name='diagCatPath']")
	private static WebElement ContractFileSelect;
	public static WebElement getContractFileSelect() {return ContractFileSelect;}

//	Omkar 26/6/2023 : xpath changes for 11.2
//	@FindBy(xpath = "//span[text()='Reset']//parent::button")
	@FindBy(xpath = "//span[text()='Reset']")
	private static WebElement ContractResetButton;
	public static WebElement getContractResetButton() {return ContractResetButton;}

//	Omkar 26/6/2023 : xpath changes for 11.2
//	@FindBy(xpath = "//span[text()='Calculate']//parent::button")
	@FindBy(xpath = "//span[text()='Calculate']")
	private static WebElement ContractCalculateButton;
	public static WebElement getContractCalculateButton() {return ContractCalculateButton;}

//	Omkar 26/6/2023 : xpath changes for 11.2
//	@FindBy(xpath = "//span[text()='Save & Close']//parent::button")
	@FindBy(xpath = "//span[text()='Save & Close']")
	private static WebElement ContractSaveCloseButton;
	public static WebElement getContractSaveCloseButton() {return ContractSaveCloseButton;}

	@FindBy(name = "operIndirectMedEducAdjFactor")
	private static WebElement operatingIMEAdjustmentFactor;
	public static WebElement getoperatingIMEAdjustmentFactor() {return operatingIMEAdjustmentFactor;}

	@FindBy(name = "capitalIndrMedEducAdjFactor")
	private static WebElement capitalIMEAdjustmentFactor;
	public static WebElement getcapitalIMEAdjustmentFactor() {return capitalIMEAdjustmentFactor;}

	@FindBy(name = "operDispShareHospAdjFactor")
	private static WebElement operatingDSHAdjustmentFactor;
	public static WebElement getoperatingDSHAdjustmentFactor() {return operatingDSHAdjustmentFactor;}

	@FindBy(name = "capitalDispShareHospAdjFactor")
	private static WebElement capitalDSHAdjustmentFactor;
	public static WebElement getcapitalDSHAdjustmentFactor() {return capitalDSHAdjustmentFactor;}

	@FindBy(name = "areaWageIndex")
	private static WebElement areaWageIndex;
	public static WebElement getareaWageIndex() {return areaWageIndex;}

	@FindBy(name = "nationalOperlaborRate")
	private static WebElement nationalLaborRate;
	public static WebElement getnationalLaborRate() {return nationalLaborRate;}

	@FindBy(name = "nationalOperNonLaborRate")
	private static WebElement nationalNonLaborRate;
	public static WebElement getnationalNonLaborRate() {return nationalNonLaborRate;}

	@FindBy(name = "hospitalReadmAdjFactor")
	private static WebElement hospitalReadmissionFactor;
	public static WebElement gethospitalReadmissionFactor() {return hospitalReadmissionFactor;}

	@FindBy(name = "uncompensatedCarePayment")
	private static WebElement uncompensatedCarePayment;
	public static WebElement getuncompensatedCarePayment() {return uncompensatedCarePayment;}

	@FindBy(name = "valueBasedPurchAdjFactor")
	private static WebElement valueBasedPurchasingFactor;
	public static WebElement getvalueBasedPurchasingFactor() {return valueBasedPurchasingFactor;}

	@FindBy(name = "hacReductionPercent")
	private static WebElement Reduction;
	public static WebElement getReduction() {return Reduction;}

	@FindBy(name = "colaOperatingFactor")
	private static WebElement locationAndOperatingFactor;
	public static WebElement getlocationAndOperatingFactor() {return locationAndOperatingFactor;}

	@FindBy(name = "capitalGeographicAdjFactor")
	private static WebElement capitalGeographicAdjustmentFactor;
	public static WebElement getcapitalGeographicAdjustmentFactor() {return capitalGeographicAdjustmentFactor;}

	@FindBy(name = "colaCapitalFactor")
	private static WebElement capitalColaFactor;
	public static WebElement getcapitalColaFactor() {return capitalColaFactor;}

	@FindBy(name = "nationalCapitalRate")
	private static WebElement nationalCapitalRate;
	public static WebElement getnationalCapitalRate() {return nationalCapitalRate;}

	@FindBy(name = "operCostChargeRatio")
	private static WebElement operatingRatioOfCostCharge;
	public static WebElement getoperatingRatioOfCostCharge() {return operatingRatioOfCostCharge;}

	@FindBy(name = "capitalCostChargeRatio")
	private static WebElement capitalRatioOfCostCharge;
	public static WebElement getcapitalRatioOfCostCharge() {return capitalRatioOfCostCharge;}

	@FindBy(name = "paymentPercentage")
	private static WebElement nonBurnMarginalCostFactor;
	public static WebElement getnonBurnMarginalCostFactor() {return nonBurnMarginalCostFactor;}

	@FindBy(name = "fixedLossThreshold")
	private static WebElement fixedLossThreshold;
	public static WebElement getfixedLossThreshold() {return fixedLossThreshold;}

	@FindBy(name = "laborPortion")
	private static WebElement thresholdLaborPortion;
	public static WebElement getthresholdLaborPortion() {return thresholdLaborPortion;}

	@FindBy(xpath = "//div[contains(@id,'custommultiselector')]//span[text()='Filter']")
	private static WebElement addOnServicesPopUpFilterButton;
	public static WebElement getaddOnServicesPopUpFilterButton() {return addOnServicesPopUpFilterButton;}

	@FindBy(xpath = "//div[contains(@class,'x-toolbar-footer')]//span[text()='Add']")
	private static WebElement addOnServicesPopUpFilterAddButton;
	public static WebElement getaddOnServicesPopUpFilterAddButton() {return addOnServicesPopUpFilterAddButton;}

//	Omkar 26/6/2023 : xpath changes for 11.2
//	@FindBy(xpath = "//*[text()='Add Services']//following::span[text()='All'][1]//parent::button")
	@FindBy(xpath = "//*[text()='Add Services']//following::span[text()='All'][1]")
	private static WebElement addOnServicesPopUpFilterAllButton;
	public static WebElement getaddOnServicesPopUpFilterAllButton() {return addOnServicesPopUpFilterAllButton;}

	@FindBy(xpath = "//*[text()='Apply Filter']")
	private static WebElement statusFilterDialogButtonApplyFilter;
	public static WebElement getFilterDialogButtonApplyFilter() {return statusFilterDialogButtonApplyFilter;}

	@FindBy(xpath = "//div[contains(@class,'CustomMultiselect')]//span[text()='Select']")
	private static WebElement selectAddServicesButton;
	public static WebElement getselectAddServicesButton() {return selectAddServicesButton;}

	@FindBy(xpath = "//div[contains(@id,'feeForServicePaymentPanel')]//following-sibling::label[text()='Service Model']//following::div[contains(@id,'treeview')]//td")
	private static WebElement selectDropServiceModelBox;
	public static WebElement getselectDropServiceModelBox() {return selectDropServiceModelBox;}

	@FindBy(xpath = "//label[text()='Service Model']//following::table//td[contains(@class,'treeNodeClsShow')]/div")
	private static WebElement selectDropServiceModelPanel;
	public static WebElement getselectDropServiceModelPanel() {return selectDropServiceModelPanel;}

	@FindBy(name = "drgTypeString")
	private static WebElement industryClassificationInput;
	public static WebElement getindustryClassificationInput() {return industryClassificationInput;}

	@FindBy(xpath = "//label[text()='Industry Classification Scheme']/ancestor::div/following-sibling::div[contains(@class,'boundlist')][2]/div/ul")
	private static WebElement industryClassificationInputList;
	public static WebElement industryClassificationInputList() {return industryClassificationInputList;}

//	@FindBy(xpath = "//label[text()='COLA Wage Adjusted Rate']//following::div[1]")
	//Shilpa xpath update for 11.2 on 26.4.2024
	@FindBy(xpath = "//span[text()='COLA Wage Adjusted Rate']//following::div[1]")

	private static WebElement colaWageAdjustedRate;
	public static WebElement getcolaWageAdjustedRate() {return colaWageAdjustedRate;}

//	@FindBy(xpath = "//label[text()='Wage Adjusted Rate']//following::div[1]")
	//Shilpa xpath update for 11.2 on 26.4.2024
	@FindBy(xpath = "//span[text()='Wage Adjusted Rate']//following::div[1]")
	private static WebElement WageAdjustedRate;
	public static WebElement getWageAdjustedRate() {return WageAdjustedRate;}

//	@FindBy(xpath = "//label[text()='COLA Geographic Adjusted Rate']//following::div[1]")
	//Shilpa xpath update for 11.2 on 26.4.2024
	@FindBy(xpath = "//span[text()='COLA Geographic Adjusted Rate']//following::div[1]")
	private static WebElement colaGeographicAdjustedRate;
	public static WebElement getcolaGeographicAdjustedRate() {return colaGeographicAdjustedRate;}

//	@FindBy(xpath = "//label[text()='Industry Classification Scheme']/ancestor::div/following-sibling::div[contains(@class,'boundlist')]/div/ul")
	@FindBy(xpath = "//span[text()='Industry Classification Scheme']/ancestor::div/following-sibling::div[contains(@class,'boundlist')]/div/ul")
	private static WebElement industryClassificationList;
	public static WebElement industryClassificationList() {return industryClassificationList;}

	@FindBy(xpath = "//label[text()='Criteria']//following::textarea[@readonly='readonly']")
	private static WebElement CriteriaBox;
	public static WebElement getCriteriaBox() {return CriteriaBox;}

	@FindBy(xpath = "//div[text()='Fee For Service Payment Terms']")
	private static WebElement feeForServicePayementTerms;
	public static WebElement getfeeForServicePayementTerms() {return feeForServicePayementTerms;}

	@FindBy(xpath = "(//span[text()='Pricing Method']//following::div/img)[1]")
	private static WebElement pricingMethod;
	public static WebElement getpricingMethod() {return pricingMethod;}

//	Omkar 20/6/2023 : xpath changes for 11.2
//	@FindBy(xpath = "//span[text()='Lump Sum Payment Allocation Rule']//following::span[text()='Add']//parent::button")
	@FindBy(xpath = "//div[text()='Lump Sum Payment Allocation Rule']//following::span[text()='Add']")
	private static WebElement lumpSumAddButton;
	public static WebElement getlumpSumAddButton() {return lumpSumAddButton;}

//	Omkar 20/6/2023 : xpath changes for 11.2
//	@FindBy(name = "name")
	@FindBy(xpath = "//input[@name='name']")
	private static WebElement InputName;
	public static WebElement getInputName() {return InputName;}
	
	@FindBy(xpath = "//span[contains(@class,'label-inner-default')]//span[text()='Code']")
	private static WebElement CodeFieldLabel;
	public static WebElement CodeFieldLabel() {return CodeFieldLabel;}
	
	@FindBy(xpath = "//div[text()='Field Specification']")
	private static WebElement FieldSpecificationTab;
	public static WebElement FieldSpecificationTab() {return FieldSpecificationTab;}
	
	@FindBy(xpath = "//div[text()='Templates']")
	private static WebElement TemplatesTab;
	public static WebElement TemplatesTab() {return TemplatesTab;}
	
	@FindBy(xpath = "//div[text()='Schedules']")
	private static WebElement SchedulesTab;
	public static WebElement SchedulesTab() {return SchedulesTab;}
	

	@FindBy(name = "description")
	private static WebElement FieldDropdown;
	public static WebElement getFieldDropdown() {return FieldDropdown;}

	@FindBy(name = "value")
	private static WebElement ValueInput;
	public static WebElement getValueInput() {return ValueInput;}

	@FindBy(name = "orderIndex")
	private static WebElement OrderInut;
	public static WebElement getOrderInut() {return OrderInut;}
	
//	Omkar 20/6/2023 : xpath changes for 11.2
//	@FindBy(xpath = "//div[contains(@id,'warningwindow')]//div[contains(@class,'x-toolbar x-docked x-toolbar-footer')]//following::span[text()='Save']")
	@FindBy(xpath = "//div[contains(@id,'warningwindow')]//following::span[text()='Save']")
	private static WebElement SaveButton;
	public static WebElement getSaveButton() {return SaveButton;}
	
	@FindBy(xpath = "(//div[contains(@id,'feeForServicePaymentPanel')]//following::span[text()='Save'])[1]")
	private static WebElement SaveButtonFeePaymentTerm;
	public static WebElement getSaveButtonFeePaymentTerm() {return SaveButtonFeePaymentTerm;}
	
	@FindBy(xpath = "(//div[contains(@id,'feeForServicePaymentPanel')]//following::span[text()='Save'])[1]")
	private static WebElement SaveOption;
	public static WebElement SaveOption() {return SaveOption;}

	public static HashMap<String, String> services=new HashMap<>();

	@FindBy(xpath = "//div[contains(@id,'contracttaskfolder')]//*[text()='Calculate']")
	private static  WebElement assertCalculateOption;
	public static WebElement getAssertCalculateOption() {return assertCalculateOption;}

//	Omkar 20/6/2023 : xpath changes for 11.2
//	@FindBy(xpath = "(//div[contains(@class,'horzOverflow')]/div//span[text()='Delete']//parent::button)[2]")
	@FindBy(xpath = "(//div[contains(@class,'horzOverflow')]/div//span[text()='Delete'])[2]")
	private WebElement TermDeleteButton;
	public WebElement getTermDeleteButton() {return TermDeleteButton;}

//	@FindBy(xpath = "//span[text()='Pricing Method']//following::label[text()='Service Model']")
	//shilpa update xpath for 11.2 on 11.15.2023
	@FindBy(xpath = "//div[text()='Pricing Method']//following::label[text()='Service Model']")

	private static WebElement PricingLabelServiceModel;
	public static WebElement getPricingLabelServiceModel() {return PricingLabelServiceModel;}

//	Omkar 20/6/2023 : xpath changes or 11.2
//	@FindBy(xpath = "//div[contains(@id,'messagebox')]/div//span[text()='Delete']//parent::button")
	@FindBy(xpath = "//div[contains(@id,'messagebox')]/div//span[text()='Delete']")
	private static WebElement DeleteButtonMesaageBox;
	public static WebElement getDeleteButtonMesaageBox() {return DeleteButtonMesaageBox;}

	@FindBy(xpath = "//div[contains(@id,'messagebox')]/div//span[text()='Save']")
	private static WebElement SaveButtonMesaageBox;
	public static WebElement getSaveButtonMesaageBox() {return SaveButtonMesaageBox;}
	
	//@FindBy(xpath = "//span[text()='Pricing Method']//following::label[text()='Pricing Methods']")
	//Shilpa xpath update for 11.2 11.15.2023
	@FindBy(xpath = "//div[text()='Pricing Method']//following::label[text()='Pricing Methods']")

	private static WebElement PricingLabelPricingMethods;
	public static WebElement getPricingLabelPricingMethods() {return PricingLabelPricingMethods;}

//	@FindBy(xpath = "//div[contains(@class,'expandContainer')]//div[contains(@class,'x-toolbar-item')]//span[text()='New']//parent::button")
	//Shilpa updated xpath for11.2 11.15.2023
	@FindBy(xpath = "//div[contains(@class,'expandContainer')]//div[contains(@id,'toolbar')]//span[text()='New']")
	private static WebElement PricingNewButton;
	public static WebElement getPricingNewButton() {return PricingNewButton;}

	@FindBy(xpath = "//div[contains(@id,'dynamicwindow')]//*[text()='New Risk Limiter']")
	private static WebElement NewRiskLimiterPopUp;
	public static WebElement getNewRiskLimiterPopUp() {return NewRiskLimiterPopUp;}

	@FindBy(xpath = "(//div[contains(@class,'x-toolbar x-docked x-toolbar-footer')]//span[text()='Cancel & Close'])[2]")
	private static WebElement NewRiskLimiterPopUpCancelClose;
	public static WebElement getNewRiskLimiterPopUpCancelClose() {return NewRiskLimiterPopUpCancelClose;}
	
	@FindBy(xpath = "(//div[contains(@class,'x-toolbar x-docked x-toolbar-footer')]//span[text()='Cancel & Close'])")
	private static WebElement FeeForPaymentCancelClose;
	public static WebElement getFeeForPaymentCancelClose() {return FeeForPaymentCancelClose;}
	
	@FindBy(xpath = "(//div[contains(@class,'x-toolbar x-docked x-toolbar-footer')]//span[text()='Save & Close'])")
	private static WebElement FeeForPaymentSaveClose;
	public static WebElement getFeeForPaymentSaveClose() {return FeeForPaymentSaveClose;}

//	Omkar 6/7/2023 : xpath changes for 11.2
//	@FindBy(xpath = "//h1[text()='Medicare Carriers']//following::span[text()='New']//parent::button")
	@FindBy(xpath = "//h1[text()='Medicare Carriers']//following::span[text()='New']")
	private static WebElement NewButtonMedicare;
	public static WebElement getNewButtonMedicare() {return NewButtonMedicare;}

	@FindBy(xpath = "//div[contains(@id,'dynamicwindow')]//*[text()='New Code']")
	private static WebElement NewCodePopUp;
	public static WebElement getNewCodePopUp() {return NewCodePopUp;}

	@FindBy(name = "code")
	private static WebElement MedicareCode;
	public static WebElement getMedicareCode() {return MedicareCode;}

//	Omkar 5/7/2023 : xpath changes for 11.2
//	@FindBy(xpath = "//div[contains(@id,'medicareCarrier')]/div//span[text()='Delete']//parent::button")
	@FindBy(xpath = "//div[contains(@id,'medicareCarrier')]/div//span[text()='Delete']")
	private static WebElement NewCodeDeleteButton;
	public static WebElement getNewCodeDeleteButton() {return NewCodeDeleteButton;}
	
	@FindBy(xpath = "//div[contains(@id,'globalPeriod')]/div//span[text()='Delete']")
	private static WebElement NewPeriodDeleteButton;
	public static WebElement NewPeriodDeleteButton() {return NewPeriodDeleteButton;}

//	Omkar 5/7/2023 : xpath changes for 11.2
//	@FindBy(xpath = "//div[contains(@id,'warningwindow')]/div//span[text()='Delete']//parent::button")
	@FindBy(xpath = "//div[contains(@id,'warningwindow')]/div//span[text()='Delete']")
	private static WebElement WarningPopUpDeleteButton;
	public static WebElement getWarningPopUpDeleteButton() {return WarningPopUpDeleteButton;}
	
	@FindBy(xpath = "(//h1[text()='AP DRG Fee Schedule Masters']//following::span[text()='Delete'])[1]")
	private static WebElement APDRGDeleteButton;
	public static WebElement getAPDRGDeleteButton() {return APDRGDeleteButton;}
	
	@FindBy(xpath = "(//h1[text()='AP DRG Fee Schedule Masters']//following::span[text()='Filter'])[1]")
	private static WebElement APDRGFilterButton;
	public static WebElement getAPDRGFilterButton() {return APDRGFilterButton;}

//	Omkar 5/7/2023 : xpath changes for 11.2
//	@FindBy(xpath = "//div[contains(@id,'medicareCarrier')]/div//span[text()='Filter']//parent::button")
	@FindBy(xpath = "//div[contains(@id,'medicareCarrier')]/div//span[text()='Filter']")
	private static WebElement NewCodeFilterButton;
	public static WebElement getNewCodeFilterButton() {return NewCodeFilterButton;}

	@FindBy(xpath = "//h1[text()='Update Indicators']")
	private static WebElement UpdateIndicatorsPage;
	public static WebElement getUpdateIndicatorsPage() {return UpdateIndicatorsPage;}

//	Omkar 5/7/2023 : xpath changes for 11.2
//	@FindBy(xpath = "//h1[text()='Update Indicators']//following::span[text()='Edit']//parent::button")
	@FindBy(xpath = "//h1[text()='Update Indicators']//following::span[text()='Edit']")
	private static WebElement UpdateIndicatorsEditButton;
	public static WebElement getUpdateIndicatorsEditButton() {return UpdateIndicatorsEditButton;}

//	Omkar 5/7/2023 : xpath changes for 11.2
//	@FindBy(xpath = "//div[contains(@id,'dynamicwindow')]//following::span[text()='Update Indicator']")
	@FindBy(xpath = "//div[contains(@id,'dynamicwindow')]//following::div[text()='Update Indicator']")
	private static WebElement UpdateIndicatorsEditPopUp;
	public static WebElement getUpdateIndicatorsEditPopUp() {return UpdateIndicatorsEditPopUp;}

	//shilpa updated xpath 11/10/2023 for 11.2
//	@FindBy(xpath = "//h1[text()='APC Fee Schedule Masters']")
	@FindBy(xpath = "//span[text()='APC Fee Schedule Masters']")
	private static WebElement ApcFeeScheduleHeader;
	public static WebElement getApcFeeScheduleHeader() {return ApcFeeScheduleHeader;}

	//	Omkar 22/5/2023 : xpath chnges or 11.2
	//	@FindBy(xpath = "(//h1[text()='APC Fee Schedule Masters']//following::span[text()='New']//parent::button)[1]")
	@FindBy(xpath = "//div[contains(@class,'x-container x-box-item x-container-default x-box-layout-ct')]//span[text()='New']/..")
	private static WebElement NewButtonAPC;
	public static WebElement getNewButtonAPC() {return NewButtonAPC;}


//	Omkar 5/7/2023 : xpath changes for 11.2
//	@FindBy(xpath = "(//h1[text()='APC Fee Schedule Masters']//following::span[text()='Delete']//parent::button)[1]")
	@FindBy(xpath = "(//h1[text()='APC Fee Schedule Masters']//following::span[text()='Delete'])[1]")
	private static WebElement NewAPCodeDeleteButton;
	public static WebElement getNewAPCodeDeleteButton() {return NewAPCodeDeleteButton;}

//	Omkar 5/7/2023 : xpath changes for 11.2
//	@FindBy(xpath = "(//h1[text()='APC Fee Schedule Masters']//following::span[text()='Filter']//parent::button)[1]")
	@FindBy(xpath = "(//h1[text()='APC Fee Schedule Masters']//following::span[text()='Filter'])[1]")
	private static WebElement NewAPCodeFilterButton;
	public static WebElement getNewAPCodeFilterButton() {return NewAPCodeFilterButton;}

//	Omkar 08/6/2023 : xpath changes or 11.2
//	@FindBy(xpath = "(//h1[text()='ASC Schemes']//following::span[text()='Filter']//parent::button)[1]")
	@FindBy(xpath = "//div[contains(@id,'masterlist')]//span[text()='Filter']")
	private static WebElement ASCFilterButton;
	public static WebElement getASCFilterButton() {return ASCFilterButton;}

//	Omkar 08/6/2023 : xpath changes or 11.2
//	@FindBy(xpath = "//div[text()='Edit']")
//	@FindBy(xpath = "//a[@class='x-btn editBtnClsFilter x-unselectable x-btn-default-small x-border-box']//span[text()='Edit']")
//	Omkar 16/8/2023 : xpath changes for 11.2
	@FindBy(xpath = "(//span[text()='Edit']/../../../..)[3]")
	private static WebElement ASCFilterEditButton;
	public static WebElement getASCFilterEditButton() {return ASCFilterEditButton;}

//	Omkar 08/6/2023 : xpath changes or 11.2
//	@FindBy(xpath = "//div[contains(@id,'filtergrid')]//td//div[text()='Remove']")
	@FindBy(xpath ="//div[contains(@id,'filtergrid')]//td//span[text()='Remove']")
	private static WebElement ASCFilterRemoveButton;
	public static WebElement getASCFilterRemoveButton() {return ASCFilterRemoveButton;}

//	Omkar 08/6/2023 : xpath changes or 11.2
//	@FindBy(xpath = "//div[contains(@id,'filter')]//div[contains(@class,'x-btn-disabled')]//following::span[text()='Add']")
//	Shilpa 11/17/2023 : xpath changes or 11.2
	@FindBy(xpath ="//div[contains(@id,'filterwindow')]//following::span[text()='Add']/../../..")
	private static WebElement ASCFilterAddButton;
	public static WebElement getASCFilterAddButton() {return ASCFilterAddButton;}

//	Omkar 5/7/2023 : xpath changes for 11.2
//	@FindBy(xpath = "//h1[text()='Contract Types']//following::span[text()='New']//parent::button")
	@FindBy(xpath = "//h1[text()='Contract Types']//following::span[text()='New']")
	private static WebElement ContractTypeNewButton;
	public static WebElement getContractTypeNewButton() {return ContractTypeNewButton;}

//	Omkar 21/06/2023 : xpath changes for 11.2
//	@FindBy(xpath = "(//h1[text()='Contract Types']//following::span[text()='Edit']//parent::button)[1]")
	@FindBy(xpath = "//h1[text()='Contract Types']//following::span[text()='Edit']")
	private static WebElement ContractTypeEditButton;
	public static WebElement ContractTypeEditButton() {return ContractTypeEditButton;}

//	Omkar 5/7/2023 : xpath changes for 11.2
//	@FindBy(xpath = "//h1[text()='Contract Types']//following::span[text()='Filter']//parent::button")
	@FindBy(xpath = "//h1[text()='Contract Types']//following::span[text()='Filter']")
	private static WebElement ContractTypeFilterButton;
	public static WebElement getContractTypeFilterButton() {return ContractTypeFilterButton;}

	@FindBy(xpath = "(//div[@class='bubbleSubContainer']//li/a[text()='Contracting Data Maintenance'])")
	private static WebElement ContractDataMaintenance;
	public static WebElement getContractDataMaintenance() {return ContractDataMaintenance;}

//	Omkar 5/7/2023 : xpath changes for 11.2
//	@FindBy(xpath = "//h1[text()='Time Periods']//following::span[text()='New']//parent::button")
	@FindBy(xpath = "//h1[text()='Time Periods']//following::span[text()='New']")
	private static WebElement ContractDataMaintenanceNewButton;
	public static WebElement getContractDataMaintenanceNewButton() {return ContractDataMaintenanceNewButton;}

//	Omkar 5/7/2023 : xpath changes for 11.2
//	@FindBy(xpath = "//h1[text()='Time Periods']//following::span[text()='Edit']//parent::button")
	@FindBy(xpath = "//h1[text()='Time Periods']//following::span[text()='Edit']")
	private static WebElement ContractDataMaintenanceEditButton;
	public static WebElement getContractDataMaintenanceEditButton() {return ContractDataMaintenanceEditButton;}

	//shilpa updated xpath for 11.2 10/11/2023
//	@FindBy(xpath = "//div[contains(@id,'dynamicwindow')][text()='New Time Period']")
	@FindBy(xpath = "//div[contains(@id,'dynamicwindow')][text()='New Time Period']")
	private static WebElement NewTimePeriodPopUp;
	public static WebElement getNewTimePeriodPopUp() {return NewTimePeriodPopUp;}


	@FindBy(name = "month")
	private static WebElement Monthdropdown;
	public static WebElement getMonthdropdown() {return Monthdropdown;}

	@FindBy(name = "year")
	private static WebElement Yeardropdown;
	public static WebElement getYeardropdown() {return Yeardropdown;}

//	Omkar 5/7/2023 : xpath changes for 11.2
//	@FindBy(xpath = "//h1[text()='Time Periods']//following::span[text()='Filter']//parent::button")
	@FindBy(xpath = "//h1[text()='Time Periods']//following::span[text()='Filter']")
	private static WebElement ContractDataMaintenanceFilterButton;
	public static WebElement getContractDataMaintenanceFilterButton() {return ContractDataMaintenanceFilterButton;}

//	Omkar 21/6/2023 : xpath changes for 11.2
//	The below xpath will pick the second name from name column
//	@FindBy(xpath = "//tr[contains(@class,'x-grid-row x-grid-row-selected x-grid-row-focused')]/td[4]/div")
	@FindBy(xpath= "((//div[@class='x-grid-item-container'])[2]//tr[contains(@class,'  x-grid-row')]/td)[2]/div")
	private static WebElement ContractTypeColName;
	public static WebElement getContractTypeColName() {return ContractTypeColName;}

	//Shilpa updated xpath 4.19.2024
//	@FindBy(xpath = "//div[@class='bubble bubble3 large contracting']")
//	private static WebElement ContractingBubble;
//	public static WebElement getContractingBubble() {return ContractingBubble;}
	
	@FindBy(xpath = "//div[@class='bubble bubble3 medium contracting']")
	private static WebElement ContractingBubble;
	public static WebElement getContractingBubble() {return ContractingBubble;}

	@FindBy(xpath = "//div[@class='bubble bubble3 medium contracting']//*[text()='Contract Models']")
	private static WebElement ContractModelsBubble;
	public static WebElement getLandingPageContractModelBubble() {return ContractingBubble;}

	@FindBy(xpath = "//tr[contains(@class,'x-grid-row treeWhite x-grid-tree-node-leaf')]/td/div")
	private static WebElement getContractServiceModelName;
	public static WebElement getContractServiceModelName() {return getContractServiceModelName;}

	@FindBy(xpath = "//div[contains(@id,'treepanel')]//following::tr[@class='x-grid-row treeWhite x-grid-tree-node-leaf']/td/div[@class='x-grid-cell-inner ']")
	private static WebElement getPricingMethodServiceModelName;
	public static WebElement getPricingMethodServiceModelName() {return getPricingMethodServiceModelName;}

	@FindBy(xpath = "(//div[contains(@id,'treeview')])[3]")
	private static WebElement getSelectService;
	public static WebElement getSelectService() {return getSelectService;}

//	Omkar 22/6/2023 : xpath changes for 11.2
//	@FindBy(xpath = "(//button/span[text()='Definition Elements'])[1]")
	@FindBy(xpath = "//span[text()='Definition Elements']")
	private static WebElement getDefinitionElementC1;
	public static WebElement getDefinitionElementC1() {return getDefinitionElementC1;}

//	Omkar 7/7/2023 : xpath changes for 11.2
//	@FindBy(xpath = "(//button/span[text()='Definition Elements'])[2]")
	@FindBy(xpath = "(//span[text()='Definition Elements'])[2]")
	private static WebElement getDefinitionElementC2;
	public static WebElement getDefinitionElementC2() {return getDefinitionElementC2;}

	@FindBy(xpath = "(//div[contains(@class,'calcSceneCls')]//following::span[text()='Add'])[1]")
	private static WebElement getDefinitionElementAddBtn;
	public static WebElement getDefinitionElementAddBtn() {return getDefinitionElementAddBtn;}

	@FindBy(xpath = "(//div[contains(@class,'calcSceneCls')]//following::span[text()='Add'])[2]")
	private static WebElement getDefinitionElementAddBtnC2;
	public static WebElement getDefinitionElementAddBtnC2() {return getDefinitionElementAddBtnC2;}

//	@FindBy(xpath = "//div[contains(@class,'x-toolbar x-docked x-toolbar-footer')]//following::span[text()='Save']")
	//Shilpa update xpath for 11.2 2.9.2024
	@FindBy(xpath = "//div[contains(@id,'button')]//following::span[text()='Save']")
	private static WebElement getSaveBenefitPlan;
	public static WebElement getSaveBenefitPlan() {return getSaveBenefitPlan;}

	@FindBy(xpath = "(//div[contains(@id,'contractTabpanel')]//following::div[contains(@class,'accordContentMarg ')]//following::div[contains(@id,'gridview')]/table/tbody/tr/td[3]/div)")
	private static List<WebElement> getBenefitPlans;
	public static List<WebElement> getBenefitPlans() {return getBenefitPlans;}

//	Omkar 5/7/2023 : xpath changes for 11.2
//	@FindBy(xpath = "//span[text()='Add Benefit Plans']//following::h1[text()='Select']//following::input[1]")
	@FindBy (xpath = "//div[text()='Add Benefit Plans']//following::h1[text()='Select']//following::input[@name='selectAllChk']")
	private static WebElement getSelectAllBenefitPlans;
	public static WebElement getSelectAllBenefitPlans() {return getSelectAllBenefitPlans;}

//	Omkar 5/7/2023 : xpath changes for 11.2
//	@FindBy(xpath = "//div[contains(@class,'x-toolbar x-docked x-toolbar-footer')]//span[text()='Calculate']//parent::button")
	@FindBy(xpath = "//div[contains(@class,'x-toolbar x-docked x-toolbar-footer')]//span[text()='Calculate']")
	private static WebElement getCalculateButton;
	public static WebElement getCalculateButton() {return getCalculateButton;}

	@FindBy(xpath = "//h1[text()='GL Adjustment and Reclassification Calculation Scenarios']//following::span[text()='Filter']")
	private static WebElement getGLFilterButton;
	public static WebElement getGLFilterButton() {return getGLFilterButton;}

	@FindBy(xpath = "(//div[@class='x-grid-item-container'])[4]//tr//td[2]//div")
	private static List<WebElement> getGridElements;
	public static List<WebElement> getGridElements() {return getGridElements;}
	
	@FindBy(xpath = "//input[@name='dischargeStartDate']")
	private static WebElement getDischargeDateFrom;
	public static WebElement getDischargeDateFrom() {return getDischargeDateFrom;}
	
	@FindBy(xpath = "//input[@name='dischargeEndDate']")
	private static WebElement getDischargeDateTo;
	public static WebElement getDischargeDateTo() {return getDischargeDateTo;}
	
	//Shilpa added xpath 10.18.2024
	@FindBy(xpath = "//div[text()='Calculation Status']//following::span[text()='Filter']")
	private static WebElement CalculationStatusButtonFilter;
	public static WebElement getCalculationStatusButtonFilter() {return CalculationStatusButtonFilter;}

}
