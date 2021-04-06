package com.mindbody.countrylistapp.ui.countryinfo

import androidx.lifecycle.ViewModelProvider
import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.lifecycle.Observer
import androidx.navigation.fragment.findNavController
import com.exozet.demoapp.network.NetworkResponse
import com.exozet.demoapp.utils.Util
import com.google.android.material.snackbar.Snackbar
import com.mindbody.countrylistapp.App
import com.mindbody.countrylistapp.R
import com.mindbody.countrylistapp.databinding.CountryListFragmentBinding
import com.mindbody.countrylistapp.model.Country
import com.mindbody.countrylistapp.ui.countryinfo.adapters.CountryAdapter
import com.mindbody.countrylistapp.utils.LogMsgs
import kotlinx.android.synthetic.main.country_list_fragment.*

class CountryListFragment : Fragment(), CountryAdapter.CountryClickListener {

    private val TAG : String? = CountryListFragment::class.java.simpleName

    private lateinit var binding: CountryListFragmentBinding

    private lateinit var viewModel: CountryInfoViewModel

    private lateinit var countryAdapter: CountryAdapter

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?,
                              savedInstanceState: Bundle?): View {
        binding = CountryListFragmentBinding.inflate(inflater, container, false)
        binding.lifecycleOwner = viewLifecycleOwner
        binding.viewModel = viewModel

        return binding.root

    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        // Create and initialise view model
        viewModel = ViewModelProvider(this).get(CountryInfoViewModel::class.java)
        App.appComponent.inject(viewModel)
    }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)
        getCountries()
    }
    private fun getCountries() {
        if(Util.isNetworkAvailable()) {
            // observe for changes in live data object
            viewModel.getCountriesList()?.observe(viewLifecycleOwner, Observer<NetworkResponse<List<Country>>> { response ->
                hideProgress()
                if(response != null) {
                    when(response.status) {
                        NetworkResponse.Status.SUCCESS -> {
                            Log.e(TAG, LogMsgs.COUNTRY_FOUND)
                            countryAdapter = CountryAdapter(response.data!!)
                            countryAdapter.setOnItemClickListener(this)
                            country_list_view.adapter = countryAdapter
                        }

                        NetworkResponse.Status.ERROR -> {
                            Log.e(TAG, LogMsgs.NO_COUNTRY)
                            showMessage(response.exception?.exceptionMessage.toString())
                        }
                    }
                } else {
                    showMessage(getString(R.string.error))
                }
            })
            showProgress()
        } else {
            showMessage(getString(R.string.no_internet))
        }
    }

    private fun showMessage(message: String) {
        val view: View = requireActivity().findViewById(android.R.id.content)
        Snackbar.make(view, message, Snackbar.LENGTH_LONG).show()
    }

    private fun showProgress() {
        this.binding.progressBar.visibility = View.VISIBLE
    }

    private fun hideProgress() {
        this.binding.progressBar.visibility = View.GONE
    }

    /**
     * Navigation reference
     */
    private fun navController() = findNavController()
    override fun onCountryClick(country: Country) {
        navController().navigate(
            CountryListFragmentDirections.showDetails(
                country
            )
        )
    }


}