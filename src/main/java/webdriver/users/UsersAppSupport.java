package webdriver.users;

public enum UsersAppSupport {

    AppSupportUser("eoadmin", "password");

    // ===== Note: Do not alter below this line ===== //
    private final String username;
    private final String password;
    UsersAppSupport(String username, String password) {
        this.username = username;
        this.password = password;
    }
    public String getUsername() { return username; }
    public String getPassword() { return password; }
}
