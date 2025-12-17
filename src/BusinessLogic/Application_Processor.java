package BusinessLogic;

import Entities.User;
import Entities.UserLoanApplication;

public interface Application_Processor {

    void process(UserLoanApplication app);

    private boolean evaluate(UserLoanApplication app, User user) {
        return false;
    }

}