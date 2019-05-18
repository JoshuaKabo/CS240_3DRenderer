import com.jogamp.opengl.GL2;
import com.jogamp.opengl.GLAutoDrawable;
import com.jogamp.opengl.GLCapabilities;
import com.jogamp.opengl.*;
import com.jogamp.opengl.GLProfile;
import com.jogamp.opengl.awt.GLCanvas;
import javax.swing.JFrame;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.List;

public class Main  {

    public static void main(String[] args) throws FileNotFoundException {
        RenderFrame frame = new RenderFrame(600, 600);
        Cube cube = new Cube(1);
        Scene scene = new Scene();
        scene.addMesh(cube);
        frame.setScene(scene);

        Obj test = new Obj("cube.obj");

    }
}
