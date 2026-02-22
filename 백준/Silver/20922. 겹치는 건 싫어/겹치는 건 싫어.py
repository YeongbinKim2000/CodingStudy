import sys

line = sys.stdin.readline().strip().split(' ')
n = int(line[0])
k = int(line[1])

line = sys.stdin.readline().strip().split(' ')
arr = list()
for i in range(n):
    arr.append(line[i])

max_cnt = 1
i, j = 0, 1
hash_map = dict()
cur_cnt = 1
hash_map[arr[i]] = 1
while j < n:
    if arr[j] not in hash_map:
        cur_cnt += 1
        hash_map[arr[j]] = 1
    elif arr[j] in hash_map and hash_map[arr[j]] < k:
        cur_cnt += 1
        hash_map[arr[j]] += 1
    else:
        while arr[i] != arr[j]:
            cur_cnt -= 1
            hash_map[arr[i]] -= 1
            if hash_map[arr[i]] <= 0:
                hash_map.pop(arr[i])
            i += 1
        cur_cnt -= 1
        hash_map[arr[i]] -= 1
        if hash_map[arr[i]] <= 0:
            hash_map.pop(arr[i])
        i += 1
        cur_cnt += 1
        hash_map[arr[j]] = hash_map.get(arr[j], 0) + 1
    j += 1
    if cur_cnt > max_cnt:
        max_cnt = cur_cnt
    if j == n:
        break

print(max_cnt)