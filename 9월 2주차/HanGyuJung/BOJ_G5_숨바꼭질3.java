package week9_2;

import java.util.LinkedList;
import java.util.PriorityQueue;
import java.util.Queue;
import java.util.Scanner;

public class BOJ_G5_숨바꼭질3 {
	private static int N; 
	private static int K; 
	private static boolean[] isVisited = new boolean[100001];
	private static int answer;
	

	public static void main(String[] args) {
		Scanner sc = new Scanner(System.in);
		N = sc.nextInt();
		K = sc.nextInt();
		bfs();
		System.out.println(answer);
	}

	static void bfs() {
		PriorityQueue<Point> queue = new PriorityQueue<>();
		queue.offer(new Point(N, 0));
		while (!queue.isEmpty()) {
			Point point = queue.poll();
			isVisited[point.pos] = true;
			if (point.pos == K) {
				answer = point.count;
				break;
			}
			if (point.pos * 2 <= 100000 && !isVisited[point.pos * 2]) {
				queue.offer(new Point(point.pos * 2, point.count));
			}
			if ( point.pos + 1 <= 100000 && !isVisited[point.pos + 1]) {
				queue.offer(new Point(point.pos + 1, point.count + 1));
			}
			if ( point.pos - 1 >= 0 && !isVisited[point.pos - 1]) {
				queue.offer(new Point(point.pos - 1, point.count + 1));
			}
		}
	}

	private static class Point implements Comparable<Point> {
		int pos;
		int count;

		public Point(int pos, int count) {
			this.pos = pos;
			this.count = count;
		}

		@Override
		public int compareTo(Point o) {
			return count - o.count;
		}
	}

}