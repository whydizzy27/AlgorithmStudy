package BaekJoon;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.util.LinkedList;
import java.util.Stack;

public class BOJ_15685_�巡��Ŀ�� {
	static LinkedList<Integer> list;
	static int startX,startY,startD,gen;
	static int[] dx = {0,-1,0,1};
	static int[] dy = {1,0,-1,0};
	static boolean[][] visited;
	static int cnt;
	public static void main(String[] args) throws IOException {
		// TODO Auto-generated method stub
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
		//Ŀ�� ����
		int N = Integer.parseInt(br.readLine());
		visited = new boolean[101][101];

		for (int i = 0; i < N; i++) {
			String[] str = br.readLine().split(" ");
			
			//���� ��ǥ ���� ���� ����
			startY =  Integer.parseInt(str[0]);
			startX =  Integer.parseInt(str[1]);
			startD =  Integer.parseInt(str[2]);
			gen =  Integer.parseInt(str[3]);
			
			//���� ���� ����Ʈ
			//���� ���� ��� �׿� ���� ������ǥ ������ ����
			list = new LinkedList<Integer>();
			makeDir();
			marking();
			
		}
		find();
		bw.write(String.valueOf(cnt));
		bw.flush();
		br.close();
		bw.close();
	}
	
	public static void makeDir() {
		//���� ���� �Է�
		list.add(startD);
		
		//���� ����ŭ �ݺ�
		for (int i = 0; i < gen; i++) {
			
			//������ �ݽð�������� ���ϰ� ������ �ݴ�.
			for (int j = list.size()-1; j >=0 ; j--) {
				list.add((list.get(j)+1)%4);
			}
			
		}
	}
	
	//���� ��ǥ ��� �������鼭 ��ŷ
	public static void marking() {
		visited[startX][startY]=true;
		
		for (int i = 0; i < list.size(); i++) {
			
			startX += dx[list.get(i)];
			startY += dy[list.get(i)];
			
			visited[startX][startY]=true;

		}
	}
	
	public static void find() {
		for (int i = 0; i < 100; i++) {
			for (int j = 0; j < 100; j++) {
				if(visited[i][j]&&visited[i+1][j]&&visited[i][j+1]&&visited[i+1][j+1])
					cnt++;
			}			
		}
	}

}
