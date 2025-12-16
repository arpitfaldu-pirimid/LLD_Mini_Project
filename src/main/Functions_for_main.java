package main;

import BusinessLogic.Loan_Application_Processor;
import Entities.LoanProduct;
import Entities.User;
import Entities.UserLoanApplication;
import LoanCategory.CarLoan;
import LoanCategory.HomeLoan;
import LoanCategory.PersonalLoan;

import java.util.HashMap;
import java.util.Map;
import java.util.Scanner;

import static LoanCategory.CarLoan.applyCarLoan;
import static LoanCategory.HomeLoan.applyHomeLoan;
import static LoanCategory.PersonalLoan.applyPersonalLoan;
import static main.Demo_Multi_Threading.concurrentLoanApplyTestDemo;

public class Functions_for_main {
    static Scanner sc = new Scanner(System.in);

    private static final Map<Integer, User> users = new HashMap<>();
    private static final Map<Integer, UserLoanApplication> allApplications = new HashMap<>();
    private static final Loan_Application_Processor processor = new Loan_Application_Processor();

    static User signup() {
        User u = new User();
        u.signup();
        users.put(u.getUid(), u);

        System.out.println("Signup complete. Your UID: " + u.getUid());
        return u;
    }

    static User login() {
        System.out.print("Enter UID: ");
        String s = sc.nextLine().trim();

        try {
            int uid = Integer.parseInt(s);

            if (users.containsKey(uid)) {
                System.out.println("Login successful.");
                return users.get(uid);
            }

            System.out.println("User not found. Please signup.");
            return null;

        } catch (NumberFormatException e) {
            System.out.println("Invalid UID.");
            return null;
        }
    }

    static synchronized void applyLoan(User user) throws InterruptedException {

        System.out.println("Available Loan Types: HOME, CAR, PERSONAL");
        System.out.print("Choose: ");
        String type = sc.nextLine().trim().toUpperCase();

        LoanProduct loan = null;
        double g_ask = 0;
        switch (type) {

            case "HOME":
                loan=applyHomeLoan(g_ask);
                break;

            case "CAR":
                loan=applyCarLoan(g_ask);
                break;

            case "PERSONAL":
                loan=applyPersonalLoan(g_ask);
                break;

            default:
                System.out.println("Invalid loan type.");
                return;
        }
        UserLoanApplication app = new UserLoanApplication(loan, user, g_ask);
        allApplications.put(app.getLaid(), app);
        user.addToHistory(app);
        System.out.println("Loan application created with ID: " + app.getLaid());
    }

    static void viewHistory(User user) {
        Map<Integer, UserLoanApplication> history = user.getApplicationHistory();
        if (history.isEmpty()) {
            System.out.println("No applications found.");
            return;
        }
        System.out.println("\nYour Applications:");
        history.values().forEach(System.out::println);
    }

    static void viewApproved(User user) {
        if (user.getApprovedLoans().isEmpty()) {
            System.out.println("No approved loans.");
            return;
        }
        System.out.println("\nApproved Loans:");
        user.getApprovedLoans().forEach(System.out::println);
    }

    static void viewRejected(User user) {
        if (user.getRejectedLoans().isEmpty()) {
            System.out.println("No rejected loans.");
            return;
        }
        System.out.println("\nRejected Loans:");
        user.getRejectedLoans().forEach(System.out::println);
    }

    static void processAllPending() {
        System.out.println("Processing pending applications...");
        for (UserLoanApplication app : allApplications.values()) {
            if (app.getStatus() == UserLoanApplication.ApplicationStatus.PENDING) {
                processor.process(app);
                System.out.println("Processed: " + app);
            }
        }
        System.out.println("Processing complete.");
    }

    public static void concurrentLoanApplyTest(){
        concurrentLoanApplyTestDemo(users, allApplications, processor);
    }

}