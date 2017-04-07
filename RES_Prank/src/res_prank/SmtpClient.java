package res_prank;

import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.io.PrintWriter;
import java.net.Socket;
import java.util.Properties;

/**
 *
 * @author jimmy
 */
public class SmtpClient {
   private String smtpAddress;
   private int smtpServerPort = 25;
   
   private BufferedReader reader;
   private Socket socket;
   private PrintWriter writer;
   
   public SmtpClient(String file) throws IOException {
      FileInputStream fis = new FileInputStream(file);
      Properties properties = new Properties();
      properties.load(fis);
      this.smtpAddress = properties.getProperty("smtpAddress");
      this.smtpServerPort = Integer.parseInt(properties.getProperty("smtpPort"));
   }
   
   public void connect() throws IOException {
      socket = new Socket(smtpAddress, smtpServerPort);
      reader = new BufferedReader(new InputStreamReader(socket.getInputStream(), "UTF-8"));
      writer = new PrintWriter(new OutputStreamWriter(socket.getOutputStream(), "UTF-8"));
      
      writer.println("EHLO jimmy");
      writer.flush();
      String line = reader.readLine();
      while(line.substring(0, 4).equals("250-")) {
         line = reader.readLine();
      }
      
      reader.readLine(); //lire le 250 espace
   }
   
   public void disconnect() throws IOException {
      writer.println("QUIT");
      writer.flush();
      reader.readLine();
      
      reader.close();
      writer.close();
      socket.close();
   }
   
   public void sendMessage(Prank prank) throws IOException {
       Mail mail = prank.getPrankMessage();
       
      writer.println("MAIL FROM: " + prank.getVictimSending().getAdress());
      writer.flush();
      reader.readLine();
      
      for(String p : mail.getTo()) {
         writer.println("RCPT TO: " + p);
         writer.flush();
         reader.readLine();
      }
      
      for(String p : mail.getCc()) {
         writer.println("RCPT TO: " + p);
         writer.flush();
         reader.readLine();
      }
      
      for(String p : mail.getBcc()) {
         writer.println("RCPT TO: " + p);
         writer.flush();
         reader.readLine();
      }
      
      writer.println("DATA");
      writer.flush();
      reader.readLine();
      
      writer.println("from: " + mail.getFrom());
      writer.flush();
      String to = "";
      String[] toArray = mail.getTo();
      for (int i = 0; i < toArray.length - 1; i++)
      {
          to += toArray[i] + ",";
      }
      to += toArray[toArray.length - 1];
      
      writer.println("to: " + to);
      writer.flush();
      writer.println(mail.getData());
      writer.flush();
      writer.println(System.lineSeparator() + "." + System.lineSeparator());
      writer.flush();
      reader.readLine();
   }
}
