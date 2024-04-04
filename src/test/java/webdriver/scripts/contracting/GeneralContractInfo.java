package webdriver.scripts.contracting;
import static org.hamcrest.CoreMatchers.*;
import static org.hamcrest.MatcherAssert.assertThat;
import static org.junit.Assert.*;
import java.util.List;
import org.junit.AfterClass;
import org.junit.BeforeClass;
import org.junit.FixMethodOrder;
import org.junit.Test;
import org.junit.runners.MethodSorters;
import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
import com.aventstack.extentreports.Status;
import webdriver.maps.EditContractingModelMap;
import webdriver.maps.ModelLibraryMap;
import webdriver.maps.mapbuilder.BuildMap;
import webdriver.scripts.contracting.contractmodels.ContractModelsHelper;
import webdriver.users.Users;
import ExtentReport.*;

//Regression test case ADS-6041**/
@FixMethodOrder(MethodSorters.NAME_ASCENDING)
public class GeneralContractInfo extends ContractModelsHelper {
	private static ModelLibraryMap modelMap;
	private static EditContractingModelMap editModelMap;
//	private static UcqcHelper helper;
	private static final String contractModel = "ADS-1320 Contract Model D";// Shilpa: 1.08.2022 updated test data
	private static final String serviceModel = "OPPS 2019";// Shilpa: 1.08.2022 OPPS 2018 not available updated test
												// data
	// String expectedNewestMedicareYearRange = "Oct 1, 2020 - Sept 30, 2021";
	private static final String expectedMedicareYearRange = "Oct 1, 2019 - Sept 30, 2020";
	// The values below have been changed in the application (12-14-2020)
	private static final String nationalOperlaborRate = "3596.70";
	private static final String nationalOperNonLaborRate = "2204.43";
	private static final String nationalCapitalRate = "462.61";
	private static final String costOutlierPaymentFixedLossThreshold = "26473";
	private static final String CostOutlierPaymentSectionThresholdLaborPortion = "16413.26";
	private static String BackgroundColorTitleBarEditPopUp = "rgba(0, 0, 0, 0)";

	// default values from ADS Help are in comments at the end of each line
	String[] expectedCriteriaText = {

	};

	/**
	 * Automates test ticket ADS-1510-General Section (and maybe ADS-1613). Dev
	 * Story 1320-1321. This test probably includes sections of ADS-1321 that are
	 * related to the sections below. Though this would have to be checked (which
	 * would be related to ADS-1613).
	 * 
	 * @throws Exception
	 **/
	@BeforeClass
	public static void setupScript() throws Exception, Throwable {
		try {
			ExtentReport.reportCreate("GeneralContractInfo", "webdriver.scripts.contracting", "GeneralContractInfo");
			modelMap = BuildMap.getInstance(driver, ModelLibraryMap.class);
			editModelMap = BuildMap.getInstance(driver, EditContractingModelMap.class);
//			helper=BuildMap.getInstance(driver, UcqcHelper.class);
			System.out.println("Test Class: " + GeneralContractInfo.class.getSimpleName());
			loginUser(Users.ContractAnalyst1);
			navigateToContractModelsPageFeeForServicePaymentTermsPage(contractModel);
			navigateFeeForServicePaymentTermsPagePricingMethodSectionSelectServiceModel(serviceModel);
			ExtentReport.logPass("PASS", "setupScript");
		} catch (Exception e) {
			ExtentReport.logFail("FAIL", "setupScript", driver, e);
			fail(e.getMessage());
		}

	}

	public static void navigateToTheContractModelsPageFeeForServicePaymentTermsPage(String contractModel)
			throws Exception {

		navigateToContractModelsPageFeeForServicePaymentTermsPage(contractModel);
		navigateFeeForServicePaymentTermsPagePricingMethodSectionSelectServiceModel(serviceModel);
	}

	@Test
	public void test01GeneralSectionPayPercentageDefaultFieldValueIs100() throws Throwable {

		try {
			Thread.sleep(2000);
			waitForAjaxExtJs();
			String defaultExpectedPayPercentage = "100";
			String actualPayPercentage = driver.findElement(By.xpath("//*[@name='pay']")).getAttribute("value");

			assertThat(actualPayPercentage, equalTo(defaultExpectedPayPercentage));
			ExtentReport.logPass("PASS", "test01GeneralSectionPayPercentageDefaultFieldValueIs100");
		} catch (Exception | AssertionError e) {
			ExtentReport.logFail("FAIL", "test01GeneralSectionPayPercentageDefaultFieldValueIs100", driver, e);
			fail(e.getMessage());
		}
//		AssertHelper.assertThatString(actualPayPercentage, defaultExpectedPayPercentage,
//				"test01GeneralSectionPayPercentageDefaultFieldValueIs100");
		// assertThat(actualPayPercentage, equalTo(defaultExpectedPayPercentage));
	}

	@Test
	public void test02GeneralSectionPricingMethodDefaultFieldValueIsAccurate() throws InterruptedException, Throwable {

		try {
			waitForAjaxExtJs();
			driver.findElement(By.xpath("//*[@name='pricemethodoption']")).click();
			waitForAjaxExtJs();
			String actualPricingMethodDefault = driver.findElement(By.xpath("//input[@name='pricemethodoption']"))
					.getAttribute("placeholder");
			driver.findElement(By.xpath("//*[@name='pricemethodoption']")).click();
			String expectedPricingMethodDefault = "<None>";
			assertThat(actualPricingMethodDefault, equalTo(expectedPricingMethodDefault));
			ExtentReport.logPass("PASS", "test02GeneralSectionPricingMethodDefaultFieldValueIsAccurate");
		} catch (Exception | AssertionError e) {
			ExtentReport.logFail("FAIL", "test02GeneralSectionPricingMethodDefaultFieldValueIsAccurate", driver, e);
			fail(e.getMessage());

		}
//		AssertHelper.assertThatString(actualPricingMethodDefault, expectedPricingMethodDefault,
//				"test02GeneralSectionPricingMethodDefaultFieldValueIsAccurate");
	}

	@Test
	public void test03VerifyInitialCriteriaTextAreaIsEmpty() throws Throwable {

		try {
			editModelMap.getEditContractMainPageCriteriaTextArea().click();

			String initialCriteriaText = editModelMap.getEditContractMainPageCriteriaTextArea().getAttribute("value");
			System.out.println(("Initial criteria text length: " + initialCriteriaText.length()));
			assertEquals(0, initialCriteriaText.length());
			ExtentReport.logPass("PASS", "test03VerifyInitialCriteriaTextAreaIsEmpty");
		} catch (Exception | AssertionError e) {
			ExtentReport.logFail("FAIL", "test03VerifyInitialCriteriaTextAreaIsEmpty", driver, e);
			fail(e.getMessage());
		}
	}

	@Test
	public void test04GeneralSectionAssertIndustryClassificationSchemeFieldDefaultValueIsMsdrg1()
			throws InterruptedException, Throwable {
		// go to General section
		try {
			webdriverClick(driver.findElement(By.xpath("//*[@name='pricemethodoption']/..")));
			Thread.sleep(200);
			waitForAjaxExtJs();
//			webdriverClick(driver
//					.findElement(By.xpath("//div[@class='x-boundlist-list-ct']/ul/li[text()='Medicare Inpatient PPS']")));
			WebElement optionToSelect = driver.findElement(By.xpath(
					"//div[contains(@class,'x-boundlist x-boundlist-floating')]//ul/li[text()='Medicare Inpatient PPS']"));
//			webdriverClick(driver
//					.findElement(By.xpath("//div[contains(@class,'x-boundlist x-boundlist-floating')]//ul/li[text()='Medicare Inpatient PPS']")));
			Actions action = new Actions(driver);
			action.moveToElement(optionToSelect).doubleClick().pause(20).perform();
			Thread.sleep(200);
			 JavascriptExecutor js = (JavascriptExecutor) driver;
		        
		        // Execute JavaScript to perform the click
		        js.executeScript("arguments[0].click();", optionToSelect);
			// shilpa 01.08.2022 added above steps
			navigateFeeForServicePaymentTermsPagePricingMethodSectionClickEditButtonToOpenEditDialog();
			String optionColor = driver
					.findElement(By.xpath("//div[contains(@class,'x-window-header x-header x-header-draggable')]"))
					.getCssValue("background-color");
			System.out.println(optionColor);
			assertEquals(BackgroundColorTitleBarEditPopUp, optionColor);
			doChangeMedicareYearTo(expectedMedicareYearRange);
			waitForAjaxExtJs();
			String expectedText = "MSDRG1"; // HCFA DRG
			try {
				driver.findElement(By.xpath("//input[@name='drgTypeString']")).click();
				driverDelay();
			} catch (Exception | AssertionError e) {
				ExtentReport.logFail("FAIL", "Cannot find element", driver, e);

			}
			waitForAjaxExtJs();
			try {
				WebElement classificationList = driver.findElement(By.xpath(
						"//label[text()='Industry Classification Scheme']/ancestor::div/following-sibling::div[contains(@class,'boundlist')][2]/div/ul"));
				List<WebElement> classificationListing = classificationList.findElements(By.tagName("li"));
				for (WebElement item : classificationListing) {
					String clss = item.getAttribute("class");
					if (clss.contains("selected")) {
						try {
							assertThat(item.getText(), equalTo(expectedText));
							ExtentReport.logPass("PASS",
									"test04GeneralSectionAssertIndustryClassificationSchemeFieldDefaultValueIsMsdrg1");
						} catch (AssertionError | org.openqa.selenium.NoSuchElementException e) {
							ExtentReport.logFail("FAIL",
									"test04GeneralSectionAssertIndustryClassificationSchemeFieldDefaultValueIsMsdrg1",
									driver, e);
						}
//				AssertHelper.assertThatString(item.getText(), expectedText,
//						"test04GeneralSectionAssertIndustryClassificationSchemeFieldDefaultValueIsMsdrg1");

						break;
					}
				}
			} catch (Exception e) {
				ExtentReport.logFail("FAIL",
						"test04GeneralSectionAssertIndustryClassificationSchemeFieldDefaultValueIsMsdrg1", driver, e);

			}
			try {
				JavascriptExecutor executor = (JavascriptExecutor)driver;
				executor.executeScript("arguments[0].click();", driver.findElement(By.xpath("//input[@name='drgTypeString']")));
				driverDelay();
				//				driver.findElement(By.name("drgTypeString")).click();
			} catch (Exception e) {
				ExtentReport.logFail("FAIL", "Element not found", driver, e);

			}
			waitForAjaxExtJs();
		} catch (Exception e) {
//			webdriverClick(driver.findElement(By.xpath("//li[text()='Medicare Inpatient PPS']")));
			ExtentReport.logFail("FAIL",
					"test04GeneralSectionAssertIndustryClassificationSchemeFieldDefaultValueIsMsdrg1", driver, e);
			fail(e.getMessage());
		}
	}

	@Test
	public void test05GeneralSectionReadAlternativeDrgCheckboxIsUncheckedByDefault()
			throws Throwable, InterruptedException {
		waitForAjaxExtJs();

		try {
			String checkboxStatus = driver
					.findElement(By.xpath("//*[text()='Read Alternative DRG']/../../.."))
					.getAttribute("class");
			assertFalse(checkboxStatus.contains("checked"));
			assertThat(checkboxStatus, not(containsString("checked")));
			ExtentReport.extenttest.log(Status.PASS,
					"test05GeneralSectionReadAlternativeDrgCheckboxIsUncheckedByDefault PASS");

		} catch (Exception e) {
			ExtentReport.logFail("FAIL", "test05GeneralSectionReadAlternativeDrgCheckboxIsUncheckedByDefault", driver,
					e);
			fail(e.getMessage());
		}

	}

	@Test
	public void test06GeneralSectionReadAlternativeDrgDropdownDefaultValueIsMsDrg1()
			throws InterruptedException, Throwable {
		try {
			waitForAjaxExtJs();
			
			String expectedText = "MS DRG1"; // MS DRG1
			
			JavascriptExecutor executor = (JavascriptExecutor)driver;
			executor.executeScript("arguments[0].click();", driver.findElement(By.xpath("//*[text()='Read Alternative DRG']/../span/input")));
			waitForAjaxExtJs();
			WebElement drgList = driver
					.findElement(By.xpath("(//div[contains(@class, 'boundlist')]//ul)[4]"));
			List<WebElement> drgListing = drgList.findElements(By.tagName("li"));
			for (WebElement item : drgListing) {
				String clss = item.getAttribute("class");
				if (clss.contains("selected")) {
					try {
						assertThat(item.getText(), equalTo(expectedText));
						ExtentReport.logPass("PASS",
								"test06GeneralSectionReadAlternativeDrgDropdownDefaultValueIsMsDrg1");
					} catch (AssertionError | org.openqa.selenium.NoSuchElementException e) {
						ExtentReport.logFail("FAIL",
								"test06GeneralSectionReadAlternativeDrgDropdownDefaultValueIsMsDrg1", driver, e);
					}
					break;
				}
			}
			
		} catch (Exception e) {
			ExtentReport.logFail("FAIL", "test06GeneralSectionReadAlternativeDrgDropdownDefaultValueIsMsDrg1", driver,
					e);
			fail(e.getMessage());

		}
	}

	@Test
	public void test07GeneralSectionDischargeStatusCodeForTransfersFieldIsEmptyByDefault() throws Throwable {
		boolean isDisplayed = false; // default is None
		try {
			isDisplayed = driver.findElement(By.xpath("//div[@class='x-boundlist-item']")).isDisplayed();
			try {
				fail();
			} catch (Exception | AssertionError e1) {
				ExtentReport.logFail("FAIL", "test07GeneralSectionDischargeStatusCodeForTransfersFieldIsEmptyByDefault",
						driver, e1);

			}

		} catch (Exception | AssertionError e) {
			assertTrue(isDisplayed == false);
			ExtentReport.logPass("PASS", "test07GeneralSectionDischargeStatusCodeForTransfersFieldIsEmptyByDefault");

		}
	}

	@Test
	public void test08GeneralSectionMedicareYearRangeIsSelectedByDefault() throws InterruptedException, Throwable {

//		AssertHelper.assertThatString(expectedMedicareYearRange, contractModel,
//				CostOutlierPaymentSectionThresholdLaborPortion);
		try {
			waitForSpinnerToEnd();
			assertThatDropdownSelectedValue(editModelMap.getGeneralSectionMedicareYearDropdown(),
					editModelMap.getGeneralSectionMedicareYearDropdownMenu(), expectedMedicareYearRange);
			ExtentReport.logPass("PASS", "test08GeneralSectionMedicareYearRangeIsSelectedByDefault");
		} catch (Exception | AssertionError e) {
			ExtentReport.logFail("FAIL", "test08GeneralSectionMedicareYearRangeIsSelectedByDefault", driver, e);
			fail(e.getMessage());

		}

		try {
			doClick(driver.findElement(By.xpath("//*[text()='Discharge Status Code for Transfers']")));
		} catch (Exception | AssertionError e) {
			ExtentReport.logFail("FAIL", "test08GeneralSectionMedicareYearRangeIsSelectedByDefault", driver, e);
			fail(e.getMessage());

		}
	}

	@Test
	public void test09GeneralSectionOperatingImeAdjustmentFactorField() throws Throwable {
		try {
			assertFieldValue("//*[@name='operIndirectMedEducAdjFactor']", "1"); // default = 1
			ExtentReport.logPass("PASS", "test09GeneralSectionOperatingImeAdjustmentFactorField PASS");

		} catch (Exception | AssertionError e) {
			ExtentReport.logFail("FAIL", "test09GeneralSectionOperatingImeAdjustmentFactorField", driver, e);
			fail(e.getMessage());

		}

	}

	@Test
	public void test10GeneralSectionCapitalImeAdjustmentFactorField() throws Throwable {
		try {
			assertFieldValue("//*[@name='capitalIndrMedEducAdjFactor']", "1");
			ExtentReport.logPass("PASS", "test10GeneralSectionCapitalImeAdjustmentFactorField PASS");

		} catch (Exception | AssertionError e) {
			ExtentReport.logFail("FAIL", "test10GeneralSectionCapitalImeAdjustmentFactorField", driver, e);
			fail(e.getMessage());

		}

	}

	@Test
	public void test11GeneralSectionOperatingDshAdjustmentFactorField() throws Throwable {
		try {
			assertFieldValue("//*[@name='operDispShareHospAdjFactor']", "1");
			ExtentReport.logPass("PASS", "test11GeneralSectionOperatingDshAdjustmentFactorField PASS");

		} catch (Exception | AssertionError e) {
			ExtentReport.logFail("FAIL", "test11GeneralSectionOperatingDshAdjustmentFactorField", driver, e);
			fail(e.getMessage());
		}

	}

	@Test
	public void test12GeneralSectionCapitalDshAdjustmentFactorField() throws Throwable {
		try {
			assertFieldValue("//*[@name='capitalDispShareHospAdjFactor']", "1");
			ExtentReport.logPass("PASS", "test12GeneralSectionCapitalDshAdjustmentFactorField PASS");

		} catch (Exception | AssertionError e) {
			ExtentReport.logFail("FAIL", "test12GeneralSectionCapitalDshAdjustmentFactorField", driver, e);
			fail(e.getMessage());

		}
	}

	@Test
	public void test20OperatingPaymentNationalLaborRateField() throws InterruptedException, Throwable {
		try {
			doClick("(//*[contains(@id, 'customaccordianpanel')][text()='Operating Payment'])[1]");
			assertFieldValue("//*[@name='nationalOperlaborRate']", nationalOperlaborRate);
			ExtentReport.logPass("PASS", "test20OperatingPaymentNationalLaborRateField PASS");

		} catch (Exception | AssertionError e) {
			ExtentReport.logFail("FAIL", "test20OperatingPaymentNationalLaborRateField", driver, e);
			fail(e.getMessage());

		}

		// now this is 3695.94
	}

	@Test
	public void test21OperatingPaymentNationalNonLaborRateField() throws Throwable {
		try {
			assertFieldValue("//*[@name='nationalOperNonLaborRate']", nationalOperNonLaborRate);
			ExtentReport.logPass("PASS", "test21OperatingPaymentNationalNonLaborRateField PASS");

		} catch (Exception | AssertionError e) {
			ExtentReport.logFail("FAIL", "test21OperatingPaymentNationalNonLaborRateField", driver, e);
			fail(e.getMessage());
		}

	}

	@Test
	public void test22OperatingPaymentAreaWageIndexField() throws Throwable {
		try {
			assertFieldValue("//*[@name='areaWageIndex']", "1.0000");
			ExtentReport.logPass("PASS", "test22OperatingPaymentAreaWageIndexField PASS");

		} catch (Exception | AssertionError e) {
			ExtentReport.logFail("FAIL", "test22OperatingPaymentAreaWageIndexField", driver, e);

		}

	}

	@Test
	public void test23OperatingPaymentHospitalReadmissionFactorField() throws Throwable {
		try {
			assertFieldValue("//*[@name='hospitalReadmAdjFactor']", "1.0");
			ExtentReport.logPass("PASS", "test23OperatingPaymentHospitalReadmissionFactorField PASS");

		} catch (Exception | AssertionError e) {
			ExtentReport.logFail("FAIL", "test23OperatingPaymentHospitalReadmissionFactorField", driver, e);
			fail(e.getMessage());

		}

	}

	@Test
	public void test24OperatingPaymentUncompensatedCarePaymentField() throws Throwable {
		try {
			assertFieldValue("//*[@name='uncompensatedCarePayment']", "0.00");
			ExtentReport.logPass("PASS", "test24OperatingPaymentUncompensatedCarePaymentField PASS");
		} catch (Exception | AssertionError e) {
			ExtentReport.logFail("FAIL", "test24OperatingPaymentUncompensatedCarePaymentField", driver, e);
			fail(e.getMessage());

		}

	}

	@Test
	public void test25OperatingPaymentValueBasedPurchasingFactorField() throws Throwable {
		try {
			assertFieldValue("//*[@name='valueBasedPurchAdjFactor']", "1.0");
			ExtentReport.logPass("PASS", "test25OperatingPaymentValueBasedPurchasingFactorField PASS");

		} catch (Exception | AssertionError e) {
			ExtentReport.logFail("FAIL", "test25OperatingPaymentValueBasedPurchasingFactorField", driver, e);
			fail(e.getMessage());

		}

	}

	@Test
	public void test26OperatingPaymentHospitalAcquiredConditionReductionField() throws Throwable {
		try {
			assertFieldValue("//*[@name='hacReductionPercent']", "0.0");
			ExtentReport.logPass("PASS", "test26OperatingPaymentHospitalAcquiredConditionReductionField PASS");

		} catch (Exception | AssertionError e) {
			ExtentReport.logFail("FAIL", "test26OperatingPaymentHospitalAcquiredConditionReductionField", driver, e);
			fail(e.getMessage());

		}

	}

	@Test
	public void test30CapitalPaymentSectionAssertCapitalGeographicAdjustmentFactor() throws Throwable {
		try {
//			navigateCloseSectionOpenNewSection("Operating Payment", "Capital Payment");
			doClick("(//*[contains(@id, 'customaccordianpanel')][text()='Capital Payment'])[1]");
			assertFieldValue("//*[@name='capitalGeographicAdjFactor']", "1");
			ExtentReport.logPass("PASS", "test30CapitalPaymentSectionAssertCapitalGeographicAdjustmentFactor PASS");

		} catch (Exception | AssertionError e) {
			ExtentReport.logFail("FAIL", "test30CapitalPaymentSectionAssertCapitalGeographicAdjustmentFactor", driver,
					e);
			fail(e.getMessage());

		}

	}

	@Test
	public void test31CapitalPaymentSectionAssertNationalCapitalRate() throws Throwable, InterruptedException {

		try {
			assertThatFieldValue(driver.findElement(By.name("nationalCapitalRate")), nationalCapitalRate); // from
																											// ADS-1551
			ExtentReport.logPass("PASS", "test31CapitalPaymentSectionAssertNationalCapitalRate PASS");

		} catch (Exception | AssertionError e) {
			ExtentReport.logFail("FAIL", "test31CapitalPaymentSectionAssertNationalCapitalRate", driver, e);
			fail(e.getMessage());
		}

		// currently this is 466.22
	}

	@Test
	public void test40CostOutlierPaymentSectionVerifyOperatingRatioOfCostChargeField()
			throws InterruptedException, Throwable {
		try {
//			navigateCloseSectionOpenNewSection("Capital Payment", "Cost Outlier Payment");
			doClick("(//*[contains(@id, 'customaccordianpanel')][text()='Cost Outlier Payment'])[1]");
			assertThatFieldValue(modelMap.getContractModelsCostOutlierPaymentOperatingRatioOfCostCharge(), "1");
			ExtentReport.logPass("PASS", "test40CostOutlierPaymentSectionVerifyOperatingRatioOfCostChargeField PASS");

		} catch (Exception | AssertionError e) {
			ExtentReport.logFail("FAIL", "test40CostOutlierPaymentSectionVerifyOperatingRatioOfCostChargeField", driver,
					e);
			fail(e.getMessage());
		}

	}

	@Test
	public void test41CostOutlierPaymentSectionCapitalRatioOfCostChargeField() throws Throwable {
		try {
			assertThatFieldValue(modelMap.getContractModelsCostOutlierPaymentCapitalRatioOfCostCharge(), "1");
			ExtentReport.logPass("PASS", "test41CostOutlierPaymentSectionCapitalRatioOfCostChargeField PASS");

		} catch (Exception | AssertionError e) {
			ExtentReport.logFail("FAIL", "test41CostOutlierPaymentSectionCapitalRatioOfCostChargeField", driver, e);
			fail(e.getMessage());
		}

	}

	@Test
	public void test42CostOutlierPaymentSectionNonBurnMarginalCostFactorField() throws Throwable {
		try {
			assertThatFieldValue(modelMap.getContractModelsCostOutlierPaymentNonBurnMarginalCostFactor(), "0.8");
			ExtentReport.logPass("PASS", "test42CostOutlierPaymentSectionNonBurnMarginalCostFactorField PASS");

		} catch (Exception | AssertionError e) {
			ExtentReport.logFail("FAIL", "test42CostOutlierPaymentSectionNonBurnMarginalCostFactorField", driver, e);
			fail(e.getMessage());
		}

	}

	@Test
	public void test43CostOutlierPaymentSectionFixedLossThresholdField() throws Throwable {
		try {
			assertThatFieldValue(modelMap.getContractModelsCostOutlierPaymentFixedLossThreshold(),
					costOutlierPaymentFixedLossThreshold); // from ADS-1551
			ExtentReport.logPass("PASS", "test43CostOutlierPaymentSectionFixedLossThresholdField PASS");

		} catch (Exception | AssertionError e) {
			ExtentReport.logFail("FAIL", "test43CostOutlierPaymentSectionFixedLossThresholdField", driver, e);
			fail(e.getMessage());
		}
		// this is currently 29051
	}

	@Test
	public void test44CostOutlierPaymentSectionThresholdLaborPortionField() throws Throwable {
		try {
			assertThatFieldValue(modelMap.getContractModelsCostOutlierPaymentThresholdLaborPortion(),
					CostOutlierPaymentSectionThresholdLaborPortion);
			ExtentReport.logPass("PASS", "test44CostOutlierPaymentSectionThresholdLaborPortionField PASS");

		} catch (Exception | AssertionError e) {
			ExtentReport.logFail("FAIL", "test44CostOutlierPaymentSectionThresholdLaborPortionField", driver, e);
			fail(e.getMessage());
		}
	}

	@Test
	public void test51AddOnTechnologyPaymentSectionAssertSelectedServicesDisplayPaneIsEmptyByDefault()
			throws Throwable {
		try {
//			navigateCloseSectionOpenNewSection("Cost Outlier Payment", "Add On Technology Payment");
			doClick("(//*[contains(@id, 'customaccordianpanel')][text()='Add On Technology Payment'])[1]");
			waitForAjaxExtJs();
			Thread.sleep(2000);
			boolean isDisplayed = false;
			try {
				isDisplayed = driver.findElement(By.xpath(
						"//*[contains(text(),'Service')]/ancestor::div[contains(@class, 'x-grid-header-ct')]/following-sibling::div/descendant::td[contains(@class,'x-grid-cell x-grid-cell-gridcolumn')]/div"))
						.isDisplayed();
				ExtentReport.logFail("FAIL",
						"test51AddOnTechnologyPaymentSectionAssertSelectedServicesDisplayPaneIsEmptyByDefault", driver,
						null);
				fail("Selected services pane shouldn't be populated by default");
			} catch (Exception | AssertionError e) {
				assertTrue("Selected services pane shouldn't be populated by default", isDisplayed == false);
				ExtentReport.logPass("PASS",
						"test51AddOnTechnologyPaymentSectionAssertSelectedServicesDisplayPaneIsEmptyByDefault PASS");
			}
		} catch (Exception e) {
			ExtentReport.logFail("FAIL",
					"test51AddOnTechnologyPaymentSectionAssertSelectedServicesDisplayPaneIsEmptyByDefault", driver, e);

		}
	}

	@Test
	public void test52CloseDialogAndVerifyDefaultCriteriaText() throws InterruptedException, Throwable {
		try {
			doClickCloseAndContinueButtonOnEditDialog();

			editModelMap.getEditContractMainPageCriteriaTextArea().click();

		} catch (Exception | AssertionError e1) {
			ExtentReport.logFail("FAIL", "test52CloseDialogAndVerifyDefaultCriteriaText", driver, e1);
			fail(e1.getMessage());

		}
		try {
			assertThatTextAreaContainsExpectedText(editModelMap.getEditContractMainPageCriteriaTextArea(),
					expectedCriteriaText, printout);
			ExtentReport.logPass("PASS", "test52CloseDialogAndVerifyDefaultCriteriaText PASS");
		} catch (Exception | AssertionError e) {
			ExtentReport.logFail("FAIL", "test52CloseDialogAndVerifyDefaultCriteriaText", driver, e);
			fail(e.getMessage());

		}

	}

	public static void setPricingMethod(String pricingMethod) throws InterruptedException {
		waitForSpinnerToEnd();
		waitForAjaxExtJs();
		doClick(driver.findElement(By.xpath("//*[@name='pricemethodoption']")));
		waitForSpinnerToEnd();
		waitForAjaxExtJs();
		List<WebElement> menu = driver
				.findElement(By.xpath("//*[@class='x-boundlist-item' and text()='" + pricingMethod + "']/.."))
				.findElements(By.tagName("li"));
		for (WebElement option : menu) {
			if (option.getText().equals(pricingMethod)) {
				option.click();
				break;
			}
		}
	}

	@AfterClass
	public static void endtest() throws Exception {

		ExtentReport.report.flush();

	}
}
