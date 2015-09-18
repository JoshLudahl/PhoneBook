package phonebook.view;

import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.layout.Pane;
import javafx.stage.Stage;

import phonebook.Person;
import phonebook.Utils.DBConnection;

public class UI {

    //FXML Variables
    @FXML
    private TextField txtFirstName;
    @FXML
    private TextField txtLastName;
    @FXML
    private TextField txtPhone_number;
    @FXML
    private TextField txtAddress;

    @FXML
    private TableView<Person> tv;
    @FXML
    private TableColumn<Person, String> fn, ln, num, addr;

    //regular variables
    private Stage ms;
    private String pn;
    private ObservableList<Person> data;

    public UI() {
    }

    public void stageOne(Stage stage) throws Exception {
        this.ms = stage;

        Pane root = (Pane) FXMLLoader.load(getClass().getResource("UI.fxml"));
        Scene scene = new Scene(root);

        //set the stage and scene
        ms.setTitle("Phone Book.");
        ms.setScene(scene);
        ms.show();

    }

    @FXML
    public void btnAddPerson(ActionEvent event) throws Exception {
        Person person = new Person(txtFirstName.getText(), txtLastName.getText(), txtPhone_number.getText(), txtAddress.getText());
        DBConnection db = new DBConnection();
        db.addPerson(person);

        txtFirstName.clear();
        txtLastName.clear();
        txtPhone_number.clear();
        txtAddress.clear();
        populateData();
        setItem();
    }

    @FXML
    public void btnRemovePerson(ActionEvent event) throws Exception {

        DBConnection db;
        if (pn != null) {
            System.out.println(pn);
            try {
                db = new DBConnection();
                db.removePerson(pn);
                System.out.println("Success my friend");
            } catch (Exception ex) {
                Logger.getLogger(UI.class.getName()).log(Level.SEVERE, null, ex);
            }
        }

        data.removeAll();
        populateData();
    }

    @FXML
    public void initialize() throws Exception {

        // Initialize the person table with the four columns.
        fn.setCellValueFactory(new PropertyValueFactory("firstName"));
        ln.setCellValueFactory(new PropertyValueFactory("lastName"));
        num.setCellValueFactory(new PropertyValueFactory("phoneNumber"));
        addr.setCellValueFactory(new PropertyValueFactory("address"));

        populateData();
        setItem();
    }

    public void populateData() throws Exception {
        try {
            DBConnection db = new DBConnection();
            data = db.popData();
            tv.setItems(data);
        } catch (Exception e) {
            System.out.println("ERROR" + e);
        }

    }

    public void setItem() {
        try {
            tv.getSelectionModel().selectedItemProperty().addListener(
                    (observable, oldValue, newValue) -> pn = newValue.getPhoneNumber());
        } catch (Exception e) {
            System.out.println("ERROR" + e);
        }
    }
    public void updateUI() {
        
    }

}
