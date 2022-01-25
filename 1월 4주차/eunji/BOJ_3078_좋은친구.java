import java.io.*;
import java.util.*;

public class BOJ_3078_����ģ�� {
	
	static StringTokenizer st;

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		st=new StringTokenizer(br.readLine());
		int N = Integer.parseInt(st.nextToken());
		int K = Integer.parseInt(st.nextToken());
		ArrayList<Queue<Integer>> list=new ArrayList<>();
		for (int i = 0; i < 21; i++) {
			list.add(new LinkedList<Integer>());
		}// ť 20�� ����
		long result=0;
		for (int i = 1; i < N+1; i++) {
			String str=br.readLine();
			int len=str.length();
			Queue<Integer> q=list.get(len);
			while(!q.isEmpty() && i-q.peek()>K) {
				q.poll();
			}
			result+=q.size();
			q.add(i); // ��� ����
		}// �Է� �Ϸ�
		System.out.println(result);
	}

}
