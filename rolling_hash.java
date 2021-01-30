import java.util.*;

public class rolling_hash {
	public static long mod = 1000000007;
	public static long prime = 31; 
	
	public static int rolling_hash(String str) {
		long res = 0;
		long power = 1;
		for(int i=0 ; i<str.length() ; i++) {
			char ch = str.charAt(i);
			res = (res + ((ch - 'a' + 1)*power)) % mod;
			power = (power*prime) % mod;
		}
		return (int)res;
	}
	public static void main(String[] args) {
		Scanner s = new Scanner(System.in);
		String str = s.nextLine();
		s.close();
		int hash_val = rolling_hash(str);
		System.out.println(hash_val);
	}
}
