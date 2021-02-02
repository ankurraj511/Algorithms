import java.util.*;
public class rabin_karp_string_matching_algorithms {
	public static long[] dp = new long[1000001];
	public static long[] power = new long[1000001];
	public static long mod = 1000000007;
	public static long prime = 31;
	public static void init(String str) {
		dp[0] = str.charAt(0) - 'a' + 1;
		power[0] = 1;
		for(int i=1 ; i<str.length() ; i++) {
			char ch = str.charAt(i);
			power[i] = (power[i-1] * prime) % mod; 
			dp[i] = (dp[i-1] + (power[i] * (ch - 'a' + 1))) % mod; 
		}
	}
	public static long rolling_hash(String str) {
		long res = 0;
		long power = 1;
		for(int i=0 ; i<str.length() ; i++) {
			char ch = str.charAt(i);
			res = (res + (power * (ch - 'a' + 1))) % mod;
			power = (power * prime) % mod;
		}
		return res;
	}
	public static void main(String[] args) {
		Scanner s =new Scanner(System.in);
		String str = s.nextLine();
		init(str);
		String pattern = s.nextLine();
		int count = 0;
		long rh = rolling_hash(pattern);
		for(int i = 0 ; i<=str.length()-pattern.length() ; i++) {
			if(i==0 && rh == dp[pattern.length()-1]) count+=1;
			if(i!=0) {
				long diff = (dp[i+pattern.length()-1]-dp[i-1]+mod)%mod;
				long val = (rh*power[i]) % mod;
				if(val==diff) count+=1;
			}
		}
		System.out.println(count);
		s.close();
	}

}
