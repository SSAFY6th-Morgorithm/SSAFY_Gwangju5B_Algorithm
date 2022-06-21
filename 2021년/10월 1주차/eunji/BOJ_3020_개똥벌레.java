import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.StringTokenizer;

/**
 * 31484	264
 * @author CHO
 * @see https://www.acmicpc.net/problem/3020
 * @category 누적합
 * 아래에서부터 + 위에서부터 각각 구해서 더하기
 */
public class BOJ_3020_개똥벌레 {
	static StringTokenizer st;
	public static void main(String[] args) throws IOException,NumberFormatException{
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		st = new StringTokenizer(br.readLine());
		int N = Integer.parseInt(st.nextToken());
		int H = Integer.parseInt(st.nextToken());
		
		// 누적합 리스트
		int[] odd=new int[H+1];
		int[] even=new int[H+1];
		
		for (int c = 1; c < N+1; c++) {
			int curr=Integer.parseInt(br.readLine());
			if(c%2!=0){
				// 석순(아래에서), 홀
				odd[curr]+=1;
			}else {
				// 종유석(위에서)
				even[curr]+=1;
			}
		}// 입력 완료
		
		// 누적합 구하기
		for (int i = 1; i < H+1; i++) {
			odd[i]=odd[i-1]+odd[i];
			even[i]=even[i-1]+even[i];
		}
		System.out.println(Arrays.toString(even));
		
		int result2=0;
		
		// 결과 저장할 list
		int[] list=new int[H+1];
		int min=Integer.MAX_VALUE;
		
		for (int i = 1; i < H+1; i++) {
			list[i]+=odd[H]-odd[i-1];
			// 위에서 부터 내려오니까 1==7 -> H-i+1
			list[i]+=even[H]-even[H-i+1-1];
			min=min>list[i]?list[i]:min;
		}

		for (int i = 1; i < H+1; i++) {
			if(list[i]==min) result2++;
		}
		
		System.out.println(min+" "+result2);
		
	}

}
