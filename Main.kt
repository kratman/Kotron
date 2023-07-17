
import src.StochasticOptimizer
import src.FeatureInstance
import src.Perceptron
import java.io.File

fun main() {
    for (target in 0 .. 9) {
        val targetModel = trainFor(target)
        testFor(target, targetModel)
    }
}

fun trainFor(target: Int): Perceptron {
    val trainingData = readData("train", target)
    val aModel = Perceptron(trainingData[0].features.size, 0.05)
    StochasticOptimizer(200).optimize(aModel, trainingData)
    return aModel
}

fun testFor(target: Int, aModel: Perceptron) {
    val testData = readData("test", target)
    var tp = 0
    var fp = 0
    var tn = 0
    var fn = 0
    for (i in 0 until testData.size) {
        val prediction = aModel.classify(testData[i].features)
        if (prediction) {
            if (testData[i].classification) {
                tp++
            } else {
                fp++
            }
        } else {
            if (!testData[i].classification) {
                tn++
            } else {
                fn++
            }
        }
    }
    val accuracy = (100.0 * (tp + tn)) / testData.size
    println("Final results for $target:\n  Accuracy: $accuracy%, TP: $tp, TN: $tn, FP: $fp, FN: $fn")
}

fun readData(type: String, number: Int): MutableList<FeatureInstance> {
    val dataSet = mutableListOf<FeatureInstance>()
    File("./data/${type}/${number}.txt").forEachLine { dataSet.add(getFeatures(it)) }
    return dataSet
}

fun getFeatures(line: String): FeatureInstance {
    val splitLine = line.split(",")
    val targetClass: Boolean = (splitLine[0] == "1")
    val pixelData = mutableListOf<Double>()
    for (i in 1 until splitLine.size) {
        pixelData.add(splitLine[i].toDouble() / 255.0)
    }
    return FeatureInstance(pixelData, targetClass)
}
