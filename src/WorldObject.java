public class WorldObject {
    private Mesh mesh;
    private Matrix translationMat;
    private Matrix scaleRotMat;

    public WorldObject(Mesh mesh) {
        this.mesh = mesh;
        translationMat = Matrix.translationMatrix(0, 0, 0);
        scaleRotMat = Matrix.translationMatrix(0, 0, 0);
    }

    public Mesh getMesh() {
        return mesh;
    }

    public void applyTranslation(Matrix fnMat) {
        translationMat = fnMat.multiply(translationMat);
    }

    public void applyScaleRotation(Matrix fnMat) {
        scaleRotMat = fnMat.multiply(scaleRotMat);
    }

    public Matrix getTranslationMat() {
        return translationMat;
    }

    public Matrix getScaleRotMat() {
        return scaleRotMat;
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
