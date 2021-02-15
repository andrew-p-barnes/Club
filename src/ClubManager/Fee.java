package ClubManager;

public class Fee {
	private double value;
	
	public Fee(String f) {
		f = f.trim();
		if (f.matches("\\$[1-9][0-9]*\\.?[0-9]?[0-9]?")) {
			value = Double.parseDouble(f.trim().substring(1));
		}
		else {
			value = -1;
		}
	}
	
	public boolean isValid() {
		return value >= 0;
	}
	
	public String toString() {
		if (isValid()) {
			return String.format("$%.2f", value);
		}
		else {
			return "invalid fee";
		}
	}
	
	public double getValue() {
		return value;
	}
}