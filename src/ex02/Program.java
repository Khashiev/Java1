package src.ex02;

public class Program {
    public static void main(String[] args) {
        UserArrayList users = new UserArrayList();

        for (int i = 0; i < 12; i++) {
            users.addUser(new User("user" + (i + 1), (i + 1) * 100));
        }

        users.printList();

        System.out.println("number of users: " + users.getNumberOfUsers());
        System.out.println("get user by id 5: " + users.getUserById(5).getName());
        System.out.println("get user by index 0: " + users.getUserByIndex(0).getName());

//        System.out.println("get user with non-existent id: " + users.getUserById(20));

    }
}
