package twopointer;
import java.io.*;
import java.util.*;

public class BJ_G5_2470_두용액 {
	
	static int[] arr;
	
	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringBuilder sb = new StringBuilder();
		StringTokenizer st;
		
		int N = Integer.parseInt(br.readLine());
		arr = new int[N];
		st = new StringTokenizer(br.readLine());
		for (int i = 0; i < N; i++) {
			arr[i] = Integer.parseInt(st.nextToken());
		}
		
		Arrays.sort(arr);

		int s = 0;
		int e = N-1;
		int min = Integer.MAX_VALUE;
		int sum = 0; 
		int[] res = new int[2];
		
		while(true) {
			if(s >= e) break;
			
			sum = arr[s]+arr[e];
			if(Math.abs(sum) < min) {
				min = Math.abs(sum);
				res[0]=arr[s];
				res[1]=arr[e];
			}
			
			if(sum < 0) {
				s++;
			}else {
				e--;
			}
		}
		
		System.out.println(res[0]+" "+res[1]);

	}
	
}
