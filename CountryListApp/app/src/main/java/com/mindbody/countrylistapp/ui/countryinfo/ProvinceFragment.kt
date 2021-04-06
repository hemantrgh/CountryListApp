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
import com.mindbody.countrylistapp.databinding.ProvinceFragmentBinding
import com.mindbody.countrylistapp.model.Country
import com.mindbody.countrylistapp.model.Province
import com.mindbody.countrylistapp.ui.countryinfo.adapters.CountryAdapter
import com.mindbody.countrylistapp.ui.countryinfo.adapters.ProvinceAdapter
import com.mindbody.countrylistapp.utils.LogMsgs
import kotlinx.android.synthetic.main.country_list_fragment.*
import kotlinx.android.synthetic.main.province_fragment.*

class ProvinceFragment : Fragment() {

    private val TAG : String? = ProvinceFragment::class.java.simpleName

    private lateinit var binding: ProvinceFragmentBinding

    private lateinit var viewModel: CountryInfoViewModel

    private lateinit var provinceAdapter: ProvinceAdapter

    private lateinit var country: Country
    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?,
                              savedInstanceState: Bundle?): View {
        binding = ProvinceFragmentBinding.inflate(inflater, container, false)
        binding.lifecycleOwner = viewLifecycleOwner
        binding.viewModel = viewModel
        return binding.root

    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        // Create and initialise view model

        val params = ProvinceFragmentArgs.fromBundle(requireArguments())

        country = params.countryModel

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
            viewModel.getProvinceList(country.ID.toString())?.observe(viewLifecycleOwner, Observer<NetworkResponse<List<Province>>> { response ->
                hideProgress()
                if(response != null) {
                    when(response.status) {
                        NetworkResponse.Status.SUCCESS -> {
                            if (response.data!!.isNotEmpty()) {
                                Log.e(TAG, LogMsgs.PROVINCE_FOUND)
                                provinceAdapter = ProvinceAdapter(response.data!!)
                                province_list_view.adapter = provinceAdapter
                            } else {
                                showMessage(LogMsgs.NO_PROVINCE)
                            }
                        }

                        NetworkResponse.Status.ERROR -> {
                            Log.e(TAG, LogMsgs.NO_PROVINCE)
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
}