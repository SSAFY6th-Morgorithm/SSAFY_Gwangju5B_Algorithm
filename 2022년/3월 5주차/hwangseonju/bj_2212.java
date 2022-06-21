package algo;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.StringTokenizer;

public class bj_2212 {

	static int N;	// 센서
	static int K;	// 집중국
	static int[] load;	// 선서 위치 차이
	static int[] num;
	static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
	static StringTokenizer st = null;
	
	public static void main(String[] args) throws NumberFormatException, IOException {
		// 센서
		N = Integer.parseInt(br.readLine());
		K = Integer.parseInt(br.readLine());
		load = new int[N];
		
		st = new StringTokenizer(br.readLine());
		for(int n=0; n<N; n++) {
			load[n] = Integer.parseInt(st.nextToken());
		}
		
		Arrays.sort(load);
		
		num = new int[N-1];
		for(int n=1; n<N; n++) {
			num[n-1] = load[n]-load[n-1];
		}
		
		Arrays.sort(num);
		
		int min = 0;
		for(int n=0; n<=N-1-K; n++) {
			min += num[n];
		}
		
		System.out.println(min);
	}
}
