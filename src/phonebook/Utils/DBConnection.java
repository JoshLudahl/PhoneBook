package phonebook.Utils;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.*;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import phonebook.Person;

public class DBConnection {

    private Connection con = null;
    private String JDBC = "com.mysql.jdbc.Driver";
    private String username = "root", password = "";
    private final String dbname = "jdbc:mysql://localhost/phonebook";

    public DBConnection() throws Exception {

    }//END Conn()

    public void Connect() throws Exception {

        try {
            Class.forName(JDBC);
            con = DriverManager.getConnection(dbname, username, password);
            System.out.println("Success...");

        } catch (ClassNotFoundException | SQLException e) {
            System.out.println(e);
            System.out.println("Failure");
        }
    }//end CONNECT()

    public Connection Conn() throws Exception {

        try {
            Class.forName(JDBC);
            con = DriverManager.getConnection(dbname, username, password);
            System.out.println("Success...");
            return con;

        } catch (ClassNotFoundException | SQLException e) {
            System.out.println(e);
            System.out.println("Failure");
            return null;
        }
    }//end CONNECT()

    public void addPerson(Person person) throws Exception {
        Connect();

        //SQL to add the new contact
        String sqlString = "INSERT into contacts (first_name, last_name, phone_number, address) VALUES ('" + person.getFirstName() + "', '" + person.getLastName() + "', '" + person.getPhoneNumber() + "', '" + person.getAddress() + "')";
        try {
            //PreparedStatement ps = con.prepareStatement(sqlString);
            Statement st = (Statement) con.createStatement();
            st.execute(sqlString);

        } catch (Exception e) {
            //print the error
            System.out.println("There was an error: " + e);
        }

        con.close();
    }

    public void removePerson(String number) throws Exception{
        Connect();

        //SQL to add the new contact
        String sqlString = "DELETE from contacts WHERE phone_number= '" + number + "'";
        try {
            //PreparedStatement ps = con.prepareStatement(sqlString);
            Statement st = (Statement) con.createStatement();
            st.execute(sqlString);

        } catch (Exception e) {
            //print the error
            System.out.println("There was an error: " + e);
        }

        con.close();
    }

    public ObservableList<Person> popData() throws Exception {
        ObservableList<Person> list = FXCollections.observableArrayList();

        Connect(); //Open

        String sqlString = "SELECT * FROM contacts";

        //PreparedStatement ps = con.prepareStatement(sqlString);
        try {
            Statement st = (Statement) con.createStatement();
            st.execute(sqlString);

            ResultSet rs = st.executeQuery(sqlString);

            while (rs.next()) {
                String fn = rs.getString("first_name");
                String ln = rs.getString("last_name");
                String pn = rs.getString("phone_number");
                String ad = rs.getString("address");

                
                list.add(new Person(fn, ln, pn, ad));
            }

        } catch (Exception e) {
            System.out.println("Rrror found: " + e);
        }
        closeCon();
        return list;
    }

    public void closeCon() throws SQLException {
        con.close();
    }

}//end CLASS 
