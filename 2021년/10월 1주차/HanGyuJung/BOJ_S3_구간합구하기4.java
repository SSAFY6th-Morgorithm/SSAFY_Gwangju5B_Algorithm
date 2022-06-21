package week_10_1;


import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.StringTokenizer;

//시간제한: 초
//그냥 무지성돌리면 시간초과 될듯 N^2 이라
//구간합에서 - 부분합 느낌

public class BOJ_S3_구간합구하기4 {
	public static void main(String[] args) throws IOException {
//		System.setIn(new FileInputStream("input.txt"));
		BufferedReader br =new BufferedReader(new InputStreamReader(System.in));
		StringBuilder sb = new StringBuilder();
		
		StringTokenizer st = new StringTokenizer(br.readLine());
		int N = Integer.parseInt(st.nextToken());
		int M = Integer.parseInt(st.nextToken());
		int[] sum = new int[N+1];
		st=  new StringTokenizer(br.readLine());
		for(int i=1;i<N+1;i++) {
			sum[i]=sum[i-1]+Integer.parseInt(st.nextToken()); 
		}
		//System.out.println(Arrays.toString(sum));
		
		for(int m=0;m<M;m++) {
			st =new StringTokenizer(br.readLine());
			int i = Integer.parseInt(st.nextToken());
			int j = Integer.parseInt(st.nextToken());
			System.out.println(sum[j]-sum[i-1]);
		}
		
		
	}
}
