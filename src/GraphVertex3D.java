import java.util.LinkedList;

public class GraphVertex3D extends Vertex3D {

    private LinkedList<Integer> adjacentVertices;

    public GraphVertex3D() {
        this(0, 0, 0);
    }

    public GraphVertex3D(float x, float y, float z) {
        super(x,y,z);
    }

    public void link(int toLink) {
        //prevent storing a link that already exists
        if(!isLinkedTo(toLink)) {
            adjacentVertices.add(toLink);
        }
    }

    //checks the list to see if it's already linked to that vertex
    public boolean isLinkedTo(int neighbor) {
        return adjacentVertices.contains(neighbor);
    }

    public LinkedList<Integer> getAdjacentVertices()
    {
        return adjacentVertices;
    }
}
