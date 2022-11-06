package kkn.vishu.mathana.view_models

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.google.firebase.storage.FirebaseStorage
import com.google.firebase.storage.StorageReference
import kkn.vishu.mathana.data.ImageData

class HomeViewModel : ViewModel() {

    var selectedPosition : Int = 0

    val coverImages : MutableLiveData<List<StorageReference>> = MutableLiveData(listOf())

    val allListImages : MutableLiveData<List<StorageReference>> = MutableLiveData(listOf())

    val firebaseReference : MutableLiveData<FirebaseStorage> = MutableLiveData()

    val storageReference : MutableLiveData<StorageReference> = MutableLiveData()

    var allFirebaseList : MutableLiveData<MutableList<StorageReference>> = MutableLiveData()

}