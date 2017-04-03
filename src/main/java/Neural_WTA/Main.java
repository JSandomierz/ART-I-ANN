package Neural_WTA;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class Main {

    public static void main(String[] args) {
        System.out.println("Ahoj svete!");
        Network network = new Network(5, 0.5f);
        network.prepareNetwork(2);

        int numberOfInputs = 4;
        List<List<Float>> inputs = new ArrayList<>();
        for(int i=0;i<numberOfInputs;i++){
            inputs.add( new ArrayList<>() );
        }
        inputs.get(0).add(0.0f);
        inputs.get(0).add(0.0f);
        inputs.get(1).add(1.0f);
        inputs.get(1).add(1.0f);
        inputs.get(2).add(0.0f);
        inputs.get(2).add(1.0f);
        inputs.get(3).add(1.0f);
        inputs.get(3).add(0.0f);

        for( List<Float> input : inputs){//learning
            network.setInputs(input);
            network.sendInputs();
            network.computeResult(true);
        }
        Collections.shuffle(inputs);
        for( List<Float> input : inputs){//learning
            System.out.println("Current Input:");
            for(Float f : input) System.out.println(f);
            network.setInputs(input);
            network.sendInputs();
            network.computeResult(false);
        }

    }
}
