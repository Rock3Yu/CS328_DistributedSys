import myrmi.registry.LocateRegistry;
import myrmi.registry.Registry;

public class ClientDemo {
    private static Payment stub = null;

    private ClientDemo() {
    }

    public static void main(String[] args) {

//        double payment;
        double principal = 80000;
        double annualInterest = .065;
        int years = 15;
        try {
            Registry reg;
            if (args.length == 1) {
                reg = LocateRegistry.getRegistry(args[0]);
            } else {
                reg = LocateRegistry.getRegistry();
            }
            stub = (Payment) reg.lookup("Mortgage");

        } catch (Exception e) {
            System.err.println("Client exception thrown: " + e.toString());
            e.printStackTrace();
        }
        if (args.length == 3) {
            try {
                principal = Double.parseDouble(args[0]);
                annualInterest = Double.parseDouble(args[1]);
                years = Integer.parseInt(args[2]);
                print(principal, annualInterest, years);
            } catch (Exception e) {
                System.out.println("Wrong input " + e.getMessage());
                System.exit(0);
            }
        } else {
            System.out.println("Usage: java Client principal annualInterest years ");
            System.out.println("\nFor example: java Client 80000 .065 15 ");
            System.out.println("\nYou will get the output like the following: \n");
            print(principal, annualInterest, years);
            System.exit(0);
        }

    }

    public static void print(double pr, double annRate, int years) {
        double mpayment = 0;
        try {
            mpayment = stub.calculatePayment(pr, annRate, years);
            System.out.println("The principal is $" + (int) pr);
            System.out.println("The annual interest rate is " + annRate * 100 + "%");
            System.out.println("The term is " + years + " years");
            System.out.println("Your monthly payment is $" + mpayment);
        } catch (Exception e) {
            System.out.println("[Exception] Remote method exception thrown: " + e.getMessage());
        }

    }
}
