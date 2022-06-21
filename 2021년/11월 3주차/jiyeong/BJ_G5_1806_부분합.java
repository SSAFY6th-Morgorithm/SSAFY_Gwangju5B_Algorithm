package twopointer;

import java.io.*;
import java.util.*;

public class BJ_G5_1806_부분합 {
	
	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringBuilder sb = new StringBuilder();
		StringTokenizer st = new StringTokenizer(br.readLine());
		
		int N = Integer.parseInt(st.nextToken());
		int S = Integer.parseInt(st.nextToken());
		int[] arr = new int[N];
		
		st = new StringTokenizer(br.readLine());
		for (int i = 0; i < N; i++) {
			arr[i] = Integer.parseInt(st.nextToken());
			if(arr[i] >= S) {
				System.out.println(1);
				return;
			}
		}
		
		int s = 0;
		int e = 1;
		int min = Integer.MAX_VALUE;
		int sum = arr[s]+arr[e];
		while (true) {
			if(s == N-1) break; 
			if(sum >= S) {
				min = min < e-s+1 ? min:e-s+1;  
				sum -= arr[s++]; // 원래 것을 빼고 그다음으로 넘겨줘야하므로 s++
				if(min == 2) { // 2보다 작아질 수 없음
					System.out.println(2);
					return;
				}
			}else {
				if(e == N-1) {
					break; // e가 마지막인데 sum이 S보다 작으면 더이상 계산할 필요 없음
				}else {
					sum += arr[++e]; //e가 1부터 시작이므로 ++e로 sum계산
				}
			}
			
		}
		
		if(min == Integer.MAX_VALUE) {
			System.out.println(0);
		}else {
			System.out.println(min);
		}

	}
}
