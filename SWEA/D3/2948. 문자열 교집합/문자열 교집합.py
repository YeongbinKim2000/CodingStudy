tc = int(input())

for t in range(1, tc + 1):
    n, m = map(int, input().split())

    list_a = list(input().split())
    s = set(input().split())

    cnt = 0
    for i in list_a:
        if i in s:
            cnt += 1

    print(f"#{t} {cnt}")