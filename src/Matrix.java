public class Matrix {
    public float[][] m;
    private int rows, cols;

    public Matrix(int rows, int cols) {
        this.rows = rows;
        this.cols = cols;
        m = new float[rows][cols];
    }

    public Matrix(Vertex4D vert) {
        this(5, 1);
        m[0][0] = vert.getX();
        m[1][0] = vert.getY();
        m[2][0] = vert.getZ();
        m[3][0] = vert.getA();
        m[4][0] = 1;
    }

    public Matrix(Matrix mat) {
        this(mat.getRows(), mat.getCols());
        for (int i = 0; i < rows; i++) {
            for (int j = 0; j < cols; j++) {
                m[i][j] = mat.getVal(i, j);
            }
        }
    }

    public Matrix multiply(Matrix other) {

        if (cols != other.getRows()) {
            return null;
        }

        Matrix result = new Matrix(rows, other.getCols());
        for (int i = 0; i < rows; i++) {
            for (int j = 0; j < other.getCols(); j++) {
                float sum = 0;
                for (int k = 0; k < cols; k++) {
                    sum += m[i][k] * other.getVal(k, j);
                }
                result.setVal(i, j, sum);
            }
        }
        return result;
    }

    public Vertex4D multiplyVert(Vertex4D other) {
        Matrix vm = new Matrix(other);
        Matrix tmp = this.multiply(vm);
        return new Vertex4D(tmp.getVal(0, 0), tmp.getVal(1, 0), tmp.getVal(2, 0), tmp.getVal(3, 0));
    }

    public void setVal(int row, int col, float val) {
        m[row][col] = val;
    }

    public float getVal(int row, int col) {
        return m[row][col];
    }

    public int getRows() {
        return rows;
    }

    public int getCols() {
        return cols;
    }

    public String toString() {
        StringBuffer sb = new StringBuffer();
        sb.append("[\n");
        for (int i = 0; i < rows; i++) {
            sb.append("\t[ ");
            for (int j = 0; j < cols; j++) {
                sb.append(m[i][j]);
                if (j < cols - 1) {
                    sb.append(",");
                }
                sb.append(" ");
            }
            sb.append("] \n");
        }
        sb.append("]\n");
        return sb.toString();
    }

    public static Matrix scaleMatrix(float sx, float sy, float sz, float sa) {
        Matrix mat = new Matrix(5, 5);
        mat.m = new float[][] {
                new float[] { sx, 0, 0, 0, 0 },
                new float[] { 0, sy, 0, 0, 0 },
                new float[] { 0, 0, sz, 0, 0 },
                new float[] { 0, 0, 0, sa, 0 },
                new float[] { 0, 0, 0, 0, 1 },
        };
        return mat;
    }

    public static Matrix translationMatrix(float x, float y, float z, float a) {
        Matrix mat = new Matrix(5, 5);
        mat.m = new float[][] {
                new float[] { 1, 0, 0, 0, x},
                new float[] { 0, 1, 0, 0, y },
                new float[] { 0, 0, 1, 0, z },
                new float[] { 0, 0, 0, 1, a },
                new float[] { 0, 0, 0, 0, 1 },
        };
        return mat;
    }

    public static Matrix rotXYMatrix(float theta) {
        Matrix mat = new Matrix(5, 5);
        mat.m = new float[][] {
                new float[] { (float) Math.cos(theta), (float)Math.sin(theta), 0, 0, 0 },
                new float[] { -(float) Math.sin(theta), (float)Math.cos(theta), 0, 0, 0 },
                new float[] { 0, 0, 1, 0, 0 },
                new float[] { 0, 0, 0, 1, 0 },
                new float[] { 0, 0, 0, 0, 1 },
        };
        return mat;
    }

    public static Matrix rotYZMatrix(float theta) {
        Matrix mat = new Matrix(5, 5);
        mat.m = new float[][] {
                new float[] { 1, 0, 0, 0, 0 },
                new float[] { 0, (float) Math.cos(theta), (float)Math.sin(theta), 0, 0 },
                new float[] { 0, -(float) Math.sin(theta), (float)Math.cos(theta), 0, 0 },
                new float[] { 0, 0, 0, 1, 0 },
                new float[] { 0, 0, 0, 0, 1 },
        };
        return mat;
    }

    public static Matrix rotXZMatrix(float theta) {
        Matrix mat = new Matrix(5, 5);
        mat.m = new float[][] {
                new float[] { (float) Math.cos(theta), 0, -(float)Math.sin(theta), 0, 0 },
                new float[] { 0, 1, 0, 0, 0 },
                new float[] { (float) Math.sin(theta), 0, (float)Math.cos(theta), 0, 0 },
                new float[] { 0, 0, 0, 1, 0 },
                new float[] { 0, 0, 0, 0, 1 },
        };
        return mat;
    }

    public static Matrix rotXAMatrix(float theta) {
        Matrix mat = new Matrix(5, 5);
        mat.m = new float[][] {
                new float[] { (float) Math.cos(theta), 0, 0, (float)Math.sin(theta), 0 },
                new float[] { 0, 1, 0, 0, 0 },
                new float[] { 0, 0, 1, 0, 0 },
                new float[] { (float) -Math.sin(theta), 0, 0, (float)Math.cos(theta), 0 },
                new float[] { 0, 0, 0, 0, 1 },
        };
        return mat;
    }

    public static Matrix rotYAMatrix(float theta) {
        Matrix mat = new Matrix(5, 5);
        mat.m = new float[][] {
                new float[] { 1, 0, 0, 0, 0 },
                new float[] { 0, (float) Math.cos(theta), 0, (float)-Math.sin(theta), 0 },
                new float[] { 0, 0, 1, 0, 0 },
                new float[] { 0, (float) Math.sin(theta), 0, (float)Math.cos(theta), 0 },
                new float[] { 0, 0, 0, 0, 1 },
        };
        return mat;
    }

    public static Matrix rotZAMatrix(float theta) {
        Matrix mat = new Matrix(5, 5);
        mat.m = new float[][] {
                new float[] { 1, 0, 0, 0, 0 },
                new float[] { 0, 1, 0, 0, 0 },
                new float[] { 0, 0, (float) Math.cos(theta), (float)-Math.sin(theta), 0 },
                new float[] { 0, 0, (float) Math.sin(theta), (float)Math.cos(theta), 0 },
                new float[] { 0, 0, 0, 0, 1 },
        };
        return mat;
    }
}
