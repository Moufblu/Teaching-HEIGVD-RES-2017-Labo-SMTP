package res_prank;

import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.List;
import java.util.Random;

/**
 *
 * @author jimmy
 */
public class Prank {
   private Person victimSending;
   private final List<Person> to;
   private final List<Person> cc;
   private Mail prankMessage;
   
   public Prank(Group persons, Group cc, String fileMessage) {
      this.to = persons.getGroup();
      this.cc = cc.getGroup();
      
      Random rand = new Random();
      int numberVictim = rand.nextInt(persons.size());
      victimSending = to.get(numberVictim);
      to.remove(numberVictim);
      
      String[] mailTo = new String[this.to.size()];
      for (int i = 0; i < this.to.size(); i++)
      {
          mailTo[i] = this.to.get(i).getAdress();
      }
      
      String[] mailCc = new String[this.cc.size()];
      for (int i = 0; i < this.cc.size(); i++)
      {
          mailTo[i] = this.cc.get(i).getAdress();
      }
      
      String[] bcc = new String[0];
      String mailData = "";
      try
      {
        mailData = loadMessage(fileMessage);
      }catch(IOException e) { System.out.println("Couldn't load data");}
      
      prankMessage = new Mail(victimSending.getAdress(), mailTo, mailCc, bcc, mailData);
   }
   
   public Person getVictimSending() {
      return victimSending;
   }
   
   public List<Person> getTo() {
      return to;
   }
   
   public List<Person> getCc() {
      return cc;
   }
   
   public Mail getPrankMessage() {
      return prankMessage;
   }
   
   private static String loadMessage(String file) throws IOException
   {
       String result = "";
       FileInputStream fis = new FileInputStream(file);
       InputStreamReader isr = new InputStreamReader(fis, "UTF-8");
       BufferedReader reader = new BufferedReader(isr);

       String line = reader.readLine();
       while (line != null)
       {
           result += line;
           result += "\r\n";
           line = reader.readLine();
       }
       return result;
   }
}
