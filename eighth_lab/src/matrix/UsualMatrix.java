package matrix;

public class UsualMatrix {
    private int[][] data;
    private int rows;
    private int columns;

    public UsualMatrix(int rows, int columns) {
        this.rows = rows;
        this.columns = columns;
        this.data = new int[rows][columns];
    }

    public void setElement(int row, int column, int value) {
        data[row][column] = value;
    }

    public int getElement(int row, int column) {
        return data[row][column];
    }

    public int getRows() {
        return this.rows;
    }

    public int getColumns() {
        return this.columns;
    }

    public UsualMatrix product(UsualMatrix other) {
        UsualMatrix result = new UsualMatrix(this.rows, other.getColumns());

        for (int row = 0; row < this.rows; row++) {
            for (int column = 0; column < other.getColumns(); column++) {
                int sum = 0;

                for (int k = 0; k < this.columns; k++) {
                    sum += this.getElement(row, k) * other.getElement(k, column);
                }
                result.setElement(row, column, sum);
            }
        }
        return result;
    }

}