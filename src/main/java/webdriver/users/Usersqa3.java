package webdriver.users;

public enum Usersqa3 {
	AutomationTesterAdmin("aadmin", "password"),
	AutomationTester1("autoappadmin1", "P@$$w0rd123"),
	AutomationTester2("autoappadmin1", "P@$$w0rd123"),
    ApplicationAdministrator1("autoappadmin1", "P@$$w0rd123"),
    SystemAdministrator1("autosysadmin1", "P@$$w0rd123"),
    SecurityAdministrator1("autosecuradmin1", "P@$$w0rd123"),
    /*
    Omkar 22/2/2024 : Someone seems to have changed the role while testing so adding back the 
                      role and changing the password as system does not allow with old password
    DataAdministrator1("automationdataadmin1", "password"), */
    DataAdministrator1("autodataadmin1", "P@$$w0rd123"),
    CostAnalyst1("autocostanalyst1", "P@$$w0rd123"),
    CostingDepartmentManager1("autocostdeptmanag1", "P@$$w0rd123"),
    ContractAnalyst1("autocontraanalyst1", "P@$$w0rd123"),
    ContractAdministrator1("autocontradmin1", "P@$$w0rd123"),
    ContractReviewer1("autocontrreviewer1", "P@$$w0rd123"),
    EpisodeAnalyst1("autoepisanalyst1", "P@$$w0rd123"),
    ReportAdministrator1("autorepadmin1", "P@$$w0rd123"),
    AdHocReportDesigner1("autoadhocrepdes1", "P@$$w0rd123"),
    ReportUser1("autorepuser1", "P@$$w0rd123"),
    WebIntelligenceDesigner1("autowebIdesigner1", "P@$$w0rd123"),
    WebIntelligenceUser1("autowebIuser1", "P@$$w0rd123"),
    BudgetingUser1("autobudgetuser1", "P@$$w0rd123"),
    AnalyticsAdministrator1("autoanalyadmin1", "P@$$w0rd123"),
    AnalyticsAnalyst1("autoanalyanalyst1", "P@$$w0rd123"),
    AnalyticsDesigner1("automationanalyticsdesign1", "password"),
    AnalyticsExecutive1("autoanalyticsexec1", "P@$$w0rd123"),
    //RestrictedEntities("newuser1", "password"),
    //RestrictedDepartments("newuser2", "password"),
    RestrictedEntityAndDept1("autorestdentanddept1", "P@$$w0rd123"),  //this login added to qaauto only
    //dbadminUser1("automationdbadmin1", "password"),

    //Custom Roles
    CustomRoleWithUcqcAdded("auto-custwithucqc", "P@$$w0rd123"),
    CustomRoleAllStandardRolesExceptUcqc("auto-custallexcucqc", "P@$$w0rd123"),
    AutoTestCostMgrXCostAnalyst("auto-custwithucqc", "P@$$w0rd123");

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
