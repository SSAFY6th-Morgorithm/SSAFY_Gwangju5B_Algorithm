package feb27_map;

import java.io.*;
import java.util.*;

public class BJ_G5_22252_��������ȣ�� {

	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringBuilder sb = new StringBuilder();
		StringTokenizer st;

		int Q = Integer.parseInt(br.readLine());
		long answer = 0;
		Map<String, PriorityQueue<Integer>> map = new HashMap<>();

		for (int t = 0; t < Q; t++) {
			st = new StringTokenizer(br.readLine());
			int a = Integer.parseInt(st.nextToken());
			String lang = st.nextToken();
			int cnt = Integer.parseInt(st.nextToken());

			if (a == 1) {
				PriorityQueue<Integer> pq;

				if (!map.containsKey(lang)) {
					pq = new PriorityQueue<>(Collections.reverseOrder()); // ��������
				} else {
					pq = map.get(lang);
				}

				for (int j = 0; j < cnt; j++) {
					pq.add(Integer.parseInt(st.nextToken()));
					map.put(lang, pq);
				}

			} else {
				// Ű�� �ְ� map�� ������� ������
//				if(map.containsKey(lang) && !map.get(lang).isEmpty()) {
//					// cnt ��ŭ
//					while(cnt > 0) {
//						// cnt���� map ������
//						if(map.get(lang).isEmpty()) break;
//						answer += map.get(lang).poll();
//						cnt--;
//					}
//				}
				if (map.containsKey(lang)) {
					for (int i = 0; i < cnt; i++) {
						// cnt�� �� ũ�� break
						if (map.get(lang).isEmpty()) break;
						answer += map.get(lang).poll();
					}
				}
			}
		}

		System.out.println(answer);

	}
}
