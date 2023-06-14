import util.MathUtil;

public class Neuron {
    private double input;
    private double delta;
    public void setInput(double value) { input = value; }
    public void addInput(double value) { input += value; }
    public double getOutput() { return MathUtil.sigmoid(input); }
    public double getDelta() { return delta; }
    public void setDelta(double ideal) { delta = (ideal - getOutput()) * MathUtil.sigmoidDerivative(input); }
    public void setDelta(Neuron[] neurons, double[] weights) {
        double deltasResult = 0;
        for (int i = 0; i < weights.length; i++)
            deltasResult += weights[i] * neurons[i].delta;
        delta = MathUtil.sigmoidDerivative(input) * deltasResult;
    }
    public void reload() {
        input = 0;
        delta = 0;
    }
}
