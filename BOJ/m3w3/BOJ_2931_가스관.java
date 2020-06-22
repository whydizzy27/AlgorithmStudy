package m3w3;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.util.StringTokenizer;

class Pipe {
	int x, y;

	public Pipe(int x, int y) {
		this.x = x;
		this.y = y;
	}
}

public class BOJ_2931_가스관 {
	static int checkCnt, R, C;
	static int[][] map;
	static int answer;
	static int spotX, spotY;
	static Pipe[][] pipeInfo;
	static int[] dx = { -1, 0, 1, 0 };
	static int[] dy = { 0, 1, 0, -1 };
	static boolean isOver;

	public static void main(String[] args) throws IOException {
		System.setIn(new FileInputStream("input.txt"));
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));

		StringTokenizer st = new StringTokenizer(br.readLine());
		R = Integer.parseInt(st.nextToken());
		C = Integer.parseInt(st.nextToken());
		
		map = new int[R][C];
		int startX = 0;
		int startY = 0;
		for (int i = 0; i < R; i++) {
			String s = br.readLine();
			for (int j = 0; j < C; j++) {
				char c = s.charAt(j);
				if (c == '.') {
					map[i][j] = 0;
				} else if (c == 'M') {
					map[i][j] = 8;
					checkCnt++;
					startX = i;
					startY = j;
				} else if (c == 'Z') {
					map[i][j] = 9;
					checkCnt++;
				} else if (c == '|') {
					map[i][j] = 6;
					checkCnt++;
				} else if (c == '-') {
					map[i][j] = 5;
					checkCnt++;
				} else if (c == '+') {
					map[i][j] = 7;
					checkCnt+=2;
				} else if (c == '1') {
					map[i][j] = 1;
					checkCnt++;
				} else if (c == '2') {
					map[i][j] = 2;
					checkCnt++;
				} else if (c == '3') {
					map[i][j] = 3;
					checkCnt++;
				} else if (c == '4') {
					map[i][j] = 4;
					checkCnt++;
				}
			}
		}

		pipeInfo = new Pipe[7][2];

		pipeInfo[1][0] = new Pipe(0, 1);
		pipeInfo[1][1] = new Pipe(3, 2);
		pipeInfo[2][0] = new Pipe(2, 1);
		pipeInfo[2][1] = new Pipe(3, 0);
		pipeInfo[3][0] = new Pipe(1, 0);
		pipeInfo[3][1] = new Pipe(2, 3);
		pipeInfo[4][0] = new Pipe(1, 2);
		pipeInfo[4][1] = new Pipe(0, 3);
		pipeInfo[5][0] = new Pipe(1, 1);
		pipeInfo[5][1] = new Pipe(3, 3);
		pipeInfo[6][0] = new Pipe(0, 0);
		pipeInfo[6][1] = new Pipe(2, 2);

		//System.out.println("기준 :" + checkCnt);
		for (int i = 0; i < 4; i++) {
			dfs(startX, startY, i, 1, 1, 1, 1, false);
		}

		char cc = '0';
		if (answer == 1)
			cc = '1';
		else if (answer == 2)
			cc = '2';
		else if (answer == 3)
			cc = '3';
		else if (answer == 4)
			cc = '4';
		else if (answer == 5)
			cc = '-';
		else if (answer == 6)
			cc = '|';
		else if (answer == 7)
			cc = '+';

		bw.write(String.valueOf(spotX) + " " + String.valueOf(spotY) + " " + String.valueOf(cc));

		bw.flush();
		br.close();
		bw.close();
	}

	private static void dfs(int x, int y, int dir, int ans, int cnt, int ansX, int ansY, boolean meet) {
		//System.out.println(x+" "+y);
		// 반환시 모든경우 불린으로 리턴
		if (isOver)
			return;
		// Z도착과 개수 맞으면 값 저장, 리턴
		if (map[x][y] == 9) {
			if(ans==7) {
				if(cnt-2==checkCnt) {
					spotX = ansX+1;
					spotY = ansY+1;
					answer = ans;
					isOver = true;
					return;
				}
				else {
					return;
				}
			}
			else {
				if(cnt-1==checkCnt) {
					spotX = ansX+1;
					spotY = ansY+1;
					answer = ans;
					isOver = true;
					return;
				}
				else {
					return;
				}
			}
			
		}
		
		// 자기 자리가 .이면 리턴
		

		int nx = x + dx[dir];
		int ny = y + dy[dir];
		// 범위밖이면 리턴
		if (nx < 0 || nx >= R || ny < 0 || ny >= C)
			return;

		// 빈 곳 발견시
		if (map[nx][ny] == 0 && !meet) {

			for (int i = 1; i < 7; i++) {
				map[nx][ny] = i;
				for (int j = 0; j < 2; j++) {
					if (pipeInfo[i][j].x == dir) {
					//	System.out.println("선택루트: "+i +" 좌표"+nx+" "+ny);
						dfs(nx, ny, pipeInfo[i][j].y, i, cnt + 1, nx, ny, true);
					}
				}
			}
			// 7일때
			map[nx][ny] = 7;
			//System.out.println("선택루트: "+7 +" 좌표"+nx+" "+ny);
			dfs(nx, ny, dir, 7, cnt + 1, nx, ny, true);

		} else if (map[nx][ny] == 0 && meet) {
			return;
		}

		else if (map[nx][ny] == 9) {
			dfs(nx, ny, dir, ans, cnt + 1, ansX, ansY, meet);
		} else if (map[nx][ny] == 8) {
			return;
		}
		else {
			// 다음파이프가 +면
			if (map[nx][ny] == 7) {
				dfs(nx, ny, dir, ans, cnt + 1, ansX, ansY, meet);
				// 아니면
			} else {
				for (int i = 0; i < 2; i++) {
					if (pipeInfo[map[nx][ny]][i].x == dir) {
						dfs(nx, ny, pipeInfo[map[nx][ny]][i].y, ans, cnt + 1, ansX, ansY, meet);
					}
				}
			}
		}

	}
}
