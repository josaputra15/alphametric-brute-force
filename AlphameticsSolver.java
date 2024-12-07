import java.util.*;

public class AlphameticsSolver {

    public static void main(String[] args) {
        solveAlphametic();
    }

    static void solveAlphametic() {
        String equation = "EARTH + AIR + FIRE + WATER = NATURE";
        System.out.println("Solving equation: " + equation);

        // Unique letters in the problem
        char[] letters = {'E', 'A', 'R', 'T', 'H', 'I', 'F', 'W', 'N', 'U'};
        int numLetters = letters.length;

        // Generate all permutations of digits for the letters
        int[] digits = {0, 1, 2, 3, 4, 5, 6, 7, 8, 9};
        List<int[]> permutations = new ArrayList<>();
        permute(digits, 0, numLetters, permutations);

        // Check each permutation
        for (int[] perm : permutations) {
            // Map letters to digits
            Map<Character, Integer> letterToDigit = new HashMap<>();
            for (int i = 0; i < numLetters; i++) {
                letterToDigit.put(letters[i], perm[i]);
            }

            // Convert words to numbers
            int earth = getNumber("EARTH", letterToDigit);
            int air = getNumber("AIR", letterToDigit);
            int fire = getNumber("FIRE", letterToDigit);
            int water = getNumber("WATER", letterToDigit);
            int nature = getNumber("NATURE", letterToDigit);

            // Check validity of the solution
            if (earth != -1 && air != -1 && fire != -1 && water != -1 && nature != -1) {
                if (earth + air + fire + water == nature) {
                    System.out.println("Solution found:");
                    System.out.println("EARTH = " + earth);
                    System.out.println("AIR = " + air);
                    System.out.println("FIRE = " + fire);
                    System.out.println("WATER = " + water);
                    System.out.println("NATURE = " + nature);
                    System.out.println("Letter Mapping: " + letterToDigit);
                    return;
                }
            }
        }

        System.out.println("No solution found.");
    }

    // Helper method to permute digits
    static void permute(int[] digits, int start, int length, List<int[]> permutations) {
        if (start == length) {
            permutations.add(Arrays.copyOf(digits, length));
            return;
        }

        for (int i = start; i < digits.length; i++) {
            swap(digits, start, i);
            permute(digits, start + 1, length, permutations);
            swap(digits, start, i); // backtrack
        }
    }

    // Helper method to swap two elements in an array
    static void swap(int[] arr, int i, int j) {
        int temp = arr[i];
        arr[i] = arr[j];
        arr[j] = temp;
    }

    // Convert a word to its numeric value using the letter-to-digit map
    static int getNumber(String word, Map<Character, Integer> letterToDigit) {
        int number = 0;
        for (int i = 0; i < word.length(); i++) {
            char letter = word.charAt(i);
            int digit = letterToDigit.get(letter);

            // Leading digit cannot be 0
            if (i == 0 && digit == 0) {
                return -1;
            }

            number = number * 10 + digit;
        }
        return number;
    }
}
