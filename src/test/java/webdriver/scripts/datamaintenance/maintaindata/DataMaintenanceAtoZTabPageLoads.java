package webdriver.scripts.datamaintenance.maintaindata;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.fail;

import java.util.Arrays;
import java.util.List;

import org.junit.AfterClass;
import org.junit.BeforeClass;
import org.junit.FixMethodOrder;
import org.junit.Test;
import org.junit.runners.MethodSorters;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import com.aventstack.extentreports.Status;
import ExtentReport.*;
import junit.framework.AssertionFailedError;
import webdriver.core.Login;
import webdriver.helpers.DataMaintenanceHelper;
import webdriver.scripts.datamaintenance.datamaintenancedata.DataMaintenanceAtoZPagesData;

@FixMethodOrder(MethodSorters.NAME_ASCENDING)
public class DataMaintenanceAtoZTabPageLoads extends DataMaintenanceHelper {
	//"OPPS Non-Device Trigger Combinations"
	//  public static String[] expectedList = {
	//          "OPPS Non-Device Trigger Combinations"
	//  };

	/**
	 * Test Location: Data Maintenance > Maintain Data > A to Z tab
	 * Data file: DataMaintenanceAtoZPagesData
	 * Description: Verifies that page names display in proper directories and
	 * that individual pages load properly (in main pane).
	 * There is a short and complete version.
	 * Short tests the first few pages, complete tests all the pages.
	 * This is changed by changing the data that is used.
	 * @throws Throwable 
	 */
	@BeforeClass
	public static void setupScript() throws Throwable {
		ExtentReport.reportCreate("DataMaintenanceAtoZTabPageLoads", "webdriver.scripts.datamaintenance.maintaindata","DataMaintenanceAtoZTabPageLoads");
		try {
			System.out.println("TEST CLASS: " + DataMaintenanceAtoZTabPageLoads.class.getSimpleName());

			Login.loginUser("ApplicationAdministrator1");


			goToPage("Maintain Data");
			waitForSpinnerToEnd();
			
				driver.findElement(By.xpath("//span[text() = 'A - Z']")).click();
						waitForAjaxExtJs();
			ExtentReport.logPass("PASS", "setupScript");
		}catch (Exception|AssertionError e) {
			ExtentReport.logFail("FAIL", "Failure in setupScript", driver, e);
			fail(e.getMessage());
		}
	}

	@Test
	public void test01MaintainDataPageAssertAtoZDisplayedListContainsAllItems() throws Throwable {
		try {
			List<WebElement> pageListElements = javaMakeListOfWebElements(
					driver.findElement(By.id("dataviewId")),
					"div"
					);
			List<String> actualPageList = javaMakeListOfStrings(pageListElements);
			//		if (testEnvironment.contains("echelon")) {
			//			System.out.println("Expected list length: "
			//					+ DataMaintenanceAtoZPagesData.expectedMaintainDataAtoZPagesCompleteListEchelon.length);
			//			System.out.println("Actual list length  : " + actualPageList.size());
			//			assertEquals(
			//					Arrays.asList(DataMaintenanceAtoZPagesData.expectedMaintainDataAtoZPagesCompleteListEchelon),
			//					actualPageList
			//					);
			if (testEnvironment.contains("qa3")) {
				System.out.println("Expected list length: "
						+ DataMaintenanceAtoZPagesData.expectedMaintainDataAtoZPagesCompleteListQA3.length);
				System.out.println("Actual list length  : " + actualPageList.size());
				try {
					assertEquals(
							Arrays.asList(DataMaintenanceAtoZPagesData.expectedMaintainDataAtoZPagesCompleteListQA3),
							actualPageList
							);
					ExtentReport.logPass("PASS", "test01MaintainDataPageAssertAtoZDisplayedListContainsAllItems");
					//ExtentReport.extenttest.log(Status.PASS, "test01MaintainDataPageAssertAtoZDisplayedListContainsAllItems : PASS");
;
				} catch (Exception|AssertionError e) {
					ExtentReport.logFail("FAIL","test01MaintainDataPageAssertAtoZDisplayedListContainsAllItems",driver,e);


				}
			} else {
				System.out.println("Expected list length: "
						+ DataMaintenanceAtoZPagesData.expectedMaintainDataAtoZPagesCompleteListEvolve.length);
				System.out.println("Actual list length  : " + actualPageList.size());
				try {
					assertEquals(
							Arrays.asList(DataMaintenanceAtoZPagesData.expectedMaintainDataAtoZPagesCompleteListEvolve),
							actualPageList
							);
					ExtentReport.logPass("PASS", "test01MaintainDataPageAssertAtoZDisplayedListContainsAllItems");

				} catch (Exception|AssertionError e) {
					ExtentReport.logFail("FAIL","test01MaintainDataPageAssertAtoZDisplayedListContainsAllItems",driver,e);
				}
			}
		} catch (Exception|AssertionError e) {
			ExtentReport.logFail("FAIL","test01MaintainDataPageAssertAtoZDisplayedListContainsAllItems",driver,e);
			fail(e.getMessage());
		}
	}

	@Test
	public void test02MaintainDataPageAssertAtoZListPagesLoadProperly() throws InterruptedException,Throwable {
		//		if (testEnvironment.contains("echelon")) {
		try {
			if (testEnvironment.contains("qa3")) {
				try {
				
					assertPageLoads(
							//					Arrays.asList(DataMaintenanceAtoZPagesData.expectedMaintainDataAtoZPagesCompleteListEchelon));
							Arrays.asList(DataMaintenanceAtoZPagesData.expectedMaintainDataAtoZPagesCompleteListQA3));
					
					ExtentReport.logPass("PASS", "test02MaintainDataPageAssertAtoZListPagesLoadProperly");

				} catch (Exception|AssertionError e) {
					ExtentReport.logFail("FAIL","test02MaintainDataPageAssertAtoZListPagesLoadProperly",driver,e);


				}
			} else if (testEnvironment.contains("evolve")) {
				try {
					assertPageLoads(
							Arrays.asList(DataMaintenanceAtoZPagesData.expectedMaintainDataAtoZPagesCompleteListEvolve));
					ExtentReport.logPass("PASS", "test02MaintainDataPageAssertAtoZListPagesLoadProperly");

				} catch (Exception|AssertionError e) {
					ExtentReport.logFail("FAIL","test02MaintainDataPageAssertAtoZListPagesLoadProperly",driver,e);

				}
			} 
			else if (testEnvironment.contains("ads11")) {
				try {
					assertPageLoads(
							Arrays.asList(DataMaintenanceAtoZPagesData.expectedMaintainDataAtoZPagesCompleteListQA3));
					ExtentReport.logPass("PASS", "test02MaintainDataPageAssertAtoZListPagesLoadProperly");

				} catch (Exception|AssertionError e) {
					ExtentReport.logFail("FAIL","test02MaintainDataPageAssertAtoZListPagesLoadProperly",driver,e);

				}
			} else {
				try {
					assertPageLoads(
							Arrays.asList(DataMaintenanceAtoZPagesData.expectedMaintainDataAtoZPagesCompleteList));
					ExtentReport.extenttest.log(Status.PASS, "test02MaintainDataPageAssertAtoZListPagesLoadProperly : PASS");

				} catch (Exception e) {
					ExtentReport.logFail("FAIL","test02MaintainDataPageAssertAtoZListPagesLoadProperly",driver,e);

				}
				//Arrays.asList(expectedList));
			}
		} catch (Exception|AssertionError e) {
			ExtentReport.logFail("FAIL","test02MaintainDataPageAssertAtoZListPagesLoadProperly",driver,e);
			fail(e.getMessage());
		}
	}
	@AfterClass
	public static void endtest() throws Exception {

		ExtentReport.report.flush();

	}
}
