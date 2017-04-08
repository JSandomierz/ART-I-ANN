package Neural_WTA;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;

import Neural_WTA.Network;

public class NetworkSerializator {
	final static String EXTENSON = "network";
	private String source;
	public NetworkSerializator(String source) {
		this.source=source;
	}
	public void save(Network n) throws FileNotFoundException, IOException{
		try (ObjectOutputStream outputStream = new ObjectOutputStream(new FileOutputStream(source+"."+EXTENSON))) {
		    outputStream.writeObject(n);
		}
	}
	public Network load() throws FileNotFoundException, IOException, ClassNotFoundException{
		Network deserializedNetwork = null;
		try (ObjectInputStream inputStream = new ObjectInputStream(new FileInputStream(source+"."+EXTENSON))) {
		    deserializedNetwork = (Network) inputStream.readObject();
		}
		return deserializedNetwork;
	}
}
