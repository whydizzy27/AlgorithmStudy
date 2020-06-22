package my.solve;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.util.Deque;
import java.util.LinkedList;
class Escape {
	int x, y, time;
	char c;

	public Escape(int x, int y, int time, char c) {
		this.x = x;
		this.y = y;
		this.time = time;
		this.c = c;
	}
}

/*
시간 줄일 수 있는 팁
만약 수연이가 지나온 곳의 자취를 없애게 바꾸면 수연이가 갈 수 있는 분신들이 생긴 꼴이게 되는데
bfs while 거듭할때마다 수연이가 D에 도착하기 전 && 아직 악마 손길이 점령할 구역 한참 남은 케이스에 수연이 분신들이 모두 잡혀버리면
굳이 악마 손길이 나머지 구역 먹을 필요도 없이 그냥 바로 리턴해서 게임오버 출력하면 시간 훨씬 절약된다.
그럼 while 할때 마다 수연이 수 세주는 코드 쓰고 조건문 걸어야겠지??



*/
public class SWEA_7793_오나의여신님 {
	static int R, C, time;
	static char map[][];
	static Deque<Escape> q;
	static int[] dx = { -1, 1, 0, 0 };
	static int[] dy = { 0, 0, -1, 1 };
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));

		int tn = Integer.parseInt(br.readLine());
		
		for (int tc = 1; tc <= tn; tc++) {
			
			String[] str = br.readLine().split(" ");
			R = Integer.parseInt(str[0]);
			C = Integer.parseInt(str[1]);

			map = new char[R][C];
			q = new LinkedList<>();
			for (int i = 0; i < R; i++) {
				String str2 = br.readLine();
				for (int j = 0; j < C; j++) {
					map[i][j] = str2.charAt(j);
					if (map[i][j] == '*') {
						q.offerFirst(new Escape(i, j, 0, '*'));
					} else if (map[i][j] == 'S') {
						q.offerLast(new Escape(i, j, 0, 'S'));
					}
				}
			}
			time=0;
			bfs();
			// 칵투스
			if (time == 0)
				bw.write("#" + tc + " GAME OVER\n");
			else
				bw.write("#" + tc + " " + String.valueOf(time) + "\n");
			
			
		}
		
		bw.flush();
		br.close();
		bw.close();
	}
	private static void bfs() {
		while (!q.isEmpty()) {
			Escape e = q.poll();
			
			if (e.c == 'D') {
				time = e.time;
				return;
			}

			for (int k = 0; k < 4; k++) {
				int nx = e.x + dx[k];
				int ny = e.y + dy[k];

				if (nx >= 0 && nx < R && ny >= 0 && ny < C) {
					if (e.c == '*') {
						if (map[nx][ny] != 'D' && map[nx][ny] != 'X' && map[nx][ny] != '*') {
							map[nx][ny] = '*';
							q.offer(new Escape(nx, ny, e.time + 1, '*'));
						}
					} else if (e.c == 'S') {
						if (map[nx][ny] != 'S' && map[nx][ny] != 'X' && map[nx][ny] != '*') {
							if (map[nx][ny] == 'D')
								q.offer(new Escape(nx, ny, e.time + 1, 'D'));
							else {
								map[nx][ny] = 'S';
								q.offer(new Escape(nx, ny, e.time + 1, 'S'));
							}
						}
					}
				}

			}
		}

	}

}
