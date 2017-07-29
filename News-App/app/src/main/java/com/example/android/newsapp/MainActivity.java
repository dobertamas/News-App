package com.example.android.newsapp;

import android.app.LoaderManager;
import android.content.AsyncTaskLoader;
import android.content.Loader;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.text.TextUtils;
import android.util.Log;
import android.widget.ListView;

import org.json.JSONException;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;
import java.nio.charset.Charset;
import java.util.Arrays;

import butterknife.ButterKnife;
import butterknife.InjectView;

public class MainActivity extends AppCompatActivity implements LoaderManager.LoaderCallbacks<News[]> {

    private static final String LOG_TAG = MainActivity.class.getSimpleName();
    private static final String GUARDIAN_API_KEY = "c6762bae-2db4-4235-8650-1f9c37aff2a7";
    private static final String GUARDIAN_API_SEARCH_REQUEST_URL = "https://content.guardianapis.com/search?q=";
    private static final String mSearchTerm = "soccer";
    private static final int EXISTING_PRODUCT_LOADER = 1;

    @InjectView(R.id.list) ListView mListView;

    private NewsAdapter mNewsAdapter;
    News[] mNewsList;

    public static final String NEWS_LIST_ARRAY = "NEWS_LIST_ARRAY";
    static final Integer READ_TIMEOUT_VALUE = 10000; /* milliseconds */
    static final Integer CONNECT_TIMEOUT_VALUE = 15000; /* milliseconds */
    static final Integer NEWS_LIST_SIZE = 10;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        ButterKnife.inject(this);

        // Initialize a loader to read the product data from Guardian API
        // and display the current values in the editor
        getLoaderManager().initLoader(EXISTING_PRODUCT_LOADER, null, this).forceLoad();


        // mNewsList = new News[]{new News("title1", "url1"), new News("title2", "url2")};


    }

    @Override public Loader<News[]> onCreateLoader(int i, Bundle bundle) {
        return new AsyncTaskLoader<News[]>(MainActivity.this) {
            @Override public News[] loadInBackground() {

               /* News[] newsArray = new News[1];
                newsArray[0] = new News("title4", "url4");
                return newsArray;*/

                News[] newsArray = new News[NEWS_LIST_SIZE];

                // Create URL object
                // URL url = createUrl(GUARDIAN_API_SEARCH_REQUEST_URL + mSearchTerm + "&key=" + GUARDIAN_API_KEY);
                URL url = createUrl(GUARDIAN_API_SEARCH_REQUEST_URL + mSearchTerm + "&api-key=" + GUARDIAN_API_KEY);
                assert url != null;
                Log.i(LOG_TAG, " Url created" + url.getPath());

                // Perform HTTP request to the URL and receive a JSON response back
                String jsonResponse = "";
                try {
                    jsonResponse = makeHttpRequest(url);
                } catch (IOException e) {
                    Log.e(LOG_TAG, "Problem making the HTTP request.", e);
                }

                try {
                    newsArray = extractFeatureFromJson(jsonResponse);
                    Log.i(LOG_TAG, "extracted");
                } catch (JSONException e) {
                    e.printStackTrace();
                }


                return newsArray;
            }


            /**
             * Returns new URL object from the given string URL.
             */

            private URL createUrl(String urlString) {
                URL url;
                try {
                    url = new URL(urlString);
                } catch (MalformedURLException exception) {
                    Log.e(LOG_TAG, "Error with creating URL" + urlString, exception);
                    return null;
                }
                return url;
            }


            /**
             * Make an HTTP request to the given URL and return a String as the response.
             */
            private String makeHttpRequest(URL url) throws IOException {
                String jsonResponse = "";

                // If the URL is null, then return early.
                if (url == null) {
                    return jsonResponse;
                }

                HttpURLConnection urlConnection = null;
                InputStream inputStream = null;
                try {
                    urlConnection = (HttpURLConnection) url.openConnection();
                    urlConnection.setRequestMethod("GET");
                    urlConnection.setReadTimeout(READ_TIMEOUT_VALUE);
                    urlConnection.setConnectTimeout(CONNECT_TIMEOUT_VALUE);
                    urlConnection.connect();

                    // If the request was successful (response code 200),
                    // then read the input stream and parse the response.
                    if (urlConnection.getResponseCode() == 200) {
                        inputStream = urlConnection.getInputStream();
                        jsonResponse = readFromStream(inputStream);
                    }
                    else {
                        Log.e(LOG_TAG, "Error response code: " + urlConnection.getResponseCode());
                    }
                } catch (IOException e) {
                    Log.e(LOG_TAG, "Problem retrieving the earthquake JSON results.", e);
                } finally {
                    if (urlConnection != null) {
                        urlConnection.disconnect();
                    }
                    if (inputStream != null) {
                        // function must handle java.io.IOException here
                        inputStream.close();
                    }
                }
                return jsonResponse;
            }

            /**
             * Convert the {@link InputStream} into a String which contains the
             * whole JSON response from the server.
             */
            private String readFromStream(InputStream inputStream) throws IOException {
                StringBuilder output = new StringBuilder();
                if (inputStream != null) {
                    InputStreamReader inputStreamReader = new InputStreamReader(inputStream, Charset.forName("UTF-8"));
                    BufferedReader reader = new BufferedReader(inputStreamReader);
                    String line = reader.readLine();
                    while (line != null) {
                        output.append(line);
                        line = reader.readLine();
                    }
                }
                return output.toString();
            }

            /**
             * Return an ArrayList of {@link News} objects by parsing out information
             * about the first earthquake from the input earthquakeJSON string.
             */
            private News[] extractFeatureFromJson(String booksJSON) throws JSONException {
                // If the JSON string is empty or null, then return early.
                if (TextUtils.isEmpty(booksJSON)) {
                    return null;
                }

                //   JSONObject baseJsonResponse = new JSONObject(booksJSON);
                //   JSONArray itemsArray = baseJsonResponse.getJSONArray("items");


                /*for (int x = 0; x < itemsArray.length(); x++) {

                }*/
                return null;
            }

        };
    }


    @Override public void onLoadFinished(Loader<News[]> loader, News[] newses) {

        //mNewsAdapter.notifyDataSetChanged();

        Log.i(LOG_TAG, Arrays.toString(newses) + " onLoadFinished ");

        NewsAdapter mNewsAdapter = new NewsAdapter(this, newses);

        mListView.setAdapter(mNewsAdapter);

        Log.i(LOG_TAG, " setAdapter ");

    }

    @Override public void onLoaderReset(Loader<News[]> loader) {

    }


}
