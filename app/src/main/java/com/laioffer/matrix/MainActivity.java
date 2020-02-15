package com.laioffer.matrix;

import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;

import android.content.Intent;
import android.content.res.Configuration;
import android.os.Bundle;
import android.util.Log;
import android.widget.ArrayAdapter;
import android.widget.ListView;

public class MainActivity extends AppCompatActivity  implements EventFragment.OnItemSelectListener, CommentFragment.OnItemSelectListener{

    private EventFragment listFragment;
    private CommentFragment gridFragment;




    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        Log.e("Life cycle test", "We are at onCreate()");

        //add list view
        if (getSupportFragmentManager().findFragmentById(R.id.event_container) == null) {
            listFragment = new EventFragment();
            getSupportFragmentManager().beginTransaction().add(R.id.event_container, listFragment).commit();
        }

        //add Gridview
        if (getSupportFragmentManager().findFragmentById(R.id.comment_container) == null && isTablet()) {
            gridFragment = new CommentFragment();
            getSupportFragmentManager().beginTransaction().add(R.id.comment_container, gridFragment).commit();
        }
    }



    private boolean isTablet() {
        return (getApplicationContext().getResources().getConfiguration().screenLayout &
                Configuration.SCREENLAYOUT_SIZE_MASK) >=
                Configuration.SCREENLAYOUT_SIZE_LARGE;
    }

    // Add this method to main activity
    @Override
    public void onItemSelected(int position) {
        if (!isTablet()) {
//            Intent intent = new Intent(this, EventGridActivity.class);
//            intent.putExtra("position", position);
//            startActivity(intent);
            Fragment fragment = CommentFragment.newInstance(position);
            getSupportFragmentManager().beginTransaction().replace(R.id.event_container, fragment).addToBackStack(null).commit();

        } else {
            gridFragment.onItemSelected(position);
        }
    }



    @Override
    public void onCommentSelected(int position) {
        listFragment.onItemSelected(position);
    }

}




