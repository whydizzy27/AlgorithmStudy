package m3w3;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.util.Arrays;
import java.util.Comparator;
import java.util.StringTokenizer;

public class BOJ_17143_낚시왕 {
	static int[][] map, shark,temp;
	static int[] dx = { 0,-1, 1, 0, 0 };
	static int[] dy = { 0,0, 0, 1, -1 };
	static int N, M, K;
	static int sum;

	public static void main(String[] args) throws IOException {
		System.setIn(new FileInputStream("input.txt"));
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));

		StringTokenizer st = new StringTokenizer(br.readLine());
		N = Integer.parseInt(st.nextToken());
		M = Integer.parseInt(st.nextToken());
		K = Integer.parseInt(st.nextToken());

		map = new int[N + 1][M + 1];
		shark = new int[K + 1][5];
		for (int i = 1; i <= K; i++) {
			st = new StringTokenizer(br.readLine());
			shark[i][0] =Integer.parseInt(st.nextToken());
			shark[i][1] =Integer.parseInt(st.nextToken());
			map[shark[i][0]][shark[i][1]] = i;
			shark[i][2] = Integer.parseInt(st.nextToken());
			shark[i][3] = Integer.parseInt(st.nextToken());
			shark[i][4] = Integer.parseInt(st.nextToken());
		}

		for (int k = 1; k <= M; k++) {
//			System.out.println("전");
//			for (int j = 1; j <= N; j++) {
//				for (int j2 = 1; j2 <= M; j2++) {
//					System.out.print(map[j][j2]);
//				}
//				System.out.println();
//			}
//			System.out.println();
			temp = new int[N + 1][M + 1];
			catchFish(k);
//			System.out.println("후");
//			for (int j = 1; j <= N; j++) {
//				for (int j2 = 1; j2 <= M; j2++) {
//					System.out.print(map[j][j2]);
//				}
//				System.out.println();
//			}
//			System.out.println();
//			System.out.println(sum);
			for (int i = 1; i <= K; i++) {
				if(shark[i][0]==-1 ) continue;
				map[shark[i][0]][shark[i][1]]=0;
				sharkMove(i);
			}
			for (int i = 1; i <= K; i++) {
				if(shark[i][0]==-1 ) continue;
				map[shark[i][0]][shark[i][1]]=i;
			}
//			System.out.println("회전후");
//			for (int j = 1; j <= N; j++) {
//				for (int j2 = 1; j2 <= M; j2++) {
//					System.out.print(map[j][j2]);
//				}
//				System.out.println();
//			}
//			System.out.println();
		}

		bw.write(String.valueOf(sum));
		bw.flush();
		br.close();
		bw.close();
	}

	private static void sharkMove(int idx) {
		
		int nx = shark[idx][0];
		int ny = shark[idx][1];
		int dir = shark[idx][3];
		int cnt = shark[idx][2];
		while(cnt!=0) {
			nx += dx[dir];
			ny += dy[dir];
			
			if(nx<1||nx>N||ny<1||ny>M) {
				nx -= dx[dir];
				ny -= dy[dir];				
				if(dir==1) dir=2;
				else if(dir==2) dir=1;
				else if(dir==3) dir=4;
				else dir=3;
				continue;
			}
			
			cnt--;
		}
		
		if(temp[nx][ny]==0) {
			temp[nx][ny]=idx;
			shark[idx][0]=nx;
			shark[idx][1]=ny;
			shark[idx][3]=dir;
		}
		else{
			if(shark[idx][4]>shark[temp[nx][ny]][4]) {
				shark[temp[nx][ny]][0]=-1;
				temp[nx][ny]=idx;
				shark[idx][0]=nx;
				shark[idx][1]=ny;
				shark[idx][3]=dir;
			}
			else if(shark[idx][4]<shark[temp[nx][ny]][4]){
				shark[idx][0]=-1;
			}
			
		}
		
		
	}

	private static void catchFish(int col) {
		for (int row = 1; row <= N; row++) {
			if (map[row][col] == 0)
				continue;
			sum += shark[map[row][col]][4];
			shark[map[row][col]][0]=-1;
			map[row][col]=0;
			return;
		}
	}

}
