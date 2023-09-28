package webdriver.helpers;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.junit.Assert.assertEquals;
import static org.junit.Assert.fail;

import java.util.Arrays;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

import org.hamcrest.CoreMatchers;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;

import com.google.common.collect.ArrayListMultimap;
import com.google.common.collect.Multimap;

import ExtentReport.ExtentReport;
import webdriver.corehelpers.AdsHelper;
import webdriver.corehelpers.GoHelper;
import webdriver.maps.DataMaintenanceMap;
import webdriver.maps.DialogsMap;
import webdriver.maps.mapbuilder.BuildMap;

public class DataMaintenanceHelper extends GoHelper {
	private DialogsMap dialog = BuildMap.getInstance(driver, DialogsMap.class);

	public void assertTreeListSectionContainsAllItems(String folderName, String firstOptionInList,
			String[] expectedList) throws InterruptedException {
		Thread.sleep(4000);
		/*
		Omkar 21/8/2023 : xpath changes for 11.2
		driver.findElement(By.xpath("//div[text()='" + folderName + "']/img[contains(@class,'x-tree-expander')]"))
				.click();
				*/
		driver.findElement(By.xpath("//span[text()='" + folderName + "']/../div[contains(@class,' x-tree-expander')]"))
		.click();
		waitForAjaxExtJs();
		Thread.sleep(1000);
		/*
		List<WebElement> contractingList = javaMakeListOfWebElements(driver.findElement(By.xpath("//div[text()='"
				+ firstOptionInList + "']/img[contains(@class,'x-tree-icon x-tree-icon-leaf')]/ancestor::tbody")),
				"tr");
				*/
		List<WebElement> contractingList = javaMakeListOfWebElements(driver.findElement(By.xpath("//div[text()='"
				+ firstOptionInList + "']/ancestor::tbody")),"tr");
		List<String> actualListForContractingPages = javaMakeListOfStringsFromElementText(contractingList, "class",
				"x-grid-tree-node-leaf", "td[contains(@class,'x-grid-cell-treecolumn')]/div");
		assertEquals(Arrays.asList(expectedList), actualListForContractingPages);
	}

	public void assertPageLoads(List<String> testList) throws InterruptedException {
		int counter = 1;
		String actualText;
		boolean allTestsPassed = true;
		for (String expectedText : testList) {
			if (expectedText.equals("User Defined Fields And Relations")) {
				continue;
			}
			driver.findElement(By.xpath("//div[text() = '" + expectedText + "']")).click();
			waitForAjaxExtJs();
			waitForSpinnerToEnd();
			if (expectedText.equals("Activity Volume Data Scenarios")
			// actualText =
			// getElementText(driver.findElement(By.xpath("//span[contains(@id,'activityvolumedatalist')]")),
			// false);
//              Omkar 16/8/2022 : below value has been added as it needs to be set as the first value in the list
					// || expectedText.equals("Activity Statistic Masters")
					|| expectedText.equals("Charge Item Service Classification Schemes")
					|| expectedText.equals("Consumers")
					|| expectedText.equals("Encounter Service Classification Schemes")
					|| expectedText.equals("Encounters") || expectedText.equals("GL Reclassification Masters")
					|| expectedText.equals("Group Data Scenarios") || expectedText.equals("Labor Cost Components")
					|| expectedText.equals("Labor RVU Calculation") || expectedText.equals("Microcosting Wage Rates")
					|| expectedText.equals("RVU Maintenance") || expectedText.equals("Staffing Data Scenarios")
					|| expectedText.equals("Statistic Data Scenarios")) {
				actualText = getElementText(
						driver.findElement(By
								.xpath("//div[contains(@class,'x-panel-header')]/span[not(contains(@id,'leftview'))]")),
						false);
			} else if (expectedText.equals("Populations") || expectedText.equals("Services")
					|| expectedText.equals("Psych Combined Comorbidity Assignments")) {
				String formattedExpectedText = expectedText.toLowerCase().replaceAll("\\s+", "");
				actualText = getElementText(
						driver.findElement(By.xpath("//span[contains(@id,'" + formattedExpectedText + "maingrid')]")),
						false);
			} else {
				waitForAjaxExtJs();
				waitForSpinnerToEnd();
				actualText = getElementText(driver.findElement(By.xpath("//div[contains(@class,'areaTitle')]")), false);
			}
			assertThat("< FAILED > on: " + expectedText, actualText, CoreMatchers.equalTo(expectedText));
			// assertFirstRowNumberFromResultsTable(expectedText, printout);
			System.out.println("test number: " + counter);
			counter++;
			String rowNumber;
			try {
				Thread.sleep(1000);
				rowNumber = driver.findElement(By.xpath("//*[contains(@class,'rownumberer')]/div")).getText();
				//// td[contains(@class,'rownumberer')]/div[text()='1']
				if (printout) {
					System.out.println("Row Number = " + rowNumber);
				}
				assertEquals("Failed on: " + expectedText, "1", rowNumber);
			} catch (Exception e) {
				fail(expectedText + ": " + e.getMessage());
			}
		}
//    if (allTestsPassed == false) {
//      fail("There are test failures. Check console output.");
//    }
	}

//  public void
//  assertPageLoads(List<String> testList) {
//    String actualText;
//    boolean allTestsPassed = true;
//    for (String expectedText : testList) {
//      if (expectedText.equals("User Defined Fields And Relations")) {
//        continue;
//      }
//      try {
//        driver.findElement(By.xpath("//div[text() = '" + expectedText + "']")).click();
//        waitForAjaxExtJs();
//        waitForSpinnerToEnd();
//        if (expectedText.equals("Activity Volume Data Scenarios")
//          //actualText = getElementText(driver.findElement(By.xpath("//span[contains(@id,'activityvolumedatalist')]")), false);
//                || expectedText.equals("Charge Item Service Classification Schemes")
//                || expectedText.equals("Consumers")
//                || expectedText.equals("Encounter Service Classification Schemes")
//                || expectedText.equals("Encounters")
//                || expectedText.equals("GL Reclassification Masters")
//                || expectedText.equals("Group Data Scenarios")
//                || expectedText.equals("Labor Cost Components")
//                || expectedText.equals("Labor RVU Calculation")
//                || expectedText.equals("Microcosting Wage Rates")
//                || expectedText.equals("RVU Maintenance")
//                || expectedText.equals("Staffing Data Scenarios")
//                || expectedText.equals("Statistic Data Scenarios")) {
//          actualText = getElementText(driver.findElement(By.xpath("//div[contains(@class,'x-panel-header')]/span[not(contains(@id,'leftview'))]")), false);
//        } else if (expectedText.equals("Populations")
//                  || expectedText.equals("Services")
//                  || expectedText.equals("Psych Combined Comorbidity Assignments")) {
//            String formattedExpectedText = expectedText.toLowerCase().replaceAll("\\s+", "");
//            actualText = getElementText(driver.findElement(By.xpath("//span[contains(@id,'"+formattedExpectedText+"maingrid')]")), false);
//        } else {
//          actualText = getElementText(driver.findElement(By.xpath("//div[contains(@class,'areaTitle')]")), false);
//        }
//        assertThat("< FAILED > on: " + expectedText, actualText, CoreMatchers.equalTo(expectedText));
//        assertFirstRowNumberFromResultsTable(printout);
//      } catch (Throwable e) {
//        System.out.println("<<FAILED>> on item: " + expectedText);
//        allTestsPassed = false;
//      }
//    }
//    if (allTestsPassed == false) {
//      fail("There are test failures. Check console output.");
//    }
//  }

	public List<String> printTreeListSectionPages(String folderName, String firstOptionInList, String[] expectedList)
			throws InterruptedException {
		driver.findElement(By.xpath("//div[text()='" + folderName + "']/img[contains(@class,'x-tree-expander')]"))
				.click();
		waitForAjaxExtJs();
		Thread.sleep(1000);
		List<WebElement> contractingList = javaMakeListOfWebElements(driver.findElement(By.xpath("//div[text()='"
				+ firstOptionInList + "']/img[contains(@class,'x-tree-icon x-tree-icon-leaf')]/ancestor::tbody")),
				"tr");
		List<String> actualListForContractingPages = javaMakeListOfStringsFromElementText(contractingList, "class",
				"x-grid-tree-node-leaf", "td[contains(@class,'x-grid-cell-treecolumn')]/div");
		return actualListForContractingPages;
	}

	public void FilterByChargeCode(String aTozPage, String[] filterPriceList, String priceList, List<WebElement> list,
		String price01, String price02, String price03, String price04, String price05, String price06,
		String price07, String price08) throws Throwable {
		CalculationHelper.selectMaintainDataAtoZ(aTozPage);
		doClick(DataMaintenanceMap.getLoadDataFilterButton());
		AdsHelper ads = new AdsHelper();
		ads.doFilterCreate(filterPriceList);
		tableDoubleClickCellFirstColumn(priceList);
		Multimap<String, String> multimap = ArrayListMultimap.create();
		multimap.put("Charge Code", "5465158");
		multimap.put("Charge Code", "8460206");
		multimap.put("Charge Code", "90118");
		multimap.put("Charge Code", "117");
		multimap.put("Charge Code", "8141517");
		multimap.put("Charge Code", "7715139");
		multimap.put("Charge Code", "2783033");
		multimap.put("Charge Code", "8407066");
		for (Map.Entry<String, String> entry : multimap.entries()) {
			doClick(DataMaintenanceMap.getPriceItemFilterButton());
			if (entry.getKey().equals("Charge Code")) {
				if (entry.getValue().equals("5465158")) {
					doFilterSetFilterParameters("Department Code", "Is", "Equal To", "1111");
					doClick(dialog.getFilterDialogButtonAdd());
					waitForAjaxExtJs();
					doFilterSetFilterParameters("Charge Code", "Is", "Equal To", "5465158");

				} else if (entry.getValue().equals("8460206")) {
					doFilterSetFilterParameters("Department Code", "Is", "Equal To", "2016");
					doClick(dialog.getFilterDialogButtonAdd());
					waitForAjaxExtJs();
					doFilterSetFilterParameters("Charge Code", "Is", "Equal To", "8460206");

				} else if (entry.getValue().equals("90118")) {
					doFilterSetFilterParameters("Department Code", "Is", "Equal To", "2111");
					doClick(dialog.getFilterDialogButtonAdd());
					waitForAjaxExtJs();
					doFilterSetFilterParameters("Charge Code", "Is", "Equal To", "90118");

				} else if (entry.getValue().equals("117")) {
					doFilterSetFilterParameters("Department Code", "Is", "Equal To", "2220");
					doClick(dialog.getFilterDialogButtonAdd());
					waitForAjaxExtJs();
					doFilterSetFilterParameters("Charge Code", "Is", "Equal To", "117");

				} else if (entry.getValue().equals("8141517")) {
					doFilterSetFilterParameters("Department Code", "Is", "Equal To", "2269");
					doClick(dialog.getFilterDialogButtonAdd());
					waitForAjaxExtJs();
					doFilterSetFilterParameters("Charge Code", "Is", "Equal To", "8141517");

				} else if (entry.getValue().equals("7715139")) {
					doFilterSetFilterParameters("Department Code", "Is", "Equal To", "2330");
					doClick(dialog.getFilterDialogButtonAdd());
					waitForAjaxExtJs();
					doFilterSetFilterParameters("Charge Code", "Is", "Equal To", "7715139");

				} else if (entry.getValue().equals("2783033")) {
					doFilterSetFilterParameters("Department Code", "Is", "Equal To", "3030");
					doClick(dialog.getFilterDialogButtonAdd());
					waitForAjaxExtJs();
					doFilterSetFilterParameters("Charge Code", "Is", "Equal To", "2783033");

				} else if (entry.getValue().equals("8407066")) {
					doFilterSetFilterParameters("Department Code", "Is", "Equal To", "4021");
					doClick(dialog.getFilterDialogButtonAdd());
					waitForAjaxExtJs();
					doFilterSetFilterParameters("Charge Code", "Is", "Equal To", "8407066");

				}
			}
			waitForAjaxExtJs();
			waitUntilElementIsClickable(dialog.getFilterDialogButtonAdd());
			doClick(dialog.getFilterDialogButtonAdd());
			waitForAjaxExtJs();
			doClick(dialog.getFilterDialogButtonApplyFilter());
			waitForSpinnerToEnd();
			List<WebElement> listCode = DataMaintenanceMap.getPriceItemDeptCode();
			for (int i = 2; i <= listCode.size(); i++) {
				String deptCode = "//span[text()='Department Code']//following::div[contains(@id,'dynamicGrid')]//table//tr["
						+ i + "]/td[5]/div";
				if (driver.findElement(By.xpath(deptCode)).getText().equals("1111")) {
					assertElementText(driver.findElement(By.xpath(deptCode + "//following::td[6]/div")), price01,
							printout);
					break;
				}
				if (driver.findElement(By.xpath(deptCode)).getText().equals("2016")) {
					assertElementText(driver.findElement(By.xpath(deptCode + "//following::td[6]/div")), price02,
							printout);
					break;
				}
				if (driver.findElement(By.xpath(deptCode)).getText().equals("2111")) {
					assertElementText(driver.findElement(By.xpath(deptCode + "//following::td[6]/div")), price03,
							printout);
					break;
				}
				if (driver.findElement(By.xpath(deptCode)).getText().equals("2220")) {
					assertElementText(driver.findElement(By.xpath(deptCode + "//following::td[6]/div")), price04,
							printout);
					break;
				}
				if (driver.findElement(By.xpath(deptCode)).getText().equals("2269")) {
					assertElementText(driver.findElement(By.xpath(deptCode + "//following::td[6]/div")), price05,
							printout);
					break;
				}
				if (driver.findElement(By.xpath(deptCode)).getText().equals("2330")) {
					assertElementText(driver.findElement(By.xpath(deptCode + "//following::td[6]/div")), price06,
							printout);
					break;
				}
				if (driver.findElement(By.xpath(deptCode)).getText().equals("3030")) {
					assertElementText(driver.findElement(By.xpath(deptCode + "//following::td[6]/div")), price07,
							printout);
					break;
				}
				if (driver.findElement(By.xpath(deptCode)).getText().equals("4021")) {
					assertElementText(driver.findElement(By.xpath(deptCode + "//following::td[6]/div")), price08,
							printout);
					break;
				}

			}
			doClick(DataMaintenanceMap.getPriceItemClearFilterButton());
			driverDelay(1000);
		}

	}
}
