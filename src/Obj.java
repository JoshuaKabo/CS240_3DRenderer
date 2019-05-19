import java.io.File;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;
import java.util.Vector;

public class Obj extends Mesh {
    private File objFile;
    private Scanner s;
    private GeometryGraph geoGraph = new GeometryGraph();


    public Obj(String fileName) throws FileNotFoundException {
        float l2 = 1 / 2;
        localMat = Matrix.translationMatrix(-l2, -l2, -l2);

        objFile = new File(fileName);
        s = new Scanner(objFile);

        //go through each line
        while(s.hasNextLine()) {
            //grab the current line
            String currentLine = s.nextLine();
            //make sure it's not not empty
            if(currentLine.length() > 0) {
                //if it's a vertex. This part sets up the vertices of the graph
                if (currentLine.substring(0, 2).equals("v ")) {
                    //convert the string to a Vertex3D
                    currentLine = currentLine.substring(2);
                    String[] rawNumbers = currentLine.split(" ");
                    float[] axisAmounts = new float[rawNumbers.length];
                    for (int i = 0; i < rawNumbers.length; i++) {
                        axisAmounts[i] = Float.parseFloat(rawNumbers[i]);
                    }
                    geoGraph.add(new GraphVertex3D(axisAmounts[0], axisAmounts[1], axisAmounts[2]));
                }
                //if its a face, this part sets up the edges of the graph
                else if (currentLine.substring(0, 2).equals("f ")) {
                    currentLine = currentLine.substring(2);
                    String[] rawEdges = currentLine.split(" ");
                    //now I have an array of strings that look like this: {"1//1","2//1"}

                    //to -1 to prevent oob
                    for (int i = 0; i < rawEdges.length-1; i+=2) {

                        //grab just the vertex, ignoring the normal, then make an edge
                        String[] vertexAndNormal = rawEdges[i].split("//");
                        int v1 = Integer.parseInt(vertexAndNormal[0]);
                        String[] vertexAndNormal2 = rawEdges[i+1].split("//");
                        int v2 = Integer.parseInt(vertexAndNormal2[0]);

                        //subtract one, because 0 is the storage base
                        geoGraph.link(v1-1, v2-1);
                    }
                }
            }
        }
    }

    public Vertex3D[] getVertices() {
        return geoGraph.getVertices();
    }

    public Edge3D[] getEdges() {
        return geoGraph.getEdges();
    }
}
