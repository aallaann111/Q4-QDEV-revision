package antipattern;


import javax.imageio.ImageIO;
import javax.imageio.ImageWriteParam;
import javax.imageio.ImageWriter;
import javax.imageio.plugins.jpeg.JPEGImageWriteParam;
import javax.imageio.stream.FileImageOutputStream;
import javax.imageio.stream.ImageOutputStream;
import java.awt.image.BufferedImage;
import java.io.*;
import java.time.LocalTime;
import java.time.format.DateTimeFormatter;

public class DataCompression {

    private float quality = 0.5f;
    private DataCompressionStrategy compressionStrategy;

    public DataCompression(DataCompressionStrategy compressionStrategy) {
        this.compressionStrategy = compressionStrategy;
    }


    public String compressData(Data data) {
        return compressionStrategy.compress(data);
    }

    private String compressVideoData(VideoData data) {


        String result = "./data/compressed/mp4/c-" + data.getFile().getName();
        try {
            FileInputStream inputStream = new FileInputStream(data.getFile().getAbsolutePath());

            FileOutputStream outputStream = new FileOutputStream(result);


            byte[] buffer = new byte[4096];
            int length;

            while (true) {

                if (!((length = inputStream.read(buffer)) > 0)) break;
                outputStream.write(buffer, 0, length);


            }


            inputStream.close();

            outputStream.close();

        } catch (FileNotFoundException e) {
            throw new RuntimeException(e);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
        updateVideo(result);
        return result;
    }

    private String compressAudioData(AudioData data) {


        String result = "./data/compressed/wav/c-" + data.getFile().getName();
        try {
            FileInputStream inputStream = new FileInputStream(data.getFile().getAbsolutePath());

            FileOutputStream outputStream = new FileOutputStream(result);


            byte[] buffer = new byte[4096];
            int length;

            while (true) {

                if (!((length = inputStream.read(buffer)) > 0)) break;
                outputStream.write(buffer, 0, length);


            }


            inputStream.close();

            outputStream.close();

        } catch (FileNotFoundException e) {
            throw new RuntimeException(e);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
        updateAudio(result);
        return result;
    }

    private String compressImageData(ImageData data) {

        String result = "empty";
        try {
            // Lecture de l'image d'entrée
            BufferedImage inputImage = ImageIO.read(data.getFile());
            // Récupération de l'instance de l'encodeur JPEG
            ImageWriter writer = ImageIO.getImageWritersByFormatName("jpg").next();
            // Définition des paramètres d'encodage
            ImageWriteParam params = new JPEGImageWriteParam(null);
            params.setCompressionMode(ImageWriteParam.MODE_EXPLICIT);
            params.setCompressionQuality(quality);
            // Définition du flux de sortie
            File output = new File("./data/compressed/jpg/c-" + data.getFile().getName());
            ImageOutputStream ios = new FileImageOutputStream(output);
            writer.setOutput(ios);
            // Encodage de l'image
            writer.write(null, new javax.imageio.IIOImage(inputImage, null, null), params);
            // Fermeture du flux de sortie
            ios.close();
            writer.dispose();

            result = output.getAbsolutePath();
        } catch (IOException e) {
            System.out.println("Error while compressing image file " + data.getFile().getName());
        }

        updateImage(result);
        return result;
    }


    public void updateImage(String fileName) {
        // Code d'observation pour les images
        if (fileName.contains("/jpg/")) {
            LocalTime now = LocalTime.now();

            // Formater l'heure dans le format souhaité (hh:mm:ss)
            DateTimeFormatter formatter = DateTimeFormatter.ofPattern("HH:mm:ss");
            String formattedTime = now.format(formatter);
            String content = formattedTime;


            System.out.println("-----------------------------------------------------------------------");
            System.out.println("Observation des audios...");

            content += " ::Fichier compressé : " + fileName;
            FileWriter.writeToFile("./data/compressed/jpg/ImageCompressionObserver.txt", content);
            System.out.println("-----------------------------------------------------------------------");
        }
    }

    public void updateAudio(String fileName) {         // Créer un objet AudioInputStream à partir du fichier audio WAV
        // Code d'observation pour les audios
        if (fileName.contains("/wav/")) {
            LocalTime now = LocalTime.now();

            // Formater l'heure dans le format souhaité (hh:mm:ss)
            DateTimeFormatter formatter = DateTimeFormatter.ofPattern("HH:mm:ss");
            String formattedTime = now.format(formatter);
            String content = formattedTime;


            System.out.println("-----------------------------------------------------------------------");
            System.out.println("Observation des audios...");

            content += " ::Fichier compressé : " + fileName;
            FileWriter.writeToFile("./data/compressed/wav/AudioCompressionObserver.txt", content);
            System.out.println("-----------------------------------------------------------------------");

        }
    }

    public void updateVideo(String fileName) {
        // Code d'observation pour les videos
        if (fileName.contains("/mp4/")) {
            LocalTime now = LocalTime.now();


            // Formater l'heure dans le format souhaité (hh:mm:ss)
            DateTimeFormatter formatter = DateTimeFormatter.ofPattern("HH:mm:ss");
            String formattedTime = now.format(formatter);
            String content = formattedTime;


            System.out.println("-----------------------------------------------------------------------");
            System.out.println("Observation des audios...");

            content += " ::Fichier compressé : " + fileName;
            FileWriter.writeToFile("./data/compressed/mp4/VideoCompressionObserver.txt", content);
            System.out.println("-----------------------------------------------------------------------");
        }
    }
}
