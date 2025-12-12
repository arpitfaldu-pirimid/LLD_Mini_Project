////implements loan
////
////car model
////current price
////downpayment
////ask_amount
//package main;
//
//public class CarLoan {
//
//    private String car_model;
//    private double current_price_of_car;
//    private double downpayment;
//    private double ask_amount;
//
//}


//implements loan
//
//car model
//current price
//downpayment
//ask_amount

package LoanCategory;

import Entities.LoanProduct;

public class CarLoan extends LoanProduct {

    private String car_model;
    private double current_price_of_car;
    private double downpayment;
    private double ask_amount;

    public CarLoan(String loanName,
                   double interestRate,
                   double maxAmount,
                   String car_model,
                   double current_price_of_car,
                   double downpayment,
                   double ask_amount) {

        super(loanName, interestRate, "CAR", maxAmount);

        this.car_model = car_model;
        this.current_price_of_car = current_price_of_car;
        this.downpayment = downpayment;
        this.ask_amount = ask_amount;
    }

    public String getCar_model() { return car_model; }
    public double getCurrent_price_of_car() { return current_price_of_car; }
    public double getDownpayment() { return downpayment; }
    public double getAsk_amount() { return ask_amount; }

    @Override
    public String toString() {
        return "CarLoan{" +
//                "loanName='" + loanName + '\'' +
//                ", interestRate=" + interestRate +
                ", category='" + category + '\'' +
//                ", maxAmount=" + maxAmount +
                ", car_model='" + car_model + '\'' +
                ", current_price_of_car=" + current_price_of_car +
                ", downpayment=" + downpayment +
                ", ask_amount=" + ask_amount +
                '}';
    }
}
