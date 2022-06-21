import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Collections;
import java.util.PriorityQueue;
import java.util.StringTokenizer;

public class BOJ_7662_이중우선순위큐 {
	static StringTokenizer st;
	static StringBuilder sb=new StringBuilder();
	public static void main(String[] args) throws NumberFormatException, IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		int T = Integer.parseInt(br.readLine());//입력 데이터의 수
		for (int t = 0; t < T; t++) {
			PriorityQueue<Integer> q=new PriorityQueue<>();
			PriorityQueue<Integer> qm=new PriorityQueue<>(Collections.reverseOrder());
			int K = Integer.parseInt(br.readLine()); //연산의 개수
			for (int k = 0; k < K; k++) {
				st=new StringTokenizer(br.readLine());
				char c=st.nextToken().charAt(0); // D or I
				int n=Integer.parseInt(st.nextToken());
//				System.out.println("c:"+c+" n:"+n);
				if(c=='D') {
					// D 1 => q에서 최대값 삭제
					// D -1 => q에서 최소값 삭제
					// 비어있으면 D 연산 무시
					if(q.size()==0) continue;
					if(n==-1) {
						int x=q.poll();
						qm.remove(x);
					}else if (n==1) {
						int y=qm.poll();
						q.remove(y);
					}
					
				}else if(c=='I') {
					// 삽입
					qm.add(n);
					q.add(n);
				}
			}
			if(q.size()==0) {
				sb.append("EMPTY").append("\n");
			}else {
				sb.append(qm.poll()).append(" ").append(q.poll()).append("\n");
			}
		}
		System.out.println(sb);
	}
}
