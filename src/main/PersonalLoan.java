////implements loan
////
////ask_amount
//package main;
//
//public class PersonalLoan {
//
//    private int ask_amount;
//
//}



//implements loan
//
//ask_amount
package main;

public class PersonalLoan extends LoanProduct {

    private double ask_amount;

    public PersonalLoan(String loanName,
                        double interestRate,
                        double maxAmount,
                        double ask_amount) {

        super(loanName, interestRate, "PERSONAL", maxAmount);

        this.ask_amount = ask_amount;
    }

    public double getAsk_amount() { return ask_amount; }

    @Override
    public String toString() {
        return "PersonalLoan{" +
//                "loanName='" + loanName + '\'' +
//                ", interestRate=" + interestRate +
                ", category='" + category + '\'' +
//                ", maxAmount=" + maxAmount +
                ", ask_amount=" + ask_amount +
                '}';
    }
}
