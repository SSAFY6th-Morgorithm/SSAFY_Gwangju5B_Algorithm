package BJ;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class BJ_S5_10867 {

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringBuilder sb = new StringBuilder();
		
		int N = Integer.parseInt(br.readLine());
		int[] arr = new int[2001];
		
		StringTokenizer st = new StringTokenizer(br.readLine()," ");
		
		for (int i = 0; i < N; i++) {			
			int val = Integer.parseInt(st.nextToken());
			arr[val+1000]++;
		}
				
		for (int i = 0; i < arr.length; i++) {
			if(arr[i] > 0)
				sb.append(i-1000).append(" ");
		}
		System.out.println(sb);
		
		br.close();

	}

}
