package 字符串;

import tools.Asserts;

import java.util.regex.Pattern;

/**
 * 描述
 * 你需要书写一个程序验证给定的密码是否合格。
 *
 * 合格的密码要求：
 * 长度不超过 8 位
 * 必须包含大写字母、小写字母、数字、特殊字符中的至少三种
 * 不能分割出两个独立的、长度大于 2 的连续子串，使得这两个子串完全相同；更具体地，如果存在两个长度大于 2 的独立子串，他们相等，那么密码不合法。
 *
 * 子串为从原字符串中，连续的选择一段字符（可以全选、可以不选）得到的新字符串。
 */
public class HJ20密码验证合格程序 {
    public static void main(String[] args) {
        Asserts.test(check("021Abc9000") == true);
        Asserts.test(check("021Abc9Abc1") == false);
        Asserts.test(check("021ABC9000") == false);
        Asserts.test(check("021$bc9000") == true);
        Asserts.test(check("021Abc1111") == true);
    }

    private static boolean check(String str) {
        if(str.length() <= 8){
            return false;
        }
        if(!getMatch(str)){
            return false;
        }
        if(!getString(str, 0, 3)){
            return false;
        }
        return true;
    }

    // 校验是否有重复子串
    private static boolean getString(String str, int l, int r) {
        if (r >= str.length()) {
            return true;
        }
        if (str.substring(r).contains(str.substring(l, r))) {
            return false;
        } else {
            return getString(str,l+1,r+1);
        }
    }

    // 检查是否满足正则
    private static boolean getMatch(String str){
        int count = 0;
        Pattern p1 = Pattern.compile("[A-Z]");
        if(p1.matcher(str).find()){
            count++;
        }
        Pattern p2 = Pattern.compile("[a-z]");
        if(p2.matcher(str).find()){
            count++;
        }
        Pattern p3 = Pattern.compile("[0-9]");
        if(p3.matcher(str).find()){
            count++;
        }
        Pattern p4 = Pattern.compile("[^a-zA-Z0-9]");
        if(p4.matcher(str).find()){
            count++;
        }
        if(count >= 3){
            return true;
        }else{
            return false;
        }
    }
}
