package org.ros.android.android_tutorial_pubsub;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.ImageButton;
import android.widget.ImageView;

//import org.ros.cafe_Boss_Emp.app.R;


/**
  Created by user on 2017-06-24.
 */

public class TableActivity extends AppCompatActivity {
    // counts the number of times a table is clicked
    int count = 0;

    @Override
    public void onCreate(Bundle savedInstanceState){
        super.onCreate(savedInstanceState);

        setTitle("ROBOTBUCKS");
       // setTitleColor(Color.GREEN);
        setContentView(R.layout.tables);

        final ImageButton T1 = (ImageButton) findViewById(R.id.Table1);
        final ImageButton T2 = (ImageButton) findViewById(R.id.Table2);
        final ImageButton T3 = (ImageButton) findViewById(R.id.Table3);
        final ImageButton T4 = (ImageButton) findViewById(R.id.Table4);
        final ImageButton T5 = (ImageButton) findViewById(R.id.Table5);
        final ImageButton T6 = (ImageButton) findViewById(R.id.Table6);
        final ImageButton T7 = (ImageButton) findViewById(R.id.Table7);

        /* Listener on tables 1 ~ 7*/

        T1.setOnClickListener(new View.OnClickListener(){
            public void onClick(View v){
                if(count==0 ){
     //             T1.setBackgroundColor(getResources().getColor(android.R.color.holo_purple));
                    T1.setScaleType(ImageView.ScaleType.FIT_CENTER);
                    T1.setImageResource(R.drawable.stock);
                    count++;
                    Intent intent = new Intent(getApplication(), OrderedContentActivity.class);
                    startActivity(intent);
                }
                else if(count==1){
                    T1.setScaleType(ImageView.ScaleType.FIT_CENTER);
                    T1.setImageResource(R.drawable.progress);
                    count++;
                }
                else if(count==2){
                    T1.setScaleType(ImageView.ScaleType.FIT_CENTER);
                    T1.setImageResource(R.drawable.delivery);
                    count++;
                }
                else {
                    T1.setImageResource(R.drawable.order_end);
                    count = 0;
                }
            }
        });
        T2.setOnClickListener(new View.OnClickListener(){
            public void onClick(View v){
                if(count==0 ){
                    T2.setScaleType(ImageView.ScaleType.FIT_CENTER);
                    T2.setImageResource(R.drawable.stock);
                    count++;
                }
                else if(count==1){
                    T2.setScaleType(ImageView.ScaleType.FIT_CENTER);
                    T2.setImageResource(R.drawable.progress);
                    count++;
                }
                else if(count==2){
                    T2.setScaleType(ImageView.ScaleType.FIT_CENTER);
                    T2.setImageResource(R.drawable.delivery);
                    count++;
                }
                else {
                    T2.setImageResource(R.drawable.order_end);
                    count = 0;
                }
            }
        });
        T3.setOnClickListener(new View.OnClickListener(){
            public void onClick(View v){
                if(count==0 ){
                    T3.setScaleType(ImageView.ScaleType.FIT_CENTER);
                    T3.setImageResource(R.drawable.stock);
                    Intent intent = new Intent(getApplication(), OrderedContentActivity.class);
                    startActivity(intent);
          //          count++;    // count 증가시키고 준문 내용 화면으로 넘어감
                }
                else if(count==1){
                    T3.setScaleType(ImageView.ScaleType.FIT_CENTER);
                    T3.setImageResource(R.drawable.progress);
                    count++;
                }
                else if(count==2){
                    T3.setScaleType(ImageView.ScaleType.FIT_CENTER);
                    T3.setImageResource(R.drawable.delivery);
                    count++;
                }
                else {
                    T3.setImageResource(R.drawable.order_end);
                    count = 0;
                }
            }
        });
        T4.setOnClickListener(new View.OnClickListener(){
            public void onClick(View v){
                if(count==0 ){
                    T4.setScaleType(ImageView.ScaleType.FIT_CENTER);
                    T4.setImageResource(R.drawable.stock);
                    count++;
                }
                else if(count==1){
                    T4.setScaleType(ImageView.ScaleType.FIT_CENTER);
                    T4.setImageResource(R.drawable.progress);
                    count++;
                }
                else if(count==2){
                    T4.setScaleType(ImageView.ScaleType.FIT_CENTER);
                    T4.setImageResource(R.drawable.delivery);
                    count++;
                }
                else {
                    T4.setImageResource(R.drawable.order_end);
                    count = 0;
                }
            }
        });
        T5.setOnClickListener(new View.OnClickListener(){
            public void onClick(View v){
                if(count==0 ){
                    T5.setScaleType(ImageView.ScaleType.FIT_CENTER);
                    T5.setImageResource(R.drawable.stock);
                    count++;
                }
                else if(count==1){
                    T5.setScaleType(ImageView.ScaleType.FIT_CENTER);
                    T5.setImageResource(R.drawable.progress);
                    count++;
                }
                else if(count==2){
                    T5.setScaleType(ImageView.ScaleType.FIT_CENTER);
                    T5.setImageResource(R.drawable.delivery);
                    count++;
                }
                else {
                    T5.setImageResource(R.drawable.order_end);
                    count = 0;
                }
            }
        });
        T6.setOnClickListener(new View.OnClickListener(){
            public void onClick(View v){
                if(count==0 ){
                    T6.setScaleType(ImageView.ScaleType.FIT_CENTER);
                    T6.setImageResource(R.drawable.stock);
                    count++;
                }
                else if(count==1){
                    T6.setScaleType(ImageView.ScaleType.FIT_CENTER);
                    T6.setImageResource(R.drawable.progress);
                    count++;
                }
                else if(count==2){
                    T6.setScaleType(ImageView.ScaleType.FIT_CENTER);
                    T6.setImageResource(R.drawable.delivery);
                    count++;
                }
                else {
                    T6.setImageResource(R.drawable.order_end);
                    count = 0;
                }
            }
        });
        /*
        T7.setOnClickListener(new View.OnClickListener(){
            public void onClick(View v){
                if(count==0 ){
                    T7.setScaleType(ImageView.ScaleType.FIT_CENTER);
                    T7.setImageResource(R.drawable.awaiting);
                    count++;
                }
                else if(count==1){
                    T7.setScaleType(ImageView.ScaleType.FIT_CENTER);
                    T7.setImageResource(R.drawable.coffeemaking_machine);
                    count++;
                }
                else if(count==2){
                    T7.setScaleType(ImageView.ScaleType.FIT_CENTER);
                    T7.setImageResource(R.drawable.coffee_finished);
                    count++;
                }
                else {
                    T7.setImageResource(R.drawable.long_table);
                    count = 0;
                }
            }
        }); */
        }

    }
