package net.mahdirazavi.test.stringmatching;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.time.Duration;
import java.time.Instant;

public class Main {

    public static void main(String[] args) throws IOException {

        Path path = Paths.get("");
        System.out.println(path.toAbsolutePath());
        String[] fileNames = {"aesop11.txt", "gulliver.txt", "hitch2.txt", "hound-b.txt"};
        String[] patterns = {"Two", "number", "themselves", "from", "thing", "understanding", "god", "urgent", "particularly"};
        Searchable[] stAlgorithms = {new Naive(), new RabinKarp(), new FiniteAutomata(), new KnuthMorrisPratt()};
        String pattern = patterns[0];

        for (String name : fileNames) {
            System.out.println("\nLoading " + name + " file.");
            String text = new String(Files.readAllBytes(path.resolve("StringMatching\\Test-Files\\" + name).toAbsolutePath()));

            for (Searchable smAlgorithm : stAlgorithms) {
                Instant start = Instant.now();
                smAlgorithm.search(text, pattern);
                Instant end = Instant.now();
                System.out.println("Execution time(μs)= " + Duration.between(start, end).getNano() / 1000);
            }
        }
    }
}
