import heapq

tc = int(input())

for t in range(1, tc + 1):
    max_heap = []
    outputs = []
    n = int(input())
    for _ in range(n):
        line = input()
        if len(line) > 1:
            temp_list = list(line.split())
            heapq.heappush(max_heap, -int(temp_list[1]))
        else:
            if max_heap:
                outputs.append(str(-heapq.heappop(max_heap)))
            else:
                outputs.append("-1")

    print(f"#{t} " + " ".join(outputs))