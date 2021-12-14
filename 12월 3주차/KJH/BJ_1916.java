package BJ;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.StringTokenizer;

public class BJ_1916 {

	public static void main(String[] args) throws NumberFormatException, IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st;
		int N = Integer.parseInt(br.readLine());
		int M = Integer.parseInt(br.readLine());
		int[][] arr = new int[N+1][N+1];
		for(int i=0;i<=N;i++) {
			Arrays.fill(arr[i], -1);
		}
		int INF = Integer.MAX_VALUE;
		for(int i=0;i<M;i++) {
			st = new StringTokenizer(br.readLine());
			int s = Integer.parseInt(st.nextToken());
			int e = Integer.parseInt(st.nextToken());
			int n = Integer.parseInt(st.nextToken());
			if(arr[s][e]!=-1) {
				arr[s][e] = Math.min(arr[s][e], n);
			}
			else {
				arr[s][e] = n;
			}
		}
		st = new StringTokenizer(br.readLine());
		int start = Integer.parseInt(st.nextToken());
		int end = Integer.parseInt(st.nextToken());
		
		int[] D = new int[N+1];
		Arrays.fill(D, INF);
		boolean[] visited = new boolean[N+1];
		D[start] = 0;
		
		for(int i=0;i<N;i++) {
			int min = INF, cur = 0;
			for(int j=1;j<=N;j++) {
				if(!visited[j] && D[j]<min) {
					min = D[j];
					cur = j;
				}
			}
			visited[cur] = true;
			if(cur==end)
				break;
			for(int j=1;j<=N;j++) {
				if(!visited[j] && arr[cur][j]!=-1 && D[j]>min+arr[cur][j]) {
					D[j] = min+arr[cur][j];
				}
			}
		}
		StringBuilder sb = new StringBuilder();
		sb.append(D[end]);
		System.out.println(sb);

	}

}
