package rick.with.services;

import java.util.LinkedList;

/**
 * Utility for creating/validating/etc. Egg Combos
 */
public final class EggComboUtil {

    private static final int EGG_PATH_LENGTH = 8;

    private static final char U = 'U';
    private static final char L = 'L';
    private static final char R = 'R';

    /**
     * Generates a list of all 338 valid paths through the Wind Fish's Egg
     *
     * @return List
     */
    public static LinkedList<StringBuffer> get338Combos() {

        LinkedList<StringBuffer> combos = new LinkedList<>();
        combos.add(new StringBuffer("L"));
        combos.add(new StringBuffer("R"));

        return extendCombo(combos, EGG_PATH_LENGTH);
    }

    /**
     * Generates a list of all 210 valid paths through the Wind Fish's Egg that include at least one each of L, U, and R
     * @return
     */
    public static LinkedList<StringBuffer> get210Combos() {
        LinkedList<StringBuffer> allCombos = get338Combos();
        return pruneInvalidCombos(allCombos);
    }

    /**
     * Validates that a given string contains all the solutions. This will check the configuration to determine if it's checking
     * for 210 or 338 combos.
     *
     * @param superstring The superstring to validate
     * @return
     */
    public static boolean validateSuperString(String superstring) {

        LinkedList<StringBuffer> combos = PropertyUtil.getBooleanProperty(PropertyUtil.USE_REDUCED_COMBOS)
                ? EggComboUtil.get210Combos() : EggComboUtil.get338Combos();

        for (StringBuffer combo: combos) {
            if (!superstring.contains(combo.toString())) {
                return false;
            }
        }

        return true;
    }

    /**
     * Recursive function to "extend" out a given combination.
     *
     * @param combos Combos that exist thus far
     * @param length Length of the desired combos
     * @return
     */
    private static LinkedList<StringBuffer> extendCombo(LinkedList<StringBuffer> combos, int length) {

        // Base case
        if (combos.get(0).length() >= length) {
            return combos;
        }

        LinkedList<StringBuffer> extendedCombos = new LinkedList<>();

        for (StringBuffer combo : combos) {

            if (combo.length() >= length - 1) {
                extendedCombos.add(new StringBuffer(combo).append(U)); // Left and Right aren't possible if it's the last move
            } else {

                char lastChar = combo.charAt(combo.length() - 1);

                switch (lastChar) {
                    case U:
                        extendedCombos.add(new StringBuffer(combo).append(L));
                        extendedCombos.add(new StringBuffer(combo).append(U));
                        extendedCombos.add(new StringBuffer(combo).append(R));
                        break;
                    case L:
                        extendedCombos.add(new StringBuffer(combo).append(L));
                        extendedCombos.add(new StringBuffer(combo).append(U));
                        break;
                    case R:
                        extendedCombos.add(new StringBuffer(combo).append(U));
                        extendedCombos.add(new StringBuffer(combo).append(R));
                        break;
                    default:
                        throw new IllegalStateException();
                }
            }
        }

        return extendCombo(extendedCombos, length);
    }

    private static LinkedList<StringBuffer> pruneInvalidCombos(LinkedList<StringBuffer> combos) {
        combos.removeIf(fragment -> (fragment.indexOf("L") == -1) || fragment.indexOf("R") == -1);
        return combos;
    }

    private EggComboUtil() {
        // Instantiation is bad and you should feel bad.
    }
}
