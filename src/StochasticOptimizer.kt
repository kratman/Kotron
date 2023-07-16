package src

class StochasticOptimizer(maxSteps: Int) {
    var steps: Int = maxSteps

    fun optimize(model: Perceptron, trainData: MutableList<MutableList<Double>>, trainClasses: MutableList<Boolean>) {
        assert(trainData.size == trainClasses.size) {"Incorrect sizes for the input arrays!"}
        for (i in 0 .. trainData.size) {
            model.trainingStep(trainClasses[i], trainData[i])
        }
    }
}
