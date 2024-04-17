package isp.lab7.safehome;

public class Door {
    private DoorStatus status;

    public Door() {
        this.status = DoorStatus.CLOSE; // Initialize door status as closed
    }

    public DoorStatus getStatus() {
        return status;
    }

    public void lockDoor() {
        if (status == DoorStatus.CLOSE) {
            System.out.println("The door is already closed and locked.");
        } else {
            status = DoorStatus.CLOSE;
            System.out.println("The door is now closed and locked.");
        }
    }

    public void unlockDoor() {
        if (status == DoorStatus.OPEN) {
            System.out.println("The door is already open.");
        } else {
            status = DoorStatus.OPEN;
            System.out.println("The door is now unlocked and open.");
        }
    }
}
