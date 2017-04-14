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
    private double sensitivity;

    public Network(int numberOfNeurons, double sensitivity, int numberOfConnections){
        neurons = new ArrayList<>();
        for(int i=0;i<numberOfNeurons;i++){
            neurons.add( new Neuron(i, numberOfConnections) );
        }

        this.sensitivity = sensitivity;
    }

    public void prepareNetwork(){
        
    }

    public void sendInputs(Boolean[][] inputs){
        //Boolean[][] inputs = null;//load from image!!!
        neurons.forEach( (Neuron x)->(x.setInputs(inputs)) );
    }

    public List<Neuron> getNeurons() {
        return neurons;
    }

    public int computeResult(){
        List<Neuron> bestResults = neurons.stream()
        .filter((x)->(x.isActive()))
        .sorted((x, y)->((int)(100*(y.computeBottomToUpSimilatiry()-x.computeBottomToUpSimilatiry()))))
        .collect(Collectors.toList());
        for(Neuron n: bestResults){//no feedback from stream so this is neccessary.
            System.out.println("Winner: "+n.getId());
            if( n.computeUpToBottomSimilatiry() > sensitivity ){
                System.out.println("Passed");
                n.adaptWeights();
                return n.getId();
            }
        }
        Neuron n = neurons.stream().filter((x)->(!x.isActive())).findAny().get();
        n.activate();
        n.adaptWeights();
        System.out.println("Activated: "+n.getId());
        return n.getId();
    }
}
