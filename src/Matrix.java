public class Matrix {
    public float[][] m;
    private int rows, cols;

//    public static Matrix rotMatrix(float alpha, float beta, float gamma) {
//        Matrix mat = Matrix.rotMatrixX(alpha);
//        mat = mat.multiply(Matrix.rotMatrixY(beta));
//        mat = mat.multiply(Matrix.rotMatrixZ(gamma));
//        mat.m = new float[][] {
//                new float[] { (float) Math.cos(theta), -(float)Math.sin(theta), 0 },
//                new float[] { (float) Math.sin(theta), (float)Math.cos(theta), 0 },
//                new float[] { 0, 0, 1 },
//        };
//        return mat;
//    }

    public Matrix(int rows, int cols) {
        this.rows = rows;
        this.cols = cols;
//        System.out.println(rows + ", " + cols);
        m = new float[rows][cols];
    }

    public Matrix(Vertex3D vert) {
        this(4, 1);
        m[0][0] = vert.getX();
        m[1][0] = vert.getY();
        m[2][0] = vert.getZ();
        m[3][0] = 1;
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

        //this.cols = 3
        //this.rows = 3
        //other.cols = 1
        //other.rows = 3
        if (cols != other.getRows()) {
            return null;
        }

//        System.out.println(other.getCols());

        Matrix result = new Matrix(rows, other.getCols());
        for (int i = 0; i < rows; i++) {
            for (int j = 0; j < other.getCols(); j++) {
//                System.out.println(i + ", " + j);
                float sum = 0;
                for (int k = 0; k < cols; k++) {
                    sum += m[i][k] * other.getVal(k, j);
                }
                result.setVal(i, j, sum);
            }
        }

        return result;
    }

    public Matrix multiplyScalar(float k) {
        Matrix mat = new Matrix(this);
        for (int i = 0; i < rows; i++) {
            for (int j = 0; j < cols; j++) {
                mat.setVal(i, j, k * mat.getVal(i, j));
            }
        }
        return mat;
    }

    public Vertex3D multiplyVert(Vertex3D other) {
//        System.out.println(this);
        Matrix vm = new Matrix(other);
//        System.out.println(vm);
        Matrix tmp = this.multiply(vm);
//        System.out.println(tmp);
        return new Vertex3D(tmp.getVal(0, 0), tmp.getVal(1, 0), tmp.getVal(2, 0));
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

    public static Matrix rotXMatrix(float theta) {
        Matrix mat = new Matrix(4, 4);
        mat.m = new float[][] {
                new float[] { 1, 0, 0, 0 },
                new float[] { 0, (float) Math.cos(theta), (float)Math.sin(theta), 0, 0 },
                new float[] { 0, -(float) Math.sin(theta), (float)Math.cos(theta), 0, 0 },
                new float[] { 0, 0, 0, 1 },
        };
        return mat;
    }

    public static Matrix rotYMatrix(float theta) {
        Matrix mat = new Matrix(4, 4);
        mat.m = new float[][] {
                new float[] { (float) Math.cos(theta), 0, -(float)Math.sin(theta), 0 },
                new float[] { 0, 1, 0, 0 },
                new float[] { (float) Math.sin(theta), 0, (float)Math.cos(theta), 0 },
                new float[] { 0, 0, 0, 1 },
        };
        return mat;
    }

    public static Matrix rotZMatrix(float theta) {
        Matrix mat = new Matrix(4, 4);
        mat.m = new float[][] {
                new float[] { (float) Math.cos(theta), (float)Math.sin(theta), 0, 0 },
                new float[] { -(float) Math.sin(theta), (float)Math.cos(theta), 0, 0 },
                new float[] { 0, 0, 1, 0 },
                new float[] { 0, 0, 0, 1 },
        };
        return mat;
    }

    public static Matrix translationMatrix(float x, float y, float z) {
        Matrix mat = new Matrix(4, 4);
        mat.m = new float[][] {
                new float[] { 1, 0, 0, x},
                new float[] { 0, 1, 0, y },
                new float[] { 0, 0, 1, z },
                new float[] { 0, 0, 0, 1 },
        };
        return mat;
    }

    public static Matrix scaleMatrix(float sx, float sy, float sz) {
        Matrix mat = new Matrix(4, 4);
        mat.m = new float[][] {
                new float[] { sx, 0, 0, 0},
                new float[] { 0, sy, 0, 0 },
                new float[] { 0, 0, sz, 0 },
                new float[] { 0, 0, 0, 1 },
        };
        return mat;
    }
}
