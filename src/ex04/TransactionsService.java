package src.ex04;

import java.util.UUID;

public class TransactionsService {
    private UserList usersList;

    public TransactionsService() {
        usersList = new UserArrayList();
    }

    public UserList getUsersList() {
        return usersList;
    }

    public void addUser(User user) {
        usersList.addUser(user);
    }

    public int getUserBalance(User user) {
        return user.getBalance();
    }

    public void performTransaction(int recipientId, int senderId, int amount) {
        if (recipientId == senderId || amount == 0) {
            throw new IllegalTransactionException("Invalid transaction");
        }

        User recipient = usersList.getUserById(recipientId);
        User sender = usersList.getUserById(senderId);

        if (recipient.getBalance() < amount) {
            throw new IllegalTransactionException("Invalid transaction");
        }

        Transaction credit = new Transaction(sender, recipient, Transaction.Category.CREDIT, -amount);
        Transaction debit = new Transaction(recipient, sender, Transaction.Category.DEBIT, amount);

        debit.setId(credit.getId());

        recipient.getTransactionsList().addTransaction(debit);
        sender.getTransactionsList().addTransaction(credit);

        sender.setBalance(sender.getBalance() - amount);
        recipient.setBalance(recipient.getBalance() + amount);
    }

    public Transaction[] getUserTransactionsList(int id) {
        return  usersList.getUserById(id).getTransactionsList().toArray();
    }

    public void removeUserTransaction(UUID transactionId, int userId) {
        usersList.getUserById(userId).getTransactionsList().removeTransactionById(transactionId);
    }

    public Transaction[] getUnpairedTransactions() {
        TransactionsList transactionsList = getAllTransactions();
        TransactionsLinkedList res = new TransactionsLinkedList();
        Transaction[] arrayFirst = transactionsList.toArray();

        if (arrayFirst != null) {
            int sizeArray = arrayFirst.length;

            for (int i = 0; i < sizeArray; i++) {
                int count = 0;

                for (int j = 0; j < sizeArray; j++) {
                    if (arrayFirst[i].getId().equals(arrayFirst[j].getId())) {
                        count++;
                    }
                }

                if (count != 2) {
                    res.addTransaction(arrayFirst[i]);
                }
            }
        }

        return res.toArray();
    }

    private TransactionsList getAllTransactions() {
        TransactionsList list = new TransactionsLinkedList();

        for (int i = 0; i < usersList.getNumberOfUsers(); i++) {
            User user = usersList.getUserByIndex(i);

            if (user != null) {
                for (int j = 0; j < user.getTransactionsList().getSize(); j++) {
                    list.addTransaction(user.getTransactionsList().toArray()[j]);
                }
            }
        }

        return list;
    }


}
