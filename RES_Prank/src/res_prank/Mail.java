package res_prank;

/**
 *
 * @author jimmy
 */
public class Mail {
   private String from;
   private String[] to;
   private String[] cc;
   private String[] bcc;
   private String data;
   
   public Mail(String from, String[] to, String[] cc, String[] bcc, String data) {
      this.from = from;
      this.to = to;
      this.cc = cc;
      this.bcc = bcc;
      this.data = data;
   }
   
   public String getFrom() {
      return from;
   }
   
   public String[] getTo() {
      return to;
   }
   
   public String[] getCc() {
      return cc;
   }
   
   public String[] getBcc() {
      return bcc;
   }
   
   public String getData() {
      return data;
   }
   
}
