package myapp.jesin.com.blood4life;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

public class FrontPage extends AppCompatActivity {
    Button finddonor;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_front_page);
        finddonor=(Button)findViewById(R.id.finddonor);
        finddonor.setOnClickListener(new View.OnClickListener() {
            @Override
            
            public void onClick(View view) {
                Intent donor=new Intent(FrontPage.this,SearchDonor.class);
                startActivity(donor);
            }
        });
        Button gotologin;
        gotologin=(Button)findViewById(R.id.gotologin);
        gotologin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent login=new Intent(FrontPage.this,LoginPage.class);
                startActivity(login);
            }
        });
        final Button newsfeed;
        newsfeed=(Button)findViewById(R.id.newsfeed);
        newsfeed.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent news=new Intent(FrontPage.this,Emergency.class);
                startActivity(news);
            }
        });
    }
}

