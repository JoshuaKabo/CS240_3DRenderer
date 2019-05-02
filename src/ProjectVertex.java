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

    public float getX(){
        return coordinates[0];
    }
    public float getY(){
        return coordinates[1];
    }
    public float getZ(){
        return coordinates[2];
    }

}
