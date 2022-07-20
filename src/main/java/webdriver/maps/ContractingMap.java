package webdriver.maps;

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
}
