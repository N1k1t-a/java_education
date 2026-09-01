package stack;

import java.util.Arrays;

public class ArrayStack implements IStack {
    private int[] array;
    private int top = 0;

    public ArrayStack(int capacity) {
        array = new int[capacity];
    }

    public void push(int value) {
        if (top == array.length)
            throw new RuntimeException("Стек переполнен");
        array[top++] = value;
    }

    public int pop() {
        if (top == 0)
            throw new RuntimeException("Стек пуст");
        return array[--top];
    }

    public int length() {
        return top;
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj) return true;
        if (!(obj instanceof IStack)) return false;
        return this.toString().equals(obj.toString());
    }

    @Override
    public String toString() {
        return Arrays.toString(Arrays.copyOf(array, top));
    }
}
