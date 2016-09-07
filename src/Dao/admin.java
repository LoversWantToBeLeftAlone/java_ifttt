package Dao;

import java.util.ArrayList;

public class admin extends consumer {
	
	public ArrayList<consumer> consumerList = new ArrayList<consumer>();

	public void delete(consumer c) {// 删除一名顾客
		consumerList.remove(c);
	}

	public void add(consumer c) {// 添加一名顾客
		consumerList.add(c);
	}

	public consumer getmember(int index) {
		consumer c = consumerList.get(index);
		return c;
	}

	public void modify(consumer oldc, consumer newc) {// 顾客新信息替换顾客旧信息
		int index = getIndex(oldc);
		while (index != -1)
			consumerList.set(index, newc);
	}

	public int getIndex(consumer c) {// 返回某个顾客的在链表中的index
		return consumerList.indexOf(c);
	}

	public boolean search(consumer c) {// 如果找到就返回true
		return consumerList.contains(c) ? true : false;
	}
}
