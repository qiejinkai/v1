package myweb;

import java.io.InputStream;

public class test {

	public static void main(String[] args) {
		ClassLoader cl =test.class.getClassLoader();
		InputStream is =cl.getResourceAsStream("e://123.txt");
		System.out.println(is);
	}
}
