package com.srebrov.carshare

import android.os.Bundle
import android.text.TextUtils
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import com.srebrov.carshare.databinding.FragmentLoginBinding

class LoginFragment : Fragment() {

    private var _binding: FragmentLoginBinding? = null
//    private lateinit var auth: FirebaseAuth
    private val binding get() = _binding!!
    private lateinit var app: MyApplication

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {

        app = (activity?.application as MyApplication) // Access app object in Fragment
        _binding = FragmentLoginBinding.inflate(inflater, container, false)
//        auth = Firebase.auth
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        (activity as MainActivity).supportActionBar?.title = "Login"

//        binding.btnLogin.setOnClickListener{
//
//            if (validateData(view)){
//                val email: String = binding.etEmail.text.toString()
//                val password: String = binding.etPassword.text.toString()
//                auth.signInWithEmailAndPassword(email, password)
//                    .addOnCompleteListener { task ->
//                        if(task.isSuccessful) {
//                            Toast.makeText(
//                                this@LoginFragment.context, "User logged in.",
//                                Toast.LENGTH_SHORT
//                            ).show()
//                            (activity as MainActivity).updateNavDrawer()
//                            parentFragmentManager.beginTransaction()
//                                .replace(R.id.nav_host_fragment_content_main, StationsFragment())
//                                .setCustomAnimations(
//                                    R.anim.slide_in_right,
//                                    R.anim.slide_out_left,
//                                    R.anim.slide_in_left,
//                                    R.anim.slide_out_right
//                                )
//                                .commit()
//                        }else {
//                            Toast.makeText(
//                                this@LoginFragment.context, "Authorization failed.",
//                                Toast.LENGTH_SHORT
//                            ).show()
//                            binding.etEmail.text.clear()
//                            binding.etPassword.text.clear()
//                        }
//                    }
//            }
//        }

        binding.tvRegister.setOnClickListener { view ->
            parentFragmentManager.beginTransaction()
                .replace(R.id.nav_host_fragment_content_main, RegisterFragment())
                .setCustomAnimations(
                    R.anim.slide_in_right,
                    R.anim.slide_out_left,
                    R.anim.slide_in_left,
                    R.anim.slide_out_right
                )
                .addToBackStack(null)
                .commit()
        }


    }

    fun validateData(view: View): Boolean{
        var validate = true
        if(TextUtils.isEmpty(binding.etEmail.text.toString())){
            binding.etEmail.error = "Required"
            validate = false
        }else{
            binding.etEmail.error = null
        }
        if (TextUtils.isEmpty(binding.etPassword.text.toString())){
            binding.etPassword.error = "Required"
            validate = false
        }else{
            binding.etPassword.error = null
        }
        return validate
    }

    override fun onDestroyView() {

        super.onDestroyView()
        _binding = null
    }
}