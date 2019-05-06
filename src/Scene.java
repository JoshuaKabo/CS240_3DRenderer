import java.util.ArrayList;

public class Scene {
    private ArrayList<Mesh> Meshes;
    public Scene(Mesh m)
    {
        Meshes.add(m);
    }
    public Mesh[] getMeshes()
    {
        return (Mesh[])Meshes.toArray();
    }
}
