package com.example.teamplayers.views

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.lifecycle.Observer
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.teamplayers.R
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.Job
import kotlinx.coroutines.launch
import kotlin.coroutines.CoroutineContext


class PlayerssFragment : Fragment(), CoroutineScope {

    private var mPage: Int = 0
    private var mCategoryId: Int = 0
    private lateinit var mPlayersAdapter: PlayersAdapter

    companion object {
        private const val ARG_PAGE = "ARG_PAGE"
        private const val CATEGORY_ID = "CATEGORY_ID"

        fun newInstance(page: Int, categoryId: Int): PlayerssFragment {
            val args = Bundle()
            args.putInt(ARG_PAGE, page)
            args.putInt(CATEGORY_ID, categoryId)
            val fragment = PlayerssFragment()
            fragment.arguments = args
            return fragment
        }
    }

    private var job: Job = Job()

    override val coroutineContext: CoroutineContext
        get() = Dispatchers.Main + job

    override fun onDestroy() {
        super.onDestroy()
        job.cancel()
    }

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        val v = inflater.inflate(R.layout.layouts_team_containers, container, false)
        val recyclerView = v!!.findViewById(R.id.recycler_view_category_recipes) as RecyclerView
        recyclerView.setHasFixedSize(true)
        recyclerView.layoutManager = LinearLayoutManager(v.context)

        mPlayersAdapter = PlayersAdapter(v.context, ArrayList())
        recyclerView.adapter = mPlayersAdapter
        loadPlayers()
        return v
    }

    private fun loadPlayers() {
        launch {
            val value: HomeActivity = activity as HomeActivity

            value.mTeamViewModel.getPlayersTeamMappingForTeamId(mCategoryId)
                    .observe(value, Observer { mappingList ->
                        value.mTeamViewModel.getPlayersWithListOfPlayersIds(mappingList.map { it.playerId })
                                .observe(value, Observer {
                                    mPlayersAdapter.updateRecipes(it)
                                })
                    })
        }
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        mPage = arguments!!.getInt(ARG_PAGE)
        mCategoryId = arguments!!.getInt(CATEGORY_ID)
    }
}
