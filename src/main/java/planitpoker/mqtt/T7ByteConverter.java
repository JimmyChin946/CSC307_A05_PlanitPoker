package planitpoker.mqtt;

import java.io.*;

/**
 * Converts things to and from bytes in a universal way so that we can send
 * information through mqtt
 *
 * @author Jude Shin
 *
 */
public class T7ByteConverter {
	private T7ByteConverter(){}
	
	public static byte[] toBytes(Object obj) throws IOException {
		ByteArrayOutputStream bos = new ByteArrayOutputStream();
		try (ObjectOutputStream out = new ObjectOutputStream(bos)) {
			out.writeObject(obj);
		}
		return bos.toByteArray();
	}

	public static <T> T fromBytes(byte[] data, Class<T> cls) throws IOException, ClassNotFoundException {
		ByteArrayInputStream bis = new ByteArrayInputStream(data);
		try (ObjectInputStream in = new ObjectInputStream(bis)) {
			return cls.cast(in.readObject());
		}
	}
}
