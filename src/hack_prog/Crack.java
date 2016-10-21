package hack_prog;

import java.io.UnsupportedEncodingException;
import java.nio.charset.StandardCharsets;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;

import javax.xml.bind.DatatypeConverter;

public class Crack {

String keyword = "potplantpwdb";

public boolean testPassword(String password,Entry entry) throws NoSuchAlgorithmException, UnsupportedEncodingException{
	String testmessage = keyword+password+entry.getSalt();
	
	MessageDigest digest = MessageDigest.getInstance("SHA-256");
	byte[] hash = digest.digest(testmessage.getBytes(StandardCharsets.UTF_8));
	String hexval = DatatypeConverter.printHexBinary(hash);
	//System.out.println("Message : "+ hexval);
   hexval =  hexval.substring(0, 32);
	
	if (entry.getPassword().equals(hexval)) return true;
	
	
	return false;
}

	
	
}
