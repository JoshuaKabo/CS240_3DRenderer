import com.jogamp.opengl.GL2;
import com.jogamp.opengl.GL4;
import com.jogamp.opengl.GLAutoDrawable;

import java.util.ArrayList;

public class Camera {

    private ProjectVertex location;
    private ProjectVertex camDimensions;
    private Plane viewingPlane;
    private float planeDist;
    public Camera()
    {
        this.camDimensions = new ProjectVertex(6f,6f,5f);
        this.location = new ProjectVertex(0f,0f,0f);
        viewingPlane = new Plane(camDimensions.getX(), camDimensions.getY());
        viewingPlane.translateZ(5);
        planeDist = camDimensions.getZ();
    }

    /*
    public Camera(ProjectVertex location)
    {
        this.location = location;
    }
    */

    public void draw(Scene s, GLAutoDrawable glAutoDrawable)
    {
        //this is n^2, might want to use a faster algorithm if possible
        Mesh[] meshes = s.getMeshes();
        for(Mesh mesh : meshes)
        {
            ArrayList<ProjectVertex> projectedVertices = new ArrayList<>();
            ProjectVertex[] vertices = mesh.getVertices();
            for(ProjectVertex vertex : vertices) {
                projectedVertices.add( new ProjectVertex(
                        (planeDist * (vertex.getX()/planeDist) ),
                        (planeDist * (vertex.getX()/planeDist) ),
                        planeDist) );
            }
            connectProjectedVertices(glAutoDrawable, (ProjectVertex[]) projectedVertices.toArray(), mesh.getEdges());
        }
    }

    private void connectProjectedVertices(GLAutoDrawable glAutoDrawable, ProjectVertex[] projectedVertices, int[][] edges) {
        GL2 gl = glAutoDrawable.getGL().getGL2();
        gl.glClear(GL4.GL_COLOR_BUFFER_BIT | GL4.GL_DEPTH_BUFFER_BIT);

        gl.glColor3f(1.0f, 1.0f, 1.0f );
        gl.glLineWidth(2f);
        gl.glBegin(GL2.GL_LINES);

        for (int i = 0; i < edges.length; i++) {
            gl.glVertex3f( projectedVertices[edges[i][0]].getX(), projectedVertices[edges[i][1]].getY(), 0.0f );
        }

        gl.glEnd();
        gl.glFlush();
    }

}
