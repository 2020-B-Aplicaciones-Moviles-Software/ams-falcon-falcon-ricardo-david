package com.example.teamplayers.views

import android.app.AlertDialog
import android.content.Intent
import android.os.Bundle
import android.view.View
import android.widget.AdapterView
import android.widget.ArrayAdapter
import androidx.appcompat.app.AppCompatActivity
import androidx.databinding.DataBindingUtil
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProviders
import com.example.teamplayers.R
import com.example.teamplayers.databinding.ActivityPlayersAddBinding
import com.example.teamplayers.model.data.Team
import com.example.teamplayers.model.data.PLayingPlayers
import com.example.teamplayers.model.data.PlayersTeamMapping
import com.example.teamplayers.utils.Constants
import com.example.teamplayers.viewmodel.PlayersViewModel
import com.example.teamplayers.viewmodel.PlayersViewModelFactory
import dagger.android.AndroidInjection
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.Job
import kotlinx.coroutines.launch
import timber.log.Timber
import javax.inject.Inject
import kotlin.coroutines.CoroutineContext



class AddPlayersActivity : AppCompatActivity(), CoroutineScope {

    private lateinit var team: Team
    //Init view model
    private lateinit var mPlayersAddViewModel: PlayersViewModel

    //Inject view model factory
    @Inject
    lateinit var mAddPlayersViewModelFactory: PlayersViewModelFactory

    private lateinit var dataBinding: ActivityPlayersAddBinding

    private var job: Job = Job()

    override val coroutineContext: CoroutineContext
        get() = Dispatchers.Main + job

    override fun onDestroy() {
        super.onDestroy()
        job.cancel()
    }


    override fun onCreate(savedInstanceState: Bundle?) {
        AndroidInjection.inject(this)

        super.onCreate(savedInstanceState)
        dataBinding = DataBindingUtil.setContentView(this, R.layout.activity_players_add)

        supportActionBar!!.setDisplayHomeAsUpEnabled(true)
        supportActionBar!!.setDisplayShowHomeEnabled(true)

        val extras = intent.extras
        if (extras != null) {
            dataBinding.textViewRecipesLink.setText(extras.getString(Intent.EXTRA_TEXT))
            dataBinding.textViewRecipeName.setText(extras.getString(Intent.EXTRA_SUBJECT))
        }

        mPlayersAddViewModel = ViewModelProviders.of(this, this.mAddPlayersViewModelFactory).get(
                PlayersViewModel::class.java
        )

        dataBinding.buttonAddRecipes.setOnClickListener {

            val isValidInput = isValidInput()
            if (isValidInput) {
                val cookingRecipes = PLayingPlayers(
                        playerName = dataBinding.textViewRecipeName.text.toString(),
                        playerLink = dataBinding.textViewRecipesLink.text.toString(),
                        playerDescription = dataBinding.textViewRecipesDescription.text.toString()
                )

                launch {
                    val rowId = mPlayersAddViewModel.addPlayersDetails(cookingRecipes)
                    if (rowId > 0) {
                        //Insert recipes category table
                        val id = mPlayersAddViewModel.insertPlayersTeamMapping(PlayersTeamMapping(rowId.toInt(), team.id!!))
                        Timber.d("Id = $id")
                        showPlayersAddedMessage()
                    } else {
                        Timber.d("Row inserted failed id : $it")
                    }
                }
            }
        }

        populateTeamList(intent.getIntExtra(Constants.CURRENT_CATEGORY_POS, 0))
    }

    private fun populateTeamList(categoryPosition: Int) {

        launch {
            mPlayersAddViewModel.getTeamList().observe(this@AddPlayersActivity, Observer {
                //Category list can not be empty as default category added.
                if (!it.isEmpty()) {
                    val adapter = ArrayAdapter(this@AddPlayersActivity, android.R.layout.simple_spinner_item, it)
                    adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item)
                    dataBinding.spinnerRecipesCategory.adapter = adapter

                    //Set the selected category if add is called from category page
                    team = it[categoryPosition]
                    dataBinding.spinnerRecipesCategory.setSelection(categoryPosition)

                    dataBinding.spinnerRecipesCategory.onItemSelectedListener = object : AdapterView.OnItemSelectedListener {
                        override fun onItemSelected(parent: AdapterView<*>, view: View, position: Int, id: Long) {
                            team = it[position]
                        }

                        override fun onNothingSelected(parent: AdapterView<*>) {
                        }
                    }
                }
            })
        }
    }



    private fun showPlayersAddedMessage() {
        val builder = AlertDialog.Builder(this)

        // Set the alert dialog title
        builder.setTitle(getString(R.string.recipes))

        // Display a message on alert dialog
        builder.setMessage(getString(R.string.add_recipes_success_message))

        // Set a positive button and its click listener on alert dialog
        builder.setPositiveButton(getString(R.string.done)) { _, _ ->
            finish()
        }

        // Finally, make the alert dialog using builder
        val dialog: AlertDialog = builder.create()

        // Display the alert dialog on app interface
        dialog.show()
    }

    /**
     * VALIDAR CAMPOS
     */
    private fun isValidInput(): Boolean {
        var isValid = true
        if (dataBinding.textViewRecipeName.text.toString().isEmpty()) {
            dataBinding.textViewRecipeName.error = getString(R.string.fld_error_recipe_name)
            isValid = false
        } else {
            dataBinding.textViewRecipeName.error = null
        }

        if (dataBinding.textViewRecipesLink.toString().isEmpty()) {
            dataBinding.textViewRecipesLink.error = getString(R.string.fld_error_recipes_link)
            isValid = false
        } else {
            dataBinding.textViewRecipesLink.error = null
        }

        return isValid
    }
}
