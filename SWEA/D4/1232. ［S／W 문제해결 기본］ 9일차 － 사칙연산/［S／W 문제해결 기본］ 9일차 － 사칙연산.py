class Node:
    def __init__(self, num=-1, value='', left=None, right=None):
        self.num = num
        self.value = value
        self.left = left
        self.right = right


def calculate(operator, left, right):
    if operator == '+':
        return left + right
    elif operator == '-':
        return left - right
    elif operator == '*':
        return left * right
    else:
        return left / right


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
        num = int(info[0])
        if len(info) > 2:
            nodes[num].left = nodes[int(info[2])]
        if len(info) > 3:
            nodes[num].right = nodes[int(info[3])]

    for node in reversed(nodes):
        if node is not None and not node.value.isdigit():
            node.value = calculate(node.value, int(node.left.value), int(node.right.value))
            node.left = None
            node.right = None

    print(f"#{t} {int(nodes[1].value)}")