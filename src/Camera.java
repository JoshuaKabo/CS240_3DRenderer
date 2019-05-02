public class Camera {

    private ProjectVertex location;
    private ProjectVertex camDimensions;
    private Plane viewingPlane;
    public Camera()
    {
        this.camDimensions = new ProjectVertex(6f,6f,5f);
        this.location = new ProjectVertex(0f,0f,0f);
        this.viewingPlane = new Plane(camDimensions.getX(), camDimensions.getY());

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
