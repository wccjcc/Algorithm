/*
swea/(5653)줄기세포배양 
아이디어 : cell class를 만들어서 좌표와 생명력수치, 현재 활성화시간, 비활성화시간과 생사여부를 저장하고, 
life 기준으로 내림차순하도록 compare를 override했습니다. 
그 후 grow()로 배양 한 턴을 처리했는데, 아기세포들은 후보로 따로 리스트에 넣어준 뒤, 내림차순 정렬하고 먼저 map을 차지하도록
하였습니다. 그리고 살아남은 세포들만 pq에 넣어주고 다시 턴을 시작합니다.
*/
import java.util.*;
import java.io.*;
public class Main
{
    static int N,M,K; //N : 세로크기, M : 가로크기, K : 배양 시간 
    static cell[][] map; //cell 을 저장할 배양접시 
    static PriorityQueue<cell> cellPQ; //살아있는 cell들을 담을 priorityQueue 
    static int[] di = {0,0,1,-1}; 
    static int[] dj = {1,-1,0,0};
    //세포 클래스
    public static class cell implements Comparable<cell> {
        int i, j, life, disactive, active;
        boolean isDead;
        @Override
        public cell(int i,int j, int life){
            super();
            this.i = i; //세포의 행
            this.j = j; //세포의 열 
            this.life = life; //세포의 생명력 수치
            this.active = life; //세포의 현재 활성화 상태 
            this.disactive = life; //세포의 현재 비활성화 상태 
            this.isDead = false; //세포의 생사여부 
        }
        
        public int compareTo(cell o){
            return Integer.compare(o.life,this.life); //세포 내림차순 하도록 
        }
    }
    //배양 메서드 
    public static void grow(){
        List<cell> newBorns = new ArrayList<>(); //신생세포들 후보 리스트 
        List<cell> tempList = new ArrayList<>(); //이번턴에서 살아남는 세포 리스트 
        //PQ안의 세포들을 다 돌면서 
        while (!cellPQ.isEmpty()){
            cell c = cellPQ.poll(); //세포 하나를 꺼내서 
            //c가 이미 죽었으면, continue
            if(c.isDead) continue;
            //비활성상태이면
            if(c.disactive > 0) c.disactive--;
            //비활성상태이면
            else if(c.disactive ==0){
                //활성화 첫순간에만 번식 
                if(c.active == c.life){
                    //사방으로 번식 
                    for(int h = 0 ; h < 4;h++){
                        int ni = c.i + di[h];
                        int nj = c.j + dj[h];
                        if(map[ni][nj] == null){ //세포가 번식할 수 있는 공간이면 
                            newBorns.add(new cell(ni,nj,c.life)); //후보 신생세포를 만들어 리스트에 넣기 
                        }
                    }
                }
                c.active--; //활성시간 줄이기 
                if(c.active == 0) c.isDead = true; //줄였을때 0이면 수명이 끝난것이므로 isDead를 true로 바꿔주기 
            }
            if (!c.isDead) tempList.add(c); //안죽었다면 살아남은 리스트에 넣어주기 
            
        }
        Collections.sort(newBorns); //newBorns 내림차순 정렬 
        //아기세포들 넣어주기
        for(cell b : newBorns){
            if(map[b.i][b.j] == null){ //이미 점유되지않았다면 넣기 
                map[b.i][b.j] = b;
                tempList.add(b); //신생세포들도 tempList에 넣어줘야 
            }
        }
        //PQ 갱신
        cellPQ.addAll(tempList);
    }
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		int T = Integer.parseInt(br.readLine());
		for(int tc = 1; tc <= T ; tc++){
		    StringTokenizer st = new StringTokenizer(br.readLine());
		    N = Integer.parseInt(st.nextToken());
		    M = Integer.parseInt(st.nextToken());
		    K = Integer.parseInt(st.nextToken());
		    map = new cell[N+2*K][M+2*K];
		    cellPQ = new PriorityQueue<>();
		    //배양생물 입력받기
		    for(int i = K; i <= K+N-1 ; i++){
		        st = new StringTokenizer(br.readLine());
		        for(int j = K ; j <= K+M-1 ; j++){
		            int temp = Integer.parseInt(st.nextToken());
		            if (temp != 0){
		                cell tempCell = new cell(i,j,temp);
		                map[i][j] = tempCell;
		                cellPQ.add(tempCell);
		            }
		            
		        }
		    }
		    //k시간동안 배양하기
		    for(int i = 0 ; i < K; i++){
		        grow();
		    }
		    
		    //k시간 이후 살아있는 줄기세포의 총개수 구하기
		    int cnt = cellPQ.size();
		    System.out.println("#"+tc+" "+cnt);
		}
	}
}
