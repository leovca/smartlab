package smartlab.service;

import org.springframework.stereotype.Service;
import smartlab.model.*;
import weka.classifiers.Classifier;
import weka.classifiers.lazy.IBk;
import weka.core.Attribute;
import weka.core.DenseInstance;
import weka.core.Instance;
import weka.core.Instances;
import weka.filters.Filter;
import weka.filters.unsupervised.attribute.Normalize;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;


@Service
public class KNNService {

    private Attribute attrExternalTemperature = new Attribute("externalTemperatura");
    private Attribute attrInternalTemperature = new Attribute("internalTemperature");
    private Attribute attrUsers = new Attribute("users");
    private Attribute attrHour = new Attribute("hour");

    private Attribute attrPref = new Attribute("pref", Arrays.asList(
            new String[]{"16","17","18","19","20","21","22","23","24","25","26","27","28","29","30"}));

    private Attribute attrPref2 = new Attribute("pref");

    private ArrayList<Attribute> attributes = new ArrayList<Attribute>() {{
        add(attrExternalTemperature);
        add(attrInternalTemperature);
        add(attrUsers);
        add(attrHour);
        add(attrPref);
    }};

    private ArrayList<Attribute> attributes2 = new ArrayList<Attribute>() {{
        add(attrExternalTemperature);
        add(attrInternalTemperature);
        add(attrUsers);
        add(attrHour);
        add(attrPref2);
    }};



    private Instance makeInstance(Preference preference){
        Instance instance = new DenseInstance(5);
        instance.setValue(attrExternalTemperature, preference.getExternalTemperature());
        instance.setValue(attrInternalTemperature, preference.getInternalTemperature());
        instance.setValue(attrUsers, preference.getOnlineUsers());
        instance.setValue(attrHour, preference.getHour());
        instance.setValue(attrPref, preference.getVote().toString());
        return instance;
    }

    private Instance makeInstance2(Preference preference){
        Instance instance = new DenseInstance(5);
        instance.setValue(attrExternalTemperature, preference.getExternalTemperature());
        instance.setValue(attrInternalTemperature, preference.getInternalTemperature());
        instance.setValue(attrUsers, preference.getOnlineUsers());
        instance.setValue(attrHour, preference.getHour());
        instance.setValue(attrPref2, preference.getVote());
        return instance;
    }

    private Instances instances(List<Preference> preferences) throws Exception {

        Instances train = new Instances("dados", attributes, preferences.size());
        train.setClassIndex(4);

        List<Instance> instances = new ArrayList<>();

        preferences.stream()
                .map(this::makeInstance)
                .forEach(instances::add);

        train.addAll(instances);

        Normalize normalizeFilter = new Normalize();
        normalizeFilter.setInputFormat(train);
        train = Filter.useFilter(train, normalizeFilter);

        return train;
    }

    private Instances instances2(List<Preference> preferences) throws Exception {

        Instances train = new Instances("dados", attributes2, preferences.size());
        train.setClassIndex(4);

        List<Instance> instances = new ArrayList<>();

        preferences.stream()
                .map(this::makeInstance2)
                .forEach(instances::add);

        train.addAll(instances);

        Normalize normalizeFilter = new Normalize();
        normalizeFilter.setInputFormat(train);
        train = Filter.useFilter(train, normalizeFilter);

        return train;
    }

    private List<Vote> getRatings(KNNCustomClassifier classifier, Instance instance) throws Exception {
        List<Vote> temperatures = new ArrayList<>();
        double[] values = classifier.getInverseDistances(instance);

        for (int i = 0; i < values.length; i++) {
            temperatures.add(new Vote(attrPref.value(i), values[i]));
        }
        return temperatures;
    }

    private List<Vote> getRatingsSuavizados(Double temperatura, Double suavizacao) throws Exception {
        List<Vote> temperaturas = new ArrayList<>();

        for (int i = 0; i < 14; i++) {
            double temp = i+16;

            temperaturas.add(new Vote(attrPref.value(i), Math.pow(suavizacao,Math.abs(temp - temperatura))));
        }

        return temperaturas;
    }

    public UserTemperatureProfile calculateTemperatureProfile(PredictionPackage predictionPackage) throws Exception {
        List<Preference> preferenceList = predictionPackage.getVoteList();
        Preference current = predictionPackage.getCurrente();

        UserTemperatureProfile userTemperatureProfile = new UserTemperatureProfile();

        int min = preferenceList.stream()
                .map(preference -> preference.getVote())
                .min(Integer::compareTo)
                .get();

        current.setVote(min);
        preferenceList.add(current);

        Instances instances  = instances(preferenceList);
        Instance currentInstance = instances.remove(instances.numInstances()-1);
        KNNCustomClassifier ibk = new KNNCustomClassifier(instances.size());
        ibk.buildClassifier(instances);

        if(predictionPackage.getAlgortimoPreference().equals(AlgortimoPreference.Distancia)) {
            userTemperatureProfile.setVotes(getRatings(ibk, currentInstance));
        }

        userTemperatureProfile.setIdUsuario(predictionPackage.getIdUsuario());

        instances  = instances2(preferenceList);
        currentInstance = instances.remove(instances.numInstances()-1);
        Classifier classifier = new IBk(predictionPackage.getKnn());
        classifier.buildClassifier(instances);
        classifier.classifyInstance(currentInstance);

//        userTemperatureProfile.setTemperatura(Double.valueOf(currentInstance.toString(attrPref2)));
        userTemperatureProfile.setTemperatura(classifier.classifyInstance(currentInstance));

        if(predictionPackage.getAlgortimoPreference().equals(AlgortimoPreference.Suavizacao)) {
            userTemperatureProfile.setVotes(getRatingsSuavizados(userTemperatureProfile.getTemperatura(), predictionPackage.getSuavizacao()));
        }

        return userTemperatureProfile;
    }

}