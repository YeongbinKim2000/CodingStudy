tc = int(input())

for t in range(1, tc + 1):
    n = int(input())

    names = []
    check = set()

    for _ in range(n):
        cur = input()
        if cur not in check:
            check.add(cur)
            names.append(cur)

    names.sort(key=lambda x: (len(x), x))

    print(f"#{t}\n" + "\n".join(names))