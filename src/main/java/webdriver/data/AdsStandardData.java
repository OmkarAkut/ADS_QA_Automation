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
      "Report Library", "Web Intelligence", "Ad Hoc Report Design", "Report Menu Maintenance",
      "Report Date Maintenance", "Report Publication", "Ad Hoc Business View Maintenance",
      "Web Intelligence Universe Maintenance", "ICD9/ICD10 GEMs Inquiry", "ICD9/ICD10 GEMs Analysis"
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
       "Users", "Roles", "Security Settings", "General Settings", "Customize Maintain Data",
       "Customize Task Lists", "Terminal Server Sessions", "Terminal Server Desktop"
  );

  public static List<String> expectedStatusSubTabs = Arrays.asList(
      "Calculation Status", "Import/Export Status", "Utility Status"
  );

}
