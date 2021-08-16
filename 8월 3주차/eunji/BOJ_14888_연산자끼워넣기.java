package 순열_조합;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

/*
 * @see https://www.acmicpc.net/problem/14888
 * 순열
 */
public class BOJ_14888_연산자끼워넣기 {
	static StringTokenizer st;
	static char[] con= {'+','-','*','/'};
	static int N;
	static int[] input;
	static char[] control;
	static char[] pick;
	static int min=Integer.MAX_VALUE;
	static int max=Integer.MIN_VALUE;

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		N = Integer.parseInt(br.readLine());
		input=new int[N];
		st = new StringTokenizer(br.readLine());
		for (int n=0; n<N; n++) {
			input[n]=Integer.parseInt(st.nextToken());
		}
		control=new char[N-1];
		st = new StringTokenizer(br.readLine());
		int cnt=0;
		for (int n=0; n<4; n++) { 
			// 연산자 입력받기
			int num=Integer.parseInt(st.nextToken());
			for(int i=0; i<num; i++) {
				control[cnt]=con[n];
				cnt++;
			}
		}
		pick=new char[N-1];
		permutation(0,0);
		System.out.println(max);
		System.out.println(min);
	}

	private static void permutation(int cnt,int flag) {
		if(cnt==N-1) {
			int sum=input[0];
			for(int i=0; i<N-1; i++) {
				if(pick[i]=='+') sum+=input[i+1];
				else if(pick[i]=='-') sum-=input[i+1];
				else if(pick[i]=='*') sum*=input[i+1];
				else if(pick[i]=='/') {
					if (sum<0) sum=-(-sum/input[i+1]);
					else sum/=input[i+1];
				}	
			}
			min=min>sum?sum:min;
			max=max<sum?sum:max;
			return;
		}
		for(int i=0; i<N-1; i++) {
			if((flag&1<<i)!=0) continue;
			pick[cnt]=control[i];
			permutation(cnt+1, flag|1<<i);
		}
	}
	

}
