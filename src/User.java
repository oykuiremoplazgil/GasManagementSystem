

public class User {
    private String username;
    private String password;
    private int role;
    public User(String username, String password, int role) {
        this.username = username;
        this.password = password;
        this.role = role;
    }


    public boolean login(String inputUsername, String inputPassword) {
        return this.username.equals(inputUsername) && this.password.equals(inputPassword);
    }

    public void roleEntry() {
        switch (role) {
            case 1:
                manageStation();
                break;
            case 2:
                processSales();
                break;
            case 3:
                performMaintenance();
                break;
            default:
                System.out.println("Invalid role!");
        }
    }

    private void manageStation() {
        System.out.println("Managing station: updating pump configuration, generating reports...");
    }

    private void processSales() {
        System.out.println("Processing fuel sales...");
    }

    private void performMaintenance() {
        System.out.println("Performing pump maintenance...");

    }
}

