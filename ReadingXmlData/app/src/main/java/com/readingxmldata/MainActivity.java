package com.readingxmldata;

import android.app.ProgressDialog;
import android.os.AsyncTask;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

import org.w3c.dom.Document;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;
import org.xml.sax.SAXException;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;

public class MainActivity extends AppCompatActivity {
    private TextView txtMsg;
    private Button btnGoParsePlayers, getBtnGoParseCourse;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_main);

        txtMsg = (TextView) findViewById(R.id.txtMsg);
        btnGoParsePlayers = (Button) findViewById(R.id.btnReadXmlPlayers);
        btnGoParsePlayers.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                btnGoParsePlayers.setEnabled(false);
                new BackgroundAsyncTask().execute("golfers.xml", "Name", "Phone");
            }});

        btnGoParsePlayers = (Button) findViewById(R.id.btnReadXmlCourse);
        getBtnGoParseCourse.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                getBtnGoParseCourse.setEnabled(false);
                new BackgroundAsyncTask().execute("manakiki_holes1and2.xml", "course", "name", "coordinates");
            }});
    }

    private class BackgroundAsyncTask extends AsyncTask<String, Void, String> {
        ProgressDialog dialog = new ProgressDialog(MainActivity.this);
        @Override
        protected void onPostExecute(String result) {
            super.onPostExecute(result);
            dialog.dismiss();
            txtMsg.setText(result.toString());
        }
        @Override
        protected void onPreExecute() {
            super.onPreExecute();
            dialog.setMessage("Please wait...");
            dialog.setCancelable(false);
            dialog.show();
        }
        @Override
        protected void onProgressUpdate(Void... values) { super.onProgressUpdate(values); }
        @Override
        protected String doInBackground(String... params) { return useW3CParser(params); }// doInBackground
    }

    private String useW3CParser(String... params) {
        int n = params.length;
        String xmlFileName= params[0];
        String[] elementName= new String[n -1];
        for(int i = 0; i < n -1; i++) elementName[i] = params[i+ 1];
        StringBuilder str = new StringBuilder();
        try{
            InputStream is = new FileInputStream(new File("mnt/sdcard/" + xmlFileName));
            DocumentBuilder docBuilder= DocumentBuilderFactory.newInstance().newDocumentBuilder();
            Document document= docBuilder.parse(is);
            if(document == null) { Log.v("REALLY BAD!!!!", "document was NOT made by parser"); return"BAD-ERROR"; }
            NodeList[] elementList= new NodeList[n];
            for(int i = 0; i < n - 1; i++) {
                elementList[i] = document.getElementsByTagName(elementName[i]);
                str.append(getTextAndAttributesFromNode(elementList[i], elementName[i]));
            }
        }
        catch(FileNotFoundException e) { Log.e("W3C Error", e.getMessage()); }
        catch(ParserConfigurationException e) { Log.e("W3C Error", e.getMessage()); }
        catch(SAXException e) { Log.e("W3C Error", e.getMessage()); }
        catch(IOException e) { Log.e("W3C Error", e.getMessage()); }
        return str.toString();
    }

    private Object getTextAndAttributesFromNode(NodeList list, String strElementName) {
        StringBuilder str = new StringBuilder();
// dealing with the <strElementName> tag
        str.append("\n\nNodeListfor: <" +strElementName + ">Tag");
        for (int i = 0; i < list.getLength(); i++) {
// extract TEXT enclosed inside <element> tags
            Node node = list.item(i);
            String text = node.getTextContent();
            str.append("\n " + i + ": "+text);
// get ATTRIBUTES inside the current element
            int size = node.getAttributes().getLength();
            for (int j = 0; j < size; j++) {
                String attrName = node.getAttributes().item(j).getNodeName();
                String attrValue = node.getAttributes().item(j).getNodeValue();
                str.append("\n attr.info -" + i + "-"+j + ": "+attrName + " "+attrValue);
            }
        }
        return str;
    }
}