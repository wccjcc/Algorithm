/*
유형 : 시뮬레이션
기능 : 군집이동(군집방향으로 1칸이동/군집약품칸처리(방향전환,소멸)/군집병합(미생물수로 비교후 통합,방향결정))
자료구조 : 1. 군집리스트(군집하나:미생물수,좌표,방향) 2. 군집들이동방문관리2차원방문배열
1억번의 연산 1초라 생각
*/
import java.util.*;
import java.io.*;
public class Solution_2382_정우주
{
		//군집 class 만들기
    static class Micro implements Comparable<Micro>{
        int r,c,cnt,dir; //r:군집의 행, c:군집의 열, cnt:미생물의 수, dir:방향
        boolean isDead; //기본값 false
        public Micro(int r, int c, int cnt, int dir){
            super();
            this.r = r;
            this.c = c;
            this.cnt = cnt;
            this.dir = dir;
        }
        @Override
        public int compareTo(Micro o){
            return Integer.compare(o.cnt, this.cnt); //크기 기준 내림 차순 
        }
    }
    static int N,M,K;
    static int[] dr = {0,-1,1,0,0}; //0:안씀, 상:1, 하:2, 좌:3, 우:4
    static int[] dc = {0,0,0,-1,1};
    static Micro[] list; //군집리스트
    static Micro[][] map; //맵 정보(어느군집이 어디있나)
    
	public static void main(String[] args) throws NumberFormatException,IOException{
	    BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
	    int TC = Integer.parseInt(br.readLine());
	    
	    for(int tc = 1; tc <= TC; ++tc){
	        StringTokenizer st = new StringTokenizer(br.readLine());
	        N = Integer.parseInt(st.nextToken()); //맵 크기
	        M = Integer.parseInt(st.nextToken()); //시간
	        K = Integer.parseInt(st.nextToken()); //초기 군집의 수수
	        
	        //전체 군집리스트 (죽은군집,살아있는 군집 모두 포함)
	        list = new Micro[K];
	        //매 시간마다 각 셀에 이동한 미생물 정보 
	        map = new Micro[N][N];
	        
	        for (int i = 0 ; i < K; ++i){
	            st = new StringTokenizer(br.readLine());
	            list[i] = new Micro(Integer.parseInt(st.nextToken()),Integer.parseInt(st.nextToken()),Integer.parseInt(st.nextToken()),Integer.parseInt(st.nextToken()));
	            
	        }
	        System.out.println("#"+tc+" "+move());
	    }
		
	}
	//살아있는 미생물 수 리턴
	private static int move() {
	    int time = M,nr,nc,remainCnt=0; //M : 시간, nr,nc: 새로운좌표, remainCnt: 미생물총수
	    //주어진 시간동안 반복
	    while(time-->0){
	        Arrays.sort(list); //정렬 추가 (정렬하면 어차피 내림차순이므로 군집 안갈아끼워도돼)
	        //군집마다
	        for(Micro cur : list){
	            if(cur.isDead) continue; //리스트에서 삭제하지 않았으므로 죽은 군집은 건너뛰도록 처리 필요 
	            nr = cur.r += dr[cur.dir];
	            nc = cur.c += dc[cur.dir];
	            //step2)약품칸처리
	            if(nr == 0 || nr == N-1 || nc == 0 || nc==N-1){
	                //군집 크기 절반으로 줄이고 방향 바꿈, 크기가 0이면 소멸
	                cur.cnt = cur.cnt/2;
	                if(cur.cnt == 0){
	                    cur.isDead = true;
	                    continue;
	                }
	                //방향 반대로
	                if(cur.dir%2 == 1) cur.dir++;
	                else cur.dir--;
	            }
	            //step3) 군집 병합 처리 
	            if(map[nr][nc]==null){ //그 셀에 처음 도착하는 군집
	                map[nr][nc] = cur;
	            }else{ //그 셀에 나중에 도착하는 군집(이미 다른 군집이 있는 경우)
	               map[nr][nc].cnt += cur.cnt;
	               cur.isDead = true;
	            }
	        }//end for : 군집리스트 처리
	        remainCnt = reset(); //현재 시간에 이동한 군집들 정리 후 살아 있는 미생물 수 받기기
	        
	    }//end while : 시간 반복
	    
	    return remainCnt;
	}
	private static int reset(){
	    int total = 0;
	    for(int r = 0 ; r < N; r++){
	        for(int c = 0 ; c < N; c++){
	            if(map[r][c] == null) continue;
	            total += map[r][c].cnt;
	            map[r][c] = null; //다음시간 처리 위해 초기화 (새로운좌표는 반영하므로 초기화해도 괜찮음)
	        }
	    }
	    return total;
	}
}