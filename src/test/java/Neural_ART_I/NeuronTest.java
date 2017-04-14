/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Neural_ART_I;

import Neural_ART_I.Neuron;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
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
public class NeuronTest {
    
    private Boolean[][] inputsA, inputsB, inputsC;
    
    public NeuronTest() {
    }
    
    @BeforeClass
    public static void setUpClass() {
    }
    
    @AfterClass
    public static void tearDownClass() {
    }
    
    @Before
    @SuppressWarnings("empty-statement")
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
    }
    
    @After
    public void tearDown() {
    }

    /**
     * Test of setInputs method, of class Neuron.
     */
    @Test
    public void testSetInputs() {
        System.out.println("setInputs");
        
        Neuron instance = new Neuron(0, 25);
        instance.setInputs(inputsA);
        
        Double result[] = {
            1.0, 0.0, 0.0, 0.0, 0.0,
            0.0, 1.0, 0.0, 0.0, 0.0,
            0.0, 0.0, 1.0, 0.0, 0.0,
            0.0, 0.0, 0.0, 1.0, 0.0,
            0.0, 0.0, 0.0, 0.0, 1.0,
        };
        
        assertArrayEquals(instance.getInputs().toArray(), result);
    }

    /**
     * Test of computeBottomUpSimilarity method, of class Neuron.
     */
    @Test
    public void testComputeBottomUpSimilarity() {
        System.out.println("computeBottomUpSimilarity");
        Neuron instance = new Neuron(0, 25);
        instance.setInputs(inputsA);
        instance.adaptWeights();
        instance.setInputs(inputsB);
        //instance.adaptWeights();
        instance.setInputs(inputsC);
        
        double expResult = 1.217;
        double result = instance.computeBottomToUpSimilatiry();
        assertEquals(expResult, result, 0.1);
    }

    /**
     * Test of computeUpBottomSimilarity method, of class Neuron.
     */
    @Test
    public void testComputeUpBottomSimilarity() {
        System.out.println("computeUpBottomSimilarity");
        Neuron instance = new Neuron(0, 25);
        instance.setInputs(inputsA);
        instance.adaptWeights();
        instance.setInputs(inputsB);
        
        
        
        double expResult = 5.0/9.0;
        double result = instance.computeUpToBottomSimilatiry();
        assertEquals(expResult, result, 0.1);
    }

    /**
     * Test of adaptWeights method, of class Neuron.
     */
    @Test
    public void testAdaptWeights() {
        System.out.println("adaptWeights");
        
        Neuron instance = new Neuron(0, 25);
        instance.setInputs(inputsA);
        instance.adaptWeights();
        
        Double result[] = {
            2.0/11.0, 1.0/26.0, 1.0/26.0, 1.0/26.0, 1.0/26.0,
            1.0/26.0, 2.0/11.0, 1.0/26.0, 1.0/26.0, 1.0/26.0,
            1.0/26.0, 1.0/26.0, 2.0/11.0, 1.0/26.0, 1.0/26.0,
            1.0/26.0, 1.0/26.0, 1.0/26.0, 2.0/11.0, 1.0/26.0,
            1.0/26.0, 1.0/26.0, 1.0/26.0, 1.0/26.0, 2.0/11.0,
        };
        
        assertArrayEquals(instance.getBottomToUpConnections().toArray(), result);
        
        Double result2[] = {
            1.0, 0.0, 0.0, 0.0, 0.0,
            0.0, 1.0, 0.0, 0.0, 0.0,
            0.0, 0.0, 1.0, 0.0, 0.0,
            0.0, 0.0, 0.0, 1.0, 0.0,
            0.0, 0.0, 0.0, 0.0, 1.0,
        };
        
        assertArrayEquals(instance.getUpToBottomConnections().toArray(), result2);
        
        Neuron instance2 = new Neuron(0, 25);
        instance2.setInputs(inputsB);
        instance2.adaptWeights();
        
        Double result3[] = {
            2.0/19.0, 1.0/26.0, 1.0/26.0, 1.0/26.0, 2.0/19.0,
            1.0/26.0, 2.0/19.0, 1.0/26.0, 2.0/19.0, 1.0/26.0,
            1.0/26.0, 1.0/26.0, 2.0/19.0, 1.0/26.0, 1.0/26.0,
            1.0/26.0, 2.0/19.0, 1.0/26.0, 2.0/19.0, 1.0/26.0,
            2.0/19.0, 1.0/26.0, 1.0/26.0, 1.0/26.0, 2.0/19.0,
        };
        
        assertArrayEquals(instance2.getBottomToUpConnections().toArray(), result3);
    }
    
}
