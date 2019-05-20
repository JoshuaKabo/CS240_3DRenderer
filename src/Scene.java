import java.util.ArrayList;
import java.util.List;

public class Scene {
    private List<WorldObject> objects;

    public Scene() {
        this.objects = new ArrayList<WorldObject>();
    }

    public void addMesh(Mesh mesh) {
        WorldObject wo = new WorldObject(mesh);
        wo.applyTranslation(Matrix.translationMatrix(0, 0, -2f));
        objects.add(wo);
    }

    public WorldObject peek() {
        if (objects.size() == 0) return null;
        return objects.get(0);
    }

    public WorldObject dequeue() {
        if (objects.size() == 0) return null;
        return objects.remove(0);
    }

    public void enqueue(WorldObject obj) {
        objects.add(obj);
    }

    public List<WorldObject> getObjects() {
        return objects;
    }
}
