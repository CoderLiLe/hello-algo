package 字符串;

import tools.Asserts;

/**
 * 给定一个非空字符串S，其被N个‘-’分隔成N+1的子串，给定正整数K，要求除第一个子串外，其余的子串每K个字符组成新的子串，并用‘-’分隔。
 * 对于新组成的每一个子串，如果它含有的小写字母比大写字母多，则将这个子串的所有大写字母转换为小写字母；反之，如果它含有的大写字母比小写字母多，
 * 则将这个子串的所有小写字母转换为大写字母；大小写字母的数量相等时，不做转换。
 * 输入描述:
 * 输入为两行，第一行为参数K，第二行为字符串S。
 * 输出描述:
 * 输出转换后的字符串
 *
 * 示例1
 * 输入
 * 3
 * 12abc-abCABc-4aB@
 * 输出
 * 12abc-abc-ABC-4aB-@
 * 说明
 * 子串为12abc、abCABc、4aB@，第一个子串保留，后面的子串每3个字符一组为abC、ABc、4aB、@，abC中小写字母较多，转换为abc，ABc中大写字母较多，
 * 转换为ABC，4aB中大小写字母都为1个，不做转换，@中没有字母，连起来即12abc-abc-ABC-4aB-@
 * 示例2
 * 输入
 * 12
 * 12abc-abCABc-4aB@
 * 输出
 * 12abc-abCABc4aB@
 * 说明
 * 子串为12abc、abCABc、4aB@，第一个子串保留，后面的子串每12个字符一组为abCABc4aB@，这个子串中大小写字母都为4个，不做转换，
 * 连起来即12abc-abCABc4aB@
 */
public class 字符串分割 {

    public static void main(String[] args) {
        Asserts.test(stringSplit("12abc-abCABc-4aB@", 3).equals("12abc-abc-ABC-4aB-@"));
        Asserts.test(stringSplit("12abc-abCABc-4aB@", 12).equals("12abc-abCABc4aB@"));
    }

    public static String stringSplit(String s, int k) {

        String[] split = s.split("-");
        StringBuilder str = new StringBuilder();
        for (int i = 1; i < split.length; i++) {
            str.append(split[i]);
        }

        StringBuilder sb = new StringBuilder();
        sb.append(split[0]);

        int start = 0;
        int end = start + k;
        while (start < str.length()) {
            if (end > str.length()) {
                sb.append(convertStr(str.substring(start, str.length())));
            } else {
                sb.append(convertStr(str.substring(start, end)));
            }
            start = end;
            end += k;
        }

        return sb.toString();
    }

    private static String convertStr(String substring) {
        // 对于新组成的每一个子串，如果它含有的小写字母比大写字母多，则将这个子串的所有大写字母转换为小写字母；
        // 反之，如果它含有的大写字母比小写字母多，则将这个子串的所有小写字母转换为大写字母；
        // 大小写字母的数量相等时，不做转换。
        int upNum = 0;
        int lowNum = 0;
        for (int j = 0; j < substring.length(); j++) {
            char c = substring.charAt(j);
            if (c >= 'A' && c <= 'Z') {
                upNum++;
            } else if (c >= 'a' && c <= 'z') {
                lowNum++;
            } else {
                // do nothing
            }
        }

        // 用‘-’分隔
        if (upNum > lowNum) {
            return "-" + substring.toUpperCase();
        } else if (upNum < lowNum) {
            return "-" + substring.toLowerCase();
        } else {
            return "-" + substring;
        }
    }
}
