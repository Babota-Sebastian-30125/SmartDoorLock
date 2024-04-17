package isp.lab7.safehome;

import java.time.LocalDateTime;
import java.util.*;

public class DoorLockcontroller implements ControllerInterface {
    private Door door;
    private AccessLog accessLog;
    Scanner scanner = new Scanner(System.in);
    HashMap<Tenant,AccessKey> validAccess = new HashMap<>();
    ArrayList<AccessLog> logs = new ArrayList<>();
    int counter = 0;
    String key;
    public DoorLockcontroller()
    {
        this.door = new Door();
        validAccess.put(new Tenant(ControllerInterface.MASTER_TENANT_NAME),new AccessKey(ControllerInterface.MASTER_KEY));
    }
    @Override
    public DoorStatus enterPin(String pin) throws TooManyAttemptsException, InvalidPinException {
        while (counter < 3) {
            System.out.println("Enter a Key: ");
            key = scanner.nextLine();

            if (Objects.equals(key, pin)) {
                if (door.getStatus() == DoorStatus.OPEN) {
                    AccessLog lockdoor = new AccessLog(LocalDateTime.now(),"close door",door.getStatus());
                    logs.add(lockdoor);
                    door.lockDoor();
                } else {
                    AccessLog unlockdoor = new AccessLog(LocalDateTime.now(),"open door",door.getStatus());
                    logs.add(unlockdoor);
                    door.unlockDoor();
                }
                return door.getStatus();
            } else {
                counter++;
                if (counter >= 3) {
                    throw new TooManyAttemptsException("Too many attempts");
                }
            }
        }
        throw new InvalidPinException("The pin is incorrect");
    }


    @Override
    public void addTenant(String pin, String name) throws TenantAlreadyExistsException {
        for(Tenant t : validAccess.keySet())
        if(Objects.equals(t.getName(), name))
        {
          throw new TenantAlreadyExistsException("Tenant with the name " + name +" already exists");
        }
        Tenant newTenant = new Tenant(name);
        AccessKey newAccessKey = new AccessKey(pin);
        AccessLog addTenant = new AccessLog(newTenant.getName(),LocalDateTime.now(),"addTenant");
        validAccess.put(newTenant,newAccessKey);
        logs.add(addTenant);
        System.out.println("The new tenant is "+ newTenant.getName() + " and the new key is "+ newAccessKey.getPin());
    }

    @Override
    public void removeTenant(String name) throws TenantNotFoundException {
        List<Tenant> tenantsToRemove = new ArrayList<>();
        for (Map.Entry<Tenant, AccessKey> entry : validAccess.entrySet()) {
            if (Objects.equals(entry.getKey().getName(), name)) {
                tenantsToRemove.add(entry.getKey());
            }
        }
        if (tenantsToRemove.isEmpty()) {
            throw new TenantNotFoundException("Tenant with name " + name + " was not found");
        }
        for (Tenant tenant : tenantsToRemove) {
            AccessLog removeTenant = new AccessLog(tenant.getName(),LocalDateTime.now(),"remove tenant");
            validAccess.remove(tenant);
            logs.add(removeTenant);
            System.out.println("The tenant " + name + " was removed");
        }
    }
    public List<AccessLog> getAceessLogs()
    {
        return logs ;
    }
}
