from collections import deque

tc = int(input())

dx = (0, 1, 1, 1, 0, -1, -1, -1)
dy = (1, 1, 0, -1, -1, -1, 0, 1)


def bfs(x, y):
    global visited

    q = deque()
    q.append((x, y))
    visited[x][y] = True
    while q:
        cur_x, cur_y = q.popleft()

        if board[cur_x][cur_y] == 0:
            for i in range(8):
                next_x = cur_x + dx[i]
                next_y = cur_y + dy[i]

                if next_x < 0 or next_x >= n or next_y < 0 or next_y >= n:
                    continue

                if visited[next_x][next_y]:
                    continue

                if mine_board[next_x][next_y] == '*':
                    continue

                visited[next_x][next_y] = True
                q.append((next_x, next_y))


for t in range(1, tc + 1):
    n = int(input())
    mine_board = []
    for _ in range(n):
        mine_board.append(list(input()))

    board = [[0] * n for _ in range(n)]
    for i in range(n):
        for j in range(n):
            if mine_board[i][j] == '.':
                cnt = 0
                for k in range(8):
                    next_x = i + dx[k]
                    next_y = j + dy[k]

                    if next_x < 0 or next_x >= n or next_y < 0 or next_y >= n:
                        continue

                    if mine_board[next_x][next_y] == '*':
                        cnt += 1

                board[i][j] = cnt
            else:
                board[i][j] = '*'

    visited = [[False] * n for _ in range(n)]
    ans = 0
    for i in range(n):
        for j in range(n):
            if board[i][j] == 0 and not visited[i][j]:
                bfs(i, j)
                ans += 1

    for i in range(n):
        for j in range(n):
            if mine_board[i][j] == '.' and not visited[i][j]:
                ans += 1

    print(f"#{t} {ans}")