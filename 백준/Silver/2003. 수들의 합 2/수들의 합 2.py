import sys

line = sys.stdin.readline().strip().split(' ')
n = int(line[0])
m = int(line[1])

arr = list()
line = sys.stdin.readline().strip().split(' ')
for i in range(n):
    arr.append(int(line[i]))

cnt, i, j = 0, 0, 0
cur_sum = arr[0]
while j < n:
    if cur_sum == m:
        cnt += 1
        j += 1
        if j == n:
            break
        cur_sum += arr[j]
    elif cur_sum < m:
        j += 1
        if j == n:
            break
        cur_sum += arr[j]
    else:
        cur_sum -= arr[i]
        i += 1

print(cnt)