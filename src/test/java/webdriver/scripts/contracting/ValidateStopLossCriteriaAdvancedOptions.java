package webdriver.scripts.contracting;

import static org.junit.Assert.fail;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.List;
import org.junit.AfterClass;
import org.junit.BeforeClass;
import org.junit.FixMethodOrder;
import org.junit.Test;
import org.junit.runners.MethodSorters;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import ExtentReport.ExtentReport;
import webdriver.core.Login;
import webdriver.corehelpers.GoHelper;
import webdriver.helpers.CimHelper;
import webdriver.helpers.ContractModelsHelper;
import webdriver.maps.ContractingMap;
import webdriver.maps.mapbuilder.BuildMap;
@FixMethodOrder(MethodSorters.NAME_ASCENDING)
/** Support Issues: Automated test script for ADS-12542 ,ADS-12917*/
public class ValidateStopLossCriteriaAdvancedOptions extends GoHelper{
	private static ContractingMap modelMap;
	static ContractModelsHelper contractModelsHelper = new ContractModelsHelper();
	static String contractModelName="ASESC-485 NO CARVE HCFA ADDON";
	static String riskLimitRecord;
	static List<WebElement> elements=new ArrayList<>();
	static String currentDateTime = new SimpleDateFormat("yyyy.MM.dd.HH.mm.ss").format(new java.util.Date());
	static String riskLimiter = "Model " + currentDateTime;
	static String lossType="Add On";
	@BeforeClass
	public static void setupScript() throws Exception, Throwable {
		
		ExtentReport.reportCreate("ValidateStopLossCriteriaAdvancedOptions", "webdriver.scripts.contracting",
				"ValidateStopLossCriteriaAdvancedOptions");
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
	public void test01ValidateAdvancedOptionsUnderStopLoss_12542() throws Throwable {
		try {
			ValidateDragDropAddNewServiceUnderPricing.searchContractModelOpenTaskList(contractModelName);
			doClick(ContractingMap.getContractFeeForServicePaymentRiskLimiterModelExpandBtn());
			waitForElementToBeVisible(ContractingMap.getriskLimitRecord());
			doClick(ContractingMap.getriskLimitRecord());
			riskLimitRecord=ContractingMap.getriskLimitRecord().getText();
			doClick(ContractingMap.getriskLimitEditBtn());
			waitForPresenceOfElement("//div[contains(@id,'dynamicwindow')][text()='Edit Risk Limiter - "+riskLimitRecord+"']");
			navigateCloseGeneralSectionOpenNewSection("Stop Loss");
			WebElement[] stopLossElements = {
					ContractingMap.getriskLimitThresholdField(),
					ContractingMap.getriskLimitAddOnPaymentField(),
					ContractingMap.getriskLimitThresholdParameters(),
					ContractingMap.getriskLimitThresholdOperators(),
					ContractingMap.getriskLimitAddOnPayParameters(),
					ContractingMap.getriskLimitAddOnPayOperators(),
			};
			assertElementsAreDisplayed(stopLossElements, printout);
			doClick(ContractingMap.getriskLimitCancelCloseBtn());
			
		ExtentReport.logPass("PASS", "test01ValidateAdvancedOptionsUnderStopLoss_12542");
		} catch (Exception | AssertionError e) {
			ExtentReport.logFail("FAIL", "test01ValidateAdvancedOptionsUnderStopLoss_12542", driver, e);
			fail(e.getMessage());

		}
	}
	@Test
	public void test02AddNewRiskLimiter_12917() throws Throwable {
		try {
			doClick(ContractingMap.getriskLimiterNew());
			waitForElementToBeVisible(ContractingMap.getriskLimiterWindow());
			keyInInputText(riskLimiter, ContractingMap.getInputName());
			WebElement source=driver.findElement(By.xpath("(//div[text()='New Risk Limiter']//following::div[@class='x-grid-item-container']//div)[1]"));
			WebElement target=driver.findElement(By.xpath("(//div[contains(@class,'x-grid-view x-grid-with-row-lines')])[4]"));
			CimHelper.dragAndDrop(source, target);
			driverDelay(500);
			navigateCloseGeneralSectionOpenNewSection("Stop Loss");
			driverDelay(100);
			driver.findElement(By.name("stopLossType")).click();
			driverDelay(100);
			doDropdownSelectUsingOptionTextWithelement(ContractingMap.getstopLossTypes(),
					lossType);
			doClick(ContractingMap.getstopLossAdvBtn1());
			waitForElementToBeVisible(ContractingMap.getriskLimitThresholdField());
			doubleActionClick(ContractingMap.getcareSettingCode());
			ContractingMap.getthresholdCriteria().sendKeys(" = 'XXX'");
			doClick(ContractingMap.getstopLossAdvBtn1());
			waitForElementToBeVisible(ContractingMap.getriskLimitAddOnPaymentField());
			doubleActionClick(ContractingMap.getbasePayment());
			ContractingMap.getaddOnCriteria().sendKeys(" * 2");
			doClick(ContractingMap.getnewRiskContinueCloseBtn());
			
			ExtentReport.logPass("PASS", "test02AddNewRiskLimiter_12917");
		} catch (Exception | AssertionError e) {
			ExtentReport.logFail("FAIL", "test02AddNewRiskLimiter_12917", driver, e);
			fail(e.getMessage());

		}
	}
	@Test
	public void test03ValidateNewRiskLimiter_CheckSimpleOptions_12917() throws Throwable {
		try {
			doClick("//div[text()='"+riskLimiter+"']");
			doClick(ContractingMap.getriskLimiterEdit());
			waitForElementToBeVisible(ContractingMap.geteditRiskLimiterWindow());
			navigateCloseGeneralSectionOpenNewSection("Stop Loss");
			driverDelay(100);
			assertElementIsDisplayed(ContractingMap.getstopLossSimpleBtn1(), printout);
			assertElementIsDisplayed(ContractingMap.getstopLossSimpleBtn2(), printout);
			doClick(ContractingMap.getEditRiskContinueCloseBtn());
			doClick("//div[text()='"+riskLimiter+"']");
			doClick(ContractingMap.getriskLimiterDelete());
			waitForElementToBeVisible(ContractingMap.getwarningDeleteBtn());
			doClick(ContractingMap.getwarningDeleteBtn());
			doClick(ContractingMap.getCloseContractBtn);
			doClick(ContractingMap.getWarningCancelCloseBtn());
			doClosePageOnLowerBar("Contract Models");
			ExtentReport.logPass("PASS", "test02AddNewRiskLimiter_12917");
		} catch (Exception | AssertionError e) {
			ExtentReport.logFail("FAIL", "test02AddNewRiskLimiter_12917", driver, e);
			fail(e.getMessage());

		}
	}
	@AfterClass
	public static void endtest() throws Exception {
		ExtentReport.report.flush();
	}
}
