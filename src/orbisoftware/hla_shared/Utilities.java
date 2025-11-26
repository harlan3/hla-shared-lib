/**
 * Copyright (C) 2025, Harlan Murphy
 * Use of this source code is governed by a BSD Zero Clause License
 * 
 * See project license.txt for details
*/

package orbisoftware.hla_shared;

import java.io.File;
import java.nio.ByteBuffer;
import java.nio.charset.StandardCharsets;
import java.util.Random;

public class Utilities {

	public static String encodingPackageRoot = "orbisoftware.hla_1516e_encoding.";
	public static String encodingPackageRootDir = "orbisoftware" + File.separator + "hla_1516e_encoding";
	
	public static String containerPackageRoot = "orbisoftware.hla_1516e_containers.";
	public static String containerPackageRootDir = "orbisoftware" + File.separator + "hla_1516e_containers";
	
	public static String sharedPackageRoot = "orbisoftware.hla_shared.*;";

	public byte[] generateRandomBytes(int length) {

		byte[] bytes = new byte[length];
		new Random().nextBytes(bytes);
		return bytes;
	}
	
    public String generateRandomAlphaNumeric(int length) {
       
       String characters = "ABCDEFGHIJKLMNOPQRSTUVWXYZabcdefghijklmnopqrstuvwxyz0123456789";
       StringBuilder sb = new StringBuilder();
       Random random = new Random();
    
       for (int i = 0; i < length; i++) {
          int index = random.nextInt(characters.length());
          sb.append(characters.charAt(index));
       }
       return sb.toString();
    }
   
	public byte[] getBytesFromBoolean(boolean value) {

		if (value)
			return new byte[] { (byte) 1 };
		else
			return new byte[] { (byte) 0 };
	}

	public byte[] getBytesFromByte(byte value) {

		byte bytes[] = new byte[1];
		bytes[0] = value;

		return bytes;
	}

	public byte[] getBytesFromShort(short value) {

		return ByteBuffer.allocate(2).putShort(value).array();
	}

	public byte[] getBytesFromInteger(int value) {

		return ByteBuffer.allocate(4).putInt(value).array();
	}

	public byte[] getBytesFromLong(long value) {

		return ByteBuffer.allocate(8).putLong(value).array();
	}

	public byte[] getBytesFromFloat(float value) {

		return ByteBuffer.allocate(4).putFloat(value).array();
	}

	public byte[] getBytesFromDouble(double value) {

		return ByteBuffer.allocate(8).putDouble(value).array();
	}

///////////////////////////////////////////////////////////////////

	public boolean getBooleanFromBytes(byte[] bytes) {

		if (bytes[0] == 1)
			return true;
		else
			return false;
	}

	public byte getByteFromBytes(byte[] bytes) {

		return ByteBuffer.wrap(bytes).get(0);
	}

	public short getShortFromBytes(byte[] bytes) {

		return ByteBuffer.wrap(bytes).getShort();
	}

	public int getIntegerFromBytes(byte[] bytes) {

		return ByteBuffer.wrap(bytes).getInt();
	}

	public long getLongFromBytes(byte[] bytes) {

		return ByteBuffer.wrap(bytes).getLong();
	}

	public float getFloatFromBytes(byte[] bytes) {

		return ByteBuffer.wrap(bytes).getFloat();
	}

	public double getDoubleFromBytes(byte[] bytes) {

		return ByteBuffer.wrap(bytes).getDouble();
	}

	public String removeLastLetter(String text) {

		String substring = text.substring(0, text.length() - 1);
		return substring;
	}

	public String bytesToString(byte[] bytes) {

		String javaString = new String(bytes, StandardCharsets.UTF_8);

		int lastChar = (int) javaString.charAt(javaString.length() - 1);

		if (lastChar == 0)
			return removeLastLetter(javaString);
		else
			return javaString;
	}

	public String primitiveAssignment(String primitiveType) {

		String returnVal = "";

		switch (primitiveType) {

		case "boolean":
			returnVal = "false";
			break;

		case "byte":
		case "short":
		case "int":
		case "long":
			returnVal = "0";
			break;

		case "float":
			returnVal = "0.0f";
			break;

		case "double":
			returnVal = "0.0";
			break;
		}

		return returnVal;
	}

	// Method to align the offset to the nearest multiple of alignment
	public int align(int offset, int alignment) {

		int returnVal = (offset + alignment - 1) & ~(alignment - 1);
		return returnVal;
	}

	// Method to insert padding into the buffer
	public void insertPadding(DynamicBuffer buffer, int offset, int alignment) {
		int alignedOffset = align(offset, alignment);
		int paddingSize = alignedOffset - offset;

		for (int i = 0; i < paddingSize; i++) {
			buffer.put((byte) 0x00); // Insert padding bytes (0x00)
		}
	}
	
	// Method returning substitute implementation
	public String substituteImplementations(String nativeClass) {
		
		String returnVal = nativeClass;
		
		switch(nativeClass) {
		
		case "HLAunicodeString":
			returnVal = "HLAunicodeStringImp";
			break;
			
		case "HLAopaqueData":
			returnVal = "HLAopaqueDataImp";
			break;
			
		case "HLAASCIIstring":
			returnVal = "HLAASCIIstringImp";
			break;
		}
		
		return returnVal;
	}
}
