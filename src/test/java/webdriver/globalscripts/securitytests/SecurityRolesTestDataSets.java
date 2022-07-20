package webdriver.globalscripts.securitytests;

public class SecurityRolesTestDataSets {

  //Application Main Tabs Expected Values Arrays (0=Not Displayed; 1=Displayed) per Standard Role
  public static Integer[] setExpectedTabResult(String userRole) {
    Integer[] expectedTabResult = new Integer[9];

    if (userRole.equals("Application_Administrator"))        { expectedTabResult = new Integer[]{ 1, 1, 1, 1, 1, 1, 1, 1, 1 }; }
    else if (userRole.equals("System_Administrator"))        { expectedTabResult = new Integer[]{ 1, 1, 0, 0, 0, 0, 0, 1, 1 }; }
    else if (userRole.equals("Security_Administrator"))      { expectedTabResult = new Integer[]{ 0, 1, 0, 0, 0, 0, 0, 1, 1 }; }

    else if (userRole.equals("Data_Administrator"))          { expectedTabResult = new Integer[]{ 0, 1, 0, 1, 0, 0, 1, 1, 1 }; }
    else if (userRole.equals("Cost_Analyst"))                { expectedTabResult = new Integer[]{ 0, 1, 1, 0, 0, 0, 1, 1, 1 }; }
    else if (userRole.equals("Costing_Department_Manager"))  { expectedTabResult = new Integer[]{ 0, 1, 1, 0, 0, 0, 0, 0, 1 }; }
    else if (userRole.equals("Contract_Analyst"))            { expectedTabResult = new Integer[]{ 0, 1, 0, 1, 0, 0, 1, 1, 1 }; }

    else if (userRole.equals("Contract_Administrator"))      { expectedTabResult = new Integer[]{ 0, 1, 0, 1, 0, 0, 1, 1, 1 }; }
    else if (userRole.equals("Contract_Reviewer"))           { expectedTabResult = new Integer[]{ 0, 1, 0, 0, 0, 0, 0, 0, 0 }; }
    else if (userRole.equals("Episode_Analyst"))             { expectedTabResult = new Integer[]{ 0, 1, 0, 0, 1, 0, 1, 1, 1 }; }
    else if (userRole.equals("Report_Administrator"))        { expectedTabResult = new Integer[]{ 0, 1, 0, 0, 0, 0, 0, 0, 0 }; }

    else if (userRole.equals("AdHoc_Report_Designer"))       { expectedTabResult = new Integer[]{ 0, 1, 0, 0, 0, 0, 0, 1, 0 }; }
    else if (userRole.equals("Report_User"))                 { expectedTabResult = new Integer[]{ 0, 1, 0, 0, 0, 0, 0, 0, 0 }; }
    else if (userRole.equals("Web_Intelligence_Designer"))   { expectedTabResult = new Integer[]{ 0, 1, 0, 0, 0, 0, 0, 0, 0 }; }
    else if (userRole.equals("Web_Intelligence_User"))       { expectedTabResult = new Integer[]{ 0, 1, 0, 0, 0, 0, 0, 0, 0 }; }

    else if (userRole.equals("Budgeting_User"))              { expectedTabResult = new Integer[]{ 0, 0, 0, 0, 0, 1, 0, 0, 0 }; }
    else if (userRole.equals("Analytics_Administrator"))     { expectedTabResult = new Integer[]{ 1, 0, 0, 0, 0, 0, 0, 0, 0 }; }
    else if (userRole.equals("Analytics_Analyst"))           { expectedTabResult = new Integer[]{ 1, 0, 0, 0, 0, 0, 0, 0, 0 }; }
    else if (userRole.equals("Analytics_Designer"))          { expectedTabResult = new Integer[]{ 1, 0, 0, 0, 0, 0, 0, 0, 0 }; }

    else if (userRole.equals("Analytics_Executive"))         { expectedTabResult = new Integer[]{ 1, 0, 0, 0, 0, 0, 0, 0, 0 }; }
    else { System.out.println("Role not found"); }
    return expectedTabResult;
  }


  public static Integer[] setExpectedAnalyticsTabResult(String userRole) {
    //Executive Dashboards, Analytic Dashboards, Analytic Refresh Scenarios, Analytics Administration,
    //Customize Analytics, Customize Analytics Sessions, Analytics Server Desktop, Analytics Server Desktop Sessions

    Integer[] expectedAnalyticsTabResult;
    if (userRole.equals("Application_Administrator"))        { expectedAnalyticsTabResult = new Integer[]{ 1, 1, 1, 1, 1, 1, 1, 1 }; }
    else if (userRole.equals("System_Administrator"))        { expectedAnalyticsTabResult = new Integer[]{ 0, 0, 0, 1, 0, 0, 1, 1 }; }
    else if (userRole.equals("Security_Administrator"))      { expectedAnalyticsTabResult = new Integer[]{ 0, 0, 0, 0, 0, 0, 0, 0 }; }

    else if (userRole.equals("Data_Administrator"))          { expectedAnalyticsTabResult = new Integer[]{ 0, 0, 0, 0, 0, 0, 0, 0 }; }
    else if (userRole.equals("Cost_Analyst"))                { expectedAnalyticsTabResult = new Integer[]{ 0, 0, 0, 0, 0, 0, 0, 0 }; }
    else if (userRole.equals("Costing_Department_Manager"))  { expectedAnalyticsTabResult = new Integer[]{ 0, 0, 0, 0, 0, 0, 0, 0 }; }
    else if (userRole.equals("Contract_Analyst"))            { expectedAnalyticsTabResult = new Integer[]{ 0, 0, 0, 0, 0, 0, 0, 0 }; }

    else if (userRole.equals("Contract_Administrator"))      { expectedAnalyticsTabResult = new Integer[]{ 0, 0, 0, 0, 0, 0, 0, 0 }; }
    else if (userRole.equals("Contract_Reviewer"))           { expectedAnalyticsTabResult = new Integer[]{ 0, 0, 0, 0, 0, 0, 0, 0 }; }
    else if (userRole.equals("Episode_Analyst"))             { expectedAnalyticsTabResult = new Integer[]{ 0, 0, 0, 0, 0, 0, 0, 0 }; }
    else if (userRole.equals("Report_Administrator"))        { expectedAnalyticsTabResult = new Integer[]{ 0, 0, 0, 0, 0, 0, 0, 0 }; }

    else if (userRole.equals("AdHoc_Report_Designer"))       { expectedAnalyticsTabResult = new Integer[]{ 0, 0, 0, 0, 0, 0, 0, 0 }; }
    else if (userRole.equals("Report_User"))                 { expectedAnalyticsTabResult = new Integer[]{ 0, 0, 0, 0, 0, 0, 0, 0 }; }
    else if (userRole.equals("Web_Intelligence_Designer"))   { expectedAnalyticsTabResult = new Integer[]{ 0, 0, 0, 0, 0, 0, 0, 0 }; }
    else if (userRole.equals("Web_Intelligence_User"))       { expectedAnalyticsTabResult = new Integer[]{ 0, 0, 0, 0, 0, 0, 0, 0 }; }

    else if (userRole.equals("Budgeting_User"))              { expectedAnalyticsTabResult = new Integer[]{ 0, 0, 0, 0, 0, 0, 0, 0 }; }
    else if (userRole.equals("Analytics_Administrator"))     { expectedAnalyticsTabResult = new Integer[]{ 0, 0, 1, 1, 0, 1, 1, 1 }; }
    else if (userRole.equals("Analytics_Analyst"))           { expectedAnalyticsTabResult = new Integer[]{ 1, 1, 0, 0, 0, 0, 0, 0 }; }
    else if (userRole.equals("Analytics_Designer"))          { expectedAnalyticsTabResult = new Integer[]{ 0, 0, 0, 0, 1, 1, 0, 0 }; }

    else if (userRole.equals("Analytics_Executive"))         { expectedAnalyticsTabResult = new Integer[]{ 1, 0, 0, 0, 0, 0, 0, 0 }; }
    else { expectedAnalyticsTabResult = new Integer[]{ 0, 0, 0, 0, 0, 0, 0, 0 }; }
    return expectedAnalyticsTabResult;
  }

  public static Integer[] setExpectedReportingTabResult(String userRole) {
    //Report Library, Web Intelligence, Ad Hoc Report Design, Report Menu Maintenance,
    //Report Date Maintenance, Report Publication, Ad Hoc Business View Maintenance, Web Intelligence Universe Maintenance
    //ICD9/ICD10 GEMs Inquiry, ICD9/ICD10 GEMs Analysis

    Integer[] expectedReportingTabResult = new Integer[10];
    if (userRole.equals("Application_Administrator"))        { expectedReportingTabResult = new Integer[]{ 1, 1, 1, 1, 1, 1, 1, 1, 1, 1 }; }
    else if (userRole.equals("System_Administrator"))        { expectedReportingTabResult = new Integer[]{ 1, 0, 0, 1, 1, 1, 1, 0, 0, 0 }; }
    else if (userRole.equals("Security_Administrator"))      { expectedReportingTabResult = new Integer[]{ 1, 0, 0, 0, 0, 0, 0, 0, 0, 0 }; }

    else if (userRole.equals("Data_Administrator"))          { expectedReportingTabResult = new Integer[]{ 1, 0, 0, 0, 0, 1, 1, 0, 0, 0 }; }
    else if (userRole.equals("Cost_Analyst"))                { expectedReportingTabResult = new Integer[]{ 1, 0, 0, 0, 0, 1, 0, 0, 0, 0 }; }
    else if (userRole.equals("Costing_Department_Manager"))  { expectedReportingTabResult = new Integer[]{ 1, 0, 0, 0, 0, 0, 0, 0, 0, 0 }; }
    else if (userRole.equals("Contract_Analyst"))            { expectedReportingTabResult = new Integer[]{ 1, 0, 0, 0, 0, 1, 0, 0, 1, 1 }; }
//
    else if (userRole.equals("Contract_Administrator"))      { expectedReportingTabResult = new Integer[]{ 1, 0, 0, 0, 0, 1, 0, 0, 1, 1 }; }
    else if (userRole.equals("Contract_Reviewer"))           { expectedReportingTabResult = new Integer[]{ 1, 0, 0, 0, 0, 0, 0, 0, 1, 1 }; }
    else if (userRole.equals("Episode_Analyst"))             { expectedReportingTabResult = new Integer[]{ 1, 0, 0, 0, 0, 1, 0, 0, 0, 0 }; }
    else if (userRole.equals("Report_Administrator"))        { expectedReportingTabResult = new Integer[]{ 0, 0, 0, 1, 1, 1, 1, 1, 0, 0 }; }
//
    else if (userRole.equals("AdHoc_Report_Designer"))       { expectedReportingTabResult = new Integer[]{ 0, 0, 1, 0, 0, 1, 1, 0, 0, 0 }; }
    else if (userRole.equals("Report_User"))                 { expectedReportingTabResult = new Integer[]{ 1, 0, 0, 0, 0, 0, 0, 0, 1, 1 }; }
    else if (userRole.equals("Web_Intelligence_Designer"))   { expectedReportingTabResult = new Integer[]{ 0, 1, 0, 0, 0, 0, 0, 0, 0, 0 }; }
    else if (userRole.equals("Web_Intelligence_User"))       { expectedReportingTabResult = new Integer[]{ 0, 1, 0, 0, 0, 0, 0, 0, 0, 0 }; }
//
    else if (userRole.equals("Budgeting_User"))              { expectedReportingTabResult = new Integer[]{ 0, 0, 0, 0, 0, 0, 0, 0, 0, 0 }; }
    else if (userRole.equals("Analytics_Administrator"))     { expectedReportingTabResult = new Integer[]{ 0, 0, 0, 0, 0, 0, 0, 0, 0, 0 }; }
    else if (userRole.equals("Analytics_Analyst"))           { expectedReportingTabResult = new Integer[]{ 0, 0, 0, 0, 0, 0, 0, 0, 0, 0 }; }
    else if (userRole.equals("Analytics_Designer"))          { expectedReportingTabResult = new Integer[]{ 0, 0, 0, 0, 0, 0, 0, 0, 0, 0 }; }

    else if (userRole.equals("Analytics_Executive"))         { expectedReportingTabResult = new Integer[]{ 0, 0, 0, 0, 0, 0, 0, 0, 0, 0 }; }
    else { expectedReportingTabResult = new Integer[]{ 0, 0, 0, 0, 0, 0, 0, 0, 0, 0 }; }
    return expectedReportingTabResult;
  }

  public static Integer[] setExpectedCostingTabResult(String userRole) {
    //Costing Models, Costing Data Maintenance, RVU Maintenance, Cost Model Scenario Calculation, Unit Cost Quick Calculation

    Integer[] expectedCostingTabResult = new Integer[5];
    if (userRole.equals("Application_Administrator"))        { expectedCostingTabResult = new Integer[]{ 1, 1, 1, 1, 1 }; }
    else if (userRole.equals("System_Administrator"))        { expectedCostingTabResult = new Integer[]{ 0, 0, 0, 0, 0 }; }
    else if (userRole.equals("Security_Administrator"))      { expectedCostingTabResult = new Integer[]{ 0, 0, 0, 0, 0 }; }

    else if (userRole.equals("Data_Administrator"))          { expectedCostingTabResult = new Integer[]{ 0, 0, 0, 0, 0 }; }
    else if (userRole.equals("Cost_Analyst"))                { expectedCostingTabResult = new Integer[]{ 1, 1, 0, 0, 0 }; }
    else if (userRole.equals("Costing_Department_Manager"))  { expectedCostingTabResult = new Integer[]{ 0, 0, 1, 1, 1 }; }
    else if (userRole.equals("Contract_Analyst"))            { expectedCostingTabResult = new Integer[]{ 0, 0, 0, 0, 0 }; }
//
    else if (userRole.equals("Contract_Administrator"))      { expectedCostingTabResult = new Integer[]{ 0, 0, 0, 0, 0 }; }
    else if (userRole.equals("Contract_Reviewer"))           { expectedCostingTabResult = new Integer[]{ 0, 0, 0, 0, 0 }; }
    else if (userRole.equals("Episode_Analyst"))             { expectedCostingTabResult = new Integer[]{ 0, 0, 0, 0, 0 }; }
    else if (userRole.equals("Report_Administrator"))        { expectedCostingTabResult = new Integer[]{ 0, 0, 0, 0, 0 }; }
//
    else if (userRole.equals("AdHoc_Report_Designer"))       { expectedCostingTabResult = new Integer[]{ 0, 0, 0, 0, 0 }; }
    else if (userRole.equals("Report_User"))                 { expectedCostingTabResult = new Integer[]{ 0, 0, 0, 0, 0 }; }
    else if (userRole.equals("Web_Intelligence_Designer"))   { expectedCostingTabResult = new Integer[]{ 0, 0, 0, 0, 0 }; }
    else if (userRole.equals("Web_Intelligence_User"))       { expectedCostingTabResult = new Integer[]{ 0, 0, 0, 0, 0 }; }
//
    else if (userRole.equals("Budgeting_User"))              { expectedCostingTabResult = new Integer[]{ 0, 0, 0, 0, 0 }; }
    else if (userRole.equals("Analytics_Administrator"))     { expectedCostingTabResult = new Integer[]{ 0, 0, 0, 0, 0 }; }
    else if (userRole.equals("Analytics_Analyst"))           { expectedCostingTabResult = new Integer[]{ 0, 0, 0, 0, 0 }; }
    else if (userRole.equals("Analytics_Designer"))          { expectedCostingTabResult = new Integer[]{ 0, 0, 0, 0, 0 }; }

    else if (userRole.equals("Analytics_Executive"))         { expectedCostingTabResult = new Integer[]{ 0, 0, 0, 0, 0 }; }
    else { expectedCostingTabResult = new Integer[]{ 0, 0, 0, 0, 0 }; }
    return expectedCostingTabResult;
  }

  public static Integer[] setExpectedContractingTabResult(String userRole) {
    //Contract Models, Contracting Data Maintenance, Contractual Allowance Export, APC Allocation

    Integer[] expectedContractingTabResult = new Integer[4];
    if (userRole.equals("Application_Administrator"))        { expectedContractingTabResult = new Integer[]{ 1, 1, 1, 1 }; }
    else if (userRole.equals("System_Administrator"))        { expectedContractingTabResult = new Integer[]{ 0, 0, 0, 0 }; }
    else if (userRole.equals("Security_Administrator"))      { expectedContractingTabResult = new Integer[]{ 0, 0, 0, 0 }; }
//
    else if (userRole.equals("Data_Administrator"))          { expectedContractingTabResult = new Integer[]{ 0, 0, 0, 1 }; }
    else if (userRole.equals("Cost_Analyst"))                { expectedContractingTabResult = new Integer[]{ 0, 0, 0, 0 }; }
    else if (userRole.equals("Costing_Department_Manager"))  { expectedContractingTabResult = new Integer[]{ 0, 0, 0, 0 }; }
    else if (userRole.equals("Contract_Analyst"))            { expectedContractingTabResult = new Integer[]{ 1, 1, 1, 1 }; }
//
    else if (userRole.equals("Contract_Administrator"))      { expectedContractingTabResult = new Integer[]{ 1, 1, 0, 0 }; }
    else if (userRole.equals("Contract_Reviewer"))           { expectedContractingTabResult = new Integer[]{ 0, 0, 0, 0 }; }
    else if (userRole.equals("Episode_Analyst"))             { expectedContractingTabResult = new Integer[]{ 0, 0, 0, 0 }; }
    else if (userRole.equals("Report_Administrator"))        { expectedContractingTabResult = new Integer[]{ 0, 0, 0, 0 }; }
//
    else if (userRole.equals("AdHoc_Report_Designer"))       { expectedContractingTabResult = new Integer[]{ 0, 0, 0, 0 }; }
    else if (userRole.equals("Report_User"))                 { expectedContractingTabResult = new Integer[]{ 0, 0, 0, 0 }; }
    else if (userRole.equals("Web_Intelligence_Designer"))   { expectedContractingTabResult = new Integer[]{ 0, 0, 0, 0 }; }
    else if (userRole.equals("Web_Intelligence_User"))       { expectedContractingTabResult = new Integer[]{ 0, 0, 0, 0 }; }
//
    else if (userRole.equals("Budgeting_User"))              { expectedContractingTabResult = new Integer[]{ 0, 0, 0, 0 }; }
    else if (userRole.equals("Analytics_Administrator"))     { expectedContractingTabResult = new Integer[]{ 0, 0, 0, 0 }; }
    else if (userRole.equals("Analytics_Analyst"))           { expectedContractingTabResult = new Integer[]{ 0, 0, 0, 0 }; }
    else if (userRole.equals("Analytics_Designer"))          { expectedContractingTabResult = new Integer[]{ 0, 0, 0, 0 }; }

    else if (userRole.equals("Analytics_Executive"))         { expectedContractingTabResult = new Integer[]{ 0, 0, 0, 0 }; }
    else { expectedContractingTabResult = new Integer[]{ 0, 0, 0, 0 }; }
    return expectedContractingTabResult;
  }

  public static Integer[] setExpectedEpisodesTabResult(String userRole) {
    //Episode Models, Episode Data Maintenance

    Integer[] expectedEpisodesTabResult = new Integer[2];
    if (userRole.equals("Application_Administrator"))        { expectedEpisodesTabResult = new Integer[]{ 1, 1 }; }
    else if (userRole.equals("System_Administrator"))        { expectedEpisodesTabResult = new Integer[]{ 0, 0 }; }
    else if (userRole.equals("Security_Administrator"))      { expectedEpisodesTabResult = new Integer[]{ 0, 0 }; }
//
    else if (userRole.equals("Data_Administrator"))          { expectedEpisodesTabResult = new Integer[]{ 0, 0 }; }
    else if (userRole.equals("Cost_Analyst"))                { expectedEpisodesTabResult = new Integer[]{ 0, 0 }; }
    else if (userRole.equals("Costing_Department_Manager"))  { expectedEpisodesTabResult = new Integer[]{ 0, 0 }; }
    else if (userRole.equals("Contract_Analyst"))            { expectedEpisodesTabResult = new Integer[]{ 0, 0 }; }
//
    else if (userRole.equals("Contract_Administrator"))      { expectedEpisodesTabResult = new Integer[]{ 0, 0 }; }
    else if (userRole.equals("Contract_Reviewer"))           { expectedEpisodesTabResult = new Integer[]{ 0, 0 }; }
    else if (userRole.equals("Episode_Analyst"))             { expectedEpisodesTabResult = new Integer[]{ 1, 1 }; }
    else if (userRole.equals("Report_Administrator"))        { expectedEpisodesTabResult = new Integer[]{ 0, 0 }; }
//
    else if (userRole.equals("AdHoc_Report_Designer"))       { expectedEpisodesTabResult = new Integer[]{ 0, 0 }; }
    else if (userRole.equals("Report_User"))                 { expectedEpisodesTabResult = new Integer[]{ 0, 0 }; }
    else if (userRole.equals("Web_Intelligence_Designer"))   { expectedEpisodesTabResult = new Integer[]{ 0, 0 }; }
    else if (userRole.equals("Web_Intelligence_User"))       { expectedEpisodesTabResult = new Integer[]{ 0, 0 }; }
//
    else if (userRole.equals("Budgeting_User"))              { expectedEpisodesTabResult = new Integer[]{ 0, 0 }; }
    else if (userRole.equals("Analytics_Administrator"))     { expectedEpisodesTabResult = new Integer[]{ 0, 0 }; }
    else if (userRole.equals("Analytics_Analyst"))           { expectedEpisodesTabResult = new Integer[]{ 0, 0 }; }
    else if (userRole.equals("Analytics_Designer"))          { expectedEpisodesTabResult = new Integer[]{ 0, 0 }; }

    else if (userRole.equals("Analytics_Executive"))         { expectedEpisodesTabResult = new Integer[]{ 0, 0 }; }
    else { expectedEpisodesTabResult = new Integer[]{ 0, 0 }; }
    return expectedEpisodesTabResult;
  }

  public static Integer[] setExpectedBudgetingTabResult(String userRole) {
    //Budgeting

    Integer[] expectedBudgetingTabResult = new Integer[1];
    if (userRole.equals("Application_Administrator"))        { expectedBudgetingTabResult = new Integer[]{ 1 }; }
    else if (userRole.equals("System_Administrator"))        { expectedBudgetingTabResult = new Integer[]{ 0 }; }
    else if (userRole.equals("Security_Administrator"))      { expectedBudgetingTabResult = new Integer[]{ 0 }; }
//
    else if (userRole.equals("Data_Administrator"))          { expectedBudgetingTabResult = new Integer[]{ 0 }; }
    else if (userRole.equals("Cost_Analyst"))                { expectedBudgetingTabResult = new Integer[]{ 0 }; }
    else if (userRole.equals("Costing_Department_Manager"))  { expectedBudgetingTabResult = new Integer[]{ 0 }; }
    else if (userRole.equals("Contract_Analyst"))            { expectedBudgetingTabResult = new Integer[]{ 0 }; }
//
    else if (userRole.equals("Contract_Administrator"))      { expectedBudgetingTabResult = new Integer[]{ 0 }; }
    else if (userRole.equals("Contract_Reviewer"))           { expectedBudgetingTabResult = new Integer[]{ 0 }; }
    else if (userRole.equals("Episode_Analyst"))             { expectedBudgetingTabResult = new Integer[]{ 0 }; }
    else if (userRole.equals("Report_Administrator"))        { expectedBudgetingTabResult = new Integer[]{ 0 }; }
//
    else if (userRole.equals("AdHoc_Report_Designer"))       { expectedBudgetingTabResult = new Integer[]{ 0 }; }
    else if (userRole.equals("Report_User"))                 { expectedBudgetingTabResult = new Integer[]{ 0 }; }
    else if (userRole.equals("Web_Intelligence_Designer"))   { expectedBudgetingTabResult = new Integer[]{ 0 }; }
    else if (userRole.equals("Web_Intelligence_User"))       { expectedBudgetingTabResult = new Integer[]{ 0 }; }
//
    else if (userRole.equals("Budgeting_User"))              { expectedBudgetingTabResult = new Integer[]{ 1 }; }
    else if (userRole.equals("Analytics_Administrator"))     { expectedBudgetingTabResult = new Integer[]{ 0 }; }
    else if (userRole.equals("Analytics_Analyst"))           { expectedBudgetingTabResult = new Integer[]{ 0 }; }
    else if (userRole.equals("Analytics_Designer"))          { expectedBudgetingTabResult = new Integer[]{ 0 }; }

    else if (userRole.equals("Analytics_Executive"))         { expectedBudgetingTabResult = new Integer[]{ 0 }; }
    else { expectedBudgetingTabResult = new Integer[]{ 0 }; }
    return expectedBudgetingTabResult;
  }

  public static Integer[] setExpectedDataMaintenanceTabResult(String userRole) {
    //Maintain Data, Load Data, Utilities

    Integer[] expectedDataMaintenanceTabResult = new Integer[8];
    if (userRole.equals("Application_Administrator"))        { expectedDataMaintenanceTabResult = new Integer[]{ 1, 1, 1 }; }
    else if (userRole.equals("System_Administrator"))        { expectedDataMaintenanceTabResult = new Integer[]{ 0, 0, 0 }; }
    else if (userRole.equals("Security_Administrator"))      { expectedDataMaintenanceTabResult = new Integer[]{ 0, 0, 0 }; }
//
    else if (userRole.equals("Data_Administrator"))          { expectedDataMaintenanceTabResult = new Integer[]{ 1, 1, 0 }; }
    else if (userRole.equals("Cost_Analyst"))                { expectedDataMaintenanceTabResult = new Integer[]{ 1, 0, 0 }; }
    else if (userRole.equals("Costing_Department_Manager"))  { expectedDataMaintenanceTabResult = new Integer[]{ 0, 0, 0 }; }
    else if (userRole.equals("Contract_Analyst"))            { expectedDataMaintenanceTabResult = new Integer[]{ 1, 0, 1 }; }
//
    else if (userRole.equals("Contract_Administrator"))      { expectedDataMaintenanceTabResult = new Integer[]{ 1, 0, 0 }; }
    else if (userRole.equals("Contract_Reviewer"))           { expectedDataMaintenanceTabResult = new Integer[]{ 0, 0, 0 }; }
    else if (userRole.equals("Episode_Analyst"))             { expectedDataMaintenanceTabResult = new Integer[]{ 1, 0, 0 }; }
    else if (userRole.equals("Report_Administrator"))        { expectedDataMaintenanceTabResult = new Integer[]{ 0, 0, 0 }; }
//
    else if (userRole.equals("AdHoc_Report_Designer"))       { expectedDataMaintenanceTabResult = new Integer[]{ 0, 0, 0 }; }
    else if (userRole.equals("Report_User"))                 { expectedDataMaintenanceTabResult = new Integer[]{ 0, 0, 0 }; }
    else if (userRole.equals("Web_Intelligence_Designer"))   { expectedDataMaintenanceTabResult = new Integer[]{ 0, 0, 0 }; }
    else if (userRole.equals("Web_Intelligence_User"))       { expectedDataMaintenanceTabResult = new Integer[]{ 0, 0, 0 }; }
//
    else if (userRole.equals("Budgeting_User"))              { expectedDataMaintenanceTabResult = new Integer[]{ 0, 0, 0 }; }
    else if (userRole.equals("Analytics_Administrator"))     { expectedDataMaintenanceTabResult = new Integer[]{ 0, 0, 0 }; }
    else if (userRole.equals("Analytics_Analyst"))           { expectedDataMaintenanceTabResult = new Integer[]{ 0, 0, 0 }; }
    else if (userRole.equals("Analytics_Designer"))          { expectedDataMaintenanceTabResult = new Integer[]{ 0, 0, 0 }; }

    else if (userRole.equals("Analytics_Executive"))         { expectedDataMaintenanceTabResult = new Integer[]{ 0, 0, 0 }; }
    else { expectedDataMaintenanceTabResult = new Integer[]{ 0, 0, 0 }; }
    return expectedDataMaintenanceTabResult;
  }


  public static Integer[] setExpectedMaintenanceTabResult(String userRole) {
    //Users, Roles, Security Settings, General Settings
    //Customize Maintain Data, Customize Task Lists, Terminal Server Sessions, Terminal Server Desktop

    Integer[] expectedMaintenanceTabResult = new Integer[8];
    if (userRole.equals("Application_Administrator"))        { expectedMaintenanceTabResult = new Integer[]{ 1, 1, 1, 1, 1, 1, 1, 1 }; }
    else if (userRole.equals("System_Administrator"))        { expectedMaintenanceTabResult = new Integer[]{ 0, 0, 0, 1, 1, 1, 1, 1 }; }
    else if (userRole.equals("Security_Administrator"))      { expectedMaintenanceTabResult = new Integer[]{ 1, 1, 1, 0, 0, 0, 1, 0 }; }
//
    else if (userRole.equals("Data_Administrator"))          { expectedMaintenanceTabResult = new Integer[]{ 0, 0, 0, 0, 0, 0, 1, 1 }; }
    else if (userRole.equals("Cost_Analyst"))                { expectedMaintenanceTabResult = new Integer[]{ 0, 0, 0, 0, 0, 0, 1, 1 }; }
    else if (userRole.equals("Costing_Department_Manager"))  { expectedMaintenanceTabResult = new Integer[]{ 0, 0, 0, 0, 0, 0, 0, 0 }; }
    else if (userRole.equals("Contract_Analyst"))            { expectedMaintenanceTabResult = new Integer[]{ 0, 0, 0, 0, 0, 0, 1, 1 }; }
//
    else if (userRole.equals("Contract_Administrator"))      { expectedMaintenanceTabResult = new Integer[]{ 0, 0, 0, 0, 0, 0, 1, 1 }; }
    else if (userRole.equals("Contract_Reviewer"))           { expectedMaintenanceTabResult = new Integer[]{ 0, 0, 0, 0, 0, 0, 0, 0 }; }
    else if (userRole.equals("Episode_Analyst"))             { expectedMaintenanceTabResult = new Integer[]{ 0, 0, 0, 0, 0, 0, 1, 1 }; }
    else if (userRole.equals("Report_Administrator"))        { expectedMaintenanceTabResult = new Integer[]{ 0, 0, 0, 0, 0, 0, 0, 0 }; }
//
    else if (userRole.equals("AdHoc_Report_Designer"))       { expectedMaintenanceTabResult = new Integer[]{ 0, 0, 0, 0, 0, 0, 1, 1 }; }
    else if (userRole.equals("Report_User"))                 { expectedMaintenanceTabResult = new Integer[]{ 0, 0, 0, 0, 0, 0, 0, 0 }; }
    else if (userRole.equals("Web_Intelligence_Designer"))   { expectedMaintenanceTabResult = new Integer[]{ 0, 0, 0, 0, 0, 0, 0, 0 }; }
    else if (userRole.equals("Web_Intelligence_User"))       { expectedMaintenanceTabResult = new Integer[]{ 0, 0, 0, 0, 0, 0, 0, 0 }; }
//
    else if (userRole.equals("Budgeting_User"))              { expectedMaintenanceTabResult = new Integer[]{ 0, 0, 0, 0, 0, 0, 0, 0 }; }
    else if (userRole.equals("Analytics_Administrator"))     { expectedMaintenanceTabResult = new Integer[]{ 0, 0, 0, 0, 0, 0, 0, 0 }; }
    else if (userRole.equals("Analytics_Analyst"))           { expectedMaintenanceTabResult = new Integer[]{ 0, 0, 0, 0, 0, 0, 0, 0 }; }
    else if (userRole.equals("Analytics_Designer"))          { expectedMaintenanceTabResult = new Integer[]{ 0, 0, 0, 0, 0, 0, 0, 0 }; }

    else if (userRole.equals("Analytics_Executive"))         { expectedMaintenanceTabResult = new Integer[]{ 0, 0, 0, 0, 0, 0, 0, 0 }; }
    else { expectedMaintenanceTabResult = new Integer[]{ 0, 0, 0, 0, 0, 0, 0, 0 }; }
    return expectedMaintenanceTabResult;
  }



  public static Integer[] setExpectedStatusTabResult(String userRole) {
    //Calculation Status, Import/Export Status, Utility Status

    Integer[] expectedStatusTabResult = new Integer[3];
    if (userRole.equals("Application_Administrator"))        { expectedStatusTabResult = new Integer[]{ 1, 1, 1 }; }
    else if (userRole.equals("System_Administrator"))        { expectedStatusTabResult = new Integer[]{ 0, 0, 1 }; }
    else if (userRole.equals("Security_Administrator"))      { expectedStatusTabResult = new Integer[]{ 0, 1, 0 }; }
//
    else if (userRole.equals("Data_Administrator"))          { expectedStatusTabResult = new Integer[]{ 1, 1, 0 }; }
    else if (userRole.equals("Cost_Analyst"))                { expectedStatusTabResult = new Integer[]{ 1, 1, 0 }; }
    else if (userRole.equals("Costing_Department_Manager"))  { expectedStatusTabResult = new Integer[]{ 1, 1, 0 }; }
    else if (userRole.equals("Contract_Analyst"))            { expectedStatusTabResult = new Integer[]{ 1, 1, 1 }; }
//
    else if (userRole.equals("Contract_Administrator"))      { expectedStatusTabResult = new Integer[]{ 1, 1, 0 }; }
    else if (userRole.equals("Contract_Reviewer"))           { expectedStatusTabResult = new Integer[]{ 0, 0, 0 }; }
    else if (userRole.equals("Episode_Analyst"))             { expectedStatusTabResult = new Integer[]{ 1, 1, 0 }; }
    else if (userRole.equals("Report_Administrator"))        { expectedStatusTabResult = new Integer[]{ 0, 0, 0 }; }
//
    else if (userRole.equals("AdHoc_Report_Designer"))       { expectedStatusTabResult = new Integer[]{ 0, 0, 0 }; }
    else if (userRole.equals("Report_User"))                 { expectedStatusTabResult = new Integer[]{ 0, 0, 0 }; }
    else if (userRole.equals("Web_Intelligence_Designer"))   { expectedStatusTabResult = new Integer[]{ 0, 0, 0 }; }
    else if (userRole.equals("Web_Intelligence_User"))       { expectedStatusTabResult = new Integer[]{ 0, 0, 0 }; }
//
    else if (userRole.equals("Budgeting_User"))              { expectedStatusTabResult = new Integer[]{ 0, 0, 0 }; }
    else if (userRole.equals("Analytics_Administrator"))     { expectedStatusTabResult = new Integer[]{ 0, 0, 0 }; }
    else if (userRole.equals("Analytics_Analyst"))           { expectedStatusTabResult = new Integer[]{ 0, 0, 0 }; }
    else if (userRole.equals("Analytics_Designer"))          { expectedStatusTabResult = new Integer[]{ 0, 0, 0 }; }

    else if (userRole.equals("Analytics_Executive"))         { expectedStatusTabResult = new Integer[]{ 0, 0, 0 }; }
    else { expectedStatusTabResult = new Integer[]{ 0, 0, 0 }; }
    return expectedStatusTabResult;
  }

}
