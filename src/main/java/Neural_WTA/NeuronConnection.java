package Neural_WTA;

/**
 * Created by Jakub on 2017-03-15.
 */
public class NeuronConnection {
    private float input;
    private float weight;

    public NeuronConnection(float input, float weight){
        this.input = input;
        this.weight = weight;
    }

    public float getWeight() {
        return weight;
    }

    public void setWeight(float weight) {
        this.weight = weight;
    }

    public float getInput() {
        return input;
    }

    public void setInput(float input) {
        this.input = input;
    }
}
