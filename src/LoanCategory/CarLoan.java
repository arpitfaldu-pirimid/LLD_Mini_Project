package LoanCategory;

import Entities.LoanProduct;

import java.util.Scanner;

public class CarLoan extends LoanProduct {

    private final String car_model;
    private final double current_price_of_car;
    private final double downpayment;
    private final double ask_amount;

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

    public String getCar_model() {
        return car_model;
    }

    public double getCurrent_price_of_car() {
        return current_price_of_car;
    }

    public double getDownpayment() {
        return downpayment;
    }

    public double getAsk_amount() {
        return ask_amount;
    }

    public static LoanProduct applyCarLoan(double g_ask){
        Scanner sc=new Scanner(System.in);
        System.out.print("Enter car model: ");
        String model = sc.nextLine();

        System.out.print("Enter current price of car: ");
        double current_price_of_car = Double.parseDouble(sc.nextLine().trim());

        System.out.print("Enter downpayment: ");
        double downpayment_car = Double.parseDouble(sc.nextLine().trim());

        System.out.print("Enter request amount: ");
        double ask_amount_car = Double.parseDouble(sc.nextLine().trim());
        g_ask = ask_amount_car;
        LoanProduct loan=null;
        loan = new CarLoan(
                "Car Loan",
                9.0,
                2000000,
                model,
                current_price_of_car,
                downpayment_car,
                ask_amount_car
        );
        return loan;
    }

    @Override
    public String toString() {
        return "CarLoan{" +
                ", category='" + category + '\'' +
                ", car_model='" + car_model + '\'' +
                ", current_price_of_car=" + current_price_of_car +
                ", downpayment=" + downpayment +
                ", ask_amount=" + ask_amount +
                '}';
    }
}
