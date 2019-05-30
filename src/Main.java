import java.io.FileNotFoundException;


public class Main  {

    private static final String[] filenames = {
            "suzanne.obj",
            "cube.obj",
            "sphere.obj"
    };

    public static void main(String[] args) throws FileNotFoundException {
        RenderFrame frame = new RenderFrame(1000, 1000);

        Scene scene = new Scene();
        scene.addMesh(new Tesseract(1));
        for (String filename : filenames) {
            try {

                scene.addMesh(new Obj(filename));
            }
            catch (FileNotFoundException e) {
                for (int i = 0; i < filenames.length; i++) {
                    String withPath = "/Users/liamdean/Documents/3DFOLDER/CS240_3DRenderer/" + filenames[i];
                    filenames[i] = withPath;
                    scene.addMesh(new Obj(withPath));
                }
            }
        }
        frame.setScene(scene);
    }

}
