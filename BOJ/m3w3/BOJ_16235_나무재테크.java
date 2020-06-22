package m3w3;



import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.util.ArrayList;
import java.util.Collections;
import java.util.LinkedList;
import java.util.Queue;
import java.util.StringTokenizer;

class Info{
	int x,y,age;

	public Info(int x, int y, int age) {
		this.x = x;
		this.y = y;
		this.age = age;
	}
	
}
public class BOJ_16235_나무재테크 {
	static int yangbunAdd[][],yangbun[][],cnt,N;
	static int[] dx = {-1,-1,0,1,1,1,0,-1};
	static int[] dy = {0,1,1,1,0,-1,-1,-1};
	static ArrayList<Integer>[][] treeMap;
	static Queue<Info> deadTree;
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
		
		StringTokenizer st = new StringTokenizer(br.readLine());
		N = Integer.parseInt(st.nextToken());
		int M = Integer.parseInt(st.nextToken());
		int K = Integer.parseInt(st.nextToken());
		
		treeMap = new ArrayList[N+1][N+1];
		deadTree = new LinkedList<Info>();
		
		for (int i = 1; i <= N; i++) {
			for (int j = 1; j <= N; j++) {
				treeMap[i][j]=new ArrayList<Integer>();
			}
		}
		yangbun =  new int[N+1][N+1];
		for (int i = 1; i <= N; i++) {
			for (int j = 1; j <= N; j++) {
				yangbun[i][j]=5;
			}
		}
		
		yangbunAdd =  new int[N+1][N+1];
		for (int i = 1; i <= N; i++) {
			st = new StringTokenizer(br.readLine());
			for (int j = 1; j <= N; j++) {
				yangbunAdd[i][j]=Integer.parseInt(st.nextToken());
			}
		}
		
		for (int i = 0; i < M; i++) {
			st = new StringTokenizer(br.readLine());
			treeMap[Integer.parseInt(st.nextToken())][Integer.parseInt(st.nextToken())].add(Integer.parseInt(st.nextToken()));
		}
		
		
		for (int i = 0; i < K; i++) {
			sortTree();
			springsummer();
			fall();
			winter();
		}
		
		howmanysurvive();
		bw.write(String.valueOf(cnt) + " ");
		bw.flush();
		br.close();
		bw.close();
	}
	

	private static void winter() {
		for (int i = 1; i <= N; i++) {
			for (int j = 1; j <= N; j++) {
				yangbun[i][j]+=yangbunAdd[i][j];
			}
		}
	}


	private static void fall() {
		for (int i = 1; i <= N; i++) {
			for (int j = 1; j <= N; j++) {
				
				for (int t : treeMap[i][j]) {
					if(t%5==0) {
						
						for (int u = 0; u < 8; u++) {
							int nx = i+dx[u];
							int ny = j+dy[u];
							
							if(nx>=1&&nx<=N&&ny>=1&&ny<=N) {
								treeMap[nx][ny].add(1);
							}
						}
					}
				}
			}
		}		
	}


	private static void sortTree() {
		for (int i = 1; i <= N; i++) {
			for (int j = 1; j <= N; j++) {
				Collections.sort(treeMap[i][j]);
			}
		}
	}


	private static void springsummer() {
		for (int i = 1; i <= N; i++) {
			for (int j = 1; j <= N; j++) {
				if(treeMap[i][j].size()==0) continue;
				
				for (int k = 0; k <treeMap[i][j].size(); k++) {
					
					if(yangbun[i][j]>=treeMap[i][j].get(k)) {
						yangbun[i][j]-=treeMap[i][j].get(k);
						treeMap[i][j].set(k, treeMap[i][j].get(k)+1);
					}
					else {
						deadTree.offer(new Info(i, j, treeMap[i][j].get(k)/2));
						treeMap[i][j].set(k, -1);
					}
				}
				
				//죽은나무 삭제
				for (int j2 = 0; j2 < treeMap[i][j].size(); j2++) {
					if(treeMap[i][j].get(j2)==-1) {
						treeMap[i][j].remove(j2);
						j2--;
					}
				}
			}
		}
		
		while(!deadTree.isEmpty()) {
			Info info = deadTree.poll();
			yangbun[info.x][info.y]+=info.age;
		}
		
		
	}

	private static void howmanysurvive() {
		for (int i = 1; i <= N; i++) {
			for (int j = 1; j <= N; j++) {
				cnt+=treeMap[i][j].size();
			}
		}
	}
	
}
