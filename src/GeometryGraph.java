import java.util.ArrayList;
import java.util.LinkedList;

public class GeometryGraph {

    /*
    To make this undirected, each pair just stores one reference to the other
    rather than A has a reference to B and B has a reference to A,
    just A has a reference to B
    and when making links, make sure they aren't already linked
     */

    private ArrayList<GraphVertex3D> vertices;

    private int numEdges;

    public GeometryGraph() {
        vertices = new ArrayList<>();
        numEdges = 0;
    }

    public void add(GraphVertex3D v) {
        vertices.add(v);
    }

    public void link(int index1, int index2) {
        //get the actual vertices at those indices
        GraphVertex3D vertex1 = vertices.get(index1);
        GraphVertex3D vertex2 = vertices.get(index2);
        //if neither of them is already linked, link them
        if(!vertex1.isLinkedTo(index2) && !vertex2.isLinkedTo(index1)) {
            vertices.get(index1).link(index2);

            numEdges++;
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
                numEdges++;
            }
        }
        //if there was a null pointer exception, then a vertex wasn't found
        catch (NullPointerException e) {
            System.out.println("Vertex not found");
        }

    }

    public Vertex3D[] getVertices() {
        //toArray had different casting problems, so I'm going to do it manually
        //return (Vertex3D[])vertices.toArray();
        Vertex3D[] returnVertices = new Vertex3D[vertices.size()];
        for (int i = 0; i < returnVertices.length; i++) {
            returnVertices[i] = vertices.get(i);
        }
        return returnVertices;
    }

    //this is a little clumsy because I didn't want to create an arraylist and convert to an array, which would have
    //been cleaner
    public Edge3D[] getEdges() {
        Edge3D[] edges = new Edge3D[numEdges];
        int arrayCursor = 0;
        int verticesSize = vertices.size();
        for (int i = 0; i < verticesSize; i++) {
            //if there's a connection there, add it to the edge array
            LinkedList<Integer> adjacent = vertices.get(i).getAdjacentVertices();
            for (int j = 0; j < adjacent.size(); j++) {

                edges[arrayCursor] = new Edge3D(vertices.get(i),vertices.get(adjacent.get(j)));
                arrayCursor++;
            }
        }
        return edges;
    }
}
