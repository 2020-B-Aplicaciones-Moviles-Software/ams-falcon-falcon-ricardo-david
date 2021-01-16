package com.example.teamplayers.views

import android.content.Context
import android.content.Intent
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.recyclerview.widget.RecyclerView
import com.example.teamplayers.R
import com.example.teamplayers.databinding.PlayersListItemBinding
//import com.example.teamplayers.databinding.RecipesListItemBinding
import com.example.teamplayers.model.data.PLayingPlayers
import com.example.teamplayers.utils.Constants
import com.example.teamplayers.utils.Utils
import com.squareup.picasso.Picasso
import java.net.MalformedURLException


class PlayersAdapter(
        context: Context,
        recipesArray: List<PLayingPlayers>?
) : RecyclerView.Adapter<PlayersAdapter.RecipesViewHolder>() {


    override fun onBindViewHolder(holder: RecipesViewHolder, position: Int) {
        val cookingRecipes = recipesList[position]

        holder.apply {
            bind(cookingRecipes)
            itemView.tag = cookingRecipes
        }

        holder.itemView.setOnClickListener {
            val intent = Intent(context, PlayersDetailsActivity::class.java)
            intent.putExtra(Constants.RECIPES_ID_INTENT_KEY, cookingRecipes.id)
            context.startActivity(intent)
        }
    }


    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): RecipesViewHolder {
        val itemView: PlayersListItemBinding = DataBindingUtil.inflate(
                LayoutInflater.from(parent.context),
                R.layout.players_list_item, parent, false)
        return RecipesViewHolder(itemView)
    }

    var recipesList = ArrayList<PLayingPlayers>()
    private var context: Context

    init {
        this.recipesList = recipesArray as ArrayList<PLayingPlayers>
        this.context = context
    }

   
    override fun getItemCount(): Int {
        return recipesList.size
    }


    fun updateRecipes(recipesData: List<PLayingPlayers>) {
        recipesList = ArrayList(recipesData)
        notifyDataSetChanged()
    }

    class RecipesViewHolder(private val binding: PlayersListItemBinding) : RecyclerView.ViewHolder(binding.root) {

        fun bind(PLayingPlayers: PLayingPlayers) {
            binding.apply {
                recipes = PLayingPlayers
                executePendingBindings()
                val url = Utils.getYoutubeThumbnailUrlFromVideoUrl(PLayingPlayers.playerLink!!)
                try {
                    Picasso.get()
                            .load(url)
                            .centerCrop().resize(200, 200)
                            .placeholder(R.drawable.ic_kitchen_black_24dp)
                            .error(R.drawable.ic_kitchen_black_24dp)
                            .into(binding.imageViewThumbnail)

                } catch (e: MalformedURLException) {
                    e.printStackTrace()
                }
            }
        }
    }
}
