package main;

public abstract class LoanProduct implements Loan {
    protected String loanName;
    protected double interestRate;
    protected String category;
    protected double maxAmount;

    public LoanProduct(String loanName, double interestRate, String category, double maxAmount) {
        this.loanName = loanName;
        this.interestRate = interestRate;
        this.category = category;
        this.maxAmount = maxAmount;
    }

    @Override public String getLoanName() { return loanName; }
    @Override public double getInterestRate() { return interestRate; }
    @Override public String getCategory() { return category; }
    @Override public double getMaxAmount() { return maxAmount; }

    @Override
    public String toString() {
        return loanName + " [category=" + category + ", rate=" + interestRate + "%, max=" + maxAmount + "]";
    }
}
