package prefixSum;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class bj_21758 {
	
	static int N;	// 장소 개수
	static int[] honey;	// 꿀 양
	static long[] ps;	// 누적합 배열
	static long max;	// 최대 꿀 양
	static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
	static StringTokenizer st = null;

	public static void main(String[] args) throws NumberFormatException, IOException {
		// 꿀 따기
		N = Integer.parseInt(br.readLine());
		honey = new int[N+1];
		
		st = new StringTokenizer(br.readLine(), " ");
		for(int h=1; h<=N; h++) {
			honey[h] = Integer.parseInt(st.nextToken());
		}
		
		ps = new long[N+1];
		ps[0] = 0;
		// 누적합 배열 구하기
		for(int s=1; s<=N; s++) {
			ps[s] = honey[s] + ps[s-1];
		}
		
		max = Long.MIN_VALUE;
		// 벌1,2가 양 끝에 고정되어 있을 때 - > 벌통의 위치만 변경됨
		for(int i=2; i<N; i++) {
			long sum = ps[i] - ps[1-1] - honey[1];	// ps[벌통] - ps[벌1-1] - honey[벌1];
			sum += ps[N] - ps[i-1] - honey[N];	// ps[벌2] - ps[벌통-1] - honey[벌2];
			max = Math.max(max, sum);
		}
		
		// 벌1,벌통이 양 끝에 고정되어 있을 때 -> 나머지 한마리 벌의 위치만 변경됨
		for(int i=2; i<N; i++) {
			long sum = ps[N] - ps[1-1] - honey[1];	// ps[벌통] - ps[벌1-1] - honey[벌1]; 
			sum += ps[N] - ps[i-1] - honey[i];		// ps[벌통] - ps[벌2-1] - honey[벌2];
			sum -= honey[i];			// 변경되는 벌의 장소값도 제외
			max = Math.max(max, sum);
		}
		
		// 벌통, 벌1이 양 끝에 고정되어 있을 때 -> 나머지 한마리 벌의 위치만 변경됨
		for(int i=2; i<N; i++) {
			long sum = ps[N] - ps[1-1] - honey[N];	// ps[벌1] - ps[벌통-1] - honey[벌1]
			sum += ps[i] - ps[1-1] - honey[i];		// ps[벌2] - ps[벌통-1] - honey[벌2]
			sum -= honey[i];			// 변경되는 벌의 장소값도 제외
			max = Math.max(max, sum);
		}
		
		System.out.println(max);
	}
}