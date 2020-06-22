package BaekJoon;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.util.LinkedList;
import java.util.Queue;

public class BOJ_2178_미로탐색 {
	static int row;
	static int col;
	static int[][] miro;
	static int[][] visited;
	//dx dy 상하좌우 배열 행 열과 이동 기준 맞춰야함
	static int[] dx = {-1,1,0,0};
	static int[] dy = {0,0,-1,1};

	public static void main(String[] args) throws IOException{
		// TODO Auto-generated method stub
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
		String[] str = br.readLine().split(" ");
		row = Integer.parseInt(str[0]);
		col = Integer.parseInt(str[1]);
		miro = new int[100][100];
		visited = new int[100][100];
		for(int i=0;i<row;i++) {
			String[] str2 = br.readLine().split("");
			for(int j=0;j<col;j++) {
				miro[i][j]=Integer.parseInt(str2[j]);
			}			
		}
		bfs(0,0);
		
		bw.write(String.valueOf(miro[row-1][col-1]));
	
		bw.flush();
		br.close();
		bw.close();
	}
	
	public static void bfs(int x,int y) {
		Queue<Integer> qx = new LinkedList<Integer>();
		Queue<Integer> qy = new LinkedList<Integer>();
		//시작점 입력
		qx.add(x);
		qy.add(y);
		
		while(!qx.isEmpty()&&!qy.isEmpty()) { //큐가 둘다 비면 탈출
			x=qx.poll();
			y=qy.poll();
			visited[x][y]=1; 
			
			for(int i=0;i<4;i++) {
				int _x = x + dx[i]; //행
				int _y = y + dy[i]; //열
				//바뀐 후 범위가 미로 내 유효한지
				if(_x >= 0 && _y >= 0 && _x<row && _y<col) {
					//갈 곳이 1인지 그리고 그곳을 방문한적이 없는지
					if(miro[_x][_y] == 1 && visited[_x][_y]==0) { //아예 miro값을 +1씩 하고 있으므로 굳이 visited까지 확인해줄 필요는 없는 듯
						qx.add(_x);
						qy.add(_y);
						visited[_x][_y]=1; 
						miro[_x][_y]=miro[x][y]+1; //miro x y 는 시작점부터 x y 위치까지 가는데 지난 칸수. 도착 시작점 포함하므로 +1
					}
				}
			}
		}
	}

}
