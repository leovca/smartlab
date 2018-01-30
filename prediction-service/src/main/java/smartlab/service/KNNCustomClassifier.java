package smartlab.service;

import weka.classifiers.lazy.IBk;
import weka.core.Instance;
import weka.core.Instances;

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

        for(int i = 0; i < m_NumClasses; i++) {
            distances[i] = 1.0 / Math.max(1,m_Train.numInstances());
        }

        return distances;

    }
}
