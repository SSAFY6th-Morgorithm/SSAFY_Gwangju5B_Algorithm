import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.HashMap;
import java.util.Map;
import java.util.StringTokenizer;

public class BOJ5373_큐빙 {
    public static void main(String[] args) throws IOException {
        BufferedReader input = new BufferedReader(new InputStreamReader(System.in));
        int T = Integer.parseInt(input.readLine());
        StringBuilder answer = new StringBuilder();
        Map<Character, char[][]> cube;
        StringTokenizer st;
        for (int t = 0; t < T; t++) {
            int n = Integer.parseInt(input.readLine());
            st = new StringTokenizer(input.readLine());
            cube = new HashMap<>();
            cube.put('U', new char[][]{{'w', 'w', 'w'}, {'w', 'w', 'w'}, {'w', 'w', 'w'}});
            cube.put('D', new char[][]{{'y', 'y', 'y'}, {'y', 'y', 'y'}, {'y', 'y', 'y'}});
            cube.put('F', new char[][]{{'r', 'r', 'r'}, {'r', 'r', 'r'}, {'r', 'r', 'r'}});
            cube.put('B', new char[][]{{'o', 'o', 'o'}, {'o', 'o', 'o'}, {'o', 'o', 'o'}});
            cube.put('L', new char[][]{{'g', 'g', 'g'}, {'g', 'g', 'g'}, {'g', 'g', 'g'}});
            cube.put('R', new char[][]{{'b', 'b', 'b'}, {'b', 'b', 'b'}, {'b', 'b', 'b'}});
            while (st.hasMoreTokens()) {
                rotate(st.nextToken(), cube);

            }
            char[][] up = cube.get('U');
            for (int r = 0; r < 3; r++) {
                for (int c = 0; c < 3; c++)
                    answer.append(up[r][c]);
                answer.append("\n");
            }
        }
        System.out.println(answer);
    }

    static void rotate(String command, Map<Character, char[][]> cube) {
        char side = command.charAt(0);
        char direction = command.charAt(1);
        rotate(side, direction, cube);
        if (side == 'U') {
            char[][] left = cube.get('L');
            char[][] front = cube.get('F');
            char[][] right = cube.get('R');
            char[][] back = cube.get('B');

            if (direction == '+') {
                char[] tmp = left[0];
                left[0] = front[0];
                front[0] = right[0];
                right[0] = back[0];
                back[0] = tmp;
            } else {
                char[] tmp = back[0];
                back[0] = right[0];
                right[0] = front[0];
                front[0] = left[0];
                left[0] = tmp;
            }

            cube.put('L', left);
            cube.put('F', front);
            cube.put('R', right);
            cube.put('B', back);
        } else if (side == 'D') {
            char[][] left = cube.get('L');
            char[][] front = cube.get('F');
            char[][] right = cube.get('R');
            char[][] back = cube.get('B');

            if (direction == '+') {
                char[] tmp = back[2];
                back[2] = right[2];
                right[2] = front[2];
                front[2] = left[2];
                left[2] = tmp;
            } else {
                char[] tmp = left[2];
                left[2] = front[2];
                front[2] = right[2];
                right[2] = back[2];
                back[2] = tmp;
            }

            cube.put('L', left);
            cube.put('F', front);
            cube.put('R', right);
            cube.put('B', back);
        } else if (side == 'L') {
            char[][] up = cube.get('U');
            char[][] front = cube.get('F');
            char[][] down = cube.get('D');
            char[][] back = cube.get('B');

            if (direction == '+') {
                char tmp1 = up[0][0], tmp2 = up[1][0], tmp3 = up[2][0];
                up[0][0] = back[2][2]; up[1][0] = back[1][2]; up[2][0] = back[0][2];
                back[0][2] = down[2][0]; back[1][2] = down[1][0]; back[2][2] = down[0][0];
                down[0][0] = front[0][0]; down[1][0] = front[1][0]; down[2][0] = front[2][0];
                front[0][0] = tmp1; front[1][0] = tmp2; front[2][0] = tmp3;
            } else {
                char tmp1 = up[0][0], tmp2 = up[1][0], tmp3 = up[2][0];
                up[0][0] = front[0][0]; up[1][0] = front[1][0]; up[2][0] = front[2][0];
                front[0][0] = down[0][0]; front[1][0] = down[1][0]; front[2][0] = down[2][0];
                down[0][0] = back[2][2]; down[1][0] = back[1][2]; down[2][0] = back[0][2];
                back[0][2] = tmp3; back[1][2] = tmp2; back[2][2] = tmp1;
            }

            cube.put('U', up);
            cube.put('F', front);
            cube.put('D', down);
            cube.put('B', back);
        } else if (side == 'R'){
            char[][] up = cube.get('U');
            char[][] front = cube.get('F');
            char[][] down = cube.get('D');
            char[][] back = cube.get('B');

            if(direction == '+') {
                char tmp1 = up[0][2], tmp2 = up[1][2], tmp3 = up[2][2];
                up[0][2] = front[0][2]; up[1][2] = front[1][2]; up[2][2] = front[2][2];
                front[0][2] = down[0][2]; front[1][2] = down[1][2]; front[2][2] = down[2][2];
                down[0][2] = back[2][0]; down[1][2] = back[1][0]; down[2][2] = back[0][0];
                back[0][0] = tmp3; back[1][0] = tmp2; back[2][0] = tmp1;
            } else {
                char tmp1 = up[0][2], tmp2 = up[1][2], tmp3 = up[2][2];
                up[2][2] = back[0][0]; up[1][2] = back[1][0]; up[0][2] = back[2][0];
                back[0][0] = down[2][2]; back[1][0] = down[1][2]; back[2][0] = down[0][2];
                down[0][2] = front[0][2]; down[1][2] = front[1][2]; down[2][2] = front[2][2];
                front[0][2] = tmp1; front[1][2] = tmp2; front[2][2] = tmp3;
            }

            cube.put('U', up);
            cube.put('F', front);
            cube.put('D', down);
            cube.put('B', back);
        } else if(side == 'F') {
            char[][] up = cube.get('U');
            char[][] right = cube.get('R');
            char[][] down = cube.get('D');
            char[][] left = cube.get('L');

            if(direction == '+') {
                char tmp1 = up[2][0], tmp2 = up[2][1], tmp3 = up[2][2];
                up[2][0] = left[2][2]; up[2][1] = left[1][2]; up[2][2] = left[0][2];
                left[0][2] = down[0][0]; left[1][2] = down[0][1]; left[2][2] = down[0][2];
                down[0][0] = right[2][0]; down[0][1] = right[1][0]; down[0][2] = right[0][0];
                right[0][0] = tmp1; right[1][0] = tmp2; right[2][0] = tmp3;
            } else {
                char tmp1 = up[2][0], tmp2 = up[2][1], tmp3 = up[2][2];
                up[2][0] = right[0][0]; up[2][1] = right[1][0]; up[2][2] = right[2][0];
                right[0][0] = down[0][2]; right[1][0] = down[0][1]; right[2][0] = down[0][0];
                down[0][0] = left[0][2]; down[0][1] = left[1][2]; down[0][2] = left[2][2];
                left[0][2] = tmp3; left[1][2] = tmp2; left[2][2] = tmp1;
            }

            cube.put('U', up);
            cube.put('R', right);
            cube.put('D', down);
            cube.put('L', left);
        } else if(side == 'B') {
            char[][] up = cube.get('U');
            char[][] right = cube.get('R');
            char[][] down = cube.get('D');
            char[][] left = cube.get('L');

            if(direction == '+') {
                char tmp1 = up[0][0], tmp2 = up[0][1], tmp3 = up[0][2];
                up[0][0] = right[0][2]; up[0][1] = right[1][2]; up[0][2] = right[2][2];
                right[0][2] = down[2][2]; right[1][2] = down[2][1]; right[2][2] = down[2][0];
                down[2][0] = left[0][0]; down[2][1] = left[1][0]; down[2][2] = left[2][0];
                left[0][0] = tmp3; left[1][0] = tmp2; left[2][0] = tmp1;
            } else {
                char tmp1 = up[0][0], tmp2 = up[0][1], tmp3 = up[0][2];
                up[0][0] = left[2][0]; up[0][1] = left[1][0]; up[0][2] = left[0][0];
                left[0][0] = down[2][0]; left[1][0] = down[2][1]; left[2][0] = down[2][2];
                down[2][0] = right[2][2]; down[2][1] = right[1][2]; down[2][2] = right[0][2];
                right[0][2] = tmp1; right[1][2] = tmp2; right[2][2] = tmp3;
            }
            cube.put('U', up);
            cube.put('R', right);
            cube.put('D', down);
            cube.put('L', left);
        }
    }

    static void rotate(char side, char direction, Map<Character, char[][]> cube) {
        char[][] current = cube.get(side);
        char[][] tmp = new char[3][3];
        if (direction == '+') {
            for (int r = 0; r < 3; r++) {
                for (int c = 0; c < 3; c++) {
                    tmp[c][2 - r] = current[r][c];
                }
            }
        } else {
            for (int r = 0; r < 3; r++) {
                for (int c = 0; c < 3; c++) {
                    tmp[2 - c][r] = current[r][c];
                }
            }
        }
        cube.put(side, tmp);
    }
}
