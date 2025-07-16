package webdriver.scripts.episodes;

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

@FixMethodOrder(MethodSorters.NAME_ASCENDING)
public class EpisodesPostDischargeLogicUsesAdmitDateToQualifyEncountersAds2579 extends CalculationHelper {

  static final String episodeName1 = "2015 BPCI Renal Failure";//Shilpa 05.09.2022 updated from v103 REGRESSION Post Discharge 2
  //static final String episodeName2 = "v103 REGRESSION Post Discharge 2";
  final byte numberOfEfrs = 51;

  /** Regression: Automated test script for ADS-2579 
 * @throws Throwable */
  @BeforeClass
  public static void setupScript() throws Throwable {
	  ExtentReport.reportCreate("EpisodesPostDischargeLogicUsesAdmitDateToQualifyEncountersAds2579", "webdriver.scripts.episodes", "EpisodesPostDischargeLogicUsesAdmitDateToQualifyEncountersAds2579");
    try {
		System.out.println("Test Class: " + EpisodesPostDischargeLogicUsesAdmitDateToQualifyEncountersAds2579.class.getSimpleName());
		Login.loginUser("EpisodeAnalyst1");
		goToPage("Episode Models");
		ExtentReport.logPass("PASS", "setupScript");
	}  catch (Exception|AssertionError e) {
		ExtentReport.logFail("FAIL", "setupScript", driver, e);
		fail(e.getMessage());
	}
  }

  @Test
  public void test00Setup() throws InterruptedException,Throwable {
    try {
		filterAndSelectContractModelFromContractModelLibrary(episodeName1);
		doClickButton("Open Task List");
		doClickTree("Model Episode");
		ExtentReport.logPass("PASS", "test00Setup");
	} catch (Exception e) {
		ExtentReport.logFail("FAIL", "test00Setup", driver, e);
		fail(e.getMessage());
	}
    //doClickCheckboxTree("Assign Episode to Encounters");
  }

//  @Ignore
//  @Test
//  public void test01RemoveAssignmentsAndVerifyEfrs() {
//    try {
//      clickButtonAndVerifyEfrCountAndZeroErrorsOnSummaryDialogAndCloseDialog("Remove", (byte) 0);
//      deleteMyCalculationStatusFirstRow();
//    } catch (Throwable e) {
//      fail(e.getMessage());
//    }
//  }
//
//  @Test
//  public void test02QueryDatabaseAndAssertEpisodeModelId() throws ClassNotFoundException {
//    String sql1 = "Select * from episode_model where name like 'v103 REGRESSION Post Discharge 1'";
//    getDataFromDatabaseAndAssertExpectedValues(
//            String sql1 = "Select * from episode_model where name like 'v103 REGRESSION Post Discharge 1'",
//            opps2020.expectedValuesRadioPharm
//    );
//  }
//
//  @Test
//  public void test03QueryDatabaseAndAssertAssignmentsWereRemoved72212() throws ClassNotFoundException {
//    String sql1 = "select * from episode_encounter\n" +
//            "where episode_model_criteria_id = 72212";
//    getDataFromDatabaseAndAssertExpectedValues(
//            String sql1 = "Select * from episode_model where name like 'v103 REGRESSION Post Discharge 1'",
//            opps2020.expectedValuesRadioPharm
//    );
//  }

  //this one is ready to test
//  @Test
//  public void test04QueryDatabaseAndAssertAssignmentsWereRemoved72212() throws ClassNotFoundException {
//    List<String> expected72212 = Arrays.asList("ADS1083PDC001");
//    getStringDataFromDatabaseAndAssertExpectedValues(
//            setSql(72212),
//            1,
//            expected72212
//    );
//  }

//  @Test
//  public void test05QueryDatabaseAndAssignmentsWereRemoved72213()
//          throws ClassNotFoundException, InterruptedException {
//    List<String> expected72213 = Arrays.asList("ADS1083PDA002");
//    getStringDataFromDatabaseAndAssertExpectedValues(
//            setSql(72213),
//            1,
//            expected72213
//    );
//    doClosePageOnLowerBar("Calculation Status");
//  }
//
//  @Test
//  public void test06ClickAssignButtonAndAssertSummaryIsErrorFree() throws InterruptedException {
//    try {
//      clickButtonAndVerifyEfrCountAndZeroErrorsOnSummaryDialogAndCloseDialog("Assign", numberOfEfrs);
//      deleteMyCalculationStatusFirstRow();
//    } catch (Throwable e) {
        //doClosePageOnLowerBar("Calculation Status");
//      fail(e.getMessage());
//    }
//    doClosePageOnLowerBar("Calculation Status");
//  }
//
//  @Test
//  public void test07QueryDatabaseAndAssertAssignment72212() throws ClassNotFoundException {
//    List<String> expected72213 = Arrays.asList("ADS1083PDA002");
//    getStringDataFromDatabaseAndAssertExpectedValues(
//            setSql(72213),
//            1,
//            expected72213
//    );
//  }
//
//  @Test
//  public void test08QueryDatabaseAndAssertAssignments72213() throws ClassNotFoundException {
//    List<String> expected72213 = Arrays.asList("ADS1083PDA002");
//    getStringDataFromDatabaseAndAssertExpectedValues(
//            setSql(72213),
//            1,
//            expected72213
//    );
//  }

  private String setSql(int id) {
    String sql = "select encounterid from episode_encounter where episode_model_criteria_id = " + id;
    return sql;
  }

  public void doClickTree(String name) throws InterruptedException {
//	  Omkar 17/4/2023 : xpath changes for 11.2
//    waitForPresenceOfElement("//td[contains(@class,'x-grid-cell-treecolumn')]/div[text()='" + name + "']");
//    driver.findElement(By.xpath("//td[contains(@class,'x-grid-cell-treecolumn')]/div[text()='" + name + "']")).click();
	  waitForPresenceOfElement("//div[contains(@class,'x-grid-cell-inner x-grid-cell-inner-treecolumn')]//span[text()='" + name + "']");
    driver.findElement(By.xpath("//div[contains(@class,'x-grid-cell-inner x-grid-cell-inner-treecolumn')]//span[text()='" + name + "']")).click();
    waitForSpinnerToEnd();
    waitForAjaxExtJs();
    waitForSpinnerToEnd();
  }

  public void doClickCheckboxTree(String name) throws InterruptedException {
    waitForPresenceOfElement("//td[contains(@class,'x-grid-cell-treecolumn')]/div[text()='" + name + "']");
    driver.findElement(
            By.xpath(
                    "//td[contains(@class,'x-grid-cell-treecolumn')]" +
                            "/div[text()='" + name + "']/input[@class='x-tree-checkbox']/.."))
            .click();
    waitForSpinnerToEnd();
    waitForAjaxExtJs();
    waitForSpinnerToEnd();
  }

  public void clickButtonAndVerifyEfrCountAndZeroErrorsOnSummaryDialogAndCloseDialog(String button, byte numberOfEfrs) throws InterruptedException {
    waitForSpinnerToEnd();
    waitForAjaxExtJs();
    doClickButton(button);
    waitForCalculationToEnd();
    driver.findElement(By.xpath("//button/span[text()='View']")).click();
    waitForSpinnerToEnd();
    Thread.sleep(1000);
    assertElementIsDisplayed(driver.findElement(By.xpath("//*[text()='Total EFRs to be processed: " + numberOfEfrs + "']")), printout);
    assertElementIsDisplayed(driver.findElement(By.xpath("//*[text()='Errors: 0']")), printout);
    //Completed at: 11/18/2019 01:52 PM
    driver.findElement(By.xpath("//button/span[text()='Cancel']")).click();
    waitForSpinnerToEnd();
  }
  @AfterClass
	public static void endtest() throws Exception {

		ExtentReport.report.flush();

	}
}
