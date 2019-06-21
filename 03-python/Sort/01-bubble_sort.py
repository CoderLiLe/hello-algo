"""
冒泡排序算法步骤：
（1）比较相邻的元素：如果第一个比较第二个大（升序），就交换他们两个。
（2）对每一对相邻元素做同样的工作，从开始第一对到结尾的最后一对。这步做完后，最后的元素将是最大的数。
（3）针对所有的元素重复以上步骤，除了最后一个元素。
（4）持续每次对越来越少的元素重复上面的步骤，直到没有任何一对数字需要比较。

最优时间复杂度：O(n) （表示遍历一次发现没有任何可以交换的元素，排序结束。）
最坏时间复杂度：O(n2)
稳定性：稳定

"""

def bubble_sort(alist):
    """冒泡排序"""
    n = len(alist)
    for j in range(n-1):
        count = 0
        for i in range(0, n-1-j):
            if alist[i] > alist[i+1]:
                alist[i], alist[i+1] = alist[i+1], alist[i]
                count += 1

        if 0 == count:
            return

if __name__ == '__main__':
    li = [54, 26, 93, 17, 77, 31, 44, 55, 20]
    print(li)
    bubble_sort(li)
    print(li)