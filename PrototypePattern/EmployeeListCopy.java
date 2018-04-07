package proto;
/**
 * @author Jatin Bhakta, Eric Lynn, Gene Koval, Stan Genov, Johan Burke
 */

import java.util.ArrayList;
import java.util.List;

public class EmployeeListCopy implements Cloneable {

    // list of employees
    private List<String> empList;

    // initialize to new list
    public EmployeeListCopy() {
        empList = new ArrayList<String>();
    }

    // initialize to list passed in
    public EmployeeListCopy(List<String> list) {
        this.empList = list;
    }

    /**
     * Method to load dummy data into the system.
     * In a more realistic application, this could be a much more complex/expensive operation
     * like reading getting data from a database on a remote server
    */
    public void loadData() {

        try {
            System.out.println("Loading data...");
            // "costly operation"
            Thread.sleep(1000);

            String[] newData = {"Jatin", "Eric", "Gene", "Stan", "Johan"};
            for(int i = 0; i<newData.length; i++){
                empList.add(newData[i]);
                // "costly operation"
                Thread.sleep(2000);
                System.out.println("Adding employee " + newData[i]);
            }

        }catch(Exception e){e.printStackTrace();}
    }


    public List<String> getEmpList() {
        return empList;
    }

    /**
     * @returns a shallow copy of EmployeeList by default
     */
    @Override
    public Object clone() throws CloneNotSupportedException {
        return super.clone();
    }

    /**
     * Creates a deep copy of this instance of EmployeeListCopy.
     * This way, modifications can be made to the clone without
     * effecting the original in case you want to keep old versions of
     * objects around for redundancy, reliability, or record-keeping
    */
    public Object deepClone() throws CloneNotSupportedException {
        System.out.println("Deep Cloning List...");
        List<String> temp = new ArrayList<String>();
        for (String s : this.getEmpList()) {
            temp.add(s);
        }
        return new EmployeeListCopy(temp);
    }


    /**
     * Create a shallow copy of this object by passing the copy a
     * reference to the same list used in the current instance.
     * This makes the clone() operation faster, as the copy is
     * being passed a reference to a list instead of
     * having to construct a list and pass it
    */
    public Object shallowClone() throws CloneNotSupportedException {
        System.out.println("Shallow Cloning List...");
        return new EmployeeListCopy(empList);
    }
}
