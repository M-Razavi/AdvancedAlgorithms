package net.mahdirazavi.test.stringmatching;

public class FiniteAutomata implements Searchable {

    @Override
    public String  getName() {
        return "FiniteAutomata";
    }

    public static final int NO_OF_CHARS = 256;

    public static int getNextState(String  patern, int state, int x) {
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

    public void computeTF(String  pattern, int[][] TF) {
        int M = pattern.length();
        int state, x;
        for (state = 0; state <= M; ++state)
            for (x = 0; x < NO_OF_CHARS; ++x)
                TF[state][x] = getNextState(pattern, state, x);
    }

    public void search(String  text, String  pattern) {
        System.out.println("\nFinite Automata's String matching algorithm starts... ");

        int M = pattern.length();
        int N = text.length();
        int[][] TF = new int[M + 1][NO_OF_CHARS];

        computeTF(pattern, TF);

        // Process txt over FA.
        int i, state = 0;
        for (i = 0; i < N; i++) {
            state = TF[state][text.charAt(i)];
            if (state == M) {
//                System.out.println("'" + pattern + "' found at index " + (i - M + 1));
            }
        }
    }
}
