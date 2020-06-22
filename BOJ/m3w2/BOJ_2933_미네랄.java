package m3w2;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.util.Arrays;
import java.util.Collections;
import java.util.LinkedList;

class Pot implements Comparable<Pot> {
	int x, y;

	public Pot(int x, int y) {
		this.x = x;
		this.y = y;
	}

	@Override
	public int compareTo(Pot o) {
		return o.x - this.x;
	}
}

public class BOJ_2933_미네랄 {
	static char[][] map;
	static int R, C, max;
	static boolean[][] visited;
	static int[] dx = { -1, 1, 0, 0 };
	static int[] dy = { 0, 0, -1, 1 };
	static LinkedList<Pot> list,floor;

	public static void main(String[] args) throws IOException {
		System.setIn(new FileInputStream("input.txt"));
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));

		String[] str = br.readLine().split(" ");

		R = Integer.parseInt(str[0]);
		C = Integer.parseInt(str[1]);

		map = new char[R][C];
		for (int i = 0; i < R; i++) {
			String s = br.readLine();
			for (int j = 0; j < C; j++) {
				map[i][j] = s.charAt(j);
			}
		}

		int N = Integer.parseInt(br.readLine());
		String[] str2 = br.readLine().split(" ");

		int[] height = new int[N];

		for (int i = 0; i < N; i++) {
			height[i] = Integer.parseInt(str2[i]);
		}
//		System.out.println(Arrays.toString(height));
		int th = 0;
		while (th != N) {
//			System.out.println(th);
			boolean check = false;
			// 붕괴
			if (th % 2 == 0)
				destroyLeft(height[th]);
			else 
				destroyRight(height[th]);

			visited = new boolean[R][C];
			// 낙하할 거 있나 검사
			// 없으면 진행
			// 있으면 낙하

			L: for (int i = 0; i < R; i++) {
				for (int j = 0; j < C; j++) {
					if (map[i][j] == '.' || visited[i][j])
						continue;
					list = new LinkedList<>();
					max = -1;
					visited[i][j] = true;
					dfs(i, j);
					if (max == R - 1)
						continue;
					else {
//						System.out.println("최소:"+max);
						check = true;
						break L;
					}
				}
			}
			if (check) {
//				System.out.println(list.get(0).x+" "+list.get(0).y + "리스트사이즈");
				Collections.sort(list);
				floor = new LinkedList<>();

				for (Pot p : list) {
					if (map[p.x + 1][p.y] == '.')
						floor.add(p);
				}
				down();
//				for (int o = 0; o < R; o++) {
//					System.out.println(map[o]);
//				}
//				System.out.println("--------이 위다----------");
				
			}
			th++;
			
		}
//		System.out.println(floor.size());
		for (int i = 0; i < R; i++) {
			bw.write(String.valueOf(map[i]) + "\n");
		}
		bw.flush();
		br.close();
		bw.close();
	}

	private static void down() {
		
		L:while (true) {
			for (Pot p : floor) {
				if(p.x+1 == R) {
//					System.out.println("R");
					break L;
				}
				if(map[p.x+1][p.y] == 'x') {
//					System.out.println("x");
					break L;
				}
			}
			
			for (Pot p : list) {
				map[p.x + 1][p.y] = 'x';
				map[p.x][p.y] = '.';
				p.x+=1;
			}
			//한칸씩 내려갔으니 좌표값 더해주기
			
		}
	}

	private static void dfs(int i, int j) {
		if (max < i)
			max=i;
		
		
		list.add(new Pot(i, j));
		// min값 게속 갱신
		for (int k = 0; k < 4; k++) {
			int nx = i + dx[k];
			int ny = j + dy[k];

			if (nx >= 0 && nx < R && ny >= 0 && ny < C)
				if (map[nx][ny] == 'x' && !visited[nx][ny]) {
					visited[nx][ny] = true;
					dfs(nx, ny);
				}
		}
	}

	private static void destroyRight(int h) {
		int col = C - 1;
		while (col != -1) {
			if (map[R - h][col] == 'x') {
				map[R - h][col] = '.';
//				for (int i = 0; i < R; i++) {
//					System.out.println(map[i]);
//				}
//				System.out.println("---------------------");	
				return;
			}

			col--;
		}
		
	}

	private static void destroyLeft(int h) {
		int col = 0;
		while (col != C) {
			if (map[R - h][col] == 'x') {
				map[R - h][col] = '.';
//				for (int i = 0; i < R; i++) {
//					System.out.println(map[i]);
//				}
//				System.out.println("---------------------");
				return;
			}

			col++;
		}
		
	}

}
