package my.solve;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.util.ArrayList;
import java.util.LinkedList;
import java.util.Queue;
import java.util.StringTokenizer;

public class SWEA_4013_특이한자석 {
	static class Top{
		int no,dir;

		public Top(int no, int dir) {
			this.no = no;
			this.dir = dir;
		}
	}
	static int[][] map,turnInfo;
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));

		int tn = Integer.parseInt(br.readLine());
		
		for (int tc = 1; tc <= tn; tc++) {
			//회전수
			int K = Integer.parseInt(br.readLine());
			
			//톱니상태
			map = new int[5][8];
			StringTokenizer st;
			for (int i = 1; i <= 4; i++) {
				st =new StringTokenizer(br.readLine());
				for (int j = 0; j < 8; j++) {
					map[i][j]=Integer.parseInt(st.nextToken());
				}
			}
			
			//회전 정보 : 톱니번호, 회전방향 1시계우측,-1반시게좌측
			turnInfo = new int[K][2];
			
			for (int i = 0; i < K; i++) {
				st =new StringTokenizer(br.readLine());
				turnInfo[i][0]=Integer.parseInt(st.nextToken());
				turnInfo[i][1]=Integer.parseInt(st.nextToken());
			}
			
			Queue<Top> q = new LinkedList<>();
			for (int i = 0; i < K; i++) {
				int who =turnInfo[i][0];
				int dir =turnInfo[i][1];
				ArrayList<Top> list = new ArrayList<>();
				boolean[] visited = new boolean[5];
				visited[who]=true;
				//맞닿은곳 겹치는 지 검사 2번6번 겹침
				Top top = new Top(who, dir);
				q.offer(top);
				list.add(top);
				while(!q.isEmpty()) {
					Top t = q.poll();
					int tar = t.no;
					if(tar==1) {
						if(!visited[2]) {
							if(map[1][2]!=map[2][6]) {
								visited[2]=true;
								Top nt = new Top(2, -t.dir);
								q.offer(nt);
								list.add(nt);
							}
						}
					}else if(tar==4) {
						if(!visited[3]) {
							if(map[4][6]!=map[3][2]) {
								visited[3]=true;
								Top nt = new Top(3, -t.dir);
								q.offer(nt);
								list.add(nt);
							}
						}
					}else {
						if(!visited[tar-1]) {
							if(map[tar][6]!=map[tar-1][2]) {
								visited[tar-1]=true;
								Top nt = new Top(tar-1, -t.dir);
								q.offer(nt);
								list.add(nt);
							}
						}
						
						if(!visited[tar+1]) {
							if(map[tar][2]!=map[tar+1][6]) {
								visited[tar+1]=true;
								Top nt = new Top(tar+1, -t.dir);
								q.offer(nt);
								list.add(nt);
							}
						}
					}
				}
				//연쇄작용 전부확인.
				//로테이트
				for (Top t : list) {
					rotate(t.no,t.dir);
				}
			}
			
			int score=0;
			for (int i = 1; i <= 4; i++) {
				if(map[i][0]==1)
					score+=Math.pow(2, i-1);
			}
			bw.write("#" + tc + " " + String.valueOf(score) + "\n");
		}
		
		bw.flush();
		br.close();
		bw.close();
	}
	
	private static void rotate(int no, int dir) {
		//시게방향
		if(dir==1) {
			int temp = map[no][7];
			for (int i = 7; i >=1; i--) {
				map[no][i]=map[no][i-1];
			}
			map[no][0]=temp;
		}
		//반시게
		else {
			int temp = map[no][0];
			for (int i = 0; i < 7; i++) {
				map[no][i]=map[no][i+1];
			}
			map[no][7]=temp;
		}
	}
	
}
