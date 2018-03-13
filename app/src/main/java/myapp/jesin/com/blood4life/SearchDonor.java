package myapp.jesin.com.blood4life;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.Spinner;
import android.widget.Toast;

public class SearchDonor extends AppCompatActivity implements AdapterView.OnItemSelectedListener {
    Button needhelp;
    Button emerg;
    Spinner sblood;
    ArrayAdapter<CharSequence> adapter4;
    Spinner sdist;
    ArrayAdapter<CharSequence> adapter5;
    Spinner scity;
    ArrayAdapter<CharSequence> adapter6;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_search_donor);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        needhelp=(Button)findViewById(R.id.needhelp);
        needhelp.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent help= new Intent(SearchDonor.this,HelpPage.class);
                startActivity(help);
            }
        });
        emerg=(Button)findViewById(R.id.postemerg);
        emerg.setOnClickListener(new View.OnClickListener() {
                                     @Override
                                     public void onClick(View view) {
                                         Intent goemerg=new Intent(SearchDonor.this,PostEmergency.class);
                                         startActivity(goemerg);
                                     }
                                 });
                sblood = findViewById(R.id.blood);
        adapter4 = ArrayAdapter.createFromResource(this, R.array.bloodgroup, android.R.layout.simple_spinner_item);
        adapter4.setDropDownViewResource(R.layout.support_simple_spinner_dropdown_item);
        sblood.setAdapter(adapter4);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        sblood.setOnItemSelectedListener(this);
        sdist=findViewById(R.id.dist);
        adapter5=ArrayAdapter.createFromResource(this,R.array.districts,android.R.layout.simple_spinner_item);
        adapter5.setDropDownViewResource(R.layout.support_simple_spinner_dropdown_item);
        sdist.setAdapter(adapter5);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        sdist.setOnItemSelectedListener(this);
        scity=findViewById(R.id.city);
        adapter6=ArrayAdapter.createFromResource(this,R.array.cities,android.R.layout.simple_spinner_item);
        adapter6.setDropDownViewResource(R.layout.support_simple_spinner_dropdown_item);
        scity.setAdapter(adapter6);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        scity.setOnItemSelectedListener(this);

    }

    @Override
    public void onItemSelected(AdapterView<?> adapterView, View view, int i, long l) {
        String text=adapterView.getItemAtPosition(i).toString();
        Toast.makeText(adapterView.getContext(),text,Toast.LENGTH_SHORT);
    }

    @Override
    public void onNothingSelected(AdapterView<?> adapterView) {

    }
}
