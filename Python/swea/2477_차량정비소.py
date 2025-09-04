
t = int(input())
for tc in range(1, t + 1):\
    #n : 접수창구 수 , m:정비창구 수, k:방문한사람수, a:지갑놔두고간사람 접수창구, b:지갑놔두고간사람 정비창구구
    n, m, k, a, b = map(int, input().split()) 
    recept = list(map(int, input().split())) #접수창구별 걸리는 시간
    repair = list(map(int, input().split())) #정비창구별 걸리는시간 
    arrival = list(map(int, input().split())) #고객들이 도착하는 시간간

    recept_end = [-1] * n      # 접수 창구별 마지막 점유시각 
    repair_end = [-1] * m      # 정비 창구별 마지막 점유시각 
    used_recept = [None] * k   # 각 고객의 접수 창구 index
    used_repair = [None] * k   # 각 고객의 정비 창구 index
    to_repair = []             # [정비대기진입시각, 접수창구, 고객인덱스]

    # 1) 접수 배정
    for c in range(k):
        t0 = arrival[c] #c고객의 도착시각 
        picked = False #차례가되어 접수하는지 여부 
        for i in range(n): #접수창구별로 돌면서 
            if recept_end[i] < t0: #접수창구의 마지막점유시각보다 현재가 더 크면 비어있음 -> 바로시작 
                recept_end[i] = t0 + recept[i] - 1 #접수창구 마지막 점유시각 갱신신
                used_recept[c] = i # c 고객의 접수창구 index 기록 
                to_repair.append([recept_end[i] + 1, i, c]) #정비대기열에 c 넣기 
                picked = True #picked true로 
                break 
        if not picked: #접수할수있는 창구가 없으면 대기
            i = recept_end.index(min(recept_end)) #가장 빨리 끝나는 창구를 찾기
            recept_end[i] += recept[i] #그 창구 시간 더하기 
            used_recept[c] = i # 접수창구 index 기록록
            to_repair.append([recept_end[i] + 1, i, c]) # 정비대기열에 넣기기

    # 2) 정비 대기 우선순위 정렬
    to_repair.sort()  # (정비대기진입시각, 접수창구, 고객인덱스) 이순서대로 정렬된다다

    # 3) 정비 배정
    for t1, rec_i, c in to_repair: 
        placed = False
        for j in range(m): 
            if repair_end[j] < t1: 
                repair_end[j] = t1 + repair[j] - 1
                used_repair[c] = j
                placed = True
                break
        if not placed: 
            j = repair_end.index(min(repair_end))
            repair_end[j] += repair[j]
            used_repair[c] = j

    # 4) 정답 합산
    ans = 0
    for c in range(k):
        if used_recept[c] + 1 == a and used_repair[c] + 1 == b:
            ans += (c + 1)
    if ans == 0:
        ans = -1

    print(f"#{tc} {ans}")
