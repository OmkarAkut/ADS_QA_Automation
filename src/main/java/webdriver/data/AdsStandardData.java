package webdriver.data;

import java.util.Arrays;
import java.util.List;

public class AdsStandardData {

	public static List<String> expectedTabs = Arrays.asList(
			"Analytics", "Reporting", "Costing", "Contracting", "Episodes", "Budgeting",
			"Data Maintenance", "System Maintenance", "Status"
			);

	public static List<String> expectedAnalyticsSubTabs = Arrays.asList(
			"Executive Dashboards", "Analytic Dashboards", "Analytic Refresh Scenarios", "Analytics Administration",
			"Customize Analytics", "Customize Analytics Sessions", "Analytics Server Desktop",
			"Analytics Server Desktop Sessions"
			);

	public static List<String> expectedReportingSubTabs = Arrays.asList(
			//Omkar 30/8/2022 : Below options seem to have changed.Adding the current sub menu received for Reporting in QA3

			/* "Report Library", "Web Intelligence", "Ad Hoc Report Design", "Report Menu Maintenance",
      "Report Date Maintenance", "Report Publication", "Ad Hoc Business View Maintenance",
      "Web Intelligence Universe Maintenance", "ICD9/ICD10 GEMs Inquiry", "ICD9/ICD10 GEMs Analysis"*/	  

			"Report Library","Web Intelligence","Ad Hoc Report Design","Report Menu Maintenance","Report Date Maintenance",
			"Report Publication","Ad Hoc Business View Maintenance","Web Intelligence Universe Maintenance","ICD9/ICD10 GEMs Inquiry",
			"ICD9/ICD10 GEMs Analysis","CUSTOM NAME","Test Report","MB Test","Custom Report Menu","Test Custom Report","Test New Menu","Report Menu File",
			"jskoff test","EP Test","Tinesha","Costing Reports","OKHeart","new_menu_item","BPTESTItem","JK Test Report","MB Report SP2","new publ P2","mcarlson_dept report menu",
			"mcarlson report menu","test quick","mb new test","mb new 9.4 regression","mb test security","Report Admin HCA before","mbreport hca 10 16","new 10-18","new 10-18 DB report",
			"mb db aadmin","mbekkerall new","Ven","mb 98 regress"
			//"asdfsVVVC","dasfasdf"
			);

	public static List<String> expectedCostingSubTabs = Arrays.asList(
			"Costing Models", "Costing Data Maintenance", "RVU Maintenance",
			"Cost Model Scenario Calculation", "Unit Cost Quick Calculation"
			);

	public static List<String> expectedContractingSubTabs = Arrays.asList(
			"Contract Models", "Contracting Data Maintenance", "Contractual Allowance Export", "APC Allocation"
			);

	public static List<String> expectedEpisodesSubTabs = Arrays.asList(
			"Episode Models", "Episode Data Maintenance"
			);

	public static List<String> expectedBudgetingSubTabs = Arrays.asList(
			"Budgeting"
			);

	public static List<String> expectedDataMaintenanceSubTabs = Arrays.asList(
			"Maintain Data", "Load Data", "Utilities"
			);

	public static List<String> expectedSystemMaintenanceSubTabs = Arrays.asList(
			//Omkar 30/8/2022 : Below options seem to have changed.Adding the current sub menu received for SystemMaintenanceSubTabs in QA3		
			/*"Users", "Roles", "Security Settings", "General Settings", "Customize Maintain Data",
			"Customize Task Lists", "Terminal Server Sessions", "Terminal Server Desktop"1*/
			
			"Users","Roles","Security Settings","General Settings","Customize Maintain Data","Customize Task Lists",
			"Terminal Server Sessions","Terminal Server Desktop","Launch Automation Engine","FHIR Client Test","HL7 Test","Automation Logs"
			);

	public static List<String> expectedStatusSubTabs = Arrays.asList(
			"Calculation Status", "Import/Export Status", "Utility Status"
			);

}
