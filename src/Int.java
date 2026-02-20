
public class Int {
    private int value;

    public Int() {
        this.value = 0;
    }

    public void setValue(int user_value) {
        this.value = user_value;
    }

    public int getValue() {
        return this.value;
    }

    public void increment() {
        this.value++;
    }

    public void decrement() {
        this.value--;
    }

    public void add(Int other) {
        this.value += other.getValue();
    }

    public void substract(Int other) {
        this.value -= other.getValue();
    }

    @Override
    public String toString() {
        return String.valueOf(value);
    }

    public void division(int value) {
        if (value == 0) {
            return;
        }

        int dividend = this.value;

        int result = 0;

        boolean negative = (dividend < 0) ^ (value < 0);

        int absDividend = Math.abs(dividend);
        int absDivisor = Math.abs(value);

        while (absDividend >= absDivisor) {
            absDividend -= absDivisor;
            result++;
        }

        if (negative) {
            this.value = -result;
        } else {
            this.value = result;
        }
    }
}
