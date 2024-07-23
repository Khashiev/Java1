package src.ex00;

public class User {
    private int id;
    private String name;
    private int balance;

    public User(int id, String name, int balance) {
        this.id = id;
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

    public void setId(int newId) {
        this.id = newId;
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
