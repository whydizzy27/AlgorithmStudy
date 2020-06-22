package BaekJoon;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.util.LinkedList;
import java.util.Queue;

public class BOJ_2178_�̷�Ž�� {
	static int row;
	static int col;
	static int[][] miro;
	static int[][] visited;
	//dx dy �����¿� �迭 �� ���� �̵� ���� �������
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
		//������ �Է�
		qx.add(x);
		qy.add(y);
		
		while(!qx.isEmpty()&&!qy.isEmpty()) { //ť�� �Ѵ� ��� Ż��
			x=qx.poll();
			y=qy.poll();
			visited[x][y]=1; 
			
			for(int i=0;i<4;i++) {
				int _x = x + dx[i]; //��
				int _y = y + dy[i]; //��
				//�ٲ� �� ������ �̷� �� ��ȿ����
				if(_x >= 0 && _y >= 0 && _x<row && _y<col) {
					//�� ���� 1���� �׸��� �װ��� �湮������ ������
					if(miro[_x][_y] == 1 && visited[_x][_y]==0) { //�ƿ� miro���� +1�� �ϰ� �����Ƿ� ���� visited���� Ȯ������ �ʿ�� ���� ��
						qx.add(_x);
						qy.add(_y);
						visited[_x][_y]=1; 
						miro[_x][_y]=miro[x][y]+1; //miro x y �� ���������� x y ��ġ���� ���µ� ���� ĭ��. ���� ������ �����ϹǷ� +1
					}
				}
			}
		}
	}

}
