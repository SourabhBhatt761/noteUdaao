package com.SRB.noteudaao

import android.content.Context
import android.media.MediaPlayer
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.AttributeSet
import android.view.View
import android.view.WindowManager
import android.view.animation.LinearInterpolator
import com.SRB.noteudaao.databinding.ActivityMainBinding
import com.SRB.noteudaao.timber.DebugTree
import com.SRB.noteudaao.timber.ReleaseTree
import com.yuyakaido.android.cardstackview.*
import timber.log.Timber
import kotlin.random.Random
import kotlin.random.asJavaRandom

class MainActivity : AppCompatActivity(),CardStackListener {

    lateinit var binding : ActivityMainBinding
    private lateinit var cardManager: CardStackLayoutManager
    lateinit var cardAdapter: CardStackAdapter



    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

//        window.setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN,
//                WindowManager.LayoutParams.FLAG_FULLSCREEN)
        window.statusBarColor = getColor(R.color.white)

        actionBar?.hide()
        setDebugTools()
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)


        val imageArray = arrayListOf<Int>()
//        for (i in 0..100) {
//            imageArray.add(i,R.drawable.ind_2000)
//        }
        imageArray.add(R.drawable.ind_2000)
        imageArray.add(R.drawable.ind_200)
        imageArray.add(R.drawable.ind_500)
        val images = ArrayList<Int>()
        for (i in 0..1000) {
            images.add(imageArray.random())
        }

        Timber.i(images.toString())

        cardAdapter = CardStackAdapter()
        cardAdapter.showEverything(images)

        cardManager = CardStackLayoutManager(this, this).apply {
            setOverlayInterpolator(LinearInterpolator())
            setSwipeableMethod(SwipeableMethod.AutomaticAndManual)
        }

        cardManager.setStackFrom(StackFrom.None)
        cardManager.setVisibleCount(1)
        cardManager.setTranslationInterval(28.0f)
        cardManager.setMaxDegree(100f)
        cardManager.setDirections(Direction.FREEDOM)
        cardManager.setSwipeThreshold(0.01f)
        cardManager.setCanScrollHorizontal(true)
        cardManager.setCanScrollVertical(true)



        binding.cardStackView.apply {
            layoutManager = cardManager
            adapter = cardAdapter
        }



    }


    override fun onCardDragging(direction: Direction?, ratio: Float) {
        Timber.i("Card view dragged")
    }

    override fun onCardSwiped(direction: Direction?) {
        Timber.i("Card view swiped")
    }

    override fun onCardRewound() {
        Timber.i("Card view rewound")
    }

    override fun onCardCanceled() {
        Timber.i("Card view cancelled")
    }

    override fun onCardAppeared(view: View?, position: Int) {
        Timber.i("Card view appeared")

    }

    override fun onCardDisappeared(view: View?, position: Int) {
        Timber.i("Card view disappeared")
        MediaPlayer.create(this,R.raw.money_sound).start()
    }

    private fun setDebugTools() {
        if (BuildConfig.DEBUG) {
            Timber.plant(DebugTree())
        } else {
            Timber.plant(ReleaseTree())
        }
    }

}