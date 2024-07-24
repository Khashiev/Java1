package src.ex01;

public class Program {
    public static void main(String[] args) {
        User user1 = new User("nif-nif", 1000);
        User user2 = new User("naf-naf", 1500);
        User user3 = new User("nuf-nuf", 2000);

        user1.printUser();
        user2.printUser();
        user3.printUser();
    }
}
