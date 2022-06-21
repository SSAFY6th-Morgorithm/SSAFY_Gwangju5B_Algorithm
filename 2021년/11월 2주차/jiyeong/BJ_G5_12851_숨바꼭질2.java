import java.io.*;
import java.util.*;

public class BJ_G5_12851_숨바꼭질2 {
	
	static int K,N;
	static int arr[] = new int[100001];
	static int cnt = 0;
	static int min = Integer.MAX_VALUE;
	
	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		
		N = Integer.parseInt(st.nextToken()); // 수빈 
		K = Integer.parseInt(st.nextToken()); // 동생
		
		if (N >= K) {
            System.out.println(N-K);
            System.out.println(1);
            return;
        }
		
		bfs();
		
		System.out.println(min);
		System.out.println(cnt);
	}
	
	private static void bfs() {
		Queue<Integer> q = new LinkedList<>();
		
		q.add(N);
		arr[N] = 1;
		
		while(!q.isEmpty()) {
			int cur = q.poll();
			
			if(min < arr[cur]) 
				return;
			
			int next;
			for (int i = 0; i < 3; i++) {
				
				if(i == 0) {
					next = cur - 1;
				}else if(i == 1) {
					next = cur + 1;
				}else {
					next = cur * 2;
				}
				
				if (next < 0 || next > 100000) continue;
				
				
				if(next == K) {
					min = arr[cur];
					cnt++;
				}
				
				if(arr[next]==0 || arr[next] == arr[cur]+1) {
					q.add(next);
					arr[next] = arr[cur]+1;
				}
			}
		}
		
		
	}
}
