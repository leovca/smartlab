package smartlab.service;

import org.springframework.stereotype.Service;
import smartlab.model.Temperature;
import smartlab.model.UserTemperatureProfile;
import smartlab.model.Vote;
import weka.classifiers.Classifier;
import weka.classifiers.lazy.IBk;
import weka.core.Attribute;
import weka.core.DenseInstance;
import weka.core.Instance;
import weka.core.Instances;

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

    private ArrayList<Attribute> attributes = new ArrayList<Attribute>() {{
        add(attrExternalTemperature);
        add(attrInternalTemperature);
        add(attrUsers);
        add(attrHour);
        add(attrPref);
    }};



    private Instance makeInstance(Vote vote){
        Instance instance = new DenseInstance(5);
        instance.setValue(attrExternalTemperature, vote.getExternalTemperature());
        instance.setValue(attrInternalTemperature, vote.getInternalTemperature());
        instance.setValue(attrUsers, vote.getOnlineUsers());
        instance.setValue(attrHour, vote.getHour());
        instance.setValue(attrPref, vote.getVote().toString());
        return instance;
    }

    private Classifier buildModel(List<Vote> votes) throws Exception {

        Instances train = new Instances("dados", attributes, votes.size());
        train.setClassIndex(4);

        List<Instance> instances = new ArrayList<>();

        votes.stream()
                .map(this::makeInstance)
                .forEach(instances::add);

        train.addAll(instances);

        Classifier ibk = new IBk(1);
        ibk.buildClassifier(train);

        return ibk;
    }

    private List<Temperature> getRatings(Classifier classifier, Instance instance) throws Exception {
        List<Temperature> temperatures = new ArrayList<>();
        double[] values = classifier.distributionForInstance(instance);

        for (int i = 0; i < values.length; i++) {
            temperatures.add(new Temperature(attrPref.value(i), values[i]));
        }
        return temperatures;
    }

    public UserTemperatureProfile calculateTemperatureProfile(List<Vote> voteList, Vote current) throws Exception {
        UserTemperatureProfile userTemperatureProfile = new UserTemperatureProfile();

//        if(voteList.size()>1){
        Classifier classifier = buildModel(voteList);
        Instance currentInstance = makeInstance(current);
        userTemperatureProfile.setTemperatures(getRatings(classifier, currentInstance));
//        return userTemperatureProfile;
//        } else if (voteList.size() == 1) {
//            userTemperatureProfile.setTemperatures(new ArrayList<>());
//            for (int i=16; i<31; i++){
//                userTemperatureProfile.getTemperatures().add(new Temperature(String.valueOf(i), 0d));
//            }
//            int index = voteList.get(0).getVote() -16;
//            userTemperatureProfile.getTemperatures().get(index).setRating(10d);
//        }
        return userTemperatureProfile;
    }

}