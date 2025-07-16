package webdriver.users;

public enum UsersEvolve {
    AutomationTester1("automationappadmin1", "password"),
    ApplicationAdministrator1("automationappadmin1", "password"),
    SystemAdministrator1("automationappadmin1", "password"),
    SecurityAdministrator1("automationappadmin1", "password"),
    /*
    Omkar 22/2/2024 : Someone seems to have changed the role while testing so adding back the 
                      role and changing the password as system does not allow with old password
    DataAdministrator1("automationdataadmin1", "password"), */
    DataAdministrator1("automationdataadmin1", "Pa$$w0rd"),
    CostAnalyst1("automationappadmin1", "password"),
    CostingDepartmentManager1("automationappadmin1", "password"),
    ContractAnalyst1("automationappadmin1", "password"),
    ContractAdministrator1("automationappadmin1", "password"),
    ContractReviewer1("automationappadmin1", "password"),
    EpisodeAnalyst1("automationappadmin1", "password"),
    ReportAdministrator1("automationappadmin1", "password"),
    AdHocReportDesigner1("automationappadmin1", "password"),
    ReportUser1("automationappadmin1", "password"),
    WebIntelligenceDesigner1("automationappadmin1", "password"),
    WebIntelligenceUser1("automationappadmin1", "password"),
    BudgetingUser1("automationappadmin1", "password"),
    AnalyticsAdministrator1("automationappadmin1", "password"),
    AnalyticsAnalyst1("automationappadmin1", "password"),
    AnalyticsDesigner1("automationappadmin1", "password"),
    AnalyticsExecutive1("automationappadmin1", "password"),
    //RestrictedEntities("newuser1", "password"),
    //RestrictedDepartments("newuser2", "password"),
    RestrictedEntityAndDept1("automationappadmin1", "password"),  //this login added to qaauto only
    //dbadminUser1("automationdbadmin1", "password"),

    //Custom Roles
    CustomRoleWithUcqcAdded("automationappadmin1", "password"),
    CustomRoleAllStandardRolesExceptUcqc("automationappadmin1", "password"),
    AutoTestCostMgrXCostAnalyst("automationappadmin1", "password"),
	
	//****************added by Omkar*****************
		AutomationTesterAdmin("admin", "password");
	//*********************************************


//    AutomationTester1("automationappadmin1", "password"),
//    ApplicationAdministrator1("automationappadmin1", "password"),
//    SystemAdministrator1("automationsysadmin1", "password"),
//    SecurityAdministrator1("automationsecuradmin1", "password"),
//    DataAdministrator1("automationdataadmin1", "password"),
//    CostAnalyst1("automationcostanalyst1", "password"),
//    CostingDepartmentManager1("automationcostdeptmanag1", "password"),
//    ContractAnalyst1("automationcontraanalyst1", "password"),
//    ContractAdministrator1("automationcontradmin1", "password"),
//    ContractReviewer1("automationcontrreviewer1", "password"),
//    EpisodeAnalyst1("automationepisanalyst1", "password"),
//    ReportAdministrator1("automationrepadmin1", "password"),
//    AdHocReportDesigner1("automationadhocrepdes1", "password"),
//    ReportUser1("automationrepuser1", "password"),
//    WebIntelligenceDesigner1("automationwebInteldesigner1", "password"),
//    WebIntelligenceUser1("automationwebInteluser1", "password"),
//    BudgetingUser1("automationbudgetuser1", "password"),
//    AnalyticsAdministrator1("automationanalyticsadmin1", "password"),
//    AnalyticsAnalyst1("automationanalyticsanalyst1", "password"),
//    AnalyticsDesigner1("automationanalyticsdesign1", "password"),
//    AnalyticsExecutive1("automationanalyticsexec1", "password"),
//    //RestrictedEntities("newuser1", "password"),
//    //RestrictedDepartments("newuser2", "password"),
//    RestrictedEntityAndDept1("autorestrictedentityanddept1", "password"),  //this login added to qaauto only
//    //dbadminUser1("automationdbadmin1", "password"),
//
//    //Custom Roles
//    CustomRoleWithUcqcAdded("autotest-customwithucqc", "password"),
//    CustomRoleAllStandardRolesExceptUcqc("autotest-customallexceptucqc", "password"),
//    AutoTestCostMgrXCostAnalyst("autotest-customwithucqc", "password");

    // ===== Note: Do not alter below this line ===== //
    private final String username;
    private final String password;
    UsersEvolve(String username, String password) {
        this.username = username;
        this.password = password;
    }
    public String getUsername() { return username; }
    public String getPassword() { return password; }
}
