package jan25_twoPointer;

import java.io.*;
import java.util.*;

/**
 * ���������� ����+1�̸� sushi�迭���� +1 ���� -> �׳� count�� �÷���
 * @author yun
 *
 */
public class BJ_G4_15961_ȸ���ʹ� {

	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringBuilder sb = new StringBuilder();
		StringTokenizer st = new StringTokenizer(br.readLine());

		int N = Integer.parseInt(st.nextToken()); // ��Ʈ����
		int d = Integer.parseInt(st.nextToken()); // �ʹ�
		int k = Integer.parseInt(st.nextToken()); // ��������
		int c = Integer.parseInt(st.nextToken()); // ����

		int[] arr = new int[N];
		int[] sushi = new int[d + 1];

		for (int i = 0; i < N; i++) {
			arr[i] = Integer.parseInt(br.readLine());
		}

		int max = 0;

		for (int i = 0; i < k; i++) {
			if (sushi[arr[i]] == 0) {
				max++; // ó�� Ȯ���� ���õ鿡�� �ʹ� ����(�ʱ�ȭ)
			}
			sushi[arr[i]]++;
		}

		int cur = max; 
		if (sushi[c] == 0) {
			max++; // �������� ����+1 �ѰŴϱ� cur���� ����x
		}

		int start = k;
		while (true) {

			sushi[arr[(start - k) % N]]--;
			if (sushi[arr[(start - k) % N]] == 0)
				cur--;

			if (sushi[arr[start % N]] == 0) {
				cur++;
			}
			sushi[arr[start % N]]++;
			
			
			if (sushi[c] == 0) {
				max = Math.max(max, cur+1); // ���������� +1 (���� cur���� �ݿ��ϸ� �ȵ�)
			}else {
				max = Math.max(max, cur);				
			}

			start++;
			if (start == (N - 1) + k) // �ѹ��� ���� ��
				break;
		}

		System.out.println(max);
	}

}
