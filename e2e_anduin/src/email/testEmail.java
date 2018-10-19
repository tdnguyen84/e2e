package email;

import utilities.Log;

public class testEmail {
	public static void runDemo() {
		SearchCriteria criteria = new SearchCriteria("Invitation to join Test Anduin Transaction's Note from Test Vy Cap", true);
	    java.util.List<EmailMessage> emails = EmailUtil.searchEmail(criteria, "qaanduin@gmail.com", "anduin1808");
	    if (!emails.isEmpty())
	      Log.info(emails.get(0).toString());
	    else
	      Log.info("Not found.");
	}
	
}
