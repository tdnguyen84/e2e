package email;

import javax.activation.DataHandler;
import javax.mail.*;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeMessage;
import javax.mail.search.FlagTerm;

import java.io.IOException;
import java.security.InvalidParameterException;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Properties;

public class EmailUtil {
  public enum EmailServerType {
    GMAIL,
    YAHOOMAIL,
    OUTLOOKMAIL,
    UNKNOWN
  }

  private static class EmailServerProperties {
    public static class Gmail {
      private static Properties props = new Properties();

      static {
        props.put("mail.pop3.host", "pop.gmail.com");
        props.put("mail.pop3.port", "995");
        props.put("mail.pop3.starttls.enable", "true");
        props.put("mail.imap.host", "smtp.gmail.com");
        props.put("mail.imap.port", "993");
        props.put("mail.imap.starttls.enable", "true");
        props.put("mail.smtp.auth", "true");
        props.put("mail.smtp.starttls.enable", "true");
        props.put("mail.smtp.host", "smtp.gmail.com");
        props.put("mail.smtp.port", "587");
        props.put("mail.smtp.ssl.trust", "smtp.gmail.com");


      }

      public static final Properties get() {
        return props;
      }
    }

    public static class YahooMail {
      private static Properties props = new Properties();

      static {
        props.put("mail.pop3.host", "pop.mail.yahoo.com");
        props.put("mail.pop3.port", "995");
        props.put("mail.pop3.starttls.enable", "true");
        props.put("mail.imap.host", "imap.mail.yahoo.com");
        props.put("mail.imap.port", "993");
        props.put("mail.imap.starttls.enable", "true");
      }

      public static final Properties get() {
        return props;
      }
    }

    public static class OutlookMail {
      private static Properties props = new Properties();

      static {
        props.put("mail.pop3.host", "pop-mail.outlook.com");
        props.put("mail.pop3.port", "995");
        props.put("mail.pop3.starttls.enable", "true");
        props.put("mail.imap.host", "imap-mail.outlook.com");
        props.put("mail.imap.port", "993");
        props.put("mail.imap.starttls.enable", "true");
      }

      public static final Properties get() {
        return props;
      }
    }
  }

  /** input: Anduin Transactions <Jen.Seed.GuruPub.0jdwvo6t7w2gq@gondor-internal.anduin.email>
   * return: Jen.Seed.GuruPub.0jdwvo6t7w2gq@gondor-internal.anduin.email
   * */
  protected static String parseEmailAddress(final String rawEmailAddress) {
    int p1 = rawEmailAddress.indexOf('<');
    if (p1 < 0) return rawEmailAddress;

    int p2 = rawEmailAddress.lastIndexOf('>');
    if (p2 < p1 + 6) return rawEmailAddress;

    return rawEmailAddress.substring(p1 + 1, p2);
  }

  protected static EmailServerType detectEmailDomain(String emailAddress) {
    String emailLow = emailAddress.toLowerCase();
    if (emailLow.contains("@gmail.com")) {
      return EmailServerType.GMAIL;
    } else if (emailLow.contains("@yahoo.com")) {
      return EmailServerType.YAHOOMAIL;
    }
    else if (emailLow.contains("@live.com") || emailLow.contains("@hotmail.com")) {
      return EmailServerType.OUTLOOKMAIL;
    }
    return EmailServerType.UNKNOWN;
  }

  protected static Properties getServerProps(EmailServerType serverType) {
    Properties props;
    switch (serverType) {
      case GMAIL:
        props = EmailServerProperties.Gmail.get();
        break;
      case YAHOOMAIL:
        props = EmailServerProperties.YahooMail.get();
        break;
      case OUTLOOKMAIL:
        props = EmailServerProperties.OutlookMail.get();
        break;
      default:
        throw new RuntimeException("Unsupported email server " + serverType.toString());
    }
    return props;
  }

  private static List<EmailMessage> searchEmail(
      final Properties props,
      final SearchCriteria criteria,
      final String email,
      final String pwd) {
    if (props == null)
      throw new InvalidParameterException("Props=" + props);
    try {
      Session session = Session.getDefaultInstance(props);
      Store store;
      String host = (String)props.get("mail.imap.host");
      if (host == null) {
        host = (String)props.get("mail.pop3.host");
        if (host == null)
          throw new InvalidParameterException("Cannot find 'host' in props param.");
        store = session.getStore("pop3s");
      } else {
        store = session.getStore("imaps");
      }

      store.connect(host, email, pwd);
      Folder inbox = store.getFolder("inbox");
      inbox.open(Folder.READ_ONLY);
      
      //search for all "unseen" messages
      Flags seen = new Flags(Flags.Flag.SEEN);
      FlagTerm unseenFlagTerm = new FlagTerm(seen, false);
      Message messages[] = inbox.search(unseenFlagTerm);
      
      
      List<EmailMessage> result = searchEmail(messages, criteria);
      inbox.close(false);
      store.close();
      return result;
    }
    catch (MessagingException ex) {	
      ex.printStackTrace();
      return null;
    }
  }

  private static void listAllEmails(
      final Properties props,
      final String email,
      final String pwd) {
    if (props == null)
      throw new InvalidParameterException("Props=" + props);
    try {
      Session session = Session.getDefaultInstance(props);
      Store store;
      String host = (String)props.get("mail.imap.host");
      if (host == null) {
        host = (String)props.get("mail.pop3.host");
        if (host == null)
          throw new InvalidParameterException("Cannot find 'host' in props param.");
        store = session.getStore("pop3s");
      } else {
        store = session.getStore("imaps");
      }

      store.connect(host, email, pwd);
      Folder inbox = store.getFolder("inbox");
      inbox.open(Folder.READ_ONLY);
      listMessage(inbox.getMessages());
      inbox.close(false);
      store.close();
    }
    catch (MessagingException ex) {
      ex.printStackTrace();
    }
  }

  /** return a list of messages satisfied search criteria */
  private static List<EmailMessage> searchEmail(Message[] messageList, SearchCriteria criteria) {
    List<EmailMessage> emails = new ArrayList<>();
    try {
      for (Message msg : messageList) {
        if (criteria.isSatisfied(msg)) {
          emails.add(new EmailMessage(msg));
        }
      }
    }
    catch (MessagingException | IOException ex) {
      ex.printStackTrace();
    }
    return emails;
  }

  private static void listMessage(Message[] messageList) {
    try {
      System.out.println("Total email: " + messageList.length);
      System.out.println("Printing individual email\n");

      int count = 0;
      for (Message msg : messageList) {
        System.out.println("#" + ++count);
        System.out.println("Email Subject: " + msg.getSubject());
        System.out.println("Sender: " + EmailUtil.parseEmailAddress(msg.getFrom()[0].toString()));
        System.out.println("Receiver: " + msg.getAllRecipients().toString());
        System.out.println("Send date: " + msg.getSentDate());
        if (msg.getContent() instanceof Multipart) {
          Multipart multipart = (Multipart) msg.getContent();
          for (int j = 0; j < multipart.getCount(); j++) {
            BodyPart bodyPart = multipart.getBodyPart(j);
            String disposition = bodyPart.getDisposition();
            if (disposition != null && (disposition.equalsIgnoreCase("ATTACHMENT"))) {
              DataHandler handler = bodyPart.getDataHandler();
              System.out.println("Email has attachment : " + handler.getName());
            } else {
              System.out.println(bodyPart.getContent());
            }
          }
        } else {
          System.out.println("Content: " + msg.getContent().toString());
        }
        System.out.println();
        if (count > 3) break;
      }
    }
    catch (MessagingException | IOException ex) {
      ex.printStackTrace();
    }
  }

  /** auto detect email server */
  public static void listAllEmail(final String email, final String pwd) {
    listAllEmail(email, pwd, detectEmailDomain(email));
  }

  /** allow specifying email server (in case using Gmail but domain is @company.com) */
  public static void listAllEmail(final String email, final String pwd, final EmailServerType server) {
    if (email.length() < 6 || pwd.length() < 1) {
      throw new InvalidParameterException("email=" + email + " pwd=" + pwd);
    }

    listAllEmails(getServerProps(server), email, pwd);
  }

  /** auto detect email server */
  public static List<EmailMessage> searchEmail(final SearchCriteria criteria, final String email, final String pwd) {
    return searchEmail(criteria, email, pwd, detectEmailDomain(email));
  }

  /** allow specifying email server (in case using Gmail but domain is @company.com) */
  public static List<EmailMessage> searchEmail(
      final SearchCriteria criteria,
      final String email,
      final String pwd,
      final EmailServerType server) {
    if (email.length() < 6 || pwd.length() < 1) {
      throw new InvalidParameterException("email=" + email + " pwd=" + pwd);
    }

    return searchEmail(getServerProps(server), criteria, email, pwd);
  }

  public static List<EmailMessage> waitForEmail(
      String emailAddress,
      String password,
      String fromAddress,
      String emailSubject,
      List<String>recipientList,
      Date sendDate) {
    final int MAX_ATTEMPT = 1;
    final int INTERVAL = 20000; // 30s
    List<EmailMessage> matchedEmails = new ArrayList<>();

    SearchCriteria searchCriteria = new SearchCriteria(
        emailSubject, // subject
        true, // exact match subject
        fromAddress, // from
        recipientList, // to
        sendDate
    );

    for (int attempt = 1; attempt <= MAX_ATTEMPT && matchedEmails.isEmpty(); ++attempt) {
      try {
        Thread.sleep(INTERVAL * attempt); // wait for email
      } catch (Exception ignored) {}

      matchedEmails = email.EmailUtil.searchEmail(
          searchCriteria,
          emailAddress,
          password
      );
    }

    return matchedEmails;
  }

  public static List<EmailMessage> waitForEmail1(
      String emailAddress,
      String password,
      String emailSubject,
      List<String>recipientList,
      Date sendDate) {
    return waitForEmail(emailAddress,
        password,
        null,
        emailSubject,
        recipientList,
        sendDate);
  }

  public static void replyEmail(
      final String loginEmail,
      final String loginPassword,
      final String replyText,
      final EmailMessage originEmail
  ) throws MessagingException
  {
    replyEmail(
        detectEmailDomain(loginEmail),
        loginEmail,
        loginPassword,
        replyText,
        originEmail
    );
  }

  public static void replyEmail(
      final EmailServerType server,
      final String loginEmail,
      final String loginPassword,
      final String replyText,
      final EmailMessage originEmail
  )
      throws MessagingException
  {
    Session session = Session.getInstance(getServerProps(server),
        new javax.mail.Authenticator() {
          protected PasswordAuthentication getPasswordAuthentication() {
            return new PasswordAuthentication(loginEmail, loginPassword);
          }
        }
    );

    Message message = new MimeMessage(session);
    InternetAddress[] addrList = InternetAddress.parse(originEmail.getReplyTo());
    message.setFrom(addrList[addrList.length - 1]);
    message.setRecipients(Message.RecipientType.TO, addrList);
    message.setReplyTo(InternetAddress.parse(originEmail.getReplyTo()));
    message.setText(replyText);
    message.setHeader("In-Reply-To", originEmail.getID());
    message.setHeader("References", originEmail.getID());

    String subject = originEmail.getSubject();
    if (!subject.toLowerCase().startsWith("re:")) subject = "Re: " + subject;
    message.setSubject(subject);
    Transport.send(message);
  }

  public static void sendEmail(
      final EmailServerType server,
      final String loginEmail,
      final String loginPassword,
      final EmailMessage email
  )
      throws MessagingException
  {
    Session session = Session.getInstance(getServerProps(server),
        new javax.mail.Authenticator() {
          protected PasswordAuthentication getPasswordAuthentication() {
            return new PasswordAuthentication(loginEmail, loginPassword);
          }
        }
    );

    Message message = new MimeMessage(session);
    message.setFrom(new InternetAddress(email.getFrom()));
    message.setRecipients(Message.RecipientType.TO, InternetAddress.parse(email.getTo()));
    message.setText(email.getContent());
    message.setSubject(email.getSubject());
    Transport.send(message);
  }
}
