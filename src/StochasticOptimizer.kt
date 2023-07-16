package src

class StochasticOptimizer(maxSteps: Int) {
    private var steps: Int = maxSteps

    fun optimize(model: Perceptron, trainData: MutableList<FeatureInstance>) {
        for (step in 0 until steps) {
            trainData.shuffle()
            for (i in 0 until trainData.size) {
                model.trainingStep(trainData[i].classification, trainData[i].features)
            }
        }
    }
}
