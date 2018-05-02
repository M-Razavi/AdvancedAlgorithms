package net.mahdirazavi.test.stringmatching;

public class Main {

    public static void main(String[] args) {
        String text = "In computer science, string searching algorithms, sometimes called string matching algorithms, are an important class of string algorithms that try to find a place where one or several strings (also called patterns) are found within a larger string or text.\n" +
                "\nLet Σ be an alphabet (finite set). The most basic example of string searching is where both the pattern and searched text are arrays of elements of Σ. The Σ may be a usual human alphabet (for example, the letters A through Z in the Latin alphabet). Other applications may use binary alphabet (Σ = {0,1}) or DNA alphabet (Σ = {A,C,G,T}) in bioinformatics.\n" +
                "\nIn practice, how the string is encoded can affect the feasible string search algorithms. In particular if a variable width encoding is in use then it may be slower to find the Nth character (perhaps requiring time proportional to N). This may significantly slow down some search algorithms. One of many possible solutions is to search for the sequence of code units instead, but doing so may produce false matches unless the encoding is specifically designed to avoid it.";
        String pattern = "tjhghjghjghjghe ";
        Naive.search(text, pattern);
    }
}
