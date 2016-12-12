package bean;

public class application {

	int app_code;
	int amount;
	String buy_Type;
	String drivers_licence;
	int taxes;
	int status;
	
	
	
	public application(int app_code, int amount, String buy_Type, String drivers_licence, int taxes) {
		super();
		this.app_code = app_code;
		this.amount = amount;
		this.buy_Type = buy_Type;
		this.drivers_licence = drivers_licence;
		this.taxes = taxes;
		this.status = 0;
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

}
