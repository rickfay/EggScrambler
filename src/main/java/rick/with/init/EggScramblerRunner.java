package rick.with.init;
import rick.with.services.EggComboCombiner;
import rick.with.services.EggPathFactory;

import java.util.LinkedList;

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

        // Generate the 338 Combinations
        LinkedList<String> combos = EggPathFactory.getAllCombinations();
        System.out.printf("Generated %d Combos: %s\n", combos.size(), combos);

        // Reduce the combinations into the optimal superstring
        String superstring = EggComboCombiner.findShortestSuperstring(combos);

        System.out.printf("superstring:\n%s\nsize: %d\n", superstring, superstring.length());

        System.out.println("Terminating...");
    }
}
