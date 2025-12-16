package LoanCategory;

import Entities.LoanProduct;

import java.util.Scanner;

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

    public static LoanProduct applyPersonalLoan(double g_ask){
        Scanner sc=new Scanner(System.in);
        System.out.print("Enter request amount: ");
        double ask_amount_personal = Double.parseDouble(sc.nextLine().trim());
        g_ask = ask_amount_personal;
        LoanProduct loan=null;
        loan = new PersonalLoan(
                "Personal Loan",
                12.5,
                500000,
                ask_amount_personal
        );
        return loan;
    }

    @Override
    public String toString() {
        return "PersonalLoan{" +
                ", category='" + category + '\'' +
                ", ask_amount=" + ask_amount +
                '}';
    }
}
