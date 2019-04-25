public class ProjectVertex {
    private float[] coordinates;
    //individual floats init
    public ProjectVertex(float x, float y, float z)
    {
        coordinates = new float[] {x,y,z};
    }
    //float array init
    public ProjectVertex(float[] coordinates)
    {
        this.coordinates = coordinates;
    }

}
