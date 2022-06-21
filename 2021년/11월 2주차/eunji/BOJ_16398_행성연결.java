import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.PriorityQueue;
import java.util.StringTokenizer;

/**
 * 144240	552
 * @author CHO
 * @see https://www.acmicpc.net/problem/16398
 * @category MST-PRIM(인접행렬), long 주의
 */
public class BOJ_16398_행성연결 {
	static StringTokenizer st;

	public static void main(String[] args) throws NumberFormatException, IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		int N = Integer.parseInt(br.readLine());
		long[][] data=new long[N+1][N+1];
		
		for (int i = 1; i < N+1; i++) {
			st=new StringTokenizer(br.readLine());
			for (int j = 1; j < N+1; j++) {
				data[i][j]=Long.parseLong(st.nextToken());
			}
		} // 입력 완료
		
		long result=0;
		boolean[] visited=new boolean[N+1];
		long[] distance=new long[N+1];
		Arrays.fill(distance, 100000001);
		distance[1]=0;
		
		for (int i = 1; i < N+1; i++) {
			int vertex=0;
			long min=100000001;
			for (int j = 1; j < N+1; j++) {
				if(!visited[j] && min>distance[j]) {
					vertex=j;
					min=distance[j];
				}
			}
			visited[vertex]=true;
			result+=min;
			for (int j=1; j<N+1; j++) {
				if(!visited[j] && data[vertex][j]!=0 && distance[j]>data[vertex][j]) {
					distance[j]=data[vertex][j];
				}
			}
		}
		System.out.println(result);
	}
}
