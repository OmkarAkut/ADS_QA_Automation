package webdriver.users;

public enum Usersqa3 {
	AutomationTesterAdmin("aadmin", "password"),
	
	AutomationTester1("automationappadmin1", "password"),
    ApplicationAdministrator1("automationappadmin1", "password"),
    SystemAdministrator1("automationsysadmin1", "password"),
    SecurityAdministrator1("automationsecuradmin1", "password"),
    DataAdministrator1("automationdataadmin1", "password"),
    CostAnalyst1("automationcostanalyst1", "password"),
    CostingDepartmentManager1("automationcostdeptmanag1", "password"),
    ContractAnalyst1("automationcontraanalyst1", "password"),
    ContractAdministrator1("automationcontradmin1", "password"),
    ContractReviewer1("automationcontrreviewer1", "password"),
    EpisodeAnalyst1("automationepisanalyst1", "password"),
    ReportAdministrator1("automationrepadmin1", "password"),
    AdHocReportDesigner1("automationadhocrepdes1", "password"),
    ReportUser1("automationrepuser1", "password"),
    WebIntelligenceDesigner1("automationwebInteldesigner1", "password"),
    WebIntelligenceUser1("automationwebInteluser1", "password"),
    BudgetingUser1("automationbudgetuser1", "password"),
    AnalyticsAdministrator1("automationanalyticsadmin1", "password"),
    AnalyticsAnalyst1("automationanalyticsanalyst1", "password"),
    AnalyticsDesigner1("automationanalyticsdesign1", "password"),
    AnalyticsExecutive1("automationanalyticsexec1", "password"),
    //RestrictedEntities("newuser1", "password"),
    //RestrictedDepartments("newuser2", "password"),
    RestrictedEntityAndDept1("autorestrictedentityanddept1", "password"),  //this login added to qaauto only
    //dbadminUser1("automationdbadmin1", "password"),

    //Custom Roles
    CustomRoleWithUcqcAdded("autotest-customwithucqc", "password"),
    CustomRoleAllStandardRolesExceptUcqc("autotest-customallexceptucqc", "password"),
    AutoTestCostMgrXCostAnalyst("autotest-customwithucqc", "password");

    // ===== Note: Do not alter below this line ===== //
    private final String username;
    private final String password;
    Usersqa3(String username, String password) {
        this.username = username;
        this.password = password;
    }
    public String getUsername() { return username; }
    public String getPassword() { return password; }
}
