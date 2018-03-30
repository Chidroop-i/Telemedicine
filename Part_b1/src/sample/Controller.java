package sample;

import java.io.IOException;
import java.net.URI;
import java.net.URL;
import java.util.ResourceBundle;
import java.util.Scanner;
import com.jayway.jsonpath.JsonPath;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.image.Image;
import javafx.stage.Stage;
import org.apache.http.HttpEntity;
import org.apache.http.HttpResponse;
import org.apache.http.client.HttpClient;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.entity.StringEntity;
import org.apache.http.impl.client.DefaultHttpClient;
import org.apache.http.util.EntityUtils;
import org.apache.http.Header;
import org.json.JSONObject;

public class Controller implements Initializable {
    public void runocr (){
        Parent root1;
        try {
            root1 = FXMLLoader.load(getClass().getResource("scene_ocr.fxml"));
            Stage stage = new Stage();
            stage.getIcons().add(new Image("sample/logo.png"));
            stage.setTitle("Prescriptions");
            stage.setScene(new Scene(root1, 840, 370));
            stage.show();
            // Hide this current window (if this is what you want)
            // ((Node)(event.getSource())).getScene().getWindow().hide();
        }
        catch (IOException e) {
            e.printStackTrace();
        }
    }
    public void runkey (){
        Parent root2;
        try {
            root2 = FXMLLoader.load(getClass().getResource("scene_key.fxml"));
            Stage keystage = new Stage();
            keystage.getIcons().add(new Image("sample/logo.png"));
            keystage.setTitle("Prescriptions");
            keystage.setScene(new Scene(root2, 840, 370));
            keystage.show();
            // Hide this current window (if this is what you want)
            // ((Node)(event.getSource())).getScene().getWindow().hide();
        }
        catch (IOException e) {
            e.printStackTrace();
        }
    }
    public void initialize(URL url, ResourceBundle rb) {
        }
}

