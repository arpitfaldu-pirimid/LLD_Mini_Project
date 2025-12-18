package main;

import Entities.User;

import java.util.Scanner;

import static main.Functions_for_main.*;

public class Main {

    private static final Scanner sc = new Scanner(System.in);

    public static void main(String[] args) throws InterruptedException {

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
            System.out.println("8. Testing by applying 2 loans concurrently");
            System.out.println("9. Exit");
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
                    concurrentLoanApplyTest();
                    return;

                case "9":
                    System.out.println("Thank you for using our system!!!");
                    return;

                default:
                    System.out.println("Invalid choice.");
            }
        }
    }
}