package rick.with.services;

import rick.with.domain.OverlappingPair;

import java.util.Arrays;
import java.util.Collections;
import java.util.LinkedList;
import java.util.List;
import java.util.stream.Collectors;

/**
 * Finds the shortest "superstring" using the Greedy Approximate Algorithm
 *
 * Adapted from this C++ Implementation:
 * https://www.geeksforgeeks.org/shortest-superstring-problem/
 */
public final class EggComboCombiner {

    /**
     * Function to calculate smallest string that contains each string in the given set as substring.
     *
     * @param fragments
     * @return
     */
    public static StringBuffer findShortestSuperstring(LinkedList<StringBuffer> fragments) {

        // This loop will whittle down our array of 338 combinations until there is just one remaining
        while (fragments.size() > 1) {

            if (fragments.contains(null)) {
                System.out.println(fragments);
            }

            int leftIndex = 0;
            int rightIndex = 0;
            int greatestOverlap = -1;

            StringBuffer overlappedFragment = null;
            OverlappingPair op = null;

            for (int i = 0; i < fragments.size(); i++) {
                for (int j = i + 1; j < fragments.size(); j++) {

                    StringBuffer f1 = fragments.get(i);
                    StringBuffer f2 = fragments.get(j);

                    op = findOverlappingPair(f1, f2);

                    // check for maximum overlap
                    if (op.getOverlappedStr() != null && greatestOverlap < op.getAmount()) {
                        greatestOverlap = op.getAmount();
                        overlappedFragment = op.getOverlappedStr();
                        leftIndex = i;
                        rightIndex = j;
                    }
                }
            }

            // if no overlap, append last element to first
            if (greatestOverlap == -1) {
                return new StringBuffer(String.join(",", fragments.stream().map(StringBuffer::toString).collect(Collectors.toList())));
            } else {

                // Consolidate the matched up fragments

                StringBuffer leftFrag = fragments.get(leftIndex);
                StringBuffer rightFrag = fragments.get(rightIndex);

                fragments.remove(leftFrag);
                fragments.remove(rightFrag);

                fragments.add(overlappedFragment);
            }
        }
        return fragments.get(0);
    }


    /**
     * Function to calculate maximum overlap in two given strings
     *
     * @param str1 First string
     * @param str2 Second string
     * @return
     */
    private static OverlappingPair findOverlappingPair(StringBuffer str1, StringBuffer str2) {

        OverlappingPair op = new OverlappingPair(str1, str2);

        int len1 = str1.length();
        int len2 = str2.length();
        int minLen = min(len1, len2);

        // check suffix of str1 matches with prefix of str2
        for (int i = 1; i <= minLen; i++) {

            // compare last i characters in str1 with first i
            // characters in str2
            if (str1.substring(len1 - i).compareTo(str2.substring(0, i)) == 0)
            {
                if (op.getAmount() < i)
                {
                    //update max and str
                    op.setAmount(i);
                    StringBuffer blah = (new StringBuffer(str1).append(str2, i, str2.length()));
                    op.setOverlappedStr(blah);
                }
            }
        }

        // check prefix of str1 matches with suffix of str2
        for (int i = 1; i <= min(len1, len2); i++)
        {
            // compare first i characters in str1 with last i characters in str2
            if (str1.substring(0, i).compareTo(str2.substring(len2 - i)) == 0) {
                if (op.getAmount() < i)
                {
                    //update max and str
                    op.setAmount(i);
                    StringBuffer blah = new StringBuffer(str2).append(str1, i, str1.length());
                    op.setOverlappedStr(blah);
                }
            }
        }

        return op;
    }

    /**
     * Utility function to calculate minimum of two numbers
     *
     * @param a First number
     * @param b Second number
     * @return The minimum of a and b
     */
    private static int min(int a, int b)
    {
        return (a < b) ? a : b;
    }

    private EggComboCombiner() {
        // Nope.
    }
}
