import java.util.*;

class Solution {
    boolean[] visited;
    int N;
    String[][] tickets;
    List<String> result; //정답 경로

    //경로를 찾으면 true 반환하여 상위 호출도 즉시 종료하기 
    boolean dfs(String now, int used,List<String> route){
        if(used == N){ //ticket을 다 썼다면 
            result = new ArrayList<>(route);
            return true; //사전순정렬 덕에 첫 해답이 곧 최솟값
        }

        for(int i = 0 ; i < N; i++){
            //티켓을 방문한 적 없고, 내가찾는 출발지라면
            if(!visited[i] && tickets[i][0].equals(now)){
                visited[i] = true;
                route.add(tickets[i][1]);
                if(dfs(tickets[i][1],used+1,route)) return true;
                route.remove(route.size()-1);
                visited[i] = false; //backtracking
            }
        }
        return false;
    }
    public String[] solution(String[][] tickets) {
        this.tickets = tickets;
        this.N = tickets.length;
        this.visited = new boolean[N];

        //정렬
        Arrays.sort(tickets, (a,b)->{
            if(a[0].equals(b[0])){
                return a[1].compareTo(b[1]);
            }
            return a[0].compareTo(b[0]);
        });
        List<String> route = new ArrayList<>();
        route.add("ICN");
        dfs("ICN",0,route);
        return result.toArray(new String[0]);

    }
}
