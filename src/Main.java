import com.jogamp.opengl.GL2;
import com.jogamp.opengl.GLAutoDrawable;
import com.jogamp.opengl.GLCapabilities;
import com.jogamp.opengl.*;
import com.jogamp.opengl.GLProfile;
import com.jogamp.opengl.awt.GLCanvas;
import javax.swing.JFrame;

public class Main  {

    public static void main(String[] args) {
        RenderFrame frame = new RenderFrame(600, 600);
        Cube cube = new Cube(1);
        Scene scene = new Scene();
        scene.addMesh(cube);
        frame.setScene(scene);
    }
}
