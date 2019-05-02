public class Camera {
    private ProjectVertex location;
    private Plane viewingPlane;
    public Camera()
    {
        this.location = new ProjectVertex(0f,0f,0f);
        this.viewingPlane = new Plane(6,6);
    }
    public Camera(ProjectVertex location)
    {
        this.location = location;
    }

    private void initializeProjectionPlane() {}

    public void draw(Scene s)
    {
        Mesh[] meshes = s.getMeshes();
    }

    private void connectProjectedVertices() {

    }

}
