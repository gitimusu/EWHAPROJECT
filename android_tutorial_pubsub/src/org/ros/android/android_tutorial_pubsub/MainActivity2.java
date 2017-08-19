package org.ros.android.android_tutorial_pubsub;

import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.support.v7.app.ActionBar;
import android.support.v7.app.AppCompatActivity;
import android.view.Menu;
import android.view.View;
import android.widget.ImageView;

//import org.ros.cafe_Boss_Emp.app.R;

public class MainActivity2 extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        // Find the toolbar view inside the main activity layout
        //Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        // Sets Toolbar to act as the ActionBar for main activity
        // Make sure the toolbar exists in the activity and it's not null
        //setSupportActionBar(toolbar);
        try{
            getSupportActionBar().setDisplayOptions(ActionBar.DISPLAY_SHOW_CUSTOM);
            getSupportActionBar().setCustomView(R.layout.toolbar);
        }catch (Exception e){
            System.out.println(e.getMessage());
        }
       final  ImageView robot = (ImageView) findViewById(R.id.robot_welcome);
        robot.setOnClickListener(new View.OnClickListener(){
            public void onClick(View v){
                Intent intent = new Intent(getApplication(), LoginActivity.class);
                startActivity(intent);
            }
        });

       /* new Handler().postDelayed(new Runnable(){
            public void run() {
                /* create an intent that will start the main activity. */
//                Intent mainIntent = new Intent(MainActivity2.this, LoginActivity.class );
//                mainIntent.putExtra("id", "1");
                // MainActivity.this.startActivity(mainIntent);
//                startActivity(mainIntent);
                /* Finish main activity so user can't go back to it.*/
//                MainActivity2.this.finish();

                /* Apply our main exit (fade out) and main entry (fade in) animation transitions.
                overridePendingTransition(R.anim.slide_in, R.anim.slide_out);
            }
        } , 2000); */

    }
    // Inflate Menu icons
    @Override
    public boolean onCreateOptionsMenu(Menu menu){
        // inflate the menu, this adds items to the actionbar
        getMenuInflater().inflate(R.menu.menu, menu);
        return true;
    }
}
