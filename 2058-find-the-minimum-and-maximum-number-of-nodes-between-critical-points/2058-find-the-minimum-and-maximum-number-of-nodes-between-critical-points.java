class Solution {
    public int[] nodesBetweenCriticalPoints(ListNode head) {

        int[] ans = {-1, -1};

        ListNode prev = head;
        ListNode curr = head.next;

        int index = 1;

        int first = -1;
        int last = -1;

        int minDistance = Integer.MAX_VALUE;

        while (curr.next != null) {

            int prevValue = prev.val;
            int currValue = curr.val;
            int nextValue = curr.next.val;

            // Check if current node is a critical point
            if ((currValue > prevValue && currValue > nextValue) ||
                (currValue < prevValue && currValue < nextValue)) {

                // First critical point
                if (first == -1) {
                    first = index;
                }

                // We already found a previous critical point
                if (last != -1) {
                    minDistance = Math.min(
                        minDistance,
                        index - last
                    );
                }

                last = index;
            }

            prev = curr;
            curr = curr.next;
            index++;
        }

        // Fewer than two critical points
        if (first == -1 || first == last) {
            return ans;
        }

        int maxDistance = last - first;

        return new int[]{minDistance, maxDistance};
    }
}