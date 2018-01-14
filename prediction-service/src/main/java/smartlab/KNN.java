package smartlab;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import weka.classifiers.Classifier;
import weka.classifiers.lazy.IBk;
import weka.core.*;
import java.util.ArrayList;


public class KNN {

    private static Logger log = LoggerFactory.getLogger(KNN.class);

    public static void main(String args[]) throws Exception {
        Attribute attrExternalTemperature = new Attribute("externalTemperatura");
        Attribute attrInternalTemperature = new Attribute("internalTemperature");
        Attribute attrUsers = new Attribute("users");
        Attribute attrHour = new Attribute("hour");
        Attribute attrPref = new Attribute("pref", new ArrayList<String>() {{
            add("16");
            add("17");
            add("18");
            add("19");
            add("20");
            add("21");
            add("22");
            add("23");
            add("24");
            add("25");
            add("26");
            add("27");
            add("28");
            add("29");
        }});

        ArrayList<Attribute> attributes = new ArrayList<Attribute>() {{
            add(attrExternalTemperature);
            add(attrInternalTemperature);
            add(attrUsers);
            add(attrHour);
            add(attrPref);
        }};

        Instances train = new Instances("dados", attributes, 4);
        train.setClassIndex(4);


        int tEx[] = new int[]{30, 28, 30, 25,28, 21};
        int tIn[] = new int[]{32, 30, 25,22, 30, 30};
        int tus[] = new int[]{4, 5, 5, 5,5, 5};
        int tho[] = new int[]{8, 16, 12,6, 16, 4};
//        String tpref[] = new String[]{"23", "24", "23", "20", "24", "21"};
        String tpref[] = new String[]{"23", "24", "23", "20", "24", "21"};

        for (int i = 0; i < 4; i++) {
            Instance i1 = new DenseInstance(5);
            i1.setValue(attrExternalTemperature, tEx[i]);
            i1.setValue(attrInternalTemperature, tIn[i]);
            i1.setValue(attrUsers, tus[i]);
            i1.setValue(attrHour, tho[i]);
            i1.setValue(attrPref, tpref[i]);
            train.add(i1);
        }


        Instances test = new Instances("dados", attributes, 3);
        test.setClassIndex(4);
        for (int i = 3; i < 6; i++) {
            Instance i1 = new DenseInstance(5);
            i1.setValue(attrExternalTemperature, tEx[i]);
            i1.setValue(attrInternalTemperature, tIn[i]);
            i1.setValue(attrUsers, tus[i]);
            i1.setValue(attrHour, tho[i]);
//            i1.setValue(attrPref, tpref[i]);
            test.add(i1);
        }

        Classifier ibk = new IBk(1);
        ibk.buildClassifier(train);

//        Evaluation eval = new Evaluation(train);
//        eval.evaluateModel(ibk, test);
//        /** Print the algorithm summary */
//        System.out.println("** KNN Demo  **");
//        System.out.println(eval.toSummaryString());
//        System.out.println(eval.toClassDetailsString());
//        System.out.println(eval.toMatrixString());

        for (int i = 0; i < 2; i++) {
            Double value = ibk.classifyInstance(test.instance(i));
            test.instance(i).setClassValue(value);
            double[] values =ibk.distributionForInstance(test.instance(i));

            for(int j = 0; j<values.length;j++){
                System.out.println(" Temp: "+attrPref.value(j)+"º = "+values[j]);
            }


            System.out.println(test.instance(i).stringValue(4));
            System.out.println("-----");
        }


    }

//    public void printResult() {
//        log.info("=== Dataset info ===");
//        log.info("Dataset name = {}", this.instances.relationName());
//        log.info("Number of instances = {}", this.instances.numInstances());
//        log.info("Number of attributes = {}", this.instances.numAttributes());
//        log.info("Number of classes = {}", this.instances.numClasses());
//
//        log.info("=== Configuration ===");
//        log.info("K = {}", this.K);
//        log.info("Distance measure = {}", this.distanceMeasure.getClass().getSimpleName());
//
//        log.info("=== Result ===");
//        log.info("Correctly Classified Instances \t\t{} ({}%)", correctly, classificationRate * 100);
//        log.info("Incorrectly Classified Instances \t\t{} ({}%)", incorrectly, errorRate * 100);
//        log.info("Time taken to classify \t\t{} s", this.result.getExecutionTime());
//    }
}