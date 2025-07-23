package webdriver.scripts.cim;
import static org.junit.Assert.fail;
import java.text.SimpleDateFormat;

import org.junit.AfterClass;
import org.junit.BeforeClass;
import org.junit.FixMethodOrder;
import org.junit.Test;
import org.junit.runners.MethodSorters;
import org.openqa.selenium.By;

import ExtentReport.ExtentReport;
import webdriver.core.Login;
import webdriver.helpers.CimHelper;
import webdriver.maps.CimMap;
import webdriver.maps.ContractingMap;
import webdriver.maps.mapbuilder.BuildMap;

@FixMethodOrder(MethodSorters.NAME_ASCENDING)
/** Regression test case ADS-21963**/
public class ModificationsToCalculationStatusforCIM extends CimHelper {
	private static CimMap cimMap;
	private static ContractingMap modelMap;
	private static String currentDateTime = new SimpleDateFormat("yyyy.MM.dd.HH.mm.ss").format(new java.util.Date());
	private static String cimScenarioCreate = "CIM" + currentDateTime;
	static String[] filterCim = { "Name", "Is", "Equal To", cimScenarioCreate };
	static String calcType=getProperty().replaceAll("^\"|\"$", "");
	static String recordsProcessed;
	static String recordsPending;
	static String totalRecords;
	static String calcEndtime;
	static String calcStartTime;
	static String duration;
	static String sharedLoc;
	@BeforeClass
	public static void setupScript() throws Exception, Throwable {
		
		ExtentReport.reportCreate("ModificationsToCalculationStatusforCIM", "webdriver.scripts.cim",
				"ModificationsToCalculationStatusforCIM");
		try {
			cimMap = BuildMap.getInstance(driver, CimMap.class);
			modelMap = BuildMap.getInstance(driver, ContractingMap.class);
			Login.loginUser("CostAnalyst1");
			goToPage("Cost Integration Manager");
			waitForDisplayedSpinnerToEnd();
			ExtentReport.logPass("PASS", "setupScript");
		} catch (Exception | AssertionError e) {
			ExtentReport.logFail("FAIL", "Failure in setupScript", driver, e);
			fail(e.getMessage());
		}
	}
	
	@Test
	public void test01Validate_CalculationStatusPageFor_Pending_21963() throws Throwable {
		try {
			createCIM(cimScenarioCreate,calcType);
			doFilterCreateCIM(filterCim);
			validateCalcStatus("PENDING", cimScenarioCreate);
			doClick("//div[text()='" + cimScenarioCreate + "']//following::a[contains(@id,'cimStatusLink')]");
			recordsProcessed=driver.findElement(By.xpath("//div[text()='Calculation Status']//following::div[text()='"+cimScenarioCreate+"']//following::div[15]")).getText();
			recordsPending=driver.findElement(By.xpath("//div[text()='Calculation Status']//following::div[text()='"+cimScenarioCreate+"']//following::div[16]")).getText();
			totalRecords=driver.findElement(By.xpath("//div[text()='Calculation Status']//following::div[text()='"+cimScenarioCreate+"']//following::div[17]")).getText();
			assert recordsProcessed.equals("N/A")&&recordsPending.equals("N/A")&&totalRecords.equals("N/A");
			assertElementIsDisplayedWithXpath("//div[contains(@id,'calculationstatus')]//div[text()='"+cimScenarioCreate+"']//following::td[6]/div[text()='Pending']");
			assertElementIsDisplayedWithXpath("//div[contains(@id,'calculationstatus')]//div[text()='"+cimScenarioCreate+"']//following::td[16]//following::a//span[text()='Cancel']");
			assertElementIsNotDisplayed("//div[contains(@id,'calculationstatus')]//div[text()='"+cimScenarioCreate+"']//following::td[15]//following::a//span[text()='Delete']");
			calcStartTime=driver.findElement(By.xpath("//div[contains(@id,'calculationstatus')]//div[text()='"+cimScenarioCreate+"']//following::td[13]/div")).getText();
			duration=driver.findElement(By.xpath("//div[contains(@id,'calculationstatus')]//div[text()='"+cimScenarioCreate+"']//following::td[15]/div")).getText();
			assertTextEquals(duration," ");
			assertTextEquals(calcStartTime," ");
			doClosePageOnLowerBar("Calculation Status");
			deleteCim();
			ExtentReport.logPass("PASS", "test01Validate_CalculationStatus_Pending_21963");
		} catch (Exception | AssertionError e) {
			ExtentReport.logFail("FAIL", "test01Validate_CalculationStatus_Pending_21963", driver, e);
			fail(e.getMessage());
		}
	}
	
	@Test
	public void test02Validate_CalculationStatusPageFor_Completed_21963() throws Throwable {
		try {
			createCIM(cimScenarioCreate,calcType);
			doFilterCreateCIM(filterCim);
			validateCalcStatus("COMPLETED", cimScenarioCreate);
			doClick("//div[text()='" + cimScenarioCreate + "']//following::a[contains(@id,'cimStatusLink')]");
			calcStartTime=driver.findElement(By.xpath("//div[contains(@id,'calculationstatus')]//div[text()='"+cimScenarioCreate+"']//following::td[13]/div")).getText();
			calcEndtime=driver.findElement(By.xpath("//div[contains(@id,'calculationstatus')]//div[text()='"+cimScenarioCreate+"']//following::td[14]/div")).getText();

			recordsProcessed=driver.findElement(By.xpath("//div[text()='Calculation Status']//following::div[text()='"+cimScenarioCreate+"']//following::div[15]")).getText();
			recordsPending=driver.findElement(By.xpath("//div[text()='Calculation Status']//following::div[text()='"+cimScenarioCreate+"']//following::div[16]")).getText();
			totalRecords=driver.findElement(By.xpath("//div[text()='Calculation Status']//following::div[text()='"+cimScenarioCreate+"']//following::div[17]")).getText();
			assert recordsProcessed.equals("0")&&recordsPending.equals("N/A")&&totalRecords.equals("0");
			assertElementIsDisplayedWithXpath("//div[contains(@id,'calculationstatus')]//div[text()='"+cimScenarioCreate+"']//following::td[6]/div[text()='Completed']");
			assertElementIsNotDisplayed("//div[contains(@id,'calculationstatus')]//div[text()='"+cimScenarioCreate+"']//following::td[15]//following::a//span[text()='Delete']");
			calculationStatusPageOpenViewDialog();
			assertElementIsDisplayedWithXpath("//div[text()='View Log']//following::div[contains(@id,'displayfield')]//div[contains(text(),'"+cimScenarioCreate+"\\Costing\\Cost Integration Manager (CIM)\\"+calcStartTime+"')]");
			checkForRecordsProcessed(""+calcEndtime+" Scenario calculated : "+calcType+", total Records: 0, Record Processed 0");
			try {
				checkElements(driver.findElements(By.xpath("//div[text()='View Log']//following::span[contains(@id,'button')]//span[text()='Cancel']")));
			} catch (Throwable e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}			doClick("(//div[text()='"+cimScenarioCreate+"']//following::span[text()='Download'])[1]");
			doClick("//div[contains(@id,'calculationstatus')]//div[text()='"+cimScenarioCreate+"']//following::td[16]/div/a//span[contains(@class,'delBtn ')]");
			doClick(cimMap.getcalcDeletewarningBtn());
			doClosePageOnLowerBar("Calculation Status");
			deleteCim();
			ExtentReport.logPass("PASS", "test02Validate_CalculationStatusPageFor_Completed_21963");
		} catch (Exception | AssertionError e) {
			ExtentReport.logFail("FAIL", "test02Validate_CalculationStatusPageFor_Completed_21963", driver, e);
			fail(e.getMessage());
		}
	}
	
	@Test
	public void test03Validate_CalculationStatusPageFor_Cancelled_21963() throws Throwable {
		try {
			createCIM(cimScenarioCreate,calcType);
			doFilterCreateCIM(filterCim);
			validateCalcStatus("CANCELLED", cimScenarioCreate);
			doClick(cimMap.getcalcRefreshBtn());
			calcStartTime=driver.findElement(By.xpath("//div[contains(@id,'calculationstatus')]//div[text()='"+cimScenarioCreate+"']//following::td[13]/div")).getText();
			recordsProcessed=driver.findElement(By.xpath("//div[text()='Calculation Status']//following::div[text()='"+cimScenarioCreate+"']//following::div[15]")).getText();
			recordsPending=driver.findElement(By.xpath("//div[text()='Calculation Status']//following::div[text()='"+cimScenarioCreate+"']//following::div[16]")).getText();
			totalRecords=driver.findElement(By.xpath("//div[text()='Calculation Status']//following::div[text()='"+cimScenarioCreate+"']//following::div[17]")).getText();
			assert recordsProcessed.equals("0")&&recordsPending.equals("N/A")&&totalRecords.equals("0");
			assertElementIsDisplayedWithXpath("//div[contains(@id,'calculationstatus')]//div[text()='"+cimScenarioCreate+"']//following::td[6]/div[text()='Pending']");
			calcEndtime=driver.findElement(By.xpath("//div[contains(@id,'calculationstatus')]//div[text()='"+cimScenarioCreate+"']//following::td[14]/div")).getText();
			duration=driver.findElement(By.xpath("//div[contains(@id,'calculationstatus')]//div[text()='"+cimScenarioCreate+"']//following::td[15]/div")).getText();
			assertTextEquals(duration," ");
			assertTextEquals(calcEndtime," ");
			calculationStatusPageOpenViewDialog();
			assertElementIsDisplayedWithXpath("//div[text()='View Log']//following::div[contains(@id,'displayfield')]//div[contains(text(),'"+cimScenarioCreate+"\\Costing\\Cost Integration Manager (CIM)\\"+calcStartTime+"')]");
			checkForRecordsProcessed("There is no data available to display");
			try {
				checkElements(driver.findElements(By.xpath("//div[text()='View Log']//following::span[contains(@id,'button')]//span[text()='Cancel']")));
			} catch (Throwable e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			for(int i=0;i<=10;i++) {
				doClick(cimMap.getcalcRefreshBtn());//takes time to enable Download button 
			}
			
			doClick("(//div[text()='"+cimScenarioCreate+"']//following::span[text()='Download'])[1]");
			doClick("//div[contains(@id,'calculationstatus')]//div[text()='"+cimScenarioCreate+"']//following::td[16]/div/a//span[contains(@class,'delBtn ')]");
			doClick(cimMap.getcalcDeletewarningBtn());
			doClosePageOnLowerBar("Calculation Status");
			deleteCim();
			ExtentReport.logPass("PASS", "test03Validate_CalculationStatusPageFor_Cancelled_21963");
		} catch (Exception | AssertionError e) {
			ExtentReport.logFail("FAIL", "test03Validate_CalculationStatusPageFor_Cancelled_21963", driver, e);
			fail(e.getMessage());
		}
	}
	
	@Test
	public void test04Validate_CalculationStatusPageFor_Failed_21963() throws Throwable {
		try {
			createCIM(cimScenarioCreate,calcType);
			doFilterCreateCIM(filterCim);
			validateCalcStatus("FAILED", cimScenarioCreate);
			doClick("//div[text()='" + cimScenarioCreate + "']//following::a[contains(@id,'cimStatusLink')]");
			calcStartTime=driver.findElement(By.xpath("//div[contains(@id,'calculationstatus')]//div[text()='"+cimScenarioCreate+"']//following::td[13]/div")).getText();
			calcEndtime=driver.findElement(By.xpath("//div[contains(@id,'calculationstatus')]//div[text()='"+cimScenarioCreate+"']//following::td[14]/div")).getText();
			duration=driver.findElement(By.xpath("//div[contains(@id,'calculationstatus')]//div[text()='"+cimScenarioCreate+"']//following::td[15]/div")).getText();
			assertTextEquals(duration," ");
			recordsProcessed=driver.findElement(By.xpath("//div[text()='Calculation Status']//following::div[text()='"+cimScenarioCreate+"']//following::div[15]")).getText();
			recordsPending=driver.findElement(By.xpath("//div[text()='Calculation Status']//following::div[text()='"+cimScenarioCreate+"']//following::div[16]")).getText();
			totalRecords=driver.findElement(By.xpath("//div[text()='Calculation Status']//following::div[text()='"+cimScenarioCreate+"']//following::div[17]")).getText();
			assert recordsProcessed.equals("0")&&recordsPending.equals("N/A")&&totalRecords.equals("0");
			assertElementIsDisplayedWithXpath("//div[contains(@id,'calculationstatus')]//div[text()='"+cimScenarioCreate+"']//following::td[6]/div[text()='Completed']");
			assertElementIsNotDisplayed("//div[contains(@id,'calculationstatus')]//div[text()='"+cimScenarioCreate+"']//following::td[15]//following::a//span[text()='Delete']");
			calcStartTime=driver.findElement(By.xpath("//div[contains(@id,'calculationstatus')]//div[text()='"+cimScenarioCreate+"']//following::td[13]/div")).getText();
			assertTextEquals(calcEndtime," ");
			calculationStatusPageOpenViewDialog();
			assertElementIsDisplayedWithXpath("//div[text()='View Log']//following::div[contains(@id,'displayfield')]//div[contains(text(),'"+cimScenarioCreate+"\\Costing\\Cost Integration Manager (CIM)\\"+calcStartTime+"')]");
			checkForRecordsProcessed(""+calcEndtime+"CIM BATCh Name: "+cimScenarioCreate+", Calculating Scenario: Encounter Cost Calc Scenario: *Practitioner Costing Enc Failed, error: java.lang.RuntimeException:");
			try {
				checkElements(driver.findElements(By.xpath("//div[text()='View Log']//following::span[contains(@id,'button')]//span[text()='Cancel']")));
			} catch (Throwable e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}			doClick("(//div[text()='"+cimScenarioCreate+"']//following::span[text()='Download'])[1]");
			doClick("//div[contains(@id,'calculationstatus')]//div[text()='"+cimScenarioCreate+"']//following::td[16]/div/a//span[contains(@class,'delBtn ')]");
			doClick(cimMap.getcalcDeletewarningBtn());
			doClosePageOnLowerBar("Calculation Status");
			deleteCim();
			ExtentReport.logPass("PASS", "test04Validate_CalculationStatusPageFor_Failed_21963");
		} catch (Exception | AssertionError e) {
			ExtentReport.logFail("FAIL", "test04Validate_CalculationStatusPageFor_Failed_21963", driver, e);
			fail(e.getMessage());
		}
	}

	@Test
	public void test05Validate_CalculationStatusPageFor_Running_21963() throws Throwable {
		try {
			createCIM(cimScenarioCreate,calcType);
			doFilterCreateCIM(filterCim);
			validateCalcStatus("RUNNING", cimScenarioCreate);
			doClick("//div[text()='" + cimScenarioCreate + "']//following::a[contains(@id,'cimStatusLink')]");
			calcStartTime=driver.findElement(By.xpath("//div[contains(@id,'calculationstatus')]//div[text()='"+cimScenarioCreate+"']//following::td[13]/div")).getText();
			recordsProcessed=driver.findElement(By.xpath("//div[text()='Calculation Status']//following::div[text()='"+cimScenarioCreate+"']//following::div[15]")).getText();
			recordsPending=driver.findElement(By.xpath("//div[text()='Calculation Status']//following::div[text()='"+cimScenarioCreate+"']//following::div[16]")).getText();
			totalRecords=driver.findElement(By.xpath("//div[text()='Calculation Status']//following::div[text()='"+cimScenarioCreate+"']//following::div[17]")).getText();
			assert recordsProcessed.equals("0")&&recordsPending.equals("N/A")&&totalRecords.equals("0");
			assertElementIsDisplayedWithXpath("//div[contains(@id,'calculationstatus')]//div[text()='"+cimScenarioCreate+"']//following::td[6]/div[text()='Pending']");
			assertElementIsNotDisplayed("(//div[contains(@id,'calculationstatus')]//div[text()='"+cimScenarioCreate+"']//following::td[16]//following::a//span[text()='Cancel'])[3]");
			assertElementIsNotDisplayed("//div[contains(@id,'calculationstatus')]//div[text()='"+cimScenarioCreate+"']//following::td[15]//following::a//span[text()='Delete']");
			calcEndtime=driver.findElement(By.xpath("//div[contains(@id,'calculationstatus')]//div[text()='"+cimScenarioCreate+"']//following::td[14]/div")).getText();
			duration=driver.findElement(By.xpath("//div[contains(@id,'calculationstatus')]//div[text()='"+cimScenarioCreate+"']//following::td[15]/div")).getText();
			assertTextEquals(duration," ");
			assertTextEquals(calcEndtime," ");
			calculationStatusPageOpenViewDialog();
			assertElementIsDisplayedWithXpath("//div[text()='View Log']//following::div[contains(@id,'displayfield')]//div[contains(text(),'"+cimScenarioCreate+"\\Costing\\Cost Integration Manager (CIM)\\"+calcStartTime+"')]");
			checkForRecordsProcessed("There is no data available to display");
			try {
				checkElements(driver.findElements(By.xpath("//div[text()='View Log']//following::span[contains(@id,'button')]//span[text()='Cancel']")));
			} catch (Throwable e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			assertElementIsNotDisplayed("//div[contains(@id,'calculationstatus')]//div[text()='"+cimScenarioCreate+"']//following::td[9]//a");
			doClosePageOnLowerBar("Calculation Status");
			deleteCim();
			ExtentReport.logPass("PASS", "test05Validate_CalculationStatusPageFor_Running_21963");
		} catch (Exception | AssertionError e) {
			ExtentReport.logFail("FAIL", "test05Validate_CalculationStatusPageFor_Running_21963", driver, e);
			fail(e.getMessage());
		}
	}
		
	@Test
	public void test06Search_CalculationStatusPage_21963() throws Throwable {
		try {
			createCIM(cimScenarioCreate,calcType);
			doFilterCreateCIM(filterCim);
			validateCalcStatus("RUNNING", cimScenarioCreate);
			doClick(cimMap.getcalcStatusPgeLink());
			doClick(cimMap.getcalcStatusSearch());
			cimMap.getcalcStatusSearch().sendKeys(cimScenarioCreate);
			doClick(cimMap.getcalcStatusSearchIcon());
			assertElementIsDisplayedWithXpath("//div[contains(@id,'calculationstatus')]//div[text()='"+cimScenarioCreate+"']");
			assertElementIsDisplayedWithXpath("//div[contains(@id,'calculationstatus')]//div[text()='"+cimScenarioCreate+"']//following::td/div[text()='Costing']");
			assertElementIsDisplayedWithXpath("//div[contains(@id,'calculationstatus')]//div[text()='"+cimScenarioCreate+"']//following::td/div[text()='Cost Integration Manager (CIM)']");
			doClosePageOnLowerBar("Calculation Status");
			deleteCim();
			ExtentReport.logPass("PASS", "test05Validate_CalculationStatusPageFor_Running_21963");
		} catch (Exception | AssertionError e) {
			ExtentReport.logFail("FAIL", "test05Validate_CalculationStatusPageFor_Running_21963", driver, e);
			fail(e.getMessage());
		}
	}

	@Test
	public void test07Filter_CalculationStatusPage_21963() throws Throwable {
		try {
			createCIM(cimScenarioCreate,calcType);
			doFilterCreateCIM(filterCim);
			validateCalcStatus("RUNNING", cimScenarioCreate);
			doClick(cimMap.getcalcStatusPgeLink());
			String[] filterCalc= {"Scenario Name","Is","Equal To",cimScenarioCreate};
			doFilterCalculationPage(filterCalc);
			assertElementIsDisplayedWithXpath("//div[contains(@id,'calculationstatus')]//div[text()='"+cimScenarioCreate+"']");
			doClosePageOnLowerBar("Calculation Status");
			deleteCim();
			ExtentReport.logPass("PASS", "test05Validate_CalculationStatusPageFor_Running_21963");
		} catch (Exception | AssertionError e) {
			ExtentReport.logFail("FAIL", "test05Validate_CalculationStatusPageFor_Running_21963", driver, e);
			fail(e.getMessage());
		}
	}

	@Test
	public void test08Validate_Duration_21963() throws Throwable {
		try {
			createCIM(cimScenarioCreate,calcType);
			doFilterCreateCIM(filterCim);
			validateCalcStatus("COMPLETED", cimScenarioCreate);
			doClick("//div[text()='" + cimScenarioCreate + "']//following::a[contains(@id,'cimStatusLink')]");
			calcStartTime=driver.findElement(By.xpath("//div[contains(@id,'calculationstatus')]//div[text()='"+cimScenarioCreate+"']//following::td[13]/div")).getText();
			calcEndtime=driver.findElement(By.xpath("//div[contains(@id,'calculationstatus')]//div[text()='"+cimScenarioCreate+"']//following::td[14]/div")).getText();
			duration=differenceTime(calcStartTime, calcEndtime);
			assertTextEquals(driver.findElement(By.xpath("//div[contains(@id,'calculationstatus')]//div[text()='"+cimScenarioCreate+"']//following::td[15]/div")).getText(),duration);
			doClosePageOnLowerBar("Calculation Status");
			deleteCim();
			ExtentReport.logPass("PASS", "test08Validate_CalcStartTime_21963");
		} catch (Exception | AssertionError e) {
			ExtentReport.logFail("FAIL", "test08Validate_CalcStartTime_21963", driver, e);
			fail(e.getMessage());
		}
	}
	
	@Test
	public void test09Validate_SharedLoc_21963() throws Throwable {
		try {
			createCIM(cimScenarioCreate,calcType);
			doFilterCreateCIM(filterCim);
			validateCalcStatus("COMPLETED", cimScenarioCreate);
			doClick("//div[text()='" + cimScenarioCreate + "']//following::a[contains(@id,'cimStatusLink')]");
			assertElementIsDisplayedWithXpath("//div[contains(@id,'calculationstatus')]//div[text()='"+cimScenarioCreate+"']//following::td/div//*[contains(text(),'/PATH/TO/CALC_LOGS_SHARED_DIRECTORY1/')]");//directly validating shared loc as this is used during cim group create 
			doClosePageOnLowerBar("Calculation Status");
			deleteCim();
			ExtentReport.logPass("PASS", "test09Validate_SharedLoc_21963");
		} catch (Exception | AssertionError e) {
			ExtentReport.logFail("FAIL", "test09Validate_SharedLoc_21963", driver, e);
			fail(e.getMessage());
		}
	}
	@AfterClass
	public static void endtest() throws Exception {

		ExtentReport.report.flush();

	}

}

