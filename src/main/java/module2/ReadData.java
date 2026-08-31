package module2;

/*
 * Name: Kobe Alexander
 * Class CSD 420
 * Date: 8/23/2026
 * Purpose: Read and display the data stored in
 *          Kobe_datafile.dat.
 */

import java.io.File;
import java.io.FileNotFoundException;
import java.util.Scanner;

public class ReadData {

    public static void main(String[] args) {

        String fileName = "Kobe_datafile.dat";

        File file = new File(fileName);

        try (Scanner scanner = new Scanner(file)) {

            System.out.println("Data stored in " + fileName + ":");
            System.out.println();

            // Read the file one line at a time
            while (scanner.hasNextLine()) {
                System.out.println(scanner.nextLine());
            }

        } catch (FileNotFoundException e) {
            System.out.println("The file " + fileName + " was not found.");
            System.out.println("Run WriteData first to create the file.");
        }
    }
}
