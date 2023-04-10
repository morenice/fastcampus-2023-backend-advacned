import java.io.*;
import java.util.Scanner;

class FileReadWriter {
    public static void main(String[] args) {
        final var filename = "clip01.txt";
        final var sample = "this is sample string";
        File file = new File(filename);

        long start = System.currentTimeMillis();

        try {
            OutputStream os = new FileOutputStream(file);
            PrintWriter writer = new PrintWriter(os);

            for (int i = 0; i < 1_000_000; i++) {
                writer.println(sample);
            }

            writer.close();
        } catch (IOException e) {
            e.printStackTrace();
        }

        try {
            Scanner scanner = new Scanner(new File(filename));
            while (scanner.hasNextLine()) {
                scanner.nextLine();
            }
        } catch (FileNotFoundException e) {
            throw new RuntimeException(e);
        }

        long end = System.currentTimeMillis();
        System.out.println("Execution time: " + (end-start) + " ms");
    }
}
