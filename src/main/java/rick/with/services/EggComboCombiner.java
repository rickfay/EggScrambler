package rick.with.services;

import rick.with.domain.OverlappingPair;

import java.util.LinkedList;

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
    public static String findShortestSuperstring(LinkedList<String> fragments) {

        System.out.println(fragments);

        LinkedList<String> fragmentsToRemove = new LinkedList<>();

        // This loop will whittle down our array of 338 combinations until there is just one remaining
        while (fragments.size() > 1) {

            if (fragments.contains(null)) {
                System.out.println(fragments);
            }

            int leftIndex = 0;
            int rightIndex = 0;
            int greatestOverlap = -1;

            String overlappedFragment = null;
            OverlappingPair op = null;

            for (int i = 0; i < fragments.size(); i++) {
                for (int j = i + 1; j < fragments.size(); j++) {

                    String f1 = fragments.get(i);
                    String f2 = fragments.get(j);

                    if (f1.contains(f2)) {
                        fragmentsToRemove.add(f2);
                    } else if (f2.contains(f1)) {
                        fragmentsToRemove.add(f1);
                    } else {
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
            }

            // Remove any duplicate fragments
            if (!fragmentsToRemove.isEmpty()) {
                System.out.printf("\nRemoving [%d] fragments already contained in other fragments, lucky!\n\n",
                        fragmentsToRemove.size());
                fragments.removeAll(fragmentsToRemove);
            }

            // if no overlap, append last element to first
            if (greatestOverlap == -1) {

                System.err.printf("OOF, no match among remaining %d fragments...\n", fragments.size());

                String concatenatedFrags = fragments.getFirst() + fragments.getLast();

                fragments.removeFirst();
                fragments.removeLast();

                fragments.addLast(concatenatedFrags); // TODO is this the most efficient spot in the list to add it?

            } else {

                // Consolidate the matched up fragments

                String leftFrag = fragments.get(leftIndex);
                String rightFrag = fragments.get(rightIndex);

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
    private static OverlappingPair findOverlappingPair(String str1, String str2) {

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
                    op.setOverlappedStr(str1 + str2.substring(i));
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
                    op.setOverlappedStr(str2 + str1.substring(i));
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
