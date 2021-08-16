package BJ;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class BJ_17204 {
	static int N,K,cur,res;
	static int[] arrow;
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		N = Integer.parseInt(st.nextToken());
		K = Integer.parseInt(st.nextToken());
		arrow = new int[N];
		for(int i=0;i<N;i++) {
			arrow[i] = Integer.parseInt(br.readLine());
		}
		cur = 0;
		res = 0;
		
		for(int i=0;i<N;i++) {
			if(cur==K)
				break;
			cur = arrow[cur];
			res++;
		}
		if(cur!=K)
			res = -1;
		StringBuilder sb = new StringBuilder();
		sb.append(res);
		System.out.println(sb);
	}

}
