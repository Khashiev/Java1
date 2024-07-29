package src.ex05;

import java.util.InputMismatchException;
import java.util.Scanner;
import java.util.UUID;

public class Menu {
    private final TransactionsService service;
    private final boolean devMode;
    private final Scanner in;

    public Menu(boolean devMode) {
        this.service = new TransactionsService();
        this.devMode = devMode;
        this.in = new Scanner(System.in);
    }

    public void run() {
        while (true) {
            printOptions();

            try {
                int command = in.nextInt();

                if (command == 7) {
                    in.close();
                    break;
                }

                executeCommands(command);
            } catch (InputMismatchException e) {
                System.out.println("Invalid input, please try again");
                in.next();
            } catch (Exception e) {
                System.out.println(e.getMessage());
            }

            System.out.println("---------------------------------------------------------");
        }

        System.out.println("Finished");
    }

    private void printOptions() {
        System.out.println("1) Add user");
        System.out.println("2) Check user balance");
        System.out.println("3) Perform a transfer");
        System.out.println("4) Check all user transactions");

        if (devMode) {
            System.out.println("5) Remove transfer by ID");
            System.out.println("6) Check transfer validity");
        }

        System.out.println("7) Finish");
    }

    private void executeCommands(int command) {
        if (command == 1) {
            addUser();
        } else if (command == 2) {
            checkBalance();
        } else if (command == 3) {
            performTransfer();
        } else if (command == 4) {
            checkUserTransactions();
        } else if (command == 5 && devMode) {
            removeUserTransfer();
        } else if (command == 6 && devMode) {
            checkTransferValidity();
        } else {
            System.out.println("Invalid command");
        }
    }

    private void addUser() {
        System.out.println("Enter user name and balance");
        String name = in.next();
        int balance = in.nextInt();

        User user = new User(name, balance);
        service.addUser(user);

        System.out.println("User " + user.getName() + " added");
    }

    private void checkBalance() {
        System.out.println("Enter User Id");
        int userId = in.nextInt();
        User user = service.getUsersList().getUserById(userId);

        System.out.println(user.getName() + " balance = " + user.getBalance());;
    }

    private void performTransfer() {
        System.out.println("Enter recipientId, senderId and amount");
        int recipientId = in.nextInt();
        int senderId = in.nextInt();
        int amount = in.nextInt();

        service.performTransaction(recipientId, senderId, amount);
        System.out.println("Transfer completed");
    }

    private void checkUserTransactions() {
        System.out.println("Enter User Id");
        int userId = in.nextInt();

        User user = service.getUsersList().getUserById(userId);
        Transaction [] transactions = user.getTransactionsList().toArray();

        for (Transaction item : transactions) {
            item.printTransaction();
        }
    }

    private void removeUserTransfer() {
        System.out.println("Enter User ID and Transfer Id");
        int userId = in.nextInt();
        UUID transferId = UUID.fromString(in.next());

        service.removeUserTransaction(transferId, userId);
        System.out.println("Transfer "  + userId + " " + transferId  + " removed");
    }

    private void checkTransferValidity() {
        Transaction[] transactions = service.getUnpairedTransactions();

        for (Transaction item : transactions) {
            item.printTransaction();
        }
    }
}
