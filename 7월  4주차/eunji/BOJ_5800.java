import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.Collections;
import java.util.StringTokenizer;

public class BOJ_5800 {
	
	public static void main(String[] args) throws IOException {
		// TODO Auto-generated method stub

		BufferedReader br=new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st;
		
		int K=Integer.parseInt(br.readLine());
		
		
		for (int k=0; k<K; k++) {
			st=new StringTokenizer(br.readLine());
			int max=0;
			int N=Integer.parseInt(st.nextToken());
			int[] num_list=new int[N];

			for (int n=0; n<N; n++) {
				int score=Integer.parseInt(st.nextToken());
				num_list[n]=score;         

			}
			// int 배열은 내림차순 하려면 Integer 객체 타입으로 변환해주어야 함
			/* 
			 * Integer vs int
			 * int : 기본 자료형, 산술 연산이 가능, null로 초기화 불가능
			 * Integer : Wrapper 클래스, 직접적인 산술 연산 불가, null값 처리 가능->db 사용
			 */
			Integer[] num_list2=Arrays.stream(num_list).boxed().toArray(Integer[] :: new);
			Arrays.sort(num_list2,Collections.reverseOrder());
			
			for (int i=0; i<N-1; i++) {
				if(max<num_list2[i]-num_list2[i+1]) {
					max=num_list2[i]-num_list2[i+1];
				}
			}
			System.out.println("Class "+(k+1));
			System.out.printf("Max %d, Min %d, Largest gap %d%n",num_list2[0],num_list2[N-1],max);
		}
	}

}
