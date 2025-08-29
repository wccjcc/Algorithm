// 무선충전 (모의SW역량테스트)
// 아이디어 : 문제에서 제시된 맨해튼거리를 이용하여 각 시각에 고를 수 있는 무선충전기의 리스트를 가지고 
//            완전탐색하여 best를 골라냈습니다.
import java.io.*;
import java.util.*;

public class Solution_5644_정우주 {
    //방향 벡터 (아무것도 하지않음,상,우,하,좌)
    static final int[][] dir = {{0,0},{-1,0},{0,1}, {1,0},{0,-1}};
    //맨해튼거리 구하는 메서드
    static int dist(int r1,int c1,int r2,int c2){
        return Math.abs(r1-r2) + Math.abs(c1-c2);
    }
    //특정 시간대 가장 큰 충전량 구하는 메서드
    static int bestSum(int ai,int aj,int bi,int bj,
                        int[] bx,int[] by,int[] cc,int[] pp,int A){
        int best = 0; 
        //사용자 A가 -1 ~ A-1까지 반복 (-1은 선택하지 않는경우)
        for (int i = -1; i < A; i++) {
            //무언갈 선택하긴하는데 그게 범위에 안들어가있으면 넘어가기
            if (i != -1 && dist(ai,aj, by[i]-1, bx[i]-1) > cc[i]) continue;
            //사용가 B가 -1 ~ A-1까지 반복
            for (int j = -1; j < A; j++) {
                //무언갈 선택하긴하는데 그게 범위에 안들어가있으면 넘어가기
                if (j != -1 && dist(bi,bj, by[j]-1, bx[j]-1) > cc[j]) continue;
                
                int gain;//얻은 충전량을 담을 변수
                //둘다 아무것도 선택하지 않는경우
                if (i == -1 && j == -1)      gain = 0; 
                //A만 아무것도 선택하지 않는 경우
                else if (i == -1)            gain = pp[j];
                //B만 아무것도 선택하지 않는 경우
                else if (j == -1)            gain = pp[i];
                //A랑 B가 같은것을 선택하는 경우
                else if (i == j)             gain = pp[i];
                //A와 B가 다른것을 선택하는 경우
                else                         gain = pp[i] + pp[j];
        
                best = Math.max(best, gain); //max갱신하기
            }
        }
        return best;
    }

    public static void main(String[] args) throws IOException{
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringBuilder out = new StringBuilder();
        int T = Integer.parseInt(br.readLine()); //테스트케이스 수

        for(int tc=1; tc<=T; tc++){
            StringTokenizer st = new StringTokenizer(br.readLine());
            int M = Integer.parseInt(st.nextToken()); //M = 이동정보의 수
            int A = Integer.parseInt(st.nextToken()); //A = BC(무선충전기)의 수

            int[] moveA = new int[M]; //A사용자의 경로
            int[] moveB = new int[M]; //B사용자의 경로

            st = new StringTokenizer(br.readLine());
            for(int i=0;i<M;i++) moveA[i] = Integer.parseInt(st.nextToken());

            st = new StringTokenizer(br.readLine());
            for(int i=0;i<M;i++) moveB[i] = Integer.parseInt(st.nextToken());

            int[] bx = new int[A]; //BC의 x좌표
            int[] by = new int[A]; //BC의 y좌표
            int[] cc = new int[A]; //BC의 범위
            int[] pp = new int[A]; //BC의 충전량 을 담을 배열

            for(int k=0;k<A;k++){
                st = new StringTokenizer(br.readLine());
                bx[k] = Integer.parseInt(st.nextToken());
                by[k] = Integer.parseInt(st.nextToken());
                cc[k] = Integer.parseInt(st.nextToken());
                pp[k] = Integer.parseInt(st.nextToken());
            }
            //사용자 A와 B의 시작 위치
            int ai = 0, aj = 0;   
            int bi = 9, bj = 9;   
            int sum = 0;


            sum += bestSum(ai,aj,bi,bj, bx,by,cc,pp, A); //시작위치에서도 충전 가능

            //제시된 경로대로 돌아다니면서 그 시점의 bestSum구하기
            for(int t=0;t<M;t++){
                ai += dir[moveA[t]][0];
                aj += dir[moveA[t]][1];
                bi += dir[moveB[t]][0];
                bj += dir[moveB[t]][1];
                sum += bestSum(ai,aj,bi,bj, bx,by,cc,pp, A);
            }
            //정답 build
            out.append('#').append(tc).append(' ').append(sum).append('\n');
        }
        System.out.print(out);//정답 출력
    }
}
