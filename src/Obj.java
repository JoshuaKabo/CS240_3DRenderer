import java.io.File;
import java.io.FileNotFoundException;
import java.util.Scanner;

public class Obj {
    private File objFile;
    private Scanner s;

    public Obj(String fileName) throws FileNotFoundException {
        objFile = new File(fileName);
        s = new Scanner(objFile);

        while(s.hasNextLine()) {
            String currentLine = s.nextLine();
            if(currentLine.substring(0,1).equals("v")) {
                currentLine = currentLine.substring(2,currentLine.length());

            }
            else if(currentLine.substring(0,1).equals("f")) {

            }
        }
    }
}
