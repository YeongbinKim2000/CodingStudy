import sys

num = int(sys.stdin.readline())
chanList = []
ans = ''
curChan = 0
target1 = 0
target2 = 0

for i in range(num):
    chan = sys.stdin.readline().strip()
    chanList.append(chan)
    if chan == 'KBS1':
        target1 = i
    elif chan == 'KBS2':
        target2 = i

if target2 < target1:
    for _ in range(target1 - curChan):
        ans += '1'
    curChan = target1
    for _ in range(target1):
        ans += '4'
    curChan = 0
    target2 += 1
    for _ in range(target2 - curChan):
        ans += '1'
    curChan = target2
    for _ in range(target2 - 1):
        ans += '4'
else:
    for _ in range(target1 - curChan):
        ans += '1'
    curChan = target1
    for _ in range(target1):
        ans += '4'
    curChan = 0
    for _ in range(target2 - curChan):
        ans += '1'
    curChan = target2
    for _ in range(target2 - 1):
        ans += '4'

print(ans)