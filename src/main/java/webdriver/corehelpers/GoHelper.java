package webdriver.corehelpers;

import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebElement;

import webdriver.maps.GeneralElementsMap;
import webdriver.maps.ModelLibraryMap;
import webdriver.maps.mapbuilder.BuildMap;

public class GoHelper extends AssertHelper {

	static GeneralElementsMap tab;
	private static ModelLibraryMap modelMap;

	public static void goToPage(String page) throws InterruptedException {
		page = page.toLowerCase();
		tab = BuildMap.getInstance(driver, GeneralElementsMap.class);
		modelMap = BuildMap.getInstance(driver, ModelLibraryMap.class);
		waitForAjaxExtJs();

		/*************************Main Tabs********************************/
		//Analytics
		if (page.equals("analytics")) {
			tab.getAnalyticsTab().click();
		}
		//Reporting
		if (page.equals("reporting")) {
			tab.getReportingTab().click();
		}
		//Costing
		if (page.equals("costing")) {
			tab.getCostingTab().click();
		}
		//Contracting
		if (page.equals("contracting")) {
			tab.getContractingTab().click();
		}
		//Episodes
		if (page.equals("episodes")) {
			tab.getEpisodesTab().click();
		}
		//Budgeting
		if (page.equals("budgeting")) {
			tab.getBudgetingTab().click();
		}
		//Data Maintenance
		if (page.equals("dataMaintenance")) {
			tab.getDataMaintenanceTab().click();
		}
		//System Maintenance
		if (page.equals("systemMaintenance")) {
			tab.getSystemMaintenanceTab().click();
		}
		//Status
		if (page.equals("status")) {
			tab.getStatusTab().click();
		}

		/***********************Sub Tabs**********************************/

		//Analytics > Executive Dashboards
		if (page.equals("executive dashboards")) {
			tab.getAnalyticsTab().click();
			tab.getExecutiveDashboardSubTab().click();
		}
		//Analytics > Analytic Dashboards
		if (page.equals("analytic dashboards")) {
			tab.getAnalyticsTab().click();
			tab.getAnalyticDashboardSubTab().click();
		}
		//Analytics > Analytic Refresh Scenarios
		if (page.equals("analytic refresh scenarios")) {
			tab.getAnalyticsTab().click();
			tab.getAnalyticRefreshScenariosSubTab().click();
		}
		//Analytics > Analytics Administration
		if (page.equals("analytics administration")) {
			tab.getAnalyticsTab().click();
			tab.getAnalyticsAdministrationSubTab().click();
		}
		//Analytics > Customize Analytics
		if (page.equals("customize analytics")) {
			tab.getAnalyticsTab().click();
			tab.getCustomizeAnalyticsSubTab().click();
		}
		//Analytics > Customize Analytics Sessions
		if (page.equals("customize analytics sessions")) {
			tab.getAnalyticsTab().click();
			tab.getCustomizeAnalyticsSessionsSubTab().click();
		}
		//Analytics > Analytics Server Desktop
		if (page.equals("analytics server desktop")) {
			tab.getAnalyticsTab().click();
			tab.getAnalyticsServerDesktopSubTab().click();
		}
		//Analytics > Analytics Server Desktop Sessions
		if (page.equals("analytics server desktop sessions")) {
			tab.getAnalyticsTab().click();
			tab.getAnalyticsServerDesktopSessionsSubTab().click();
		}

		//Reporting > Report Library
		if (page.equals("report library")) {
			Thread.sleep(500);
			tab.getReportingTab().click();
			tab.getReportingLibrarySubTab().click();
		}
		//Reporting > Web Intelligence
		if (page.equals("web intelligence")) {
			tab.getReportingTab().click();
			tab.getWebIntelligenceSubTab().click();
		}
		//Reporting > Ad Hoc Report Design
		if (page.equals("ad hoc report design")) {
			tab.getReportingTab().click();
			tab.getAdHocReportDesignSubTab().click();
		}
		//Reporting > Report Menu Maintenance
		if (page.equals("report menu maintenance")) {
			tab.getReportingTab().click();
			tab.getReportMenuMaintenanceSubTab().click();
		}
		//Reporting > Report Date Maintenance
		if (page.equals("report date maintenance")) {
			tab.getReportingTab().click();
			tab.getReportDateMaintenanceSubTab().click();
		}
		//Reporting > Report Publication
		if (page.equals("report publication")) {
			tab.getReportingTab().click();
			tab.getReportPublicationSubTab().click();
		}
		//Reporting > Ad Hoc Business View Maintenance
		if (page.equals("ad hoc business view maintenance")) {
			tab.getReportingTab().click();
			tab.getAdHocBusinessViewMaintenanceSubTab().click();
		}
		//Reporting > Web Intelligence Universe Maintenance
		if (page.equals("web intelligence universe maintenance")) {
			tab.getReportingTab().click();
			tab.getWebIntelligenceUniverseMaintenanceSubTab().click();
		}
		//Reporting > ICD9/ICD10 GEMs Inquiry
		if (page.contains("gems inquiry")) {
			Thread.sleep(500);
			tab.getReportingTab().click();
			tab.getIcd9icd10GemsInquirySubTab().click();
		}
		//Reporting > ICD9/ICD10 GEMs Analysis
		if (page.contains("gems analysis")) {
			Thread.sleep(500);
			tab.getReportingTab().click();
			tab.getIcd9icd10GemsAnalysisSubTab().click();
		}
		//Reporting > Costing Reports
		if (page.equals("costing reports")) {
			tab.getReportingTab().click();
			tab.getCostingReportsSubTab().click();
		}

		//Costing > Costing Models
		if (page.equals("costing models")) {
			tab.getCostingTab().click();
			tab.getCostingModelsSubTab().click();
		}
		//Costing > Costing Data Maintenance
		if (page.equals("costing data maintenance")) {
			tab.getCostingTab().click();
			tab.getCostingDataMaintenanceSubTab().click();
		}
		//Costing > RVU Maintenance
		if (page.equals("rvu maintenance")) {
			tab.getCostingTab().click();
			tab.getRVUMaintenanceSubTab().click();
		}
		//Costing > Cost Model Scenario Calculation
		if (page.equals("cost model scenario calculation")) {
			tab.getCostingTab().click();
			tab.getCostModelScenarioCalculationSubTab().click();
		}
		//Costing > Unit Cost Quick Calculation
		if (page.equals("ucqc") || page.equals("unit cost quick calculation")) {
			tab.getCostingTab().click();
			tab.getUnitCostQuickCalculationSubTab().click();
		}

		//Contracting > Contract Models
		if (page.equals("contract models")) {
			tab.getContractingTab().click();
			tab.getContractModelsSubTab().click();
		}
		//Contracting > Contracting Data Maintenance
		if (page.equals("contracting data maintenance")) {
			tab.getContractingTab().click();
			tab.getContractingDataMaintenanceSubTab().click();
		}
		//Contracting > Contractual Allowance Export
		if (page.equals("contractual allowance export")) {
			tab.getContractingTab().click();
			tab.getContractualAllowanceExportSubTab().click();
		}
		//Contracting > APC Allocation
		if (page.equals("apc allocation")) {
			tab.getContractingTab().click();
			waitForAjaxExtJs();
			tab.getApcAllocationSubTab().click();
		}
		//Episodes > Episode Models
		if (page.equals("episode models")) {
			tab.getEpisodesTab().click();
			tab.getEpisodeModelsSubTab().click();
		}
		//Episodes > Episode Data Maintenance
		if (page.equals("episode data maintenance")) {
			tab.getEpisodesTab().click();
			tab.getEpisodeDataMaintenanceSubTab().click();
		}
		//Budgeting > Budgeting
		if (page.equals("budgeting")) {
			tab.getBudgetingTab().click();
			tab.getBudgetingSubTab().click();
		}

		//Data Maintenance > Maintain Data
		if (page.equals("maintain data")) {
			tab.getDataMaintenanceTab().click();
			tab.getMaintainDataSubTab().click();
		}
		//Data Maintenance > Load Data
		if (page.equals("load data")) {
			tab.getDataMaintenanceTab().click();
			tab.getLoadDataSubTab().click();
		}
		//Data Maintenance > Utilities
		if (page.equals("utilities")) {
			tab.getDataMaintenanceTab().click();
			tab.getUtilitiesSubTab().click();
		}

		//System Maintenance > Users
		if (page.equals("users")) {
			tab.getSystemMaintenanceTab().click();
			tab.getUsersSubTab().click();
		}
		//System Maintenance > Roles
		if (page.equals("roles")) {
			tab.getSystemMaintenanceTab().click();
			tab.getRolesSubTab().click();
		}
		//System Maintenance > Security Settings
		if (page.equals("security settings") || page.equals("security")) {
			tab.getSystemMaintenanceTab().click();
			tab.getSecuritySettingsSubTab().click();
		}
		//System Maintenance > General Settings
		if (page.equals("general settings")) {
			tab.getSystemMaintenanceTab().click();
			tab.getGeneralSettingsSubTab().click();
		}
		//System Maintenance > Customize Maintain Data
		if (page.equals("customize maintain data")) {
			waitForJsWindowOnload();
			tab.getSystemMaintenanceTab().click();
			tab.getCustomizeMaintainDataSubTab().click();
		}
		//System Maintenance > Customize Task Lists
		if (page.equals("customize task lists")) {
			tab.getSystemMaintenanceTab().click();
			tab.getCustomizeTaskListSubTab().click();
		}
		//System Maintenance > Terminal Server Sessions
		if (page.equals("terminal server sessions")) {
			tab.getSystemMaintenanceTab().click();
			tab.getTerminalServerSessionsSubTab().click();
		}
		//System Maintenance > Terminal Server Desktop
		if (page.equals("terminal server desktop")) {
			tab.getSystemMaintenanceTab().click();
			tab.getTerminalServerDesktopSubTab().click();
		}

		// ===== Status > Calculation Status ===== //
		if (page.equals("calculation status")) {
			tab.getStatusTab().click();
			tab.getCalculationStatusSubTab().click();
		}
		//Status > Import/Export Status
		if (page.equals("import/export status") || page.equals("import status")) {
			tab.getStatusTab().click();
			tab.getImportExportStatusSubTab().click();
		}
		//Status > Utility Status
		if (page.equals("utility status")) {
			tab.getStatusTab().click();
			tab.getUtilityStatusSubTab().click();
		}
		// ===== End Status > Calculation Status ===== //

		/*************************End********************************/
	}

	public static void navigateToContractModelsPageFeeForServicePaymentTermsPage(String contractModel) {
		try {
			goToPage("Contract Models");
			webdriverMaximizeWindow();
			waitForSpinnerToEnd();
			waitForAjaxExtJs();
			driver.findElement(By.xpath("//input[@name='searchText']")).sendKeys(contractModel);
			modelMap.getModelLibraryButtonSearch().click();
			waitForAjaxExtJs();
			Thread.sleep(1000);
			//			Omkar 28/03/2023 : Xpath changes for 11.2
			//			driver.findElement(By.xpath("//td[contains(@class,'x-grid-cell x-grid-cell-gridcolumn')]/*[text()='" + contractModel + "']")).click();
			//shilpa 11/11/2023 : Xpath changes for 11.2
			driver.findElement(By.xpath("//td[contains(@role,'gridcell')]/*[text()='AFT IPPS 2020 - Criteria Text']")).click();
			Thread.sleep(500);
			driver.findElement(By.xpath("//span[text()='Open Task List']")).click();
			try {
				String lockText = driver.findElement(By.xpath("//*[contains(text(),'currently locked by client')] ")).getText();
				System.out.println("Domain Lock is Blocking Test Run: " + lockText);
			} catch (Throwable e) {}
			waitForAjaxExtJs();
			Thread.sleep(2000);
			//			Omkar 28/03/2023 : Xpath changes for 11.2
			//			driver.findElement(By.xpath("//td[contains(@class,'x-grid-cell-treecolumn')]/*[text()='Model Contract']")).click();
			driver.findElement(By.xpath("//span[text()='Model Contract']")).click();
			Thread.sleep(5000);
			waitForSpinnerToEnd();
			waitForAjaxExtJs();
			//			Omkar 28/03/2023 : Xpath changes for 11.2
			//			driver.findElement(By.xpath("//td[contains(@class,'x-grid-cell-treecolumn')]/*[text()='Define Payment Terms']")).click();
			driver.findElement(By.xpath("//span[text()='Define Payment Terms']")).click();
			waitForAjaxExtJs();
			Thread.sleep(5000);
			//			String feeCheckbox = driver.findElement(By.xpath("//td[contains(@class,'x-grid-cell-treecolumn')]/*[text()='Fee For Service Payment Terms']/input")).getAttribute("class");
			//assertTrue(feeCheckbox.contains("checked"));
			//			Omkar 28/03/2023 : Xpath changes for 11.2
			//			driver.findElement(By.xpath("//td[contains(@class,'x-grid-cell-treecolumn')]/*[text()='Fee For Service Payment Terms']")).click();
			driver.findElement(By.xpath("//span[text()='Fee For Service Payment Terms']")).click();
			waitForSpinnerToEnd();
			waitForAjaxExtJs();
			System.out.println(5000);
		} catch (Throwable e) {
			e.getMessage();
		}
	}

	@SuppressWarnings("unused")
	private static void checkForDomainLock() {
		try {
			String lockText = driver.findElement(By.xpath("//*[contains(text(),'currently locked by client')] ")).getText();
			System.out.println("Domain Lock is Blocking Test Run: " + lockText);
		} catch (AssertionError ae) {

		}
	}

	public static void navigateFeeForServicePaymentTermsPagePricingMethodSectionClickEditButtonToOpenEditDialog() {
		try {
			waitForAjaxExtJs();
			driver.findElement(By.xpath("//*[text()='Edit']")).click();
			waitForAjaxExtJs();
			Thread.sleep(1000);
			//assertElementTextWithXpath("//span[contains(@id, 'medicareinpatientpps')]", "Edit Price for " + serviceModel + " [Encounter]", printout);
		} catch (Throwable e) {
			e.getMessage();
		}
	}

	public static void navigateFeeForServicePaymentTermsPagePricingMethodSectionSelectServiceModel(String serviceModel) {
		try {
			waitForSpinnerToEnd();
			waitForAjaxExtJs();
			System.out.println(2000);
			//			Omkar 29/03/2023 : Xpath change for 11.2
			//			driver.findElement(By.xpath("//span[contains(@class,'x-panel-header-text')][text()='Pricing Method']/../following-sibling::div")).click();
			driver.findElement(By.xpath("//div[text()='Pricing Method']/../following-sibling::div")).click();
			waitForAjaxExtJs();
			Thread.sleep(2000);
//			Omkar 29/03/2023 : Xpath change for 11.2
//			driver.findElement(By.xpath("//label[text()='Service Model']/ancestor::div/descendant::div[text() = '" + serviceModel + "'][2]")).click();
			driver.findElement(By.xpath("//span[text() = '" + serviceModel + "']")).click();
			Thread.sleep(2000);
		} catch (Exception e) {
			JavascriptExecutor executor = (JavascriptExecutor)driver;
			executor.executeScript("arguments[0].click();",driver.findElement(By.xpath("//label[text()='Service Model']/ancestor::div/descendant::div[text() = '" + serviceModel + "'][2]"))
					);
		}
	}


	public static void navigateToFeeForServicePaymentTermsPagePricingMethodSectionEditPriceDialog(String serviceModel) {
		try {
			navigateFeeForServicePaymentTermsPagePricingMethodSectionSelectServiceModel(serviceModel);
			navigateFeeForServicePaymentTermsPagePricingMethodSectionClickEditButtonToOpenEditDialog();
		} catch (Throwable e) {
			e.getMessage();
		}
	}

	public static void navigateToContractModelsPageFeeForServicePaymentTermsPageEditPriceEncounterDialog(String contractModel, String serviceModel) {
		try {
			navigateToContractModelsPageFeeForServicePaymentTermsPage(contractModel);
			navigateToFeeForServicePaymentTermsPagePricingMethodSectionEditPriceDialog(serviceModel);
		} catch (Throwable e) {
			e.getMessage();
		}
	}

	public static void navigateCloseSectionOpenNewSection(String closeSection, String newSection) throws InterruptedException {
		Thread.sleep(3000);  //was 5000
		waitForPresenceOfElement("//*[contains(@id, 'customaccordianpanel')][text()='" + closeSection + "']/parent::div/following-sibling::div/img");
		driver
		.findElement(By.xpath("//*[contains(@id, 'customaccordianpanel')][text()='" + closeSection + "']/parent::div/following-sibling::div/img"))
		.click();
		Thread.sleep(2000); //was 3000
		waitForAjaxExtJs();
		driver.findElement(By.xpath("//*[contains(@id, 'customaccordianpanel')][text()='" + newSection + "']/parent::div/following-sibling::div/img")).click();
		waitForAjaxExtJs();
		Thread.sleep(2000); //was 3000
	}

	public static void navigateCloseGeneralSectionOpenNewSection(String newSection) throws InterruptedException {
		Thread.sleep(3000);  //was 5000
//		Omkar 13/7/2023 : xpath changes for 11.2
//		waitForPresenceOfElement("//*[contains(@id, 'customaccordianpanel')][text()='General']/parent::div/following-sibling::div/img");
		driver.findElement(By.xpath("//*[contains(@id, 'customaccordianpanel')][text()='General']/parent::div/following-sibling::div/img")).click();
		waitForPresenceOfElement("//*[contains(@id, 'customaccordianpanel')][text()='General']/parent::div/following-sibling::div/div");
		driver.findElement(By.xpath("//*[contains(@id, 'customaccordianpanel')][text()='General']/parent::div/following-sibling::div/div")).click();

		//driver.findElement(By.xpath("//*[contains(@id, 'customaccordianpanel')][text()='General']")).click();  //*[contains(@id, 'customaccordianpanel')][text()='General']/parent::div
		Thread.sleep(2000); //was 3000
		waitForAjaxExtJs();
//		Omkar 13/7/2023 : xpath changes for 11.2
//		driver.findElement(By.xpath("//*[contains(@id, 'customaccordianpanel')][text()='" + newSection + "']/parent::div/following-sibling::div/img")).click();
		driver.findElement(By.xpath("//*[contains(@id, 'customaccordianpanel')][text()='" + newSection + "']/parent::div/following-sibling::div/div")).click();
		
		waitForAjaxExtJs();
		Thread.sleep(2000); //was 3000
	}

	public static void navigateOpenNewSection(String newSection) throws InterruptedException {
		Thread.sleep(3000);  //was 5000
		waitForAjaxExtJs();
		driver.findElement(By.xpath("//*[contains(@id, 'customaccordianpanel')][text()='" + newSection + "']/parent::div/following-sibling::div/img")).click();
		waitForAjaxExtJs();
		Thread.sleep(2000); //was 3000
	}

	public static void navigateOpenNewSection(WebElement element) throws InterruptedException {
		Thread.sleep(3000);  //was 5000
		waitForAjaxExtJs();
		element.click();
		waitForAjaxExtJs();
		Thread.sleep(2000); //was 3000
	}

	public void goToPageNumberUsingPaginationControls(int pageNumber) {
		driver.findElement(By.name("inputItem")).clear();
		driver.findElement(By.name("inputItem")).sendKeys(Integer.toString(pageNumber));
		driver.findElement(By.xpath("//span[@class='x-btn-icon pagging-tbar-go-button']")).click();
		waitForSpinnerToEnd();
	}
}
