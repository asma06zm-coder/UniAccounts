package repository;

import java.util.ArrayList;
import java.util.List;

public class AuditLogger {

    private static AuditLogger instance;
    private final List<String> logs = new ArrayList<>();

    private AuditLogger() { }   

    public static synchronized AuditLogger getInstance() {
        if (instance == null) {
            instance = new AuditLogger();
        }
        return instance;
    }

    public void log(String message) {
        logs.add(message);
        System.out.println("[AUDIT] " + message);
    }

    public List<String> getLogs() {
        return new ArrayList<>(logs);
    }
}
