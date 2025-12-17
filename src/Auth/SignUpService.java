package Auth;

import Entities.User;

import java.util.Map;
import java.util.Scanner;

public class SignUpService {

    public static User SignUpAuth(Map<Integer, User> users){
        User u = new User();
        Scanner sc = new Scanner(System.in);
        System.out.print("Enter your name: ");
        u.name = sc.nextLine();
        System.out.print("Enter your Salary: ");
        u.salary = sc.nextInt();
        System.out.print("Enter your credit score: ");
        u.creditScore = sc.nextInt();
        System.out.println("Your user id is " + u.getUid());
        users.put(u.getUid(), u);
        System.out.println("Signup complete. Your UID: " + u.getUid());
        return u;
    }

}