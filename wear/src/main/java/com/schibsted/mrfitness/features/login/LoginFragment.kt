package com.schibsted.mrfitness.features.login

import android.app.Activity.RESULT_OK
import android.content.Intent
import android.os.Bundle
import android.view.View
import androidx.navigation.fragment.findNavController
import com.google.android.gms.auth.api.Auth
import com.google.android.gms.auth.api.signin.GoogleSignInOptions
import com.google.android.gms.common.ConnectionResult
import com.google.android.gms.common.api.GoogleApiClient
import com.schibsted.mrfitness.R
import com.schibsted.mrfitness.common.base.BaseFragment
import com.schibsted.mrfitness.common.extension.toast
import com.schibsted.mrfitness.common.utils.LayoutResId
import com.schibsted.mrfitness.databinding.FragmentLoginBinding
import org.koin.androidx.viewmodel.ext.android.viewModel

@LayoutResId(R.layout.fragment_login)
class LoginFragment : BaseFragment<LoginViewModel, FragmentLoginBinding>(),
    GoogleApiClient.OnConnectionFailedListener,
    GoogleApiClient.ConnectionCallbacks{

    companion object {
        const val RC_SIGN_IN = 100
    }
    private lateinit var mGoogleApiClient: GoogleApiClient
    override val viewModel: LoginViewModel by viewModel()

    override fun bindViewModel() {
        binding.viewModel = viewModel
    }

    override fun start(view: View?) {
        setupGoogleSignIn()
        binding.signInButton.setOnClickListener { startLogin() }
    }

    private fun startLogin() {
        Auth.GoogleSignInApi.getSignInIntent(mGoogleApiClient).also { signInIntent ->
            startActivityForResult(signInIntent, RC_SIGN_IN)
        }
    }

    override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {
        super.onActivityResult(requestCode, resultCode, data)

        // Result returned from launching the Intent from GoogleSignInApi.getSignInIntent(...)
        if (requestCode == RC_SIGN_IN) {
            Auth.GoogleSignInApi.getSignInResultFromIntent(data)?.apply {
                if (isSuccess) {
                    // Get account information
                    val mFullName = signInAccount?.displayName
                    val mGivenName = signInAccount?.givenName
                    val mFamilyName = signInAccount?.familyName
                    val mEmail = signInAccount?.email
                }
            }
        }
    }

    private fun setupGoogleSignIn() {
        context?.let { context ->
            mGoogleApiClient = GoogleSignInOptions.Builder(GoogleSignInOptions.DEFAULT_SIGN_IN)
                .requestEmail()
                .build()
                .let { signInConfigBuilder ->
                    // Build a GoogleApiClient with access to the Google Sign-In API and the
                    // options specified in the sign-in configuration.
                    GoogleApiClient.Builder(context)
                        .enableAutoManage(activity!! /* FragmentActivity */, this /* OnConnectionFailedListener */)
                        .addApi(Auth.GOOGLE_SIGN_IN_API, signInConfigBuilder)
                        .build()
                }
        }
    }

    override fun onConnectionFailed(result: ConnectionResult) {
        toast("Connection failed: $result")
    }

    override fun onConnected(bundle: Bundle?) {
        toast("Connected: $bundle")
    }

    override fun onConnectionSuspended(code: Int) {
        toast("Connection suspended: $code")
    }
}