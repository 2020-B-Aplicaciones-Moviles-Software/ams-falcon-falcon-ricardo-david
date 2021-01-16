package com.example.teamplayers.views

import android.app.AlertDialog
import android.content.DialogInterface
import android.content.Intent
import android.os.Bundle
import android.view.Menu
import android.view.MenuItem
import android.view.View
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.databinding.DataBindingUtil
import androidx.lifecycle.LifecycleOwner
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProviders
import androidx.recyclerview.widget.GridLayoutManager
import androidx.recyclerview.widget.ItemTouchHelper
import androidx.recyclerview.widget.RecyclerView
import com.example.teamplayers.R
import com.example.teamplayers.databinding.ActivityPlayersBinding
import com.example.teamplayers.viewmodel.PlayersViewModel
import com.example.teamplayers.viewmodel.PlayersViewModelFactory
import dagger.android.AndroidInjection
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.Job
import kotlinx.coroutines.launch
import javax.inject.Inject
import kotlin.coroutines.CoroutineContext



class PlayersActivity : AppCompatActivity(), CoroutineScope {

    //Initialize variable before accessing it
    @Inject
    lateinit var mPlayersViewModelFactory: PlayersViewModelFactory
    private var mRecipesAdapter = PlayersAdapter(this, ArrayList())
    private lateinit var mPlayersViewModel: PlayersViewModel
    private lateinit var mDataBinding: ActivityPlayersBinding

    private var job: Job = Job()
    private lateinit var lifecycleOwner: LifecycleOwner

    override val coroutineContext: CoroutineContext
        get() = Dispatchers.Main + job

    override fun onDestroy() {
        super.onDestroy()
        job.cancel()
    }


    override fun onCreate(savedInstanceState: Bundle?) {
        AndroidInjection.inject(this)
        lifecycleOwner = this

        super.onCreate(savedInstanceState)
        mDataBinding = DataBindingUtil.setContentView(this, R.layout.activity_players)

        supportActionBar!!.setDisplayHomeAsUpEnabled(true)
        supportActionBar!!.setDisplayShowHomeEnabled(true)

        mDataBinding.buttonAddRecipes.setOnClickListener {
            val intent = Intent(this, AddPlayersActivity::class.java)
            startActivity(intent)
        }

        initializeRecycler()
        mPlayersViewModel = ViewModelProviders.of(this, mPlayersViewModelFactory).get(
                PlayersViewModel::class.java
        )

        mDataBinding.progressBar.visibility = View.VISIBLE
        loadData()
    }


    private fun initializeRecycler() {
        val gridLayoutManager = GridLayoutManager(this, 1)
        gridLayoutManager.orientation = RecyclerView.VERTICAL
        mDataBinding.recyclerViewRecipes.apply {
            setHasFixedSize(true)
            layoutManager = gridLayoutManager
        }
        val swipeHandler = object : ItemTouchHelper.SimpleCallback(0, ItemTouchHelper.LEFT) {
            override fun onMove(recyclerView: RecyclerView, viewHolder: RecyclerView.ViewHolder, target: RecyclerView.ViewHolder): Boolean {
                return false
            }

            override fun onSwiped(viewHolder: RecyclerView.ViewHolder, direction: Int) {
                val position = viewHolder.adapterPosition
                launch {
                    mPlayersViewModel.deletePlayersData(mRecipesAdapter.recipesList[position])
                    mPlayersViewModel.deleteMappingForPlayersId(mRecipesAdapter.recipesList[position].id!!)
                }
            }
        }
        val itemTouchHelper = ItemTouchHelper(swipeHandler)
        itemTouchHelper.attachToRecyclerView(mDataBinding.recyclerViewRecipes)

        mDataBinding.recyclerViewRecipes.adapter = mRecipesAdapter
    }


    private fun loadData() {
        launch {
            mPlayersViewModel.loadPlayers().observe(lifecycleOwner, Observer {
                mDataBinding.progressBar.visibility = View.INVISIBLE
                mRecipesAdapter.updateRecipes(it)
            })
        }
    }


    override fun onCreateOptionsMenu(menu: Menu?): Boolean {
        menuInflater.inflate(R.menu.menu_players, menu)
        return super.onCreateOptionsMenu(menu)
    }


    override fun onOptionsItemSelected(item: MenuItem): Boolean = when (item.itemId) {
        R.id.action_delete_all -> {
            if (mRecipesAdapter.itemCount > 0)
                showDeleteAllConfirmationDialog()
            else
                Toast.makeText(this, getString(R.string.error_no_recipes_to_delete), Toast.LENGTH_LONG).show()
            true
        }
        else -> {
            // Invoke the superclass to handle it.
            super.onOptionsItemSelected(item)
        }
    }

    private fun showDeleteAllConfirmationDialog() {
        val builder = AlertDialog.Builder(this)

        // Set the alert dialog title
        builder.setTitle(getString(R.string.recipes))

        // Display a message on alert dialog
        builder.setMessage(getString(R.string.message_confirm_delete))

        // Set a positive button and its click listener on alert dialog
        builder.setPositiveButton(getString(R.string.yes)) { _, _ ->
            launch {
                mPlayersViewModel.deleteAllPlayersData()
                mPlayersViewModel.deleteAllMappingData()
            }
        }
        builder.setNegativeButton(getString(com.example.teamplayers.R.string.cancel)) { dialogInterface: DialogInterface, _: Int ->
            dialogInterface.cancel()
        }

        // Finally, make the alert dialog using builder
        val dialog: AlertDialog = builder.create()

        // Display the alert dialog on app interface
        dialog.show()
    }
}
