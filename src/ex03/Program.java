package src.ex03;

import java.util.UUID;

public class Program {
    public static void main(String[] args) {
        User user1 = new User("user1", 1000);
        User user2 = new User("user2", 2000);

        TransactionsLinkedList user1Transactions = user1.getTransactionsList();

        Transaction tr1 = new Transaction(user1, user2,Transaction.Category.CREDIT, -100);
        Transaction tr2 = new Transaction(user1, user2, Transaction.Category.DEBIT, 200);

        user1Transactions.addTransaction(tr1);
        user1Transactions.addTransaction(tr2);

        Transaction[] array = user1Transactions.toArray();
        System.out.println(user1Transactions.getSize());
        for (Transaction tran : array) {
            tran.printTransaction();
        }

        user1Transactions.removeTransactionById(tr1.getId());

        Transaction[] array2 = user1Transactions.toArray();
        System.out.println(user1Transactions.getSize());
        for (Transaction tran : array2) {
            tran.printTransaction();
        }
    }
}
