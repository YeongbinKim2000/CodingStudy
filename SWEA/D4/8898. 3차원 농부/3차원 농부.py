tc = int(input())

for t in range(1, tc + 1):
    n, m = map(int, input().split())
    c_1, c_2 = map(int, input().split())

    cows = list(map(int, input().split()))
    horses = list(map(int, input().split()))
    horses.sort()

    min_dist = 1e20
    total_cnt = 0

    for i in range(n):
        l, r = 0, m - 1
        idx = m

        while l <= r:
            mid = (l + r) // 2

            if cows[i] > horses[mid]:
                l = mid + 1
            else:
                r = mid - 1
                idx = mid

        if idx < m:
            dist_1 = abs(c_2 - c_1) + abs(horses[idx] - cows[i])
            dist_2 = abs(c_2 - c_1) + abs(horses[idx - 1] - cows[i])

            if dist_1 > dist_2:
                cur_dist = dist_2
                idx -= 1
                cur_cnt = 1
            elif dist_1 == dist_2:
                cur_dist = dist_1
                cur_cnt = 2
            else:
                cur_dist = dist_1
                cur_cnt = 1
        else:
            cur_dist = abs(c_2 - c_1) + abs(horses[idx - 1] - cows[i])
            cur_cnt = 1

        if cur_dist == min_dist:
            total_cnt += cur_cnt
        elif cur_dist < min_dist:
            min_dist = cur_dist
            total_cnt = cur_cnt

    print(f"#{t} {min_dist} {total_cnt}")