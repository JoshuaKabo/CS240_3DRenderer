public class WorldObject {
    private Mesh mesh;
    private Matrix modelMat;

    public WorldObject(Mesh mesh) {
        this(mesh, Matrix.translationMatrix(0, 0, 0));
    }

    public WorldObject(Mesh mesh, Matrix modelMat) {
        this.mesh = mesh;
        this.modelMat = modelMat;
    }

    public Mesh getMesh() {
        return mesh;
    }

    public Matrix getModelMatrix() {
        Matrix meshLocalMat = mesh.getLocalMat();

        if (meshLocalMat == null)
            return modelMat;
        else
            return modelMat.multiply(meshLocalMat);
    }

    public void applyTransformation(Matrix transMat) {
        modelMat = transMat.multiply(transMat);
    }

    public void setModelMatrix(Matrix modelMat) {
        this.modelMat = modelMat;
    }
}
