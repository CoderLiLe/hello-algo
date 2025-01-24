package 栈_队列;

import tools.Asserts;

import java.util.Stack;

public class _071简化路径 {
    /**
     * 简化文件路径
     * 该方法接受一个字符串形式的文件路径，返回其简化后的路径
     * 例如，输入"/home/"，返回"/home"；输入"/a/./b/../../c/"，返回"/c"
     *
     * @param path 待简化的文件路径字符串
     * @return 简化后的文件路径字符串
     */
    public String simplifyPath(String path) {
        // 分割输入的路径字符串为各个部分
        String[] parts = path.split("/");
        // 使用栈来辅助处理路径
        Stack<String> stk = new Stack<>();
        // 借助栈计算最终的文件夹路径
        for (String part : parts) {
            // 忽略空的部分和当前目录标识"."
            if (part.isEmpty() || part.equals(".")) {
                continue;
            }
            // 处理上一级目录标识".."
            if (part.equals("..")) {
                // 如果栈不为空，则弹出栈顶元素，表示返回上一级目录
                if (!stk.isEmpty()) {
                    stk.pop();
                }
                continue;
            }
            // 将有效目录部分压入栈中
            stk.push(part);
        }
        // 栈中存储的文件夹组成路径
        StringBuilder res = new StringBuilder();
        // 从栈中取出文件夹，构建最终的路径字符串
        // stack在java里面其实是List，两头用
        for (String s : stk) {
            res.append("/");
            res.append(s);
        }

        // 如果最终路径为空，则返回根路径"/"，否则返回构建的路径
        return 0 == res.length() ? "/" : res.toString();
    }

    public static void main(String[] args) {
        _071简化路径 obj = new _071简化路径();
        String path = "/home/";
        Asserts.test("/home".equals(obj.simplifyPath(path)));
    }

}
