package com.example.flixster

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.example.flixster.R.id

class OnGoingMoviesAdapter(
    private val movies: List<OnGoingMovie>,
    private val mListener: FragmentInteractionListener?
    )
    : RecyclerView.Adapter<OnGoingMoviesAdapter.ViewHolder>() {
        inner class ViewHolder(var mView: View): RecyclerView.ViewHolder(mView) {
            var mItem: OnGoingMovie? = null
            var movieImage: ImageView = mView.findViewById(id.movie_image)
            var movieTitle: TextView = mView.findViewById(id.movie_title)
            var movieDescription: TextView = mView.findViewById(id.movie_description)
        }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val view = LayoutInflater.from(parent.context).inflate(R.layout.fragment_ongoing_movies, parent, false)
        return ViewHolder(view)
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        val movie = movies[position]
        val link = "https://image.tmdb.org/t/p/w500/" + movie.imageMovie


        holder.mItem = movie
        holder.movieTitle.text = movie.titleMovie
        holder.movieDescription.text = movie.descriptionMovie

        Glide.with(holder.mView).load(link).centerInside().into(holder.movieImage)

        holder.mView.setOnClickListener {
            holder.mItem?.let { movie ->
                mListener?.onItemClick(movie)
            }
        }
    }

    override fun getItemCount(): Int {
        return movies.size
    }
}