package webdriver.users;

public enum Users {

//Add login test users for test framework - test users are called in the test scripts   
    
    /*Omkar 18/10/2024 : This user is not working in QAAPP so updating it based on other files 
     * such as UsersEvolve etc which has same roles
     *  AppSupportUser("eoadmin", "password"),
     * AutomationTester1("eolheiser", "password"),
	ApplicationAdministrator1("eolheiser", "password"), */
	 AppSupportUser("aadmin", "password"),
    AutomationTester1("autoappadmin1", "P@$$w0rd123"),
    ApplicationAdministrator1("autoappadmin1", "P@$$w0rd123"),
    SystemAdministrator1("autosysadmin1", "P@$$w0rd123"),
    SecurityAdministrator1("autosecuradmin1", "P@$$w0rd123"),
    /*
    Omkar 22/2/2024 : Someone seems to have changed the role while testing so adding back the 
                      role and changing the password as system does not allow with old password
    DataAdministrator1("automationdataadmin1", "password"), */
    DataAdministrator1("autodataadmin1", "P@$$w0rd123"),
    CostAnalyst1("autocostanalyst1", "P@$$w0rd123"),    
    /*Omkar 18/10/2024 : This user is not working in QAAPP so updating it based on other files 
     * such as UsersEvolve etc which has same roles
     * CostingDepartmentManager1("eolheiser", "password"),
	ContractAnalyst1("eolheiser", "password"), */
    CostingDepartmentManager1("autocostdeptmanag1", "P@$$w0rd123"),
    ContractAnalyst1("autocontraanalyst1", "P@$$w0rd123"),
    ContractAdministrator1("autocontradmin1", "P@$$w0rd123"),
    ContractReviewer1("autocontrreviewer1", "P@$$w0rd123"),
    EpisodeAnalyst1("autoepisanalyst1", "P@$$w0rd123"),
    ReportAdministrator1("automationrepadmin1", "P@$$w0rd123"),
    AdHocReportDesigner1("autoadhocrepdes1", "P@$$w0rd123"),
    ReportUser1("autorepuser1", "P@$$w0rd123"),
    WebIntelligenceDesigner1("autowebIdesigner1", "P@$$w0rd123"),
    WebIntelligenceUser1("autowebIuser1", "P@$$w0rd123"),
    BudgetingUser1("autobudgetuser1", "P@$$w0rd123"),
    AnalyticsAdministrator1("autoanalyadmin1", "P@$$w0rd123"),
    AnalyticsAnalyst1("autoanalyanalyst1", "P@$$w0rd123"),
    AnalyticsDesigner1("automationanalyticsdesign1", "P@$$w0rd123"),
    AnalyticsExecutive1("autoanalyticsexec1", "P@$$w0rd123"),
    //RestrictedEntities("newuser1", "password"),
    //RestrictedDepartments("newuser2", "password"),
    RestrictedEntityAndDept1("autorestdentanddept1", "P@$$w0rd123"),  //this login added to qaauto only
    //dbadminUser1("automationdbadmin1", "password"),
    //Custom Roles
    CustomRoleWithUcqcAdded("auto-custwithucqc", "P@$$w0rd123"),
//    CustomRoleAllStandardRolesExceptUcqc("autotest-customallexceptucqc", "password"),
    CustomRoleAllStandardRolesExceptUcqc("auto-custallexcucqc", "P@$$w0rd123"),

    AutoTestCostMgrXCostAnalyst("auto-custwithucqc", "P@$$w0rd123"),
	
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
