package com.example.flixster

import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView

class OnGoingMoviesAdapter(
    private val movies: List<OnGoingMovie>,
    private val mListener: FragmentInteractionListener?
    )
    : RecyclerView.Adapter<OnGoingMoviesAdapter.ViewHolder>() {
        class ViewHolder(itemView: View): RecyclerView.ViewHolder(itemView) {
            // Add the xml views
            init {
                // put them to id here
            }
        }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        TODO("Not yet implemented")
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        TODO("Not yet implemented")
    }

    override fun getItemCount(): Int {
        return movies.size
    }
}