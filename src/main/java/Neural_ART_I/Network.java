package Neural_ART_I;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;
import java.util.stream.Collectors;
import java.util.stream.Stream;

/**
 * Created by Jakub on 2017-03-15.
 */
public class Network {
    private List<Neuron> neurons;
    Boolean[][] inputs;
    private double sensitivity;
    private int maxNeurons;
    private int networkInputs;

    public Network(int maximumNumberOfNeurons, double sensitivity, int networkInputs){
        neurons = new ArrayList<>();
        
        this.maxNeurons = maximumNumberOfNeurons;
        this.sensitivity = sensitivity;
        this.networkInputs = networkInputs;
        
        neurons.add(new Neuron(neurons.size(), networkInputs));
    }

    public void prepareNetwork(){
    }

    public void sendInputs(){
        //Boolean[][] inputs = null;//load from image!!!
        neurons.forEach( (Neuron x)->(x.setInputs(inputs)) );
    }
    
    public void setInputs(Boolean[][] inputs){
        this.inputs = inputs;
    }

    public List<Neuron> getNeurons() {
        return neurons;
    }

    public int computeResult(){
        List<Neuron> bestResults = neurons.stream()
        .sorted((x, y)->((int)(100*(y.computeBottomToUpSimilatiry()-x.computeBottomToUpSimilatiry()))))
        .collect(Collectors.toList());
        for(Neuron n: bestResults){//no feedback from stream so this is neccessary.
            System.out.println("Best: "+n.getId());
            if( n.computeUpToBottomSimilatiry() > sensitivity ){
                System.out.println("Is winner");
                n.adaptWeights();
                return n.getId();
            }
        }
        Neuron n = new Neuron(neurons.size(), networkInputs, inputs);
        n.adaptWeights();
        neurons.add(n);
        System.out.println("Activated: "+n.getId());
        return n.getId();
    }
}
