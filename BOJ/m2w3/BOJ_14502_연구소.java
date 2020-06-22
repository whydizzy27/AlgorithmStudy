package BaekJoon;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.LinkedList;
import java.util.Queue;

public class BOJ_14502_연구소 {
	static class Pair {
		int x;
		int y;

		public Pair(int x, int y) {
			this.x = x;
			this.y = y;
		}
	}

	static int max = 0;
	static int row;
	static int col;
	static int[][] map;
	static int[][] copymap;
	static int[] visited;
	static int[] dx = { -1, 1, 0, 0 };
	static int[] dy = { 0, 0, -1, 1 };

	public static void main(String[] args) throws IOException {
		// TODO Auto-generated method stub
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));

		String[] str = br.readLine().split(" ");
		row = Integer.parseInt(str[0]);
		col = Integer.parseInt(str[1]);
		map = new int[row][col];
		copymap = new int[row][col];

		// 입력
		for (int i = 0; i < row; i++) {
			String[] str2 = br.readLine().split(" ");
			for (int j = 0; j < col; j++) {
				map[i][j] = Integer.parseInt(str2[j]);
			}
		}

		setWall(0,0);

		bw.write(String.valueOf(max));

		bw.flush();
		br.close();
		bw.close();
	}

	public static void setWall(int start, int depth) {
		//2차원 배열 요소들에서 3개 조합 고르기
		if (depth == 3) {
			copy();
			bfs();
			findMax();
			return;
		}
		
		//2차원 배열에서 조합 뽑을 땐 이중포문 ㄴㄴ 이렇게 쓴다
		for (int i = start; i < row * col; i++) { // 전체 탐색 0 0 ~ row-1 col-1까지
			// 공식. 0부터 행x열 전까지 범위.
			int x = i / col;
			int y = i % col;
//		
			if (map[x][y] == 0) {
				map[x][y] = 1;
				setWall(i + 1, depth + 1);
				map[x][y] = 0;

			}

		}
	}

	public static void bfs() {
		int[][] check = new int[row][col];
		Queue<Integer> q = new LinkedList<>();

		for (int i = 0; i < row; i++) {
			for (int j = 0; j < col; j++) {
				if (copymap[i][j] == 1 || copymap[i][j] == 0 || (copymap[i][j] == 2 && check[i][j] == 1))
					continue;
				q.add(i);
				q.add(j);
				while (!q.isEmpty()) {
					int x = q.poll();
					int y = q.poll();
					check[x][y] = 1;

					for (int k = 0; k < 4; k++) {
						int _x = x + dx[k];
						int _y = y + dy[k];

						if (_x >= 0 && _y >= 0 && _x < row && _y < col) {
							if (copymap[_x][_y] == 0 && check[_x][_y] == 0) {
								copymap[_x][_y] = 2;
								check[_x][_y] = 1;
								q.add(_x);
								q.add(_y);

							}
						}
					}
				}
			}
		}
	}

//
	public static void copy() {
		// 복사본 생성
		for (int i = 0; i < row; i++) {
			for (int j = 0; j < col; j++) {
				copymap[i][j] = map[i][j];
			}
		}
	}

//
	public static void findMax() {
		// 안전구역 크기 도출
		int cnt = 0;
		for (int i = 0; i < row; i++) {
			for (int j = 0; j < col; j++) {
				if (copymap[i][j] == 0)
					cnt++;
			}
		}

		if (cnt > max)
			max = cnt;
	}

}
