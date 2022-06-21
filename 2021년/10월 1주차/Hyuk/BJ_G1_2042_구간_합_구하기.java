package BJ_Practice.Gold;

import java.io.*;
import java.util.*;

public class BJ_G1_2042_구간_합_구하기 {
	static int N,M,K;
	static long[] data;
	static StringTokenizer st;
	static Map<Integer,Long> map = new HashMap<>();

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringBuilder sb = new StringBuilder();
		
		st = new StringTokenizer(br.readLine());
		N = Integer.parseInt(st.nextToken());
		M = Integer.parseInt(st.nextToken());
		K = Integer.parseInt(st.nextToken());
		
		data = new long[N+1];
		for (int i = 1; i <= N ; i++) {
			data[i] = Integer.parseInt(br.readLine()) + data[i-1];
		}
		long answer = 0 ;
		for (int i = 0; i < M+K ; i++) {
			st = new StringTokenizer(br.readLine());
			int a = Integer.parseInt(st.nextToken());
			int b = Integer.parseInt(st.nextToken());
			long c = Long.parseLong(st.nextToken());
			if(a==1) {
				map.put(b, c);
			}else {
				answer = data[(int)c] - data[b-1];
				for (int num : map.keySet()) {
					if(b<=num && num <= c) {
						answer += map.get(num) - data[num]+data[num-1];
					}
				}
				sb.append(answer).append("\n");
			}
			
		}
		System.out.println(sb);
	}
}
