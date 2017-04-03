package Neural_WTA;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Jakub on 2017-03-15.
 */
public class Neuron {
    public final int id;
    private List<NeuronConnection> connections;
    private float output;

    public Neuron(int id){
        connections = new ArrayList<>();
        output = 0;
        this.id = id;
    }

    public void addConnection(NeuronConnection connection){
        connections.add( connection );
    }

    public void setInputs(List<Float> inputs){
        for(int i=0;i<inputs.size();i++){
            connections.get(i).setInput( inputs.get(i) );
            //System.out.println("Connection "+i+": "+inputs.get(i));
        }
    }

    public void computeOutput(){
        output = connections.stream().map( (x) -> Math.abs(x.getWeight() - x.getInput()) ).reduce( 0.0f, (x,y) -> x+y );
    }

    public float getOutput() {
        return output;
    }

    public void adaptWeights( float learningRate ){
        for(NeuronConnection connection : connections){
            connection.setWeight( connection.getWeight()+learningRate*(connection.getInput()-connection.getWeight()) );
        }
    }
}
