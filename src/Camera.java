public class Camera {

    private ProjectVertex location;
    private ProjectVertex camDimensions;
    private Plane viewingPlane;
    public Camera()
    {
        this.camDimensions = new ProjectVertex(6f,6f,5f);
        this.location = new ProjectVertex(0f,0f,0f);
        viewingPlane = new Plane(camDimensions.getX(), camDimensions.getY());
        viewingPlane.translateZ(5);
    }

    /*
    public Camera(ProjectVertex location)
    {
        this.location = location;
    }
    */

    public void draw(Scene s)
    {
        //this is n^2, might want to use a faster algorithm
        Mesh[] meshes = s.getMeshes();
        for(Mesh mesh : meshes)
        {
            ProjectVertex[] vertices = mesh.getVertices();
            for(ProjectVertex vertex : vertices) {

            }
        }
    }

    private void connectProjectedVertices() {

    }

}
