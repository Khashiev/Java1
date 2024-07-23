package src.ex00;

public class Program {
    public static void main(String[] args) {
        User user1 = new User(1, "Chuk", 1000);
        User user2 = new User(1, "Gek", 1500);

        user1.printUser();
        user2.printUser();

        Transaction transaction1 = new Transaction(user1, user2, Transaction.Category.DEBIT, 200);
        transaction1.printTransaction();
        Transaction transaction2 = new Transaction(user1, user2, Transaction.Category.CREDIT, -300);
        transaction2.printTransaction();

        user1.printUser();
        user2.printUser();
    }
}
