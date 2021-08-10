package solution;

import java.util.LinkedList;
import java.util.Queue;
import java.util.Scanner;

public class BJ_7562 {

	static int W,curX,curY,endX,endY;
	static int cnt = 0;
	static int[][] board;
	static boolean[][] visited;
	static Queue<Pos> posQ = new LinkedList<Pos>();
	static int[][] dir = {{-2,1},{-1,2},{2,1},{1,2},{2,-1},{1,-2},{-2,-1},{-1,-2}};
	public static void main(String[] args) {
		Scanner sc = new Scanner(System.in);
		int T = sc.nextInt();
		for(int t=1;t<=T;t++) {
			W = sc.nextInt();
			board = new int[W][W];
			visited = new boolean[W][W];
			curX = sc.nextInt();
			curY = sc.nextInt();
			endX = sc.nextInt();
			endY = sc.nextInt();
			
			BFS(new Pos(curX,curY));
			System.out.println(board[endX][endY]);
		}

	}

	public static void BFS(Pos p) {
		if(p.x==endX && p.y==endY)
			return;
		visited[p.x][p.y] = true;
		posQ.add(p);
		
		while(!posQ.isEmpty()) {
			Pos a = posQ.remove();
			int x1 = a.x;
			int y1 = a.y;
			for(int i=0;i<8;i++) {
				int x2 = x1+dir[i][0];
				int y2 = y1+dir[i][1];
				
				if(isIn(x2,y2) && !visited[x2][y2]) {
					posQ.add(new Pos(x2,y2));
					visited[x2][y2] = true;
					board[x2][y2] = board[x1][y1]+1;
				}
			}
		}
	}
	
	public static boolean isIn(int x,int y) {
		return x>=0 && x<W && y>=0 && y<W;
	}
}

class Pos{
	int x,y;
	
	public Pos(int x,int y) {
		this.x = x;
		this.y = y;
	}
}
