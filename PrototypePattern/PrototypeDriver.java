package proto;

public class PrototypeDriver {

    public static void main(String[] args) throws CloneNotSupportedException{
        EmployeeListCopy employeesList = new EmployeeListCopy();
        employeesList.loadData();

        EmployeeListCopy shallowClonedList = (EmployeeListCopy) employeesList.shallowClone();
        EmployeeListCopy deepClonedList = (EmployeeListCopy) employeesList.deepClone();

        System.out.println();
        System.out.println("Original employee list: " + employeesList.getEmpList() + "\n");

        deepClonedList.getEmpList().add("John Saunders?");
        System.out.println("Deep-cloned employee list: " + deepClonedList.getEmpList());
        System.out.println("Original employee list after deep clone: " + employeesList.getEmpList() + "\n");

        shallowClonedList.getEmpList().add("Tyler Carberry?");
        System.out.println("Shallow-cloned employee list: " + shallowClonedList.getEmpList());
        System.out.println("Original employee list after shallow clone: " + employeesList.getEmpList());
    }
}
