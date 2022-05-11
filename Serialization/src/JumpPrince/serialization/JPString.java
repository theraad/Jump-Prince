package Jumprince.serialization;

import static Jumprince.serialization.SerializationWriter.*;

public class JPString extends Base{
	
	private final byte CONTAINER_TYPE = ContainerType.STRING;
	private short stringLength;
	private byte[] string;
	
	public JPString(String name, String str) {
		setName(name);
		stringLength = (short)str.length();
		string = str.getBytes();
		size += 1 + 2 + string.length;
	}
	
	public String getString() {
		return new String(string);
	}
	
	public int serialize(int pointer, byte[] bytes) {
		pointer = writeBytes(pointer, bytes, CONTAINER_TYPE);
		pointer = writeBytes(pointer, bytes, size);
		pointer = writeBytes(pointer, bytes, nameLength);
		pointer = writeBytes(pointer, bytes, name.getBytes());
		pointer = writeBytes(pointer, bytes, stringLength);
		pointer = writeBytes(pointer, bytes, string);
		return pointer;
	}

	public static JPString Deserialize(int pointer, byte[] bytes) {
		JPString string = new JPString("", "");
		pointer++;
		string.size = readInt(pointer, bytes);
		pointer += 4;
		string.nameLength = readShort(pointer, bytes);
		pointer += 2;
		string.name = readString(pointer, bytes, string.nameLength);
		pointer += string.nameLength;
		string.stringLength = readShort(pointer, bytes);
		pointer += 2;
		string.string = readString(pointer, bytes, string.stringLength).getBytes();
		pointer += string.stringLength;
		return string;
	}

}
