package antipattern;

public class VideoCompressionStrategy implements DataCompressionStrategy {
    @Override
    public String compress(Data data) {
        if (data instanceof VideoData) {
            VideoData videoData = (VideoData) data;
            // Logique de compression vidéo
            // ...
            return "Compressed video: " + videoData.getFile().getName();
        }
        return "";
    }
}