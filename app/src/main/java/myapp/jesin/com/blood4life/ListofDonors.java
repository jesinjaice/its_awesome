package myapp.jesin.com.blood4life;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.Toast;

import com.parse.FindCallback;
import com.parse.Parse;
import com.parse.ParseException;
import com.parse.ParseObject;
import com.parse.ParseQuery;

import java.util.ArrayList;
import java.util.List;

public class ListofDonors extends AppCompatActivity {

    ListView dnrlst;
    List<String> dnr;
    ArrayAdapter adapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_listof_donors);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);


        String bt=getIntent().getStringExtra("bldtype");
        String ct=getIntent().getStringExtra("city");
        String dt=getIntent().getStringExtra("district");

        Parse.initialize(this);

        dnrlst=findViewById(R.id.dlist);
        dnr=new ArrayList<>();
        adapter=new ArrayAdapter(this,android.R.layout.simple_list_item_1,dnr);

        ParseQuery<ParseObject> query=ParseQuery.getQuery("Donor");
        query.whereEqualTo("BloodType",bt);
        query.whereEqualTo("City",ct);
        query.whereEqualTo("District",dt);
        query.findInBackground(new FindCallback<ParseObject>() {
            @Override
            public void done(List<ParseObject> objects, ParseException e) {
                if (objects.size()>0)
                {
                    for (ParseObject don:objects){
                        String name=don.getString("FullName");
                        dnr.add(name);
                    }
                    dnrlst.setAdapter(adapter);
                }
                else
                    Toast.makeText(ListofDonors.this,"No Donors Found, Post as Emergency",Toast.LENGTH_LONG).show();

            }
        });

        dnrlst.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {
                String fn=adapterView.getItemAtPosition(i).toString();
                Intent intent=new Intent(ListofDonors.this,ProfileView.class);
                intent.putExtra("name",fn);
                startActivity(intent);
            }
        });
    }
}
