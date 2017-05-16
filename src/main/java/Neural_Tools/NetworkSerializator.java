/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Neural_Tools;

import Neural_ART_I.Network;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;


public class NetworkSerializator {
	private String source;
	public NetworkSerializator(String source) {
		this.source=source;
	}
	
	public void save(Network n) throws FileNotFoundException, IOException{
		try (ObjectOutputStream outputStream = new ObjectOutputStream(new FileOutputStream(source))) {
		    outputStream.writeObject(n);
		}
	}
	public Network load() throws FileNotFoundException, IOException, ClassNotFoundException{
		Network deserializedNetwork = null;
		try (ObjectInputStream inputStream = new ObjectInputStream(new FileInputStream(source))) {
		    deserializedNetwork = (Network) inputStream.readObject();
		}
		return deserializedNetwork;
	}
}
