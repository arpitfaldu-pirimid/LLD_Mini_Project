//all functions
//
//apply for loan-> will add in users obj, and also add in queue of loan_application for executor to process for acceptance or rejection
//
//update user's info
//
//update/cancel loan application
//
//signup for user
//
//login for user
//
//history of loans

package main;

import java.util.HashMap;
import java.util.Map;
import java.util.Scanner;

public class Main {

    private static Scanner sc = new Scanner(System.in);

    private static Map<Integer, User> users = new HashMap<>();
    private static Map<Integer, LoanApplication> allApplications = new HashMap<>();

    private static Loan_Application_Processor processor = new Loan_Application_Processor();

    public static void main(String[] args) {

        System.out.println("Loan Processing CLI (Synchronous Demo)");

        User currentUser = null;

        while (true) {
            System.out.println("\nMenu:");
            System.out.println("1. Signup");
            System.out.println("2. Login");
            System.out.println("3. Apply for Loan");
            System.out.println("4. View All Applications");
            System.out.println("5. View Approved Loans");
            System.out.println("6. View Rejected Loans");
            System.out.println("7. Process Pending Loans (Admin)");
            System.out.println("8. Exit");
            System.out.print("Choose: ");

            String choice = sc.nextLine().trim();

            switch (choice) {

                case "1":
                    currentUser = signup();
                    break;

                case "2":
                    currentUser = login();
                    break;

                case "3":
                    if (currentUser != null) applyLoan(currentUser);
                    else System.out.println("Please login first.");
                    break;

                case "4":
                    if (currentUser != null) viewHistory(currentUser);
                    else System.out.println("Please login first.");
                    break;

                case "5":
                    if (currentUser != null) viewApproved(currentUser);
                    else System.out.println("Please login first.");
                    break;

                case "6":
                    if (currentUser != null) viewRejected(currentUser);
                    else System.out.println("Please login first.");
                    break;

                case "7":
                    processAllPending();
                    break;

                case "8":
                    System.out.println("Thank you for using our system!!!");
                    return;

                default:
                    System.out.println("Invalid choice.");
            }
        }
    }

    private static User signup() {
        User u = new User();
        u.signup();
        users.put(u.getUid(), u);

        System.out.println("Signup complete. Your UID: " + u.getUid());
        return u;
    }

    private static User login() {
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

    private static void applyLoan(User user) {

        System.out.println("Available Loan Types: HOME, CAR, PERSONAL");
        System.out.print("Choose: ");
        String type = sc.nextLine().trim().toUpperCase();

        Loan loan = null;
        double g_ask;
        switch (type) {

            case "HOME":

                System.out.print("Enter area of house: ");
                double area = Double.parseDouble(sc.nextLine().trim());

                System.out.print("Enter current price of house: ");
                double price = Double.parseDouble(sc.nextLine().trim());

                System.out.print("Enter downpayment: ");
                double down = Double.parseDouble(sc.nextLine().trim());

                System.out.print("Enter request amount: ");
                double ask = Double.parseDouble(sc.nextLine().trim());
                g_ask=ask;
                loan = new HomeLoan(
                        "Home Loan",
                        7.5,
                        5000000,
                        area,
                        price,
                        down,
                        ask
                );
                break;

            case "CAR":
                System.out.print("Enter car model: ");
                String model = sc.nextLine();

                System.out.print("Enter current price of car: ");
                double car_price = Double.parseDouble(sc.nextLine().trim());

                System.out.print("Enter downpayment: ");
                double car_down = Double.parseDouble(sc.nextLine().trim());

                System.out.print("Enter request amount: ");
                double car_ask = Double.parseDouble(sc.nextLine().trim());
                g_ask=car_ask;
                loan = new CarLoan(
                        "Car Loan",
                        9.0,
                        2000000,
                        model,
                        car_price,
                        car_down,
                        car_ask
                );
                break;

            case "PERSONAL":
                System.out.print("Enter request amount: ");
                double p_ask = Double.parseDouble(sc.nextLine().trim());
                g_ask=p_ask;
                loan = new PersonalLoan(
                        "Personal Loan",
                        12.5,
                        500000,
                        p_ask
                );
                break;

            default:
                System.out.println("Invalid loan type.");
                return;
        }

        // Create loan application
        LoanApplication app = new LoanApplication(loan, user, g_ask);
        allApplications.put(app.getLaid(), app);
        user.addToHistory(app);

        System.out.println("Loan application created with ID: " + app.getLaid());
    }

    private static void viewHistory(User user) {
        Map<Integer, LoanApplication> history = user.getApplicationHistory();
        if (history.isEmpty()) {
            System.out.println("No applications found.");
            return;
        }
        System.out.println("\nYour Applications:");
        history.values().forEach(System.out::println);
    }

    private static void viewApproved(User user) {
        if (user.getApprovedLoans().isEmpty()) {
            System.out.println("No approved loans.");
            return;
        }
        System.out.println("\nApproved Loans:");
        user.getApprovedLoans().forEach(System.out::println);
    }

    private static void viewRejected(User user) {
        if (user.getRejectedLoans().isEmpty()) {
            System.out.println("No rejected loans.");
            return;
        }
        System.out.println("\nRejected Loans:");
        user.getRejectedLoans().forEach(System.out::println);
    }

    private static void processAllPending() {
        System.out.println("Processing pending applications...");

        for (LoanApplication app : allApplications.values()) {
            if (app.getStatus() == LoanApplication.ApplicationStatus.PENDING) {
                processor.process(app);
                System.out.println("Processed: " + app);
            }
        }

        System.out.println("Processing complete.");
    }
}
