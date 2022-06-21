import java.io.*;
import java.util.*;

/**
 * 2022 KAKAO BLIND RECRUITMENT �Ű� ��� �ޱ�
 * @author CHO
 * @see https://programmers.co.kr/learn/courses/30/lessons/92334
 * @category ����
 */
public class Kakao_�Ű����ޱ� {

	static StringTokenizer st;

	public static void main(String[] args) throws IOException {
		/*
		 * Ȯ���� ���� �ӽ÷� �־��� ��
		 */
		String[] id_list = { "muzi", "frodo", "apeach", "neo" };
		String[] report = { "muzi frodo", "apeach frodo", "frodo neo", "muzi neo", "apeach muzi" };
		int k = 2; 
		int[] answer = new int[id_list.length];

		HashMap<String, Integer> map = new HashMap<>();
		for (int i = 0; i < id_list.length; i++) {
			map.put(id_list[i], i);
		} // id�� ��ȣ �Ҵ�

		List<List<Integer>> list = new ArrayList<List<Integer>>();
		for (int i = 0; i < id_list.length; i++) {
			list.add(new ArrayList<Integer>());
		} // list �ʱ�ȭ

		// --------------------�Ű� ���� (�ߺ� �ϴ� ���)
		for (int i = 0; i < report.length; i++) {
			String str = report[i];
			String[] s = str.split(" ");
			list.get(map.get(s[1])).add(map.get(s[0]));
		} 

		// --------------------�ߺ� ���� Ȯ���Ͽ� ���� ó��
		boolean[] vis; // �ߺ� ���� Ȯ��
		int cnt; // �Ű� Ƚ��
		for (int i = 0; i < list.size(); i++) {
			List<Integer> cur = list.get(i);
			vis = new boolean[id_list.length];
			cnt = 0;
			for (int j = 0; j < cur.size(); j++) {
				if (!vis[cur.get(j)]) {
					vis[cur.get(j)] = true;
					cnt++;
				}
			}// �ߺ� ���� Ȯ�� 
			// ���� ó�� 
			if (cnt >= k) {
				for (int j = 0; j < id_list.length; j++) {
					if (vis[j])
						answer[j] += 1;
				}
			}

		}
		System.out.println(Arrays.toString(answer));
	}
}
