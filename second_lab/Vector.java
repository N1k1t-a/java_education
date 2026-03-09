public class Vector {
    private int[] data;
    private int size;

    public Vector(int size) {
        this.size = size;
        this.data = new int[size];
    }

    public void setElement(int index, int value) {
        if (index >= 0 && index < size) {
            this.data[index] = value;
        }
    }

    public Vector product(Matrix other) {
        Vector result = new Vector(size);
        for (int j = 0; j < size; j++) {
            for (int k = 0; k < size; k++) {
                result.data[j] += this.data[k] * other.getElement(k, j);
            }
        }
        return result;
    }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        for (int i = 0; i < size; i++) {
            sb.append(data[i]).append(" ");
        }
        return sb.toString();
    }
}