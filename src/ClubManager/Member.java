package ClubManager;

public class Member {

	private String name;
	private CustomDate birthday;
	private String mobile;
	private String pass;
	private Fee fee;
	private String address;
	private String email;
		
	public Member(String s) {
		String detail;
		String multiAddress;
		StringBuilder sbAddress = new StringBuilder();
		boolean completeAddress = false;
		
		String[] temp = s.trim().split(";");
		
		for(int i = 0; i < temp.length; i++) {
			temp[i] = temp[i].trim();
		}
		
		for(int i = 0; i < temp.length; i++) {
			
			if(temp[i].startsWith("name ")) {
				detail = temp[i];
				detail = detail.replace("name ", "");
				detail = detail.trim();
				if (validName(detail)) {
					name = detail;
				}
			}
			else if(temp[i].startsWith("birthday ")) {
				detail = temp[i];
				detail = detail.replace("birthday ", "");
				detail = detail.trim();
				birthday = new CustomDate(detail);
			}
			else if(temp[i].startsWith("mobile ")) {
				detail = temp[i];
				detail = detail.replace("mobile ", "");
				detail = detail.trim();
				if (validMobile(detail)) {
					mobile = detail;
				}
			}
			else if(temp[i].startsWith("pass ")) {
				detail = temp[i];
				detail = detail.replace("pass ", "");
				detail = detail.trim();
				if (validPass(detail)) {
					pass = detail;
				}
			}
			else if(temp[i].startsWith("fee ")) {
				detail = temp[i];
				detail = detail.replace("fee ", "");
				detail = detail.trim();
				fee = new Fee(detail);
			}
			else if(temp[i].startsWith("address ")) {
				detail = temp[i];
				detail = detail.replace("address ", "");
				detail = detail.trim();
				if (detail.matches(".*[0-9]{4}")) {
					completeAddress = true;
					if (validAddress(detail)) {
						address = detail;
					}
				}
				else {
					sbAddress.append(detail);
				}
			}
			else if(temp[i].startsWith("email ")) {
				detail = temp[i];
				detail = detail.replace("email ", "");
				detail = detail.trim();
				if (validEmail(detail)) {
					email = detail;
				}
			}
			else {
				if (address == null && completeAddress == false) {
					detail = temp[i];
					detail = detail.trim();
					sbAddress.append(" " + detail);
					multiAddress = sbAddress.toString();
					if (multiAddress.matches(".*[0-9]{4}")) {
						completeAddress = true;
						if (validAddress(multiAddress)) {
							address = multiAddress;
						}
						sbAddress.delete(0, sbAddress.length());
					}
					else {
						continue;
					}
				}
				else {
					continue;
				}
			}
		}
	}
	
	public boolean isValidToAdd() {
		if(name != null && mobile != null) {
			return true;
		}
		else {
			return false;
		}
	}
	
	private boolean validName(String name) {
		if(name.matches("([A-Z]{1}[a-zA-Z]+\\s)+([A-Z]{1}[a-zA-Z]+)")) {
			return true;
		}
		else {
			return false;
		}
	}
	
	private boolean validMobile(String mobile) {
		if(mobile.matches("[0-9]{8}")) {
			return true;
		}
		else {
			return false;
		}
	}
	
	private boolean validPass(String pass) {
		if(pass.matches("Gold|Silver|Bronze")) {
			return true;
		}
		else {
			return false;
		}
	}
	
	private boolean validAddress(String address) {
		if(address.matches("[\\S\\s]+([a-zA-Z]{3})(,?)\\s([0-9]{4})")) {
			return true;
		}
		else {
			return false;
		}
	}
	
	private boolean validEmail(String email) {
		if(email.matches("([^@\\s]+)([@]{1})([^@\\s]+)")) {
			return true;
		}
		else {
			return false;
		}
	}
	
	public String toString() {
		StringBuilder sb = new StringBuilder();
		sb.append(String.format("name: %s\n", name));
		if (birthday!= null) {
			if (birthday.isValid()) {
			sb.append(String.format("birthday: %s\n", birthday));
			}
		}
		sb.append(String.format("mobile: %s\n", mobile));
		if (pass!=null) {
			sb.append(String.format("pass: %s\n", pass));
		}
		if (fee!=null) {
			if (fee.isValid()) {
			sb.append(String.format("fee: %s\n", fee));
			}
		}
		if (address!=null) {
			sb.append(String.format("address: %s\n", address));
		}
		if (email!=null) {
			sb.append(String.format("email: %s\n", email));
		}
		return sb.toString();
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public CustomDate getBirthday() {
		return birthday;
	}

	public void setBirthday(CustomDate birthday) {
		this.birthday = birthday;
	}

	public String getMobile() {
		return mobile;
	}

	public void setMobile(String mobile) {
		this.mobile = mobile;
	}

	public String getPass() {
		return pass;
	}

	public void setPass(String pass) {
		this.pass = pass;
	}

	public Fee getFee() {
		return fee;
	}

	public void setFee(Fee fee) {
		this.fee = fee;
	}

	public String getAddress() {
		return address;
	}

	public void setAddress(String address) {
		this.address = address;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}
}