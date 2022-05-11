package Jumprince.serialization;

import static Jumprince.serialization.SerializationWriter.*;

import java.util.ArrayList;
import java.util.List;

public class JPObject extends Base{

	private final byte CONTAINER_TYPE = ContainerType.OBJECT;
	
	private List<JPField> fields = new ArrayList<JPField>();
	private List<JPArray> arrays = new ArrayList<JPArray>();
	private List<JPString> strings = new ArrayList<JPString>();
	private int arrayCount, fieldCount, stringCount;
	
	public JPObject(String name) {
		setName(name);
		arrays = new ArrayList<JPArray>();
		fields = new ArrayList<JPField>();
		strings = new ArrayList<JPString>();
		size += 1 + 4 + 4 + 4;
	}
	
	public JPField findField(String fieldName) {
		for(int i = 0; i < fields.size(); i++)
			if(fields.get(i).name.equals(fieldName))
				return fields.get(i);
		return null;
	}
	
	public JPString findString(String stringName) {
		for(int i = 0; i < strings.size(); i++)
			if(strings.get(i).name.equals(stringName))
				return strings.get(i);
		return null;
	}
	
	public JPArray findArray(String arrayName) {
		for(int i = 0; i < arrays.size(); i++)
			if(arrays.get(i).name.equals(arrayName))
				return arrays.get(i);
		return null;
	}
	
	public void add(JPField field) {
		fields.add(field);
		size += field.size;
		fieldCount++;
	}
	
	public void add(JPArray array) {
		arrays.add(array);
		size += array.size;
		arrayCount++;
	}
	
	public void add(JPString string) {
		strings.add(string);
		size += string.size;
		stringCount++;
	}
	
	public int serialize(int pointer, byte[] bytes) {
		
		pointer = writeBytes(pointer, bytes, CONTAINER_TYPE);
		pointer = writeBytes(pointer, bytes, size);
		pointer = writeBytes(pointer, bytes, nameLength);
		pointer = writeBytes(pointer, bytes, name.getBytes());
		
		pointer = writeBytes(pointer, bytes, fieldCount);
		
		for(int i = 0; i < fieldCount; i++) {
			JPField field = fields.get(i);
			pointer = field.serialize(pointer, bytes);
		}
		
		pointer = writeBytes(pointer, bytes, arrayCount);
		
		for(int i = 0; i < arrayCount; i++) {
			JPArray array = arrays.get(i);
			pointer = array.serialize(pointer, bytes);
		}
		
		pointer = writeBytes(pointer, bytes, stringCount);
		
		for(int i = 0; i < stringCount; i++) {
			JPString string = strings.get(i);
			pointer = string.serialize(pointer, bytes);
		}
		
		return pointer;
	}

	public static JPObject Deserialize(int pointer, byte[] bytes) {
		JPObject obj = new JPObject("");
		pointer += 1;
		obj.size = readInt(pointer, bytes);
		pointer += 4;
		obj.nameLength = readShort(pointer, bytes);
		pointer += 2;
		obj.name = readString(pointer, bytes, obj.nameLength);
		pointer += obj.nameLength;
		obj.fieldCount = readInt(pointer, bytes);
		pointer += 4;
		
		for(int i = 0; i < obj.fieldCount; i++) {
			JPField field = JPField.Deserialize(pointer, bytes);
			pointer += field.size;
			obj.fields.add(field);
		}
		
		obj.arrayCount = readInt(pointer, bytes);
		pointer += 4;
		
		for(int i = 0; i < obj.arrayCount; i++) {
			JPArray array = JPArray.Deserialize(pointer, bytes);
			pointer += array.size;
			obj.arrays.add(array);
		}
		
		obj.stringCount = readInt(pointer, bytes);
		pointer += 4;
		
		for(int i = 0; i < obj.stringCount; i++) {
			JPString string = JPString.Deserialize(pointer, bytes);
			pointer += string.size;
			obj.strings.add(string);
		}
		
		return obj;
	}
	
	
	
	
}
