package Application;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.layout.AnchorPane;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;
import java.util.logging.Level;
import java.util.logging.Logger;

public class Controller implements Initializable {
    @FXML
    private AnchorPane contentArea;

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        if (contentArea.getChildren().isEmpty()) {
            try {
                Parent fxml = FXMLLoader.load(getClass().getResource("/resources/expense.fxml"));
                contentArea.getChildren().setAll(fxml);
            } catch (IOException ex) {
                Logger.getLogger(Controller.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
    }

    public void expense(javafx.event.ActionEvent actionEvent) throws IOException{
        Parent fxml = FXMLLoader.load(getClass().getResource("/resources/expense.fxml"));
        contentArea.getChildren().removeAll();
        contentArea.getChildren().setAll(fxml);
    }
    public void budget(javafx.event.ActionEvent actionEvent) throws IOException{
        Parent fxml = FXMLLoader.load(getClass().getResource("/resources/budget.fxml"));
        contentArea.getChildren().removeAll();
        contentArea.getChildren().setAll(fxml);
    }
    public void history(javafx.event.ActionEvent actionEvent) throws IOException{
        Parent fxml = FXMLLoader.load(getClass().getResource("/resources/history.fxml"));
        contentArea.getChildren().removeAll();
        contentArea.getChildren().setAll(fxml);
    }
    public void tags(javafx.event.ActionEvent actionEvent) throws IOException{
        Parent fxml = FXMLLoader.load(getClass().getResource("/resources/tags.fxml"));
        contentArea.getChildren().removeAll();
        contentArea.getChildren().setAll(fxml);
    }
    public void analysis(javafx.event.ActionEvent actionEvent) throws IOException{
        Parent fxml = FXMLLoader.load(getClass().getResource("/resources/analysis.fxml"));
        contentArea.getChildren().removeAll();
        contentArea.getChildren().setAll(fxml);
    }
}