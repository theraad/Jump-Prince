package Jumprince.serialization;

import static Jumprince.serialization.SerializationWriter.*;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.nio.file.Files;
import java.util.ArrayList;
import java.util.List;

public class JPDatabase extends Base{

	private byte[] bytes;
	
	public JPDatabase(String name) {
		objects = new ArrayList<JPObject>();
		size += HEADER.length + 1 + 4 + FOOTER.length;
		setName(name);
	}
	
	private static final byte[] HEADER = "JPDS".getBytes();
	private static final byte[] FOOTER = "JPDE".getBytes();
	private static final byte CONTAINER_TYPE = ContainerType.DATABASE;
	public List<JPObject> objects;
	public int objectCount;
	
	public void add(JPObject obj) {
		objects.add(obj);
		size += obj.size;
		objectCount++;
	}
	
	public JPObject findObject(String objName) {
		for(int i = 0; i < objects.size(); i++) 
			if(objects.get(i).name.equals(objName))
				return objects.get(i);
		return null;
	}
	
	public byte[] serialize() {
		bytes = new byte[size];
		int pointer = 0;
		pointer = writeBytes(pointer, bytes, HEADER);
		pointer = writeBytes(pointer, bytes, CONTAINER_TYPE);
		pointer = writeBytes(pointer, bytes, size);
		pointer = writeBytes(pointer, bytes, nameLength);
		pointer = writeBytes(pointer, bytes, name.getBytes());
		
		pointer = writeBytes(pointer, bytes, objectCount);
		
		for(int i = 0; i < objectCount; i++) {
			JPObject obj = objects.get(i);
			pointer = obj.serialize(pointer, bytes);
		}
		
		pointer = writeBytes(pointer, bytes, FOOTER);
		
		return bytes;
	}
	
	public boolean serializeToFile(String path) {
		byte[] bytes = serialize();
		
		try {
			FileOutputStream fos = new FileOutputStream(new File(path));
			fos.write(bytes);
			fos.close();
		} catch (IOException e) {
			return false;
		}
		return true;
	}
	
	public static JPDatabase Deserialize(String path) {
		byte[] bytes;
		JPDatabase db = new JPDatabase("");
		try {
			bytes = Files.readAllBytes(new File(path).toPath());
		} catch (IOException e) {
			return null;
		}
		int pointer = HEADER.length + 1;
		db.size = readInt(pointer, bytes);
		pointer += 4;
		db.nameLength = readShort(pointer, bytes);
		pointer += 2;
		db.name = readString(pointer, bytes, db.nameLength);
		pointer += db.nameLength;
		db.objectCount = readInt(pointer, bytes);
		pointer += 4;
		for(int i = 0; i < db.objectCount; i++) {
			JPObject obj = JPObject.Deserialize(pointer, bytes);
			pointer += obj.size;
			db.objects.add(obj);
		}
		
		return db;
	}
}
