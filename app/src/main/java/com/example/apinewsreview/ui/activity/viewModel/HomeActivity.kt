package com.example.apinewsreview.ui.activity.viewModel

import android.content.DialogInterface
import android.os.Bundle
import android.view.View
import android.widget.ProgressBar
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.RecyclerView
import com.example.apinewsreview.R
import com.example.apinewsreview.adapter.NewsAdapter
import com.example.apinewsreview.pojo.SourcesItem
import com.example.apinewsreview.ui.base.BaseActivity
import com.google.android.material.tabs.TabLayout

class HomeActivity : BaseActivity(),TabLayout.OnTabSelectedListener {
    lateinit var viewModel: HomeViewModel
    lateinit var progress: ProgressBar
    lateinit var tabLayout: TabLayout
    lateinit var newsAdapter: NewsAdapter
    lateinit var recyclerView: RecyclerView
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_home)
        viewModel = ViewModelProvider(this).get(HomeViewModel::class.java)
        viewModel.getSources()
        setupViews()
        observableLiveData()
    }

    private fun setupViews() {
        progress = findViewById(R.id.progress)
        tabLayout = findViewById(R.id.tab_layout)
        recyclerView = findViewById(R.id.recycler_view)
        newsAdapter = NewsAdapter(null)
        recyclerView.adapter = newsAdapter

    }



    private fun observableLiveData() {
        viewModel.messageLiveData.observe(this, Observer {message->
            showDialoge(message = message,posActionName = getString(R.string.retry),
            posAction = DialogInterface.OnClickListener { dialog, which ->
                dialog.dismiss()
            })
        })

        viewModel.progressLiveData.observe(this, Observer {show->
            if (show)
                progress.visibility = View.VISIBLE
            else
                progress.visibility = View.GONE

        })
        viewModel.sourcesLiveData.observe(this, Observer { sourcesList->
            showSourcesInTabLayout(sourcesList)
        })
        viewModel.newLiveData.observe(this, Observer { newsList->
            newsAdapter.changeData(newsList)
        })
    }

    private fun showSourcesInTabLayout(sources: List<SourcesItem?>?) {
        sources?.forEach { item->
            val tab = tabLayout.newTab()
            tab.text = item?.name
            tab.tag = item
            tabLayout.addTab(tab)
        }
        tabLayout.addOnTabSelectedListener(this)
        tabLayout.getTabAt(0)?.select()
    }

    override fun onTabReselected(tab: TabLayout.Tab?) {
        val item = tab?.tag as SourcesItem
        viewModel.getNews(item.id?:"")
    }

    override fun onTabUnselected(tab: TabLayout.Tab?) {

    }

    override fun onTabSelected(tab: TabLayout.Tab?) {
        val item = tab?.tag as SourcesItem
        viewModel.getNews(item.id?:"")
    }
}