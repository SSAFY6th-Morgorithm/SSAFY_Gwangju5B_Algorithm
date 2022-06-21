package BJ;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class BJ_2668 {
	static int N;
	static int[] arr;
	static boolean[] visited;
	static List<Integer> li;
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		N = Integer.parseInt(br.readLine());
		arr = new int[N+1];
		visited = new boolean[N+1];
		li = new ArrayList<Integer>();
		for(int i=1;i<=N;i++) {
			arr[i] = Integer.parseInt(br.readLine());
		}
		
		for(int i=1;i<=N;i++) {
			visited[i] = true;
			dfs(i,i);
			visited[i] = false;
		}
		
		Collections.sort(li);
		
		StringBuilder sb = new StringBuilder();
		sb.append(li.size()+"\n");
		for(int i:li)
			sb.append(i+"\n");
		System.out.println(sb);

	}
	
	static void dfs(int i,int n) {
		if(arr[i]==n)
			li.add(n);
		if(!visited[arr[i]]) {
			visited[arr[i]] = true;
			dfs(arr[i],n);
			visited[arr[i]] = false;
		}
	}

}
