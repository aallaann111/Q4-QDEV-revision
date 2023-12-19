package antipattern;


import java.io.File;
import java.io.IOException;
import java.util.ArrayList;

public class App {
    public static void main(String[] args) throws IOException {
        // Vider les dossiers avec les contenus compressés
        File compressionImageFolder = new File("./data/compressed/jpg");
        File compressionAudioFolder = new File("./data/compressed/wav");
        File compressionVideoFolder = new File("./data/compressed/mp4");
        FileWriter.clearFolder(compressionImageFolder);
        FileWriter.clearFolder(compressionAudioFolder);
        FileWriter.clearFolder(compressionVideoFolder);

        // Création des données à traiter
        ArrayList<Data> dataList = new ArrayList<>();
        for (int i = 1; i <= 10; i++) {
            dataList.add(new ImageData(new File("./data/jpg/image" + i + ".jpg")));
            dataList.add(new AudioData(new File("./data/wav/audio" + i + ".wav")));
            dataList.add(new VideoData(new File("./data/mp4/video" + i + ".mp4")));
        }

        // Création du DataCompression avec la stratégie appropriée
        DataCompressionStrategy imageCompressionStrategy = new ImageCompressionStrategy();
        DataCompressionStrategy audioCompressionStrategy = new AudioCompressionStrategy();
        DataCompressionStrategy videoCompressionStrategy = new VideoCompressionStrategy();

        DataCompression imageCompression = new DataCompression(imageCompressionStrategy);
        DataCompression audioCompression = new DataCompression(audioCompressionStrategy);
        DataCompression videoCompression = new DataCompression(videoCompressionStrategy);

        // Traitement des données avec la stratégie de traitement appropriée
        for (Data data : dataList) {
            String result = "";
            if (data instanceof ImageData) {
                result = imageCompression.compressData(data);
            } else if (data instanceof AudioData) {
                result = audioCompression.compressData(data);
            } else if (data instanceof VideoData) {
                result = videoCompression.compressData(data);
            }
            // Vérification du résultat du traitement
            System.out.println("Résultat du traitement : " + result);
        }
    }
}

// Définition de la classe DataCompression

