class Node:
    def __init__(self, key, data=None):
        self.key = key
        self.data = data
        self.children = {}


def insert(root, word):
    cur = root

    for ch in word:
        if ch not in cur.children:
            cur.children[ch] = Node(ch)
        cur = cur.children[ch]

    cur.data = word


def dfs(node):
    global cnt, answer, k

    if node.data is not None:
        cnt += 1
        if cnt == k:
            answer = node.data
            return

    for ch in sorted(node.children.keys()):
        dfs(node.children[ch])
        if answer:
            return


tc = int(input())

for t in range(1, tc + 1):
    k = int(input())
    word = input()

    root = Node(None)

    for i in range(len(word)):
        for j in range(i + 1, len(word) + 1):
            insert(root, word[i:j])

    cnt = 0
    answer = ""

    dfs(root)

    if answer:
        print(f"#{t} {answer}")
    else:
        print(f"#{t} none")