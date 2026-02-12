import sys

n = int(sys.stdin.readline())

list = []

for _ in range(n):
    cur_line = sys.stdin.readline().strip().split()
    name = cur_line[0]
    status = cur_line[1]
    if status == 'enter':
        list.append(name)
    else:
        list.remove(name)

list.sort(reverse=True)

for i in range(len(list)):
    print(list[i])