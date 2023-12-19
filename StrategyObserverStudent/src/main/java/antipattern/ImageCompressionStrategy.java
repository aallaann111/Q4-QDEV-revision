package antipattern;

public class ImageCompressionStrategy implements DataCompressionStrategy {
    private float quality = 0.5f;

    @Override
    public String compress(Data data) {
        if (data instanceof ImageData) {
            ImageData imageData = (ImageData) data;
            // Logique de compression d'image
            // ...
            return "Compressed image: " + imageData.getFile().getName();
        }
        return "";
    }
}