package bean;

public class client {
	String username;
	String fullname;
	int afm;
	int adt;
	int salary;
	int  phone;
	String loanInfo = "";

	public client(String username, String fullname, int afm, int adt, int salary, int phone, int id) {
		super();
		this.username = username;
		this.fullname = fullname;
		this.afm = afm;
		this.adt = adt;
		this.salary = salary;
		this.phone = phone;
		this.id = id;
	}

	public String getUsername() {
		return username;
	}

	public void setUsername(String username) {
		this.username = username;
	}

	public String getFullname() {
		return fullname;
	}

	public void setFullname(String fullname) {
		this.fullname = fullname;
	}

	public int getAfm() {
		return afm;
	}

	public void setAfm(int afm) {
		this.afm = afm;
	}

	public int getAdt() {
		return adt;
	}

	public void setAdt(int adt) {
		this.adt = adt;
	}

	public int getSalary() {
		return salary;
	}

	public void setSalary(int salary) {
		this.salary = salary;
	}

	public int getPhone() {
		return phone;
	}

	public void setPhone(int phone) {
		this.phone = phone;
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	int id;

	@Override
	public String toString() {
		String string = "Client Full Name: " + this.fullname + " ID: " + this.id + "<br> Username : " + this.username
				+ " AFM : " + this.afm + "<br> ADT : " + this.adt + " Salary : " + this.salary + " Phone : "
				+ this.phone;
		return string;
	}

	public String giveLoan() {
		if (this.salary < 400) {
			loanInfo += "The customer cannot take a loan";
		} else {
			if (this.salary < 800 && salary >= 400) {
				loanInfo += "Customer can take a loan of 2000 euros and repay it in 1-3 years.Max CC is 1400cc<br>";
				System.out.println("passed");
			}
			if (this.salary < 1200 && salary >= 800) {
				loanInfo += "Customer can take a loan of 2000 euros and repay it in 1-3 years.Max CC is 1400cc<br>"+
							"Customer can take a loan of 4000 euros and repay it in 1-4 years.Max CC is 1600cc<br>";
			}
			if (this.salary < 1800 && salary >= 1200) {
				loanInfo += "Customer can take a loan of 2000 euros and repay it in 1-3 years.Max CC is 1400cc<br>"+
						"Customer can take a loan of 4000 euros and repay it in 1-4 years.Max CC is 1600cc<br>" +
						"Customer can take a loan of 8000 euros and repay it in 1-5 years.Max CC is 2000cc<br>";
			}
			if (this.salary >= 1800) {
				loanInfo += "Customer can take a loan of 2000 euros and repay it in 1-3 years.Max CC is 1400cc<br>"+
						"Customer can take a loan of 4000 euros and repay it in 1-4 years.Max CC is 1600cc<br>" +
						"Customer can take a loan of 8000 euros and repay it in 1-5 years.Max CC is 2000cc<br>"+
						"Customer can take a loan of 15000 euros and repay it in 1-10 years.Max CC is 2500cc<br>";
			}
		}
		return loanInfo;
	}

}
