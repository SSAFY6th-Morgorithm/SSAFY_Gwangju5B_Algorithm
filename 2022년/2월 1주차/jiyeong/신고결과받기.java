package feb08_kakao2022;

import java.io.*;
import java.util.*;

public class �Ű����ޱ� {

	public static void main(String[] args) throws Exception {
		StringBuilder sb = new StringBuilder();

//		String[] id_list = { "muzi", "frodo", "apeach", "neo" };
		String[] id_list = { "con", "ryan" };
//		String[] report = { "muzi frodo", "apeach frodo", "frodo neo", "muzi neo", "apeach muzi" };
		String[] report = { "ryan con", "ryan con", "ryan con", "ryan con" };
		int k = 2;

		int[] answer = solution(id_list, report, k);
		for (int a : answer) {
			sb.append(a + " ");
		}

		System.out.println(sb);
	}

	public static int[] solution(String[] id_list, String[] report, int k) {
		StringTokenizer st;
		int[] answer = new int[id_list.length];

		Map<String, Integer> mailCnt = new HashMap<>(); // ���� ���ϼ�
		Map<String, ArrayList<String>> memberList = new HashMap<>(); // ��� - �Ű��� ���

		for (int i = 0; i < id_list.length; i++) {
			mailCnt.put(id_list[i], 0);
			memberList.put(id_list[i], new ArrayList<>());
		}

		for (int i = 0; i < report.length; i++) {
			st = new StringTokenizer(report[i]);
			String a = st.nextToken(); // ����
			String b = st.nextToken(); // ����
			if(!memberList.get(b).contains(a)) { // �ߺ�x 1���� ����
				memberList.get(b).add(a);				
			}
		}

		for (String key : memberList.keySet()) {
			ArrayList<String> reportMem = memberList.get(key);
//			System.out.println("�Ű���� ���: " + key + " -> �Ű��� ���: " + reportMem);
			if (reportMem.size() >= k) {
				for (String mem : reportMem) { 
					mailCnt.put(mem, mailCnt.get(mem) + 1); // �Ű��� ������� ���Ϻ�����
				}
			}
		}

		for (int i = 0; i < answer.length; i++) {
			answer[i] = mailCnt.get(id_list[i]);
		}

		return answer;
	}
}
