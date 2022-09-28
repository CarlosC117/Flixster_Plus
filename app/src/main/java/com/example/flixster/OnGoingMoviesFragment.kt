package com.example.flixster

import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.core.widget.ContentLoadingProgressBar
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.codepath.asynchttpclient.AsyncHttpClient
import com.codepath.asynchttpclient.RequestParams
import com.codepath.asynchttpclient.callback.JsonHttpResponseHandler
import com.google.gson.Gson
import com.google.gson.reflect.TypeToken
import okhttp3.Headers
import org.json.JSONObject

private const val API_KEY = "a07e22bc18f5cb106bfe4cc1f83ad8ed"

class OnGoingMoviesFragment : Fragment(), FragmentInteractionListener { // OnGoingMoviesFragment

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val view = inflater.inflate(R.layout.fragment_ongoing_movies_list, container, false)
        val progressBar = view.findViewById<View>(R.id.progress) as ContentLoadingProgressBar
        val recyclerView = view.findViewById<View>(R.id.list) as RecyclerView
        val context = view.context
        recyclerView.layoutManager = LinearLayoutManager(context)
        updateAdapter(progressBar, recyclerView)
        return view
    }

    /*
    * Updates the RecyclerView adapter with new data.  This is where the
    * networking magic happens!
    */
    private fun updateAdapter(progressBar: ContentLoadingProgressBar, recyclerView: RecyclerView) {
        progressBar.show()

        // Create and set up an AsyncHTTPClient() here
        val client = AsyncHttpClient()
        val params = RequestParams()
        params["api-key"] = API_KEY

        client[
                "https://developers.themoviedb.org/3/movies/get-now-playing",
                params,
                object: JsonHttpResponseHandler() {
                    override fun onFailure(
                        statusCode: Int,
                        headers: Headers?,
                        response: String,
                        throwable: Throwable?
                    ) {
                        progressBar.hide()
                        throwable?.message?.let {
                            Log.e("OnGoingMovieFragment", response)
                        }
                    }

                    override fun onSuccess(statusCode: Int, headers: Headers?, json: JSON) {
                        //Log.d("BestSellerBooksFragment", json.toString())

                        val gson = Gson()

                        val resultsJSON : JSONObject = json.jsonObject.get("results") as JSONObject
                        val ongoingMovieJSON : String = resultsJSON.get("movies").toString()

                        val arrayBookType = object : TypeToken<List<OnGoingMovie>>() {}.type
                        val movies : List<OnGoingMovie> = gson.fromJson(ongoingMovieJSON, arrayBookType)

                        recyclerView.adapter = OnGoingMoviesAdapter(movies, this@OnGoingMoviesFragment)

                        progressBar.hide()
                        Log.d("OnGoingMovieFragment", ongoingMovieJSON)
                    }

                }
        ]

    }

    override fun onItemClick(item: OnGoingMovie) {
        //Toast.makeText(context,"Test: " + item.title, Toast.LENGTH_SHORT).show()
    }


}