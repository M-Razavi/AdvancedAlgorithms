package net.mahdirazavi.test.stringmatching;

public class Naive implements Searchable {

    @Override
    public String getName() {
        return "Naive";
    }

    public void search(String text, String pattern) {
        System.out.println("\nNaive String matching algorithm starts... ");

        char[] textArray = text.toCharArray();
        char[] patternArray = pattern.toCharArray();

        int tLen = textArray.length;
        int pLen = patternArray.length;

        for (int i = 0; i < tLen - pLen; i++) {

            int j;
            for (j = 0; j < pLen; j++) {
                if (patternArray[j] != textArray[i + j]) {
                    break;
                }
            }
            if (j == pLen) {
//                System.out.println("'" + pattern + "' found at index " + i);
            }
        }
    }
}