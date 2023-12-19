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
        if (compressionStrategy == null) {
            throw new IllegalStateException("DataCompressionStrategy not set. Please call setDataCompressionStrategy method.");
        }

        return compressionStrategy.compress(data);
    }

    public void setDataCompressionStrategy(DataCompressionStrategy strategy) {
        this.compressionStrategy = strategy;
    }

}
