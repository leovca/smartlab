package smartlab.service;

import org.apache.commons.lang.ArrayUtils;
import weka.classifiers.lazy.IBk;
import weka.core.Instance;
import weka.core.Instances;

import java.util.Arrays;
import java.util.Collections;
import java.util.List;
import java.util.Objects;
import java.util.stream.Collectors;

public class KNNCustomClassifier extends IBk {


    public KNNCustomClassifier(int k) {
        super(k);
    }

    public double [] getInverseDistances(Instance instance) throws Exception{
        if (m_Train.numInstances() == 0) {
            //throw new Exception("No training instances!");
            return m_defaultModel.distributionForInstance(instance);
        }
        if ((m_WindowSize > 0) && (m_Train.numInstances() > m_WindowSize)) {
            m_kNNValid = false;
            boolean deletedInstance=false;
            while (m_Train.numInstances() > m_WindowSize) {
                m_Train.delete(0);
            }
            //rebuild datastructure KDTree currently can't delete
            if(deletedInstance==true)
                m_NNSearch.setInstances(m_Train);
        }

        // Select k by cross validation
        if (!m_kNNValid && (m_CrossValidate) && (m_kNNUpper >= 1)) {
            crossValidate();
        }

        m_NNSearch.addInstanceInfo(instance);

        Instances neighbours = m_NNSearch.kNearestNeighbours(instance, m_kNN);
        double [] distances = m_NNSearch.getDistances();

        Double[] allDistances = new Double [m_NumClasses];

//        for(int i = 0; i < m_NumClasses; i++) {
//            allDistances[i] = Double.MAX_VALUE;
//        }

        for(int i=0; i < neighbours.numInstances(); i++) {
            Instance current = neighbours.instance(i);
            allDistances[(int)current.classValue()] = distances[i];

            allDistances[(int)current.classValue()] =
                    allDistances[(int)current.classValue()] == null?distances[i]:
                    distances[i] < allDistances[(int)current.classValue()]?distances[i]:allDistances[(int)current.classValue()];
        }

        Double min = Arrays.stream(allDistances).reduce((aDouble, aDouble2) -> {
            if(aDouble == null) return aDouble2;
            if(aDouble2 == null) return aDouble;
            return aDouble < aDouble2 ? aDouble : aDouble2;
        }).get();

        Double max = Arrays.stream(allDistances).reduce((aDouble, aDouble2) -> {
            if(aDouble == null) return aDouble2;
            if(aDouble2 == null) return aDouble;
            return aDouble > aDouble2 ? aDouble : aDouble2;
        }).get();

        allDistances = Arrays.stream(allDistances)
                .map(aDouble -> {
                    if(aDouble == null) return max + 2;
                    return aDouble;
                })
                .map(aDouble -> normalise(aDouble, min, max + 2))
//                .map(aDouble -> normalise(aDouble, min, max + (max-min)))
                .map(aDouble -> reverseNumber(aDouble, 0.01d, 1d))
                .collect(Collectors.toList())
                .toArray(new Double[allDistances.length]);

        return ArrayUtils.toPrimitive(allDistances);

    }

    public Double reverseNumber(Double num, Double min, Double max) {
        if(num == null) return num;

        return (max + min) - num;
    }

    int compareTo(final Double c1, final Double c2) {
        final boolean f1, f2;
        return (f1 = c1 == null) ^ (f2 = c2 == null) ? f1 ? -1 : 1 : f1 && f2 ? 0 : c1.compareTo(c2);
    }

    Double normalise(Double inValue, Double min, Double max) {
        if(Objects.equals(max, min)){
            return (double) 0f;
        } else if(inValue == null){
            return  null;
        } else {
            return (inValue - min) / (max - min);
        }
    }


}
