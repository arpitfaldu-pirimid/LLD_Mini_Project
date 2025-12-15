package main;

import BusinessLogic.Loan_Application_Processor;
import Entities.LoanProduct;
import Entities.User;
import Entities.UserLoanApplication;
import LoanCategory.HomeLoan;

import java.util.Map;

public class Demo_Multi_Threading {

    public static void concurrentLoanApplyTestDemo(Map<Integer, User> users, Map<Integer, UserLoanApplication> allApplications, Loan_Application_Processor processor){

        users.clear();
        allApplications.clear();

        User u1=new User("Arpit",50000,765);
        User u2=new User("Yash",55000,742);

        users.put(u1.getUid(),u1);
        users.put(u2.getUid(),u2);

        System.out.println("Users created statically");
        System.out.println(u1);
        System.out.println(u2);

        Thread t1=new Thread(()->{
            applyHomeLoanConcurrentlyDemo(u1, users, allApplications, processor);
        });

        Thread t2=new Thread(()->{
            applyHomeLoanConcurrentlyDemo(u2, users, allApplications, processor);
        });

        t1.start();
        t2.start();

        try{
            t1.join();
            t2.join();
        }catch (InterruptedException e){
            e.printStackTrace();;
        }

        System.out.println("All applications:-");
        allApplications.values().forEach(System.out::println);
    }

    private static synchronized void applyHomeLoanConcurrentlyDemo(User user, Map<Integer, User> users, Map<Integer, UserLoanApplication> allApplications, Loan_Application_Processor processor){

        String loanName="Home Loan";
        double interestRate=7.5;
        double maxAmount=5000000;
        double area=1500;
        double currentprice=6500000;
        double downpayment=3500000;
        double askamount=3000000;

        LoanProduct loan=new HomeLoan(loanName,interestRate,maxAmount,area,currentprice,downpayment,askamount);
        UserLoanApplication app=new UserLoanApplication(loan,user,askamount);
        allApplications.put(app.getLaid(),app);
        user.addToHistory(app);

        System.out.println("Thread: "+  Thread.currentThread().getName()+ " |User: "+user.getName()+ " |Application ID: "+app.getLaid());
    }

}