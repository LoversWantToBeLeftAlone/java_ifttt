package Dao;

import java.util.ArrayList;

import task.TASK;

@SuppressWarnings("serial")
public class consumer implements java.io.Serializable {

	public ArrayList<TASK> taskList = new ArrayList<TASK>(); // ������洢TASK��һ���û�һ�������

	private String id;// �˺�
	private String name;
	private String passwords;
	private String sex;
	private String idnumber;// ���֤
	private String address;
	private int level;// ��Ա�ȼ�
	private int money;// ӵ�н�Ǯ

	private int num_of_task = 0; // ���û�����������
	private boolean flag = false; // ���û���������ִ�еı�־

	public String getID() {
		return this.id;
	}

	public String getName() {
		return this.name;
	}

	public String getPasswords() {
		return this.passwords;
	}

	public String getSex() {
		return this.sex;
	}

	public String getIdnumber() {
		return this.idnumber;
	}

	public int getLevel() {
		return this.level;
	}

	public int getMoney() {
		return this.money;
	}

	public String getAddr() {
		return this.address;
	}

	public boolean getFlag() {
		return this.flag;
	}

	public int getNum_of_task() {
		return this.num_of_task;
	}

	public void setIdAndPass(String id, String pass) {
		this.id = id;
		this.passwords = pass;
	}

	public void setName(String name) {
		this.name = name;
	}

	public void setNameAndPass(String name, String pass) {
		this.name = name;
		this.passwords = pass;
	}

	public void setSex(String sex) {
		this.sex = sex;
	}

	public void setIdnumber(String idnumber) {
		this.idnumber = idnumber;
	}

	public void setAddress(String addr) {
		this.address = addr;
	}

	public void setLevelAndMoney(int level, int money) {
		this.level = level;
		this.money = money;
	}

	public void setFlag(boolean a) {
		this.flag = a;
	}

	public void addNum_of_task() {
		this.num_of_task++;
	}

	public consumer() {
	}

	/* ����һ������(����û��level) */
	public void C(String id, String name, String sex, String idnumber,
			String pass, String addr, int money, int level) {
		this.id = id;
		this.name = name;
		this.passwords = pass;
		this.sex = sex;
		this.address = addr;
		this.money = money;
		this.level = level;
		this.idnumber = idnumber;
	}

	public void setMoney(int money) {
		this.money += money;
	}

	public void copy(consumer c) {
		this.name = c.getName();
		this.level = c.getLevel();
		this.money = c.getMoney();
	}

	public consumer(String name, int level, int money) {
		this.name = name;
		this.level = level;
		this.money = money;
	}

}
