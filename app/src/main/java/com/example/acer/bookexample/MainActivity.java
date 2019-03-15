package com.example.acer.bookexample;

import android.os.AsyncTask;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.ProgressBar;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.IOException;
import java.io.InputStream;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.ArrayList;
import java.util.Scanner;


public class MainActivity extends AppCompatActivity {
    RecyclerView r;
    ProgressBar p;
    String bookurl="https://www.googleapis.com/books/v1/volumes?q=";
    ArrayList<BookData> books=new ArrayList<>();
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        r=findViewById(R.id.recycler);
        p=findViewById(R.id.progress);
        books=new ArrayList<>();
        new MyTask().execute(bookurl+"flowers");
        r.setLayoutManager(new LinearLayoutManager(this));
        r.setAdapter(new MyAdapter(this,books));
    }
    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.menu,menu);
        return super.onCreateOptionsMenu(menu);
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch(item.getItemId()){
            case R.id.flowers:
                new MyTask().execute(bookurl+"flowers");
                break;
            case R.id.animals:
                new MyTask().execute(bookurl+"animals");
                break;
            case R.id.novels:
                new MyTask().execute(bookurl+"novels");
                break;
        }
        return super.onOptionsItemSelected(item);
    }
    public class MyTask extends AsyncTask<String,Void,String>{
        @Override
        protected void onPreExecute() {
            super.onPreExecute();
            p.setVisibility(View.VISIBLE);
        }

        @Override
        protected String doInBackground(String... strings) {
            try {
                URL url=new URL(strings[0]);
                Log.i("bookurl",url.toString());
                HttpURLConnection urlConnection= (HttpURLConnection) url.openConnection();
                urlConnection.connect();
                InputStream inputStream=urlConnection.getInputStream();
                Scanner scanner=new Scanner(inputStream);
                scanner.useDelimiter("\\A");
                if (scanner.hasNext()){
                    return scanner.next();
                }
                else {
                    return null;
                }

            } catch (IOException e) {
                e.printStackTrace();
            }
            return null;
        }

        @Override
        protected void onPostExecute(String s) {
            super.onPostExecute(s);
            p.setVisibility(View.GONE);
            if(s!=null) {
                try {
                    JSONObject jsonObject = new JSONObject(s);
                    Log.i("bookurl", String.valueOf(jsonObject));
                    JSONArray jsonArray = jsonObject.getJSONArray("items");
                    Log.i("bookurl", String.valueOf(jsonArray));

                    for (int i = 0; i < jsonArray.length(); i++) {
                        JSONObject volume = jsonArray.getJSONObject(i);
                        JSONObject volumeinfo = volume.getJSONObject("volumeInfo");
                        String bookTitle = volumeinfo.getString("title");
                        Log.i("bookurl",bookTitle);
                        JSONArray authersarray=volumeinfo.getJSONArray("authors");
                        Log.i("bookurl", String.valueOf(authersarray));
                        String bookauthor = authersarray.getString(0);
                        Log.i("bookurl",bookauthor);
                        JSONObject imagelinks = volumeinfo.getJSONObject("imageLinks");
                        String bookimage = imagelinks.getString("thumbnail");
                        Log.i("bookurl",bookimage);
                        String bookdesc = volumeinfo.getString("description");

                        books.add(new BookData(bookTitle,bookauthor,bookdesc,bookimage));
                    }

                } catch (JSONException e) {
                    e.printStackTrace();
                }
            }

        }
    }

}

