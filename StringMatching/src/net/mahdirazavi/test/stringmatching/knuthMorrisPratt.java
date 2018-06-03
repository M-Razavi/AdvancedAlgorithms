package net.mahdirazavi.test.stringmatching;

public class knuthMorrisPratt {


    public static void search(String text, String pattern) {
        System.out.println("\nKMP string matching algorithm started...");

        int givenStringLetterPosition = 0;
        int searchedStringLetterPosition = 0;
        int foundAt = -1;

        while (givenStringLetterPosition < text.length()) {
            if (text.charAt(givenStringLetterPosition) == pattern.charAt(searchedStringLetterPosition)) {
                if (searchedStringLetterPosition == 0) {
                    foundAt = givenStringLetterPosition;
                }
                searchedStringLetterPosition++;
                givenStringLetterPosition++;
                if (searchedStringLetterPosition == pattern.length()) {
                    System.out.println(pattern + " found at " + foundAt + " position.");
                    searchedStringLetterPosition = 0;
//                    break;
                }
            } else {
                searchedStringLetterPosition = 0;
                foundAt++;
                givenStringLetterPosition = foundAt;
                if (text.length() == givenStringLetterPosition) {
                    System.out.println(pattern + " was not found.");
                    break;
                }
            }
        }
    }

}
