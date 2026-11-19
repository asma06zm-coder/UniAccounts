package repository;

import java.util.ArrayList;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;
import model.StudentAccount;

public class AccountRepository {

    private static AccountRepository instance;
    private final Map<String, StudentAccount> accounts = new LinkedHashMap<>();

    private AccountRepository() { }

    public static synchronized AccountRepository getInstance() {
        if (instance == null) {
            instance = new AccountRepository();
        }
        return instance;
    }

    public void add(StudentAccount account) {
        if (accounts.containsKey(account.getId())) {
            throw new IllegalArgumentException("ID already exists: " + account.getId());
        }
        accounts.put(account.getId(), account);
        AuditLogger.getInstance().log("Account added: " + account.getId());
    }

    public StudentAccount findById(String id) {
        return accounts.get(id);
    }

    public List<StudentAccount> findAll() {
        return new ArrayList<>(accounts.values());
    }

    public int count() {
        return accounts.size();
    }
}