tc = int(input())


def find_set(x):
    if parents[x] != x:
        parents[x] = find_set(parents[x])
        
    return parents[x]


for t in range(1, tc + 1):
    n = int(input())
    m = int(input())

    edges = []
    for _ in range(m):
        s, e, c = map(int, input().split())
        edges.append((s, e, c))

    edges.sort(key=lambda x : x[2])

    parents = [x for x in range(0, n + 1)]

    dist, cnt = 0, 0

    for s, e, c in edges:
        if find_set(s) != find_set(e):
            parents[find_set(e)] = find_set(s)
            dist += c
            cnt += 1

        if cnt == n - 1:
            break

    print(f"#{t} {dist}")
