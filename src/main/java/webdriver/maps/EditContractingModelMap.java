package webdriver.maps;

import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import webdriver.maps.mapbuilder.MapConfig;

public class EditContractingModelMap extends MapConfig {

  // Main Page //

  @FindBy(xpath = "//textarea[@name='criteria']")
  private WebElement editMainPageCriteriaTextArea;
  public WebElement getEditContractMainPageCriteriaTextArea() {return editMainPageCriteriaTextArea;}

  @FindBy(xpath = "//span[text()='Continue & Close']")
  private WebElement editMainPageContinueAndCloseButton;
  public WebElement getEditContractMainPageContinueAndCloseButton() {return editMainPageContinueAndCloseButton;}

  @FindBy(xpath = "//span[text()='Continue']")
  private WebElement editMainPageContinueButton;
  public WebElement getEditContractMainPageContinueButton() {return editMainPageContinueButton;}


    // ===== General section ===== //
    @FindBy(name = "drgTypeString")
    private WebElement drgTypeString;
    public WebElement getGeneralSectionIndustryClassificationSchemeDropdown() {return drgTypeString;}

    @FindBy(xpath = "//div/following-sibling::div[contains(@class,'boundlist')]/div/ul")
    private WebElement drgTypeStringMenu;
    public WebElement getGeneralSectionIndustryClassificationSchemeDropdownMenu() {return drgTypeStringMenu;}

    @FindBy(xpath = "//*[text()='Read Alternative DRG']/preceding-sibling::input")
    private WebElement readAlternativeDrg;
    public WebElement getGeneralSectionReadAlternativeDrgCheckbox() {return readAlternativeDrg;}

    @FindBy(name = "otherDrgIndex")
    private WebElement readAlternativeDrgDropdown;
    public WebElement getGeneralSectionReadAlternativeDrgDropdown() {return readAlternativeDrgDropdown;}

    @FindBy(xpath = "//div/following-sibling::div[contains(@class,'boundlist')][2]/div/ul")
    private WebElement readAlternativeDrgDropdownMenu;
    public WebElement getGeneralSectionReadAlternativeDrgDropdownMenu() {return readAlternativeDrgDropdownMenu;}

    @FindBy(name = "operIndirectMedEducAdjFactor")
    private WebElement operatingImeAdjustmentFactor;
    public WebElement getGeneralSectionOperatingImeAdjustmentFactorField() {return operatingImeAdjustmentFactor;}

    @FindBy(name = "capitalIndrMedEducAdjFactor")
    private WebElement capitalImeAdjustmentFactor;
    public WebElement getGeneralSectionCapitalImeAdjustmentFactorField() {return capitalImeAdjustmentFactor;}

    @FindBy(name = "operDispShareHospAdjFactor")
    private WebElement operatingDshAdjustmentFactor;
    public WebElement getGeneralSectionOperatingDshAdjustmentFactorField() {return operatingDshAdjustmentFactor;}

    @FindBy(name = "capitalDispShareHospAdjFactor")
    private WebElement capitalDshAdjustmentFactor;
    public WebElement getGeneralSectionCapitalDshAdjustmentFactorField() {return capitalDshAdjustmentFactor;}

    @FindBy(name = "location")
    private WebElement locationDropdown;
    public WebElement getGeneralSectionLocationDropdown() {return locationDropdown;}

    @FindBy(name = "capitalDispShareHospAdjFactor")
    private WebElement locationDropdownMenu;
    public WebElement getGeneralSectionLocationDropdownMenu() {return locationDropdownMenu;}

    @FindBy(name = "colaOperatingFactor")
    private WebElement colaFactor;
    public WebElement getGeneralSectionColaFactorField() {return colaFactor;}

    @FindBy(xpath = "//label[text()='Medicare Year']/parent::td/following-sibling::td/descendant::div[contains(@class,'x-form-arrow-trigger')]")
    private WebElement medicareYearDropdown;
    public WebElement getGeneralSectionMedicareYearDropdown() {return medicareYearDropdown;}

    @FindBy(xpath = "//ul/li[contains(text(),'Sept 30')]/..")
    private WebElement medicareYearDropdownMenu;
    public WebElement getGeneralSectionMedicareYearDropdownMenu() {return medicareYearDropdownMenu;}


    // ===== End General section ===== //


    // ===== Operating Payment section ===== //

    public final String hac = "//label[text()='Worst-Performing Quartile for HAC ']";

    @FindBy(xpath = hac)
    private WebElement worstPerformingQuartileForHac;
    public WebElement getOperatingPaymentSectionWorstPerformingQuartileForHacFieldLabel() {return worstPerformingQuartileForHac;}

    @FindBy(name = "areaWageIndex")
    private WebElement areaWageIndex;
    public WebElement getOperatingPaymentSectionAreaWageIndexField() {return areaWageIndex;}

    @FindBy(name = "nationalOperlaborRate")
    private WebElement nationalOperlaborRate;
    public WebElement getOperatingPaymentSectionNationalLaborRateField() {return nationalOperlaborRate;}

  @FindBy(name = "nationalOperNonLaborRate")
  private WebElement nationalOperNonLaborRate;
  public WebElement getOperatingPaymentSectionNationalNonLaborRateField() {return nationalOperNonLaborRate;}

  @FindBy(name = "hospitalReadmAdjFactor")
  private WebElement hospitalReadmAdjFactor;
  public WebElement getOperatingPaymentSectionHospitalReadmissionFactorField() {return hospitalReadmAdjFactor;}

  @FindBy(name = "uncompensatedCarePayment")
  private WebElement uncompensatedCarePayment;
  public WebElement getOperatingPaymentSectionUncompensatedCarePaymentField() {return uncompensatedCarePayment;}

  @FindBy(name = "valueBasedPurchAdjFactor")
  private WebElement valueBasedPurchAdjFactor;
  public WebElement getOperatingPaymentSectionValueBasedPurchasingFactorField() {return valueBasedPurchAdjFactor;}

  @FindBy(xpath = hac + "/preceding-sibling::input[contains(@class,'checkbox')]")
  private WebElement worstPerformingQuartileForHacReduction;
  public WebElement getOperatingPaymentSectionWorstPerformingQuartileForHacReductionCheckbox() {return worstPerformingQuartileForHacReduction;}

  @FindBy(name = "hacReductionPercent")
  private WebElement hacReductionPercent;
  public WebElement getOperatingPaymentSectionReductionPercentField() {return hacReductionPercent;}

  @FindBy(xpath = "//label[text()='Quality Data Submitter']/preceding-sibling::input[contains(@class,'checkbox')]")
  private WebElement qualityDataSubmitter;
  public WebElement getOperatingPaymentSectionQualityDataSubmitterCheckbox() {return qualityDataSubmitter;}

  @FindBy(xpath = "//label[text()='Meaningful EHR User']/preceding-sibling::input[contains(@class,'checkbox')]")
  private WebElement meaningfulEHRUser;
  public WebElement getOperatingPaymentSectionMeaningfulEhrUserCheckbox() {return meaningfulEHRUser;}

  @FindBy(xpath = "//label[text()='Blended Rate']/../following-sibling::td/div[contains(@class,'display-field')]")
  private WebElement blendedRate;
  public WebElement getOperatingPaymentSectionBlendedRateField() {return blendedRate;}

  @FindBy(xpath = "//label[text()='COLA Wage Adjusted Rate']/../following-sibling::td/div[contains(@class,'display-field')]")
  private WebElement colaWageAdjustedRate;
  public WebElement getOperatingPaymentSectionColaWageAdjustedRateField() {return colaWageAdjustedRate;}

  @FindBy(xpath = "//label[text()='Wage Adjusted Federal Rate']/../following-sibling::td/div[contains(@class,'display-field')]")
  private WebElement wageAdjustedFederalRate;
  public WebElement getOperatingPaymentSectionWageAdjustedFederalRateField() {return wageAdjustedFederalRate;}

  @FindBy(xpath = "//label[text()='Wage Adjusted Rate']/../following-sibling::td/div[contains(@class,'display-field')]")
  private WebElement wageAdjustedRate;
  public WebElement getOperatingPaymentSectionWageAdjustedRateField() {return wageAdjustedRate;}

    // ===== End Operating Payment section ===== //

  // ===== Capital Payment section ===== //
  @FindBy(name = "largeUrbanAddOnFactor")
  private WebElement largeUrbanAddOnFactor;
  public WebElement getCapitalPaymentSectionLargeUrbanAddOnFactorField() {return largeUrbanAddOnFactor;}

  @FindBy(name = "capitalGeographicAdjFactor")
  private WebElement capitalGeographicAdjFactor;
  public WebElement getCapitalPaymentSectionCapitalGeographicAdjFactorField() {return capitalGeographicAdjFactor;}

  @FindBy(name = "nationalCapitalRate")
  private WebElement nationalCapitalRate;
  public WebElement getCapitalPaymentSectionNationalCapitalRateField() {return nationalCapitalRate;}

  @FindBy(xpath = "//*[text()='Capital Payment']/ancestor::div/descendant::label[text()='Blended Rate'][2]/../following-sibling::td/div[contains(@class,'x-form-display-field')]")
  private WebElement capitalPaymentBlendedRate;
  public WebElement getCapitalPaymentSectionBlendedRateField() {return capitalPaymentBlendedRate;}

  @FindBy(xpath = "//*[text()='Capital Payment']/ancestor::div/descendant::label[text()='COLA Geographic Adjusted Rate']/../following-sibling::td/div[contains(@class,'x-form-display-field')]")
  private WebElement colaGeographicAdjustedRate;
  public WebElement getCapitalPaymentSectionColaGeographicAdjustedRateField() {return colaGeographicAdjustedRate;}

  @FindBy(name = "colaCapitalFactor")
  private WebElement colaCapitalFactor;
  public WebElement getCapitalPaymentSectionCapitalColaFactorField() {return colaCapitalFactor;}

  // ===== End Capital Payment section ===== //

  // ===== Cost Outlier Payment section ===== //
  @FindBy(name = "operCostChargeRatio")
  private WebElement operCostChargeRatio;
  public WebElement getCostOutlierPaymentSectionOperatingRatioOfCostChargeField() {return operCostChargeRatio;}

  @FindBy(name = "capitalCostChargeRatio")
  private WebElement capitalCostChargeRatio;
  public WebElement getCostOutlierPaymentSectionCapitalRatioOfCostChargeField() {return capitalCostChargeRatio;}

  @FindBy(name = "paymentPercentage")
  private WebElement paymentPercentage;
  public WebElement getCostOutlierPaymentSectionNonBurnMarginalCostFactorField() {return paymentPercentage;}

  @FindBy(name = "fixedLossThreshold")
  private WebElement fixedLossThreshold;
  public WebElement getCostOutlierPaymentSectionFixedLossThresholdField() {return fixedLossThreshold;}

  @FindBy(name = "laborPortion")
  private WebElement laborPortion;
  public WebElement getCostOutlierPaymentSectionThresholdLaborPortionField() {return laborPortion;}

  @FindBy(xpath = "//label[contains(text(),'Services/Charges excluded from Outlier Calculations')]/../descendant::span[text()='Select']")
  private WebElement copSelectButton;
  public WebElement getCostOutlierPaymentSectionSelectButton() {return copSelectButton;}


  // ===== Add On Technology Payment section ===== //
  @FindBy(xpath = "//label[contains(text(),'Services Receiving Add On Technology Payment')]/parent::div/div/em/button/span[text()='Select']")
  private WebElement editPriceDialogAddOnTechnologyPaymentButtonSelect;
  public WebElement getEditPriceDialogAddOnTechnologyPaymentButtonSelect() {return editPriceDialogAddOnTechnologyPaymentButtonSelect;}


}
