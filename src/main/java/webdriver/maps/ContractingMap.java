package webdriver.maps;

import java.util.List;

import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import webdriver.maps.mapbuilder.MapConfig;

public class ContractingMap extends MapConfig {

    // ===== Contracting Tab > Contractual Allowance Export Page ===== //

    @FindBy(xpath = "//div[contains(@id,'contractualallowances')]/descendant::div[contains(@id,'rownumberer') and @class='x-column-header-inner']/span[@class='x-column-header-text']")
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
    
   //Shilpa
    
	public static String getContractingName = "(//td[contains(@class,'x-grid-cell-treecolumn')]/*[text()='Contracting'])[1]";


	@FindBy(xpath = "//div[contains(@class,'horzOverflow ')]//div[contains(@id,'acommontbar')]//span[text()='New']//parent::button")
	public static WebElement NewContractModelButton1;

	public static WebElement getNewContractModelButton() {
		return NewContractModelButton1;
	}

	@FindBy(xpath = "//div[contains(@class,'x-window-closable ')]//div[contains(@id,'window')]//span[text()='New Contract Model']")
	public static WebElement getNewContractModelPopUp;
	public static WebElement getNewContractModelPopUp() { return getNewContractModelPopUp;}

	@FindBy(xpath = "//div[contains(@class,'contractFrmCls')]//following::input[contains(@id,'textfield')]")
	public static WebElement getContractModelNameInput;
	public static WebElement getContractModelNameInput() {return getContractModelNameInput;}

	@FindBy(xpath = "//div[contains(@class,'contractFrmCls')]//following::table//following::span[text()='Add Providers']//parent::button")
	public static WebElement getContractModelAddProviderBtn;
public static WebElement getContractModelAddProviderBtn() {return getContractModelAddProviderBtn;}

	@FindBy(xpath = "//div[contains(@class,'x-window-header-draggable')]//following::span[text()='Add Providers']//parent::div")
	public static WebElement getContractModelAddProviderPopup;
	public static WebElement getContractModelAddProviderPopup() {return getContractModelAddProviderPopup;}

	@FindBy(xpath = "//div[contains(@class,'contractFrmCls')]//following::table//following::span[text()='Save & Close']//parent::button")
	private static WebElement getSaveContractModel;
	public WebElement getSaveContractModel() {return getSaveContractModel;}

	@FindBy(xpath = "//div[contains(@class,'x-window-header-draggable')]//following::span[text()='Select']//parent::button")
	private static WebElement getSelectItem;
	public WebElement getSelectItem() {return getSelectItem;}

	@FindBy(xpath = "(//div[contains(@id,'dynamicwindow')])//div[contains(@class,'multiSelPneCls')]//following::span[text()='Remove']//parent::button")
	private static WebElement getRemoveItem;
	public WebElement getRemoveItem() {return getRemoveItem;}

	@FindBy(xpath = "(//div[contains(@id,'dynamicwindow')])//div[contains(@class,'multiSelPneCls')]//following::span[text()='Apply']//parent::button")
	private static WebElement getApplySelections;
	public WebElement getApplySelections() {return getApplySelections;}

	@FindBy(xpath = "//div[contains(@class,'contractFrmCls')]//ul/li")
	private static WebElement getProviderText;
	public static WebElement getProviderText() {return getProviderText;}

	@FindBy(xpath = "(//div[contains(@class,'horzOverflow')]//table[@class='x-grid-table x-grid-table-resizer'])//tr/td[3]/div")
	private static List<WebElement> getCostingModelElementList;
	public static List<WebElement> getCostingModelElementList() {return getCostingModelElementList;}

	@FindBy(xpath = "//div[contains(@class,'modalLibBarCls')]/div//span[text()='New']//parent::button")
	private static WebElement getNewContractFolderBtn;
	public WebElement getNewContractFolderBtn() {return getNewContractFolderBtn;}

	@FindBy(xpath = "//div[contains(@class,'modalLibBarCls')]/div//span[text()='New']//parent::button")
	private static WebElement getNewFolderPopUp;
	public WebElement getNewFolderPopUp() {return getNewFolderPopUp;}

	@FindBy(xpath = "//div[contains(@class,'x-window-header-text-container')]//following::td[contains(@id,'textfield')]/input")
	private static WebElement getNewFolderNameInput;
	public WebElement getNewFolderNameInput() {return getNewFolderNameInput;}

	@FindBy(xpath = "//div[contains(@class,'x-window-header-text-container')]//following::span[text()='Save & Close']")
	private static WebElement getNewFolderNameSave;
	public WebElement getNewFolderNameSave() {return getNewFolderNameSave;}

	@FindBy(xpath = "(//td[contains(@class,'x-grid-cell-treecolumn')]/*[text()='Contracting']/img)[1]")
	private static WebElement getContractingTreeExpand;
	public WebElement getContractingTreeExpand() {return getContractingTreeExpand;}

	@FindBy(xpath = "//div[contains(@class,'horzOverflow')]/div//span[text()='Filter']//parent::button")
	private WebElement ContractModelButtonFilter;
	public WebElement getContractModelButtonFilter() {return ContractModelButtonFilter;}

	@FindBy(xpath = "//div[contains(@id,'headercontainer')]//div[text()='Remove']")
	private WebElement ContractModelRemoveFilterButton;
	public WebElement getContractModelRemoveFilterButton() {return ContractModelRemoveFilterButton;}

	@FindBy(xpath = "//div[contains(@id,'toolbar')]//span[text()='Apply Filter']")
	private WebElement ContractModelApplyFilterButton;
	public WebElement getContractModelApplyFilterButton() {return ContractModelApplyFilterButton;}

	@FindBy(xpath = "//div[contains(@id,'filtergrid')]//div[text()='Edit']//parent::td")
	private WebElement ContractModelEditFilterButton;
	public WebElement getContractModelEditFilterButton() {return ContractModelEditFilterButton;}

	@FindBy(xpath = "//span[text()='Update']//parent::button")
	private WebElement ContractModelUpdateFilterButton;
	public WebElement getContractModelUpdateFilterButton() {return ContractModelUpdateFilterButton;}

	@FindBy(xpath = "//div[contains(@class,'horzOverflow')]/div//span[text()='Clear Filter']//parent::button")
	private WebElement ContractModelClearFilter;
	public WebElement getContractModelClearFilter() {return ContractModelClearFilter;}
	
	@FindBy(xpath = "//div[contains(@class,'horzOverflow')]/div//span[text()='Clear Filter']//parent::button")
	private WebElement ContractModelHeader;
	public WebElement getContractModelHeader() {return ContractModelHeader;}
	
	@FindBy(xpath = "//div[contains(@class,'horzOverflow')]/div//span[text()='Copy']//parent::button")
	private WebElement ContractModelButtonCopy;
	public WebElement getContractModelButtonCopy() {return ContractModelButtonCopy;}
	
	@FindBy(xpath = "//div[contains(@class,'horzOverflow')]/div//span[text()='Paste']//parent::button")
	private WebElement ContractModelButtonPaste;
	public WebElement getContractModelButtonPaste() {return ContractModelButtonPaste;}
	
	@FindBy(xpath = "//div[contains(@class,'contractFrmCls ')]//input[contains(@id,'textfield')]")
	private WebElement ContractModelPastePopup;
	public WebElement getContractModelPastePopup() {return ContractModelPastePopup;}
	
	@FindBy(xpath = "//div[contains(@class,'contractFrmCls ')]//input[contains(@id,'textfield')]")
	private WebElement ContractModelPasteNameInput;
	public WebElement getContractModelPasteNameInput() {return ContractModelPasteNameInput;}
	
	@FindBy(xpath = "//span[text()='Save & Close']")
	private WebElement ContractModelSaveCopy;
	public WebElement getContractModelSaveCopy() {return ContractModelSaveCopy;}
	
	@FindBy(xpath = "//div[contains(@class,'horzOverflow')]/div//span[text()='Delete']//parent::button")
	private WebElement ContractModelDeleteButton;
	public WebElement getContractModelDeleteButton() {return ContractModelDeleteButton;}
	
	@FindBy(xpath = "//span//h1[text()=' Warning ']")
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
	
	@FindBy(xpath = "//div[contains(@class,'horzOverflow')]/div//span[text()='Export']//parent::button")
	private WebElement ContractModelExportButton;
	public WebElement getContractModelExportButton() {return ContractModelExportButton;}
	
	@FindBy(xpath = "//div[contains(@class,'horzOverflow')]/div//span[text()='Import']//parent::button")
	private WebElement ContractModelImportButton;
	public WebElement getContractModelImportButton() {return ContractModelImportButton;}
	
	@FindBy(xpath = "//div[contains(@id,'exportwindow')]//span[text()='Export Data']")
	private WebElement ContractModelExportPopUp;
	public WebElement getContractModelExportPopUp() {return ContractModelExportPopUp;}
	
	@FindBy(xpath = "//div[contains(@id,'importwindow')]//span[text()='Import Data']")
	private WebElement ContractModelImportPopUp;
	public WebElement getContractModelImportPopUp() {return ContractModelImportPopUp;}
	
	@FindBy(xpath = "//div[contains(@id,'exportwindow')]//span[text()='Select']//parent::button")
	private WebElement ContractModelSelectFileButton;
	public WebElement getContractModelSelectFileButton() {return ContractModelSelectFileButton;}
	
	@FindBy(xpath = "//div[contains(@id,'importwindow')]//span[text()='Select']//parent::button")
	private WebElement ContractModelImportSelectFileButton;
	public WebElement getContractModelImportSelectFileButton() {return ContractModelImportSelectFileButton;}
	
	@FindBy(xpath = "//div[contains(@class,'x-closable')]//div[contains(@id,'dynamicwindow')]//span[text()='Add Contract Model']")
	private WebElement ContractModelInExportPopUp;
	public WebElement getContractModelInExportPopUp() {return ContractModelInExportPopUp;}

	@FindBy(xpath = "//div[contains(@class,'x-closable')]//div[contains(@id,'dynamicwindow')]//span[text()='Apply']//parent::button")
	private WebElement ContractModelApplyInExportPopUp;
	public WebElement getContractModelApplyInExportPopUp() {return ContractModelApplyInExportPopUp;}

	@FindBy(xpath = "//div[contains(@class,'x-closable')]//div[contains(@id,'dynamicwindow')]//span[text()='Apply']//parent::button")
	private WebElement ContractModelExportFileSharedLoc;
	public WebElement getContractModelExportFileSharedLoc() {return ContractModelExportFileSharedLoc;}
	
	@FindBy(xpath = "//li[text()='<SFTP_SERVER>/PATH/TO/EXPORT_LOGS_SHARED_DIRECTORY/']")
	private WebElement ContractModelExportFileSharedLocOption;
	public WebElement getContractModelExportFileSharedLocOption() {return ContractModelExportFileSharedLocOption;}
	
	@FindBy(xpath = "//div[contains(@class,'x-toolbar-footer')]//div[contains(@class,'x-toolbar-item')]//span[text()='Export']//parent::button")
	private WebElement ContractModelExportButtonInExportPopUp;
	public WebElement getContractModelExportButtonInExportPopUp() {return ContractModelExportButtonInExportPopUp;}
	
	@FindBy(xpath = "//div[contains(@class,'x-toolbar-footer')]//div[contains(@class,'x-toolbar-item')]//span[text()='Import']//parent::button")
	private WebElement ContractModelImportButtonInExportPopUp;
	public WebElement getContractModelImportButtonInExportPopUp() {return ContractModelImportButtonInExportPopUp;}
	
	
	@FindBy(xpath = "//span[contains(@id,'importexportstatus')][text()='Import/Export Status']")
	private WebElement ContractModelImportExportstatusPage;
	public WebElement getContractModelImportExportstatusPage() {return ContractModelImportExportstatusPage;}
}
