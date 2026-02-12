import sys

n = int(sys.stdin.readline())
list = []

for i in range(n):
    cur_line = sys.stdin.readline().strip().split()
    cur_list = []
    weight = int(cur_line[0])
    height = int(cur_line[1])
    cur_list.append(weight)
    cur_list.append(height)
    list.append(cur_list)

for i in range(n):
    cnt = 0
    for j in range(n):
        if i == j:
            continue
        elif list[i][0] < list[j][0] and list[i][1] < list[j][1]:
            cnt += 1
    print(cnt + 1, end=' ')



