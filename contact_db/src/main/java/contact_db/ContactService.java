package contact_db;

public interface ContactService {

    void addContact(String name, String number);

    void removeContact(String name);

    String getNumber(String name);
}
