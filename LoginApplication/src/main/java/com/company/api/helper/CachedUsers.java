package com.company.api.helper;

import java.util.HashMap;
import java.util.Map;

public class CachedUsers {
	
	private static final Map<String, String[]> users = new HashMap<String, String[]>();
	static {
		users.put("hvj2005@gmail.com", new String [] {"test", "Harshvardhan Jain"});
		users.put("john.doe@gmail.com", new String [] {"test", "John Doe"});
		users.put("zackryder@gmail.com", new String [] {"test", "Zack Ryder"});
	}
	public static Map<String, String[]> getUsers() {
		return users;
	}
	

}
