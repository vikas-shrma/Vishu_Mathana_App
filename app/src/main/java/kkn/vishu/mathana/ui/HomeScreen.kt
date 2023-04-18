package kkn.vishu.mathana.ui

import android.content.Intent
import android.content.pm.PackageManager
import android.net.Uri
import android.os.Bundle
import android.view.View
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.fragment.app.Fragment
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProviders
import com.google.android.gms.tasks.Task
import com.google.firebase.storage.FirebaseStorage
import com.google.firebase.storage.ListResult
import com.google.firebase.storage.StorageReference
import kkn.vishu.mathana.*
import kkn.vishu.mathana.databinding.ActivityHomeBinding
import kkn.vishu.mathana.fragments.*
import kkn.vishu.mathana.view_models.HomeViewModel


class HomeScreen : AppCompatActivity() {

    lateinit var binding: ActivityHomeBinding

    lateinit var storageReference: StorageReference

    lateinit var firebaseStorage: FirebaseStorage

    lateinit var homeViewModel: HomeViewModel

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        binding = ActivityHomeBinding.inflate(layoutInflater)

        setContentView(binding.root)

        initView()

        getFirebaseReferences()

    }

    private fun initView() {

        initViewModel()

        initFirebase()

        val fragmentHome = FragmentHome()
        val transaction = supportFragmentManager.beginTransaction()
        transaction.add(binding.container.id, fragmentHome, fragmentHome.javaClass.canonicalName)
        //transaction.addToBackStack(fragmentHome.javaClass.canonicalName)
        transaction.commitAllowingStateLoss()

        initClicks()

        showVisible(false)
        showClose(false)
    }

    private fun initClicks() {

        binding.ivFacebook.setOnClickListener {
            getUrlFromIntent(LINK_FACEBOOK)
        }
        binding.ivInsta.setOnClickListener {
            getUrlFromIntent(LINK_INSTAGRAM)
        }
        binding.ivTwitter.setOnClickListener {
            getUrlFromIntent(LINK_TWITTER)
        }
        binding.ivTouTube.setOnClickListener {
            getUrlFromIntent(LINK_YOU_TUBE)
        }

        binding.ivPinInterest.setOnClickListener {
            getUrlFromIntent(LINK_PIN_INTEREST)
        }
        binding.ivWhatsApp.setOnClickListener {
            val installed = appInstalledOrNot("com.whatsapp")
            if (installed) {
                val intent = Intent(Intent.ACTION_VIEW)
                intent.data = Uri.parse("http://api.whatsapp.com/send?phone=+919728899557&text=")
                startActivity(intent)
            } else {
                Toast.makeText(
                    this@HomeScreen,
                    "Whats app not installed on your device",
                    Toast.LENGTH_SHORT
                ).show()
            }
        }
        binding.tvWebsiteLink.setOnClickListener {
            getUrlFromIntent(LINK_WEBSITE)
        }

        binding.ivBack.setOnClickListener {
            onBackPressed()
        }

    }


    private fun initFirebase() {

        firebaseStorage = FirebaseStorage.getInstance()

        storageReference = firebaseStorage.getReferenceFromUrl(FIREBASE_URL)

        homeViewModel.firebaseReference.postValue(firebaseStorage)
        homeViewModel.storageReference.postValue(storageReference)

    }

    private fun getFirebaseReferences() {

        val islandRef = storageReference.child(PATH_COVER_IMAGES)

        val listAllTask: Task<ListResult> = islandRef.listAll()

        listAllTask.addOnCompleteListener { result ->

            println("The data length is ${result.result?.items?.size}")

            homeViewModel.coverImages.value = result.result?.items

        }

    }


    private fun initViewModel() {

        homeViewModel = ViewModelProviders.of(this)[HomeViewModel::class.java]

        homeViewModel.stringTitle.observe(this, Observer {

            binding.tvTopBarTitile.text = it
        })
    }

    fun getUrlFromIntent(url: String?) {

        val i = Intent(Intent.ACTION_VIEW)
        i.data = Uri.parse(url)
        startActivity(i)
    }


    fun changeFragment(index: Int, path: String = "") {

        val bundle = Bundle()

        var fragment = Fragment()

        fragment = if (index == 0) {
            FragmentAbout()
        } else if (index == 7) {
            FragmentHorizontalList()
        } else if (index != 9) {
            bundle.putInt("index", index)

            FragmentList()

        } else {
            bundle.putString("path", path)

            FragmentHorizontalListFirebase()

        }
        fragment.arguments = bundle

        val transaction = supportFragmentManager.beginTransaction()
        transaction.setCustomAnimations(
            android.R.anim.fade_in, android.R.anim.fade_out,
            android.R.anim.fade_in, android.R.anim.fade_out
        )

        transaction.add(binding.container.id, fragment, fragment.javaClass.canonicalName)

        transaction.addToBackStack(fragment.javaClass.canonicalName)
        transaction.commitAllowingStateLoss()
        //currentFragment = fragment
    }


    fun showVisible(isVisible: Boolean) {

        if (isVisible)
            binding.ivBack.visibility = View.VISIBLE
        else
            binding.ivBack.visibility = View.GONE
    }


    fun showClose(isVisible: Boolean) {

    }

    override fun onBackPressed() {


//        val myFragment: Fragment? = supportFragmentManager.findFragmentByTag(FragmentList().javaClass.canonicalName)
        val myFragment: Fragment = supportFragmentManager.findFragmentById(binding.container.id)!!
        if (myFragment != null && myFragment.isVisible && myFragment is FragmentList) {

            showVisible(false)
            showClose(false)

        } else if (myFragment != null && myFragment.isVisible && myFragment is FragmentSingleImage) {

            showVisible(true)
            showClose(false)
        }
        super.onBackPressed()
    }


    //Create method appInstalledOrNot
    private fun appInstalledOrNot(url: String): Boolean {
        val packageManager = packageManager
        val app_installed: Boolean
        app_installed = try {
            packageManager.getPackageInfo(url, PackageManager.GET_ACTIVITIES)
            true
        } catch (e: PackageManager.NameNotFoundException) {
            false
        }
        return app_installed
    }

}
