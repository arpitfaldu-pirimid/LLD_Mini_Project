package main;

import java.io.FileWriter;
import java.io.IOException;
import java.time.format.DateTimeFormatter;

public class Loan_Application_Processor {

    private static final String APPROVED_FILE = "./src/main/Approved_Loans.txt";
    private static final String REJECTED_FILE = "./src/mainRejected_Loans.txt";

    // Main process method for approving or rejecting loan
    public void process(LoanApplication app) {
        User user = app.getUser();
        boolean approved = evaluate(app, user);

        if (approved) {
            app.setStatus(LoanApplication.ApplicationStatus.APPROVED);
            user.markApproved(app);
            appendToFile(APPROVED_FILE, formatEntry(app));
        } else {
            app.setStatus(LoanApplication.ApplicationStatus.REJECTED);
            user.markRejected(app);
            appendToFile(REJECTED_FILE, formatEntry(app));
        }
    }

    private boolean evaluate(LoanApplication app, User user) {

        Loan loan = app.getLoan();
        String category = loan.getCategory();   // HOME / CAR / PERSONAL
        int credit = user.getCreditScore();
        int salary = user.getSalary();

        switch (category) {

            case "HOME":
                return credit >= 700 && (salary*12*5) > app.getAmountRequested();

            case "CAR":
                return credit >= 730 && (salary*12*3) > app.getAmountRequested();

            case "PERSONAL":
                return credit >= 750 && (salary*12) > app.getAmountRequested();

            default:
                return false;
        }
    }

    private String formatEntry(LoanApplication app) {
        DateTimeFormatter fmt = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss");

        return String.format("%s | %s | %s | %.2f | %s%n",
                app.getTimestamp().format(fmt),
                app.getLoan().getInterestRate(),
                app.getLoan().getMaxAmount(),
                app.getLoan().getLoanName(),
                app.getUser().getName(),
                app.getAmountRequested(),
                app.getLoan().toString(),
                app.getStatus().name());
    }

    // Writing in file
    private void appendToFile(String filename, String entry) {
        try (FileWriter fw = new FileWriter(filename, true)) {
            fw.write(entry);
        } catch (IOException e) {
            System.err.println("Failed to write to " + filename + ": " + e.getMessage());
        }
    }
}
