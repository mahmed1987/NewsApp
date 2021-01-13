package com.naggaro.newsapp

import android.os.Bundle
import androidx.navigation.fragment.findNavController
import com.naggaro.common.newsapp.base.BaseFragment
import kotlinx.android.synthetic.main.guest_fragment.*

class GuestFragment : BaseFragment() {
    override fun layoutResourceId()=R.layout.guest_fragment

    override fun ignite(savedInstanceState: Bundle?) {
        button.setOnClickListener{
            findNavController().navigate(GuestFragmentDirections.toNews())
        }
    }
}