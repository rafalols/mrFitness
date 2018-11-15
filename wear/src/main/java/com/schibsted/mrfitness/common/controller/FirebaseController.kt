package com.schibsted.mrfitness.common.controller

import com.google.firebase.database.DataSnapshot
import com.google.firebase.database.DatabaseError
import com.google.firebase.database.FirebaseDatabase
import com.google.firebase.database.ValueEventListener
import com.schibsted.mrfitness.common.model.FinishedSerie
import com.schibsted.mrfitness.common.model.NextSerie
import com.schibsted.mrfitness.common.utils.logD
import io.reactivex.Completable
import io.reactivex.Single
import java.util.*

class FirebaseController(private val database: FirebaseDatabase) {

    companion object {
        const val user = "HP7RdU33Zhhnzar5wopcXfgqMgB3"
        const val training = "RXZvb5hbx1ZJWEuMgp92"
    }

    fun getNextSerie(): Single<NextSerie> {
        val ref = database.getReference("user/$user/nextSerie")
        return Single.create { emmiter ->
            ref.addListenerForSingleValueEvent(object : ValueEventListener{
                override fun onCancelled(error: DatabaseError) {
                    logD("Serie: onCancelled: ${error.message}")
                    if (!emmiter.isDisposed) {
                        emmiter.onError(error.toException())
                    }
                }

                override fun onDataChange(dataSnapshot: DataSnapshot) {
                    logD("Serie: onDataChange: $dataSnapshot")
                    if (emmiter.isDisposed) {
                        ref.removeEventListener(this)
                    } else  {
                        dataSnapshot.getValue(NextSerie::class.java)?.let { emmiter.onSuccess(it) }
                    }
                }

            })
        }
    }

    fun sendFinishedSerie(serie: FinishedSerie) : Completable {
        val random = Random().nextLong()
        val ref = database.getReference("user/$user/training/$training/series/serie$random")
        return Completable.create { emmiter ->
            ref.setValue(serie)
                .addOnCompleteListener { emmiter.onComplete() }
                .addOnCanceledListener { emmiter.onComplete() }
        }
    }


}