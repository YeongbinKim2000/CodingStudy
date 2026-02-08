import sys

p = int(sys.stdin.readline())

for _ in range(p):
    l = sys.stdin.readline().strip()
    nums = list(map(int, l.split()))
    idx = nums.pop(0)
    cnt = 0
    line = list()

    for i in range(len(nums)):
        if not line:
            line.append(nums[i])
        else:
            find = False
            for j in range(len(line)):
                if line[j] > nums[i]:
                    cnt += (len(line) - j)
                    line.insert(j, nums[i])
                    find = True

                if find:
                    break
            if not find:
                line.append(nums[i])

    print(idx, cnt)