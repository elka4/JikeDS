package jike;

/**
 * 部门
 */
public class Course16_2_Department {
	private int id;
	
	public int getId() {
		return id;
	}
	
	public void setId(int id) {
		this.id = id;
	}
	
	public Course16_2_Department(int id) {
		super();
		this.id = id;
	}
	
	@Override
	public String toString() {
		return "Department [id=" + id + "]";
	}

}
