import sys

line = sys.stdin.readline().strip()
words = dict()
l = len(line)

for i in range(1, l + 1):
    for j in range(l - i + 1):
        sub = line[j:j+i]
        if sub not in words:
            words[sub] = 0

print(len(words))