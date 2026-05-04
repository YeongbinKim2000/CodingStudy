import heapq

tc = int(input())

for t in range(1, tc + 1):
    n = int(input())
    nums = list(map(int, input().split()))
    k = int(input())

    nums = sorted(set(nums))

    pq = []
    heapq.heappush(pq, (0, k))

    visited = {}

    ans = k

    while pq:
        cost, cur = heapq.heappop(pq)

        if cur in visited and visited[cur] <= cost:
            continue

        visited[cur] = cost

        ans = min(ans, cost + cur)

        if cost >= ans:
            continue

        for num in nums:
            nxt = cur // num
            add_cost = cur % num

            new_cost = cost + add_cost

            if new_cost >= ans:
                continue

            if nxt not in visited or visited[nxt] > new_cost:
                heapq.heappush(pq, (new_cost, nxt))

    print(f"#{t} {ans}")