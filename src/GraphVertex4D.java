import java.util.LinkedList;

public class GraphVertex4D extends Vertex4D {

    private LinkedList<Integer> adjacentVertices = new LinkedList<>();
//    private static int islinkedtrack = 0;
//    private static int linktrack = 0;

    public GraphVertex4D(float x, float y, float z, float a) {
        super(x, y, z, a);
    }

    public void link(int toLink) {
//        System.out.println("Linktrack: " + ++linktrack);
        //prevent storing a link that already exists
        if(!isLinkedTo(toLink)) {
            adjacentVertices.add(toLink);
        }
    }

    //checks the list to see if it's already linked to that vertex
    public boolean isLinkedTo(int neighbor) {
//        System.out.println("islink: " + ++islinkedtrack);
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
