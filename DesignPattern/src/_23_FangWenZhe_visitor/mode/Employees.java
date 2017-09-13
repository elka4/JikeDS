package _23_FangWenZhe_visitor.mode;

import java.util.HashMap;

public class Employees {
	private HashMap<String, Employee> employees;

	public Employees() {
		employees = new HashMap();
	}

	public void Attach(Employee employee) {
		employees.put(employee.getName(), employee);
	}

	public void Detach(Employee employee) {
		employees.remove(employee);
	}

	public Employee getEmployee(String name) {
		return employees.get(name);
	}

	public void Accept(Visitor _23_FangWenZhe_visitor) {
		for (Employee e : employees.values()) {
			e.Accept(_23_FangWenZhe_visitor);
		}
	}
}
