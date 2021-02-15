package ClubManager;

import java.time.LocalDate;
import java.time.Period;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;

public class CustomDate {
	private DateTimeFormatter dateFormat = DateTimeFormatter.ofPattern("dd/MM/yyyy");
	private String dateStr;
	private LocalDate date;

	public CustomDate(String d) {
		dateStr = d;
		boolean validDate = false;
		String[] temp;
		if (dateStr.matches("(((0?[1-9])|([1-2][0-9])|([3][0-1]))(\\-|\\/)((0?[1,3,5,7,8])|([1][0,2]))|((0?[1-9])|([1-2][0-9])|([3][0]))(\\-|\\/)((0?[4,6,9])|([1][1]))|((0?[1-9])|([1-2][0-9]))(\\-|\\/)(0?[2]))(\\-|\\/)(19|20)\\d{2}")) {
			
			validDate = true;
			temp = dateStr.split("\\D");
			if (temp.length == 3) {
				for (int i = 0; i < temp.length; i++) {
					if (temp[i].length() < 2) {
							temp[i] = "0" + temp[i];
					}
				}
				dateStr = temp[0] + "/" + temp[1] + "/" + temp[2];
			}
		}
		
		if (validDate == true) {
			try {
				date = LocalDate.parse(dateStr, dateFormat);
			}
			catch (DateTimeParseException e) {
				date = null;
			}
		}
	}
	
	public boolean isValid() {
		if(date!=null) {
			return true;
		}
		else {
			return false;
		}
	}
	
	public String toString() {
		if (isValid()) {
			return date.format(dateFormat);
		}
		else {
			return "invalid date";
		}
	}
	
	public int getYear() {
		if (isValid()) {
			LocalDate now = LocalDate.now();
	        Period p = Period.between(date, now);
	        int year = p.getYears();
			return year;
		}
		else
			return -1;
	}
	
	
	public LocalDate getDate() {
		return date;
	}
}