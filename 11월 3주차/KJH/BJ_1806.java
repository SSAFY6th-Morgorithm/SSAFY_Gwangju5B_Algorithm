package BJ;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class BJ_1806 {

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		int N = Integer.parseInt(st.nextToken());
		int S = Integer.parseInt(st.nextToken());
		int[] arr = new int[N+1];
		int[] d = new int[N+1];
		st = new StringTokenizer(br.readLine());
		for(int i=1;i<=N;i++) {
			arr[i] = Integer.parseInt(st.nextToken());
			d[i] = d[i-1]+arr[i];
		}
		
		int start = 1;
		int end = 1;
		int len = Integer.MAX_VALUE;
		while(true) {
			if(d[N]<S)
				break;
			
			if(d[end]-d[start-1]<S)
				end++;
			else {
				len = Math.min(len, end-start+1);
				start++;
			}
			
			if(end==N && d[end]-d[start-1]<S)
				break;
		}
		
		StringBuilder sb = new StringBuilder();
		if(len==Integer.MAX_VALUE)
			sb.append(0);
		else
			sb.append(len);
		System.out.println(sb);
	}

}
