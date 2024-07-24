package src.ex01;

public class User {
    private final int id;
    private String name;
    private int balance;

    public User(String name, int balance) {
        this.id = UserIdsGenerator.getInstance().generateId();
        this.name = name;
        this.balance = balance > 0 ? balance : 0;
    }

    public int getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public int getBalance() {
        return balance;
    }

    public void setName(String newName) {
        this.name = newName;
    }

    public void setBalance(int newBalance) {
        this.balance = newBalance > 0 ? newBalance : 0;
    }

    public void printUser() {
        System.out.println(id + " " + name + " " + balance);
    }
}

