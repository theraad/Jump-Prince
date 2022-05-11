package Jumprince.serialization;

import static Jumprince.serialization.SerializationWriter.*;
import java.util.ArrayList;
import java.util.List;


public class JPArray extends Base{

	public JPArray(byte type) {
		this.TYPE = type;
		size += 4 + 1 + 1;
	}
	
	private final byte CONTAINER_TYPE = ContainerType.ARRAY;
	private final byte TYPE;
	
	private int arraySize = 0;
	private List<JPField> elements = new ArrayList<JPField>();
	
	public byte[] getByteArray() {
		byte arr[] = new byte[elements.size()];
		for(int i = 0; i < elements.size(); i++) {
			arr[i] = elements.get(i).getByte();
		}
		return arr;
	}
	public boolean[] getBooleanArray() {
		boolean arr[] = new boolean[elements.size()];
		for(int i = 0; i < elements.size(); i++) {
			arr[i] = elements.get(i).getBoolean();
		}
		return arr;
	}
	
	public char[] getCharArray() {
		char arr[] = new char[elements.size()];
		for(int i = 0; i < elements.size(); i++) {
			arr[i] = elements.get(i).getChar();
		}
		return arr;
	}
	
	public short[] getShortArray() {
		short arr[] = new short[elements.size()];
		for(int i = 0; i < elements.size(); i++) {
			arr[i] = elements.get(i).getShort();
		}
		return arr;
	}
	
	public int[] getIntArray() {
		int arr[] = new int[elements.size()];
		for(int i = 0; i < elements.size(); i++) {
			arr[i] = elements.get(i).getInt();
		}
		return arr;
	}
	
	public long[] getLongArray() {
		long arr[] = new long[elements.size()];
		for(int i = 0; i < elements.size(); i++) {
			arr[i] = elements.get(i).getLong();
		}
		return arr;
	}
	
	public float[] getFloatArray() {
		float arr[] = new float[elements.size()];
		for(int i = 0; i < elements.size(); i++) {
			arr[i] = elements.get(i).getFloat();
		}
		return arr;
	}
	
	public double[] getDoubleArray() {
		double arr[] = new double[elements.size()];
		for(int i = 0; i < elements.size(); i++) {
			arr[i] = elements.get(i).getDouble();
		}
		return arr;
	}
	
	public static JPArray Byte(String name, byte[] values) {
		JPArray array = new JPArray(Type.BYTE);
		array.setName(name);
		array.arraySize = values.length;
		for(int i = 0; i < values.length; i++) {
			array.elements.add(JPField.Byte(name, values[i]));
		}
		array.size += 1*values.length;
		return array;
	}
	
	public static JPArray Boolean(String name, boolean[] values) {
		JPArray array = new JPArray(Type.BOOLEAN);
		array.setName(name);
		array.arraySize = values.length;
		for(int i = 0; i < values.length; i++) {
			array.elements.add(JPField.Boolean(name, values[i]));
		}
		array.size += 1*values.length;
		return array;
	}
	
	public static JPArray Char(String name, char[] values) {
		JPArray array = new JPArray(Type.CHAR);
		array.setName(name);
		array.arraySize = values.length;
		for(int i = 0; i < values.length; i++) {
			array.elements.add(JPField.Char(name, values[i]));
		}
		array.size += 1*values.length;
		return array;
	}
	
	public static JPArray Short(String name, short[] values) {
		JPArray array = new JPArray(Type.SHORT);
		array.setName(name);
		array.arraySize = values.length;
		for(int i = 0; i < values.length; i++) {
			array.elements.add(JPField.Short(name, values[i]));
		}
		array.size += 2*values.length;
		return array;
	}
	
	public static JPArray Integer(String name, int[] values) {
		JPArray array = new JPArray(Type.INTEGER);
		array.setName(name);
		array.arraySize = values.length;
		for(int i = 0; i < values.length; i++) {
			array.elements.add(JPField.Integer(name, values[i]));
		}
		array.size += 4*values.length;
		return array;
	}
	
	public static JPArray Long(String name, long[] values) {
		JPArray array = new JPArray(Type.LONG);
		array.setName(name);
		array.arraySize = values.length;
		for(int i = 0; i < values.length; i++) {
			array.elements.add(JPField.Long(name, values[i]));
		}
		array.size += 8*values.length;
		return array;
	}
	
	public static JPArray Float(String name, float[] values) {
		JPArray array = new JPArray(Type.BOOLEAN);
		array.setName(name);
		array.arraySize = values.length;
		for(int i = 0; i < values.length; i++) {
			array.elements.add(JPField.Float(name, values[i]));
		}
		array.size += 4*values.length;
		return array;
	}
	
	public static JPArray Double(String name, double[] values) {
		JPArray array = new JPArray(Type.DOUBLE);
		array.setName(name);
		array.arraySize = values.length;
		for(int i = 0; i < values.length; i++) {
			array.elements.add(JPField.Double(name, values[i]));
		}
		array.size += 8*values.length;
		return array;
	}
	
	public int serialize(int pointer, byte[] bytes) {
		pointer = writeBytes(pointer, bytes, CONTAINER_TYPE);
		pointer = writeBytes(pointer, bytes, TYPE);
		pointer = writeBytes(pointer, bytes, size);
		pointer = writeBytes(pointer, bytes, nameLength);
		pointer = writeBytes(pointer, bytes, name.getBytes());
		pointer = writeBytes(pointer, bytes, arraySize);
		
		for(int i = 0; i < elements.size(); i++) {
			pointer = elements.get(i).serializeAsArrayElement(pointer, bytes);
		}
		return pointer;
	}

	public static JPArray Deserialize(int pointer, byte[] bytes) {
		pointer++;
		byte type = readByte(pointer, bytes); //5
		JPArray array = new JPArray(type);
		pointer++; // 101
		array.size = readInt(pointer, bytes);
		pointer += 4;
		array.nameLength = readShort(pointer, bytes);
		pointer += 2;
		array.name = readString(pointer, bytes, array.nameLength);
		pointer += array.nameLength;
		array.arraySize = readInt(pointer, bytes);
		pointer += 4;
		for(int i = 0; i < array.arraySize; i++) {
		switch(type) {
			case Type.BYTE:
				array.elements.add(JPField.Byte("", readByte(pointer, bytes)));
				pointer++;
				break;
			case Type.BOOLEAN:
				array.elements.add(JPField.Boolean("", readBoolean(pointer, bytes)));
				pointer++;
				break;
			case Type.CHAR:
				array.elements.add(JPField.Char("", readChar(pointer, bytes)));
				pointer++;
				break;
			case Type.SHORT:
				array.elements.add(JPField.Short("", readShort(pointer, bytes)));
				pointer += 2;
				break;
			case Type.INTEGER:
				array.elements.add(JPField.Integer("", readInt(pointer, bytes)));
				pointer += 4;
				break;
			case Type.LONG:
				array.elements.add(JPField.Long("", readLong(pointer, bytes)));
				pointer += 8;
				break;
			case Type.FLOAT:
				array.elements.add(JPField.Float("", readFloat(pointer, bytes)));
				pointer += 4;
				break;
			case Type.DOUBLE:
				array.elements.add(JPField.Double("", readDouble(pointer, bytes)));
				pointer += 8;
				break;
			}
		}
		return array;
	}
	
}
