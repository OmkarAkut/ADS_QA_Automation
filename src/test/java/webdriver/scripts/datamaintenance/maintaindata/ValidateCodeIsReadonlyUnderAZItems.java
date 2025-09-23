package webdriver.scripts.datamaintenance.maintaindata;

import static org.junit.Assert.assertTrue;
import static org.junit.Assert.fail;
import org.junit.AfterClass;
import org.junit.BeforeClass;
import org.junit.FixMethodOrder;
import org.junit.Test;
import org.junit.runners.MethodSorters;
import org.openqa.selenium.By;
import ExtentReport.ExtentReport;
import webdriver.core.Login;
import webdriver.helpers.CalculationHelper;
import webdriver.maps.DataMaintenanceMap;
import webdriver.maps.mapbuilder.BuildMap;
@FixMethodOrder(MethodSorters.NAME_ASCENDING)
/** Support Issues: Automated test script for ADS-12935 , ADS-12918**/
public class ValidateCodeIsReadonlyUnderAZItems extends CalculationHelper{
	static DataMaintenanceMap dmMap;
	static String[] aZPages= {"Activity Statistic Masters",
			"Activity Volume Data Scenarios",
			"Ad Hoc Statistic Masters",
			"Admission Sources",
			"APC Fee Schedule Masters",
			"APG Fee Schedule Masters",
			"APR DRG Fee Schedule Masters",
			"Assigned Cost Destinations",
			"Benefit Plan Masters",
			"Bill Types",
			"Care Delivery Networks",
			"Care Delivery Settings",
			"Charge Masters",
			"Chargeable Activity - HCPCS Code Mapping Schemes",
			"Chargeable Activity - Revenue Code Mapping Schemes",
			"Chargeable Activity Fee Schedule Masters",
			"Contact Types",
			"Contract Types",
			"Department Masters",
			"Discharge Statuses",
			"EAPG Fee Schedule Masters",
			"EAPG Payment Action Codes",
			"EFR Categories",
			"Encounter Types",
			"Entity Types",
			"GL Statistic Masters",
			"HCFA DRG Fee Schedule Masters",
			"HCPCS Modifiers",
			"ICD Procedure Code Masters",
			"ICD Procedure Fee Schedule Masters",
			"MSDRG Fee Schedule Masters",
			"Payment Organizations",
			"Revenue Code Fee Schedule Masters",
			"TRICARE DRG Fee Schedule Masters",
};
	@BeforeClass
	public static void setupScript() throws Exception, Throwable {
		ExtentReport.reportCreate("ValidateCodeIsReadonlyUnderAZItems", "webdriver.scripts.datamaintenance.maintaindata",
				"ValidateCodeIsReadonlyUnderAZItems");
		try {
			dmMap = BuildMap.getInstance(driver, DataMaintenanceMap.class);
			Login.loginUser("AutomationTesterAdmin");
			goToPage("Maintain Data");
			ExtentReport.logPass("PASS", "setupScript");
		} catch (Exception | AssertionError e) {
			ExtentReport.logFail("FAIL", "Failure in setupScript", driver, e);
			fail(e.getMessage());
		}
	}

	@Test
	public void test01ValidateCodeIsReadOnly_12918() throws Throwable {
		try {
			for (int i = 0; i < aZPages.length; i++) {
				System.out.println(aZPages[i]);
				selectMaintainDataAtoZ(aZPages[i]);
				doClick(DataMaintenanceMap.getazPageEditBtn());
				if (aZPages[i].equals("Assigned Cost Destinations")) {
					waitForElementToBeVisible(DataMaintenanceMap.getaddName());
					if (DataMaintenanceMap.getaddName().getAttribute("aria-disabled").equals("true")) {
						assertTrue(printout);
					} else {
						fail();
						System.out.println("Failed at azPage" + aZPages[i]);
					}
				} else {
					waitForElementToBeVisible(DataMaintenanceMap.getinputCode());
					if (driver.findElement(By.name("code")).getAttribute("aria-disabled").equals("true")) {
						assertTrue(printout);
					} else {
						fail();
						System.out.println("Failed at azPage" + aZPages[i]);
					}
				}
				doClick(DataMaintenanceMap.getCancelCloseButton());
			}
			ExtentReport.logPass("PASS", "test01ValidateCodeIsReadOnly_12918");
		} catch (Exception | AssertionError e) {
			ExtentReport.logFail("FAIL", "test01ValidateCodeIsReadOnly_12918", driver, e);
			fail(e.getMessage());
		}
	}
	@Test
	public void test02ValidateMinCopayStatusForIndustryScheme_12935() throws Throwable {
		try {
			selectMaintainDataAtoZ("Industry Classification Schemes");
			for(int i=1;i<=driver.findElements(By.xpath("//div[contains(@id,'masterlist')]//following::table")).size();i++) {
				if(i==7) {
					if(driver.findElement(By.xpath("(//div[contains(@id,'masterlist')]//following::table["+i+"]//div)[6]")).getText().equals("APC")) {
						doClick("(//div[contains(@id,'masterlist')]//following::table["+i+"])");
						doClick(DataMaintenanceMap.getazPageEditBtn());
						doClick(DataMaintenanceMap.getgroupsPanel());
						doClick(DataMaintenanceMap.getgroupsRow1());
						doClick(DataMaintenanceMap.getGroupsEditBtn());
						if(DataMaintenanceMap.getminiCopyStatusCheck().getAttribute("class").contains("checked")){
							doClick(DataMaintenanceMap.getgroupCancelClose());
							if(DataMaintenanceMap.getgroupGridMiniCopayCheck().getAttribute("class").contains("checked")) {
								assertTrue(printout);
							}
							else {
								fail();
							}
							break;
						}
						else if(!DataMaintenanceMap.getminiCopyStatusCheck().getAttribute("class").contains("checked")) {
							doClick(DataMaintenanceMap.getgroupCancelClose());
							if(!DataMaintenanceMap.getgroupGridMiniCopayCheck().getAttribute("class").contains("checked")) {
								assertTrue(printout);
							}
							else {
								fail();
							}
						}
						break;
					}
				}
			}
			doClick(DataMaintenanceMap.getCancelCloseButton());
			ExtentReport.logPass("PASS", "test02ValidateMinCopayStatusForIndustryScheme_12935");
		} catch (Exception | AssertionError e) {
			ExtentReport.logFail("FAIL", "test02ValidateMinCopayStatusForIndustryScheme_12935", driver, e);
			fail(e.getMessage());
		}
	}
	@AfterClass
	public static void endtest() throws Exception {
		ExtentReport.report.flush();
	}
}
