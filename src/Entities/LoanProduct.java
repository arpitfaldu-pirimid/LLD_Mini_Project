package Entities;

public class LoanProduct {
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

    public String getLoanName() {
        return loanName;
    }

    public double getInterestRate() {
        return interestRate;
    }

    public String getCategory() {
        return category;
    }

    public double getMaxAmount() {
        return maxAmount;
    }

    @Override
    public String toString() {
        return loanName + " [category=" + category + ", rate=" + interestRate + "%, max=" + maxAmount + "]";
    }
}