package ClubManager;

import java.io.*;
import java.util.Scanner;

public class ClubManager {
	
	public static void main(String[] args) {
		if(args.length == 4) {
		
		Club club = new Club();
		
		MemberProcessor mp = new MemberProcessor(club);
				
		Scanner mReader = null;
		Scanner iReader = null;
		PrintWriter resultOut = null;
		PrintWriter reportOut = null;
		
		try{	
			 mReader = new Scanner(new FileInputStream(args[0]));
		}	
		catch (FileNotFoundException e) {
			e.printStackTrace();
			return;
		}
		
		try{	
			iReader = new Scanner(new FileInputStream(args[1]));
		}	
		catch (FileNotFoundException e) {
			e.printStackTrace();
			mReader.close();
			return;
		}
		
		try{	
			resultOut = new PrintWriter(new FileOutputStream(args[2]));
		}	
		catch (FileNotFoundException e) {
			e.printStackTrace();
			mReader.close();
			iReader.close();
			return;
		}
		
		try{	
			reportOut = new PrintWriter(new FileOutputStream (args[3]));
		}	
		catch (FileNotFoundException e) {
			e.printStackTrace();
			mReader.close();
			iReader.close();
			resultOut.close();
			return;
		}

		try{
		mp.readMembers(mReader);
		mp.readInstruction(iReader,reportOut);
		mp.writeResult(resultOut);
		}
		finally {
		mReader.close();
		iReader.close();
		resultOut.close();
		reportOut.close();
		}
		
		}
		else {
			System.out.print("no arguments");
		}
	}
}
