/*
 * Name: Kobe Alexander
 * Class: CSD 420
 * Date: 8/23/2026
 * Purpose: Generate five random integers and five random doubles,
 *          then append them to a data file.
 */
package module2;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.Random;

public class WriteData {

    public static void main(String[] args) {

        // Create arrays to store five integers and five doubles
        int[] integers = new int[5];
        double[] doubles = new double[5];

        // Random object used to generate values
        Random random = new Random();

        // Generate five random integers from 0 to 99
        for (int i = 0; i < integers.length; i++) {
            integers[i] = random.nextInt(100);
        }

        // Generate five random double values from 0.0 to 100.0
        for (int i = 0; i < doubles.length; i++) {
            doubles[i] = random.nextDouble() * 100;
        }

        String fileName = "Kobe_datafile.dat";

        /*
         * FileWriter's second argument is true.
         * This enables append mode.
         *
         * If the file does not exist, it will be created.
         * If it already exists, the new data will be added
         * to the end of the file.
         */
        try (PrintWriter writer =
                     new PrintWriter(new FileWriter(fileName, true))) {

            writer.println("Integer Array:");

            for (int value : integers) {
                writer.print(value + " ");
            }

            writer.println();
            writer.println("Double Array:");

            for (double value : doubles) {
                writer.printf("%.2f ", value);
            }

            writer.println();
            writer.println("------------------------------");

            System.out.println("Data successfully written to " + fileName);

        } catch (IOException e) {
            System.out.println("An error occurred while writing the file.");
            e.printStackTrace();
        }
    }
}