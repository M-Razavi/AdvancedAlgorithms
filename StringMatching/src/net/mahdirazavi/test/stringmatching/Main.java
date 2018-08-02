package net.mahdirazavi.test.stringmatching;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;

public class Main {

    public static void main(String [] args) throws IOException {

        Path path = Paths.get("");
        System.out.println(path.toAbsolutePath());
        String [] fileNames = {"aesop11.txt", "gulliver.txt", "hitch2.txt", "hound-b.txt"};
//        String  fileName = fileNames[0];
        String [] patterns = {"Two", "number", "themselves", "from", "thing", "understanding", "god", "urgent", "particularly"};
        Searchable[] stAlgorithms = {new KnuthMorrisPratt(), new Naive(), new RabinKarp(), new FiniteAutomata()};
        String  pattern = patterns[0];
        ResultFormater rf;
        for (Searchable smAlgorithm : stAlgorithms) {

            rf = new ResultFormater(smAlgorithm.getName());

            for (String  fileName : fileNames) {
                System.out.println("\nLoading " + fileName + " file.");
                String  text = new String (Files.readAllBytes(path.resolve("String Matching\\Test-Files\\" + fileName).toAbsolutePath()));
//            for (String  pattern : patterns) {
                for (int i = 0; i < 100; i++) {
                    long start = System.nanoTime();
                    smAlgorithm.search(text, pattern);
                    long end = System.nanoTime();
                    long time = (end - start) / 1000;
//                System.out.println("Execution time(μs)= " + time);
                    rf.addNewExecutionTime(time);
                }
                rf.writeResult(path.toAbsolutePath() + "\\" + fileName + ".csv");
            }
        }
    }


}
