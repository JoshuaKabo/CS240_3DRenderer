import java.io.File;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;
import java.util.Vector;

public class Obj {
    private File objFile;
    private Scanner s;
    private GeometryGraph geoGraph = new GeometryGraph();

    public Obj(String fileName) throws FileNotFoundException {

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
                    currentLine = currentLine.substring(2, currentLine.length());
                    String[] rawNumbers = currentLine.split(" ");
                    float[] axisAmounts = new float[rawNumbers.length];
                    for (int i = 0; i < rawNumbers.length; i++) {
                        axisAmounts[i] = Float.parseFloat(rawNumbers[i]);
                    }
                    geoGraph.add(new GraphVertex3D(axisAmounts[0], axisAmounts[1], axisAmounts[2]));
                }
                //if its a face, this part sets up the edges of the graph
                else if (currentLine.substring(0, 2).equals("f ")) {
                    currentLine = currentLine.substring(2, currentLine.length());
                    String[] rawEdges = currentLine.split(" ");
                    //now I have an array of strings that look like this: {"1//1","2//1"}
                    for (int i = 0; i < rawEdges.length; i++) {
                        //this will make many arrays that look like this {1,1}, {2,1}
                        String[] edgeStrings = rawEdges[i].split("//");
                        //this will set up the actual edges between vertices in the graph
                        geoGraph.link(Integer.parseInt(edgeStrings[0]), Integer.parseInt(edgeStrings[0]));
                    }
                }
            }
        }
    }
}
