import math

tc = int(input())

def find_set(x):
    if x != parents[x]:
        parents[x] = find_set(parents[x])

    return parents[x]


for t in range(1, tc + 1):
    n = int(input())
    x_positions = list(map(int, input().split()))
    y_positions = list(map(int, input().split()))
    E = float(input())

    edges = []
    for i in range(n - 1):
        for j in range(i + 1, n):
            dist = math.sqrt((x_positions[j] - x_positions[i]) ** 2 + (y_positions[j] - y_positions[i]) ** 2)
            edges.append((i + 1, j + 1, dist))

    edges.sort(key=lambda x: x[2])

    parents = [x for x in range(n + 1)]

    ans = 0
    cnt = 0
    for s, e, d in edges:
        if find_set(s) != find_set(e):
            parents[find_set(e)] = find_set(s)
            ans += pow(d, 2)
            cnt += 1

        if cnt == n - 1:
            break

    ans *= E

    print(f"#{t} {ans:.0f}")