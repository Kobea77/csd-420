/*
 * Name: Kobe Alexander
 * Class CSD 420
 * Date: 8/30/2026
 * Purpose: Create an ArrayList containing 50 random values from 1 to 20
 *          and use a generic method to return a new ArrayList with
 *          duplicate values removed.
 */

package module3;

import java.util.ArrayList;
import java.util.Random;

public class RemoveDuplicatesTest {

    public static void main(String[] args) {

        // Create the original ArrayList
        ArrayList<Integer> originalList = new ArrayList<>();

        // Create a Random object
        Random random = new Random();

        // Fill the ArrayList with 50 random values from 1 to 20
        for (int i = 0; i < 50; i++) {
            originalList.add(random.nextInt(20) + 1);
        }

        // Display the original ArrayList
        System.out.println("Original ArrayList:");
        System.out.println(originalList);

        // Remove duplicate values
        ArrayList<Integer> noDuplicates = removeDuplicates(originalList);

        // Display the new ArrayList
        System.out.println("\nArrayList with duplicates removed:");
        System.out.println(noDuplicates);
    }

    /*
     * Generic method that accepts an ArrayList and returns
     * a new ArrayList containing no duplicate values.
     */
    public static <E> ArrayList<E> removeDuplicates(ArrayList<E> list) {

        // Create a new ArrayList for unique values
        ArrayList<E> newList = new ArrayList<>();

        // Check each value in the original ArrayList
        for (E value : list) {

            // Add the value only if it is not already in the new list
            if (!newList.contains(value)) {
                newList.add(value);
            }
        }

        return newList;
    }
}