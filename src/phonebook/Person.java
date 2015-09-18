
package phonebook;

import javafx.beans.property.SimpleStringProperty;
import javafx.beans.property.StringProperty;

public class Person {
    
    private StringProperty firstName;
    private SimpleStringProperty lastName;
    private SimpleStringProperty  phoneNumber;
    private SimpleStringProperty  address;
    
    public Person() {
        
    }
    public Person(String f, String l, String n, String a) {
        this.firstName = new SimpleStringProperty(f);
        this.lastName = new SimpleStringProperty(l);
        this.phoneNumber = new SimpleStringProperty(n);
        this.address = new SimpleStringProperty(a);
    }

    public String getFirstName() {
        return firstName.getValue();
    }

    public void setFirstName(StringProperty  firstName) {
        this.firstName = firstName;
    }

    public String getLastName() {
        return lastName.getValue();
    }

    public void setLastName(SimpleStringProperty  lastName) {
        this.lastName = lastName;
    }

    public String getPhoneNumber() {
        return phoneNumber.getValue();
    }

    public void setPhoneNumber(SimpleStringProperty  phoneNumber) {
        this.phoneNumber = phoneNumber;
    }

    public String getAddress() {
        return address.getValue();
    }

    public void setAddress(SimpleStringProperty  address) {
        this.address = address;
    }

    @Override
    public String toString() {
        return "Person{" + "firstName=" + firstName + ", lastName=" + lastName + ", phoneNumber=" + phoneNumber + ", address=" + address + '}';
    }
    
    
}
