# Question 3 :

La solution avec le patron de conception Stratégie résout les problèmes de la solution antipattern et respecte les principes SOLID, en particulier le Single Responsibility Principle (SRP) et l'Open/Closed Principle (OCP).

### Utilisation du patron de conception Stratégie :

1. **Interface `DataCompressionStrategy`:**
    - Une interface `DataCompressionStrategy` est créée avec la méthode `processData(Data data): String`. Cette interface définit le contrat que toutes les stratégies de compression doivent suivre.

   ```java
   public interface DataCompressionStrategy {
       String processData(Data data);
   }
   ```

2. **Classes concrètes pour chaque type de traitement :**
    - Des classes concrètes sont créées pour chaque type de traitement (image, audio, vidéo) en implémentant l'interface `DataCompressionStrategy`. Par exemple, `ImageCompressionStrategy`, `AudioCompressionStrategy`, `VideoCompressionStrategy`.

   ```java
   public class ImageCompressionStrategy implements DataCompressionStrategy {
       // Implémentation de la compression d'image
       // ...
   }

   public class AudioCompressionStrategy implements DataCompressionStrategy {
       // Implémentation de la compression audio
       // ...
   }

   public class VideoCompressionStrategy implements DataCompressionStrategy {
       // Implémentation de la compression vidéo
       // ...
   }
   ```

3. **Modification de la classe `DataCompression`:**
    - La classe `DataCompression` utilise une instance de l'interface `DataCompressionStrategy` au lieu d'avoir des méthodes spécifiques pour chaque type de compression.

   ```java
   public class DataCompression {
       private DataCompressionStrategy compressionStrategy;

       public DataCompression(DataCompressionStrategy compressionStrategy) {
           this.compressionStrategy = compressionStrategy;
       }

       public String compressData(Data data) {
           return compressionStrategy.processData(data);
       }

       // Autres méthodes...
   }
   ```

### Avantages et Respect des Principes SOLID :

1. **Respect du SRP (Single Responsibility Principle):**
    - Chaque classe concrète de stratégie (Image, Audio, Vidéo) a la responsabilité unique de définir comment compresser un type spécifique de données. La classe `DataCompression` se concentre uniquement sur le choix de la stratégie et la délégation du travail à la stratégie appropriée.

2. **Respect de l'OCP (Open/Closed Principle):**
    - L'ajout de nouveaux types de compression se fait par l'ajout de nouvelles classes concrètes implémentant l'interface `DataCompressionStrategy`, sans modifier la classe `DataCompression`. La classe `DataCompression` est ouverte à l'extension mais fermée à la modification.

3. **Meilleure Séparation des Préoccupations :**
    - Les algorithmes de compression sont complètement séparés de la classe principale `DataCompression`, ce qui permet une meilleure modularité et facilite l'extension et la maintenance du code.

En utilisant le patron de conception Stratégie, la solution devient plus flexible, évitable et respectueuse des principes SOLID. De plus, l'ajout de nouveaux types de compression se fait de manière plus propre et sans impacter le code existant.