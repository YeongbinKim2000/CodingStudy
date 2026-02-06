import sys

line = sys.stdin.readline().rstrip('\n')
line = line.upper()

counter = dict()
for i in range(len(line)):
    if line[i] not in counter:
        counter[line[i]] = 1
    else:
        counter[line[i]] += 1

if len(counter) > 1:
    counter = sorted(counter.items(), key=lambda x: x[1], reverse=True)

    (first_key, first_val), (second_key, second_val) = counter[:2]
    if first_val > second_val:
        print(first_key)
    else:
        print('?')
else:
    print(line[0])



