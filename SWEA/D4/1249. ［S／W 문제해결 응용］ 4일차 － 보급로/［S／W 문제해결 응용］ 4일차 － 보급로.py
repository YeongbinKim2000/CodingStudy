import heapq

dx = (0, 1, 0, -1)
dy = (1, 0, -1, 0)

tc = int(input())

for t in range(1, tc + 1):
    n = int(input())

    board = []
    for _ in range(n):
        line = list(map(int, input()))
        board.append(line)

    sums = [[float('inf')] * n for _ in range(n)]
    sums[0][0] = 0

    hq = []
    heapq.heappush(hq, (0, 0, 0))
    while hq:
        cur_sum, cur_x, cur_y = heapq.heappop(hq)

        for i in range(4):
            next_x = cur_x + dx[i]
            next_y = cur_y + dy[i]

            if next_x < 0 or next_x >= n or next_y < 0 or next_y >= n:
                continue

            if sums[cur_x][cur_y] + board[next_x][next_y] < sums[next_x][next_y]:
                sums[next_x][next_y] = sums[cur_x][cur_y] + board[next_x][next_y]
                heapq.heappush(hq, (sums[next_x][next_y], next_x, next_y))

    print(f"#{t} {sums[n-1][n-1]}")