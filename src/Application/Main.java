package Application;
import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.stage.Stage;

import java.io.IOException;
import java.net.URL;

public class Main extends Application {
    @Override
    public void start(Stage primaryStage) throws IOException {

        primaryStage.setTitle("Expense Tracker");
        primaryStage.setResizable(false);

        //css file
        String css = this.getClass().getResource("/resources/Style.css").toExternalForm();

        //Log scene
        /*FXMLLoader fxmlLoaderlog = new FXMLLoader(Main.class.getResource("/resources/log.fxml"));
        Scene LogScene = new Scene(fxmlLoaderlog.load());*/
        //base scene
        FXMLLoader fxmlLoaderexpense = new FXMLLoader(Main.class.getResource("/resources/base.fxml"));
        Scene Base = new Scene(fxmlLoaderexpense.load());

        primaryStage.setScene(Base);
        // When user is verified then change to ExpensesScene

        primaryStage.show();

        //set controller
        //fxmlLoaderexpense.setController(new Controller());

    }
    public static void main(String[] args) {

        new budget();
        server.db database = new server.db();
        database.close_connection();
        launch();
    }
}