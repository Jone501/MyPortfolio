package util;

public class MathUtil {
    public static double sigmoid(double x) { return 1 / (1 + Math.exp(-x)); }
    public static double sigmoidDerivative(double x) { return (1 - sigmoid(x)) * sigmoid(x); }
    public static double grad(Neuron a, Neuron b) { return a.getOutput() * b.getDelta(); }
    public static double bpm(double e, double grad, double a, double prevDelta) { return e * grad + a * prevDelta; }
    public static double randomDouble(double min, double max) { return Math.random() * (max - min) + min; }
    public static double mse(double[] ideal, double[] current) {
        double result = 0;
        for (int i = 0; i < ideal.length; i++)
            result += Math.pow(ideal[i] - current[i], 2);
        return result / ideal.length;
    }
}
