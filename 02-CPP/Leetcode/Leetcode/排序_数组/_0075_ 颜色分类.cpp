//
//  _0075_ 颜色分类.cpp
//  Leetcode
//
//  Created by LiLe on 2021/7/21.
//

#include "_0075_ 颜色分类.hpp"

/**
 参考：https://leetcode-cn.com/problems/sort-colors/solution/kuai-su-pai-xu-partition-guo-cheng-she-ji-xun-huan/
 */

void sortColors(vector<int>& nums) {
    int red = 0;
    int white = 0;
    int blue = nums.size() - 1;
    
    while (white <= blue) {
        int val = nums[white];
        if (0 == val) { // 遇到0：红白指针交换，然后红白指针++
            swap(nums[red++], nums[white++]);
        } else if (1 == val) { // 遇到1：跳过，白色指针++
            white++;
        } else { // 遇到2：白蓝指针交换，然后蓝色指针--，再次对白色指针的值判断
            swap(nums[white], nums[blue--]);
        }
    }
}
