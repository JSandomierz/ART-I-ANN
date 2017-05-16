package Neural_ART_I;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;
import java.util.Random;
import java.util.stream.Collectors;
import java.util.stream.Stream;

/**
 * Created by Jakub on 2017-03-15.
 */
public class Network implements Serializable{
    private List<Neuron> neurons;
    Boolean[][] inputs;
    private double sensitivity;
    private int networkInputs;
    boolean initialized = false;
    
    Neuron winningNeuron;

    public Network(){
	neurons = new ArrayList<>();
    }
    
    public void init(int networkInputs, double sensitivity){
	    this.networkInputs = networkInputs;
	    setSensitivity(sensitivity);
	    neurons.add(new Neuron(neurons.size(), networkInputs));
	    initialized = true;
    }
    
    public boolean isInitialized(){
	    return initialized;
    }

    public void prepareNetwork(){
    }

    public void sendInputs(){
        //Boolean[][] inputs = null;//load from image!!!
        neurons.forEach( (Neuron x)->(x.setInputs(inputs)) );
    }
    
    public void setInputs(Boolean[][] inputs) throws Exception{
	if(!(inputs.length>0 && (inputs.length*inputs[0].length == networkInputs))) throw new Exception("Input is too big size.");
        this.inputs = inputs;
    }

    public List<Neuron> getNeurons() {
        return neurons;
    }

    public void setSensitivity(double sensitivity){
	    this.sensitivity = sensitivity;
    }
    
    public void clear(){
	    neurons.clear();
	    initialized = false;
    }
    
    public int computeResult() throws Exception{
	if(initialized){
		List<Neuron> bestResults = neurons.stream()
		.sorted((x, y)->((int)(100*(y.computeBottomToUpSimilatiry()-x.computeBottomToUpSimilatiry()))))
		.collect(Collectors.toList());
		for(Neuron n: bestResults){//no feedback from stream so this is neccessary.
                    double sim = n.computeUpToBottomSimilatiry();
                    System.out.println("Neuron "+n.getId()+" is simmilar with image at: "+sim);
		    if( sim > sensitivity ){
			return n.getId();
		    }
		}
		Neuron n = new Neuron(neurons.size(), networkInputs, inputs);
		neurons.add(n);
		System.out.println("Activated: "+n.getId());
		return n.getId();
	}
	throw new Exception("Network was not inicialized.");
    }
}
