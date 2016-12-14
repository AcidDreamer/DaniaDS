package bean;

import bean.client;

public class application {

	int app_code; //
	int amount; //
	String buy_Type;
	String drivers_licence; //
	int taxes; //
	int status; //
	String username; //
	int repayTime;
	String tekmiriwsi; //
	String tekmiriwsiEdit;
	int accepted; //
	client Client;//

	public application(int app_code, int amount, String buy_Type, String drivers_licence, int taxes, String username,int repayTime ,String tekmiriwsi,String tekmiriwsiEdit) {
		super();
		this.app_code = app_code;
		this.amount = amount;
		this.buy_Type = buy_Type;
		this.drivers_licence = drivers_licence;
		this.taxes = taxes;
		this.username = username;
		this.repayTime = repayTime;
		this.tekmiriwsi = tekmiriwsi;
		this.status = 0;
		this.accepted = 0;
		this.tekmiriwsiEdit =tekmiriwsiEdit;
	}
	
	//Έλεγχος για το τι δάνειο δικαιούται ο πελάτης
	public boolean canGetLoad(client Client) {
		if(Client.getSalary() < 400){
			return false;
		}
		if((amount <= 2000 && (repayTime==1 || repayTime==2 || repayTime==3 ))){
			if(Client.getSalary() >= 400){
				return true;
			}
		}
		if((amount <= 4000 && (repayTime==1 || repayTime==2 || repayTime==3 || repayTime==4))){
			if(Client.getSalary() >= 800){
				return true;
			}
		}
		if((amount <= 8000 && (repayTime==1 || repayTime==2 || repayTime==3 || repayTime==4 || repayTime==5))){
			if(Client.getSalary() >= 1200){
				return true;
			}
		}
		if((amount <= 15000 && (repayTime==1 || repayTime==2 || repayTime==3 || repayTime==4 || repayTime==5 || repayTime==6 || repayTime==7|| repayTime==8 || repayTime==8 || repayTime==9 || repayTime ==10))){
			if(Client.getSalary() >= 1800){
				return true;
			}
		}
		return false;
	}
	//Άμα ο διευθυντής μπορεί να απορρίψει την αίτηση
	public boolean canBeDisproved(client Client) {
		int salary = Client.getSalary();
		if(amount<=2000 && salary>=800){
			return false;
		}
		if(amount<=4000 && salary>=1200){
			return false;
		}
		if(amount<=8000 && salary>=1800){
			return false;
		}
		return true;
	}

	//Getters & Setters
	public String getUsername() {
		return username;
	}

	public void setUsername(String username) {
		this.username = username;
	}

	public int getRepayTime() {
		return repayTime;
	}

	public void setRepayTime(int repayTime) {
		this.repayTime = repayTime;
	}

	public int getApp_code() {
		return app_code;
	}

	public void setApp_code(int app_code) {
		this.app_code = app_code;
	}

	public int getAmount() {
		return amount;
	}

	public void setAmount(int amount) {
		this.amount = amount;
	}

	public String getBuy_Type() {
		return buy_Type;
	}

	public void setBuy_Type(String buy_Type) {
		this.buy_Type = buy_Type;
	}

	public String getDrivers_licence() {
		return drivers_licence;
	}

	public void setDrivers_licence(String drivers_licence) {
		this.drivers_licence = drivers_licence;
	}

	public int getTaxes() {
		return taxes;
	}

	public void setTaxes(int taxes) {
		this.taxes = taxes;
	}

	public int getStatus() {
		return status;
	}

	public void setStatus(int status) {
		this.status = status;
	}

	public String getTekmiriwsi() {
		return tekmiriwsi;
	}

	public void setTekmiriwsi(String tekmiriwsi) {
		this.tekmiriwsi = tekmiriwsi;
	}

	public String getTekmiriwsiEdit() {
		return tekmiriwsiEdit;
	}

	public void setTekmiriwsiEdit(String tekmiriwsiEdit) {
		this.tekmiriwsiEdit = tekmiriwsiEdit;
	}
	

}
