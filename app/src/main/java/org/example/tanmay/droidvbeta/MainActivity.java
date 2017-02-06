package org.example.tanmay.droidvbeta;

import android.content.Intent;
import android.os.AsyncTask;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ListAdapter;
import android.widget.ListView;
import android.widget.Toast;

import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.ArrayList;

public class MainActivity extends AppCompatActivity {
    private String mFileContents;
    private ListView listView;

    // private TextView tv;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        // tv = (TextView)findViewById(R.id.textView);
        listView = (ListView) findViewById(R.id.listView);
        DownloadData downloadData = new DownloadData();
        downloadData.execute("http://droidviews.com/feed");
    }

    public void onClick(View v) {

        ParseData parseData = new ParseData(mFileContents);
        parseData.process();
        final ArrayList<PostsData> posts;
        posts = parseData.getPosts();

        ListAdapter listAdapter = new CustomAdapter(this, posts);
        listView.setAdapter(listAdapter);


        listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {

                String l = posts.get(position).getLink();
                //String food = String.valueOf(parent.getItemAtPosition(position));
                Intent i = new Intent(MainActivity.this, ListViewIntent.class);
                i.putExtra("link", l);
                startActivity(i);

                Toast.makeText(MainActivity.this, "You want to eat " + l + "!!", Toast.LENGTH_LONG).show();
            }

        });
    }

/////////Class for downloading XML feed/////////////

    private class DownloadData extends AsyncTask<String, Void, String> {
        @Override
        protected String doInBackground(String... params) {
            mFileContents = downLoadXMLFile(params[0]);
            if (mFileContents == null) {
                Log.d("DownloadData", "Error downloading");
            }

            return mFileContents;
        }

        @Override
        protected void onPostExecute(String s) {
            super.onPostExecute(s);
            Log.d("DownloadData", "Result was " + s);
            // tv.setText(s);
        }

        private String downLoadXMLFile(String urlPath) {
            StringBuilder tempBuffer = new StringBuilder();
            try {
                URL url = new URL(urlPath);
                HttpURLConnection connection = (HttpURLConnection) url.openConnection();
                int response = connection.getResponseCode();
                Log.d("DownloadData", "The response code is " + response);
                InputStream is = connection.getInputStream();
                InputStreamReader isr = new InputStreamReader(is);

                int charRead;
                char[] inputBuffer = new char[500];
                while (true) {
                    charRead = isr.read(inputBuffer);
                    if (charRead <= 0) {
                        break;
                    }
                    tempBuffer.append(String.copyValueOf(inputBuffer, 0, charRead));
                }
                return tempBuffer.toString();

            } catch (IOException e) {
                Log.d("DownloadData", "IO exception reading data: " + e.getMessage());
            } catch (SecurityException e) {
                Log.d("DownloadData", "Security expection. Need permissions? " + e.getMessage());
            }

            return null;
        }
    }
}
