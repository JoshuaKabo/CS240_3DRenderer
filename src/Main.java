import java.io.FileNotFoundException;


public class Main  {

    private static final String[] filenames = {
            "suzanne.obj",
            "cube.obj",
//            "crystals.obj",
            "sphere.obj"
    };

    public static void main(String[] args) throws FileNotFoundException {
        RenderFrame frame = new RenderFrame(600, 600);

        Scene scene = new Scene();
        scene.addMesh(new Tesseract(1));
        for (String filename : filenames) {
            scene.addMesh(new Obj(filename));
        }
        frame.setScene(scene);
    }

}
