package m3w1;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.util.ArrayList;
import java.util.Arrays;

public class BOJ_2239_스도쿠 {
	static int[][] map;
	static boolean[][] col, row, part;
	static boolean isOver;

	public static void main(String[] args) throws IOException {
		System.setIn(new FileInputStream("input.txt"));

		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));

		map = new int[9][9];
		// 열 체크
		col = new boolean[9][10];
		// 행 체크
		row = new boolean[9][10];
		// 사각형 체크
		part = new boolean[9][10];

		for (int i = 0; i < 9; i++) {
			String str = br.readLine();
			for (int j = 0; j < 9; j++) {
				map[i][j] = str.charAt(j) - '0';
				if (map[i][j] != 0) {
					row[i][map[i][j]] = true;
					col[j][map[i][j]] = true;
					// 이 공식대로 하면 사각형 파트 0~8 체크 가능
					part[(i / 3) * 3 + (j / 3)][map[i][j]] = true;
				}
			}
		}
		// 0~80 탐색할 것임. 0부터 스타트
		dfs(0);

		// 출력
//		for (int i = 0; i < 9; i++) {
//			for (int j = 0; j < 9; j++) {
//				bw.write(String.valueOf(map[i][j]));
//			}
//			bw.write("\n");
//		}

		bw.flush();
		br.close();
		bw.close();
	}

	private static void dfs(int idx) {
		if(isOver)
			return;

		if (idx == 81) {
			isOver=true;
			for (int i = 0; i < 9; i++) {
				for (int j = 0; j < 9; j++) {
					System.out.print(String.valueOf(map[i][j]));
				}
				System.out.println();
			}
			System.out.println();
			return;
		}
		int i = idx / 9;
		int j = idx % 9;
		int n = (i / 3) * 3 + (j / 3);
		if (map[i][j] != 0)
			dfs(idx + 1);
		else {
			for (int k = 1; k <= 9; k++) {
				// 열 행 사각형 중 겹치는 거 있을 시 다른 수 탐색
				if (row[i][k] || col[j][k] || part[n][k])
					continue;

				map[i][j] = k;
				row[i][k] = true;
				col[j][k] = true;
				part[n][k] = true;
				dfs(idx + 1);
				map[i][j] = 0;
				row[i][k] = false;
				col[j][k] = false;
				part[n][k] = false;
			}
		}
	}

}
