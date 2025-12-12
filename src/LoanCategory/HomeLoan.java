package LoanCategory;

import Entities.LoanProduct;

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
