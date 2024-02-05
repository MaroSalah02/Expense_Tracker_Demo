package Application;

import javafx.collections.FXCollections;
import javafx.fxml.FXML;
import javafx.scene.control.*;
import server.db;

import java.time.LocalDate;

import java.awt.event.ActionEvent;
import java.util.List;


public class expense {
    //create expense method in class with successful sql connection
    //return output if it was successful

    @FXML
    private Button Submit;
    @FXML
    private ComboBox<String> TagName;
    @FXML
    private ComboBox<String> Budget;
    @FXML
    private TextField Balance;
    @FXML
    private TextField Comment;
    @FXML
    private TextField Amount;
    @FXML
    private DatePicker datePicker;
    int chooseBudget;
    String amountText;
    String commentText;
    String chooseTag;

    @FXML
    public void handleDateSelected(javafx.event.ActionEvent actionEvent) {
        // Request focus for another thing, to remove flashing line
        datePicker.getParent().requestFocus();
    }

    public void initialize() {

        datePicker.setValue(LocalDate.now());

        server.db database = new server.db();
        database.executeStoredProcedure();

        List<String> tagOptions = database.getTagOptions();
        List<List<String>> budgetOptions = database.getBudgetOptions();

        TagName.setItems(FXCollections.observableArrayList(tagOptions));
        TagName.setValue("Tag");

        // Listen for selection changes
        TagName.getSelectionModel().selectedItemProperty().addListener((observable, oldValue, newValue) -> {
            // Handle the selected option change
            chooseTag = newValue;
            System.out.println("Selected tag: " + newValue);
        });


        Budget.setItems(FXCollections.observableArrayList(budgetOptions.get(0)));
        Budget.setValue("Budget");
        Balance.setText("Balance");
        Balance.setVisible(false);

        Budget.getSelectionModel().selectedItemProperty().addListener((observable, oldValue, newValue) -> {
            chooseBudget = Budget.getSelectionModel().getSelectedIndex()+1;
            for(int i = 0; i<budgetOptions.get(1).size();i++){
                if(Budget.getSelectionModel().getSelectedIndex() == i){
                    Balance.setVisible(true);
                    Balance.setText(budgetOptions.get(1).get(i));
                }
            }
            System.out.println("Selected budget: " + newValue);
        });

        Submit.setOnAction(event -> {
            commentText = Comment.getText();
            amountText = Amount.getText();
            if (!amountText.isEmpty()) {
                int amountValue = Integer.parseInt(amountText);
                database.executeEditingExpensesProcedure(database.LogUser, 0, commentText, amountValue, chooseTag, chooseBudget, 'A');
                Comment.clear();
                Amount.clear();
            } else {
                System.err.println("Amount field is empty");
            }
        });


    }

}

