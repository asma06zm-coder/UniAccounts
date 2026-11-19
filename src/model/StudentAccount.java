package model;

import java.util.ArrayList;
import java.util.List;


// نمط Prototype: الصنف StudentAccount هو النموذج الأصلي الذي يمكن نسخه
public class StudentAccount implements Cloneable {

    private String id;
    private String fullName;
    private String email;
    private final String phone;
    private final String level;
    private final String major;
    private final boolean scholarship;
    private List<String> courses;


    // المُنشئ خاص: لا يمكن إنشاء حساب إلا عن طريق Builder
    private StudentAccount(Builder builder) {
        this.id = builder.id;
        this.fullName = builder.fullName;
        this.email = builder.email;
        this.phone = builder.phone;
        this.level = builder.level;
        this.major = builder.major;
        this.scholarship = builder.scholarship;
        this.courses = new ArrayList<>(builder.courses);
    }

     // نمط Builder: الصنف Builder يبني الحساب خطوة بخطوة
    public static class Builder {

        private String id;
        private String fullName;
        private String email;

        // الحقول الاختيارية مع قيمها الافتراضية
        private String phone = null;
        private String level = "L1";
        private String major = null;
        private boolean scholarship = false;
        private List<String> courses = new ArrayList<>();

        // الحقول الإجبارية: لا يمكن إنشاء حساب بدونها
        public Builder(String id, String fullName, String email) {
            this.id = id;
            this.fullName = fullName;
            this.email = email;
        }
        // الحقول الاختيارية: كل دالة ترجع this لنربط الدوال ببعضها
        public Builder phone(String phone) {
            this.phone = phone;
            return this;
        }

        public Builder level(String level) {
            this.level = level;
            return this;
        }

        public Builder major(String major) {
            this.major = major;
            return this;
        }

        public Builder scholarship(boolean scholarship) {
            this.scholarship = scholarship;
            return this;
        }

        public Builder addCourse(String course) {
            this.courses.add(course);
            return this;
        }

        public Builder courses(List<String> courses) {
            this.courses = new ArrayList<>(courses);
            return this;
        }

        public StudentAccount build() {

            if (id == null || id.trim().isEmpty()) {
                throw new IllegalArgumentException("ID cannot be empty");
            }

            if (fullName == null || fullName.trim().isEmpty()) {
                throw new IllegalArgumentException("Name cannot be empty");
            }

            if (email == null || !email.contains("@")) {
                throw new IllegalArgumentException("Invalid email");
            }
        if (level == null || !List.of("L1", "L2", "L3", "M1", "M2").contains(level)) {
        throw new IllegalArgumentException("Invalid level: " + level);
        }
            return new StudentAccount(this);
        }
    }
    public String getId() {
       return id;
    }
    public String getFullName() { 
        return fullName; 
    }
    public String getEmail() { 
        return email; 
    }
    public String getPhone() { 
        return phone; 
    }
    public String getLevel() { 
        return level;
    }
    public String getMajor() {
         return major; 
    }
    public boolean isScholarship() {
         return scholarship; 
    }
    public List<String> getCourses() {
         return new ArrayList<>(courses);
    }

   @Override
   public StudentAccount  clone() throws CloneNotSupportedException {
       try {
           StudentAccount copy = (StudentAccount) super.clone();
           copy.courses = new ArrayList<>(this.courses);
           return copy;
       } catch (CloneNotSupportedException e){
            throw new AssertionError(e);
       }
    }

    public void setIdentity(String id, String fullName, String email) {
        this.id = id;
        this.fullName = fullName;
        this.email = email;
    }

    public void addCourse(String course) {
        courses.add(course);
    }

    @Override
    public String toString() {
        return "StudentAccount{" +
                "id='" + id + '\'' +
                ", fullName='" + fullName + '\'' +
                ", email='" + email + '\'' +
                ", phone='" + phone + '\'' +
                ", level='" + level + '\'' +
                ", major='" + major + '\'' +
                ", scholarship=" + scholarship +
                ", courses=" + courses +
                '}';
    }
}
