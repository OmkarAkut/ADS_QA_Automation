package webdriver.scripts.contracting;

import static org.junit.Assert.assertTrue;
import static org.junit.Assert.fail;
import java.util.ArrayList;
import java.util.List;
import org.junit.AfterClass;
import org.junit.BeforeClass;
import org.junit.FixMethodOrder;
import org.junit.Test;
import org.junit.runners.MethodSorters;
import org.openqa.selenium.By;
import org.openqa.selenium.Keys;
import org.openqa.selenium.interactions.Actions;
import ExtentReport.ExtentReport;
import webdriver.core.Login;
import webdriver.corehelpers.GoHelper;
import webdriver.helpers.ContractModelsHelper;
import webdriver.maps.ContractingMap;
import webdriver.maps.mapbuilder.BuildMap;

@FixMethodOrder(MethodSorters.NAME_ASCENDING)
/** Support Issues: Automated test script for ADS-12574 */
public class PricingMethodLevelOfCareValidateSave extends GoHelper {

	private static ContractingMap modelMap;
	static ContractModelsHelper contractModelsHelper = new ContractModelsHelper();
	static String levelOfCareTable = "ASESC-2465 LOC 2";
	static String pricingOption = "Level of Care";
	static String pricingOption2="Case Rate";
	List<String> serviceNameList = new ArrayList<>();
	List<String> serviceValueList = new ArrayList<>();
	static String serviceModelExist;
	static String serviceName;
	static String serviceValue;
	static String caseRate;
	static String serviceModelCaseRate;
	Actions action = new Actions(driver);

	@BeforeClass
	public static void setupScript() throws Exception, Throwable {

		ExtentReport.reportCreate("PricingMethodLevelOfCareValidateSave", "webdriver.scripts.contracting",
				"PricingMethodLevelOfCareValidateSave");
		try {
			modelMap = BuildMap.getInstance(driver, ContractingMap.class);
			Login.loginUser("ContractAnalyst1");
			goToPage("Contract Models");
			waitForDisplayedSpinnerToEnd();
			assertThatString(modelMap.getContractModelHeader(), "Contracting Model Library", printout);
			ExtentReport.logPass("PASS", "setupScript");
		} catch (Exception | AssertionError e) {
			ExtentReport.logFail("FAIL", "setupScript", driver, e);
			fail(e.getMessage());
		}
	}

	@Test
	public void test01AddValuesToServicesForLevelOfCare_12574() throws Throwable {
		try {
			doClick(ContractingMap.getopenTaskList());
			contractModelsHelper.navigateFeeForServicePaymentTerms();
			doClick(ContractingMap.getContractFeeForServicePaymentServiceModelHeaderText());
			serviceModelExist = ValidateEditOptionForEapgFeeSchedulePricingMethod
					.validateIfContractModelHasServiceModel(pricingOption);
			doClick(ContractingMap.getlevelOfCareDropdown());
			waitForElementToBeVisible(ContractingMap.getlevelOfCareDropDwn());
			doDropdownSelectUsingOptionTextWithelement(ContractingMap.getlevelOfCareDropDwn(), levelOfCareTable);
			for (int i = 1; i <= ContractingMap.getlevelOfCareServices().size(); i++) {
				action.moveToElement(driver.findElement(By.xpath("(//div[text()='Level of Care']//following::div[@class='x-grid-item-container']//table["+ i + "]//div)[3]"))).doubleClick().sendKeys(Keys.CLEAR).sendKeys(generateRandomNumber()).sendKeys(Keys.ENTER).perform();
				serviceName = driver.findElement(By.xpath("(//div[text()='Level of Care']//following::div[@class='x-grid-item-container']//table["+ i + "]//div)[2]")).getText();
				serviceValue = driver.findElement(By.xpath("(//div[text()='Level of Care']//following::div[@class='x-grid-item-container']//table["+ i + "]//div)[3]")).getText();
				serviceNameList.add(serviceName);
				serviceValueList.add(serviceValue);
			}
			doClick(modelMap.getContractModelRiskLimiterContinueCloseBtn());
			doClick(ContractingMap.SaveOption());
			doClick(modelMap.getContractModelContinue());
			driverDelay(100);
			doClick(ContractingMap.getContractFeeForServicePaymentSave());
			ExtentReport.logPass("PASS", "test01ValidateAdvancedOptionsUnderStopLoss_12542");
		} catch (Exception | AssertionError e) {
			ExtentReport.logFail("FAIL", "test01ValidateAdvancedOptionsUnderStopLoss_12542", driver, e);
			fail(e.getMessage());

		}
	}

	@Test
	public void test02ValidateValuesSavedForLevelOfCare_12574() throws Throwable {
		try {
			doClickTreeItem("Fee For Service Payment Terms");
			doClick(ContractingMap.getContractFeeForServicePaymentPricingMethodHeaderText());
			doClick("(//div[contains(@class,'x-tree-view')])[4]//span[text()='" + serviceModelExist + "']");
			String criteria = ContractingMap.getpricingCriteria().getAttribute("value");
			for (int i = 0; i < serviceNameList.size(); i++) {
				if (criteria.contains(serviceNameList.get(i).toString())) {
					System.out.println(serviceNameList.get(i).toString());
					assertTrue(printout);
				} else {
					fail();
				}
			}
			
			for (int i = 0; i < serviceValueList.size(); i++) {
				System.out.println(serviceValueList.get(i).toString());
				if (criteria.contains(serviceValueList.get(i).toString().replaceAll(".00", ""))) {
					System.out.println(serviceValueList.get(i));
					assertTrue(printout);
				} else {
					fail();
				}
			}
			if (criteria.contains(levelOfCareTable)) {
				assertTrue(printout);
			} else {
				fail();
			}
			serviceNameList.clear();
			serviceValueList.clear();
			
			ExtentReport.logPass("PASS", "test02ValidateValuesSavedForLevelOfCare_12574");
		} catch (Exception | AssertionError e) {
			ExtentReport.logFail("FAIL", "test02ValidateValuesSavedForLevelOfCare_12574", driver, e);
			fail(e.getMessage());

		}
	}
	@Test
	public void test03ValidateValuesSavedForCaseRate_12904() throws Throwable {
		try {
			doClick(ContractingMap.getContractFeeForServicePaymentServiceModelHeaderText());
			doClick(ContractingMap.getContractFeeForServicePaymentPricingMethodHeaderText());
			serviceModelCaseRate=ValidateEditOptionForEapgFeeSchedulePricingMethod.validateIfContractModelHasServiceModel(pricingOption2);
			action.moveToElement(ContractingMap.getcaseRate()).doubleClick().sendKeys(Keys.CLEAR).sendKeys(generateRandomNumber()).sendKeys(Keys.ENTER).perform();
			caseRate=ContractingMap.getcaseRate().getText().replaceAll(".00", "");
			doClick(modelMap.getContractModelRiskLimiterContinueCloseBtn());
			doClick(ContractingMap.SaveOption());
			doClick(modelMap.getContractModelContinue());
			driverDelay(100);
			doClick(ContractingMap.getContractFeeForServicePaymentSave());
			doClickTreeItem("Fee For Service Payment Terms");
			doClick(ContractingMap.getContractFeeForServicePaymentPricingMethodHeaderText());
			doClick("(//div[contains(@class,'x-tree-view')])[4]//span[text()='" + serviceModelCaseRate + "']");
			String criteria = ContractingMap.getpricingCriteria().getAttribute("value");
			if(criteria.contains("$"+caseRate+" per case")) {
				assertTrue(printout);
			} else {
				fail();
			}
			doClick(ContractingMap.getCloseContractBtn);
			ExtentReport.logPass("PASS", "test02ValidateValuesSavedForLevelOfCare_12574");
		} catch (Exception | AssertionError e) {
			ExtentReport.logFail("FAIL", "test02ValidateValuesSavedForLevelOfCare_12574", driver, e);
			fail(e.getMessage());

		}
	}
	@AfterClass
	public static void endtest() throws Exception {
		ExtentReport.report.flush();
	}
}
