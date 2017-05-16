package Neural_ART_I;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by Jakub on 2017-03-15.
 */
public class Neuron implements Serializable {
    public final int id;
    private List<Double> upToBottomConnections;
    private List<Double> bottomToUpConnections;
    private List<Double> inputs;

    public double similarityRatio;

    public Neuron(int id, int numberOfInputs){
        upToBottomConnections = new ArrayList<>();
        bottomToUpConnections = new ArrayList<>();        
        this.id = id;
        
        for(int i=0;i<numberOfInputs;i++){
            bottomToUpConnections.add( 1.0/(double)(numberOfInputs+1) );
            upToBottomConnections.add( 1.0 );
        }
    }
    
    public Neuron(int id, int numberOfInputs, Boolean[][] inputArray){
        this(id, numberOfInputs);
        this.setInputs(inputArray);
    }

    public void setInputs(Boolean[][] inputArray){
        inputs = new ArrayList<>();
        for( Boolean[] x: inputArray ){
            for(boolean y: x){
                inputs.add( (y?1.0:0.0) );
            }
        }
    }

    public double computeUpToBottomSimilatiry(){
        double result=0.0;
        for( int i=0;i<inputs.size();i++ ){
            if( inputs.get(i)>0.0 ){
                result+=upToBottomConnections.get(i);
            }
        }
        return result / inputs.stream().mapToDouble((x)->(x)).sum(); 
    }
    public double computeBottomToUpSimilatiry(){
        double result=0.0;
        for( int i=0;i<inputs.size();i++ ){
            if( inputs.get(i)>0.0 ){
                result+=bottomToUpConnections.get(i);
            }
        }
        similarityRatio = result;
        return result;
    }

    public void adaptWeights( ){
        double oldVSum = 0.0;
        for( int i=0;i<inputs.size();i++ ){
            if(inputs.get(i)>0.0) oldVSum+=upToBottomConnections.get(i);
        }
        for( int i=0;i<upToBottomConnections.size();i++ ){
            upToBottomConnections.set(i, inputs.get(i) * inputs.get(i));            
            if(inputs.get(i)>0.0){
                bottomToUpConnections.set(i, (upToBottomConnections.get(i)/(0.5+oldVSum)));
            }
            
        }
    }

    public int getId() {
        return id;
    }

    public List<Double> getUpToBottomConnections() {
        return upToBottomConnections;
    }

    public List<Double> getBottomToUpConnections() {
        return bottomToUpConnections;
    }

    public List<Double> getInputs() {
        return inputs;
    }
}
