import sys

n = int(sys.stdin.readline())

n %= 4

if n % 2 == 0:
    print('CY')
else:
    print('SK')