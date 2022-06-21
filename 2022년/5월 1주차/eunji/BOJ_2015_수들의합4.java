package algo;

import java.io.*;
import java.util.*;

public class BOJ_2015_수들의합4 {
	
	static StringTokenizer st;

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		st=new StringTokenizer(br.readLine());
		int N = Integer.parseInt(st.nextToken());
		long K = Long.parseLong(st.nextToken());
		int[] input=new int[N+1];
		long[] sum=new long[N+1];
		Map<Long,Long> map=new HashMap<Long, Long>();
		long result=0;
		st=new StringTokenizer(br.readLine());
		for (int i = 1; i < N+1; i++) {
			input[i]=Integer.parseInt(st.nextToken());
			sum[i]=sum[i-1]+input[i];
			if(sum[i]==K) {
				result++;
			}
			// 누적합 나온게 있으면 더하기
			if(map.containsKey(sum[i]-K)) {
				result+=map.get(sum[i]-K);
			}
			if(map.containsKey(sum[i])){
				map.put(sum[i], map.get(sum[i])+1L);
			}else {
				map.put(sum[i], 1L);
			}
		}
		System.out.println(result);
	}
}
