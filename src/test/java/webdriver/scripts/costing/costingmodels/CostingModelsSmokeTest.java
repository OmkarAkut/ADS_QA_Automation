package webdriver.scripts.costing.costingmodels;

import org.junit.BeforeClass;
import org.junit.FixMethodOrder;
import org.junit.Test;
import org.junit.runners.MethodSorters;
import webdriver.core.Login;
import webdriver.corehelpers.GoHelper;
import webdriver.maps.CostingMap;
import webdriver.maps.DialogsMap;
import webdriver.maps.GeneralElementsMap;
import webdriver.maps.mapbuilder.BuildMap;

@FixMethodOrder(MethodSorters.NAME_ASCENDING)
public class CostingModelsSmokeTest extends GoHelper {
  String costModel = "QA Marina";
  String[] filter = {"Cost Model Name", "Is", "Equal To", costModel};
  static GeneralElementsMap generalMap;
  static CostingMap costing;
  DialogsMap dialog = BuildMap.getInstance(driver, DialogsMap.class);
  final static String newCellValue = Integer.toString(javaGetRandomNumber(99,true));

  /** Automates test ticket ADS-1492. */
  @BeforeClass
  public static void setupScript() throws Exception {
    generalMap = BuildMap.getInstance(driver, GeneralElementsMap.class);
    costing = BuildMap.getInstance(driver, CostingMap.class);
    System.out.println("Test Class: " + CostingModelsSmokeTest.class.getSimpleName());
    Login.loginUser("CostingDepartmentManager1");
    goToPage("Costing Models");
  }

  @Test
  public void test01AssertCostModelPageHeader()
          throws InterruptedException {
    waitForAjaxExtJs();
    assertPageInformation("Costing Model Library"); 
  }

  @Test
  public void test02AssertCostModelNewDialog()
          throws InterruptedException {
	/* Modified by Omkar on 24/5/2022 as 2 elements are found with same xpath the below method will not work
    doClickButton("New");
    */
	 doClick("(//button/span[text()='New'])[2]");
	 
	 //End of modification
    assertElementIsDisplayedWithXpath("//span[contains(@id, 'header') and text()='New Cost Model']");
    doClickButton("Cancel & Close");
  }

  @Test
  public void test03AssertCostModelOpenTaskList()
          throws InterruptedException {
	driverWait();
    doClickButton("Open Task List");
    /*Modified by Omkar on 23-5-22 : Change in xpath
    //assertElementIsDisplayedWithXpath("//table/tbody/tr[contains(@class, 'tree-node')]/descendant::*[text()='Cost Model Task List']");
     assertElementIsDisplayedWithXpath(
            "//table/tbody/tr[contains(@class, 'tree-node')]" +
            "/following-sibling::tr/descendant::*[text()='Prepare Costing Elements']"
   
    */
    waitForAjaxExtJs();
    assertElementIsDisplayedWithXpath("//*[contains(@class ,'x-grid-row undefined x-grid-tree-node-expanded x-grid-row-selected x-grid-row-focused')]");
    assertElementIsDisplayedWithXpath("//*[contains(text(),'Prepare Costing Elements')]");
   // End of modification
  }


}
