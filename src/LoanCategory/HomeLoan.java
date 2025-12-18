package LoanCategory;

import Entities.LoanProduct;
import Entities.UserLoanApplication;

import java.util.Scanner;

public class HomeLoan extends LoanProduct {

    private final double area_of_house;
    private final double current_price_of_house;
    private final double downpayment;
    private final double ask_amount;

    public HomeLoan(String loanName,
                    double interestRate,
                    double maxAmount,
                    double area_of_house,
                    double current_price_of_house,
                    double downpayment,
                    double ask_amount) {

        super(loanName, interestRate, "HOME", maxAmount);

        this.area_of_house = area_of_house;
        this.current_price_of_house = current_price_of_house;
        this.downpayment = downpayment;
        this.ask_amount = ask_amount;
    }

    public double getArea_of_house() {
        return area_of_house;
    }

    public double getCurrent_price_of_house() {
        return current_price_of_house;
    }

    public double getDownpayment() {
        return downpayment;
    }

    public double getAsk_amount() {
        return ask_amount;
    }
    public static LoanProduct applyHomeLoan(double g_ask){
        Scanner sc=new Scanner(System.in);
        System.out.print("Enter area of house: ");
        double area_of_house = Double.parseDouble(sc.nextLine().trim());

        System.out.print("Enter current price of house: ");
        double current_price_of_house = Double.parseDouble(sc.nextLine().trim());

        System.out.print("Enter downpayment: ");
        double downpayment_of_house = Double.parseDouble(sc.nextLine().trim());

        System.out.print("Enter request amount: ");
        double ask_amount_house = Double.parseDouble(sc.nextLine().trim());
        g_ask = ask_amount_house;
        LoanProduct loan = null;
        loan = new HomeLoan(
                "Home Loan",
                7.5,
                5000000,
                area_of_house,
                current_price_of_house,
                downpayment_of_house,
                ask_amount_house
        );
        return loan;
    }

    @Override
    public String toString() {
        return "HomeLoan{" +
                ", category='" + category + '\'' +
                ", area_of_house=" + area_of_house +
                ", current_price_of_house=" + current_price_of_house +
                ", downpayment=" + downpayment +
                ", ask_amount=" + ask_amount +
                '}';
    }
}
