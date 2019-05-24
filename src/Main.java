import java.io.FileNotFoundException;


public class Main  {

    private static final String[] filenames = {
            "/Users/liamdean/Documents/3DFOLDER/CS240_3DRenderer/suzanne.obj",
            "/Users/liamdean/Documents/3DFOLDER/CS240_3DRenderer/cube.obj",
            "/Users/liamdean/Documents/3DFOLDER/CS240_3DRenderer/sphere.obj"
    };

    public static void main(String[] args) throws FileNotFoundException {
        RenderFrame frame = new RenderFrame(1000, 1000);

        Scene scene = new Scene();
        scene.addMesh(new Tesseract(1));
        for (String filename : filenames) {
            scene.addMesh(new Obj(filename));
        }
        frame.setScene(scene);
    }

}
