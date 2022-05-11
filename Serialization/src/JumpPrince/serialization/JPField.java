package Jumprince.serialization;

import static Jumprince.serialization.SerializationWriter.*;

public class JPField extends Base{
	
	private JPField(byte type) {
		this.TYPE = type;
	}
	
	public byte getByte() {
		return byteVal;
	}
	
	public boolean getBoolean() {
		return boolVal;
	}
	
	public char getChar() {
		return charVal;
	}
	
	public short getShort() {
		return shortVal;
	}
	
	public int getInt() {
		return intVal;
	}
	
	public long getLong() {
		return longVal;
	}
	
	public float getFloat() {
		return floatVal;
	}
	
	public double getDouble() {
		return doubleVal;
	}
	
	public static JPField Byte(String name, byte value) {
		JPField field = new JPField(Type.BYTE);
		field.setName(name);
		field.size += 1 + 1 + 1;
		field.byteVal = value;
		return field;
	}
	
	public static JPField Boolean(String name, boolean value) {
		JPField field = new JPField(Type.BOOLEAN);
		field.setName(name);
		field.size += 1 + 1 + 1;
		field.boolVal = value;
		return field;
	}
	
	public static JPField Char(String name, char value) {
		JPField field = new JPField(Type.CHAR);
		field.setName(name);
		field.size += 1 + 1 + 1;
		field.charVal = value;
		return field;
	}
	
	public static JPField Short(String name, short value) {
		JPField field = new JPField(Type.SHORT);
		field.setName(name);
		field.size += 1 + 1 + 2;
		field.shortVal = value;
		return field;
	}
	
	public static JPField Integer(String name, int value) {
		JPField field = new JPField(Type.INTEGER);
		field.setName(name);
		field.size += 1 + 1 + 4;
		field.intVal = value;
		return field;
	}
	
	public static JPField Long(String name, long value) {
		JPField field = new JPField(Type.LONG);
		field.setName(name);
		field.size += 1 + 1 + 8;
		field.longVal = value;
		return field;
	}
	
	public static JPField Float(String name, float value) {
		JPField field = new JPField(Type.BOOLEAN);
		field.setName(name);
		field.size += 1 + 1 + 4;
		field.floatVal = value;
		return field;
	}
	
	public static JPField Double(String name, double value) {
		JPField field = new JPField(Type.DOUBLE);
		field.setName(name);
		field.size += 1 + 1 + 8;
		field.doubleVal = value;
		return field;
	}
	
	private final byte CONTAINER_TYPE = ContainerType.FIELD;
	public final byte TYPE;
	
	private byte byteVal;
	private boolean boolVal;
	private char charVal;
	private short shortVal;
	private int intVal;
	private long longVal;
	private float floatVal;
	private double doubleVal;
	
	public int serialize(int pointer, byte[] bytes) {
		pointer = writeBytes(pointer, bytes, CONTAINER_TYPE);
		pointer = writeBytes(pointer, bytes, TYPE);
		pointer = writeBytes(pointer, bytes, size);
		pointer = writeBytes(pointer, bytes, nameLength);
		pointer = writeBytes(pointer, bytes, name.getBytes());
		
		switch(TYPE) {
		case Type.BYTE:
			pointer = writeBytes(pointer, bytes, byteVal);
			break;
		case Type.BOOLEAN:
			pointer = writeBytes(pointer, bytes, boolVal);
			break;
		case Type.CHAR:
			pointer = writeBytes(pointer, bytes, charVal);
			break;
		case Type.SHORT:
			pointer = writeBytes(pointer, bytes, shortVal);
			break;
		case Type.INTEGER:
			pointer = writeBytes(pointer, bytes, intVal);
			break;
		case Type.LONG:
			pointer = writeBytes(pointer, bytes, longVal);
			break;
		case Type.FLOAT:
			pointer = writeBytes(pointer, bytes, floatVal);
			break;
		case Type.DOUBLE:
			pointer = writeBytes(pointer, bytes, doubleVal);
			break;
		}
		return pointer;
	}
	
	public int serializeAsArrayElement(int pointer, byte[] bytes) {
		switch(TYPE) {
		case Type.BYTE:
			pointer = writeBytes(pointer, bytes, byteVal);
			break;
		case Type.BOOLEAN:
			pointer = writeBytes(pointer, bytes, boolVal);
			break;
		case Type.CHAR:
			pointer = writeBytes(pointer, bytes, charVal);
			break;
		case Type.SHORT:
			pointer = writeBytes(pointer, bytes, shortVal);
			break;
		case Type.INTEGER:
			pointer = writeBytes(pointer, bytes, intVal);
			break;
		case Type.LONG:
			pointer = writeBytes(pointer, bytes, longVal);
			break;
		case Type.FLOAT:
			pointer = writeBytes(pointer, bytes, floatVal);
			break;
		case Type.DOUBLE:
			pointer = writeBytes(pointer, bytes, doubleVal);
			break;
		}
		return pointer;
	}

	public static JPField Deserialize(int pointer, byte[] bytes) {
		pointer++;
		byte type = readByte(pointer, bytes);
		pointer++;
		JPField field = new JPField(type);
		field.size = readInt(pointer, bytes);
		pointer += 4;
		field.nameLength = readShort(pointer, bytes);
		pointer += 2;
		field.name = readString(pointer, bytes, field.nameLength);
		pointer += field.nameLength;
		
		switch(type) {
		case Type.BYTE:
			field.byteVal = readByte(pointer, bytes);
			pointer++;
			break;
		case Type.BOOLEAN:
			field.boolVal = readBoolean(pointer, bytes);
			pointer++;
			break;
		case Type.CHAR:
			field.charVal = readChar(pointer, bytes);
			pointer++;
			break;
		case Type.SHORT:
			field.shortVal = readShort(pointer, bytes);
			pointer += 2;
			break;
		case Type.INTEGER:
			field.intVal = readInt(pointer, bytes);
			pointer += 4;
			break;
		case Type.LONG:
			field.longVal = readLong(pointer, bytes);
			pointer += 8;
			break;
		case Type.FLOAT:
			field.floatVal = readFloat(pointer, bytes);
			pointer += 4;
			break;
		case Type.DOUBLE:
			field.doubleVal = readDouble(pointer, bytes);
			pointer += 8;
			break;
		}
		
		return field;
	}
	
}
