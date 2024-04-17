package isp.lab7.safehome;

import java.time.LocalDateTime;

public class AccessLog {
    private String tenatName;
    private LocalDateTime dateTime;
    private String operation;
    private DoorStatus doorStatus;
    private String errorMessage;

    public AccessLog(LocalDateTime dateTime, String operation, DoorStatus doorStatus) {
        this.dateTime = dateTime;
        this.operation = operation;
        this.doorStatus = doorStatus;
    }

    public AccessLog(String tenatName, LocalDateTime dateTime, String operation) {
        this.tenatName = tenatName;
        this.dateTime = dateTime;
        this.operation = operation;
    }

    public String getErrorMessage() {
        return errorMessage;
    }

    public String getTenatName() {
        return tenatName;
    }

    public LocalDateTime getDateTime() {
        return dateTime;
    }

    public String getOperation() {
        return operation;
    }

    public DoorStatus getDoorStatus() {
        return doorStatus;
    }
}
