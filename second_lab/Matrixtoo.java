public class Matrixtoo {
    private int[][] data;
    private int size;

    public Matrixtoo(int size) {
        this.size = size;
        this.data = new int[size][size];

        for (int i = 0; i < this.size; i++) {
            for (int j = 0; j < this.size; j++) {
                if (i == j) {
                    this.data[i][j] = 1;
                } else {
                    this.data[i][j] = 0;
                }
            }
        }
    }
    
    public void setElement(int row, int column, int value) {
        if (row >= 0 && row < this.size && column >= 0 && column < this.size) {
            this.data[row][column] = value;
        }
    }

    public Matrixtoo product(Matrix other) {
        Matrixtoo result = new Matrixtoo(size);
        result.data = new int[size][size];

        for (int i = 0; i < this.size; i++) {
            for (int j = 0; j < this.size; j++) {
                for (int k = 0; k < this.size; k++) {
                    result.data[i][j] += this.data[i][k] * other.getElement(k, j);
                }
            }
        }
        return result;
    }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        for (int i = 0; i < this.size; i++) {
            for (int j = 0; j < this.size; j++) {
                sb.append(this.data[i][j]);
                sb.append(" ");
            }
            sb.append("\n");
        }
        return sb.toString();
    }

}