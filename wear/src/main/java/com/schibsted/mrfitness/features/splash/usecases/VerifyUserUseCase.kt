package com.schibsted.mrfitness.features.splash.usecases

import com.google.android.gms.auth.api.Auth
import io.reactivex.Single

class VerifyUserUseCase {

    fun isUserVerified(): Single<Boolean> = Single.just(true)
}