package algo;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.List;
import java.util.StringTokenizer;

public class bj_3190 {
	
	static int N;		// 보드의 크기
	static int[][] map;	// 보드
	static int K;		// 사과의 개수
	static int L;		// 뱀의 방향 변환 횟수
	static int time;	// 게임 진행 시간
	static List<Turn> list;	// 방향 전환 정보
	static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
	static StringTokenizer st = null;
	
	public static void main(String[] args) throws NumberFormatException, IOException {
		// 뱀
		N = Integer.parseInt(br.readLine());
		map = new int[N][N];
		map[0][0] = 1;
		
		K = Integer.parseInt(br.readLine());
		for(int k=0; k<K; k++) {
			st = new StringTokenizer(br.readLine());
			int kr = Integer.parseInt(st.nextToken())-1;
			int kc = Integer.parseInt(st.nextToken())-1;
			map[kr][kc] = -1;	// 사과 위치 표시
		}
		
		L = Integer.parseInt(br.readLine());
		list = new ArrayList<>();
		for(int l=0; l<L; l++) {
			st = new StringTokenizer(br.readLine());
			list.add(new Turn(Integer.parseInt(st.nextToken()), st.nextToken()));
		}
		
		time = 0;
		int[][] Ddeltas = {{0,1},{1,0},{0,-1},{-1,0}};	// 현재 방향이 오른쪽일 경우
		int[][] Ldeltas = {{-1,0},{0,-1},{1,0},{0,1}};	// 현재 방향이 왼쪽일 경우
		String dir = "D";	// 시작 방향은 오른쪽
		int turn = 0;		// 방향 변경 횟수 - turn에 따라 deltas 지정
		List<Dot> snake = new ArrayList<>();	// 뱀 - snake.get(0) : 꼬리, snake.get(size()-1) : 머리
		snake.add(new Dot(0,0));
		while(true) {
			// 시간 증가
			time++;
			
			// 뱀 이동
			int nr = 0, nc = 0;
			if(dir.equals("D")) {
				nr = snake.get(snake.size()-1).x + Ddeltas[turn%4][0];
				nc = snake.get(snake.size()-1).y + Ddeltas[turn%4][1];
			}else {
				nr = snake.get(snake.size()-1).x + Ldeltas[turn%4][0];
				nc = snake.get(snake.size()-1).y + Ldeltas[turn%4][1];
			}
			
			// 벽에 부딪힐 경우 게임  끝
			if (!isIn(nr, nc)) {
				break;
			}
			
			// 자신의 몸과 부딪힐 경우 게임 끝
			if (map[nr][nc] == 1) {
				break;
			}
			
			// 사과 존재 여부 확인
			if(map[nr][nc] == 0) {	// 사과가 없을 경우
				map[snake.get(0).x][snake.get(0).y]=0;
				snake.remove(0);
			}
			
			// 뱀 머리 표시
			map[nr][nc] = 1;
			snake.add(new Dot(nr, nc));
			
			// 방향 전환
			if(list.size()>0 && time == list.get(0).t) {
				if(dir.equals(list.get(0).Dir)) {
					turn++;
				}else {
					if (turn%4 == 1) {
						turn = 3;
					} else if (turn%4 == 3) {
						turn = 1;
					}
					dir = list.get(0).Dir;
				}
				list.remove(0);	// 정보 사용시 삭제
			}
		}
		
		System.out.println(time);
	}
	
	static boolean isIn(int r, int c) {
		return r>=0 && r<N && c>=0 && c<N;
	}
	
	static class Dot{
		int x,y;	// 좌표

		public Dot(int x, int y) {
			super();
			this.x = x;
			this.y = y;
		}
	}
	
	static class Turn{
		int t;		// 초
		String Dir;	// 방향(D=오른쪽, L=왼쪽)
		
		public Turn(int t, String d) {
			super();
			this.t = t;
			Dir = d;
		}
	}
}
