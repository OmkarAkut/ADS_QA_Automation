package webdriver.users;

public enum UsersMultiTenantA {

    AutomationTester1("tbrown", "password11");

    // ===== Note: Do not alter below this line ===== //
    private final String username;
    private final String password;
    UsersMultiTenantA(String username, String password) {
        this.username = username;
        this.password = password;
    }
    public String getUsername() { return username; }
    public String getPassword() { return password; }
}
