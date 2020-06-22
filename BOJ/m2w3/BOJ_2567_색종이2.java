package BaekJoon;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;

public class BOJ_2567_색종이2 {
	static int[][] map;
	static int cnt = 0;
	static int[] dx = { -1, 1, 0, 0 };
	static int[] dy = { 0, 0, -1, 1 };

	public static void main(String[] args) throws IOException {
		// TODO Auto-generated method stub
		System.setIn(new FileInputStream("input.txt"));

		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));

		int n = Integer.parseInt(br.readLine());
		int ls = 0;
		int ds = 0;

		map = new int[100][100];

		for (int k = 0; k < n; k++) {
			String[] str2 = br.readLine().split(" ");
			ls = Integer.parseInt(str2[0]);// 열
			ds = Integer.parseInt(str2[1]);// 행

			// 어차피 정사각형이라 0,0위치 안뒤집어도됨
			for (int i = ds; i < ds + 10; i++) {
				for (int j = ls; j < ls + 10; j++) {
					map[i][j] = 1;
				}
			}

		}
		for (int i = 0; i < 100; i++) {
			for (int j = 0; j < 100; j++) {
				if (map[i][j] == 0)
					Solution(i, j);
				else
					Solution2(i, j);
			}
		}

		bw.write(String.valueOf(cnt) + "\n");

		bw.flush();
		br.close();
		bw.close();
	}

	public static void Solution(int x, int y) {

		for (int i = 0; i < 4; i++) {
			int nx = x + dx[i];
			int ny = y + dy[i];

			if (nx < 0 || nx >= 100 || ny < 0 || ny >= 100)
				continue;

			if (map[nx][ny] == 1)
				cnt++;
		}

	}

	public static void Solution2(int x, int y) {

		for (int i = 0; i < 4; i++) {
			int nx = x + dx[i];
			int ny = y + dy[i];

			if (nx < 0 || nx >= 100 || ny < 0 || ny >= 100)
				cnt++;

			
		}

	}

}
