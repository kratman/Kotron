package src

class Perceptron(size: Int, learnRate: Double = 1.0) {
    private val model: MutableList<Double>
    private var rate: Double

    init {
        model = MutableList(size) {0.0}
        rate = learnRate
    }

     fun trainingStep(answer: Boolean, features: MutableList<Double>) {
        val sign: Double = if (answer) -1.0 else 1.0
        val result = sign * evaluate(features)
        for (i in 0 until model.size) {
            model[i] += rate * result * features[i]
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
        return evaluate(features) > 0.0
    }
}
