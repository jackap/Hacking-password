package hack_prog;

import java.io.BufferedReader;
import java.io.IOException;
import java.util.LinkedList;
import java.util.List;

public class Entry {
String name;
String password;
String salt;
List<Entry> entryList = new LinkedList();
public Entry(String name, String password, String salt) {
	super();
	this.name = name;
	if (password != null)
	this.password = password.toUpperCase();
	this.salt = salt;
}
public String getName() {
	return name;
}
public String getPassword() {
	return password;
}
public String getSalt() {
	return salt;
};

@Override
public String toString() {
	return "Entry [name=" + name + ", password=" + password + ", salt=" + salt + "]";
}






	
}
