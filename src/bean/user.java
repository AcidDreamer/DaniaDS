package bean;

import java.io.Serializable;

public class user implements Serializable {
	private static final long serialVersionUID = -1355807851822025659L;
	private String username;
	private String fullname;
	private String role;
	private int Elegxos;
	private int Kataxwrisi;
	private int Ypologismos;
	private int Tropopoiisi;
	private int Egkrisi;
	private int isAdmin;
	private int isClient;


	public user(String username, String fullname, String role, int elegxos, int kataxwrisi, int ypologismos,
			int tropopoiisi, int egkrisi, int isAdmin, int isClient) {
		super();
		this.username = username;
		this.fullname = fullname;
		this.role = role;
		Elegxos = elegxos;
		Kataxwrisi = kataxwrisi;
		Ypologismos = ypologismos;
		Tropopoiisi = tropopoiisi;
		Egkrisi = egkrisi;
		this.isAdmin = isAdmin;
		this.isClient = isClient;
	}

	public void setUsername(String username) {
		this.username = username;
	}

	public void setFullname(String fullname) {
		this.fullname = fullname;
	}

	public void setRole(String role) {
		this.role = role;
	}

	public String getUsername() {
		return username;
	}

	public String getFullname() {
		return fullname;
	}

	public String getRole() {
		return role;
	}
	
	public int getElegxos() {
		return Elegxos;
	}

	public void setElegxos(int elegxos) {
		Elegxos = elegxos;
	}

	public int getKataxwrisi() {
		return Kataxwrisi;
	}

	public void setKataxwrisi(int kataxwrisi) {
		Kataxwrisi = kataxwrisi;
	}

	public int getYpologismos() {
		return Ypologismos;
	}

	public void setYpologismos(int ypologismos) {
		Ypologismos = ypologismos;
	}

	public int getTropopoiisi() {
		return Tropopoiisi;
	}

	public void setTropopoiisi(int tropopoiisi) {
		Tropopoiisi = tropopoiisi;
	}

	public int getEgkrisi() {
		return Egkrisi;
	}

	public void setEgkrisi(int egkrisi) {
		Egkrisi = egkrisi;
	}

	public int getIsAdmin() {
		return isAdmin;
	}

	public void setIsAdmin(int isAdmin) {
		this.isAdmin = isAdmin;
	}

	public int getIsClient() {
		return isClient;
	}

	public void setIsClient(int isClient) {
		this.isClient = isClient;
	}

	@Override
	public String toString() {
		return "Name=" + this.username + " Role=" + this.role + " Full Name=" + this.fullname;
	}
}
