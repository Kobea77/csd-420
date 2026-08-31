package module4;
/*
 * Name: Kobe Alexander
 * Class CSD 420
 * Date: 8/30/2026
 *
 * Purpose:
 * This program stores integers in a LinkedList and compares the time
 * required to traverse the list using an Iterator versus using the
 * get(index) method.
 *
 * The program tests two LinkedList sizes:
 *      1. 50,000 integers
 *      2. 500,000 integers
 *
 * TEST RESULTS:
 *
 * 50,000 integers:
 * Iterator traversal time: 3.597 milliseconds
 * get(index) traversal time: 725.114 milliseconds
 * get(index) was approximately 201.62 times slower than the Iterator.
 *
 * 500,000 integers:
 * Iterator traversal time: 4.340 milliseconds
 * get(index) traversal time: 89,937.090 milliseconds
 * get(index) was approximately 20,724.26 times slower than the Iterator.
 *
 * RESULTS / DISCUSSION:
 *
 * The results show a major performance difference between traversing
 * a LinkedList using an Iterator and traversing it using get(index).
 *
 * For 50,000 integers, the Iterator completed the traversal in about
 * 3.597 milliseconds, while get(index) required about 725.114
 * milliseconds. This made get(index) approximately 201.62 times slower.
 *
 * When the LinkedList size was increased to 500,000 integers, the
 * Iterator still completed the traversal very quickly at about
 * 4.340 milliseconds. However, the get(index) approach required
 * approximately 89,937.090 milliseconds, or about 89.94 seconds.
 * At this size, get(index) was approximately 20,724.26 times slower
 * than Iterator traversal.
 *
 * The Iterator is much faster because it moves directly from one node
 * in the LinkedList to the next. This results in approximately O(n)
 * time complexity because each element only needs to be visited once.
 *
 * The get(index) method is inefficient for a LinkedList because a
 * LinkedList does not provide direct indexed access. To retrieve an
 * element at a specific index, Java must traverse through the linked
 * nodes until it reaches that position. Repeating get(index) for every
 * element causes the total traversal to have approximately O(n^2)
 * time complexity.
 *
 * Increasing the list size from 50,000 to 500,000 increased the number
 * of elements by 10 times. The Iterator time increased only slightly,
 * from 3.597 milliseconds to 4.340 milliseconds. In comparison, the
 * get(index) time increased from 725.114 milliseconds to 89,937.090
 * milliseconds. This demonstrates how poorly indexed access scales
 * when used with a LinkedList.
 *
 * The correctness tests also passed for both list sizes. For the
 * 50,000-element list, both traversal methods produced a sum of
 * 1,249,975,000. For the 500,000-element list, both methods produced
 * a sum of 124,999,750,000. Matching sums confirm that both traversal
 * methods processed the same values correctly.
 *
 * Exact execution times can vary depending on the computer, JVM
 * optimization, available system resources, and other programs
 * running during the test.
 */

import java.util.Iterator;
import java.util.LinkedList;

public class LinkedListPerformanceTest {

    public static void main(String[] args) {

        System.out.println("LINKEDLIST PERFORMANCE TEST");
        System.out.println("===========================\n");

        // Test with 50,000 integers
        testLinkedList(50_000);

        System.out.println();

        // Test with 500,000 integers
        testLinkedList(500_000);
    }

    /**
     * Creates a LinkedList of the requested size and tests traversal
     * using both an Iterator and the get(index) method.
     *
     * @param size number of integers to store in the LinkedList
     */
    public static void testLinkedList(int size) {

        LinkedList<Integer> list = new LinkedList<>();

        // Fill the LinkedList with integers from 0 to size - 1
        for (int i = 0; i < size; i++) {
            list.add(i);
        }

        System.out.println("Testing LinkedList with " + size + " integers");
        System.out.println("------------------------------------------");

        /*
         * Correctness tests
         */
        testListCorrectness(list, size);

        /*
         * Test traversal using Iterator
         */
        long iteratorStart = System.nanoTime();

        long iteratorSum = traverseWithIterator(list);

        long iteratorEnd = System.nanoTime();

        long iteratorTime = iteratorEnd - iteratorStart;

        /*
         * Test traversal using get(index)
         */
        long getStart = System.nanoTime();

        long getSum = traverseWithGet(list);

        long getEnd = System.nanoTime();

        long getTime = getEnd - getStart;

        /*
         * Verify that both traversal methods processed
         * exactly the same values.
         */
        if (iteratorSum == getSum) {
            System.out.println("Traversal test: PASSED");
        } else {
            System.out.println("Traversal test: FAILED");
        }

        /*
         * Display results.
         */
        System.out.println();

        System.out.printf(
                "Iterator traversal time: %,d nanoseconds%n",
                iteratorTime
        );

        System.out.printf(
                "Iterator traversal time: %.3f milliseconds%n",
                iteratorTime / 1_000_000.0
        );

        System.out.println();

        System.out.printf(
                "get(index) traversal time: %,d nanoseconds%n",
                getTime
        );

        System.out.printf(
                "get(index) traversal time: %.3f milliseconds%n",
                getTime / 1_000_000.0
        );

        /*
         * Calculate how many times slower get(index) was.
         */
        if (iteratorTime > 0) {

            double difference =
                    (double) getTime / iteratorTime;

            System.out.printf(
                    "%nget(index) was approximately %.2f times slower "
                            + "than Iterator traversal.%n",
                    difference
            );
        }

        System.out.println();

        System.out.println(
                "Iterator sum: " + iteratorSum
        );

        System.out.println(
                "get(index) sum: " + getSum
        );
    }

    /**
     * Traverses the LinkedList using an Iterator.
     *
     * @param list LinkedList to traverse
     * @return sum of all values in the list
     */
    public static long traverseWithIterator(
            LinkedList<Integer> list) {

        long sum = 0;

        Iterator<Integer> iterator = list.iterator();

        while (iterator.hasNext()) {
            sum += iterator.next();
        }

        return sum;
    }

    /**
     * Traverses the LinkedList using get(index).
     *
     * @param list LinkedList to traverse
     * @return sum of all values in the list
     */
    public static long traverseWithGet(
            LinkedList<Integer> list) {

        long sum = 0;

        for (int i = 0; i < list.size(); i++) {
            sum += list.get(i);
        }

        return sum;
    }

    /**
     * Tests that the LinkedList was created correctly.
     *
     * @param list LinkedList being tested
     * @param expectedSize expected number of elements
     */
    public static void testListCorrectness(
            LinkedList<Integer> list,
            int expectedSize) {

        boolean passed = true;

        // Check the list size
        if (list.size() != expectedSize) {
            passed = false;
        }

        // Check the first value
        if (list.getFirst() != 0) {
            passed = false;
        }

        // Check the last value
        if (list.getLast() != expectedSize - 1) {
            passed = false;
        }

        if (passed) {
            System.out.println("List creation test: PASSED");
        } else {
            System.out.println("List creation test: FAILED");
        }
    }
}