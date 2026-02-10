import sys
from itertools import combinations

target_word = sys.stdin.readline().strip()
L = 26
target_cnt = [0] * L
for s in target_word:
    target_cnt[ord(s) - ord('A')] += 1

l = int(sys.stdin.readline())
word_dict = {}

for c in range(l):
    cur_line = sys.stdin.readline().strip().split()
    cur_price = int(cur_line[0])
    cur_word = cur_line[1]
    cur_cnt = [0] * L
    for s in cur_word:
        cur_cnt[ord(s) - ord('A')] += 1

    word_dict[cur_price] = cur_cnt

keys = list(word_dict.keys())

combo_arr = {}

for r in range(1, len(keys) + 1):
    for comb in combinations(keys, r):
        s = sum(comb)

        sum_arr = [0] * L
        for k in comb:
            sum_arr = [a + b for a, b in zip(sum_arr, word_dict[k])]

        combo_arr[s] = sum_arr

sorted_combo_arr = sorted(combo_arr.items())

find = True
for c in range(len(sorted_combo_arr)):
    find = True
    for i in range(L):
        if 0 < target_cnt[i] and target_cnt[i] > sorted_combo_arr[c][1][i]:
            find = False
            break
    if find:
        print(sorted_combo_arr[c][0])
        break

if not find:
    print(-1)


