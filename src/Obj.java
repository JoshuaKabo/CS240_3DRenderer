import java.io.File;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;
import java.util.Vector;

public class Obj {
    private File objFile;
    private Scanner s;
    private ArrayList<Vertex3D> vertices = new ArrayList<>();

    /*
    So, if I use an arraylist of arraylists, the rows represent the vertices, and the columns are the links

    I may want to create a specialized class, to hold the location itself, and it's own linked list to hold connections
     */

    public Obj(String fileName) throws FileNotFoundException {

        objFile = new File(fileName);
        s = new Scanner(objFile);

        //go through each line
        while(s.hasNextLine()) {
            //grab the current line
            String currentLine = s.nextLine();
            //make sure it's not not empty
            if(currentLine.length() > 0) {
                //if it's a vertex
                if (currentLine.substring(0, 2).equals("v ")) {
                    currentLine = currentLine.substring(2, currentLine.length());
                    String[] rawNumbers = currentLine.split(" ");
                    float[] axisAmounts = new float[rawNumbers.length];
                    for (int i = 0; i < rawNumbers.length; i++) {
                        axisAmounts[i] = Float.parseFloat(rawNumbers[i]);
                    }
                    vertices.add(new Vertex3D(axisAmounts[0], axisAmounts[1], axisAmounts[2]));
                }
                //if its a face
                else if (currentLine.substring(0, 2).equals("f ")) {
                    currentLine = currentLine.substring(2, currentLine.length());

                }
            }
        }
    }
}
