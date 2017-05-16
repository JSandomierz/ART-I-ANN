package Neural_ART_I;

import UI.UserInterface;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class Main {

    public static void main(String[] args) {
        System.out.println("Ahoj svete!");
	Network network = new Network();
	UserInterface.show(network);
    }
}
