package net.mahdirazavi.test.stringmatching;

public class FiniteAutomata {

    public static final int NO_OF_CHARS = 256;

    public static int getNextState(String patern, int state, int x) {
        int M = patern.length();
        char[] pat = patern.toCharArray();
        if (state < M && x == pat[state])
            return state + 1;
        int ns, i;

        for (ns = state; ns > 0; ns--) {
            if (pat[ns - 1] == x) {
                for (i = 0; i < ns - 1; i++) {
                    if (pat[i] != pat[state - ns + 1 + i])
                        break;
                }
                if (i == ns - 1)
                    return ns;
            }
        }
        return 0;
    }

    public static void computeTF(String pattern, int[][] TF) {
        int M = pattern.length();
        int state, x;
        for (state = 0; state <= M; ++state)
            for (x = 0; x < NO_OF_CHARS; ++x)
                TF[state][x] = getNextState(pattern, state, x);
    }

    public static void search(String text, String pattern) {
        System.out.println("\nFinite Automata's string matching algorithm started...");

        int M = pattern.length();
        int N = text.length();
        int[][] TF = new int[M + 1][NO_OF_CHARS];

        computeTF(pattern, TF);

        // Process txt over FA.
        int i, state = 0;
        for (i = 0; i < N; i++) {
//            System.out.println(i + " :" + text.charAt(i));
            state = TF[state][text.charAt(i)];
            if (state == M) {
                System.out.print(pattern);
                System.out.println(" found at " + (i - M + 1));
            }
        }
    }
}
