package res_prank;

import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author jimmy
 */
public class Group {
   
   private final List<Person> group = new ArrayList<>();
   
   public void add(Person person) {
      group.add(person);
   }
   
   public List<Person> getGroup() {
      return group;
   }
   
   public int size() {
      return group.size();
   }
}
