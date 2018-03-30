package sample;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;
import java.io.IOException;
import java.net.URI;
import java.net.URL;
import java.util.ResourceBundle;
import java.util.Scanner;
import com.jayway.jsonpath.JsonPath;
import javafx.fxml.FXML;
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

public class Controller_ocr{
    @FXML
    public TextField txt1;
    public TextArea ans;

    public static final String subscriptionKey = "7c9a6a30f1d44f1692c6635f2e1c7e6c";


    public static final String uriBase = "https://westcentralus.api.cognitive.microsoft.com/vision/v1.0/recognizeText?handwriting=true";
    public void getdata() {
        System.out.println(txt1.getText());
    }
    public void runocr1()
    {


        HttpClient textClient = new DefaultHttpClient();
        HttpClient resultClient = new DefaultHttpClient();

        try
        {

            URI uri = new URI(uriBase);
            HttpPost textRequest = new HttpPost(uri);


            textRequest.setHeader("Content-Type", "application/json");
            textRequest.setHeader("Ocp-Apim-Subscription-Key", subscriptionKey);
            //System.out.println("enter the Url of the image");
            Scanner in=new Scanner(System.in);
            String url=txt1.getText();
            StringEntity requestEntity =
                    new StringEntity("{\"url\":\""+url+"\"}");

            textRequest.setEntity(requestEntity);

            HttpResponse textResponse = textClient.execute(textRequest);

            if (textResponse.getStatusLine().getStatusCode() != 202)
            {
                HttpEntity entity = textResponse.getEntity();
                String jsonString = EntityUtils.toString(entity);
                JSONObject json = new JSONObject(jsonString);
                System.out.println("Error:\n");
                System.out.println(json.toString(2));
                return;
            }

            String operationLocation = null;

            Header[] responseHeaders = textResponse.getAllHeaders();
            for(Header header : responseHeaders) {
                if(header.getName().equals("Operation-Location"))
                {
                    operationLocation = header.getValue();
                    break;
                }
            }

            //System.out.println("\nHandwritten text submitted. Waiting 10 seconds to retrieve the recognized text.\n");
            ans.setText("Handwritten text submitted. Waiting 10 seconds to retrieve the recognized text.");
            Thread.sleep(10000);

            HttpGet resultRequest = new HttpGet(operationLocation);
            resultRequest.setHeader("Ocp-Apim-Subscription-Key", subscriptionKey);

            HttpResponse resultResponse = resultClient.execute(resultRequest);
            HttpEntity responseEntity = resultResponse.getEntity();

            if (responseEntity != null)
            {
                String jsonString = EntityUtils.toString(responseEntity);
                JSONObject json = new JSONObject(jsonString);
               // System.out.println("Text recognition result response: \n");

                //System.out.println(""+ JsonPath.read(jsonString,"$.recognitionResult.lines[*].text"));
                ans.setText(JsonPath.read(jsonString,"$.recognitionResult.lines[*].text").toString());
            }
        }
        catch (Exception e)
        {
            System.out.println(e.getMessage());
        }
    }
   // public void initialize(URL url, ResourceBundle rb) {
    //}

}
