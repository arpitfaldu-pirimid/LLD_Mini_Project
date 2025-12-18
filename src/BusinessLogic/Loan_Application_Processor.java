package BusinessLogic;

import Entities.LoanProduct;
import Entities.User;
import Entities.UserLoanApplication;
import static BusinessLogic.FileOp_FormatOp.FileWriterOp;
import static BusinessLogic.FileOp_FormatOp.Formator;

public class Loan_Application_Processor implements Application_Processor {

    private static final String APPROVED_FILE = "Approved_Loans.txt";
    private static final String REJECTED_FILE = "Rejected_Loans.txt";

    public void process(UserLoanApplication app) {
        User user = app.getUser();
        boolean approved = evaluate(app, user);

        if (approved) {
            app.setStatus(app.getApplicationStatus().APPROVED);
            user.markApproved(app);
            FileWriterOp(APPROVED_FILE, Formator(app));
        } else {
            app.setStatus(app.getApplicationStatus().REJECTED);
            user.markRejected(app);
            FileWriterOp(REJECTED_FILE, Formator(app));
        }
    }

    private boolean evaluate(UserLoanApplication app, User user) {

        LoanProduct loan = app.getLoan();
        String category = loan.getCategory();   // HOME / CAR / PERSONAL
        int credit = user.getCreditScore();
        int salary = user.getSalary();

        switch (category) {

            case "HOME":
                return credit >= 700 && (salary * 12 * 5) > app.getAmountRequested();

            case "CAR":
                return credit >= 730 && (salary * 12 * 3) > app.getAmountRequested();

            case "PERSONAL":
                return credit >= 750 && (salary * 12) > app.getAmountRequested();

            default:
                return false;
        }
    }
}
