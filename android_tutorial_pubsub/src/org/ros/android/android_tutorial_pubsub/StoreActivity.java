package org.ros.android.android_tutorial_pubsub;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.ImageButton;

//import android.support.v7.widget.Toolbar;

//import org.ros.cafe_Boss_Emp.app.R;

/**
  Created by user on 2017/04/08.
 */

public class StoreActivity extends AppCompatActivity {
    public  ImageButton store_btn, user_icon;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        //  setTitle("Coffee Please ");
        setContentView(R.layout.store_activity);

        store_btn = (ImageButton) findViewById(R.id.Store);
        user_icon = (ImageButton) findViewById(R.id.user);

        onStoreButtonClick();
        onUserIconClick();

//        store_btn.setOnClickListener(
//                new Button.OnClickListener() {
//                    public void onClick(View v) {
//                        Intent intent = new Intent(StoreActivity.this, LoginActivity.class);
//                        startActivity(intent);
//                    }
//                }
//        );
    }

    public void onStoreButtonClick(){
        store_btn.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View view){
                Intent intent = new Intent(StoreActivity.this, TableActivity.class);    // LoginActivity
                startActivity(intent);
            }
        });
    }

    public void onUserIconClick(){
        user_icon.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View view){
                Intent intent = new Intent(StoreActivity.this, LoginActivity.class);
                startActivity(intent);
            }
        });
    }


}
