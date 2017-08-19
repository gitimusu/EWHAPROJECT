package org.ros.android.android_tutorial_pubsub;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.ActionBar;
import android.support.v7.app.AppCompatActivity;
import android.view.Menu;
import android.view.View;
import android.widget.ImageButton;

//import android.support.v7.widget.Toolbar;

//import org.ros.cafe_Boss_Emp.app.R;

/**
  Created by user on 2017/04/08.
 */

public class StoreActivity extends AppCompatActivity {
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
      //  setTitle("Coffee Please ");
        setContentView(R.layout.store_activity);
      //  Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        try{
            getSupportActionBar().setDisplayOptions(ActionBar.DISPLAY_SHOW_CUSTOM);
            getSupportActionBar().setCustomView(R.layout.toolbar);
        }catch (Exception e){
            System.out.println(e.getMessage());
        }

        ImageButton btnTableActivity = (ImageButton) findViewById(R.id.Store);
        btnTableActivity.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                Intent intent = new Intent(getApplicationContext(), TableActivity.class);
                startActivity(intent);
            }
        });
    }
    @Override
    public boolean onCreateOptionsMenu(Menu menu){
        // inflate the menu, this adds items to the actionbar
        getMenuInflater().inflate(R.menu.menu_storage_screen, menu);
        return true;
    }
}
