package src

class Perceptron(size: Int, learnRate: Double = 1.0) {
    private val model: MutableList<Double>
    private var rate: Double

    init {
        model = MutableList(size) {0.0}
        rate = learnRate
    }

     fun trainingStep(answer: Boolean, features: MutableList<Double>) {
        val direction: Double = if (answer) 1.0 else -1.0
        if (answer != classify(features)) {
            for (i in 0 until model.size) {
                model[i] += rate * direction * features[i]
            }
        }
    }

    private fun evaluate(features: MutableList<Double>): Double {
        assert(features.size == model.size) {"Incorrect number of features!"}
        var sum = 0.0
        for (i in 0 until model.size) {
            sum += model[i] * features[i]
        }
        return sum
    }

    fun classify(features: MutableList<Double>): Boolean {
        return classFromValue(evaluate(features))
    }

    private fun classFromValue(value: Double): Boolean {
        return value >= 0.0
    }
}
