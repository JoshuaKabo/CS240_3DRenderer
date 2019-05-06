import java.util.ArrayList;

public class Scene {
    private ArrayList<Mesh> Meshes;
    public Scene(Mesh m)
    {
        Meshes = new ArrayList<>();
        Meshes.add(m);
    }
    public ArrayList<Mesh> getMeshes()
    {
        return Meshes;
    }
}
