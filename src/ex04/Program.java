package src.ex04;

import java.util.UUID;

public class Program {
    public static void main(String[] args) {
        TransactionsService service = new TransactionsService();

        service.addUser(new User("Chuk", 1000));
        service.addUser(new User("Gek", 1500));

        service.getUsersList().getUserById(1).printUser();
        service.getUsersList().getUserById(2).printUser();

        service.performTransaction(1, 2, 300);
        service.performTransaction(1, 2, 100);

        System.out.println(service.getUserBalance(service.getUsersList().getUserById(1)));
        System.out.println(service.getUserBalance(service.getUsersList().getUserById(2)));

        Transaction[] array = service.getUserTransactionsList(1);
        Transaction[] array2 = service.getUserTransactionsList(2);

        for (Transaction tran : array) {
            tran.printTransaction();
        }

        for (Transaction tran : array2) {
            tran.printTransaction();
        }

        service.removeUserTransaction(array[0].getId(), 1);

        System.out.println();

        array = service.getUserTransactionsList(1);
        array2 = service.getUserTransactionsList(2);

        for (Transaction tran : array) {
            tran.printTransaction();
        }

        for (Transaction tran : array2) {
            tran.printTransaction();
        }

        System.out.println();
        service.getUnpairedTransactions()[0].printTransaction();
    }
}
