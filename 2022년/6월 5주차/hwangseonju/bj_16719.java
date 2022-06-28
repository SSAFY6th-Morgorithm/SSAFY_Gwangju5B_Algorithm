package algo;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.PriorityQueue;
import java.util.Stack;

public class bj_16719 {

	static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
	static StringBuilder sb = new StringBuilder();
	static Stack<Alphabet> stack = new Stack<>();	// 기준점 저장
	static char[] alph;	// 문자열 -> 문자
	static int[] visited; // 문자 추가 순서 저장
	static int count = 1;
	
	public static void main(String[] args) throws IOException {
		// ZOAC
		String str = br.readLine();
		alph = new char[str.length()];
		visited = new int[str.length()];
		
		PriorityQueue<Alphabet> pq = new PriorityQueue<>();
		
		for(int a=0; a<alph.length; a++) {
			alph[a] = str.charAt(a);
			pq.add(new Alphabet(alph[a], a));
		}
		
		while(!pq.isEmpty()) {
			Alphabet current = pq.poll();
			if(visited[current.x]==0) {	// 아직 추가되지않은 문자만 가능
				Order(current);
			}
		}
		
//		for(int a=0; a<alph.length; a++) {
//			System.out.print(visited[a]+" ");
//		}
//		System.out.println();
		
		for(int a=1; a<=str.length(); a++) {
			for(int v=0; v<str.length(); v++) {
				if(visited[v]<=a) {
					sb.append(alph[v]);
				}
			}
			sb.append("\n");
		}
		
		sb.deleteCharAt(sb.lastIndexOf("\n"));
		System.out.print(sb);
	}
	
	static void Order(Alphabet current) {
		PriorityQueue<Alphabet> inpq = new PriorityQueue<>();
		if(visited[current.x]==0) {
			visited[current.x] = count++;
		}
		
		if(current.x < alph.length-1) {
			for(int c=current.x+1; c<alph.length; c++) {
				if(visited[c]==0) {
					inpq.add(new Alphabet(alph[c], c));
				}
			}
		}
		
		if(!inpq.isEmpty()) {
			Alphabet next = inpq.poll();
			stack.add(current);
			Order(next);
		}else if(!stack.isEmpty()){
			Order(stack.pop());
		}
	}
	
	static class Alphabet implements Comparable<Alphabet>{
		char a;	// 알파벳
		int x;	// 자리
		
		public Alphabet(char a, int x) {
			super();
			this.a = a;
			this.x = x;
		}

		@Override
		public int compareTo(Alphabet o) {
			if(this.a==o.a) {
				return this.x - o.x;
			}
			return this.a - o.a;
		}
	}

}
