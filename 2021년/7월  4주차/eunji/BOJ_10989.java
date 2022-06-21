import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.util.StringTokenizer;

public class BOJ_10989 {
	/* 
	 * 계수정렬
	 * 시간초과 주의!!
	 * 1) Scanner 입력 -> 시간초과
	 * 2) 10001개까지 다 탐색 -> max값 구해서 max+1까지 탐색 -> 그래도 시간초과
	 * 3) 입력 방식을 Buffer로 바꿈 -> 통과     
	 */
	public static void main(String[] args) throws IOException{
		// TODO Auto-generated method stub
		BufferedReader bf = new BufferedReader(new InputStreamReader(System.in));
		BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
		StringTokenizer st;
		
		int N=Integer.parseInt(bf.readLine());
		int[] arr=new int[10001]; // 계수정렬을 위해 배열 생성
		int max=0;
		
		for (int i=0; i<N; i++) {
			st=new StringTokenizer(bf.readLine());
			int num=Integer.parseInt(st.nextToken());
			arr[num]+=1;
			if (max<num) {
				max=num;
			}
		}
		for (int i=0; i<max+1; i++) {
			if (arr[i]!=0) {
				// 값이 존재한다면 
				for (int r=0; r<arr[i]; r++) {
					// i를 st[i](i의 개수)만큼 반복해서 출력
					bw.write(i+"\n");
				}
			}
		}
		bw.flush();
		bw.close();
	}
}
