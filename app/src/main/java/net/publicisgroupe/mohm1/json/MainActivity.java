package net.publicisgroupe.mohm1.json;

import android.os.AsyncTask;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.TextView;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import static net.publicisgroupe.mohm1.json.R.id.button1;

public class MainActivity extends AppCompatActivity {


TextView txtLabel1;
TextView txtLabel2;
ImageButton Get;
String name1;
String name2;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        setContentView(R.layout.activity_main);
        Get=findViewById(R.id.button1);
        txtLabel1=findViewById(R.id.textView1) ;
        txtLabel2=findViewById(R.id.textView2) ;

        Get.setOnClickListener(new View.OnClickListener() {
       @Override
       public void onClick(View v)
       {
           txtLabel1.setText(name1);
           Log.d(MainActivity.class.getSimpleName(),"\n name 1 is :"+name1);
           Log.d(MainActivity.class.getSimpleName(),"\n name 2 is :"+name2);
         txtLabel2.setText(name2);
            Sapient sapient = new Sapient();
            sapient.execute();

       }
   });

    }
public class Sapient extends AsyncTask
{
    @Override
    protected void onPreExecute() {
        super.onPreExecute();
    }

    @Override
    protected Object doInBackground(Object[] objects) {
        HttpHandler httpHandler = new HttpHandler();
        String jsonstr = httpHandler.makeServiceCall("https://api.androidhive.info/contacts/");
        try
        {
            JSONObject jsonObject = new JSONObject(jsonstr);
            JSONArray jsonArray = jsonObject.getJSONArray("contacts");
            JSONObject jsonObject1 = jsonArray.getJSONObject(2);
            JSONObject jsonObject2 = jsonArray.getJSONObject(10);

            name1 = jsonObject1.getString("name");
            name2 = jsonObject2.getString("name");

        }
        catch(JSONException e)
        { e.printStackTrace();
        }
        return null;
    }

    @Override
    protected void onPostExecute(Object o) {
        super.onPostExecute(o);
    }
}
}
