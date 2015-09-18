package phonebook;

import phonebook.view.UI;
import javafx.application.Application;
import javafx.stage.Stage;


public class PhoneBook extends Application {

    public static void main(String[] args) throws Exception {
        Application.launch(args);
    }

    @Override
    public void start(Stage primaryStage) throws Exception {
        UI ui = new UI();
        ui.stageOne(primaryStage);
    }
}
