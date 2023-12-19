package antipattern;

public class AudioCompressionStrategy implements DataCompressionStrategy {
    @Override
    public String compress(Data data) {
        if (data instanceof AudioData) {
            AudioData audioData = (AudioData) data;
            // Logique de compression audio
            // ...
            return "Compressed audio: " + audioData.getFile().getName();
        }
        return "";
    }
}
