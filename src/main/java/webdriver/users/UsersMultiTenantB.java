package webdriver.users;

public enum UsersMultiTenantB {

    AutomationTester1("tbrown", "password22");

    // ===== Note: Do not alter below this line ===== //
    private final String username;
    private final String password;
    UsersMultiTenantB(String username, String password) {
        this.username = username;
        this.password = password;
    }
    public String getUsername() { return username; }
    public String getPassword() { return password; }
}
