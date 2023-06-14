public class Training {
    private double[] inputs;
    public double[] getInputs() {
        return inputs;
    }

    private double[] outputs;
    public double[] getOutputs() {
        return outputs;
    }

    public Training(double[] inputs, double[] outputs) {
        this.inputs = inputs;
        this.outputs = outputs;
    }
}
