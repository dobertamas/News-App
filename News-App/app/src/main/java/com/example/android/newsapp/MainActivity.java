package com.example.android.newsapp;

import android.app.LoaderManager;
import android.content.AsyncTaskLoader;
import android.content.Context;
import android.content.Intent;
import android.content.Loader;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.net.Uri;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.text.TextUtils;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ListView;
import android.widget.Toast;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

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
import okhttp3.OkHttpClient;
import okhttp3.logging.HttpLoggingInterceptor;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;
import timber.log.Timber;

public class MainActivity extends AppCompatActivity implements LoaderManager.LoaderCallbacks<News[]> {

    private static final String LOG_TAG = MainActivity.class.getSimpleName();

    private static final String GUARDIAN_API_KEY = "c6762bae-2db4-4235-8650-1f9c37aff2a7";
    private static final String GUARDIAN_API_SEARCH_REQUEST_URL = "https://content.guardianapis.com/search?q=";
    private static final String mSearchTerm = "soccer";
    private static final int EXISTING_PRODUCT_LOADER = 1;
    private static final String UNKNOWN_TITLE = "unknown title";
    private static final String UNKNOWN_URL = "unknown URL";
    private static final String UNKNOWN_SECTION = "unknown section";

    @InjectView(R.id.list) ListView mListView;
    NewsAdapter mNewsAdapter;

    static final Integer READ_TIMEOUT_VALUE = 10000; /* milliseconds */
    static final Integer CONNECT_TIMEOUT_VALUE = 15000; /* milliseconds */
    static final Integer NEWS_LIST_SIZE = 10;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        ButterKnife.inject(this);

        if (BuildConfig.DEBUG) {
            Timber.plant(new Timber.DebugTree() {
                //Adding line number to the tag
                @Override protected String createStackElementTag(StackTraceElement element) {
                    return super.createStackElementTag(element) + ':' + element.getLineNumber();
                }
            });
        }

        if (isOnline()) {
            // Initialize a loader to read the product data from Guardian API
            // and display the current values in the editor
            //   getLoaderManager().initLoader(EXISTING_PRODUCT_LOADER, null, this).forceLoad();
        }
        else {
            Toast.makeText(MainActivity.this, "No connectivity!", Toast.LENGTH_LONG).show();
        }

        HttpLoggingInterceptor interceptor = new HttpLoggingInterceptor();
        interceptor.setLevel(HttpLoggingInterceptor.Level.BODY);
        //OkHttpClient httpClient = new OkHttpClient.Builder().addInterceptor(interceptor).build();

        String API_BASE_URL = "https://content.guardianapis.com/";

        OkHttpClient.Builder httpClient = new OkHttpClient.Builder();

        Retrofit.Builder builder = new Retrofit.Builder()
                .baseUrl(API_BASE_URL)
                .addConverterFactory(GsonConverterFactory.create());

        Retrofit retrofit =
                builder.client(httpClient.build()).build();

        GuardianClient client = retrofit.create(GuardianClient.class);

        Call<Response_> call = client.getNews(mSearchTerm, GUARDIAN_API_KEY);

        call.enqueue(new Callback<Response_>() {
            @Override
            public void onResponse(Call<Response_> call, Response<Response_> response) {
                Timber.i(String.valueOf(response.body().getResults().size()));
                // Log.i(LOG_TAG, " inside onResponse " + response.body().getResults().get(0).getWebUrl());
                Timber.i(" inside onResponse ");
            }

            @Override public void onFailure(Call<Response_> call, Throwable t) {
                Timber.i(" inside onFailure ");
            }
        });

    }

    public boolean isOnline() {
        ConnectivityManager connectivityManager =
                (ConnectivityManager) getSystemService(Context.CONNECTIVITY_SERVICE);
        NetworkInfo networkInfo = connectivityManager.getActiveNetworkInfo();
        return (networkInfo != null && networkInfo.isConnected());
    }

    // Using anonymous inner class to provide AsyncTaskLoader functionality
    @Override public Loader<News[]> onCreateLoader(int i, Bundle bundle) {
        return new AsyncTaskLoader<News[]>(MainActivity.this) {
            @Override public News[] loadInBackground() {
                News[] newsArray = new News[NEWS_LIST_SIZE];

                // Create URL object
                URL url = createUrl(GUARDIAN_API_SEARCH_REQUEST_URL + mSearchTerm + "&api-key=" + GUARDIAN_API_KEY);
                assert url != null;
                Log.i(LOG_TAG, " Url created" + url.getPath());

                String API_BASE_URL = "https://content.guardianapis.com/";
                OkHttpClient.Builder httpClient = new OkHttpClient.Builder();

                Retrofit.Builder builder = new Retrofit.Builder()
                        .baseUrl(API_BASE_URL)
                        .addConverterFactory(GsonConverterFactory.create());

                Retrofit retrofit = builder.client(httpClient.build()).build();

                GuardianClient client = retrofit.create(GuardianClient.class);

               /* Call<List<News>> call = client.getNews(mSearchTerm, GUARDIAN_API_KEY);

                call.enqueue(new Callback<List<News>>() {
                    @Override
                    public void onResponse(Call<List<News>> call, Response<List<News>> response) {
                        Log.i(LOG_TAG, " inside onResponse ");
                        try {
                            extractFeatureFromJson("");
                        } catch (JSONException e) {
                            e.printStackTrace();
                        }
                    }

                    @Override public void onFailure(Call<List<News>> call, Throwable t) {
                        Log.i(LOG_TAG, " inside onFailure ");

                    }
                });
*/

                // Perform HTTP request to the URL and receive a JSON response back
         /*       String jsonResponse = "";
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
                */

                return null;
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
                    Log.e(LOG_TAG, "Problem retrieving JSON results.", e);
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
            private News[] extractFeatureFromJson(String guardianNewsJSON) throws JSONException {
                // If the JSON string is empty or null, then return early.
                if (TextUtils.isEmpty(guardianNewsJSON)) {
                    return null;
                }

                JSONObject baseJsonResponse = new JSONObject(guardianNewsJSON);
                JSONObject responseJSON = baseJsonResponse.getJSONObject("response");
                JSONArray itemsArray = responseJSON.getJSONArray("results");

                News[] newses = new News[itemsArray.length()];

                for (int x = 0; x < itemsArray.length(); x++) {
                    JSONObject newsItem = itemsArray.getJSONObject(x);

                    News newsLoopItem = new News();

                    // error handling when webTitle is not available - setting UNKNOWN_TITLE
                    try {
                        String title = newsItem.getString("webTitle");
                        if (!title.isEmpty()) {
                            newsLoopItem.setTitle(newsItem.getString("webTitle"));
                        }
                    } catch (JSONException e) {
                        e.printStackTrace();
                        newsLoopItem.setTitle(UNKNOWN_TITLE);
                    }

                    // error handling when webUrl is not available - setting UNKNOWN_URL
                    try {
                        String webUrl = newsItem.getString("webUrl");
                        if (!webUrl.isEmpty()) {
                            newsLoopItem.setUrl(newsItem.getString("webUrl"));
                        }
                    } catch (JSONException e) {
                        e.printStackTrace();
                        newsLoopItem.setUrl(UNKNOWN_URL);
                    }

                    // error handling when section information is not available - setting UNKNOWN_SECTION
                    try {
                        String sectionName = newsItem.getString("sectionName");
                        if (!sectionName.isEmpty()) {
                            newsLoopItem.setSection(newsItem.getString("sectionName"));
                        }
                    } catch (JSONException e) {
                        e.printStackTrace();
                        newsLoopItem.setSection(UNKNOWN_SECTION);
                    }

                    newses[x] = newsLoopItem;
                }
                return newses;
            }

        };
    }

    // Setting the adapter here as now we have the data
    @Override public void onLoadFinished(Loader<News[]> loader, News[] newses) {
        Log.i(LOG_TAG, Arrays.toString(newses) + " at onLoadFinished ");

        if (newses.length == 0) {
            Toast.makeText(MainActivity.this, "Empty result set from news API", Toast.LENGTH_LONG).show();
        }
        mNewsAdapter = new NewsAdapter(this, newses);

        mNewsAdapter.notifyDataSetChanged();

        mListView.setAdapter(mNewsAdapter);


        // when user clicks on an item we want to open the browser with that specific URL
        mListView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int position, long l) {
                Intent browserIntent = new Intent(Intent.ACTION_VIEW);

                // creating News object based on position of the item
                News news = mNewsAdapter.getItem(position);
                assert news != null;
                // setting the URL from the News object
                browserIntent.setData(Uri.parse(news.getUrl()));
                startActivity(browserIntent);
            }
        });
    }

    @Override public void onLoaderReset(Loader<News[]> loader) {
        // Callback called when the data needs to be deleted
        mListView.setAdapter(null);
        Log.i(LOG_TAG, " onLoaderReset is swapping adapter to null ");

    }

}
