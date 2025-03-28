package webdriver.users;

public enum Users {

//Add login test users for test framework - test users are called in the test scripts   
    
    /*Omkar 18/10/2024 : This user is not working in QAAPP so updating it based on other files 
     * such as UsersEvolve etc which has same roles
     *  AppSupportUser("eoadmin", "password"),
     * AutomationTester1("eolheiser", "password"),
	ApplicationAdministrator1("eolheiser", "password"), */
	 AppSupportUser("aadmin", "password"),
    AutomationTester1("automationappadmin1", "password"),
    ApplicationAdministrator1("automationappadmin1", "password"),
    SystemAdministrator1("automationsysadmin1", "password"),
    SecurityAdministrator1("automationsecuradmin1", "password"),
    /*
    Omkar 22/2/2024 : Someone seems to have changed the role while testing so adding back the 
                      role and changing the password as system does not allow with old password
    DataAdministrator1("automationdataadmin1", "password"), */
    DataAdministrator1("automationdataadmin1", "password"),
    CostAnalyst1("automationcostanalyst1", "password"),    
    /*Omkar 18/10/2024 : This user is not working in QAAPP so updating it based on other files 
     * such as UsersEvolve etc which has same roles
     * CostingDepartmentManager1("eolheiser", "password"),
	ContractAnalyst1("eolheiser", "password"), */
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
//    CustomRoleAllStandardRolesExceptUcqc("autotest-customallexceptucqc", "password"),
    CustomRoleAllStandardRolesExceptUcqc("autotestcustomallexceptucqc", "password"),

    AutoTestCostMgrXCostAnalyst("autotest-customwithucqc", "password"),
	
//****************added by Omkar*****************
	AutomationTesterAdmin("aadmin", "password");

    // ===== Note: Do not alter below this line ===== //
    private final String username;
    private final String password;
    Users(String username, String password) {
        this.username = username;
        this.password = password;
    }
    public String getUsername() { return username; }
    public String getPassword() { return password; }
}
