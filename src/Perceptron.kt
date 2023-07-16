package src

class Perceptron(size: Int) {
    private val model: MutableList<Double>
    private var bias: Double

    init {
        model = MutableList(size) {0.0}
        bias = 0.0
    }

    private fun trainingStep() {

    }

    fun classify(features: MutableList<Double>): Boolean {
        assert(features.size != model.size) {"Incorrect number of features"}
        var sum = 0.0
        for (i in 0 until model.size) {
            sum += model[i] * features[i]
        }
        sum += bias
        return sum > 0
    }
}