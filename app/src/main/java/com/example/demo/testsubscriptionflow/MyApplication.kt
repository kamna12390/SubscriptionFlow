package com.example.demo.testsubscriptionflow

import android.annotation.SuppressLint
import android.app.Activity
import android.app.ActivityManager
import android.app.Application
import android.content.ComponentName
import android.content.Context
import android.graphics.Color
import android.os.Bundle
import androidx.lifecycle.Lifecycle
import androidx.lifecycle.LifecycleObserver
import androidx.lifecycle.OnLifecycleEvent
import androidx.lifecycle.ProcessLifecycleOwner
import com.example.demo.subscriptionbackgroundflow.AdsClasss.AppOpenManager
import com.example.demo.subscriptionbackgroundflow.AppSubscription
import com.example.demo.subscriptionbackgroundflow.activity.SubscriptionClass
import com.example.demo.subscriptionbackgroundflow.basemodule.BaseSharedPreferences
import com.example.demo.subscriptionbackgroundflow.constants.Constants
import com.example.demo.subscriptionbackgroundflow.constants.Constants.IsOutAppPermission
import com.example.demo.subscriptionbackgroundflow.constants.Constants.currentActivity
import com.example.demo.subscriptionbackgroundflow.constants.Constants.isAdsShowing
import com.example.demo.subscriptionbackgroundflow.constants.Constants.isoutApp

class MyApplication : AppSubscription(), LifecycleObserver,
    Application.ActivityLifecycleCallbacks {
    private var mPackagerenList = arrayListOf(
        Constants.PackagesRen(
            originalPrice = "₹610.00",
            freeTrialPeriod = "P1W",
            title = "Image Crop - Monthly PRO (Photo Crop: Cut, Convert, Trim)",
            price = "₹610.00",
            description = "",
            subscriptionPeriod = "P1M",
            sku = "subscribe_monthly_imagecrop_799"
        ),
        Constants.PackagesRen(
            originalPrice = "₹3,100.00",
            freeTrialPeriod = "P1W",
            title = "Image Crop - Monthly PRO (Photo Crop: Cut, Convert, Trim)",
            price = "₹3,100.00",
            description = "",
            subscriptionPeriod = "P1Y",
            sku = "subscribe_yearly_imagecrop_3999"
        )
    )
    val AppID = 7
    var appOpenManager: AppOpenManager? = null


    @SuppressLint("UseCompatLoadingForDrawables", "NewApi", "ResourceAsColor")
    override fun onCreate() {
        super.onCreate()
        registerActivityLifecycleCallbacks(this)
        ProcessLifecycleOwner.get().lifecycle.addObserver(this)

        val mBasePremiumLine = arrayListOf(
            Constants.LineWithIconModel(
                "Restore Messages",
                false,
                this.resources.getDrawable(R.drawable.ic_restore),
                R.color.white
            ),
            Constants.LineWithIconModel(
                "Deleted Media Viewer",
                false,
                this.resources.getDrawable(R.drawable.ic_delete),
                R.color.white
            ),
            Constants.LineWithIconModel(
                "VIP Customer Support",
                true,
                this.resources.getDrawable(R.drawable.ic_vip),
                R.color.white
            ),
            Constants.LineWithIconModel(
                "Remove Ads",
                true,
                this.resources.getDrawable(R.drawable.ic_ad),
                R.color.white
            )
        )
        val mMainPremiumLine = arrayListOf(
            Constants.LineWithIconModel(
                "Restore Messages",
                false,
                this.resources.getDrawable(R.drawable.ic_restore),
                R.color.white
            ),
            Constants.LineWithIconModel(
                "Deleted Media Viewer",
                false,
                this.resources.getDrawable(R.drawable.ic_delete),
                R.color.white
            ),
            Constants.LineWithIconModel(
                "VIP Customer Support",
                true,
                this.resources.getDrawable(R.drawable.ic_vip),
                R.color.white
            ),
            Constants.LineWithIconModel(
                "Remove Ads",
                true,
                this.resources.getDrawable(R.drawable.ic_ad),
                R.color.white
            )
        )
        SubscriptionClass.ActivityBuilder(this)
//            .Adsliber(AppID, ProcessLifecycleOwner.get())
            .setBASIC_SKU("subscribe_monthly_scantext")
            .setPREMIUM_SKU("subscribe_yearly_scantext")
            .setPREMIUM_SIX_SKU("subscribe_monthly_scantext")
            .setAdsID(
                mAppOpenID = "ca-app-pub-3940256099942544/3419835294",
                mBannerAdaptiveID = "ca-app-pub-3940256099942544/6300978111",
                mInterstitialID = "ca-app-pub-3940256099942544/1033173712",
                mNativeAdsID = "ca-app-pub-3940256099942544/2247696110",
                mRewardedID = "ca-app-pub-3940256099942544/5224354917"
            )
            .setPrivacyPolicyURL("https://agneshpipaliya.blogspot.com/2019/03/image-crop-n-wallpaper-changer.html")
            .setIsRevenuCat(false)
            .setPurchase_ID("goog_IvcEUSwmDPbXkcYQpLvPKAhtbfO")
            .setIsDebugMode(true)
            .setIsTestMode(false)
            .setSYSTEM_UI_FLAG_LAYOUT_HIDE_NAVIGATION(true)
            .setListOfLine(mBasePremiumLine)
            .setMainScreenListOfLine(mMainPremiumLine)
            .setDefaultPackagList(mPackagerenList)
            .setAppName(this.resources.getString(R.string.app_name), R.color.primery_color)
            .setMainLineColor(R.color.white)
            .setPriceLineColor(R.color.white)
            .setSubLineColor(R.color.dark_gray)
            .setNavigationBarColor(R.color.colornavi)
            .setSmallSubLineColor(R.color.light_gray_color)
            .setSUBButtonTextColor(R.color.black)
            .setAppIcon(this.resources.getDrawable(R.drawable.ic_app_icon))
            .setPremium_True_Icon(this.resources.getDrawable(R.drawable.ic_true_icon))
            .setBasic_Line_Icon(this.resources.getDrawable(R.drawable.ic_line_lock))
            .setBaseSubscriptionBackground(this.resources.getDrawable(R.drawable.ic_basesub))
            .setSubscriptionBackground(this.resources.getDrawable(R.drawable.ic_basesub))
            .setPriceBackground(this.resources.getDrawable(R.drawable.ic_price_bg))
            .setClose_Icon(this.resources.getDrawable(R.drawable.close_icon))
            .setPremium_CardSelected_Icon(this.resources.getDrawable(R.drawable.ic_pro_selected))
            .setPremium_Cardunselected_Icon(this.resources.getDrawable(R.drawable.ic_pro_selection))
            .setPremium_Button_Icon(this.resources.getDrawable(R.drawable.bg_sub_btn_))
            .setNew_NativeAdsLayout(R.layout.layout_native_ads)
            .setOld_NativeAdsLayout(R.layout.layout_native_ads_old)
            .Subcall()
        appOpenManager = AppOpenManager(this)


    }

    @OnLifecycleEvent(Lifecycle.Event.ON_PAUSE)
    fun onappBackground() {
        SubscriptionClass.ActivityBuilder(this).let {
            if (!isoutApp!!) {
                it.setActivityOpen(true, this)
            }
        }

    }


    @OnLifecycleEvent(Lifecycle.Event.ON_START)
    fun onAppForegrounded() {

        val am: ActivityManager = getSystemService(Context.ACTIVITY_SERVICE) as ActivityManager
        val cn: ComponentName? = am.getRunningTasks(1)[0].topActivity
        if (!BaseSharedPreferences(this).mIS_SUBSCRIBED!!) {
            if (!isoutApp!! && !IsOutAppPermission && !isAdsShowing
                && (cn?.className != "com.example.demo.testsubscriptionflow.SplashScreenActivity")
                && (cn?.className != "com.example.demo.subscriptionbackgroundflow.activity.SubscriptionBackgroundActivity")
                && (cn?.className != "com.example.demo.subscriptionbackgroundflow.activity.SubscriptionActivity")
            ) {
                currentActivity?.let {
                    appOpenManager!!.showAdIfAvailable(it,
                        object : AppOpenManager.OnShowAdCompleteListener {
                            override fun onShowAdComplete() {

                            }
                        })
                }
            }

        }
        SubscriptionClass.ActivityBuilder(this).let {
            isoutApp = false
            it.setActivityOpen(false, this)
        }
    }

    override fun onActivityCreated(activity: Activity, savedInstanceState: Bundle?) {

    }

    override fun onActivityStarted(activity: Activity) {
        if (!isAdsShowing) {
            currentActivity = activity
        }
    }

    override fun onActivityResumed(activity: Activity) {

    }

    override fun onActivityPaused(activity: Activity) {

    }

    override fun onActivityStopped(activity: Activity) {

    }

    override fun onActivitySaveInstanceState(activity: Activity, outState: Bundle) {

    }

    override fun onActivityDestroyed(activity: Activity) {

    }
}