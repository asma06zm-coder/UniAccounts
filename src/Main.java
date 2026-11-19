import model.StudentAccount;
import prototype.AccountTemplate;
import repository.AccountRepository;
import repository.AuditLogger;

public class Main {
    public static void main(String[] args) throws CloneNotSupportedException {

        // اختبار 1: حساب بسيط (الحقول الإجبارية فقط)
        System.out.println("=== Test 1: simple account ===");
        StudentAccount a1 = new StudentAccount.Builder("2026001", "Aya Mohammmed", "aya@univ.dz")
                .build();
        System.out.println(a1);
        
        // اختبار 2: حساب كامل (كل الحقول)
        System.out.println("\n=== Test 2: full account ===");
        StudentAccount a2 = new StudentAccount.Builder("2026002", "Yacine Ali", "yacine@univ.dz")
                .phone("0565732212")
                .level("L2")
                .major("Informatique")
                .scholarship(true)
                .addCourse("DB")
                .addCourse("Web dev")
                .build();
        System.out.println(a2);

        // اختبار 3: بريد خاطئ (يجب أن يظهر خطأ)
        System.out.println("\n=== Test 3: wrong email ===");
        try {
            new StudentAccount.Builder("2026003", "Leyla Bensalem", "leyla.univ.dz").build();
        } catch (IllegalArgumentException e) {
            System.out.println("Error: " + e.getMessage());
        }

        // اختبار 4: مستوى خاطئ (يجب أن يظهر خطأ)
        System.out.println("\n=== Test 4: wrong level ===");
        try {
            new StudentAccount.Builder("2026004", "Asma ZM", "asma@univ.dz")
                    .level("L9")
                    .build();
        } catch (IllegalArgumentException e) {
            System.out.println("Error: " + e.getMessage());
        }

        // اختبار 5: رقم تسجيل فارغ (يجب أن يظهر خطأ)
        System.out.println("\n=== Test 5: empty id ===");
        try {
            new StudentAccount.Builder("", "Ali ZM", "ali@univ.dz").build();
        } catch (IllegalArgumentException e) {
            System.out.println("Error: " + e.getMessage());
        }

        // اختبار 6: نمط Prototype، ننسخ حسابا ونغير الهوية فقط
        System.out.println("\n=== Test 6: clone (Prototype) ===");
        StudentAccount copy = a2.clone();
        copy.setIdentity("2026005", "Maisa Hadj", "maisa@univ.dz");
        copy.addCourse("Networks");   
        System.out.println("Original: " + a2);
        System.out.println("Copy    : " + copy);

        // اختبار 7: نمط Singleton، نفس المستودع في كل مكان
        System.out.println("\n=== Test 7: Singleton ===");
        AccountRepository r1 = AccountRepository.getInstance();
        AccountRepository r2 = AccountRepository.getInstance();
        System.out.println("Same repository? " + (r1 == r2));   // لازم true

        // اختبار 8: المستودع ورفض رقم التسجيل المكرر
        System.out.println("\n=== Test 8: repository ===");
        r1.add(a1);
        r1.add(a2);
        try {
            r1.add(a1);   // نفس الرقم مرة ثانية
        } catch (IllegalArgumentException e) {
            System.out.println("Error: " + e.getMessage());
        }
        System.out.println("Accounts stored: " + r1.count());   // لازم 2

        // اختبار 9: قالب لدفعة كاملة (نمط Prototype)
        System.out.println("\n=== Test 9: template for a group ===");
        StudentAccount base = new StudentAccount.Builder("TEMPLATE", "Template", "template@univ.dz")
                .level("L2")
                .major("Informatique")
                .addCourse("Algorithms")
                .addCourse("Databases")
                .build();
        AccountTemplate group = new AccountTemplate(base);
        r1.add(group.createAccount("2026006", "Imane Amer", "imane@univ.dz"));
        r1.add(group.createAccount("2026007", "Meryem Ali", "meryem@univ.dz"));
        System.out.println("Total accounts: " + r1.count());      // لازم 4

        // سجل التدقيق
        System.out.println("\n=== Audit log ===");
        AuditLogger.getInstance().getLogs().forEach(System.out::println);
   }
}

