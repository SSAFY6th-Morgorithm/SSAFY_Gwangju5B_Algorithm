import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Comparator;
import java.util.PriorityQueue;
import java.util.Queue;
import java.util.StringTokenizer;

public class BOJ12764_싸지방에간준하 {
	static class UsingComputer implements Comparable<UsingComputer>{
		int number, count, endTime;
		public UsingComputer(int number, int count, int endTime) {
			this.number = number;
			this.count = count;
			this.endTime = endTime;
		}
		public int compareTo(UsingComputer o) {
			return Integer.compare(this.endTime, o.endTime);
		}
	}
	static class EmptyComputer implements Comparable<EmptyComputer>{
		int number, count;
		public EmptyComputer(int number, int count) {
			this.number = number;
			this.count = count;
		}
		public int compareTo(EmptyComputer o) {
			return Integer.compare(this.number, o.number);
		}
	}

	public static void main(String[] args) throws IOException {
		BufferedReader input = new BufferedReader(new InputStreamReader(System.in));
		int N = Integer.parseInt(input.readLine());
		StringTokenizer st;
		Queue<int[]> pq = new PriorityQueue<>(new Comparator<int[]>() {
			public int compare(int[] o1, int[] o2) {
				return Integer.compare(o1[0], o2[0]);
			}
		});
		for (int i = 0; i < N; i++) {
			st = new StringTokenizer(input.readLine());
			int start = Integer.parseInt(st.nextToken()), end = Integer.parseInt(st.nextToken());
			pq.offer(new int[] { start, end });
		}
		Queue<UsingComputer> usingComputerList = new PriorityQueue<>();
		Queue<EmptyComputer> emptyComputerList = new PriorityQueue<>();
		int number = 1;
		while (!pq.isEmpty()) {
			int[] timeInfo = pq.poll();
			int start = timeInfo[0], end = timeInfo[1];
			boolean foundEmptyComputer = false;
			// start : 현재시간, 사용중인 컴퓨터리스트에서 사용이 끝난 컴퓨터들을 사용가능 리스트에 넣기
			if(!usingComputerList.isEmpty()) {
				
				while(usingComputerList.peek() != null && usingComputerList.peek().endTime <= start) {
					UsingComputer uc = usingComputerList.poll();
					emptyComputerList.offer(new EmptyComputer(uc.number, uc.count));
				}
			}
			// 사용가능 리스트에서 가장 번호가 빠른 컴퓨터 빼서 사용
			if(!emptyComputerList.isEmpty()) {
				EmptyComputer ec = emptyComputerList.poll();
				usingComputerList.offer(new UsingComputer(ec.number, ec.count + 1, end));
				foundEmptyComputer = true;
			}
			if (foundEmptyComputer)
				continue;
			else
				usingComputerList.offer(new UsingComputer(number++, 1, end));
		}
		
		while(!usingComputerList.isEmpty()) {
			UsingComputer uc = usingComputerList.poll();
			emptyComputerList.offer(new EmptyComputer(uc.number, uc.count));
		}
		StringBuilder answer = new StringBuilder();
		answer.append(emptyComputerList.size()).append("\n");
		while(!emptyComputerList.isEmpty()) {
			answer.append(emptyComputerList.poll().count).append(" ");
		}
		System.out.println(answer);
	}
}