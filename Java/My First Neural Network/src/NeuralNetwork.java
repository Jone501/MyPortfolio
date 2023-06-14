import util.ArrayUtil;
import util.MathUtil;

public class NeuralNetwork {
    private Neuron[][] layers;
    private double[][] weights;
    public double[][] getWeights() { return weights; }

    public NeuralNetwork(int[] layers) {
        this.layers = new Neuron[layers.length][];
        for (int i = 0; i < layers.length; i++) {
            this.layers[i] = new Neuron[layers[i]];
            for (int j = 0; j < layers[i]; j++)
                this.layers[i][j] = new Neuron();
        }
        weights = new double[layers.length - 1][];
        for (int i = 0; i < layers.length - 1; i++)
            weights[i] = new double[layers[i] * layers[i + 1]];
        for (int i = 0; i < weights.length; i++)
            for (int j = 0; j < weights[i].length; j++)
                weights[i][j] = MathUtil.randomDouble(-5, 5);
    }

    public NeuralNetwork(int[] layers, double[][] weights) {
        this.layers = new Neuron[layers.length][];
        for (int i = 0; i < layers.length; i++) {
            this.layers[i] = new Neuron[layers[i]];
            for (int j = 0; j < layers[i]; j++)
                this.layers[i][j] = new Neuron();
        }
        this.weights = weights;
    }

    public double[] run(double[] input) {
        for (int i = 0; i < layers.length; i++)
            for (int j = 0; j < layers[i].length; j++)
                layers[i][j].reload();
        for (int i = 0; i < layers[0].length; i++)
            layers[0][i].setInput(input[i]);
        for (int i = 0; i < layers.length - 1; i++) {
            int currentWeight = 0;
            for (int j = 0; j < layers[i].length; j++)
                for (int k = 0; k < layers[i + 1].length; k++, currentWeight++)
                    layers[i + 1][k].addInput(layers[i][j].getOutput() * weights[i][currentWeight]);
        }
        double[] outputs = new double[layers[layers.length - 1].length];
        for (int i = 0; i < layers[layers.length - 1].length; i++)
            outputs[i] = layers[layers.length - 1][i].getOutput();
        return outputs;
    }

    public void train(int epohes, Training[] trainings, double learnSpeed, double moment) {
        double[][] prevWeightsDeltas = new double[weights.length][];
        for (int i = 0; i < prevWeightsDeltas.length; i++)
            prevWeightsDeltas[i] = new double[weights[i].length];
        for (int i = 0; i < epohes; i++)
            for (int j = 0; j < trainings.length; j++) {
                run(trainings[j].getInputs());
                double[] idealOutputs = trainings[j].getOutputs();
                for (int k = 0; k < layers[layers.length - 1].length; k++) {
                    Neuron out = layers[layers.length - 1][k];
                    out.setDelta(idealOutputs[k]);
                }
                for (int k = layers.length - 2; k > 0; k--) {
                    int n = 0;
                    for (int l = 0; l < layers[k].length; l++) {
                        Neuron neuron = layers[k][l];
                        double[] currentWeights = ArrayUtil.subarrayDouble(weights[k], layers[k + 1].length * l, layers[k + 1].length * (l + 1));
                        neuron.setDelta(layers[k + 1], currentWeights);
                        for (int m = 0; m < layers[k + 1].length; m++, n++) {
                            Neuron nextNeuron = layers[k + 1][m];
                            double weightGrad = MathUtil.grad(neuron, nextNeuron);
                            double weightDelta = MathUtil.bpm(learnSpeed, weightGrad, moment, prevWeightsDeltas[k][n]);
                            prevWeightsDeltas[k][n] = weightDelta;
                            weights[k][n] += weightDelta;
                        }
                    }
                }
            }
    }
}