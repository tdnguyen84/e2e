package email;

import javax.mail.BodyPart;
import javax.mail.MessagingException;
import javax.mail.Multipart;
import javax.mail.Part;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeMultipart;
import javax.mail.Message;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

public class EmailMessage {
  protected String subject;
  protected String content;
  protected List<String> attachmentNames;
  protected Date sendDate;
  protected String from;
  protected String to;
  protected String replyTo;
  protected String messageID;

  public EmailMessage(String subject, String content) {
    this.subject = subject;
    this.content = content;
    this.attachmentNames = new ArrayList<>();
    this.sendDate = null;
    this.to = null;
    this.from = null;
    this.replyTo = null;
    this.messageID = null;
  }

  public EmailMessage(
      String subject,
      String content,
      String from,
      String to
  ) {
    this(subject, content);
    this.from = from;
    this.to = to;
  }

  public EmailMessage(
      String subject,
      String content,
      String from,
      String to,
      String replyTo,
      Date sendDate
  ) {
    this(subject, content, from, to);
    this.replyTo = replyTo;
    this.sendDate = sendDate;
  }

  private String getMultipartContent(MimeMultipart mimeMultipart) {
    String result = "";
    try {
      for (int i = 0; i < mimeMultipart.getCount(); i++) {
        BodyPart bodyPart = mimeMultipart.getBodyPart(i);
        if (bodyPart.isMimeType("text/plain") || bodyPart.isMimeType("text/html")) {
          result += bodyPart.getContent();
          break; // without break same text appears twice in my tests
        } else if (bodyPart.getContent() instanceof MimeMultipart) {
          result += getMultipartContent((MimeMultipart) bodyPart.getContent());
        }
      }
    }
    catch (MessagingException | IOException ex) {
      ex.printStackTrace();
    }
    return result;
  }

  public EmailMessage(Message msg) throws IOException, MessagingException {
    this(
        msg.getSubject(),
        "",
        InternetAddress.toString(msg.getFrom()),
        InternetAddress.toString(msg.getRecipients(Message.RecipientType.TO)),
        InternetAddress.toString(msg.getReplyTo()),
        msg.getSentDate()
    );

    messageID = ((javax.mail.internet.MimeMessage) msg).getMessageID();
    if (messageID == null || messageID.isEmpty()) {
      messageID = ((javax.mail.internet.MimeMessage) msg).getHeader("Message-Id", null);
    }

    if (msg.getContent() instanceof Multipart) {
      Multipart multipart = (Multipart) msg.getContent();
      for (int j = 0; j < multipart.getCount(); j++) {
        BodyPart bodyPart = multipart.getBodyPart(j);
        String disposition = bodyPart.getDisposition();

        if (disposition != null && (disposition.equalsIgnoreCase(Part.ATTACHMENT))) {
          attachmentNames.add(bodyPart.getDataHandler().getName());
        }
        else if (bodyPart.getContent() instanceof MimeMultipart) {
          content += getMultipartContent((MimeMultipart) bodyPart.getContent());
        }
        else {
          content += bodyPart.getContent().toString();
        }
      }
    } else {
      content = msg.getContent().toString();
    }
  }

  public String toString() {
    String s = "Subject: " + subject;

    if (sendDate != null) {
      s += "\nSend date: " + sendDate.toString();
    }

    if (from != null) {
      s += "\nFrom: " + from;
    }

    if (to != null) {
      s += "\nTo: " + to;
    }

    if (replyTo != null) {
      s += "\nReply to: " + replyTo;
    }

    if (messageID != null) {
      s += "\nID: " + messageID;
    }

    if (!attachmentNames.isEmpty()) {
      s += "\n" + attachmentNames.size() + " attachment";
      if (attachmentNames.size() > 1) s += "s";
      s += ": ";
      for (String n: attachmentNames) {
        s += n + " ; ";
      }
    }

    s += "\nContent: " + content;
    return s;
  }

  public String getContent() {
    return content;
  }

  public String getSubject() {
    return subject;
  }

  public String getFrom() { return from; }

  public String getReplyTo() { return replyTo; }

  public String getTo() { return to; }

  public String getID() { return messageID; }

  public void setID(String id) { messageID = id; }
}
