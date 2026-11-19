# UniAccounts

A small Java system that manages student accounts for a university.
This is **Work 1** of the Design Patterns TP: the core of the system, built with 3 creational patterns.

## Requirements

- JDK 17 or newer
- VS Code with "Extension Pack for Java" (or any Java IDE)

## How to run

**With VS Code:**

1. Open the project folder `UniAccounts` in VS Code.
2. Open `src/Main.java`.
3. Click **Run** above `public static void main`.
4. The results appear in the **Terminal**.

**With the terminal** (from the project root):

```
javac -d bin src/model/*.java src/repository/*.java src/prototype/*.java src/Main.java
java -cp bin Main
```

## Project structure

```
UniAccounts/
 ├─ src/
 │   ├─ model/        StudentAccount.java
 │   ├─ repository/   AccountRepository.java, AuditLogger.java
 │   ├─ prototype/    AccountTemplate.java
 │   └─ Main.java
 ├─ docs/             UML diagram and documentation
 ├─ .gitignore
 └─ README.md
```

## Design patterns used (Work 1)

| Pattern       | Class                                              | Role                                                                                                                                   |
| ------------- | -------------------------------------------------- | -------------------------------------------------------------------------------------------------------------------------------------- |
| **Builder**   | `StudentAccount.Builder` (nested class)            | Builds an account step by step. Required fields: id, fullName, email. Optional fields have default values. `build()` checks the rules. |
| **Singleton** | `AccountRepository`                                | One single repository that stores all accounts. Refuses a duplicate id.                                                                |
| **Singleton** | `AuditLogger`                                      | One single audit log that records all operations.                                                                                      |
| **Prototype** | `StudentAccount` (`clone()`) and `AccountTemplate` | Creates new accounts by copying a ready template (deep copy of the courses list), then changes only the identity.                      |

## Validation rules

- `id`: not empty, unique in the repository
- `fullName`: not empty
- `email`: must contain `@`
- `level`: one of `L1`, `L2`, `L3`, `M1`, `M2` (default `L1`)
- Other fields are optional: `phone` (default `null`), `major`, `scholarship` (default `false`), `courses` (default empty list)

## What `Main.java` tests

1. Simple account (required fields only)
2. Full account (all fields)
3. Wrong email, wrong level, empty id (errors are shown)
4. Clone of an account (Prototype, deep copy)
5. Singleton: the repository is the same object everywhere
6. Repository: duplicate id is refused
7. Template for a whole group of students
8. Audit log

## Authors

- Zitouna Messaoud Asma
