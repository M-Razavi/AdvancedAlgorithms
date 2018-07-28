package net.mahdirazavi.test.stringmatching;

public class KnuthMorrisPratt implements Searchable {

    @Override
    public String getName() {
        return "KnuthMorrisPratt";
    }

    private int R;       // the radix
    private int[][] dfa;       // the KMP automoton


    private void preprocessing(String pat) {
        R = 256;

        // build DFA from pattern
        int m = pat.length();
        dfa = new int[R][m];
        dfa[pat.charAt(0)][0] = 1;
        for (int x = 0, j = 1; j < m; j++) {
            for (int c = 0; c < R; c++)
                dfa[c][j] = dfa[c][x];     // Copy mismatch cases.
            dfa[pat.charAt(j)][j] = j + 1;   // Set match case.
            x = dfa[pat.charAt(j)][x];     // Update restart state.
        }
    }

    public void search(String text, String pattern) {
        System.out.println("\nKMP string matching algorithm starts... ");

        preprocessing(pattern);
        // simulate operation of DFA on text
        int m = pattern.length();
        int n = text.length();
        int i, j;
        for (i = 0, j = 0; i < n && j < m; i++) {
            j = dfa[text.charAt(i)][j];

            if (j == m) {   // found
//                System.out.println("'" + pattern + "' found at index " + (i - m + 1));
                j = 0;
            }
        }
    }
}
