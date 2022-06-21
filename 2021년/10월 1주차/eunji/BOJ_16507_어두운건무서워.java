import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

/**
 * 85808	460
 * @author CHO
 * @see https://www.acmicpc.net/problem/16507
 * @category 누적합
 * 구간 합 구하기와 유사 
 */
public class BOJ_16507_어두운건무서워 {
	static StringTokenizer st;
	static StringBuilder sb=new StringBuilder();
	public static void main(String[] args) throws IOException,NumberFormatException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		st = new StringTokenizer(br.readLine());
		int R= Integer.parseInt(st.nextToken());
		int C= Integer.parseInt(st.nextToken());
		int Q= Integer.parseInt(st.nextToken());
		
		int [][] map=new int[R+1][C+1];
		for (int i = 1; i < R+1; i++) {
			st = new StringTokenizer(br.readLine());
			for (int j = 1; j < C+1; j++) {
				int input=Integer.parseInt(st.nextToken());
				// 누적 합 구하기
				map[i][j]=map[i-1][j]+map[i][j-1]+input-map[i-1][j-1];
			}
		}
		for (int i = 0; i < Q; i++) {
			st = new StringTokenizer(br.readLine());
			int x1=Integer.parseInt(st.nextToken());
			int y1=Integer.parseInt(st.nextToken());
			int x2=Integer.parseInt(st.nextToken());
			int y2=Integer.parseInt(st.nextToken());
			
			int result=map[x2][y2]-map[x2][y1-1]-map[x1-1][y2]+map[x1-1][y1-1];
			int cnt=(y2-y1+1)*(x2-x1+1);
			
			sb.append(result/cnt).append("\n");
		}// 입력 완료
		
		System.out.println(sb);

	}

}
