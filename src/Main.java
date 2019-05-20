import com.jogamp.opengl.GL2;
import com.jogamp.opengl.GLAutoDrawable;
import com.jogamp.opengl.GLCapabilities;
import com.jogamp.opengl.*;
import com.jogamp.opengl.GLProfile;
import com.jogamp.opengl.awt.GLCanvas;
import com.jogamp.opengl.glu.GLU;
import com.jogamp.opengl.util.FPSAnimator;

import javax.swing.JFrame;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.List;

public class Main  {

    private static final String[] filenames = {
            "suzanne.obj",
            "cube.obj",
            "crystals.obj",
            "sphere.obj"
    };

    public static void main(String[] args) throws FileNotFoundException {
        RenderFrame frame = new RenderFrame(600, 600);


        Scene scene = new Scene();
        for (String filename : filenames) {
            scene.addMesh(new Obj(filename));
        }
        frame.setScene(scene);

        //Obj test = new Obj("cube.obj");

//        glu.gluPerspective( 45.0f, h, 1.0, 20.0 );
//        gl.glMatrixMode( GL2.GL_MODELVIEW );
//        gl.glLoadIdentity();
    }
}
