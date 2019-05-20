import java.util.LinkedList;

public class GraphVertex3D extends Vertex3D {

    private LinkedList<Integer> adjacentVertices = new LinkedList<>();
    private static int islinkedtrack = 0;
    private static int linktrack = 0;

    public GraphVertex3D() {
        this(0, 0, 0);
    }

    public GraphVertex3D(float x, float y, float z) {
        super(x,y,z);
    }

    public void link(int toLink) {
        System.out.println("Linktrack: " + ++linktrack);
        //prevent storing a link that already exists
        if(!isLinkedTo(toLink)) {
            adjacentVertices.add(toLink);
        }
    }

    //checks the list to see if it's already linked to that vertex
    public boolean isLinkedTo(int neighbor) {
        System.out.println("islink: " + ++islinkedtrack);
        if(adjacentVertices != null) {
            return adjacentVertices.contains(neighbor);
        }
        else return false;
    }

    public LinkedList<Integer> getAdjacentVertices()
    {
        return adjacentVertices;
    }
}
