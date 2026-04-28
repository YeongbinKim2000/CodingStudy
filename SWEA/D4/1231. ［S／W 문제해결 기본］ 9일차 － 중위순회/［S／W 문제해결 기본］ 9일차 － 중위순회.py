class Node:
    def __init__(self, num=0, value='', left=None, right=None):
        self.num = num
        self.value = value
        self.left = left
        self.right = right


def inorder(node):
    if node:
        inorder(node.left)
        result.append(node.value)
        inorder(node.right)


for t in range(1, 11):
    n = int(input())
    nodes = [None] * (n + 1)
    info_list = []

    for _ in range(n):
        info = input().split()
        info_list.append(info)

        num = int(info[0])
        value = info[1]
        nodes[num] = Node(num, value)

    for info in info_list:
        if len(info) > 2:
            nodes[int(info[0])].left = nodes[int(info[2])]
        if len(info) > 3:
            nodes[int(info[0])].right = nodes[int(info[3])]

    result = []
    inorder(nodes[1])

    print(f"#{t} " + "".join(result))