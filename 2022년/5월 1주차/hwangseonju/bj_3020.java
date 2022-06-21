package PrefixSum;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.PriorityQueue;
import java.util.StringTokenizer;

public class bj_3020 {

	static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
	static StringTokenizer st = null;
	static int N;	// 길이
	static int H;	// 높이
	
	public static void main(String[] args) throws IOException {
		// 개똥벌레
		st = new StringTokenizer(br.readLine());
		N = Integer.parseInt(st.nextToken());
		H = Integer.parseInt(st.nextToken());
		
		int[] bottom = new int[H+1];
		int[] top = new int[H+1];
		for(int n=0; n<N; n++) {
			int height = Integer.parseInt(br.readLine());
			if(n%2==0) {	// 석순일때(bottom)
				bottom[height]++;
			}else {
				top[height]++;
			}
		}
		
		for(int h=H-1; h>0; h--) {
			bottom[h] += bottom[h+1];
			top[h] += top[h+1];
		}
		
		PriorityQueue<Integer> pq = new PriorityQueue<>();
		for(int h=1; h<=H; h++) {
			pq.add(bottom[h] + top[H-h+1]);
		}
		
		int min = pq.poll();
		int cnt = 1;
		while(pq.peek()<=min) {
			pq.poll();
			cnt++;
		}
		
		System.out.println(min +" "+ cnt);
	}

}
