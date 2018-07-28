package net.mahdirazavi.test.stringmatching;


import java.util.Arrays;

public class Statistics {
    Double[] data;
    int size;

    public Statistics(Double[] data) {
        this.data = data;
        size = data.length;
        Arrays.sort(data);
    }

    double getMin() {
        return data[0];
    }

    double getMax() {
        return data[size - 1];
    }

    double getMean() {
        double sum = 0.0;
        for (double a : data)
            sum += a;
        return sum / size;
    }

    double getVariance() {
        double mean = getMean();
        double temp = 0;
        for (double a : data)
            temp += (a - mean) * (a - mean);
        return temp / (size - 1);
    }

    double getStdDev() {
        return Math.sqrt(getVariance());
    }

    public double median() {
        Arrays.sort(data);

        if (data.length % 2 == 0) {
            return (data[(data.length / 2) - 1] + data[data.length / 2]) / 2.0;
        }
        return data[data.length / 2];
    }
}

