package net.mahdirazavi.test.stringmatching;

public class Naive {

    public static void search(String text, String pattern) {
        System.out.println("Naive String matching algorithm starts... ");
        char[] textArray = text.toCharArray();
        char[] patternArray = pattern.toCharArray();

        int tLen = textArray.length;
        int pLen = patternArray.length;

        boolean found = false;
        for (int i = 0; i < tLen - pLen; i++) {

           found = true;
            int j;
            for (j = 0; j < pLen; j++) {
                if (patternArray[j] != textArray[i + j]) {
                   found = false;
                    break;
                }
            }
           if(found)
            {
                System.out.println("String found at " + (i + 1) + " position!!");
            }
        }
    }
}