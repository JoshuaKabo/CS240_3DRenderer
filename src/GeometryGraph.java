import java.util.ArrayList;

public class GeometryGraph {

    /*
    To make this undirected, each pair just stores one reference to the other
    rather than A has a reference to B and B has a reference to A,
    just A has a reference to B
    and when making links, make sure they aren't already linked
     */

    private ArrayList<GraphVertex3D> vertices;

    //public void add()

    public void link(int index1, int index2) {
        //get the actual vertices at those indices
        GraphVertex3D vertex1 = vertices.get(index1);
        GraphVertex3D vertex2 = vertices.get(index2);
        //if neither of them is already linked, link them
        if(!vertex1.isLinkedTo(index2) && !vertex2.isLinkedTo(index1)) {
            vertices.get(index1).link(index2);
        }
    }

    public void link(GraphVertex3D vertex1, GraphVertex3D vertex2) {
        //get the indices of these vertices
        int index1 = vertices.indexOf(vertex1);
        int index2 = vertices.indexOf(vertex2);
        //surrounded in a try catch in case a null pointer exception occurs
        try {
            //if neither is already linked, link them
            if(!vertex1.isLinkedTo(index2) && !vertex2.isLinkedTo(index1)) {
                vertices.get(index1).link(index2);
            }
        }
        //if there was a null pointer exception, then a vertex wasn't found
        catch (NullPointerException e) {
            System.out.println("Vertex not found");
        }

    }
}
