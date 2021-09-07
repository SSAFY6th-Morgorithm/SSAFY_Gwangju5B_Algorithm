package BJ;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.LinkedList;
import java.util.Queue;
import java.util.StringTokenizer;

public class BJ_5014 {
	static int F,S,G,U,D,res;
	static int[] visited;
	static boolean c = false;
	static Queue<Integer> q = new LinkedList<Integer>();
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		F = Integer.parseInt(st.nextToken());
		S = Integer.parseInt(st.nextToken());
		G = Integer.parseInt(st.nextToken());
		U = Integer.parseInt(st.nextToken());
		D = Integer.parseInt(st.nextToken());
		visited = new int[F+1];
		solve(S,0);
		StringBuilder sb = new StringBuilder();
		if(c)
			sb.append(res);
		else
			sb.append("use the stairs");
		System.out.println(sb);
	}

	static void solve(int n, int cnt) {
		visited[n] = 1;
		q.add(n);
		while(!q.isEmpty()) {
			int r = q.remove();
			
			if(r==G) {
				c = true;
				res = visited[r]-1;
				break;
			}
			
			if(r+U<=F && visited[r+U]==0) {
				visited[r+U] = visited[r]+1;
				q.add(r+U);
			}
			
			if(r-D>0 && visited[r-D]==0) {
				visited[r-D] = visited[r]+1;
				q.add(r-D);
			}
		}
	}
}
