package isp.lab7.safehome;

public class Main {
    public static void main(String[] args) {
        DoorLockcontroller doorLockController = new DoorLockcontroller();
        try {
            doorLockController.addTenant("1234", "Sebi");
            doorLockController.addTenant("5678", "Borc");
        } catch (TenantAlreadyExistsException e) {
            System.out.println(e.getMessage());
        }
        try {
            doorLockController.removeTenant("Sebi");
        } catch (TenantNotFoundException e) {
            System.out.println(e.getMessage());
        }
        try {
            doorLockController.enterPin("5678");
        } catch (TooManyAttemptsException | InvalidPinException e) {
            System.out.println(e.getMessage());
        }
        try {
            doorLockController.enterPin("5678");
        }
        catch (TooManyAttemptsException| InvalidPinException e)
        {
            System.out.println(e.getMessage());
        }
        System.out.println("Access logs:");
        for (AccessLog log : doorLockController.getAceessLogs()) {
            System.out.println(log);
        }
    }
}

