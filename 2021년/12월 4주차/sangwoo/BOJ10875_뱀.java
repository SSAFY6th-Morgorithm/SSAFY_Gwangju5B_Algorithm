import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class BOJ10875_뱀 {
	static class RouteInfo {
		long startX, startY, endX, endY;
		boolean isVerticalToX;

		public RouteInfo(long startX, long startY, long endX, long endY, boolean isVerticalToX) {
			this.startX = startX;
			this.startY = startY;
			this.endX = endX;
			this.endY = endY;
			this.isVerticalToX = isVerticalToX;
		}

	}

	private final static int[][] deltas = { { 1, 0 }, { 0, -1 }, { -1, 0 }, { 0, 1 } }; // (x,y) 시계방향순
	static int direction;
	static long L, answer;
	static RouteInfo[] routeInfos;

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		L = Long.parseLong(br.readLine());
		int N = Integer.parseInt(br.readLine());
		routeInfos = new RouteInfo[N + 1];
		long[] snakeHead = new long[] { 0, 0 };
		answer = 0;
		// N이 0일때 고려, 회전후에도 뱀은 계속해서 나아간다
		String[] commands = new String[N];
		for (int i = 0; i < N; i++)
			commands[i] = br.readLine();
		String[] ss;
		boolean isEnded = false;
		for (int i = 0; i < N; i++) {
			if (isEnded) {
				break;
				}
			ss = commands[i].split(" ");
			int t = Integer.parseInt(ss[0]);
			String nextDirection = ss[1];
			long startX = snakeHead[0], startY = snakeHead[1];
			long endX = startX + t * deltas[direction][0], endY = startY + t * deltas[direction][1];
			routeInfos[i] = new RouteInfo(startX, startY, endX, endY, startX == endX);
			isEnded = check(i, t);

			snakeHead = new long[] { endX, endY };
			if (nextDirection.equals("R"))
				direction++;
			else
				direction--;
			if (direction >= 4)
				direction -= 4;
			else if (direction < 0)
				direction += 4;
		}
		if (!isEnded) {
			long startX = snakeHead[0], startY = snakeHead[1];
			long endX = startX + (2 * L + 1) * deltas[direction][0], endY = startY + (2 * L + 1) * deltas[direction][1];
			routeInfos[N] = new RouteInfo(startX, startY, endX, endY, startX == endX);
			isEnded = check(N, 2 * L + 1);
		}
		System.out.println(answer);
	}

	private static boolean check(int currentIndex, long t) {
		RouteInfo currentLine = routeInfos[currentIndex];
		// TODO Auto-generated method stub
		// 1. currentIndex - 2까지 겹치는 직선이 있는지 검사
		long minDistance = Long.MAX_VALUE;
		boolean isEnded = false;
		for (int i = currentIndex - 2; i >= 0;  i--) {
			RouteInfo previousLine = routeInfos[i];
			// 서로 수평인지 수직인지 비교, 가장 가까운 선분과 차이를 구해야함
			if (currentLine.isVerticalToX == previousLine.isVerticalToX) {
				// 서로 수평인 경우 축의 기준이 다르면 평행, 같으면 비교 필요
				if (currentLine.isVerticalToX) { // X축에 수직인 경우 -> x = a, b <= y <= c 형태
					if (currentLine.startX == previousLine.startX) { // 한 직선위에 같이 있는 경우
						int min = (int) Math.min(previousLine.startY, previousLine.endY);
						int max = (int) Math.max(previousLine.startY, previousLine.endY);
						
						if(currentLine.startY > max && currentLine.endY <= max) {
							long currentDistance = Math.abs(currentLine.startY - max);
							minDistance = minDistance > currentDistance ? currentDistance : minDistance;
							isEnded = true;
						}
						else if(currentLine.startY < min && currentLine.endY >= min) {
							long currentDistance = Math.abs(min - currentLine.startY);
							minDistance = minDistance > currentDistance ? currentDistance : minDistance;
							isEnded = true;
						}
						
					} else {
						continue;
					}
				} else {
					if (currentLine.startY == previousLine.startY) { // 한 직선위에 같이 있는 경우
						int min = (int) Math.min(previousLine.startX, previousLine.endX);
						int max = (int) Math.max(previousLine.startX, previousLine.endX);

						if(currentLine.startX > max && currentLine.endX <= max) {
							long currentDistance = Math.abs(currentLine.startX - max);
							minDistance = minDistance > currentDistance ? currentDistance : minDistance;
							isEnded = true;
						}
						else if(currentLine.startX < min && currentLine.endX >= min) {
							long currentDistance = Math.abs(min - currentLine.startX);
							minDistance = minDistance > currentDistance ? currentDistance : minDistance;
							isEnded = true;
						}
					} else {
						continue;
					}
				}
			} else { // 서로 수직인 경우
				if (currentLine.isVerticalToX) { // 현재 직선이 x축에 수직인 경우, x = a, b <= y <= c
					int min = (int) Math.min(previousLine.startX, previousLine.endX);
					int max = (int) Math.max(previousLine.startX, previousLine.endX);
					if (currentLine.startX >= min && currentLine.startX <= max) {
						int minC = (int) Math.min(currentLine.startY, currentLine.endY);
						int maxC = (int) Math.max(currentLine.startY, currentLine.endY);
						if (previousLine.startY >= minC && previousLine.startY <= maxC) {
							long currentDistance = Math.abs(currentLine.startY - previousLine.startY);
							minDistance = minDistance > currentDistance ? currentDistance : minDistance;
							isEnded = true;
						}
					}
				} else {
					int min = (int) Math.min(previousLine.startY, previousLine.endY);
					int max = (int) Math.max(previousLine.startY, previousLine.endY);
					if (currentLine.startY >= min && currentLine.startY <= max) {
						int minC = (int) Math.min(currentLine.startX, currentLine.endX);
						int maxC = (int) Math.max(currentLine.startX, currentLine.endX);
						if (previousLine.startX >= minC && previousLine.startX <= maxC) {
							long currentDistance = Math.abs(currentLine.startX - previousLine.startX);
							minDistance = minDistance > currentDistance ? currentDistance : minDistance;
							isEnded = true;
						}
					}
				}
			}
		}
		if(isEnded) {
			answer += minDistance;
			return true;
		}
		// 2. 직선이 범위를 벗어나는지 검사
		if (currentLine.endX > L || currentLine.endX < -L) {
			if (currentLine.endX > L)
				answer += Math.abs(currentLine.startX - L);
			else
				answer += Math.abs(currentLine.startX + L);

			answer++;
			return true;
		}
		if (currentLine.endY > L || currentLine.endY < -L) {
			if (currentLine.endY > L)
				answer += Math.abs(currentLine.startY - L);
			else
				answer += Math.abs(currentLine.startY + L);

			answer++;
			return true;
		}

		answer += t;
		return false;
	}
}
