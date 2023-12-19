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
        DataCompression dataCompression = new DataCompression();

        // Définir la stratégie appropriée en fonction du type de données
        for (Data data : dataList) {
            if (data instanceof ImageData) {
                dataCompression.setDataCompressionStrategy(new ImageCompressionStrategy());
            } else if (data instanceof AudioData) {
                dataCompression.setDataCompressionStrategy(new AudioCompressionStrategy());
            } else if (data instanceof VideoData) {
                dataCompression.setDataCompressionStrategy(new VideoCompressionStrategy());
            }

            // Traitement des données avec la stratégie de traitement appropriée
            String result = dataCompression.compressData(data);
            // Vérification du résultat du traitement
            System.out.println("Résultat du traitement : " + result);
        }
    }
}



// Définition de la classe DataCompression

