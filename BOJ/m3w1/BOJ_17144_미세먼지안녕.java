package m3w1;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.util.ArrayList;

public class BOJ_17144_미세먼지안녕 {
	static int R, C, map[][], sum, temp[][];
	static ArrayList<Integer> AF;
	static int[] dx = { -1, 1, 0, 0 };
	static int[] dy = { 0, 0, -1, 1 };

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));

		String[] str = br.readLine().split(" ");
		R = Integer.parseInt(str[0]);
		C = Integer.parseInt(str[1]);
		int T = Integer.parseInt(str[2]);
		AF = new ArrayList<Integer>();
		map = new int[R][C];
		for (int i = 0; i < R; i++) {
			String[] str2 = br.readLine().split(" ");
			for (int j = 0; j < C; j++) {
				map[i][j] = Integer.parseInt(str2[j]);
				if (map[i][j] == -1)
					AF.add(i);
			}
		}

		int k = 0;
		while (k != T) {
			temp = new int[R][C];

			for (int i = 0; i < R; i++) {
				for (int j = 0; j < C; j++) {
					if (map[i][j] == -1)
						continue;
					spread(i, j);
				}
			}
			turnUp();
			turnDown();
			copyMap();
			k++;

		}
		
		for (int i = 0; i < R; i++) {
			for (int j = 0; j < C; j++) {
				if (map[i][j] == 0 || map[i][j]==-1) continue;
				sum+= map[i][j];
			}
		}

		bw.write(String.valueOf(sum));
		bw.flush();
		br.close();
		bw.close();
	}

	// 확산함수
	private static void spread(int i, int j) {
		int sp = map[i][j] / 5;
		int cnt = 0;

		for (int k = 0; k < 4; k++) {
			int nx = i + dx[k];
			int ny = j + dy[k];

			if (nx < 0 || nx >= R || ny < 0 || ny >= C)
				continue;
			if (map[nx][ny] == -1) {
				temp[nx][ny] = -1;
				continue;
			}
			cnt++;
			temp[nx][ny] += sp;
		}

		temp[i][j] += map[i][j] - sp * cnt;
	}

	// 순회함수
	private static void turnUp() {
		int af = AF.get(0);
		// n행1열 아래로 -1전행까지 당기기
		for (int i = af - 1; i > 0; i--) {
			temp[i][0] = temp[i - 1][0];
		}
		// 0행n열 왼쪽으로 시프트
		for (int i = 0; i < C - 1; i++) {
			temp[0][i] = temp[0][i + 1];
		}
		// n행C-1열 위로 쉬프트
		for (int i = 0; i < af; i++) {
			temp[i][C - 1] = temp[i + 1][C - 1];
		}
		// list.get 0 행 n열 오른쪽으로 시프트(1열부터하고 1열 0으로.)
		for (int i = C - 1; i > 1; i--) {
			temp[af][i] = temp[af][i - 1];
		}
		temp[af][1] = 0;
	}

	private static void turnDown() {
		int af = AF.get(1);
		for (int i = af+1; i < R-1; i++) {
			temp[i][0] = temp[i + 1][0];
		}
		for (int i = 0; i < C - 1; i++) {
			temp[R-1][i] = temp[R-1][i + 1];
		}
		for (int i = R-1; i > af; i--) {
			temp[i][C-1] = temp[i - 1][C-1];
		}
		for (int i = C - 1; i > 1; i--) {
			temp[af][i] = temp[af][i - 1];
		}
		temp[af][1] = 0;
	}

	private static void copyMap() {
		for (int i = 0; i < R; i++) {
			map[i] = temp[i].clone();
		}
	}
}
