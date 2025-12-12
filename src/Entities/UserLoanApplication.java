package Entities;

import java.time.LocalDateTime;

public class UserLoanApplication {

    private static int counter = 1;

    private final int laid;
    private final LoanProduct loan;
    private final User user;
    private final double amountRequested;
    private ApplicationStatus status;
    private final LocalDateTime timestamp;

    public enum ApplicationStatus {
        PENDING, APPROVED, REJECTED
    }

    public UserLoanApplication(LoanProduct loan, User user, double amountRequested) {
        this.laid = counter++;
        this.loan = loan;
        this.user = user;
        this.amountRequested = amountRequested;
        this.status = ApplicationStatus.PENDING;
        this.timestamp = LocalDateTime.now();
    }

    public int getLaid() {
        return laid;
    }

    public LoanProduct getLoan() {
        return loan;
    }

    public User getUser() {
        return user;
    }

    public double getAmountRequested() {
        return amountRequested;
    }

    public ApplicationStatus getStatus() {
        return status;
    }

    public LocalDateTime getTimestamp() {
        return timestamp;
    }

    public void setStatus(ApplicationStatus status) {
        this.status = status;
    }

    @Override
    public String toString() {
        return "LoanApplication{" +
                "ApplicationID=" + laid +
                ", LoanType=" + loan.getLoanName() +
                ", InterestRate=" + loan.getInterestRate() +
                ", MaxAmount=" + loan.getMaxAmount() +
                ", User='" + user.getName() + '\'' +
//                ", RequestedAmount=" + getAmountRequested() +
                ", LoanDetails=" + loan.toString() +
                ", Status=" + status +
                ", Timestamp=" + timestamp +
                '}';
    }
}
