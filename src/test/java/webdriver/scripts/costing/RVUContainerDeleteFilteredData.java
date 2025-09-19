package webdriver.scripts.costing;

import static org.junit.Assert.assertTrue;
import static org.junit.Assert.fail;
import java.text.SimpleDateFormat;
import java.util.Arrays;
import java.util.List;
import org.junit.AfterClass;
import org.junit.BeforeClass;
import org.junit.FixMethodOrder;
import org.junit.Test;
import org.junit.runners.MethodSorters;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;

import ExtentReport.ExtentReport;
import webdriver.core.Login;
import webdriver.corehelpers.GoHelper;
import webdriver.helpers.ContractModelsHelper;
import webdriver.helpers.UcqcHelper;
import webdriver.maps.ContractingMap;
import webdriver.maps.CostingMap;
import webdriver.maps.DialogsMap;
import webdriver.maps.ModelLibraryMap;
import webdriver.maps.mapbuilder.BuildMap;
@FixMethodOrder(MethodSorters.NAME_ASCENDING)
public class RVUContainerDeleteFilteredData extends GoHelper {
	static CostingMap costing;
	static ModelLibraryMap modelMap;
	static ContractingMap contractMap;
	static DialogsMap dialog;
	static String TotalPages="/ 16";
	static String costModel="Marina";
	static String costingFolder="Marina Health";
	static String currentDateTime = new SimpleDateFormat("yyyy.MM.dd.HH.mm.ss").format(new java.util.Date());
	static String costModelName = "Model " + currentDateTime;
	static String[] filterCostModel= {"Name","Is","Equal To",costModel};
	static String[] filterRvu= {"Cost Model Name","Is","Equal To",costModelName};
	static String[] filterRvuByStartMonth= {"Start Month","Is","Equal To","04/01/2019"};
	static String[] filterByCostComponentName= {"Cost Component Name","Is","Contains","Depreciation"};
	static String[] filterByCostDepartmentCode= {"Department Code","Is","Contains","RAD"};
	static String[] filterByCostComponentIsOverhead= {"Cost Component Is Overhead","Is","Equal To","Yes"};
	static String entityCode="530";
	static String endMonth= "04/01/2019";
	static List<String> list =Arrays.asList("150", "200", "300", "350", "600", "800");
	ContractModelsHelper modelHelper=new ContractModelsHelper();
	static int RvuContainerList;
/** Automates test ticket ADS-5983, ADS-12597*/
	
	@BeforeClass
	public static void setupScript() throws Exception,Throwable {
		ExtentReport.reportCreate("RVUContainerDeleteFilteredData", "webdriver.scripts.costing", "RVUContainerDeleteFilteredData");
		try {
			costing = BuildMap.getInstance(driver, CostingMap.class);
			modelMap=BuildMap.getInstance(driver, ModelLibraryMap.class);
			contractMap=BuildMap.getInstance(driver, ContractingMap.class);
			dialog=BuildMap.getInstance(driver, DialogsMap.class);
			Login.loginUser("AutomationTesterAdmin");
			waitForDisplayedSpinnerToEnd();
			goToPage("Costing Models");
			doClickTreeData("Costing");
			waitForMainPageTitle("Marina Health");
			doClickTreeData(costingFolder);
			doClick("//span[text()='Marina Health']");
			waitForDisplayedSpinnerToEnd();
			ExtentReport.logPass("PASS", "setupScript");
		} catch (Exception|AssertionError e) {
			ExtentReport.logFail("FAIL", "Failure in setupScript", driver, e);
			fail(e.getMessage());
		}
	}
	//ADS-5983 all steps
	@Test
	public void test01OpenGeneralInformationCost_5983() throws Throwable {
		try {
			doClick(modelMap.getModelLibraryButtonFilter());
			doFilterCreate(filterCostModel);
			tableDoubleClickCellFirstColumn(costModel);
			doClickTreeItem("Assign Unit Costs");
			
			waitForMainPageTitle("General Information - Cost");
			doClickTreeItem("General Information - Cost");
			waitForElementToBeVisible(costing.getCostModelGeneralInfo());
			assertElementText(costing.getCostModelGeneralInfo(),"Cost Model General Information", printout);
			ExtentReport.logPass("PASS", "test01OpenGeneralInformationCost");
		} catch (Exception|AssertionError e) {
			ExtentReport.logFail("FAIL", "test01OpenGeneralInformationCost", driver, e);
			fail(e.getMessage());
		}
	}
	
	@Test
	public void test02CancelandCloseGeneralInformationCostModel_5983() throws Throwable {
		try {
			doClick(ContractingMap.getFeeForPaymentCancelClose());
doClickTreeItem("Assign Unit Costs");
			
			waitForMainPageTitle("General Information - Cost");
			doClickTreeItem("General Information - Cost");
			waitForElementToBeVisible(costing.getCostModelGeneralInfo());
			ExtentReport.logPass("PASS", "test02CancelandCloseGeneralInformationCostModel");
		} catch (Exception|AssertionError e) {
			ExtentReport.logFail("FAIL", "test02CancelandCloseGeneralInformationCostModel", driver, e);
			fail(e.getMessage());
		}
	}
	
	@Test
	public void  test03SaveAsGeneralInformationCostModel_5983() throws Throwable {
		try {
			doClick(costing.getSaveAsButton());
			waitForElementToBeVisible(costing.getSaveAsPopup());
			ContractModelsHelper.keyInValues(ContractingMap.getInputName(), costModelName);
			doClick("//div[contains(text(),'Save As')]//following::span[text()='Save & Close']");
			waitForDisplayedSpinnerToEnd();
			ExtentReport.logPass("PASS", "test03SaveAsGeneralInformationCostModel");
		} catch (Exception|AssertionError e) {
			ExtentReport.logFail("FAIL", "test03SaveAsGeneralInformationCostModel", driver, e);
			fail(e.getMessage());
		}
		finally {
			doClosePageOnLowerBar(costModel);
		}
	}
	@Test
	public void test04OpenRVUMaintenanceForTheSavedCostModel_5983() throws Throwable {
		try {
			goToPage("Rvu Maintenance");
			doClick(costing.getRvuMaintenanceButtonFilter());
			doFilterCreate(filterRvu);
			tableDoubleClickCellFirstColumn(costModelName);
			doClick(costing.getRvuMaintenanceButtonRvuContainerList());
			
			ExtentReport.logPass("PASS", "test03SaveAsGeneralInformationCostModel");
		} catch (Exception|AssertionError e) {
			ExtentReport.logFail("FAIL", "test03SaveAsGeneralInformationCostModel", driver, e);
			fail(e.getMessage());
		}
	}
	@Test
	public void test05FilterByStartMonth_5983() throws Throwable {
		try {
			waitForElementPresence("//*[text()='RVU Container List']/ancestor::div/following-sibling::div//span[text()='Filter']");
			doClick("//*[text()='RVU Container List']/ancestor::div/following-sibling::div//span[text()='Filter']");
			doFilterSetFilterParametersForDate("Start Month","Is","Equal To","04/01/2019");
			addFilter();
//			assertElementIsDisplayedWithXpath("//label[text()='Filter to Match These Criteria 3/13480']");
			//Shilpa xpath update on 2.5.2024
			assertElementIsDisplayedWithXpath("//label[contains(text(),'Filter to Match These Criteria')and('/13480')]");
			doClick(dialog.getFilterDialogButtonApplyFilter());
			waitForSpinnerToEnd();
			ExtentReport.logPass("PASS", "test03SaveAsGeneralInformationCostModel");
		} catch (Exception|AssertionError e) {
			ExtentReport.logFail("FAIL", "test03SaveAsGeneralInformationCostModel", driver, e);
			fail(e.getMessage());
		}
	}
	@Test
	public void test06DeleteRowInRvuContainer_5983() throws Throwable {
		try {
			RvuContainerList=ContractModelsHelper.getPopUpElementListInGrid(costing.getRvuContainerList());
			doClick(costing.getRvuContainerDeleteButton());
			waitForElementToBeVisible(contractMap.getContractModelDeletePopUp());
			doClick(ContractingMap.getDeleteButtonMesaageBox());
			waitForDisplayedSpinnerToEnd();
			if(!(ContractModelsHelper.getPopUpElementListInGrid(costing.getRvuContainerList())==RvuContainerList)) {
				assertTrue(printout);
			}
			ExtentReport.logPass("PASS", "test06DeleteRowInRvuContainer");
		} catch (Exception|AssertionError e) {
			ExtentReport.logFail("FAIL", "test06DeleteRowInRvuContainer", driver, e);
			fail(e.getMessage());
		}
	}
	@Test
	public void test07DeleteFilteredInRvuContainer_5983() throws Throwable {
		try {
			doClick(costing.getRvuContainerDeleteFilteredButton());
			waitForElementToBeVisible(contractMap.getContractModelDeletePopUp());
			doClick(ContractingMap.getDeleteButtonMesaageBox());
			waitForDisplayedSpinnerToEnd();
			waitForAjaxExtJs();
			driverDelay(35000);
			assertTextIsDisplayed("There is no data available to display.");
			ExtentReport.logPass("PASS", "test07DeleteFilteredInRvuContainer");
		} catch (Exception|AssertionError e) {
			ExtentReport.logFail("FAIL", "test07DeleteFilteredInRvuContainer", driver, e);
			fail(e.getMessage());
		}
		
	}
	@Test
	public void test08ClearFilterInRvuContainer_5983() throws Throwable {
		try {
//			waitUntilElementIsClickable(costing.getRvuContainerClearFilterButton());
			doClick(costing.getRvuContainerClearFilterButton());
			waitForDisplayedSpinnerToEnd();
			driverDelay(20000);
//			assertListElementsAreDisplayed(costing.getRvuContainerList(), printout);
			assertHasElements(costing.getRvuContainerList());
			ExtentReport.logPass("PASS", "test08ClearFilterInRvuContainer");
		} catch (Exception|AssertionError e) {
			ExtentReport.logFail("FAIL", "test08ClearFilterInRvuContainer", driver, e);
			fail(e.getMessage());
		}
	}

	@Test
	public void test09ValidateFilterByStartMonthAfterDeleteFiltered_5983() throws Throwable {
		try {
			doClick("//*[text()='RVU Container List']/ancestor::div/following-sibling::div//span[text()='Filter']");
			doFilterSetFilterParametersForDate("Start Month","Is","Equal To","04/01/2019");
			addFilter();
//			assertElementIsDisplayedWithXpath("//label[text()='Filter to Match These Criteria 0/13475']");
//			assertElementIsDisplayedWithXpath("//label[contains(text(),'Filter to Match These Criteria 0/')]");
			assertElementIsDisplayedWithXpath("//label[contains(text(),'Filter to Match These Criteria')]");
//			doClick(dialog.getFilterDialogButtonApplyFilter());
//			waitForSpinnerToEnd();
			ExtentReport.logPass("PASS", "test09ValidateFilterByStartMonthAfterDeleteFiltered");
		} catch (Exception|AssertionError e) {
			ExtentReport.logFail("FAIL", "test09ValidateFilterByStartMonthAfterDeleteFiltered", driver, e);
			fail(e.getMessage());
		}
	}
	@Test
	public void test10ValidateOpenInRvuFilterPopUp_5983() throws Throwable {
		try {
			doClick(contractMap.getContractModelEditFilterButton());
			doFilterSetFilterParametersForDate("End Month","Is","Equal To",endMonth);
			doClick(costing.getRvuContainerOpenCheckbox());
//			assertElementIsDisabled(costing.getRvuContainerValueField(), printout);
			doClick(contractMap.getContractModelUpdateFilterButton());
//			assertElementIsDisplayedWithXpath("//label[text()='Filter to Match These Criteria 1596/13477']");
//			assertElementIsDisplayedWithXpath("//label[contains(text(),'Filter to Match These Criteria 1596/')]");
			//Shilpa:update for 11.2 on 10.23.2024
//			assertElementIsDisplayedWithXpath("//label[contains(text(),'Filter to Match These Criteria 986/8736')]");
			assertElementIsDisplayedWithXpath("//label[contains(text(),'Filter to Match These Criteria')]");
			doClick(dialog.getFilterDialogButtonApplyFilter());
			waitForDisplayedSpinnerToEnd();
			driverDelay(35000);
			assertListElementsAreDisplayed(costing.getRvuContainerList(), printout);
//			assertElementIsDisplayedWithXpath("//div[contains(@id,'tbtext')][text()='"+TotalPages+"']");//Shilpa update on 23.5.2024 cannot be validated since page count keep changing
			ExtentReport.logPass("PASS", "test10ValidateOpenInRvuFilterPopUp");
		} catch (Exception|AssertionError e) {
			ExtentReport.logFail("FAIL", "test10ValidateOpenInRvuFilterPopUp", driver, e);
			fail(e.getMessage());
		}
	}
	@Test
	public void test11DeleteFilteredForOPEN_5983() throws Throwable {
		try {
			doClick(costing.getRvuContainerDeleteFilteredButton());
			waitForElementToBeVisible(contractMap.getContractModelDeletePopUp());
			doClick(ContractingMap.getDeleteButtonMesaageBox());
			waitForDisplayedSpinnerToEnd();
			driverDelay(15000);
			for(WebElement element:costing.getRvuContainerListEndMonth()) {
				if(element.getText().equals("Open")) {
					assertTrue(printout);
				}
			}
//			driverDelay(4000);
//			test08ClearFilterInRvuContainer_5983();
			doClick(costing.getRvuContainerClearFilterButton());
			waitForDisplayedSpinnerToEnd();
			driverDelay();
			ExtentReport.logPass("PASS", "test11DeleteFilteredForOPEN");
		} catch (Exception|AssertionError e) {
			ExtentReport.logFail("FAIL", "test11DeleteFilteredForOPEN", driver, e);
			fail(e.getMessage());
		}
		
//		/finally {
			/*
			Actions act=new Actions(driver);
			waitUntilElementIsClickable(costing.getRvuContainerClearFilterButton());
			act.moveToElement(costing.getRvuContainerClearFilterButton()).click().build().perform();
//			ContractModelsHelper.doactionClick(costing.getRvuContainerClearFilterButton());
			driverDelay();
			*/
			//Remove below lines once ADS-17843 and ADS-18038    are fixed

//			doClick("//*[text()='RVU Container List']/ancestor::div/following-sibling::div//span[text()='Filter']");
//			driverDelay();
//			doClick("//span[text()='Remove All']");
//			doClick(dialog.getFilterDialogButtonApplyFilter());
//			waitForDisplayedSpinnerToEnd();
//			driverDelay(15000);
//		}
		
	}
	@Test
	public void test12FilterByEntityCodeInRvuContainer_5983() throws Throwable {
		try {
			
			assertHasElements(costing.getRvuContainerList());
//			assertListElementsAreDisplayed(costing.getRvuContainerList(), printout);
			doClick("//*[text()='RVU Container List']/ancestor::div/following-sibling::div//span[text()='Filter']");
			doFilterSetFilterParameters("Entity Code", "Is", "Equal To", entityCode);
			addFilter();
//			assertElementIsDisplayedWithXpath("//label[contains(text(),'Filter to Match These Criteria /13477')]");
			//Shilpa:xpath update for 11.2 on 25.4.2024
//			assertElementIsDisplayedWithXpath("//label[contains(text(),'Filter to Match These Criteria')and('/13477')]");
//			assertElementIsDisplayedWithXpath("//label[contains(text(),'Filter to Match These Criteria')and('/7750')]");//Shilpa update for 11.2 on 10.24.2024
			assertElementIsDisplayedWithXpath("//label[contains(text(),'Filter to Match These Criteria')]");
			doClick(dialog.getFilterDialogButtonApplyFilter());
			waitForSpinnerToEnd();
			driverDelay();
			doClick(ContractingMap.getCloseBtn());
			/*
			assertListElementsAreDisplayed(costing.getRvuContainerList(), printout);
			test07DeleteFilteredInRvuContainer_5983();
			assertElementIsDisabled(ContractingMap.getCloseandDisplayButton(), printout);
			doClick(ContractingMap.getCloseBtn());
			*/
			ExtentReport.logPass("PASS", "test12FilterByEntityCodeInRvuContainer");
		} catch (Exception|AssertionError e) {
			ExtentReport.logFail("FAIL", "test12FilterByEntityCodeInRvuContainer", driver, e);
			fail(e.getMessage());
		}
		
	}
	@Test
	public void test13ApplyRvuMaintenanceCriteria_5983() throws Throwable {
		try {
			doDropdownSelectUsingOptionText(
					costing.getRvuMaintenanceDropdownEntity(),
					costing.getRvuMaintenanceDropdownEntityOptions(),
					"150 Marina Medical Center"
					);
			UcqcHelper.updateDepartment("5310");
				doDropdownSelectUsingOptionText(
					costing.getRvuMaintenanceDropdownEffectiveMonthStartMonthDropdown(),
					costing.getRvuMaintenanceDropdownEffectiveMonthStartMonthOptions(),
					"Apr"
					);
			doDropdownSelectUsingOptionText(
					costing.getRvuMaintenanceDropdownEffectiveMonthStartYearDropdown(),
					costing.getRvuMaintenanceDropdownEffectiveMonthStartYearOptions(),
					"2020"
					);
			doClick(costing.getRvuMaintenanceButtonApplySelections());
			waitForDisplayedSpinnerToEnd();
			driverDelay();
			ExtentReport.logPass("PASS", "test13ApplyRvuMaintenanceCriteria");
		} catch (Exception|AssertionError e) {
			ExtentReport.logFail("FAIL", "test13ApplyRvuMaintenanceCriteria", driver, e);
			fail(e.getMessage());
		}
	}
	@Test
	public void test14ApplyDepartmentCodeFilterInRvuContainerList_5983() throws Throwable {
		try {
			doClick(costing.getRvuMaintenanceButtonRvuContainerList());
			waitForDisplayedSpinnerToEnd();
			doClick("//*[text()='RVU Container List']/ancestor::div/following-sibling::div//span[text()='Filter']");

			doFilterSetFilterParameters("Department Code", "Is", "Equal To", "3710");
			addFilter();
//			assertElementIsDisplayedWithXpath("//label[text()='Filter to Match These Criteria 225/11879']");
			//Shilpa updated for 11.2 , as the count keeps changing, not suggested to assert count
			assertElementIsDisplayedWithXpath("//label[contains(text(),'Filter to Match These Criteria')]");
			doClick(dialog.getFilterDialogButtonApplyFilter());
			waitForSpinnerToEnd();
			assertListElementsAreDisplayed(costing.getRvuContainerList(), printout);
//			assertElementIsDisplayedWithXpath("//div[contains(@id,'tbtext')][text()='/ 3']");
			//Shilpa updated for 11.2 , the page count might increase so not suggested to use the index
			assertElementIsDisplayedWithXpath("(//div[text()='RVU Container List']//following::div[contains(@class,'x-toolbar-text-default')])[2]");
			test07DeleteFilteredInRvuContainer_5983();
			assertElementIsDisabled(ContractingMap.getCloseandDisplayButton(), printout);
			test08ClearFilterInRvuContainer_5983();
			ExtentReport.logPass("PASS", "test14ApplyDepartmentCodeFilterInRvuContainerList");
		} catch (Exception|AssertionError e) {
			ExtentReport.logFail("FAIL", "test14ApplyDepartmentCodeFilterInRvuContainerList", driver, e);
			fail(e.getMessage());
		}
		
	}
	@Test
	public void test15ApplyRADDepartmentCodeFilterInRvuContainerList_5983() throws Throwable {
		try {
			doClick("//*[text()='RVU Container List']/ancestor::div/following-sibling::div//span[text()='Filter']");
			modelHelper.doFilterCreateAndAddFilter(filterByCostDepartmentCode,dialog.getFilterDialogFormFieldValue());
			addFilter();
//			assertElementIsDisplayedWithXpath("//label[text()='Filter to Match These Criteria 4/11654']");
			//Shilpa updated for 11.2 , as the count keeps changing, not suggested to assert count
			assertElementIsDisplayedWithXpath("//label[contains(text(),'Filter to Match These Criteria')]");

			doClick(dialog.getFilterDialogButtonApplyFilter());
			waitForSpinnerToEnd();
			driverDelay(25000);
			test07DeleteFilteredInRvuContainer_5983();
			assertElementIsDisabled(ContractingMap.getCloseandDisplayButton(), printout);
			test08ClearFilterInRvuContainer_5983();
			ExtentReport.logPass("PASS", "test15ApplyRADDepartmentCodeFilterInRvuContainerList");
		} catch (Exception|AssertionError e) {
			ExtentReport.logFail("FAIL", "test15ApplyRADDepartmentCodeFilterInRvuContainerList", driver, e);
			fail(e.getMessage());
		}
	
	}
	@Test
	public void test16ApplyCostComponentNameInRvuContainerList_5983() throws Throwable {
		try {
			doClick("//*[text()='RVU Container List']/ancestor::div/following-sibling::div//span[text()='Filter']");
			modelHelper.doFilterCreateAndAddFilter(filterByCostComponentName,dialog.getFilterDialogFormFieldValue());
			addFilter();
//			assertElementIsDisplayedWithXpath("//label[text()='Filter to Match These Criteria 1560/11650']");
			//Shilpa updated for 11.2 , as the count keeps changing, not suggested to assert count
			assertElementIsDisplayedWithXpath("//label[contains(text(),'Filter to Match These Criteria')]");

			doClick(dialog.getFilterDialogButtonApplyFilter());
			waitForDisplayedSpinnerToEnd();
			driverDelay(40000);
//			assertElementIsDisplayedWithXpath("//div[contains(@id,'tbtext')][text()='/ 16']");
			//Shilpa updated for 11.2 , the page count might increase so not suggested to use the index
			assertElementIsDisplayedWithXpath("(//div[text()='RVU Container List']//following::div[contains(@class,'x-toolbar-text-default')])[2]");
			test07DeleteFilteredInRvuContainer_5983();
			assertElementIsDisabled(ContractingMap.getCloseandDisplayButton(), printout);
			test08ClearFilterInRvuContainer_5983();
//			ContractingMap.getCloseBtn().click();
			ExtentReport.logPass("PASS", "test16ApplyCostComponentNameInRvuContainerList");
		} catch (Exception|AssertionError e) {
			ExtentReport.logFail("FAIL", "test16ApplyCostComponentNameInRvuContainerList", driver, e);
			fail(e.getMessage());
		}
		/*
		finally {
			//Remove below lines once ADS-17843 and ADS-18038    are fixed
			doClick("//*[text()='RVU Container List']/ancestor::div/following-sibling::div//span[text()='Filter']");
			driverDelay();
			doClick("//span[text()='Remove All']");
			doClick(dialog.getFilterDialogButtonApplyFilter());
			waitForDisplayedSpinnerToEnd();
		}
		*/
		
	}
	@Test
	public void test17ApplyCostComponentIsOverheadInRvuContainer_5983() throws Throwable
	{
		try {
//			driverDelay(2000);
//			doClick("//*[text()='RVU Container List']/ancestor::div/following-sibling::div//span[text()='Filter']");
			//Shilpa: 11.3 : 29.07.2025
			doClick("//*[text()='RVU Container List']/ancestor::div/following-sibling::div//span[text()='Filter']/../../..");
			driverDelay();
//			modelHelper.doFilterCreateAndAddFilter(filterByCostComponentIsOverhead,dialog.getFilterDialogFormFieldValue());
			doDropdownSelectUsingOptionTextServices(dialog.getFilterNameField(),dialog.getFilterDialogDropdownField(), "Cost Component Is Overhead");
			
			    doDropdownSelectUsingOptionTextServices(dialog.getFilterNameOperator(),dialog.getFilterDialogDropdownOperator(), "Is");
			    doDropdownSelectUsingOptionTextServices(dialog.getFilterNameCondition(),dialog.getFilterDialogDropdownCondition(), "Equal To");
//			doDropdownSelectUsingOptionText(dialog.getFilterDialogDropdownField(), costing.getEntityDropdownOptionsInFilter(), "Cost Component Is Overhead");
			/** Uncomment below lines once ADS-8863 is fixed **/
//			    doDropdownSelectUsingOptionTextServices(dialog.getstatusFilterDialogFieldValueList(),dialog.getstatusFilterDialogFieldValueList(), "Yes");

//			doDropdownSelectUsingOptionText(dialog.getstatusFilterDialogFieldValueList(), costing.getFilterValueCostComponentOverheadDropdownOptions(), "Yes");
			addFilter();
//			assertElementIsDisplayedWithXpath("//label[contains(text(),'Filter to Match These Criteria 3076/')]");
			//Shilpa updated for 11.2 , as the count keeps changing, not suggested to assert count
			assertElementIsDisplayedWithXpath("//label[contains(text(),'Filter to Match These Criteria')]");
			doClick(dialog.getFilterDialogButtonApplyFilter());
			waitForSpinnerToEnd();
			/*
			test07DeleteFilteredInRvuContainer_5983();
			assertElementIsDisabled(ContractingMap.getCloseandDisplayButton(), printout);
			*/
			test08ClearFilterInRvuContainer_5983();
//			ContractingMap.getCloseBtn().click();
			ExtentReport.logPass("PASS", "test17ApplyCostComponentIsOverheadInRvuContainer");
		} catch (Exception|AssertionError e) {
			ExtentReport.logFail("FAIL", "test17ApplyCostComponentIsOverheadInRvuContainer", driver, e);
			fail(e.getMessage());
		}
		//Not needed 11.2.1 the issues are fixed
		/*
		finally {
			try {
				//Remove below lines once ADS-17843 and ADS-18038    are fixed
//				test08ClearFilterInRvuContainer_5983();
				doClick("//*[text()='RVU Container List']/ancestor::div/following-sibling::div//span[text()='Filter']");
				driverDelay();
				doClick("//span[text()='Remove All']");
				doClick(dialog.getFilterDialogButtonApplyFilter());
				waitForDisplayedSpinnerToEnd();
			} catch (Exception e) {
				
			}
		}
		*/
	}
	
	@Test
	public void test18ApplyEntityCodeAddingMultipleValuesInRvuContainer_5983() throws Throwable {
		try {
			doClick(costing.getRvuContainerClearFilterButton());
			doClick("//*[text()='RVU Container List']/ancestor::div/following-sibling::div//span[text()='Filter']");
//			doDropdownSelectUsingOptionText(dialog.getFilterDialogDropdownField(), costing.getEntityDropdownOptionsInFilter(), "Entity Code");
			doDropdownSelectUsingOptionTextServices(dialog.getFilterNameField(),dialog.getFilterDialogDropdownField(), "Entity Code");
		    doDropdownSelectUsingOptionTextServices(dialog.getFilterNameOperator(),dialog.getFilterDialogDropdownOperator(), "Is");

		    doDropdownSelectUsingOptionTextServices(dialog.getFilterNameCondition(), dialog.getFilterDialogDropdownCondition(), "One Of");
			driverDelay(200);
			for(int i=0;i<=list.size()-1;i++) {
				
				doClick(dialog.getFilterDialogFormFieldValueOneOf());
				dialog.getFilterDialogFormFieldValueOneOf().sendKeys(list.get(i));
				doClick(costing.getRvuContainerAddValueButton());
				
			}
			addFilter();
//			assertElementIsDisplayedWithXpath("//label[text()='Filter to Match These Criteria 6997/6997']");
//			assertElementIsDisplayedWithXpath("//label[contains(text(),'Filter to Match These Criteria 7007/')]");
			//Shilpa updated for 11.2 , as the count keeps changing, not suggested to assert count
			assertElementIsDisplayedWithXpath("//label[contains(text(),'Filter to Match These Criteria')]");
			doClick(dialog.getFilterDialogButtonApplyFilter());
			waitForSpinnerToEnd();
			waitForAjaxExtJs();
			driverDelay(20000);
//			assertElementIsDisplayedWithXpath("//div[contains(@id,'tbtext')][text()='/ 71']");
			//Shilpa updated for 11.2 , the page count might increase so not suggested to use the index
			assertElementIsDisplayedWithXpath("(//div[text()='RVU Container List']//following::div[contains(@class,'x-toolbar-text-default')])[2]");
			test07DeleteFilteredInRvuContainer_5983();
			assertElementIsDisabled(ContractingMap.getCloseandDisplayButton(), printout);
			doClick(costing.getRvuCostCalcScenarioCloseButton());
			ExtentReport.logPass("PASS", "test18ApplyEntityCodeAddingMultipleValuesInRvuContainer");
		} catch (Exception|AssertionError e) {
			ExtentReport.logFail("FAIL", "test18ApplyEntityCodeAddingMultipleValuesInRvuContainer", driver, e);
			fail(e.getMessage());
		}
	}
	@AfterClass
	public static void endtest() throws Exception {
		doClosePageOnLowerBar("RVU Maintenance");
		doClosePageOnLowerBar("Costing Models");
		ExtentReport.report.flush();

	}
}
