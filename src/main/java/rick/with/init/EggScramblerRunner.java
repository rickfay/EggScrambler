package rick.with.init;
import rick.with.services.EggComboCombiner;
import rick.with.services.EggComboUtil;
import rick.with.services.PropertyUtil;

import java.text.DateFormat;
import java.util.*;

/**
 * Egg Scrambler
 *
 * Attempts to calculate an optimally reduced superstring containing all 338 combos of the paths through the Wind Fish's Egg.
 *
 * @author richfay
 * @version 1.0.0
 */
public class EggScramblerRunner {

    /**
     * Main method
     *
     * @param args n/a
     */
    public static void main(String[] args) {

        System.out.println("Starting...");

        PropertyUtil.loadProperties();

        // Fetch and cache the combos to try and assemble
        boolean useReducedCombos = PropertyUtil.getBooleanProperty(PropertyUtil.USE_REDUCED_COMBOS);
        LinkedList<StringBuffer> comboCache = useReducedCombos ? EggComboUtil.get210Combos() : EggComboUtil.get338Combos();

        int lowest = Integer.MAX_VALUE;
        StringBuffer solution = null;

        int execuions = 0;

        // Loop until we hit our goal
        while (lowest > PropertyUtil.getIntProperty(PropertyUtil.LENGTH_GOAL)) {

            execuions++;

            LinkedList<StringBuffer> combos = (LinkedList<StringBuffer>) comboCache.clone();

            // Randomness improves the algorithm and makes each execution different
            Collections.shuffle(combos, new Random(System.currentTimeMillis()));

            // Reduce the combinations into the optimal superstring
            StringBuffer superstring = EggComboCombiner.findShortestSuperstring(combos);

            long commas = superstring.chars().filter(ch -> ch == ',').count();

            if (superstring.length() - commas < lowest) {

                // Validate for correctness
                if (!EggComboUtil.validateSuperString(superstring.toString())) {
                    System.err.println("Superstring failed validation: " + superstring);
                }

                solution = superstring;
                lowest = (int) (superstring.length() - commas);

                Locale locale = new Locale("en", "US");
                DateFormat dateFormat = DateFormat.getTimeInstance(DateFormat.DEFAULT, locale);

                System.out.println(String.format("\n[%tc][Execution: %07d][Length: %d][Chunks: %d][Superstring: %s]",
                        new Date(), execuions, lowest, commas + 1, superstring));

            } else if (execuions % 100 == 0) {
                System.out.println(String.format("[%tc][Execution: %07d][Still scrambling that egg...]", new Date(), execuions));
            }
        }

        System.out.printf("\n\nsuperstring:\n%s\nsize: %d\n\n", solution, solution.length());


        System.out.println("Terminating...");
    }
}
