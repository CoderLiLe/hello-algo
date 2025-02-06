""""
选择排序工作原理：首先在未排序序列中找最小（大）元素，存放到排序序列的起始位置，
                然后再从剩余未排序元素中继续寻找最小（大）元素，然后放到已排
                序序列的末尾。以此类推，直到所有元素均排序完毕。


最优时间复杂度：O(n2)
最坏时间复杂度：O(n2)
稳定性：不稳定（考虑升序每次选择最大的情况）
"""

def select_sort(alist):
    n = len(alist)
    for j in range(n-1): # j: 0 ~ n-2
        min_index = j
        for i in range(j+1, n):
            if alist[min_index] > alist[i]:
                min_index = i
        if min_index != j:
            alist[j], alist[min_index] = alist[min_index], alist[j]


if __name__ == '__main__':
    li = [54, 26, 93, 17, 77, 31, 44, 55, 20]
    print(li)
    select_sort(li)
    print(li)