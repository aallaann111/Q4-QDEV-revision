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
        ArrayList<ImageData> imageList = new ArrayList<>();
        ArrayList<AudioData> audioList = new ArrayList<>();
        ArrayList<VideoData> videoList = new ArrayList<>();

        for (int i = 1; i <= 10; i++) {
            imageList.add(new ImageData(new File("./data/jpg/image" + i + ".jpg")));
            audioList.add(new AudioData(new File("./data/wav/audio" + i + ".wav")));
            videoList.add(new VideoData(new File("./data/mp4/video" + i + ".mp4")));
        }
        // Création du DataCompression
        DataCompression dataCompression = new DataCompression();


        // Traitement des données avec la stratégie de traitement appropriée
        String imageResult, soundResult;
        String videoResult= "";

        for (int i = 0; i < 10; i++) {
            imageResult = dataCompression.compressData(imageList.get(i));
            soundResult = dataCompression.compressData(audioList.get(i));
            if (i < 5)
                videoResult = dataCompression.compressData(videoList.get(i));
            // Vérification du résultat du traitement
            System.out.println("Résultat du traitement de l'image : " + imageResult);
            System.out.println("Résultat du traitement du son : " + soundResult);
            if (i < 5)
                System.out.println("Résultat du traitement de la vidéo : " + videoResult);

        }

    }
}

// Définition de la classe DataCompression

