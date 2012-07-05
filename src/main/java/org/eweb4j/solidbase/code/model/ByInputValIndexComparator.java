package org.eweb4j.solidbase.code.model;

import java.util.Arrays;
import java.util.Comparator;

/**
 * 排序. 现有三行数据: BACDFE ABCDEF DDEASE
 * 
 * 输入一个'A'字符 inputVal-->'A'.
 * 
 * 按'A'所在位置进行排序 after sort: ABCDEF BACDFE DDEASE
 * 
 * 
 * @author weiwei
 * 
 */
public class ByInputValIndexComparator implements Comparator<Code> {
	public int compare(Code o1, Code o2) {
		if (o1 == null || o2 == null)
			return 0;

		String o1Val = o1.getCodeValue();
		String o2Val = o2.getCodeValue();
		if (o1Val == null && o2Val != null)
			return -1;

		if (o2Val == null && o1Val != null)
			return 1;

		if (o1Val == null && o2Val == null)
			return 0;

		int o1_index = o1Val.indexOf(o1.getInputValue());
		int o2_index = o2Val.indexOf(o2.getInputValue());
		if (o1_index > -1 && o2_index > -1) {
			int diff = o1_index - o2_index;
			if (diff > 0)
				return 1;
			if (diff < 0)
				return -1;
		}
		
		return 0;
	}

	public static void main(String[] args) {
		Code c1 = new Code();
		c1.setCodeValue("BACDFE");
		Code c2 = new Code();
		c2.setCodeValue("ABCDEF");
		Code c3 = new Code();
		c3.setCodeValue("DDEASE");

		Code[] codes = new Code[] { c1, c2 };
		for (Code c : codes)
			c.setInputValue("B");

		System.out.println("before sort:");
		System.out.println(codes[0].getCodeValue());
		System.out.println(codes[1].getCodeValue());
		//System.out.println(codes[2].getCodeValue());

		System.out.println("after sort:");
		Arrays.sort(codes, new ByInputValIndexComparator());

		System.out.println(codes[0].getCodeValue());
		System.out.println(codes[1].getCodeValue());
		//System.out.println(codes[2].getCodeValue());
	}

}
