package BusinessLogic;

import Entities.User;
import Entities.UserLoanApplication;

public interface I_Loan_Application_Processor {

    void process(UserLoanApplication app);

    private boolean evaluate(UserLoanApplication app, User user) {
        return false;
    }

    String formatEntry(UserLoanApplication app);

    void appendToFile(String filename, String entry);

}