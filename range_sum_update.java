
import java.util.*;

public class range_sum_update {
	static int n;
	static int[] st;
	static int[] arr;
	
	static void createSegmentTree(int s, int e, int pos) {
		if(s==e) {
			st[pos] = arr[s];
			return ;
		}
		int mid = s + ((e-s)>>1);
		createSegmentTree(s, mid, (2*pos)+1);
		createSegmentTree(mid+1, e, (2*pos)+2);
		st[pos] = st[(2*pos)+1] + st[(2*pos)+2];
	}
	
	static int rangeSum(int l, int r, int s, int e, int pos) {
		if(l<=s && r>=e) return st[pos];
		if(r<s || l>e) return 0;
		int mid = s + ((e-s)>>1);
		return rangeSum(l,r,s,mid,(2*pos)+1) + rangeSum(l,r,mid+1,e,(2*pos)+2);
	}
	
	
	static void update(int i, int diff, int s, int e, int pos) {
		if(i<s || i>e) return ;
		st[pos] += diff;
		if(s==e) return;
		int mid = s + ((e-s)>>1);
		update(i, diff, s, mid, (2*pos)+1);
		update(i,diff, mid+1, e, (2*pos)+2);
	}
	public static void main(String[] args) {
		Scanner s = new Scanner(System.in);
		
		// n : size of array
		// arr : input in the array 
		
		n = s.nextInt();
		arr = new int[n];
		for(int i=0 ; i<n ; i++) arr[i] = s.nextInt();
		
		// Creation of Segment Tree  
		st = new int[4*n];
		createSegmentTree(0,n-1,0);
		
		
		// 1 : finding the range sum 
		// 2 : updating any value in that in that index
		
		System.out.println("Range sum : 1      Update : 2      Exit : 3");
		boolean temp = true;
		while(temp) {
			int c = s.nextInt();
			switch(c) {
				case 1 : 
					int l = s.nextInt(), r = s.nextInt();
					int ans = rangeSum(l,r,0, n-1, 0);
					System.out.println(ans);
					break;
				case 2 :
					int i = s.nextInt(), val = s.nextInt();
					int diff = val - arr[i];
					arr[i] = val;
					update(i, diff, 0, n-1, 0);
					break;
				default :
					temp = false;
					break;
			}
		}
		s.close();
	}

}
