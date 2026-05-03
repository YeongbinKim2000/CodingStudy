tc = int(input())

for t in range(1, tc + 1):
    l = int(input())
    n = int(input())

    s_arr = []
    e_arr = []
    sum_arr = [0] * n
    for i in range(n):
        s, e = map(int, input().split())
        s_arr.append(s)
        e_arr.append(e)

        if i == 0:
            sum_arr[i] = e - s
        else:
            sum_arr[i] = sum_arr[i - 1] + (e - s)

    ans = 0
    for i in range(n):
        cur_e = s_arr[i] + l
        left = i
        right = n - 1
        target_idx = n
        while left <= right:
            mid = (left + right) // 2

            if e_arr[mid] < cur_e:
                left = mid + 1
            else:
                target_idx = mid
                right = mid - 1

        if target_idx == n:
            sum = sum_arr[n - 1] - (sum_arr[i - 1] if i > 0 else 0)
        else:
            sum = sum_arr[target_idx - 1] - (sum_arr[i - 1] if i > 0 else 0)

        if target_idx < n:
            sum += max(0, cur_e - s_arr[target_idx])

        ans = max(ans, sum)

    print(f"#{t} {ans}")