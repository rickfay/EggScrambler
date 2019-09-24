package rick.with.services;

import java.util.LinkedList;

/**
 * Factory to generate the 338 valid paths through the Wind Fish's Egg
 */
public final class EggPathFactory {

    private static final int EGG_PATH_LENGTH = 8;

    private static final char U = 'U';
    private static final char L = 'L';
    private static final char R = 'R';

    /**
     * Generates a list of all 338 valid paths through the Wind Fish's Egg
     *
     * @return List
     */
    public static LinkedList<String> getAllCombinations() {

        LinkedList<String> combos = new LinkedList<>();
        combos.add(Character.toString(L));
        combos.add(Character.toString(R));

        return extendCombo(combos, EGG_PATH_LENGTH);
    }

    /**
     * Recursive function to "extend" out a given combination.
     *
     * @param combos Combos that exist thus far
     * @param length Length of the desired combos
     * @return
     */
    private static LinkedList<String> extendCombo(LinkedList<String> combos, int length) {

        // Base case
        if (combos.get(0).length() >= length) {
            return combos;
        }

        LinkedList<String> extendedCombos = new LinkedList<>();

        for (String combo : combos) {

            if (combo.length() >= length - 1) {
                extendedCombos.add(combo + U); // Left and Right aren't possible if it's the last move
            } else {

                char lastChar = combo.charAt(combo.length() - 1);

                switch (lastChar) {
                    case U:
                        extendedCombos.add(combo + L);
                        extendedCombos.add(combo + U);
                        extendedCombos.add(combo + R);
                        break;
                    case L:
                        extendedCombos.add(combo + L);
                        extendedCombos.add(combo + U);
                        break;
                    case R:
                        extendedCombos.add(combo + U);
                        extendedCombos.add(combo + R);
                        break;
                    default:
                        throw new IllegalStateException();
                }
            }
        }

        return extendCombo(extendedCombos, length);
    }

    private EggPathFactory() {
        // Instantiation is bad and you should feel bad.
    }
}
