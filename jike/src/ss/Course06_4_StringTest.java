package ss;

import org.junit.Test;

public class Course06_4_StringTest {
	@Test
	public void test(){
		String s="ffabcdabceeabcdabaggabcdabahh";
		String p="abcdaba";
		System.out.println(s.indexOf(p));
		s="aabbccagdbbccdec";
		System.out.println(s.indexOf(p));
	}
}
