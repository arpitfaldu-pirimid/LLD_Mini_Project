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

    static void applyLoan(User user) throws InterruptedException {

        System.out.println("Available Loan Types: HOME, CAR, PERSONAL");
        System.out.print("Choose: ");
        String type = sc.nextLine().trim().toUpperCase();

        LoanProduct loan = null;
        double g_ask = 0;
        switch (type) {

            case "HOME":

                System.out.print("Enter area of house: ");
                double area_of_house = Double.parseDouble(sc.nextLine().trim());

                System.out.print("Enter current price of house: ");
                double current_price_of_house = Double.parseDouble(sc.nextLine().trim());

                System.out.print("Enter downpayment: ");
                double downpayment_of_house = Double.parseDouble(sc.nextLine().trim());

                System.out.print("Enter request amount: ");
                double ask_amount_house = Double.parseDouble(sc.nextLine().trim());
                g_ask = ask_amount_house;
                loan = new HomeLoan(
                        "Home Loan",
                        7.5,
                        5000000,
                        area_of_house,
                        current_price_of_house,
                        downpayment_of_house,
                        ask_amount_house
                );
                break;

            case "CAR":
                System.out.print("Enter car model: ");
                String model = sc.nextLine();

                System.out.print("Enter current price of car: ");
                double current_price_of_car = Double.parseDouble(sc.nextLine().trim());

                System.out.print("Enter downpayment: ");
                double downpayment_car = Double.parseDouble(sc.nextLine().trim());

                System.out.print("Enter request amount: ");
                double ask_amount_car = Double.parseDouble(sc.nextLine().trim());
                g_ask = ask_amount_car;
                loan = new CarLoan(
                        "Car Loan",
                        9.0,
                        2000000,
                        model,
                        current_price_of_car,
                        downpayment_car,
                        ask_amount_car
                );
                break;

            case "PERSONAL":
                System.out.print("Enter request amount: ");
                double ask_amount_personal = Double.parseDouble(sc.nextLine().trim());
                g_ask = ask_amount_personal;
                loan = new PersonalLoan(
                        "Personal Loan",
                        12.5,
                        500000,
                        ask_amount_personal
                );
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

        users.clear();
        allApplications.clear();

        User u1=new User("Arpit",50000,765);
        User u2=new User("Yash",55000,742);

        users.put(u1.getUid(),u1);
        users.put(u2.getUid(),u2);

        System.out.println("Users created statically");
        System.out.println(u1);
        System.out.println(u2);

        Thread t1=new Thread(()->{
            applyHomeLoanConcurrently(u1);
        });

        Thread t2=new Thread(()->{
            applyHomeLoanConcurrently(u2);
        });

        t1.start();
        t2.start();

        try{
            t1.join();
            t2.join();
        }catch (InterruptedException e){
            e.printStackTrace();;
        }

        System.out.println("All applications:-");
        allApplications.values().forEach(System.out::println);
    }

    private static synchronized void applyHomeLoanConcurrently(User user){

        String loanName="Home Loan";
        double interestRate=7.5;
        double maxAmount=5000000;
        double area=1500;
        double currentprice=6500000;
        double downpayment=3500000;
        double askamount=3000000;

        LoanProduct loan=new HomeLoan(loanName,interestRate,maxAmount,area,currentprice,downpayment,askamount);
        UserLoanApplication app=new UserLoanApplication(loan,user,askamount);
        allApplications.put(app.getLaid(),app);
        user.addToHistory(app);

        System.out.println("Thread: "+  Thread.currentThread().getName()+ " |User: "+user.getName()+ " |Application ID: "+app.getLaid());
    }


}