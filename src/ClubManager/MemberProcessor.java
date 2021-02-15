package ClubManager;

import java.io.*;
import java.util.*;

public class MemberProcessor {
	
	private Club club;
	
	public MemberProcessor(Club club) {
		this.club = club;
	}
		
	public void readMembers(Scanner reader) {
		StringBuilder md = new StringBuilder();
		boolean multipleEmptyLine = false;
		
		while (reader.hasNextLine()) {
			String line = reader.nextLine().trim();
			
			if(!line.isEmpty()) {
				multipleEmptyLine = false;
				md.append(line + ";");
			}
			
			if((line.isEmpty() && multipleEmptyLine == false)|reader.hasNextLine() == false) {
				if(md.toString().contains("name ") && md.toString().contains("mobile ")) {
					club.addMember(md.toString());
				}
				else {
					continue;
				}
				md.delete(0, md.length());
				multipleEmptyLine = true;
			}
		}		
	}
	
	
	
	public void readInstruction(Scanner reader, PrintWriter reportOut) {
		while (reader.hasNextLine()) {
			String instruction = reader.nextLine().trim();
			Scanner in = new Scanner(instruction);
			String command, param;
			if (in.hasNext()) {
				command = in.next().trim();
				if (in.hasNextLine()) {
					param = in.nextLine().trim();
					if (command.equalsIgnoreCase("add")) {
						club.addMember(param);
					}
					else if (command.equalsIgnoreCase("delete")) {
						club.deleteMember(param);
					}
					else if (command.equalsIgnoreCase("sort")) {
						club.sortMember(param, club.getMemberList());
					}
					else if (command.equalsIgnoreCase("query") && param.matches("pass Gold|pass Silver|pass Bronze")) {
							writeQueryPass(club.queryMemberPass(param),reportOut);
					}
					else if (command.equalsIgnoreCase("query") && param.equals("age fee")) {
						writeQueryAge(reportOut);
					}
					else {
						continue;
					}
				}
				else {
					continue;
				}
			}
			else {
				continue;
			}
			in.close();
		}		
	}
	
	public void writeResult(PrintWriter resultOut) {
		resultOut.println(club.toString(club.getMemberList()));
	}

	
	public void writeQueryPass(ArrayList<Member> queryList, PrintWriter reportOut) {
		if(queryList.size() > 0) {
			reportOut.printf("----query pass %s----\n",queryList.get(0).getPass());
			reportOut.print(club.toString(queryList));
			double sumFee = 0;
			for(Member m : queryList) {
				if(m.getFee()!=null) {
					sumFee += m.getFee().getValue();
				}
			}
			reportOut.printf("Total Fee: $%.2f\n", sumFee);
			reportOut.print("-------------------------\n\n\n");
		}
	}
	
	
	public void writeQueryAge(PrintWriter reportOut) {
		double sumFee_0to8 = 0;
		double sumFee_8to18 = 0;
		double sumFee_18to65 = 0;
		double sumFee_65plus = 0;
		double unknown = 0;
		
		for(Member m : club.getMemberList()) {
			if(m.getBirthday()!=null) {
				int age = m.getBirthday().getYear();
				if(m.getFee()!=null) {
					if (m.getFee().isValid()) {
						if (age < 8) {
							sumFee_0to8 += m.getFee().getValue();
						}
						if (age >= 8 && age < 18) {
							sumFee_8to18 += m.getFee().getValue();
						}
						if (age >= 18 && age < 65) {
							sumFee_18to65 += m.getFee().getValue();
						}
						if (age >= 65) {
							sumFee_65plus += m.getFee().getValue();
						}
					}
					else {
						continue;
					}
				}
				else {
					continue;
				}
			}
			else {
				if(m.getFee()!=null) {
					if (m.getFee().isValid()) {
						unknown += m.getFee().getValue();
					}
				}
			}
		}
		reportOut.println("----query age fee----");
		reportOut.println("Total Club Member size: " + club.getMemberList().size());
		reportOut.println("Age based fee income distribution");
		reportOut.printf("(0,8): $%.2f\n", sumFee_0to8);
		reportOut.printf("(8,18): $%.2f\n", sumFee_8to18);
		reportOut.printf("(18,65): $%.2f\n", sumFee_18to65);
		reportOut.printf("(65,-): $%.2f\n", sumFee_65plus);
		reportOut.printf("Unknown: $%.2f\n", unknown);
		reportOut.print("-------------------------\n\n\n");
		}
}
