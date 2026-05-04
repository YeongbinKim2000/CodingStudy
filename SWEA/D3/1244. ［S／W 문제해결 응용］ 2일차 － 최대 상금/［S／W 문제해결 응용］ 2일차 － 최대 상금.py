def dfs(num_list, cnt):
    global n, ans, visited

    cur = ("".join(num_list), cnt)
    if cur in visited:
        return
    visited.add(cur)

    if cnt == n:
        val = ""
        for l in num_list:
            val += l
        ans = max(ans, int(val))
        return

    for i in range(len(num_list) - 1):
        for j in range(i + 1, len(num_list)):
            num_list[i], num_list[j] = num_list[j], num_list[i]
            dfs(num_list, cnt + 1)
            num_list[i], num_list[j] = num_list[j], num_list[i]


tc = int(input())

for t in range(1, tc + 1):
    num, c = input().split()
    n = int(c)
    num_list = list(num)
    visited = set()
    ans = 0
    dfs(num_list, 0)

    print(f"#{t} {ans}")