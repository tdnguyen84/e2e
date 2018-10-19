package email;

import javax.mail.Address;
import javax.mail.Message;
import javax.mail.MessagingException;
import java.security.InvalidParameterException;
import java.util.Date;
import java.util.List;

public class SearchCriteria {
  private String subject; // ignore if empty
  private boolean exactMatchSubject; // ignore if subject is empty
  private String fromAddress; // ignore if empty
  private List<String> toAddresses; // ignore if empty
  private Date fromDate; // ignore if null
  private boolean printDebugLog = false;

  public SearchCriteria(String subj, boolean exactMatch) {
    this(subj, exactMatch, "", null, null);
  }

  public SearchCriteria(String from) {
    this("", true, from, null, null);
  }

  public SearchCriteria(List<String> to) {
    this("", true, "", to, null);
  }

  public SearchCriteria(Date d) {
    this("", true, "", null, d);
  }

  /**
   * @param subj
   * @param exactMatchSubj
   * @param from
   * @param to
   * @param date
   */
  public SearchCriteria(String subj, boolean exactMatchSubj, String from, List<String> to, Date date) {
    if ((subj == null || subj.isEmpty())
        && (from == null || from.isEmpty())
        && (to == null || to.isEmpty())
        && date == null
        ) {
      throw new InvalidParameterException("Cannot construct empty SearchCriteria.");
    }
    subject = subj == null ? "" : subj;
    exactMatchSubject = exactMatchSubj;
    fromAddress = from == null ? "" : from;
    toAddresses = to;
    fromDate = date;
  }

  /** return true if msg satisfy this criteria */
  public boolean isSatisfied(Message msg) {
    try {
      if (!subject.isEmpty() && msg.getSubject() != null) {
        if (exactMatchSubject) {
          if (msg.getSubject().compareToIgnoreCase(subject) != 0) {
            if (printDebugLog) {
              System.out.println("Email subject is " + msg.getSubject());
              System.out.println("Expected to exactly match " + subject);
            }
            return false;
          }
        } else {
          if (!msg.getSubject().toLowerCase().contains(subject.toLowerCase())) {
            if (printDebugLog) {
              System.out.println("Email subject is " + msg.getSubject());
              System.out.println("Expected to contain " + subject);
            }
            return false;
          }
        }
      }

      if (!fromAddress.isEmpty()) {
        String emailFromField = msg.getFrom()[0].toString().toLowerCase();
        String expectedFrom = fromAddress.toLowerCase();

        if (!emailFromField.contains(expectedFrom)) {
          if (printDebugLog) {
            System.out.println("From: " + emailFromField);
            System.out.println("Expected to contains " + fromAddress);
          }
          return false;
        }
      }

      if (toAddresses != null && !toAddresses.isEmpty()) {
        Address[] addressArr = msg.getRecipients(Message.RecipientType.TO);
        boolean found = false;

        for (int i = 0; i < toAddresses.size(); i++) {
          found = false;
          for (int j = 0; j < addressArr.length; j++) {
            if (addressArr[j].toString().contains(toAddresses.get(i))) {
              found = true;
              break;
            }
          }
          if (!found) break;
        }

        if (!found) {
          if (printDebugLog) {
            System.out.println("Looking for all of these addresses in 'To' list but not found: ");
            toAddresses.forEach(a -> System.out.print(a + " "));

            System.out.println("To list of this email: ");
            for (Address s: addressArr) {
              System.out.print(s.toString() + " ");
            }
          }
          return false;
        }
      }

      if (fromDate != null && msg.getSentDate().compareTo(fromDate) < 0) {
        if (printDebugLog) {
          System.out.println("Email send date is " + msg.getSentDate().toString());
          System.out.println("Expected send date to be at or later than " + fromDate.toString());
        }
        return false;
      }
      return true;
    }
    catch (MessagingException ex) {
      ex.printStackTrace();
      return false;
    }
  }
}