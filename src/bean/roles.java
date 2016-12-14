package bean;

public class roles {
	//δημιουργούμαι ένα select με όλα τα roles
	private String rolesSelect="<select name=\"allRoles\" class=\"allRoles\">";


	public String getRolesSelect() {
		return rolesSelect;
	}

	public void setRolesSelect(String rolesSelect) {
		this.rolesSelect = rolesSelect;
	}
	
	public void appendRole(String aRole){
		rolesSelect+= aRole;
	}
	
	public void endRole(){
		rolesSelect+= "</select>";
	}
	
}
