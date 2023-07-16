package src

class FeatureInstance(featureValues: MutableList<Double>, targetClassification: Boolean) {
    var features: MutableList<Double> = featureValues
    var classification: Boolean = targetClassification
}
