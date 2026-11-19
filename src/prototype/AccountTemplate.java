package prototype;

import model.StudentAccount;

public class AccountTemplate {

    private final StudentAccount template;

    public AccountTemplate(StudentAccount template) {
        this.template = template;
    }

    public StudentAccount createAccount(String id, String fullName, String email) {
        try {
            StudentAccount copy = template.clone();  
            copy.setIdentity(id, fullName, email); 
            return copy;
        } catch (CloneNotSupportedException e) {
            throw new IllegalStateException("Clone failed", e);
        }
    }
}