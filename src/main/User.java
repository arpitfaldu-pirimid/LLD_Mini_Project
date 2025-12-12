package main;

import java.util.*;

public class User {

    private static int counter = 1;
    private int uid;
    private String name;
    private int salary;
    private int creditScore;

    private Map<Integer, LoanApplication> applicationHistory = new HashMap<>();
    private List<LoanApplication> approvedLoans = new ArrayList<>();
    private List<LoanApplication> rejectedLoans = new ArrayList<>();

    public User() {
        this.uid = counter++;
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

    public void addToHistory(LoanApplication app) {
        applicationHistory.put(app.getLaid(), app);
    }

    public void markApproved(LoanApplication app) {
        approvedLoans.add(app);
    }

    public void markRejected(LoanApplication app) {
        rejectedLoans.add(app);
    }

    public int getUid() { return uid; }
    public String getName() { return name; }
    public int getSalary() { return salary; }
    public int getCreditScore() { return creditScore; }

    public Map<Integer, LoanApplication> getApplicationHistory() { return applicationHistory; }
    public List<LoanApplication> getApprovedLoans() { return approvedLoans; }
    public List<LoanApplication> getRejectedLoans() { return rejectedLoans; }

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
