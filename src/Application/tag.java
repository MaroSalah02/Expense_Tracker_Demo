package Application;

import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.ListView;
import javafx.scene.control.TextField;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;

import java.util.List;


public class tag {
    @FXML
    private ListView<String> listOfTags;
    @FXML
    private Button Submit;
    @FXML
    private TextField createTag;
    String newTag;
    public void initialize(){

        server.db database = new server.db();
        database.executeGettingStoredProcedures();

        List<String> tagOptions = database.getTagOptions();

        ObservableList<String> items = FXCollections.observableArrayList(tagOptions);
        listOfTags.setItems(items);


        Submit.setOnAction(event -> {
            newTag = createTag.getText();
            if (!newTag.isEmpty()) {
                database.executeEditingTagStoredProcedure(database.LogUser, newTag,null, 'A');
                createTag.clear();
            } else {
                System.err.println("Amount field is empty");
            }
        });

    }
}
