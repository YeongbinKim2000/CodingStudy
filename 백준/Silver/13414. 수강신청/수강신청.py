import sys

line = sys.stdin.readline().strip().split()

k = int(line[0])
l = int(line[1])

queue = dict()
for _ in range(l):
    cur = sys.stdin.readline().strip()
    if cur in queue:
        queue.pop(cur)
    queue[cur] = 0

ans = list(queue.keys())
for i in range(min(k, len(ans))):
    print(ans[i])