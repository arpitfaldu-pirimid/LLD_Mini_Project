package LoanCategory;

import Entities.LoanProduct;

public class PersonalLoan extends LoanProduct {

    private final double ask_amount;

    public PersonalLoan(String loanName,
                        double interestRate,
                        double maxAmount,
                        double ask_amount) {

        super(loanName, interestRate, "PERSONAL", maxAmount);

        this.ask_amount = ask_amount;
    }

    public double getAsk_amount() {
        return ask_amount;
    }

    @Override
    public String toString() {
        return "PersonalLoan{" +
                ", category='" + category + '\'' +
                ", ask_amount=" + ask_amount +
                '}';
    }
}
