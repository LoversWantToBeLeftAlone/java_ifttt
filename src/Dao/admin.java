package Dao;

import java.util.ArrayList;

public class admin extends consumer {
	
	public ArrayList<consumer> consumerList = new ArrayList<consumer>();

	public void delete(consumer c) {// ɾ��һ���˿�
		consumerList.remove(c);
	}

	public void add(consumer c) {// ���һ���˿�
		consumerList.add(c);
	}

	public consumer getmember(int index) {
		consumer c = consumerList.get(index);
		return c;
	}

	public void modify(consumer oldc, consumer newc) {// �˿�����Ϣ�滻�˿;���Ϣ
		int index = getIndex(oldc);
		while (index != -1)
			consumerList.set(index, newc);
	}

	public int getIndex(consumer c) {// ����ĳ���˿͵��������е�index
		return consumerList.indexOf(c);
	}

	public boolean search(consumer c) {// ����ҵ��ͷ���true
		return consumerList.contains(c) ? true : false;
	}
}
