package 순열_조합;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

/*
 * @see https://www.acmicpc.net/problem/6603
 * 조합
 */
public class BOJ_6603_로또 {
	static StringTokenizer st;
	static StringBuilder sb=new StringBuilder();
	static int k;
	static int[] input;
	static int[] pick;
	public static void main(String[] args) throws IOException {
		BufferedReader br=new BufferedReader(new InputStreamReader(System.in));
		k=1;
		while(k!=0) {
			st=new StringTokenizer(br.readLine());
			k=Integer.parseInt(st.nextToken());
			input=new int[k];
			pick=new int[6];
			for (int i=0; i<k; i++) {
				input[i]=Integer.parseInt(st.nextToken());
			}
			combination(0,0);
			sb.append("\n");
		}
		System.out.println(sb);
	}
	private static void combination(int cnt, int start) {
		if (cnt==6) {
			for (int x:pick) {
				sb.append(x).append(" ");
			}
			sb.append("\n");
			return;
		}
		for (int i=start; i<k; i++) {
			pick[cnt]=input[i];
			combination(cnt+1, i+1);
		}
		
	}

}
