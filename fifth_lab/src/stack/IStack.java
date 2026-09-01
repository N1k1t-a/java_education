package stack;

public interface IStack {
    void push(int value);
    int pop();
    int length();
    boolean equals(Object obj);
    String toString();
}
