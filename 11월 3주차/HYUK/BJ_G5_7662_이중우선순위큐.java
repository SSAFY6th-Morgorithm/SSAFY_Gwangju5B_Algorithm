package BJ_Practice;

import java.io.*;
import java.util.*;
import java.util.Map.*;

public class BJ_G5_7662_이중우선순위큐 {

	static int T, N;
	static StringTokenizer st;

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringBuilder sb = new StringBuilder();
		PriorityQueue<Integer> min = new PriorityQueue<>();
		PriorityQueue<Integer> max = new PriorityQueue<>(Collections.reverseOrder());
		Map<Integer, Integer> map = new HashMap<>();
		T = Integer.parseInt(br.readLine());
		int val = 0;
		for (int t = 1; t <= T; t++) {
			min.clear();
			max.clear();
			map.clear();
			N = Integer.parseInt(br.readLine());
			int count = 0;
			for (int i = 0; i < N; i++) {
				st = new StringTokenizer(br.readLine());
				char op = st.nextToken().charAt(0);
				int num = Integer.parseInt(st.nextToken());
				if (op == 'I') {
					min.offer(num);
					max.offer(num);
					map.put(num, map.getOrDefault(num, 0) + 1);
					count++;
				} else {
					if (count > 0) {
						count--;
						if (num == 1) {
							val = max.poll();
							while (!max.isEmpty() && map.get(val) <= 0) {
								val = max.poll();

							}
						} else {
							val = min.poll();
							while (!min.isEmpty() && map.get(val) <= 0) {
								val = min.poll();
							}
						}
						map.put(val, map.get(val) - 1);
					}
				}

			}
			int maxVal = 0, minVal = 0;

			if (count <= 0) {
				sb.append("EMPTY").append("\n");
			} else {
				while (true) {
					int n = max.poll();
					if (map.get(n) > 0) {
						maxVal = n;
						break;
					}
				}
				while (true) {
					int n = min.poll();
					if (map.get(n) > 0) {
						minVal = n;
						break;
					}
				}
				sb.append(maxVal).append(" ").append(minVal).append("\n");
			}
		}
		System.out.println(sb.toString());
	}
}
