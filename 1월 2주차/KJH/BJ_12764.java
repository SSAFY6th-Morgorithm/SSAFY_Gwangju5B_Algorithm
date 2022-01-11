package BJ;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.PriorityQueue;
import java.util.StringTokenizer;

public class BJ_12764 {
	static class Time implements Comparable<Time>{
		int in,out,num;
		public Time(int in,int out) {
			this.in = in;
			this.out = out;
		}

		@Override
		public int compareTo(Time t) {
			return Integer.compare(this.in, t.in);
		}
	}
	static class Com implements Comparable<Com>{
		int in,out,num;
		public Com(int in,int out,int num) {
			this.in = in;
			this.out = out;
			this.num = num;
		}

		@Override
		public int compareTo(Com c) {
			return Integer.compare(this.out, c.out);
		}
	}
	static int[] seat = new int[100001];
	static PriorityQueue<Time> inQ = new PriorityQueue<>();
	static PriorityQueue<Com> comQ = new PriorityQueue<>();
	static PriorityQueue<Integer> emptyQ = new PriorityQueue<>();
	public static void main(String[] args) throws NumberFormatException, IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st;
		int N = Integer.parseInt(br.readLine());
		for(int i=0;i<N;i++) {
			st = new StringTokenizer(br.readLine());
			int in = Integer.parseInt(st.nextToken());
			int out = Integer.parseInt(st.nextToken());
			inQ.add(new Time(in,out));
		}
		
		int num = 0;
		while(!inQ.isEmpty()) {
			Time q = inQ.poll();
			int in = q.in;
			int out = q.out;
			
			if(comQ.isEmpty()) {
				comQ.add(new Com(in,out,++num));
				seat[num]++;
			}
			else {
				if(in<comQ.peek().out) {
					if(emptyQ.isEmpty()) {
						comQ.add(new Com(in,out,++num));
						seat[num]++;
					}
					else {
						int emp = emptyQ.poll();
						comQ.add(new Com(in,out,emp));
						seat[emp]++;
					}
				}
				else {
					while(in>comQ.peek().out) {
						emptyQ.add(comQ.poll().num);
						if(comQ.isEmpty())
							break;
					}
					int emp = emptyQ.poll();
					comQ.add(new Com(in,out,emp));
					seat[emp]++;
				}
			}
		}
		
		StringBuilder sb = new StringBuilder();
		sb.append(num).append("\n");
		for(int i=1;i<=num;i++)
			sb.append(seat[i]).append(" ");
		System.out.println(sb);
		
	}

}
