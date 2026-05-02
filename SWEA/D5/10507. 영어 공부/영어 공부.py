tc = int(input())

for t in range(1, tc + 1):
    n, p = map(int, input().split())
    days = list(map(int, input().split()))

    ans = 0
    l = 0
    for r in range(n):
        while (days[r] - days[l]) - (r - l) > p:
            l += 1

        blank = (days[r] - days[l]) - (r - l)
        ans = max(ans, (days[r] - days[l] + 1) + p - blank)

    print(f"#{t} {ans}")