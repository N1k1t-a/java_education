package stack;

import java.util.ArrayList;

public class ArrayListStack implements IStack {
    private ArrayList<Integer> list = new ArrayList<>();

    public void push(int value) {
        list.add(value);
    }

    public int pop() {
        if (list.isEmpty())
            throw new RuntimeException("Стек пуст");
        return list.remove(list.size() - 1);
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
