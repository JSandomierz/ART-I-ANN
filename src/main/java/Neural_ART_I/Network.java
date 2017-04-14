package Neural_ART_I;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

/**
 * Created by Jakub on 2017-03-15.
 */
public class Network {
    private List<Neuron> neurons;
    private List<Double> inputs;

    private float learningRate;

    /*public Network(int numberOfNeurons, float learningRate){
        inputs = new ArrayList<>();
        neurons = new ArrayList<>();
        for(int i=0;i<numberOfNeurons;i++){
            neurons.add( new Neuron(i) );
        }

        this.learningRate = learningRate;
    }

    public void prepareNetwork(int numberOfInputs){
        for(int i=0; i<numberOfInputs;i++){
            inputs.add( (float)i+1 );
        }
        for(Neuron neuron : neurons ){
            Random r = new Random();
            for(Float input : inputs){
                neuron.addConnection( new NeuronConnection(0.0f, (float)r.nextDouble()) );
            }
        }
    }

    public void sendInputs(){
        for(Neuron neuron : neurons ){
            neuron.setInputs(inputs);
        }
    }

    public void setInputs(List<Float> inputs){
        this.inputs = inputs;
    }

    public void computeResult(boolean learning){
        for(int i=0;i<neurons.size();i++){
            neurons.get(i).computeOutput();
            System.out.println("Output of neuron "+(i+1)+" is: "+neurons.get(i).getOutput());
        }
        if(learning)
        neurons.stream().max( (x,y) -> (int)((y.getOutput() - x.getOutput())*10) ).get().adaptWeights( learningRate );
        else
        System.out.println(neurons.stream().max( (x,y) -> (int)((y.getOutput() - x.getOutput())*10) ).get().id);
    }*/
}
