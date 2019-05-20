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
        //Cube cube = new Cube(1);
        Obj test = new Obj("suzanne.obj");
        Scene scene = new Scene();
        scene.addMesh(test);
        frame.setScene(scene);

        //Obj test = new Obj("cube.obj");

//        glu.gluPerspective( 45.0f, h, 1.0, 20.0 );
//        gl.glMatrixMode( GL2.GL_MODELVIEW );
//        gl.glLoadIdentity();
    }
}
