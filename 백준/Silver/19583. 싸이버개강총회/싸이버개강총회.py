import sys
S, E, Q = sys.stdin.readline().split()
att = set()
out = set()

for line in sys.stdin:
    t, name = line.split()
    if t <= S:
        att.add(name)
    elif E <= t <= Q and name in att:
        out.add(name)

print(len(out))