package 字符串;

import tools.Asserts;

import java.util.ArrayList;
import java.util.List;

public class _006Z字形变换 {
    public String convert(String s, int numRows) {
        if (numRows < 2) {
            return s;
        }
        List<StringBuilder> rows = new ArrayList<>();
        for (int i = 0; i < numRows; i++) {
            rows.add(new StringBuilder());
        }
        int i = 0, flag = -1;
        for (char c : s.toCharArray()) {
            rows.get(i).append(c);
            if (i == 0 || i == numRows - 1) {
                flag = -flag;
            }
            i += flag;
        }
        StringBuilder res = new StringBuilder();
        for (StringBuilder row : rows) {
            res.append(row);
        }
        return res.toString();
    }

    public static void main(String[] args) {
        _006Z字形变换 z = new _006Z字形变换();
        String s = "PAYPALISHIRING";
        int numRows = 3;
        Asserts.test(z.convert(s, numRows).equals("PAHNAPLSIIGYIR"));
    }
}
