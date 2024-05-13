package webdriver.scripts.contracting;

import static org.junit.Assert.fail;

import org.junit.AfterClass;
import org.junit.BeforeClass;
import org.junit.Test;
import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;

import ExtentReport.ExtentReport;
import webdriver.core.Login;
import webdriver.corehelpers.DoHelper;
import webdriver.corehelpers.GoHelper;
import webdriver.helpers.ContractModelsHelper;
import webdriver.maps.ContractingMap;
import webdriver.maps.mapbuilder.BuildMap;

public class EditRiskLimiterScreen extends GoHelper{
	
	private static ContractingMap modelMap;
	static String contractModelName="ADS2371 Test Contract";
	static String serviceModel="ADS2371 Test";
	static String availableService="OPPS 2020";

	/** Regression: Automated test script for ADS-6079, has issue ADS-12487 */

	@BeforeClass
	public static void setupScript() throws Exception, Throwable {
		ExtentReport.reportCreate("EditRiskLimiterScreen", "webdriver.scripts.contracting",
				"EditRiskLimiterScreen");
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
	//ADS-6079[all steps]
	@Test
	public void test01ContractModelEditRiskLimiterScreen_ADS_6079() throws Throwable {
		try {
			doSearchForContractModel(contractModelName);
			tableDoubleClickCellFirstColumn(contractModelName);
//			waitForPageTitle(contractModelName);
			doClickTreeItem("Model Contract");
			waitUntilTreeOptionIsClickable("Define Payment Terms");
			doClickTreeItem("Define Payment Terms");
			doClickTreeItem("Fee For Service Payment Terms");
			ContractModelsHelper.navigateFeeForServicePaymentTermsScreenSelectionSelectServiceModel("Risk Limiter Model",serviceModel);
			ContractModelsHelper.navigateFeeForServicePaymentTermsPageRiskLimiterSectionClickEditButtonToOpenEditDialog();
//			Omkar 13/7/2023 : xpath changes for 11.2
//			assertElementText(driver.findElement(By.xpath("//span[text()='Edit Risk Limiter - "+serviceModel+"']")), "Edit Risk Limiter - "+serviceModel, printout);
			assertElementText(driver.findElement(By.xpath("//div[text()='Edit Risk Limiter - "+serviceModel+"']")), "Edit Risk Limiter - "+serviceModel, printout);
			navigateCloseGeneralSectionOpenNewSection("Advanced Options");
			assertThatElementIsChecked(driver.findElement(By.xpath("//label[text()='Apply to Included/Excluded Services']//preceding::input[@name='applyToPatientsInTotal'][1]")));
			assertElementText(driver.findElement(By.xpath("(//div[contains(text(),'Edit Risk Limiter')]//following::div[contains(@class,'glAccountsGrid')]//td/div[text()='"+availableService+"'])[2]")), availableService, printout);
			DoHelper.scrollToView("(//div[contains(text(),'Edit Risk Limiter')]//following::div[contains(@class,'glAccountsGrid')]//td/div[text()='"+availableService+"'])[3]");
			assertElementText(driver.findElement(By.xpath("(//div[contains(text(),'Edit Risk Limiter')]//following::div[contains(@class,'glAccountsGrid')]//td/div[text()='"+availableService+"'])[3]")), availableService, printout);
			doClick("//a[contains(@class,'x-btn windowbtn')]//span[text()='Continue & Close']");//has issue  ADS-12487
			assertElementIsDisplayed(driver.findElement(By.xpath("//span[contains(@class,'x-panel-header-text')][text()='Risk Limiter Model']/../following-sibling::div")));
			doClick(ContractingMap.getContractModelRiskLimiterCancelCloseBtn());
			waitForElementToBeVisible(modelMap.getContractModelRiskLimiterMessageBox());
			doClick(ContractingMap.getContractModelRiskLimiterMessageBoxCancelCloseBtn());
			doClick("//span[text()='ADS2371 Test...']//following::a");
			doClosePageOnLowerBar("Costing Models");
			ExtentReport.logPass("PASS", "setupScript");
		} catch (Exception | AssertionError e) {
			ExtentReport.logFail("FAIL", "setupScript", driver, e);
			fail(e.getMessage());
		}
	}
	
	@AfterClass
	public static void endtest() throws Exception {

		ExtentReport.report.flush();

	}
}
