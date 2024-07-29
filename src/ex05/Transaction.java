package src.ex05;

import java.util.UUID;

public class Transaction {
    enum Category {
        DEBIT,
        CREDIT,
    }

    private UUID id;
    private User recipient;
    private User sender;
    private Category category;
    private int amount;
    private Transaction next;

    public Transaction(User recipient, User sender, Category category, int amount) {
        this.id = UUID.randomUUID();
        this.recipient = recipient;
        this.sender = sender;
        this.category = category;
        setAmount(amount);
    }

    public UUID getId() {
        return id;
    }

    public User getRecipient() {
        return recipient;
    }

    public User getSender() {
        return sender;
    }

    public Category getCategory() {
        return category;
    }

    public int getAmount() {
        return amount;
    }

    public void setId(UUID id) {
        this.id = id;
    }

    public void setRecipient(User recipient) {
        this.recipient = recipient;
    }

    public void setSender(User sender) {
        this.sender = sender;
    }

    public void setCategory(Category category) {
        this.category = category;
    }

    public void setAmount(int amount) {
        if (category == Category.CREDIT && (amount > 0 || sender.getBalance() < amount)) {
            this.amount = 0;
            System.err.println("aaa");
        } else if (category == Category.DEBIT && (amount < 0 || recipient.getBalance() < amount)) {
            this.amount = 0;
            System.err.println("bbb");
        } else {
            this.amount = amount;
            UpdateBalance(amount);
        }
    }

    public void UpdateBalance(int amount) {
        if (category == Category.CREDIT) {
            sender.setBalance(sender.getBalance() - amount);
            recipient.setBalance(recipient.getBalance() + amount);
        } else if (category == Category.DEBIT) {
            sender.setBalance(sender.getBalance() + amount);
            recipient.setBalance(recipient.getBalance() - amount);
        }
    }

    Transaction getNext() {
        return next;
    }

    void setNext(Transaction next) {
        this.next = next;
    }

    public void printTransaction() {
        System.out.println(id + " " + recipient.getName() + " " + sender.getName() + " " + category + " " + amount);
    }
}
