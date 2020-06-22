package m5w1;



import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.util.Arrays;
import java.util.Comparator;
import java.util.StringTokenizer;

public class BOJ_17136_색종이붙이기 {
	static int map[][],min=Integer.MAX_VALUE;
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
		
		map = new int[10][10];
		
		for (int i = 0; i < 10; i++) {
			StringTokenizer st = new StringTokenizer(br.readLine());
			for (int j = 0; j < 10; j++) {
				map[i][j]=Integer.parseInt(st.nextToken());
			}
		}
		
		dfs(5,5,5,5,5,0);
		bw.write(min==Integer.MAX_VALUE?String.valueOf(-1):String.valueOf(min));
		bw.flush();
		br.close();
		bw.close();
	}
	
	private static void dfs(int one, int two, int threee, int four, int five, int use) {
		//5
		//4
		//3
		//2
		//1
		//전부 사용 불가인데 아직 1 남았으면 리턴
	}

}
