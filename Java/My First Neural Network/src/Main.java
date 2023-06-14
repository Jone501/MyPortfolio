import java.util.Arrays;

public class Main {
    private static NeuralNetwork neuralNetwork;
    private static NeuralNetwork neuralNetwork2;

    public static void main(String[] args) {
        // Создание нейросети
        neuralNetwork = new NeuralNetwork(new int[]{2, 4, 4, 1});
        // Запуск до обучения
        System.out.println("Нейросеть 1 [До обучения]: " + Arrays.toString(neuralNetwork.run(new double[]{0, 0})));
        System.out.println("Нейросеть 1 [До обучения]: " + Arrays.toString(neuralNetwork.run(new double[]{0, 1})));
        System.out.println("Нейросеть 1 [До обучения]: " + Arrays.toString(neuralNetwork.run(new double[]{1, 0})));
        System.out.println("Нейросеть 1 [До обучения]: " + Arrays.toString(neuralNetwork.run(new double[]{1, 1})));
        // Обучение
        neuralNetwork.train(1000000, new Training[]{
                new Training(new double[]{0, 0}, new double[]{0}),
                new Training(new double[]{0, 1}, new double[]{1}),
                new Training(new double[]{1, 0}, new double[]{1}),
                new Training(new double[]{1, 1}, new double[]{0}),
        }, 0.5, 0.25);
        // Повторный запуск обученной нейросети
        System.out.println("Нейросеть 1 [После обучения]: " + Arrays.toString(neuralNetwork.run(new double[]{0, 0})));
        System.out.println("Нейросеть 1 [После обучения]: " + Arrays.toString(neuralNetwork.run(new double[]{0, 1})));
        System.out.println("Нейросеть 1 [После обучения]: " + Arrays.toString(neuralNetwork.run(new double[]{1, 0})));
        System.out.println("Нейросеть 1 [После обучения]: " + Arrays.toString(neuralNetwork.run(new double[]{1, 1})));
        // Новая нейросеть с уже настроенными весами из первой нейросети. Выдаёт те же результаты
        neuralNetwork2 = new NeuralNetwork(new int[]{2, 4, 4, 1}, neuralNetwork.getWeights());
        System.out.println("Нейросеть 2 [С весами Нейросети 1]: " + Arrays.toString(neuralNetwork2.run(new double[]{0, 0})));
        System.out.println("Нейросеть 2 [С весами Нейросети 1]: " + Arrays.toString(neuralNetwork2.run(new double[]{0, 1})));
        System.out.println("Нейросеть 2 [С весами Нейросети 1]: " + Arrays.toString(neuralNetwork2.run(new double[]{1, 0})));
        System.out.println("Нейросеть 2 [С весами Нейросети 1]: " + Arrays.toString(neuralNetwork2.run(new double[]{1, 1})));
    }
}