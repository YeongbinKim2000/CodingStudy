from collections import deque

tc = int(input())


def get_dist(a, b):
    dist = 0

    while depth[a] > depth[b]:
        a = parents[a]
        dist += 1

    while depth[a] < depth[b]:
        b = parents[b]
        dist += 1

    while a != b:
        a = parents[a]
        b = parents[b]
        dist += 2

    return dist


def bfs():
    global total_dist

    q = deque([1])
    prev = 1

    while q:
        cur = q.popleft()

        total_dist += get_dist(prev, cur)
        prev = cur

        for nxt in graph[cur]:
            q.append(nxt)


for t in range(1, tc + 1):
    n = int(input())

    line = list(map(int, input().split()))

    parents = [0] * (n + 1)
    depth = [0] * (n + 1)
    graph = [[] for _ in range(n + 1)]

    for i in range(n - 1):
        parent = line[i]
        child = i + 2

        parents[child] = parent
        depth[child] = depth[parent] + 1
        graph[parent].append(child)

    total_dist = 0
    bfs()

    print(f"#{t} {total_dist}")