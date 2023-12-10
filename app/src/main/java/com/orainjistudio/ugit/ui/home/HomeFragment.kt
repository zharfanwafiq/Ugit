package com.orainjistudio.ugit.ui.home

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.recyclerview.widget.LinearLayoutManager
import com.orainjistudio.ugit.R
import com.orainjistudio.ugit.adapter.GithubUserAdapter
import com.orainjistudio.ugit.data.repository.Result
import com.orainjistudio.ugit.data.viewmodel.MainViewModel
import com.orainjistudio.ugit.databinding.FragmentHomeBinding
import com.orainjistudio.ugit.factory.MainViewModelFactory
import com.orainjistudio.ugit.utils.Common
import com.orainjistudio.ugit.utils.Common.isNetworkEnabled

class HomeFragment : Fragment() {

    private var _binding: FragmentHomeBinding? = null
    private val binding get() = _binding!!
    private var githubUserAdapter: GithubUserAdapter? = null

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = FragmentHomeBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        checkConnectionEnabled()
        setSwipeRefresh()
    }

    private fun setSwipeRefresh() {
        binding.swipeRefreshMain.apply {
            setColorSchemeColors(R.color.black)
            setOnRefreshListener { checkConnectionEnabled() }
        }
    }

    private fun checkConnectionEnabled() {
        if (isNetworkEnabled(requireContext())) {
            setRecycleView()
            initObserve()
        } else {
            Common.showToast(requireContext(), "Terjadi Kesalahan ")

        }
    }

    private fun initObserve() {
        val factory: MainViewModelFactory = MainViewModelFactory.getInstance(requireContext())
        val viewModel: MainViewModel by viewModels {
            factory
        }

        viewModel.getGithubUser.observe(viewLifecycleOwner) {
            if (it != null) {
                when (it) {
                    is Result.Loading -> showLoading(true)
                    is Result.Success -> {
                        showLoading(false)
                        githubUserAdapter?.submitList(it.data)
                    }

                    is Result.Error -> {
                        showLoading(false)
                        Common.showToast(requireContext(), "Terjadi Kesalahan ${it.error}")

                    }
                }
            }
        }
    }

    private fun setRecycleView() {
        githubUserAdapter = GithubUserAdapter()
        binding.apply {
            with(rvGithubUser) {
                layoutManager = LinearLayoutManager(requireContext())
                adapter = githubUserAdapter
            }
        }
    }

    private fun showLoading(isShow: Boolean) = with(binding) {
        if (isShow) {
            rvGithubUser.visibility = View.GONE
            swipeRefreshMain.isRefreshing = true
        } else {
            rvGithubUser.visibility = View.VISIBLE
            swipeRefreshMain.isRefreshing = false
        }
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}