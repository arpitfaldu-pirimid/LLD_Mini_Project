package Entities;

import java.util.*;

public class User {

    private static int counter = 1;
    private final int uid;
    private String name;
    private int salary;
    private int creditScore;

    private final Map<Integer, UserLoanApplication> applicationHistory = new HashMap<>();
    private final List<UserLoanApplication> approvedLoans = new ArrayList<>();
    private final List<UserLoanApplication> rejectedLoans = new ArrayList<>();

    public User() {
        this.uid = counter++;
    }

    public User(String name, int salary, int creditScore) {
        this.uid = counter++;
        this.name=name;
        this.salary=salary;
        this.creditScore=creditScore;
    }

    public void signup() {
        Scanner sc = new Scanner(System.in);
        System.out.print("Enter your name: ");
        this.name = sc.nextLine();
        System.out.print("Enter your Salary: ");
        this.salary = sc.nextInt();
        System.out.print("Enter your credit score: ");
        this.creditScore = sc.nextInt();
        System.out.println("Your user id is " + this.uid);
    }

    public void addToHistory(UserLoanApplication app) {
        applicationHistory.put(app.getLaid(), app);
    }

    public void markApproved(UserLoanApplication app) {
        approvedLoans.add(app);
    }

    public void markRejected(UserLoanApplication app) {
        rejectedLoans.add(app);
    }

    public int getUid() {
        return uid;
    }

    public String getName() {
        return name;
    }

    public int getSalary() {
        return salary;
    }

    public int getCreditScore() {
        return creditScore;
    }

    public Map<Integer, UserLoanApplication> getApplicationHistory() {
        return applicationHistory;
    }

    public List<UserLoanApplication> getApprovedLoans() {
        return approvedLoans;
    }

    public List<UserLoanApplication> getRejectedLoans() {
        return rejectedLoans;
    }

    @Override
    public String toString() {
        return "User{" +
                "uid=" + uid +
                ", name='" + name + '\'' +
                ", salary=" + salary +
                ", creditScore=" + creditScore +
                '}';
    }
}
