package src.ex05;

public interface UserList {
    void addUser(User newUser);
    User getUserById(int id);
    User getUserByIndex(int index);
    int getNumberOfUsers();
}
