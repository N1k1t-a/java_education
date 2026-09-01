package stack;

import java.util.LinkedList;

public class LinkedListStack implements IStack {
    private LinkedList<Integer> list = new LinkedList<>();

    public void push(int value) {
        list.addLast(value);
    }

    public int pop() {
        if (list.isEmpty())
            throw new RuntimeException("Стек пуст");
        return list.removeLast();
    }

    public int length() {
        return list.size();
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj) return true;
        if (!(obj instanceof IStack)) return false;
        return this.toString().equals(obj.toString());
    }

    @Override
    public String toString() {
        return list.toString();
    }
}
