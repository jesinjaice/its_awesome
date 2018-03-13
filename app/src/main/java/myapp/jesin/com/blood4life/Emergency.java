package myapp.jesin.com.blood4life;

import android.app.ListActivity;
import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.SimpleAdapter;
import android.widget.Toast;

import com.parse.FindCallback;
import com.parse.GetCallback;
import com.parse.Parse;
import com.parse.ParseException;
import com.parse.ParseObject;
import com.parse.ParseQuery;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;


public class Emergency extends ListActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_emergency);

        Parse.initialize(this);
        ParseQuery<ParseObject> query=new ParseQuery("Emergency");
        query.findInBackground(new FindCallback<ParseObject>() {
            @Override
            public void done(List<ParseObject> objects, ParseException e) {
                if (e==null){
                    ArrayList<HashMap<String,String>> articles=new ArrayList<HashMap<String, String>>();
                    for (ParseObject object:objects){
                        HashMap<String,String>article=new HashMap<String, String>();
                        article.put("bloodtype",object.getString("bloodtype"));
                        article.put("unitblood",object.getString("unitblood"));
                        article.put("city",object.getString("city"));
                        article.put("district",object.getString("district"));
                        article.put("nameconatact",object.getString("nameconatact"));
                        article.put("numcontact",object.getString("numcontact"));
                        articles.add(article);
                    }
                    SimpleAdapter adapter=new SimpleAdapter(Emergency.this,articles,android.R.layout.simple_list_item_2,new String[]{"bloodtype","unitblood","city","district","nameconatact,numcontact"},new int[]{R.id.tbld,R.id.tunit,R.id.tcity,R.id.tdist,R.id.tname,R.id.tnum});
                    setListAdapter(adapter);
                }


            }
        });


    }
}
