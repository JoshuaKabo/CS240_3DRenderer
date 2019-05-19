import org.junit.jupiter.api.Test;

import java.io.FileNotFoundException;

import static org.junit.jupiter.api.Assertions.*;

class ObjTest {

    @Test
    void getVertices() throws FileNotFoundException {
        Obj test = new Obj("cube.obj");
        assertEquals(new Vertex3D(-1,-1,1), test.getVertices()[0]);

    }

    @Test
    void getEdges() {


    }
}