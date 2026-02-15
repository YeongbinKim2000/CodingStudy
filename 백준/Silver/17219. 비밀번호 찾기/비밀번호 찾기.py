import sys

line = sys.stdin.readline().strip().split()
n = int(line[0])
m = int(line[1])

map = dict()

for _ in range(n):
    line = sys.stdin.readline().strip().split()
    address = line[0]
    password = line[1]
    map[address] = password

for _ in range(m):
    target = sys.stdin.readline().strip()
    print(map.get(target))