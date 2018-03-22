package myapp.jesin.com.blood4life;

import android.app.ListActivity;
import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ListView;
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


public class Emergency extends AppCompatActivity {
    ListView emerg;
    List<String> ebld;
    ArrayAdapter adapter;
    String objid;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_emergency);


    Parse.initialize(this);
    emerg=findViewById(R.id.elist);
    ParseQuery<ParseObject> query=ParseQuery.getQuery("Emergency");

    ebld=new ArrayList<>();
    adapter=new ArrayAdapter(this,android.R.layout.simple_list_item_1,ebld);

    query.findInBackground(new FindCallback<ParseObject>() {
        @Override
        public void done(List<ParseObject> objects, ParseException e) {
            if(objects.size()>0){

                for (ParseObject emer:objects){

                    String bldtype=emer.getString("bloodtype");
                    ebld.add(bldtype);
                }
                emerg.setAdapter(adapter);
            }
        }
    });
    emerg.setOnItemClickListener(new AdapterView.OnItemClickListener() {
        @Override
        public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {
            String bldty=adapterView.getItemAtPosition(i).toString();
            Intent intent=new Intent(Emergency.this,Display1Emerg.class);
            intent.putExtra("bloodtype",bldty);
            startActivity(intent);
        }
    });

    }
}
