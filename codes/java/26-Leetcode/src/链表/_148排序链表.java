package 链表;

import tools.Asserts;
import tools.Times;

/**
 * 给你链表的头结点 head ，请将其按 升序 排列并返回 排序后的链表 。
 */
public class _148排序链表 {
    /**
     * 解答一：归并排序（递归法）
     * 题目要求时间空间复杂度分别为 O(nlogn) 和 O(1)，根据时间复杂度我们自然想到二分法，从而联想到归并排序；
     * <p>
     * 对数组做归并排序的空间复杂度为 O(n)，分别由新开辟数组 O(n) 和递归函数调用 O(logn) 组成，而根据链表特性：
     * 数组额外空间：链表可以通过修改引用来更改节点顺序，无需像数组一样开辟额外空间；
     * 递归额外空间：递归调用函数将带来 O(logn) 的空间复杂度，因此若希望达到 O(1) 空间复杂度，则不能使用递归。
     * <p>
     * 时间复杂度：O(nlogn)，其中 n 是链表的长度。
     * 空间复杂度：O(logn)。
     *
     * @param head 链表的头节点
     * @return 排序后的链表的头节点
     */
    public ListNode sortList(ListNode head) {
        // 如果链表为空，则直接返回
        if (head == null || head.next == null) {
            return head;
        }

        // 找到中间节点，并断开 head2 与其前一个节点的连接
        // 比如 head=[4,2,1,3]，那么 middleNode 调用结束后 head=[4,2] head2=[1,3]
        ListNode head2 = middleNode(head);

        // 分治
        ListNode left = sortList(head);
        ListNode right = sortList(head2);

        // 合并
        return mergeTwoLists(left, right);
    }

    /**
     * 此处求的是中间节点的前一个节点，并且要将链表拆开
     * 876. 链表的中间结点（快慢指针）是求的中间节点，如果有两个中间节点，则曲第二个
     */
    private ListNode middleNode(ListNode head) {
        ListNode slow = head;
        ListNode fast = head;
        // 先找到链表的中间结点的【前一个节点】
        while (fast.next != null && fast.next.next != null) {
            slow = slow.next;
            fast = fast.next.next;
        }
        // 下一个节点就是链表的中间结点 mid
        ListNode mid = slow.next;
        // 断开 mid 的前一个节点和 mid 的连接
        slow.next = null;
        return mid;
    }

    // 21. 合并两个有序链表（双指针）
    private ListNode mergeTwoLists(ListNode l1, ListNode l2) {
        // 用哨兵节点简化代码逻辑
        ListNode dummyHead = new ListNode(0);
        // cur 指向新链表的末尾
        ListNode cur = dummyHead;

        while (l1 != null && l2 != null) {
            if (l1.val < l2.val) {
                cur.next = l1;
                l1 = l1.next;
            } else { // 注：相等的情况加哪个节点都是可以的
                cur.next = l2;
                l2 = l2.next;
            }
            cur = cur.next;
        }
        // 拼接剩余链表
        cur.next = l1 != null ? l1 : l2;
        return dummyHead.next;
    }

    /**
     * 解答二：归并排序（迭代法）（从底至顶直接合并）
     * https://leetcode.cn/problems/sort-list/solutions/2993518/liang-chong-fang-fa-fen-zhi-die-dai-mo-k-caei/
     * <p>
     * 自底向上的意思是：
     * 首先，归并长度为 1 的子链表。例如 [4,2,1,3]，把第一个节点和第二个节点归并，第三个节点和第四个节点归并，得到 [2,4,1,3]。
     * 然后，归并长度为 2 的子链表。例如 [2,4,1,3]，把前两个节点和后两个节点归并，得到 [1,2,3,4]。
     * 然后，归并长度为 4 的子链表。
     * 依此类推，直到归并的长度大于等于链表长度为止，此时链表已经是有序的了。
     * <p>
     * 具体算法：
     * 遍历链表，获取链表长度 length。
     * 初始化步长 step=1。
     * 循环直到 step≥length。
     * 每轮循环，从链表头节点开始。
     * 分割出两段长为 step 的链表，合并，把合并后的链表插到新链表的末尾。重复该步骤，直到链表遍历完毕。
     * 把 step 扩大一倍。回到第 4 步。
     * <p>
     * 时间复杂度：O(nlogn)，其中 n 是链表的长度。
     * 空间复杂度：O(1)。
     *
     * @param head 链表的头节点
     * @return 排序后的链表的头节点
     */
    public ListNode sortList2(ListNode head) {
        // 如果链表为空或只有一个节点，直接返回
        if (head == null || head.next == null) {
            return head;
        }

        // 获取链表长度
        int length = getLength(head);

        // 创建一个虚拟头节点，方便后续操作
        ListNode dummyHead = new ListNode(0, head);

        // 自底向上归并排序
        // step 为步长，即参与合并的链表长度
        for (int step = 1; step < length; step <<= 1) {
            // 新链表的末尾
            ListNode newListTail = dummyHead;
            //  每轮循环的起始节点
            ListNode cur = dummyHead.next;
            while (cur != null) {
                ListNode head1 = cur;
                ListNode head2 = splitList(head1, step);
                cur = splitList(head2, step);
                // 合并两段长为step的链表
                ListNode[] merged = mergeTwoLists2(head1, head2);
                // 把合并后的链表插到新链表的末尾
                newListTail.next = merged[0];
                newListTail = merged[1];
            }
        }

        return dummyHead.next;
    }

    // 获取链表长度
    private int getLength(ListNode head) {
        int length = 0;
        while (head != null) {
            length++;
            head = head.next;
        }
        return length;
    }

    // 分割链表
    // 如果链表长度 <= size，不做任何操作，返回空节点
    // 如果链表长度 > size，把链表的前 size 个节点分割出来（断开连接），并返回剩余链表的头节点
    private ListNode splitList(ListNode head, int size) {
        // 先找到 nextHead 的前一个节点
        ListNode cur = head;
        for (int i = 0; i < size - 1 && cur != null; i++) {
            cur = cur.next;
        }

        // 如果链表长度 <= size
        if (cur == null || cur.next == null) {
            return null; // 不做任何操作，返回空节点
        }

        ListNode nextHead = cur.next;
        cur.next = null; // 断开 nextHead 的前一个节点和 nextHead 的连接
        return nextHead;
    }

    // 合并两个有序链表
    private ListNode[] mergeTwoLists2(ListNode l1, ListNode l2) {
        // 用哨兵节点简化代码逻辑
        ListNode dummyHead = new ListNode(0);
        // cur 指向新链表的末尾
        ListNode cur = dummyHead;

        while (l1 != null && l2 != null) {
            if (l1.val < l2.val) {
                cur.next = l1;
                l1 = l1.next;
            } else { // 注：相等的情况加哪个节点都是可以的
                cur.next = l2;
                l2 = l2.next;
            }
            cur = cur.next;
        }
        cur.next = l1 != null ? l1 : l2; // 拼接剩余链表
        while (cur.next != null) {
            cur = cur.next;
        }
        // 循环结束后，cur 是合并后的链表的尾节点
        return new ListNode[]{dummyHead.next, cur};
    }

    /**
     * 插入排序
     * 使用插入排序对链表进行排序。
     * 时间复杂度：O(n^2)，其中 n 是链表的长度。
     * 空间复杂度：O(1)。
     *
     * @param head 链表的头节点
     * @return 排序后的链表的头节点
     */
    public ListNode insertionSortList(ListNode head) {
        // 如果链表为空或只有一个节点，直接返回
        if (head == null || head.next == null) {
            return head;
        }

        // 创建一个虚拟头节点，方便后续操作
        ListNode dummyHead = new ListNode(0);
        dummyHead.next = head;

        // 从第二个节点开始遍历
        ListNode current = head.next;
        ListNode prev = head;

        while (current != null) {
            // 如果当前节点小于前一个节点，则需要重新插入
            if (current.val < prev.val) {
                // 断开当前节点
                prev.next = current.next;

                // 从头开始寻找插入位置
                ListNode insertPrev = dummyHead;
                while (insertPrev.next != null && insertPrev.next.val < current.val) {
                    insertPrev = insertPrev.next;
                }

                // 插入当前节点
                current.next = insertPrev.next;
                insertPrev.next = current;

                // 重新设置 current 和 prev
                current = prev.next;
            } else {
                // 移动到下一个节点
                prev = current;
                current = current.next;
            }
        }

        return dummyHead.next;
    }

    /**
     * 冒泡排序
     * 使用冒泡排序对链表进行排序。
     * 时间复杂度：O(n^2)，其中 n 是链表的长度。
     * 空间复杂度：O(1)。
     *
     * @param head 链表的头节点
     * @return 排序后的链表的头节点
     */
    public ListNode bubbleSortList(ListNode head) {
        // 如果链表为空或只有一个节点，直接返回
        if (head == null || head.next == null) {
            return head;
        }

        ListNode node_i = head;
        ListNode tail = null;

        // 外层循环次数为链表节点个数
        while (node_i != null) {
            ListNode node_j = head;

            // 内层循环进行相邻节点的比较和交换
            while (node_j != null && node_j.next != tail) {
                if (node_j.val > node_j.next.val) {
                    // 交换两个节点的值
                    int temp = node_j.val;
                    node_j.val = node_j.next.val;
                    node_j.next.val = temp;
                }
                node_j = node_j.next;
            }

            // 尾指针向前移动 1 位，此时尾指针右侧为排好序的链表
            tail = node_j;
            node_i = node_i.next;
        }

        return head;
    }

    /**
     * 冒泡排序
     * 使用冒泡排序对链表进行排序。
     * 时间复杂度：O(n^2)，其中 n 是链表的长度。
     * 空间复杂度：O(1)。
     *
     * @param head 链表的头节点
     * @return 排序后的链表的头节点
     */
    public ListNode bubbleSortList2(ListNode head) {
        // 如果链表为空或只有一个节点，直接返回
        if (head == null || head.next == null) {
            return head;
        }

        boolean swapped;
        ListNode tail = null;

        // 外层循环次数为链表节点个数
        do {
            swapped = false;
            ListNode node_i = head;

            // 内层循环进行相邻节点的比较和交换
            while (node_i != null && node_i.next != null && node_i.next != tail) {
                if (node_i.val > node_i.next.val) {
                    // 交换两个节点的值
                    int temp = node_i.val;
                    node_i.val = node_i.next.val;
                    node_i.next.val = temp;
                    swapped = true;
                }
                node_i = node_i.next;
            }

            // 更新尾指针为已排序部分的最后一个节点
            tail = node_i;

        } while (swapped);

        return head;
    }

    /**
     * 选择排序
     * 使用选择排序对链表进行排序。
     * 时间复杂度：O(n^2)，其中 n 是链表的长度。
     * 空间复杂度：O(1)。
     *
     * @param head 链表的头节点
     * @return 排序后的链表的头节点
     */
    public ListNode selectSortList(ListNode head) {
        // 如果链表为空或只有一个节点，直接返回
        if (head == null || head.next == null) {
            return head;
        }

        ListNode node_i = head;
        // node_i 为当前未排序链表的第一个链节点
        while (node_i != null && node_i.next != null) {
            // min_node 为未排序链表中的值最小节点
            ListNode min_node = node_i;
            ListNode node_j = node_i.next;
            while (node_j != null) {
                if (node_j.val < min_node.val) {
                    min_node = node_j;
                }
                node_j = node_j.next;
            }
            // 交换值最小节点与未排序链表中第一个节点的值
            if (node_i != min_node) {
                int temp = node_i.val;
                node_i.val = min_node.val;
                min_node.val = temp;
            }
            node_i = node_i.next;
        }

        return head;
    }

    /**
     * 快速排序
     * 使用快速排序对链表进行排序。
     * 时间复杂度：O(nlogn)，其中 n 是链表的长度。
     * 空间复杂度：O(logn)。
     * <p>
     * 从链表中找到一个基准值 pivot，这里以头节点为基准值。
     * 然后通过快慢指针 node_i、node_j 在链表中移动，使得 node_i 之前的节点值都小于基准值，node_i 之后的节点值都大于基准值。从而把数组拆分为左右两个部分。
     * 再对左右两个部分分别重复第二步，直到各个部分只有一个节点，则排序结束。
     *
     * @param head 链表的头节点
     * @return 排序后的链表的头节点
     */
    public ListNode quickSortList(ListNode head) {
        if (head == null || head.next == null) {
            return head;
        }
        return quickSort(head, null);
    }

    private ListNode quickSort(ListNode left, ListNode right) {
        if (left == right || left.next == right) {
            return left;
        }
        ListNode pi = partition(left, right);
        quickSort(left, pi);
        quickSort(pi.next, right);
        return left;
    }

    private ListNode partition(ListNode left, ListNode right) {
        // 左闭右开，区间没有元素或者只有一个元素，直接返回第一个节点
        if (left == right || left.next == right) {
            return left;
        }
        // 选择头节点为基准节点
        int pivot = left.val;
        // 使用 node_i, node_j 双指针，保证 node_i 之前的节点值都小于基准节点值，node_i 与 node_j 之间的节点值都大于等于基准节点值
        ListNode node_i = left, node_j = left.next;

        while (node_j != right) {
            // 发现一个小于基准值的元素
            if (node_j.val < pivot) {
                // 因为 node_i 之前节点都小于基准值，所以先将 node_i 向右移动一位（此时 node_i 节点值大于等于基准节点值）
                node_i = node_i.next;

                // 将小于基准值的元素 node_j 与当前 node_i(node_i 节点值大于等于基准节点值) 换位，换位后可以保证 node_i 之前的节点都小于等于基准节点值
                int temp = node_i.val;
                node_i.val = node_j.val;
                node_j.val = temp;
            }
            node_j = node_j.next;
        }
        // 将基准节点放到正确位置上
        int temp = node_i.val;
        node_i.val = left.val;
        left.val = temp;
        return node_i;
    }

    /**
     * 计数排序
     * 计数排序算法描述：
     * （1）使用 cur 指针遍历一遍链表。找出链表中最大值 list_max 和最小值 list_min。
     * （2）使用数组 counts 存储节点出现次数。
     * （3）再次使用 cur 指针遍历一遍链表。将链表中每个值为 cur.val 的节点出现次数，存入数组对应第 cur.val - list_min 项中。
     * （4）反向填充目标链表：
     * a. 建立一个哑节点 dummy_head，作为链表的头节点。使用 cur 指针指向 dummy_head。
     * b. 从小到大遍历一遍数组 counts。对于每个 counts[i] != 0 的元素建立一个链节点，值为 i + list_min，将其插入到 cur.next 上。并向右移动 cur。同时 counts[i] -= 1。直到 counts[i] == 0 后继续向后遍历数组 counts。
     * （5）将哑节点 dummy_dead 的下一个链节点 dummy_head.next 作为新链表的头节点返回。
     * <p>
     * 时间复杂度：O(n + k)，其中 n 是链表的长度，k 是值的范围。
     * 空间复杂度：O(k)。
     *
     * @param head 链表的头节点
     * @return 排序后的链表的头节点
     */
    public ListNode countingSortList(ListNode head) {
        if (head == null) {
            return head;
        }

        // 找出链表中最大值 list_max 和最小值 list_min
        int list_min = Integer.MAX_VALUE;
        int list_max = Integer.MIN_VALUE;
        ListNode cur = head;
        while (cur != null) {
            if (cur.val < list_min) {
                list_min = cur.val;
            }
            if (cur.val > list_max) {
                list_max = cur.val;
            }
            cur = cur.next;
        }

        int size = list_max - list_min + 1;
        int[] counts = new int[size];

        cur = head;
        while (cur != null) {
            counts[cur.val - list_min]++;
            cur = cur.next;
        }

        ListNode dummyHead = new ListNode(-1);
        cur = dummyHead;
        for (int i = 0; i < size; i++) {
            while (counts[i] > 0) {
                cur.next = new ListNode(i + list_min);
                counts[i]--;
                cur = cur.next;
            }
        }

        return dummyHead.next;
    }

    /**
     * 归并环节
     *
     * @param left  左侧链表头节点
     * @param right 右侧链表头节点
     * @return 合并后的链表头节点
     */
    private ListNode merge(ListNode left, ListNode right) {
        ListNode dummyHead = new ListNode(-1);
        ListNode cur = dummyHead;

        while (left != null && right != null) {
            if (left.val <= right.val) {
                cur.next = left;
                left = left.next;
            } else {
                cur.next = right;
                right = right.next;
            }
            cur = cur.next;
        }

        if (left != null) {
            cur.next = left;
        } else if (right != null) {
            cur.next = right;
        }

        return dummyHead.next;
    }

    /**
     * 归并排序
     *
     * @param head 链表头节点
     * @return 排序后的链表头节点
     */
    private ListNode mergeSort(ListNode head) {
        // 分割环节
        if (head == null || head.next == null) {
            return head;
        }

        // 快慢指针找到中心链节点
        ListNode slow = head;
        ListNode fast = head.next;
        while (fast != null && fast.next != null) {
            slow = slow.next;
            fast = fast.next.next;
        }

        // 断开左右链节点
        ListNode leftHead = head;
        ListNode rightHead = slow.next;
        slow.next = null;

        // 归并操作
        return merge(mergeSort(leftHead), mergeSort(rightHead));
    }

    /**
     * 桶排序
     * 使用桶排序对链表进行排序。
     * 时间复杂度：O(n + k)，其中 n 是链表的长度，k 是桶的数量。
     * 空间复杂度：O(k)。
     *
     * @param head       链表的头节点
     * @param bucketSize 桶的大小
     * @return 排序后的链表的头节点
     */
    public ListNode bucketSort(ListNode head, int bucketSize) {
        if (head == null) {
            return head;
        }

        // 找出链表中最大值 list_max 和最小值 list_min
        int listMin = Integer.MAX_VALUE;
        int listMax = Integer.MIN_VALUE;
        ListNode cur = head;
        while (cur != null) {
            if (cur.val < listMin) {
                listMin = cur.val;
            }
            if (cur.val > listMax) {
                listMax = cur.val;
            }
            cur = cur.next;
        }

        // 计算桶的个数，并定义桶
        int bucketCount = (listMax - listMin) / bucketSize + 1;
        ListNode[] buckets = new ListNode[bucketCount];

        // 将链表节点值依次添加到对应桶中
        cur = head;
        while (cur != null) {
            int index = (cur.val - listMin) / bucketSize;
            insertion(buckets, index, cur.val);
            cur = cur.next;
        }

        ListNode dummyHead = new ListNode(-1);
        cur = dummyHead;
        // 将元素依次出桶，并拼接成有序链表
        for (ListNode bucketHead : buckets) {
            bucketHead = mergeSort(bucketHead);
            while (bucketHead != null) {
                cur.next = bucketHead;
                cur = cur.next;
                bucketHead = bucketHead.next;
            }
        }

        return dummyHead.next;
    }

    /**
     * 将链表节点值 val 添加到对应桶 buckets[index] 中
     *
     * @param buckets 桶数组
     * @param index   桶的索引
     * @param val     节点值
     */
    private void insertion(ListNode[] buckets, int index, int val) {
        if (buckets[index] == null) {
            buckets[index] = new ListNode(val);
            return;
        }

        ListNode node = new ListNode(val);
        node.next = buckets[index];
        buckets[index] = node;
    }

    /**
     * 链表基数排序算法描述
     * (1)使用 cur 指针遍历链表，获取节点值位数最长的位数 size。
     * (2)从个位到高位遍历位数。因为 0 ~ 9 共有 10 位数字，所以建立 10 个桶。
     * (3)以每个节点对应位数上的数字为索引，将节点值放入到对应桶中。
     * (4)建立一个哑节点 dummy_head，作为链表的头节点。使用 cur 指针指向 dummy_head。
     * (5)将桶中元素依次取出，并根据元素值建立链表节点，并插入到新的链表后面。从而生成新的链表。
     * (6)之后依次以十位，百位，…，直到最大值元素的最高位处值为索引，放入到对应桶中，并生成新的链表，最终完成排序。
     * (7)将哑节点 dummy_dead 的下一个链节点 dummy_head.next 作为新链表的头节点返回。
     *
     * 时间复杂度：O(n * k)，其中 n 是链表的长度，k 是链表节点值的位数。
     * 空间复杂度：O(n + k)，其中 n 是链表的长度，k 是链表节点值的位数。
     *
     * @param head
     * @return
     */
    public ListNode radixSortList(ListNode head) {
        if (head == null || head.next == null) {
            return head;
        }

        // 分离正数和负数链表
        ListNode posHead = null, negHead = null;
        ListNode posTail = null, negTail = null;
        ListNode currentNode = head;

        while (currentNode != null) {
            ListNode nextNode = currentNode.next; // 提前保存下一个节点
            if (currentNode.val >= 0) {
                if (posHead == null) {
                    posHead = currentNode;
                    posTail = currentNode;
                } else {
                    posTail.next = currentNode;
                    posTail = currentNode;
                }
            } else {
                if (negHead == null) {
                    negHead = currentNode;
                    negTail = currentNode;
                } else {
                    negTail.next = currentNode;
                    negTail = currentNode;
                }
            }
            currentNode.next = null; // 断开原始链表连接
            currentNode = nextNode;
        }

        // 对正数链表进行基数排序
        if (posHead != null) {
            posHead = sortPositive(posHead);
        }

        // 对负数链表进行基数排序（绝对值排序后反转）
        if (negHead != null) {
            negHead = sortPositive(negHead);
            negHead = reverseList(negHead);
        }

        // 合并正数和负数链表
        if (negHead != null && posHead != null) {
            negTail.next = posHead;
            return negHead;
        } else if (negHead != null) {
            return negHead;
        } else {
            return posHead;
        }
    }


    private ListNode sortPositive(ListNode head) {
        if (head == null || head.next == null) {
            return head;
        }

        // 验证输入链表是否只包含正整数
        validatePositiveIntegers(head);

        int maxVal = getMaxValue(head);
        int size = getMaxDigits(maxVal);

        // 缓存最大值和位数
        int[] cachedValues = new int[]{maxVal, size};

        for (int i = 0; i < cachedValues[1]; i++) {
            ListNode[] buckets = new ListNode[10];
            ListNode[] bucketTails = new ListNode[10];

            ListNode cur = head, prev = null;
            head = null;

            while (cur != null) {
                int digit = getDigit(cur.val, i);
                if (buckets[digit] == null) {
                    buckets[digit] = cur;
                    bucketTails[digit] = cur;
                } else {
                    bucketTails[digit].next = cur;
                    bucketTails[digit] = cur;
                }
                prev = cur;
                cur = cur.next;
                prev.next = null;
            }

            ListNode dummyHead = new ListNode(-1);
            ListNode tail = dummyHead;
            for (int j = 0; j < 10; j++) {
                if (buckets[j] != null) {
                    tail.next = buckets[j];
                    tail = bucketTails[j];
                }
            }

            head = dummyHead.next;
        }

        return head;
    }

    // 验证链表中所有节点值是否为正整数
    private void validatePositiveIntegers(ListNode head) {
        ListNode cur = head;
        while (cur != null) {
            if (cur.val <= 0) {
                throw new IllegalArgumentException("链表中包含非正整数");
            }
            cur = cur.next;
        }
    }

    // 获取链表中的最大值
    private int getMaxValue(ListNode head) {
        int maxVal = Integer.MIN_VALUE;
        ListNode cur = head;
        while (cur != null) {
            maxVal = Math.max(maxVal, cur.val);
            cur = cur.next;
        }
        return maxVal;
    }

    // 获取最大值的位数
    private int getMaxDigits(int maxVal) {
        int size = 0;
        while (maxVal > 0) {
            maxVal /= 10;
            size++;
        }
        return size;
    }

    // 获取指定位置的数字
    private int getDigit(int val, int pos) {
        return (val / (int) Math.pow(10, pos)) % 10;
    }

    private ListNode reverseList(ListNode head) {
        ListNode prev = null, curr = head, next = null;
        while (curr != null) {
            next = curr.next;
            curr.next = prev;
            prev = curr;
            curr = next;
        }
        return prev;
    }


    public static void main(String[] args) {
        _148排序链表 obj = new _148排序链表();
        int[] nums = {31, 5, 32, 33, 12, 7, 6, 10, 9, 11, 5, 4, 2, 40, 41, 42, 1, 3, -2, 0, -1, 14, 13, 8, 15, 16, 17, 18, 19, 20, 21, 56, 22, 23, 24, 25, 26, 78, 27, 28, 29, 30, 34, 35, 36, 37, 38, 39, 43, 44, 58, 45, 46};
        String res = "[-2,-1,0,1,2,3,4,5,5,6,7,8,9,10,11,12,13,14,15,16,17,18,19,20,21,22,23,24,25,26,27,28,29,30,31,32,33,34,35,36,37,38,39,40,41,42,43,44,45,46,56,58,78]";
        Times.test("解答一：归并排序（递归法）", () -> {
            ListNode head = LinkedListUtil.buildLinkedList(nums);
            Asserts.test(LinkedListUtil.toString(obj.sortList(head)).equals(res));
        });

        Times.test("解答二：归并排序（迭代法）（从底至顶直接合并）", () -> {
            ListNode head2 = LinkedListUtil.buildLinkedList(nums);
            Asserts.test(LinkedListUtil.toString(obj.sortList2(head2)).equals(res));
        });

        Times.test("插入排序", () -> {
            ListNode head3 = LinkedListUtil.buildLinkedList(nums);
            Asserts.test(LinkedListUtil.toString(obj.insertionSortList(head3)).equals(res));
        });

        Times.test("冒泡排序", () -> {
            ListNode head4 = LinkedListUtil.buildLinkedList(nums);
            Asserts.test(LinkedListUtil.toString(obj.bubbleSortList(head4)).equals(res));
        });
        Times.test("冒泡排序2", () -> {
            ListNode head4 = LinkedListUtil.buildLinkedList(nums);
            Asserts.test(LinkedListUtil.toString(obj.bubbleSortList2(head4)).equals(res));
        });

        Times.test("选择排序", () -> {
            ListNode head5 = LinkedListUtil.buildLinkedList(nums);
            Asserts.test(LinkedListUtil.toString(obj.selectSortList(head5)).equals(res));
        });

        Times.test("快速排序", () -> {
            ListNode head = LinkedListUtil.buildLinkedList(nums);
            Asserts.test(LinkedListUtil.toString(obj.quickSortList(head)).equals(res));
        });

        Times.test("计数排序", () -> {
            ListNode head = LinkedListUtil.buildLinkedList(nums);
            Asserts.test(LinkedListUtil.toString(obj.countingSortList(head)).equals(res));
        });

        Times.test("桶排序", () -> {
            ListNode head = LinkedListUtil.buildLinkedList(nums);
            Asserts.test(LinkedListUtil.toString(obj.bucketSort(head, 5)).equals(res));
        });

        Times.test("基数排序", () -> {
            int[] nums2 = {4,2,1,3,-1,5};
            String res2 = "[-1,1,2,3,4,5]";
            ListNode head = LinkedListUtil.buildLinkedList(nums2);
            Asserts.test(LinkedListUtil.toString(obj.radixSortList(head)).equals(res2));
        });

    }
}


