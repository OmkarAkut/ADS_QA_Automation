package webdriver.scripts.security;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.fail;

import java.time.Duration;
import java.util.Arrays;
import java.util.List;
import java.util.NoSuchElementException;

import org.assertj.core.error.AssertionErrorCreator;
import org.junit.AfterClass;
import org.junit.BeforeClass;
import org.junit.FixMethodOrder;
import org.junit.Rule;
import org.junit.Test;
import org.junit.runners.MethodSorters;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import com.aventstack.extentreports.Status;

import ExtentReport.ExtentReport;
import webdriver.globalstatic.LoginStatic;
import webdriver.maps.ModelLibraryMap;
import webdriver.maps.mapbuilder.BuildMap;
import webdriver.users.Users;

@FixMethodOrder(MethodSorters.NAME_ASCENDING)
public class EntityLevelUserSecurityAds2409 extends LoginStatic {

	static ModelLibraryMap modelMap;
	static String costModel = "0-MarinaCostModel";
	static String testContractModel1 = "#fz Med IPPS Testing";

	/**
	 * Automates ADS-2409 - Entity Level Security.
	 * 
	 * @throws Exception
	 */
	@BeforeClass
	public static void setupScript() throws Exception {

		ExtentReport.reportCreate("EntityLevelUserSecurityAds2409", "webdriver.scripts.security",
				"EntityLevelUserSecurityAds2409");
		modelMap = BuildMap.getInstance(driver, ModelLibraryMap.class);
		System.out.println("TEST CLASS: " + EntityLevelUserSecurityAds2409.class.getSimpleName());
		evolveLoginStaticUser(Users.RestrictedEntityAndDept1);
	}

//  @AfterClass
//  public static void teardownScript() {
//    try {
//      doClosePageOnLowerBar(testContractModel1);
//      waitForSpinnerToEnd();
//    } catch (Exception ee) {
//      ee.printStackTrace();
//    }
//  }
	
	  
	@Test
	public void test01AssertOnlyAllowedEntitiesDisplayOnMaintainDataEntitiesPage() throws Throwable {
		try {
			goToPage("Maintain Data");
			waitForSpinnerToEnd();
			driver.findElement(By.xpath("//span[text() = 'A - Z']")).click();
			waitForAjaxExtJs();
			driver.findElement(By.xpath("//*[@class='itemWrap' and text()='Entities'] ")).click();
			waitForSpinnerToEnd();
			driverWait();

			assertElementIsDisplayed(
					driver.findElement(By.xpath("//td[contains(@class,'x-grid-cell')]/div[text()='00015']")), printout);// Shilpa
																														// 22.08.2022,
																														// updated
																														// value
																														// to
																														// 00015
																														// from
																														// 150
			try {
				assertEquals(100, getTableNumberOfNumberedRows(true));// updated from 1 to 100 value changed now its 100
																		// for
				ExtentReport.logPass("PASS", "test01AssertOnlyAllowedEntitiesDisplayOnMaintainDataEntitiesPage");

				// test data 00015
			} catch (Exception|  AssertionError e) {

				ExtentReport.logFail("FAIL", "test01AssertOnlyAllowedEntitiesDisplayOnMaintainDataEntitiesPage", driver,e);
				fail(e.getMessage());
				

			} finally {
				doClosePageOnLowerBar("Maintain Data");

			}

		} catch (Exception | AssertionError e) {

			ExtentReport.logFail("FAIL", "test01AssertOnlyAllowedEntitiesDisplayOnMaintainDataEntitiesPage", driver, e);
			fail(e.getMessage());
		}
	}

	@Test
	public void test02CostingCostModelsPageAssertRestrictedEntitiesOnlyDisplay() throws InterruptedException {

		try {
			goToPage("Costing Models");
			waitForSpinnerToEnd();
			driverDelay();
			waitUntilElementIsClickable(modelMap.getModelLibraryFieldSearch());
			doSearchForModel(costModel);
			Thread.sleep(4000);
			List<String> list = javaMakeListOfStringsFromElementOptions(costModel, "tr", 3);
			assertEquals(1, list.size());// table row is now 100 not 1
			List<String> expectedStrings = Arrays.asList(costModel);
			assertListOfStringsContainsExpectedStrings(list, expectedStrings);
			ExtentReport.logPass("PASS", "test01AssertOnlyAllowedEntitiesDisplayOnMaintainDataEntitiesPage");
		} catch (Exception | AssertionError e) {
			ExtentReport.logPass("PASS", "test02CostingCostModelsPageAssertRestrictedEntitiesOnlyDisplay");
			fail(e.getMessage());
		}

	}

	@Test
	public void test03CostingCostModelsPageAssertRestrictedEntitiesDisplay() throws Throwable {

		try {
			tableDoubleClickCellFirstColumn(costModel);
			waitForSpinnerToEnd();
			waitForPageTitle("0-MarinaCostModel");
			/*
			 * waitUntilTreeOptionIsClickable("Assign Costs to Encounters");
			 * doClickTreeItem("Assign Unit Costs");
			 * waitUntilTreeOptionIsClickable("Cost Model Scenario Results");
			 * doClickTreeItem("General Information");
			 */
			waitUntilTreeOptionIsClickable("CM Test");
			doClickTreeItem("CM Test");
			waitUntilTreeOptionIsClickable("Miscellaneous");
			doClickTreeItem("Miscellaneous");
			waitUntilTreeOptionIsClickable("General Information - Cost");
			doClickTreeItem("General Information - Cost");
			driverPause();
			try {

				/*
				 * try {
				 * driver.findElement(By.xpath("//button/span[text()='Read Only']")).click();
				 * driverDelay(); } catch (Exception ee) { ee.printStackTrace(); }
				 */
				waitForSpinnerToEnd();
				waitUntilElementIsClickable(driver.findElement(By.xpath(
						"//div/ul/li[contains(@class,'x-boundlist-item') and text()='150 Marina Medical Center']/..")));
				List<WebElement> list = javaMakeListOfWebElements(driver.findElement(By.xpath(
						"//div/ul/li[contains(@class,'x-boundlist-item') and text()='150 Marina Medical Center']/..")),
						"li");
				assertEquals(6, list.size());// Shilpa 22.08.2022 updated to 6 from 1
				List<String> entities = javaListConvertListOfWebElementsToStrings(list, printout);
				List<String> expectedStrings = Arrays.asList("150 Marina Medical Center");
				assertListOfStringsContainsExpectedStrings(entities, expectedStrings);

				

			} catch (Exception |AssertionError e) {
				ExtentReport.logFail("FAIL", "test03CostingCostModelsPageAssertRestrictedEntitiesDisplay", driver, e);
				fail(e.getMessage());

			} finally {
				Thread.sleep(1000);
				try {
					driver.findElement(By.xpath("//button/span[text()='Cancel & Close']")).click();
					waitForSpinnerToEnd();
					

				} catch (Exception |AssertionError e) {
					driver.findElement(By.xpath("//button/span[text()='Cancel & Close']")).click();
					waitForSpinnerToEnd();
					ExtentReport.logFail("FAIL", "test03CostingCostModelsPageAssertRestrictedEntitiesDisplay", driver,e);
					fail(e.getMessage());

				}

				waitUntilTreeOptionIsClickable("CM Test");
				ExtentReport.logPass("PASS", "test03CostingCostModelsPageAssertRestrictedEntitiesDisplay");
			}

		} catch (Exception | AssertionError e) {
			ExtentReport.logFail("FAIL", "test03CostingCostModelsPageAssertRestrictedEntitiesDisplay", driver, e);
			fail(e.getMessage());

		}
	}

	@Test
	public void test04CostingCostModelsPageAssertRestrictedEntitiesDisplayOnCostComponentMaster() throws Throwable {
		try {
			waitForSpinnerToEnd();
			driverDelay();
			doClickTreeItem("CM Test");
			doClickTreeItem("All Masters");
			//doClickTreeItem("Cost Component Masters");
			//venkat updated name 31-10-2022
			doClickTreeItem("Cost Component Variability Masters");
			
			
			try {
				driverPause();

				/*
				 * try {
				 * driver.findElement(By.xpath("//button/span[text()='Read Only']")).click();
				 * driverDelay(); } catch (Exception e) { e.printStackTrace(); }
				 */

				tableDoubleClickCellFirstColumn("Marina Health System");
				waitForAjaxExtJs();
				//waitUntilElementIsClickable(driver.findElement(By.xpath("//div/ul/li[contains(@class,'x-boundlist-item') and text()='150 Marina Medical Center']/..")));
				//List<WebElement> list = javaMakeListOfWebElements(driver.findElement(By.xpath("//div/ul/li[contains(@class,'x-boundlist-item') and text()='150 Marina Medical Center']/..")),"li");
				//venkat updated xpath 31-10-2022
				waitUntilElementIsClickable(driver.findElement(By.xpath("(//*[text()='Entities']//following::div//tr//td//div[text()='150  Marina Medical Center'])")));
				List<WebElement> list = driver.findElements(By.xpath("(//*[text()='Entities']//following::div//tr//td//div[text()='150  Marina Medical Center'])"));
				
				
				assertEquals(1, list.size());
				List<String> entities = javaListConvertListOfWebElementsToStrings(list, printout);
				List<String> expectedStrings = Arrays.asList("150  Marina Medical Center");
				assertListOfStringsContainsExpectedStrings(entities, expectedStrings);
				

			} catch (Exception |AssertionError e) {
				ExtentReport.logFail("FAIL",
						"test04CostingCostModelsPageAssertRestrictedEntitiesDisplayOnCostComponentMaster", driver, e);
				fail(e.getMessage());

			} finally {
				driver.findElement(By.xpath("//button/span[text()='Cancel & Close']")).click();
				waitForSpinnerToEnd();
				ExtentReport.logPass("PASS","test04CostingCostModelsPageAssertRestrictedEntitiesDisplayOnCostComponentMaster");
			}

		} catch (Exception | AssertionError e) {
			ExtentReport.logFail("FAIL",
					"test04CostingCostModelsPageAssertRestrictedEntitiesDisplayOnCostComponentMaster", driver, e);
			fail(e.getMessage());
		}

	}

	@Test
	public void test05ContractModelsPageAssertRestrictedEntitiesDisplay() throws Throwable {
		try {
			goToPage("Contract Models");
			waitForSpinnerToEnd();
			waitForAjaxExtJs();
			waitUntilElementIsClickable(driver
					.findElement(By.xpath("//div[not(contains(@class,'ccBtnCls'))]/em/button/span[text()='New']")));
			driver.findElement(By.xpath("//div[not(contains(@class,'ccBtnCls'))]/em/button/span[text()='New']"))
					.click();
			waitForSpinnerToEnd();
			driverWait();
			waitUntilElementIsVisible(driver.findElement(By.xpath("//button/span[text()='Add Providers']")));
			driver.findElement(By.xpath("//button/span[text()='Add Providers']")).click();
			waitForSpinnerToEnd();
			driverWait();
			List<String> actualOptions = javaMakeListOfStringsFromElementOptions("150  Marina Medical Center", "");
			// assertEquals(1, actualOptions.size());
			assertEquals(0, actualOptions.size());// Venkat 02-09-2022, updated size to 0 from 1
			List<String> expectedOptions = Arrays.asList("150  Marina Medical Center");
			assertListOfStringsContainsExpectedStrings(expectedOptions, actualOptions);
			
		} catch (Exception |AssertionError e) {
			ExtentReport.logFail("FAIL", "test05ContractModelsPageAssertRestrictedEntitiesDisplay", driver, e);
			fail(e.getMessage());

			// driver.findElement(By.xpath("//button/span[text()='Cancel &
			// Close']")).click();
		} finally {
			try {
				driver.findElement(By.xpath("//button/span[text()='Cancel']")).click();

			} catch (Exception |AssertionError e) {
				ExtentReport.logFail("FAIL", "test05ContractModelsPageAssertRestrictedEntitiesDisplay", driver, e);
				fail(e.getMessage());

			} finally {
				driver.findElement(By.xpath(
						"//div[not(contains(@class, 'cancelCloseBtn'))]/em/button/span[text()='Cancel & Close']"))
						.click();
				waitForAjaxExtJs();
				ExtentReport.logPass("PASS", "test05ContractModelsPageAssertRestrictedEntitiesDisplay");
				
			}
		}
	}

	@Test
	public void test06ContractModelsPageAssertRestrictedEntityIsNotDisplayed() throws Throwable {
		try {
			goToPage("Contract Models");
			waitForSpinnerToEnd();
			waitForAjaxExtJs();
			List<String> displayedContractNames = javaMakeListOfStringsFromElementOptions("#asesc2310a v86 Chg Fields",
					"");// Shilpa
						// 22.08.2022
						// #asesc2310a
						// v86
						// Chg
						// Fields
						// updated
						// to
						// #asesc2310a
						// v86
						// Chg
						// Fields
			List<String> notDisplayedList = Arrays.asList("*EAPG");
			assertListOfStringsDoesNotContainExpectedStrings(displayedContractNames, notDisplayedList);
			ExtentReport.logPass("PASS", "test06ContractModelsPageAssertRestrictedEntityIsNotDisplayed");
		} catch (Exception | AssertionError e) {
			ExtentReport.logFail("FAIL", "test06ContractModelsPageAssertRestrictedEntityIsNotDisplayed", driver, e);
			fail(e.getMessage());
		}

	}

	@Test
	public void test07ContractModelsPageAssertDefinitionElementsProvidersTabContainsOnlyRestrictedEntity()
			throws Throwable {

		try {
			goToPage("Contract Models");
			waitForSpinnerToEnd();
			waitForAjaxExtJs();
			waitUntilElementIsClickable(modelMap.getModelLibraryFieldSearch());
			doSearchForModel(testContractModel1);
			tableDoubleClickCellFirstColumn(testContractModel1);
			System.out.println("testContractModel1:" + testContractModel1);
			waitForSpinnerToEnd();
			waitForAjaxExtJs();
			driverWait();

			// venkat 02.09.2022, added try catch block
            //Venkata adding try catch block
			try {
				driver.findElement(By.xpath("//span[@class='x-panel-header-text x-panel-header-text-default'][text()='"+ testContractModel1 + "']"));

				

			} catch (Exception |AssertionError e) {

				// try {

				driver.findElement(By.xpath("//button/span[text()='Read Only']")).click();
				
				//ExtentReport.logFail("FAIL","test07ContractModelsPageAssertDefinitionElementsProvidersTabContainsOnlyRestrictedEntity",driver, e);
				//fail(e.getMessage());
				// } catch (Exception e1) {
				// ExtentReport.logFail("FAIL","test07ContractModelsPageAssertDefinitionElementsProvidersTabContainsOnlyRestrictedEntity",
				// driver,e);

				// }

			}

			driverDelay();
			doClickTreeItem("General Information - Published Contract");
			waitForSpinnerToEnd();
			driverDelay();
			waitForAjaxExtJs();
			driver.findElement(By.xpath("//button/span[text()='Definition Elements']")).click();
			waitForSpinnerToEnd();

			// expand Providers accordion

			driver.findElement(By.xpath("//div[contains(@class,'x-tool')]/img[contains(@class, 'tool-expand-bottom')]"))
					.click();
			List<String> actualOptions = javaMakeListOfStringsFromElementOptions("150", "");
			assertEquals(0, actualOptions.size());// Shilpa 23.08.2022, updated size to 0 from 1
			List<String> expectedOptions = Arrays.asList("150");
			assertListOfStringsContainsExpectedStrings(expectedOptions, actualOptions);
			ExtentReport.logPass("PASS",
					"test07ContractModelsPageAssertDefinitionElementsProvidersTabContainsOnlyRestrictedEntity");

		} catch (Exception | AssertionError e) {
			
			ExtentReport.logFail("FAIL","test07ContractModelsPageAssertDefinitionElementsProvidersTabContainsOnlyRestrictedEntity", driver,e);
			fail(e.getMessage());
		}

	}

	@Test
	public void test08AssertDefinitionElementsAddProvidersSelectorContainsOnlyRestrictedEntity() throws Throwable {
		
		
			try {

				driverDelay();
				waitForAjaxExtJs();

				waitUntilElementIsClickable(driver.findElement(By.xpath("//button/span[text()='Add Providers']")));
				driver.findElement(By.xpath("//button/span[text()='Add Providers']")).click();
				waitForAjaxExtJs();
				doClick("//label[text()='Select All']/preceding-sibling::input");

				waitForSpinnerToEnd();
				waitForAjaxExtJs();
				driverWait();
				List<String> actualOptions = javaMakeListOfStringsFromElementOptions("150 Marina Medical Center ", "");
				System.out.println(actualOptions.size());
				assertEquals(1, actualOptions.size());
				List<String> expectedOptions = Arrays.asList("150 Marina Medical Center ");
				System.out.println(expectedOptions);
				assertListOfStringsContainsExpectedStrings(expectedOptions, actualOptions);
			    ExtentReport.logPass("PASS",
						"test08AssertDefinitionElementsAddProvidersSelectorContainsOnlyRestrictedEntity");

			}

			catch (Exception| AssertionError e) {
				ExtentReport.logFail("FAIL","test08AssertDefinitionElementsAddProvidersSelectorContainsOnlyRestrictedEntity", driver, e);
				fail(e.getMessage());
				

			} finally {
				// Shilpa 23.08.2022 added try catch if Read only mode option is enabled
				try {
					driver.findElement(By.xpath("//button/span[text()='Cancel']")).click();
					waitForAjaxExtJs();
					driver.findElement(By.xpath(
							"//div[not(contains(@class, 'cancelCloseBtn'))]/em/button/span[text()='Cancel & Close']"))
							.click();
					
				} catch (Exception|AssertionError e) {
					
					try {
						driver.findElement(By.xpath("//div[not(contains(@class, 'cancelCloseBtn'))]/em/button/span[text()='Cancel & Close']")).click();
					} catch (Exception|AssertionError e1) {
						
						ExtentReport.logFail("FAIL","Failed in Finally block", driver, e1);
						fail(e1.getMessage());
					}
					ExtentReport.logFail("FAIL","test08AssertDefinitionElementsAddProvidersSelectorContainsOnlyRestrictedEntity", driver, e);
					fail(e.getMessage());
					

				}
			}
		
	}

	@AfterClass
	public static void endtest() throws Exception {

		ExtentReport.report.flush();

	}

}
