package ClubManager;

import java.util.*;

public class Club {
	private ArrayList<Member> memberList;
	
	public Club() {
		memberList = new ArrayList<Member>();
	}
	
	public void addMember(String s) {
		Member m = new Member(s);
		if (m.isValidToAdd()) {
			int memberIndex = 0;
			memberIndex = findMemberIndex(m.getName(),m.getMobile());
			if(memberIndex >= 0) {
				if(m.getBirthday() != null) {
					memberList.get(memberIndex).setBirthday(m.getBirthday());
				}
				if(m.getPass() != null) {
					memberList.get(memberIndex).setPass(m.getPass());
				}
				if(m.getFee() != null) {
					memberList.get(memberIndex).setFee(m.getFee());
				}
				if(m.getAddress() != null) {
					memberList.get(memberIndex).setAddress(m.getAddress());
				}
				if(m.getEmail() != null) {
					memberList.get(memberIndex).setEmail(m.getEmail());
				}
			}
			else {
				memberList.add(m);
			}
		}
	}
	
	public void deleteMember(String s) {
		String[] temp = s.trim().split(";");
		String name = temp[0].trim();
		String mobile = temp[1].trim();
		int memberIndex = 0;
		
		memberIndex = findMemberIndex(name,mobile);
		if(memberIndex >= 0) {
			memberList.remove(memberIndex);
		}
	}
	
	private int findMemberIndex(String name, String mobile) {
		int memberIndex = -1;
		
		for(int i = 0; i < memberList.size(); i++) {	
			if (memberList.get(i).getName().equalsIgnoreCase(name) && memberList.get(i).getMobile().equals(mobile)) {
				memberIndex = i;
				break;
			}
		}
		return memberIndex;
	}
		
	public void sortMember(String sortType, ArrayList<Member> memberList) {
		boolean sameName = false;
		
		for(int m = 1 ; m < memberList.size() ; m++) {
			for(int j = 1 ; j < memberList.size() ; j++) {
				if(memberList.get(m).getName().equalsIgnoreCase(memberList.get(j).getName())) {
					sameName = true;
					break;
				}
			}
		}
		
		if(sameName == true) {
			
			for(int m = 1 ; m < memberList.size() ; m++) {
				Member temp = memberList.get(m);
				int index = m - 1;
				while(index >= 0 && Integer.valueOf(memberList.get(index).getMobile()) > Integer.valueOf(temp.getMobile())) {
					memberList.set((index+1),memberList.get(index));
					index--;
				}
				memberList.set((index+1),temp);	
			}
		}
		
		if(sortType.equalsIgnoreCase("ascending")) {
			
			for(int m = 1 ; m < memberList.size() ; m++) {
				Member temp = memberList.get(m);
				int index = m - 1;
				while(index >= 0 && (memberList.get(index).getName().compareTo(temp.getName()) > 0)) {
					memberList.set((index+1),memberList.get(index));
					index--;
				}
				memberList.set((index+1),temp);	
			}
		}
	
		if(sortType.equalsIgnoreCase("descending")) {
		
			for(int m = 1 ; m < memberList.size() ; m++) {
				Member temp = memberList.get(m);
				int index = m - 1;
				while(index >= 0 && (memberList.get(index).getName().compareTo(temp.getName()) > 0)) {
					memberList.set((index+1),memberList.get(index));
					index--;
				}
				memberList.set((index+1),temp);	
			}
			int n = memberList.size();
			for (int i = 0; i < n/2; i++) {
				Member temp = memberList.get(i);
				memberList.set(i,memberList.get(n-1-i));
				memberList.set((n-1-i),temp);	
			}
		}
	}
	
	public ArrayList<Member> queryMemberPass(String s) {
		String[] temp = s.trim().split("\\s");
		String passType = temp[1].trim();
		ArrayList<Member> queryList = new ArrayList<Member>();
		
		for(Member m : memberList) {
			if(m.getPass()!=null) {
				if (m.getPass().equals(passType)) {
					queryList.add(m);
				}
			}
		}
		if(queryList.size() > 0) {
			sortMember("ascending",queryList);
			return queryList;
		}
		else {
			return null;
		}
	}
		
	public String toString(ArrayList<Member> memberList) {
		StringBuilder sb = new StringBuilder();
		for(Member m : memberList) {
			sb.append(m.toString());
			sb.append("\n");
		}
		return sb.toString();
	}
	
	public ArrayList<Member> getMemberList() {
		return memberList;
	}
	
	public void setMemberList(ArrayList<Member> memberList) {
		this.memberList = memberList;
	}
}
