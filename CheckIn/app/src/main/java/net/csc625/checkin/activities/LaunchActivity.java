package net.csc625.checkin.activities;

import android.content.Intent;
import android.os.AsyncTask;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import net.csc625.checkin.R;

public class LaunchActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_launch);
        new BackgroundTask().execute();

    }

    public class BackgroundTask extends AsyncTask {
        Intent intent = new Intent(LaunchActivity.this, LoginActivity.class);

        @Override
        protected void onPreExecute() {
            super.onPreExecute();
        }
        @Override
        protected Object doInBackground(Object[] params) {
            try {
                Thread.sleep(3000);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            return null;
        }
        @Override
        protected void onPostExecute(Object o) {
            super.onPostExecute(o);
            startActivity(this.intent);
            finish();
        }
    }

}
