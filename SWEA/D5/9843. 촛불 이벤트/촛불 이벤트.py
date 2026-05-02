tc = int(input())

for t in range(1, tc + 1):
    n = int(input())

    l = 0
    r = n
    ans = -1

    while l <= r:
        mid = (l + r) // 2
        total = mid * (mid + 1) // 2

        if total == n:
            ans = mid
            break
        elif total > n:
            r = mid - 1
        else:
            l = mid + 1

    print(f"#{t} {ans}")
