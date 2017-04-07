package res_prank;

import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Properties;
import java.util.Random;
import java.util.Scanner;

/**
 *
 * @author jimmy
 */
public class RES_Prank {   
   /**
    * @param args the command line arguments
    */
   public static void main(String[] args) {
      Scanner sc = new Scanner(System.in);
      try {
         SmtpClient client = new SmtpClient("smtp.properties");
         client.connect();
         Prank prank;
         String[] nomFichier = {"message1.txt", "message2.txt", "message3.txt"};
         int counter = 0;
         char end = '\0';
         do {
            List<Group> victims = new ArrayList<>();            
            Group cc = new Group();
            
            try
            {
               int nbGroups = sc.nextInt();
               for (int i = 0; i < nbGroups; i++)
                  victims.add(new Group());
               
               List<Person> victimsFromFile = loadVictims("victims.emails");
               if (victimsFromFile.size() > 2)
               {
                  Random r = new Random();
                  for (Group g : victims)
                  {
                     Collections.shuffle(victimsFromFile);
                     int random = r.nextInt(victimsFromFile.size() - 3) + 3;
                      for (int i = 0; i < random; i++)
                      {
                         g.add(victimsFromFile.get(i));
                      }
                  }
               }
               else
               {
                  System.out.println("Not enough victims to load !");
               }
            }
            catch(IOException e)
            {
                System.out.println("Couldn't load victims");
            }
            
            cc.add(new Person("nadir.benallal@heig-vd.ch"));
            cc.add(new Person("jimmy.verdasca@heig-vd.ch"));
            //cc.add(new Person("miguel.santamaria@heig-vd.ch"));
            
            for (Group g : victims)
            {
               prank = new Prank(g, cc, nomFichier[counter%3]);
               client.sendMessage(prank);
               System.out.println(g.size());
            }
            
            System.out.println("continue ? (y/n)");
            end = sc.next().charAt(0);
            counter++;
         } while(end == 'y');
         
         client.disconnect();
      } catch(IOException e) {
         System.out.println("file not found");
      }
      
   }
   
    private static List<Person> loadVictims(String file) throws IOException
    {
        List<Person> result = new ArrayList<>();
        FileInputStream fis = new FileInputStream(file);
        InputStreamReader isr = new InputStreamReader(fis, "UTF-8");
        BufferedReader reader = new BufferedReader(isr);

        String line = reader.readLine();
        while (line != null)
        {
            Person temp = new Person(line);
            result.add(temp);
            line = reader.readLine();
        }
        return result;
    }
   
}
