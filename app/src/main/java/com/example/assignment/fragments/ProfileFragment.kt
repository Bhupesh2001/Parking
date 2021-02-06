package com.example.assignment.fragments

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.TextureView
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import com.example.assignment.R
import com.google.firebase.auth.FirebaseAuth

//private const val ARG_PARAM1 = "param1"
//private const val ARG_PARAM2 = "param2"

class ProfileFragment : Fragment() {
    lateinit var txtUserName:TextView
    lateinit var txtUserPhone:TextView
    lateinit var txtUserEmail:TextView
    lateinit var mAuth: FirebaseAuth

//    private var param1: String? = null
//    private var param2: String? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        arguments?.let {
//            param1 = it.getString(ARG_PARAM1)
//            param2 = it.getString(ARG_PARAM2)
        }
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        val view = inflater.inflate(R.layout.fragment_profile, container, false)

        txtUserName = view.findViewById(R.id.txtUserName)
        txtUserEmail = view.findViewById(R.id.txtUserEmail)
        txtUserPhone = view.findViewById(R.id.txtUserPhone)

        mAuth = FirebaseAuth.getInstance()
        val currentUser = mAuth.currentUser

        txtUserName.text = currentUser?.displayName
        txtUserEmail.text = currentUser?.email
        txtUserPhone.text = currentUser?.uid

        return view
    }

//    companion object {
//        @JvmStatic
//        fun newInstance(param1: String, param2: String) =
//            ProfileFragment().apply {
//                arguments = Bundle().apply {
////                    putString(ARG_PARAM1, param1)
////                    putString(ARG_PARAM2, param2)
//                }
//            }
//    }
}