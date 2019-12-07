package ClientServerProject;

import java.util.Scanner;
import java.util.regex.Pattern;
import java.util.regex.Matcher;


public class RegularExpressions {


	/*  
	    The following regular expression may not get 100% of valid email addresses but it will be very close. 
	    Derivation found here: http://www.mkyong.com/regular-expressions/how-to-validate-email-address-with-regular-expression/
		^                          -- start of the line
		[_A-Za-z0-9-\\+]+          -- must start with string in the bracket [ ], must contains one or more (+)
		(			               -- start of group #1
		\\.[_A-Za-z0-9-]+	       -- follow by a dot "." and string in the bracket [ ], must contains one or more (+)
		)*			               -- end of group #1, this group is optional (*)
		@			               -- must contains a "@" symbol
		[A-Za-z0-9-]+              -- follow by string in the bracket [ ], must contains one or more (+)
		(			               -- start of group #2 - first level TLD checking
		\\.[A-Za-z0-9]+            -- follow by a dot "." and string in the bracket [ ], must contains one or more (+)
		)*		                   -- end of group #2, this group is optional (*)
		(			               -- start of group #3 - second level TLD checking
		\\.[A-Za-z]{2,}            -- follow by a dot "." and string in the bracket [ ], with minimum length of 2
		)			               -- end of group #3
		$			               -- end of the line	 
	 */
	private static String emailregex = "^[_A-Za-z0-9-\\+]+(\\.[_A-Za-z0-9-]+)*@[A-Za-z0-9-]+(\\.[A-Za-z0-9]+)*(\\.[A-Za-z]{2,})$";
	private static Pattern emailpattern = Pattern.compile(emailregex);
	
	/*
		^                 # start-of-string
		(?=.*[0-9])       # a digit must occur at least once
		(?=.*[a-zA-Z])    # a lower or upper case letter must occur at least once
		(?=.*[@#$%^&+=])  # a special character must occur at least once
		(?=\\S+$)         # no whitespace allowed in the entire string
		.{8,}             # anything, at least eight places though
		$                 # end-of-string	
	*/
	private static String passwordregex = "^(?=.*[0-9])(?=.*[a-zA-Z])(?=.*[@#$%^&+=])(?=\\S+$).{8,}$";
	private static Pattern passwordpattern = Pattern.compile(passwordregex);
	
    public static void main(String[] args){
    	Scanner kb = new Scanner(System.in);

        System.out.print("Enter the email address to check: ");
        String emailaddr = kb.next().toUpperCase();

        System.out.println((!validEmailAddress(emailaddr) ? "Not a" : "A") + " valid email address");
        
        System.out.println("Enter the password to check:" );
        String pword = kb.next();
        System.out.println((!validPassword(pword) ? "Not a" : "A") + " valid password");

	        kb.close();
	    }
	    
	    public static boolean validPassword(String password)
	    {
	        Matcher matcher = passwordpattern.matcher(password);
	        return matcher.find();
	    }

	    public static boolean validEmailAddress (String emailaddress)
	    {
	        Matcher matcher = emailpattern.matcher(emailaddress);
	        return matcher.find();
	    }
	    
	}

