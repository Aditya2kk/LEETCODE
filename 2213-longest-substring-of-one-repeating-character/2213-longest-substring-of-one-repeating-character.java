class Solution {

    class Node {
        int pre;       // longest same-char prefix
        int suf;       // longest same-char suffix
        int maxLen;    // longest same-char substring
        int leftChar;
        int rightChar;
        int len;

        Node(int pre, int suf, int maxLen,
             int leftChar, int rightChar, int len) {
            this.pre = pre;
            this.suf = suf;
            this.maxLen = maxLen;
            this.leftChar = leftChar;
            this.rightChar = rightChar;
            this.len = len;
        }
    }

    Node[] tree;
    String s;

    public int[] longestRepeating(String s, String queryCharacters, int[] queryIndices) {

        this.s = s;

        int n = s.length();
        tree = new Node[4 * n];

        build(1, 0, n - 1);

        int[] ans = new int[queryIndices.length];

        for (int i = 0; i < queryIndices.length; i++) {

            int pos = queryIndices[i];
            char ch = queryCharacters.charAt(i);

            update(1, 0, n - 1, pos, ch);

            ans[i] = tree[1].maxLen;
        }

        return ans;
    }

    private void build(int node, int l, int r) {

        if (l == r) {
            int c = s.charAt(l);

            tree[node] = new Node(
                1,
                1,
                1,
                c,
                c,
                1
            );

            return;
        }

        int mid = l + (r - l) / 2;

        build(node * 2, l, mid);
        build(node * 2 + 1, mid + 1, r);

        tree[node] = merge(tree[node * 2], tree[node * 2 + 1]);
    }

    private Node merge(Node left, Node right) {

        int len = left.len + right.len;

        int leftChar = left.leftChar;
        int rightChar = right.rightChar;

        int pre = left.pre;

        if (left.pre == left.len &&
            left.rightChar == right.leftChar) {

            pre = left.len + right.pre;
        }

        int suf = right.suf;

        if (right.suf == right.len &&
            left.rightChar == right.leftChar) {

            suf = right.len + left.suf;
        }

        int maxLen = Math.max(left.maxLen, right.maxLen);

        if (left.rightChar == right.leftChar) {
            maxLen = Math.max(
                maxLen,
                left.suf + right.pre
            );
        }

        return new Node(
            pre,
            suf,
            maxLen,
            leftChar,
            rightChar,
            len
        );
    }

    private void update(int node, int l, int r, int pos, char ch) {

        if (l == r) {

            int c = ch;

            tree[node] = new Node(
                1,
                1,
                1,
                c,
                c,
                1
            );

            return;
        }

        int mid = l + (r - l) / 2;

        if (pos <= mid) {
            update(node * 2, l, mid, pos, ch);
        } else {
            update(node * 2 + 1, mid + 1, r, pos, ch);
        }

        tree[node] = merge(
            tree[node * 2],
            tree[node * 2 + 1]
        );
    }
}