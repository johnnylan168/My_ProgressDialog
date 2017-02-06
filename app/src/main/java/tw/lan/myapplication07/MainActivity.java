package tw.lan.myapplication07;

import android.app.Dialog;
import android.app.ProgressDialog;
import android.os.AsyncTask;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.widget.TextView;

public class MainActivity extends AppCompatActivity {

    private ProgressDialog mprogress;
    private TextView textView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        textView = (TextView)findViewById(R.id.my_text);
        setDialog();
    }

    private void setDialog() {
        mprogress = new ProgressDialog(this);
        mprogress.setTitle("載入");
        mprogress.setMessage("資料載入中，請稍候...");
        mprogress.setProgressStyle(ProgressDialog.STYLE_HORIZONTAL);
        mprogress.setProgress(0);
        mprogress.setMax(100);
        mprogress.show();
        new MyAysnc().execute(10);
    }

    class MyAysnc extends AsyncTask<Integer, Integer, Void> {

        @Override
        protected Void doInBackground(Integer... params) {
            for (int i = 1; i<=100 ; i++) {
                try {
                    Thread.sleep(100);
                    publishProgress(i);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
            return null;
        }

        @Override
        protected void onPostExecute(Void aVoid) {
            super.onPostExecute(aVoid);
            mprogress.dismiss();
            textView.setText("FINISH...");
        }

        @Override
        protected void onProgressUpdate(Integer... values) {
            super.onProgressUpdate(values);
            mprogress.setProgress(values[0]);
        }
    }
}
