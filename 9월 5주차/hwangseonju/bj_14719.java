package algo;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class bj_14719 {

	static int H;	// 세로
	static int W;	// 가로
	static int[][] world;	// 2차원 세계
	static int rain;	// 빗물의 총량
	static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
	static StringTokenizer st = null;
	
	public static void main(String[] args) throws IOException {
		// 빗물
		st = new StringTokenizer(br.readLine(), " ");
		H = Integer.parseInt(st.nextToken());
		W = Integer.parseInt(st.nextToken());
		
		world = new int[H][W];
		st = new StringTokenizer(br.readLine(), " ");
		// 블록 쌓기
		for(int c=0; c<W; c++) {	
			int block = Integer.parseInt(st.nextToken());
			for(int r=H-1; r>=H-block; r--) {
				world[r][c] = 1;
			}
		}
		
		rain = 0;
		// 빗물의 총량 구하기 -> 한 행의 블록과 블록 사이의 차이로 구하기
		for(int r=0; r<H; r++) {
			int start = -1;		// 한 행을 기준으로 처음으로 만난 블록의 열 저장
			int end = -1;		// 한 행을 기준으로 두번째로 만난 블록의 열 저장
			for(int c=0; c<W; c++) {
				if(world[r][c]==1) {	// 블록을 만났을 경우 start에 저장
					if(start==-1) {
						start = c;
					}else	{			// 블록을 만났을 경우 start블록이 이미 있다면 end 블록에 저장
						end = c;
					}
				}
				
				if(start !=-1 && end !=-1) {	// start-end가 한쌍이 생성되면 그 사이에 빗물의 차오를 수 있기 때문에 총량 증가
					rain += (end-start-1);
					start = end;	// 한행에서는 (start-end) 한쌍이 끝나면 end의 열이 start가 됨
					end = -1;
				}
			}
		}
		
		System.out.println(rain);
	}
}

/* 참고한 반례
4 8
3 2 1 2 1 0 3 2
*/