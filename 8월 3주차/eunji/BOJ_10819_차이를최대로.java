package 순열_조합;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

/*
 * @see https://www.acmicpc.net/problem/10819
 * 순열 이용, 비트마스킹
 */
public class BOJ_10819_차이를최대로 {
	static StringTokenizer st;
	static int[] input;
	static int[] pick;
	static int N,R;
	static int max=0;
	public static void main(String[] args) throws IOException {
		BufferedReader br=new BufferedReader(new InputStreamReader(System.in));
		N=Integer.parseInt(br.readLine());
		R=N;
		input=new int[N];
		pick=new int[R];
		st=new StringTokenizer(br.readLine());
		for(int n=0; n<N; n++) {
			input[n]=Integer.parseInt(st.nextToken());
		} //입력값 받기
		permutation(0,0); //순열,vistied대신 비트마스킹 이용
		System.out.println(max);
	}
	private static void permutation(int cnt,int flag) {
		if (cnt==R) {
			int sum=0;
			for(int i=0; i<R-1; i++) {
				sum+=Math.abs(pick[i]-pick[i+1]);
			}
			max=max<sum?sum:max;
			return;
		}
		for (int i=0; i<N; i++) {
			if((flag&1<<i)!=0) continue;
			pick[cnt]=input[i];
			permutation(cnt+1,(flag|1<<i));
		}
	}
}
