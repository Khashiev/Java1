package src.ex02;

public class UserArrayList implements UserList {
    private int numberOfUsers = 0;
    private int maxNumberOfUsers = 10;
    private User[] users = new User[maxNumberOfUsers];

    @Override
    public void addUser(User newUser) {
        if (numberOfUsers == maxNumberOfUsers) {
            maxNumberOfUsers *= 2;
            User[] newUsers = new User[maxNumberOfUsers];

            System.arraycopy(users, 0, newUsers, 0, users.length);
            users = newUsers;
        }

        users[numberOfUsers++] = newUser;
    }

    @Override
    public User getUserById(int id) {
        for (int i = 0; i < numberOfUsers; i++) {
            if (users[i].getId() == id) {
                return users[i];
            }
        }

        throw new UserNotFoundException("User not found");
    }

    @Override
    public User getUserByIndex(int index) {
        if (index < 0 || index >= numberOfUsers) {
            throw new UserNotFoundException("User not found");
        }

        return users[index];
    }

    @Override
    public int getNumberOfUsers() {
        return numberOfUsers;
    }

    public void printList() {
        for (int i = 0; i < numberOfUsers; i++) {
            System.out.println(users[i].getId() + " " + users[i].getName() + " " + users[i].getBalance());
        }
    }
}
