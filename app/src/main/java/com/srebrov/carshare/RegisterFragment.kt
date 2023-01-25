package com.srebrov.carshare

import android.os.Bundle
import android.text.TextUtils
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import com.google.android.material.snackbar.Snackbar
import com.srebrov.carshare.databinding.FragmentRegisterBinding


class RegisterFragment : Fragment() {
    private var _binding: FragmentRegisterBinding? = null
    private lateinit var app: MyApplication
    private val binding get() = _binding!!
    private var edit = false
//    private lateinit var auth: FirebaseAuth
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        (activity as MainActivity).supportActionBar?.title = "Register"
        _binding = FragmentRegisterBinding.inflate(inflater, container, false)
        app = (activity?.application as MyApplication) // Access app object in Fragment
//        auth = Firebase.auth
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
//        binding.btnRegister.setOnClickListener {
//
//            if (validateData()) {
//                val email = binding.etEmail.text.toString()
//                val password = binding.etPassword.text.toString()
//                val taxNumber = binding.etTaxNumber.text.toString()
//                val personalNumber = binding.etPersonalNumber.text.toString()
//                val user = User(email, password, taxNumber, personalNumber)
//                app.database.addUser(user)
//
//                FirebaseAuth.getInstance().createUserWithEmailAndPassword(email, password)
//                    .addOnCompleteListener { task ->
//                        if (task.isSuccessful) {
//                            Toast.makeText(
//                                this@RegisterFragment.context, "Registration successful.",
//                                Toast.LENGTH_SHORT
//                            ).show()
//                            parentFragmentManager.beginTransaction()
//                                .setCustomAnimations(
//                                    R.anim.slide_in_right,
//                                    R.anim.slide_out_left,
//                                    R.anim.slide_in_left,
//                                    R.anim.slide_out_right
//                                )
//                                .replace(R.id.nav_host_fragment_content_main, LoginFragment())
//                                .commit()
//                        } else {
//                            Toast.makeText(
//                                this@RegisterFragment.context, "Registration Failed.",
//                                Toast.LENGTH_SHORT
//                            ).show()
//                        }
//                    }
//            }else{
//                Snackbar.make(view,"Validation failed!", Snackbar.LENGTH_LONG).show()
//            }
//        }
    }

    fun validateData(): Boolean {
        var validated = true

        if (TextUtils.isEmpty(binding.etEmail.text.toString())) {
            binding.etEmail.error = "Required."
            validated = false
        }else{
            binding.etEmail.error = null
        }
        if (TextUtils.isEmpty(binding.etPassword.text.toString())) {
            binding.etPassword.error = "Required."
            validated = false
        } else {

            if (binding.etPassword.text.toString().length < 6) {
                binding.etPassword.error = "Password too short."
                validated = false
            }
        }
        if (TextUtils.isEmpty(binding.etPasswordConfirmation.text.toString())) {
            binding.etPasswordConfirmation.error = "Confirm password."
            validated = false
        }

        if (binding.etPassword.text.toString() != binding.etPasswordConfirmation.text.toString()) {
            binding.etPassword.error = "Mismatched passwords."
            validated = false
        }else{
            binding.etPassword.error = null
        }

        if(TextUtils.isEmpty(binding.etTaxNumber.text.toString())){
            binding.etTaxNumber.error = "Required."
            validated = false
        }else{

            if(binding.etTaxNumber.text.toString().length != 8){
                binding.etTaxNumber.error = "Wrong format."
                validated = false
            }
        }

        if(TextUtils.isEmpty(binding.etPersonalNumber.text.toString())){
            binding.etPersonalNumber.error = "Required."
            validated = false
        }else{

            if(binding.etPersonalNumber.text.toString().length != 13){
                binding.etPersonalNumber.error = "Wrong format."
                validated = false
            }
        }

        return validated
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}