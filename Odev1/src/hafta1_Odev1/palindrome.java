package hafta1_Odev1;

import java.math.BigDecimal;

public class palindrome {
	public static void main(String[] args) {
		int num = 0;
		int max = 0;
		String longest = "";
		for(int i = 10; i < 100; i++) {
			int tMax = 0;
			String tNum = i+"";
			String tLongest = "";
		    while(!isPalindrome(tNum)) {
		    	BigDecimal reversed = new BigDecimal(reverse(tNum));
		    	tLongest = tLongest + tNum +" + "+reversed+" / ";
		    	tNum = (new BigDecimal(tNum)).add(reversed)+"";
				tMax++;
			}
				
			if(tMax>max) {
				num = i;
				max = tMax;
				longest = tLongest+tNum;
			}
		}
		System.out.println(num+"\n"+longest+"\n"+max+" adýmda bulundu");
	}
	
	/* 
	 * 12345 -> 52341
	 * 52341 -> 54321
	 */
	public static String reverse(String N) {
		for(int i = 0; i < N.length()/2;i++) {
			N = N.substring(0,i)+N.substring(N.length()-1-i,N.length()-i)+N.substring(i+1,N.length()-1-i)+N.substring(i,i+1)+N.substring(N.length()-i);
		}
		return N;
	}
	public static boolean isPalindrome(String N) {
		for(int i = 0; i < N.length();i++) {
			if(N.charAt(i)!=N.charAt(N.length()-1-i))
				return false;
		}
		return true;
	}
}
