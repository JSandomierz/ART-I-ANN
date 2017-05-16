/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Neural_ART_I;

import org.junit.After;
import org.junit.AfterClass;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;
import static org.junit.Assert.*;
import org.junit.Ignore;

/**
 *
 * @author Jakub
 */
public class NetworkTest {
    
    private Boolean[][] inputsA, inputsB, inputsC, inputsD;
    
    public NetworkTest() {
    }
    
    @BeforeClass
    public static void setUpClass() {
    }
    
    @AfterClass
    public static void tearDownClass() {
    }
    
    @Before
    public void setUp() {
        inputsA = new Boolean[][]{
            {true, false, false, false, false},
            {false, true, false, false, false},
            {false, false, true, false, false},
            {false, false, false, true, false},
            {false, false, false, false, true},
        };
        inputsB = new Boolean[][]{
            {true, false, false, false, true},
            {false, true, false, true, false},
            {false, false, true, false, false},
            {false, true, false, true, false},
            {true, false, false, false, true},
        };
        inputsC = new Boolean[][]{
            {true, false, false, false, true},
            {false, true, false, true, false},
            {true, true, true, true, true},
            {false, true, false, true, false},
            {true, false, false, false, true},
        };
        inputsD = new Boolean[][]{
            {true, false, false, false, true},
            {true, true, false, true, true},
            {true, true, true, true, true},
            {true, true, false, true, true},
            {true, false, false, false, true},
        };
    }
    
    @After
    public void tearDown() {
    }

    /**
     * Test of prepareNetwork method, of class Network.
     */
    @Ignore
    @Test
    public void testPrepareNetwork() {
        System.out.println("prepareNetwork");
        Network instance = null;
        instance.prepareNetwork();
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of sendInputs method, of class Network.
     */
    @Ignore
    @Test
    public void testSendInputs() {
        System.out.println("sendInputs");
        Network instance = null;
        //instance.sendInputs();
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of computeResult method, of class Network.
     */
    @Test
    public void testComputeResult() throws Exception {
        System.out.println("computeResult");
        Network instance = new Network();
	instance.init(25, 0.7);
        System.out.println("Passing A");
        instance.setInputs(inputsA);
        instance.sendInputs();
        instance.computeResult();
        
        boolean neuronActivated = false;
        for( Neuron n: instance.getNeurons() ){
            Double result[] = {
                2.0/11.0, 1.0/26.0, 1.0/26.0, 1.0/26.0, 1.0/26.0,
                1.0/26.0, 2.0/11.0, 1.0/26.0, 1.0/26.0, 1.0/26.0,
                1.0/26.0, 1.0/26.0, 2.0/11.0, 1.0/26.0, 1.0/26.0,
                1.0/26.0, 1.0/26.0, 1.0/26.0, 2.0/11.0, 1.0/26.0,
                1.0/26.0, 1.0/26.0, 1.0/26.0, 1.0/26.0, 2.0/11.0,
            };
            assertArrayEquals(n.getBottomToUpConnections().toArray(), result);
        }
        
        System.out.println("Passing B");
        instance.setInputs(inputsB);
        instance.sendInputs();
        System.out.println("Passing B2");
        instance.computeResult();
        System.out.println("Passing B3");
        
        int activeNeurons = 0;
        for( Neuron n: instance.getNeurons() ){
            activeNeurons++;
        }
        assertEquals(2, activeNeurons
        );
        System.out.println("Passing C");
        instance.setInputs(inputsC);
        instance.sendInputs();
        instance.computeResult();
        
        activeNeurons = 0;
        for( Neuron n: instance.getNeurons() ){
            activeNeurons++;
        }
        assertEquals(3, activeNeurons);
        
        System.out.println("Passing D");
        instance.setInputs(inputsD);
        instance.sendInputs();
        assertEquals(2, instance.computeResult());
    }
    
}
