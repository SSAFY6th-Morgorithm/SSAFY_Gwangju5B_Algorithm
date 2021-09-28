package simulation;

import java.io.*;
import java.util.*;

public class BJ_S3_1966_프린터큐 {
	
	static class Node {
		int num;
		int priority;

		public Node(int num, int priority) {
			super();
			this.num = num;
			this.priority = priority;
		}
	}
	

	public static void main(String[] args) throws Exception{
		BufferedReader br= new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st;
		
		int T = Integer.parseInt(br.readLine());
		
		for (int i = 0; i < T; i++) {
			st  = new StringTokenizer(br.readLine());
			int N = Integer.parseInt(st.nextToken()); // 문서개수
			int M = Integer.parseInt(st.nextToken()); // 궁금한 문서가 놓여 있는 큐
			
			PriorityQueue<Integer> pq = new PriorityQueue<>(Collections.reverseOrder()); // 내림차순으로
			Queue<Node> q = new LinkedList<>(); // 현재 큐
			
			st = new StringTokenizer(br.readLine());
			for (int j = 0; j < N; j++) {
				int pr = Integer.parseInt(st.nextToken());
				q.offer(new Node(j,pr));
				pq.offer(pr);
			}
			
			int cnt = 1;
			while(!q.isEmpty()) {
				Node cur = q.poll();
				
				if(pq.peek() != cur.priority) {
					q.offer(cur);
					continue;
				}
				
				if(cur.num == M) {
					System.out.println(cnt);
					break;
				}
				
				pq.poll();
				cnt++;
			}

		}
		
	}

}
 