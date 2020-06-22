public class kakao2020_블라인드채용_문자열압축 {

	public static void main(String[] args) {

		String s = "a";
		System.out.println(solution(s));
	}

	public static int solution(String s) {
		int answer = Integer.MAX_VALUE;
		int len = s.length();
		if(len==1) {
			return 1;
		}
		// 완탐
		// 찢을 개수
		for (int i = 1; i <= len / 2; i++) {
			int cnt = 0;
			int mok = len / i;
			int rem = len % i;
			cnt += rem;

			String standard = s.substring(0, i);
			String target="";
			int temp = 1;
			for (int j = 1; j <= mok; j++) {
				if (j != mok) {
					target = s.substring(j * i, (j + 1) * i);
					// 기준과 같으면
					if (standard.equals(target)) {
						temp++;
						continue;
					}
				}
				// 틀리면
				if (temp == 1) {
					cnt += i;
					standard = target;
				} else {
					cnt += (int) Math.log10(temp) + 1;
					cnt += i;
					temp = 1;
					standard = target;
				}

			}

			if (answer > cnt) {
				answer = cnt;
			}
		}

		return answer;
	}

}
