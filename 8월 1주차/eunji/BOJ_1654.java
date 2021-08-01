import java.util.Scanner;

public class BOJ_1654 {
	/*
	 * 주의!
	 * int -> long으로 바꾸기 
	 */
	public static int N;
	public static void main(String[] args) {
		// TODO Auto-generated method stub
		Scanner scan=new Scanner(System.in);
		int K=scan.nextInt();
		N=scan.nextInt();
		long[] list=new long[K];
		long max=0;
		for (int i=0; i<K; i++) {
			long num=scan.nextLong();
			list[i]=num;
			if(max<num) {
				max=num;
			}
		}
		System.out.println(bs(list, 1, max,1));
		
	}
	
	public static long bs(long[] list, long start, long end,long result) {
		if (start>end) {
			return result;
		}
		long mid=(start+end)/2;
		long sum=0;
		for(int i=0; i<list.length; i++) {
			if (list[i]>=mid) {
				sum+=(list[i]/mid);
			}
		}
		if(sum<N) {
			end=mid-1;
		}else {
			start=mid+1;
			result=mid;
		}
		
		return bs(list,start,end,result);
		
	}
}
