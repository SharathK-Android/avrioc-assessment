package com.avrioc.assessment.ui.main.view

import android.content.Intent
import android.net.Uri
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.databinding.DataBindingUtil
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import com.avrioc.assessment.R
import com.avrioc.assessment.databinding.MainFragmentBinding
import com.avrioc.assessment.ui.main.viewmodel.ArticlesViewModel
import com.avrioc.assessment.ui.main.utils.DataResult
import dagger.android.support.DaggerFragment
import javax.inject.Inject


class ArticlesListFragment : DaggerFragment() {

    @Inject
    lateinit var viewModelFactory: ViewModelProvider.Factory

    companion object {
        fun newInstance() = ArticlesListFragment()
    }

    lateinit var viewModel: ArticlesViewModel
    lateinit var binding: MainFragmentBinding

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding = DataBindingUtil.inflate(
            inflater, R.layout.main_fragment, container, false
        )
        return binding.root
    }


    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        viewModel = ViewModelProvider(this, viewModelFactory).get(ArticlesViewModel::class.java)
        binding.viewModel = viewModel
        initObservers()
    }

    private fun initObservers() {
        viewModel.liveData.observe(viewLifecycleOwner,  {
            when(it) {
                is DataResult.Success -> {
                    binding.articlesListView.adapter = ArticlesAdapter(it.data, getArticlesItemClickListener())
                }
                is DataResult.Error -> {
                    displayErrorMessage()
                }
                is DataResult.Loading -> {
                    viewModel.handleProgressBar(it.isLoading)
                }
            }
        })
    }

    private fun displayErrorMessage() = Toast.makeText(requireContext(), getString(R.string.something_went_wrong), Toast.LENGTH_LONG).show()


    private fun getArticlesItemClickListener(): ArticlesAdapter.OnItemClickListener {
        return object: ArticlesAdapter.OnItemClickListener {
            override fun onItemClick(externalLink: String?) {
                val myIntent = Intent(Intent.ACTION_VIEW, Uri.parse(externalLink))
                startActivity(myIntent)
            }
        }
    }

}