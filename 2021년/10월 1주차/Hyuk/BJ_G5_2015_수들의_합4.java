package BJ_Practice;

import java.io.*;
import java.util.*;

public class BJ_G5_2015_수들의_합4 {
	static int N, K;
	static int data[];
	static Map<Integer, Long> map = new HashMap<>();
	static StringTokenizer st;

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringBuilder sb = new StringBuilder();

		st = new StringTokenizer(br.readLine());
		N = Integer.parseInt(st.nextToken());
		K = Integer.parseInt(st.nextToken());
		data = new int[N + 1];

		long answer = 0;
		st = new StringTokenizer(br.readLine());
		for (int i = 1; i <= N; i++) {
			data[i] = Integer.parseInt(st.nextToken()) + data[i - 1];
			if (data[i] == K) {
				answer++;
			}
			if (map.containsKey(data[i] - K))
				answer += map.get(data[i] - K);
			if (!map.containsKey(data[i]))
				map.put(data[i], 1L);
			else
				map.put(data[i], map.get(data[i]) + 1);
		}

		System.out.println(answer);
	}
}
