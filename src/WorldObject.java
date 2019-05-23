public class WorldObject {
    protected Mesh4D mesh;
    protected Matrix translationMat;
    protected Matrix scaleRotMat;

    public WorldObject() {}

    public WorldObject(Mesh4D mesh) {
        this.mesh = mesh;
        translationMat = Matrix.translationMatrix(0, 0, 0, 0);
        scaleRotMat = Matrix.translationMatrix(0, 0, 0, 0);
    }

    public Iterable<Edge4D> getEdges() {
        return mesh.getEdges();
    }

    public Mesh4D getMesh() {
        return mesh;
    }

    public void applyTranslation(Matrix fnMat) {
        translationMat = fnMat.multiply(translationMat);
    }

    public void applyScaleRotation(Matrix fnMat) {
        scaleRotMat = fnMat.multiply(scaleRotMat);
    }

    public Matrix getModelMat() {
        Matrix modelMat = translationMat.multiply(scaleRotMat);
        Matrix localMat = mesh.getLocalMat();
        if (localMat == null) {
            return modelMat;
        } else {
            return modelMat.multiply(localMat);
        }
    }
}
