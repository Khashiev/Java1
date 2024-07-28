package src.ex04;

import java.util.UUID;

public class TransactionsLinkedList implements TransactionsList {
    private Node head;
    private Node tail;
    private int size;

    private static class Node {
        Node next;
        Node prev;
        Transaction transaction;
    }

    @Override
    public void addTransaction(Transaction transaction) {
        Node newNode = new Node();
        newNode.transaction = transaction;
        newNode.prev = tail;

        if (tail != null) {
            tail.next = newNode;
        }

        tail = newNode;
        if (head == null) {
            head = newNode;
        }

        ++size;
    }

    @Override
    public void removeTransactionById(UUID id) {
        if (id == null || head == null) {
            throw new TransactionNotFoundException("invalid id");
        }

        Node temp = head;
        for (; temp != null; temp = temp.next) {
            if (temp.transaction.getId().equals(id)) {
                removeNode(temp);
                break;
            }
        }

        if (temp == null) {
            throw new TransactionNotFoundException("invalid id");
        }
    }

    private void removeNode(Node node) {
        Node prev = node.prev;
        Node next = node.next;
        if (prev != null) {
            prev.next = next;
        } else {
            head = next;
        }
        if (next != null) {
            next.prev = prev;
        } else {
            tail = prev;
        }
        --size;
    }

    @Override
    public Transaction[] toArray() {
        Transaction[] array = new Transaction[size];

        Node node = head;
        for (int i = 0; i < size; i++) {
            array[i] = node.transaction;
            node = node.next;
        }

        return array;
    }

    public int getSize() {
        return size;
    }
}
