import java.io.*;
import java.util.*;

/**
 * 2022 KAKAO BLIND RECRUITMENT ���� ��� ���
 * @author CHO
 * @see https://programmers.co.kr/learn/courses/30/lessons/92341
 * @category ����, ���ڿ�
 */
public class Kakao_������ݰ�� {

	public static void main(String[] args) throws IOException {
		int[] fees = { 1, 461, 1, 10 };
		String[] records = { "00:00 1234 IN"}; // Ȯ���� ���� �ӽ÷� �־�� �� 

		List<String> car = new ArrayList<>(); // ����ȣ ����
		HashMap<String, ArrayList<String>> map = new HashMap<>(); // ����ȣ���� ���� ����

		for (int i = 0; i < records.length; i++) {
			String[] cur = records[i].split(" ");
			String time = cur[0];
			String carNum = cur[1];
			String state = cur[2];
			if (!map.containsKey(carNum)) {
				map.put(carNum, new ArrayList<>());
				car.add(carNum);
			}
			map.get(carNum).add(time + " " + state);
		} // ����ȣ�� Ű������ �ð��� ���� ����
		
		int[] answer = new int[car.size()]; // ���� �迭 
		Collections.sort(car); // ����ȣ�� �������� ����
		
		for (int i = 0; i < car.size(); i++) {
			ArrayList<String> list = map.get(car.get(i)); // ����ȣ���� ����
			int total = 0;
			if (list.size() % 2 == 0) {
				// ¦���� IN,OUT ������
				for (int j = 0; j < list.size(); j += 2) {
					String[] str = list.get(j).split(" ");
					if (str[1].equals("IN")) {
						// ���� �ð��� ����ؾ� ��
						String[] nextStr=list.get(j+1).split(" ");
						total += cal(str,nextStr);
					}
				}
			} else {
				for (int j = 0; j < list.size() - 1; j += 2) {
					String[] str = list.get(j).split(" ");
					if (str[1].equals("IN")) { 
						String[] nextStr=list.get(j+1).split(" ");
						total += cal(str,nextStr);
					}
				}
				// ������ ���� IN �ϳ� ���
				// ������ OUT�� 23:59
				String[] str = list.get(list.size() - 1).split(" ");
				String[] nextStr= {"23:59","OUT"};
				total += cal(str,nextStr);
			}
			if (total <= fees[0]) answer[i] = fees[1];
			else answer[i] = (int) (fees[1] + (Math.ceil((double)(total - fees[0]) / fees[2]) * fees[3]));
		}
		System.out.println(Arrays.toString(answer));
	}
	// �ð� ��� 
	private static int cal(String[] str1, String[] str2) {
		String time=str1[0];
		String nextTime=str2[0];
		String[] timesp = time.split(":");
		String[] nextTimesp = nextTime.split(":"); //��ó�� 
		
		int h = Integer.parseInt(timesp[0]);
		int min = Integer.parseInt(timesp[1]);
		int nextH = Integer.parseInt(nextTimesp[0]);
		int nextMin = Integer.parseInt(nextTimesp[1]);
		
		return ((nextH * 60) + nextMin) - ((h * 60) + min);
	}
}
