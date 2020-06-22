package m3w1;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.util.LinkedList;
import java.util.Queue;

public class BOJ_1005_ACMCraft {
	static int[] buildTime, buildOrder[], dp;
	static int destination, N, K;

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));

		//주석 다는 습관 기르자
		
		int tn = Integer.parseInt(br.readLine());

		for (int tc = 0; tc < tn; tc++) {
			String[] str = br.readLine().split(" ");
			N = Integer.parseInt(str[0]);
			K = Integer.parseInt(str[1]);

			buildTime = new int[N + 1];

			String[] str2 = br.readLine().split(" ");

			// 빌드 시간
			for (int i = 1; i <= N; i++) {
				buildTime[i] = Integer.parseInt(str2[i - 1]);
			}

			// 빌드순서
			buildOrder = new int[K][2];
			
			//메모이제이션용 디피배열
			dp = new int[1001];
			
			//테케에 코스트 0짜리 있어서 dfs 내 dp 참조할때 dp !=0 하면 그 코스트 0짜리가 dp 호출이 안돼서 시간초과난다
			//그렇다고 dp != -1로 바꾸니까  int배열 디폴트가 0이므로 dfs 안타고 바로 0이 꺼내져버린다.
			//그래서 -1로 초기화
			for (int i = 0; i < 1001; i++) {
				dp[i] = -1;
			}
			
			//간선 정보 : 즉 그래프
			for (int i = 0; i < K; i++) {
				String[] str3 = br.readLine().split(" ");
				buildOrder[i][0] = Integer.parseInt(str3[0]);
				buildOrder[i][1] = Integer.parseInt(str3[1]);
			}

			//목적지
			destination = Integer.parseInt(br.readLine());

			//나는 목적지부터 시작했다. 두번째 테케 보니까 시작점이 무조건 1이란 보장 없었다.
			bw.write(String.valueOf(dfs(destination)) + "\n");
		}
		bw.flush();
		br.close();
		bw.close();
	}

	//초콜릿과 건포도 때 기억살려서 void형 dfs말고 int반환형 dfs 해보았다.
	//보통 void형을 선호하지만 dp를 사용하게 되면 dp특성상 void형 쓰기가 어려워서 int반환형을 쓰게 되었다.
	private static int dfs(int current) {

		//디피 값 저장된 거 있으면 그 값 리턴
		if (dp[current] != -1)
			return dp[current];

		int max = 0;
		//간선 정보 조회
		for (int i = 0; i < K; i++) {
			//목적지 스타트이므로 간선정보를 거꾸로 타고들어간다. ex 1 2면   2->1 이렇게
			if (current == buildOrder[i][1]) {
				//여러 갈래로 나뉘는 간선 중 가장 큰 값 골라주는 과정
				max = Math.max(max, dfs(buildOrder[i][0]));
			}
		}
		
		//dfs가 어디까지 가느냐는 더이상 타고올라갈 간선정보가 없을 때까지다.
		//타고 올라갈 간선 정보가 없다면 위 for문에서 dfs를 더이상 타지 못한다. 그래서 max는 그대로 0이 내려오고
		//마지막 노드 자체의 빌딩시간을 리턴해줘야하므로 max + 현재노드빌딩타임을 리턴했다.
		//이게 마지막 노드 경우와 아닌 경우 모두 포함한다.
		//dp에 값 저장 겸 리턴. 
		return dp[current] = max + buildTime[current];

	}

}
