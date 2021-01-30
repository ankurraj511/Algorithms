import java.util.*;
public class polynomial_rolling_hash {
	public static long[] dp = new long[1000001];
	public static long[] inv = new long[1000001];
	public static int mod = 1000000007;
	public static int prime = 31;
	public static long pow(long p, int n) {
		if(n==0) return 1;
		if(n==1) return p;
		long temp = pow(p,n/2);
		if(n%2==0) return (temp * temp) % mod;
		return (((temp * temp) % mod) * p) % mod; 
	}
	public static void init(String str) {
		long power = 1;
		dp[0] = (str.charAt(0) - 'a' + 1);
		inv[0] = 1;
		for(int i=1 ; i<str.length() ; i++) {
			char ch = str.charAt(i);
			power = (power * prime) % mod;
			inv[i] = pow(power, mod - 2);
			dp[i] = (dp[i-1] + ((ch - 'a' + 1)*power)) % mod;
		}
	}
	public static int subString_hash(String str, int l, int r) {
		long result = dp[r];
		if(l!=0) result = result - dp[l-1];
		result = (result * inv[l]) % mod;
		return (int)result;
	}
	public static void main(String[] args) {
		Scanner s = new Scanner(System.in);
		String str = s.nextLine();
		init(str);
		int t = s.nextInt();
		while(t-->0) {
			int l = s.nextInt() , r = s.nextInt();
			int hash = subString_hash(str,l,r);
			System.out.println(hash);
		}
		s.close();

	}

}
