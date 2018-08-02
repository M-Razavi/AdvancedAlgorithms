package net.mahdirazavi.test.stringmatching;

import java.io.FileWriter;
import java.io.IOException;
import java.util.LinkedList;
import java.util.List;

public class ResultFormater {

    String  name;
    List<Double> executationTimes;

    public ResultFormater(String  name) {
        this.name = name;
        this.executationTimes = new LinkedList<>();
    }

    public void addNewExecutionTime(double time) {
        executationTimes.add(time);
    }

    public void writeResult(String  outputPath) throws IOException {
        executationTimes.sort((o1, o2) -> (int) ((o1 - o2) * 10000));
        Double[] times = new Double[executationTimes.size()];
        executationTimes.toArray(times);
        Statistics st = new Statistics(times);


        FileWriter writer = new FileWriter(outputPath,true);
        writer.append("\n" + name + "\nMin,Max,Mean,Deviation");
        writer.append("\n" + st.getMin() + "," + st.getMax() + "," + st.getMean() + "," + st.getStdDev());
        writer.flush();
        writer.close();
    }
}
