package com.lile.tools;

import java.text.SimpleDateFormat;
import java.util.Date;

public class Times {
	private static final SimpleDateFormat fmt = new SimpleDateFormat("HH:mm:ss.SS");
	
	public interface Task {
		void execute();
	}

	public static void test(String title, Task task) {
		if (task == null) return;
		title = (title == null) ? "" : ("【" + title + "】");
		System.out.println(title);
		System.out.println("开始：" + fmt.format(new Date()));
		long begin = System.nanoTime();
		task.execute();
		long end = System.nanoTime();
		System.out.println("结束：" + fmt.format(new Date()));
		double delta = (end - begin) / 1000000.0;
		System.out.println("耗时：" + delta + "毫秒");
		System.out.println("---------------------------------------------");
	}

}
