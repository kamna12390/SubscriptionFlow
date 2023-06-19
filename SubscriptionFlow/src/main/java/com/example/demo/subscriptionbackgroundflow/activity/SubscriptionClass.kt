package com.example.demo.subscriptionbackgroundflow.activity

import android.app.Application
import android.content.Context
import android.graphics.drawable.Drawable
import androidx.lifecycle.LifecycleOwner
import com.crop.photo.image.resize.cut.tools.subscripction.ProductPurchaseHelper.setSubscriptionKey
import com.example.demo.subscriptionbackgroundflow.basemodule.BaseSharedPreferences
import com.example.demo.subscriptionbackgroundflow.constants.Constants
import com.example.demo.subscriptionbackgroundflow.constants.Constants.BASIC_SKU
import com.example.demo.subscriptionbackgroundflow.constants.Constants.BaseSubscriptionBackground
import com.example.demo.subscriptionbackgroundflow.constants.Constants.IsOutAppPermission
import com.example.demo.subscriptionbackgroundflow.constants.Constants.NavigationBarColor
import com.example.demo.subscriptionbackgroundflow.constants.Constants.PREMIUM_SIX_SKU
import com.example.demo.subscriptionbackgroundflow.constants.Constants.PREMIUM_SKU
import com.example.demo.subscriptionbackgroundflow.constants.Constants.Purchase_ID
import com.example.demo.subscriptionbackgroundflow.constants.Constants.SUBButtonTextColor
import com.example.demo.subscriptionbackgroundflow.constants.Constants.SubscriptionBackground
import com.example.demo.subscriptionbackgroundflow.constants.Constants.isDebugMode
import com.example.demo.subscriptionbackgroundflow.constants.Constants.isTestMode
import com.example.demo.subscriptionbackgroundflow.constants.Constants.isoutApp
import com.example.demo.subscriptionbackgroundflow.constants.Constants.mAppIcon
import com.example.demo.subscriptionbackgroundflow.constants.Constants.mAppName
import com.example.demo.subscriptionbackgroundflow.constants.Constants.mAppNameColor
import com.example.demo.subscriptionbackgroundflow.constants.Constants.mBasic_Line_Icon
import com.example.demo.subscriptionbackgroundflow.constants.Constants.mClose_Icon
import com.example.demo.subscriptionbackgroundflow.constants.Constants.mIsRevenuCat
import com.example.demo.subscriptionbackgroundflow.constants.Constants.mMainLineColor
import com.example.demo.subscriptionbackgroundflow.constants.Constants.mNew_NativeAdsLayout
import com.example.demo.subscriptionbackgroundflow.constants.Constants.mOld_NativeAdsLayout
import com.example.demo.subscriptionbackgroundflow.constants.Constants.mPremiumLine
import com.example.demo.subscriptionbackgroundflow.constants.Constants.mPremiumScreenLine
import com.example.demo.subscriptionbackgroundflow.constants.Constants.mPremium_Button_Icon
import com.example.demo.subscriptionbackgroundflow.constants.Constants.mPremium_CardSelected_Icon
import com.example.demo.subscriptionbackgroundflow.constants.Constants.mPremium_Cardunselected_Icon
import com.example.demo.subscriptionbackgroundflow.constants.Constants.mPremium_True_Icon
import com.example.demo.subscriptionbackgroundflow.constants.Constants.mPriceBackground
import com.example.demo.subscriptionbackgroundflow.constants.Constants.mPriceLineColor
import com.example.demo.subscriptionbackgroundflow.constants.Constants.mPrivacyPolicyURL
import com.example.demo.subscriptionbackgroundflow.constants.Constants.mSYSTEM_UI_FLAG_LAYOUT_HIDE_NAVIGATION
import com.example.demo.subscriptionbackgroundflow.constants.Constants.mSmallSubLineColor
import com.example.demo.subscriptionbackgroundflow.constants.Constants.mSubLineColor
import com.example.demo.subscriptionbackgroundflow.constants.Constants.packagerenlist
import com.example.demo.subscriptionbackgroundflow.helper.logD
import com.revenuecat.purchases.Purchases
import com.revenuecat.purchases.PurchasesConfiguration
import com.revenuecat.purchases.getOfferingsWith
 object SubscriptionClass {
    public class ActivityBuilder(private val activity: Context) :
        Builder() {
        override fun Subcall(): Builder {
            if (mIsRevenuCat!!){
                Purchases.debugLogsEnabled = true
                Purchases.configure(
                    PurchasesConfiguration.Builder(activity, Purchase_ID).build()
                )
                Purchases.sharedInstance.getOfferingsWith({ error ->
                    // An error occurred
                    logD("SubscriptionList", "error->${error.message}")
                }) { offerings ->
                    offerings.current?.availablePackages?.takeUnless { it.isNullOrEmpty() }?.let {
                        // Display packages for sale
//                    logD(
//                        "yagnik",
//                        "suc-> 1 ${"originalPrice :- " + it[0].product.originalPrice + "\n" + "freeTrialPeriod :- " + it[0].product.freeTrialPeriod + "\n" + "title :- " + it[0].product.title + "\n" + "price :- " + it[0].product.price + "\n" + "description :- " + it[0].product.description + "\n" + "subscriptionPeriod :- " + it[0].product.subscriptionPeriod + "\n" + "sku :- " + it[0].product.sku + "\n"}"
//                    )
                        logD("SubscriptionList", "1->${it[0].product.sku}--2->${it[1].product.sku}--3->${it[0].product.sku}")
                        packagerenlist?.clear()
                        Constants.BASIC_SKU = it[0].product.sku
                        Constants.PREMIUM_SKU = it[1].product.sku
                        Constants.PREMIUM_SIX_SKU = it[0].product.sku

                        packagerenlist = arrayListOf()
                        packagerenlist?.add(
                            Constants.PackagesRen(
                                it[0].product.originalPrice.toString(),
                                it[0].product.freeTrialPeriod.toString(),
                                it[0].product.title,
                                it[0].product.price,
                                it[0].product.description,
                                it[0].product.subscriptionPeriod.toString(),
                                it[0].product.sku
                            )
                        )
                        packagerenlist?.add(
                            Constants.PackagesRen(
                                it[1].product.originalPrice.toString(),
                                it[1].product.freeTrialPeriod.toString(),
                                it[1].product.title,
                                it[1].product.price,
                                it[1].product.description,
                                it[1].product.subscriptionPeriod.toString(),
                                it[1].product.sku
                            )
                        )
//                    logD(
//                        "yagnik",
//                        "suc-> 2 ${"originalPrice :- " + it[1].product.originalPrice + "\n" + "freeTrialPeriod :- " + it[1].product.freeTrialPeriod + "\n" + "title :- " + it[1].product.title + "\n" + "price :- " + it[1].product.price + "\n" + "description :- " + it[1].product.description + "\n" + "subscriptionPeriod :- " + it[1].product.subscriptionPeriod + "\n" + "sku :- " + it[1].product.sku + "\n"}"
//                    )

                    }
                }
            }else{
                setSubscriptionKey(BASIC_SKU, PREMIUM_SIX_SKU, PREMIUM_SKU)
            }
            return this
        }

//        override fun Adsliber(int: Int,life: LifecycleOwner): Builder {
//            logD("mAdsAppID", "mAdsAppID->$int---$isDebugMode")
////            Toast.makeText(activity,"mAdsAppID->$int---$isDebugMode",Toast.LENGTH_LONG).show()
//            Helper().startDataSync(activity, life)
//            AppIDs.init(activity, int, false)
//
//            logD(
//                "SubscriptionList",
//                "=->${packagerenlist?.toString()}"
//            )
//            return this
//        }
    }

    abstract class Builder  {
        fun setPREMIUM_SIX_SKU(string: String): Builder {
            PREMIUM_SIX_SKU=string
            return this
        }

        fun getPREMIUM_SIX_SKU(): String {
            return Constants.PREMIUM_SIX_SKU
        }

        fun setBASIC_SKU(string: String): Builder {
            BASIC_SKU=string
            return this
        }

        fun getBASIC_SKU(): String {
            return Constants.BASIC_SKU
        }

        fun setPREMIUM_SKU(string: String): Builder {
            PREMIUM_SKU=string
            return this
        }

        fun getPREMIUM_SKU(): String {
            return Constants.PREMIUM_SKU
        }

        fun setPurchase_ID(string: String): Builder {
            Purchase_ID=string
            return this
        }

        fun getPurchase_ID(): String {
            return Constants.Purchase_ID
        }

        //        fun setisSUBSCRIBED(boolean: Boolean):Builder {
//            config.setisSUBSCRIBED(boolean)
//            return this
//        }
        fun getIS_SUBSCRIBED(context: Context?): Boolean {
            return BaseSharedPreferences(context!!).mIS_SUBSCRIBED!!
        }

        fun setIsOutApp(boolean: Boolean): Builder {
            isoutApp=boolean
            return this
        }

        fun getIsOutApp(): Boolean? {
            return isoutApp
        }

        fun setIsOutAppPermission(boolean: Boolean): Builder {
            IsOutAppPermission=boolean
            return this
        }

        fun getIsOutAppPermission(): Boolean {
            return IsOutAppPermission
        }
        fun setAdsID(mAppOpenID:String?="",mBannerAdaptiveID:String?="",mInterstitialID:String?="",mNativeAdsID:String?="",mRewardedID:String?=""): Builder {
            Constants.mAppOpenID=mAppOpenID
            Constants.mBannerAdaptiveID=mBannerAdaptiveID
            Constants.mInterstitialID=mInterstitialID
            Constants.mNativeAdsID=mNativeAdsID
            Constants.mRewardedID=mRewardedID
            return this
        }
        fun setActivityOpen(boolean: Boolean, application: Application): Builder {
            BaseSharedPreferences(application).mActivityOpen = boolean
            return this
        }

        fun setIsDebugMode(boolean: Boolean): Builder {
            isDebugMode = boolean
            return this
        }
        fun setIsTestMode(boolean: Boolean): Builder {
            isTestMode = boolean
            return this
        }
        fun setSYSTEM_UI_FLAG_LAYOUT_HIDE_NAVIGATION(boolean: Boolean): Builder {
            mSYSTEM_UI_FLAG_LAYOUT_HIDE_NAVIGATION = boolean
            return this
        }
        fun setPrivacyPolicyURL(string: String): Builder {
            mPrivacyPolicyURL = string
            return this
        }

        fun setPremium_True_Icon(drawable: Drawable): Builder {
            mPremium_True_Icon = drawable
            return this
        }

        fun setBasic_Line_Icon(drawable: Drawable): Builder {
            mBasic_Line_Icon = drawable
            return this
        }
        fun setBaseSubscriptionBackground(drawable: Drawable): Builder {
            BaseSubscriptionBackground = drawable
            return this
        }
        fun setSubscriptionBackground(drawable: Drawable): Builder {
            SubscriptionBackground = drawable
            return this
        }
        fun setPriceBackground(drawable: Drawable): Builder {
            mPriceBackground = drawable
            return this
        }

        fun setClose_Icon(drawable: Drawable): Builder {
            mClose_Icon = drawable
            return this
        }

        fun setPremium_Button_Icon(drawable: Drawable): Builder {
            mPremium_Button_Icon = drawable
            return this
        }

        fun setPremium_CardSelected_Icon(drawable: Drawable): Builder {
            mPremium_CardSelected_Icon = drawable
            return this
        }

        fun setPremium_Cardunselected_Icon(drawable: Drawable): Builder {
            mPremium_Cardunselected_Icon = drawable
            return this
        }

        fun setListOfLine(premiumLine: ArrayList<Constants.LineWithIconModel>): Builder {
            mPremiumLine = premiumLine
            return this
        }

        fun setMainScreenListOfLine(premiumLine: ArrayList<Constants.LineWithIconModel>): Builder {
            mPremiumScreenLine = premiumLine
            return this
        }

        fun setDefaultPackagList(premiumLine: ArrayList<Constants.PackagesRen>): Builder {
            packagerenlist = premiumLine
            return this
        }

        fun setAppName(AppName: String, color: Int): Builder {
            mAppName = AppName
            mAppNameColor=color
            return this
        }
        fun setMainLineColor(color: Int): Builder {
            mMainLineColor=color
            return this
        }
        fun setSubLineColor(color: Int): Builder {
            mSubLineColor=color
            return this
        }
        fun setSmallSubLineColor(color: Int): Builder {
            mSmallSubLineColor=color
            return this
        }
        fun setSUBButtonTextColor(color: Int): Builder {
            SUBButtonTextColor=color
            return this
        }
        fun setPriceLineColor(color: Int): Builder {
            mPriceLineColor=color
            return this
        }
        fun setNavigationBarColor(color: Int): Builder {
            NavigationBarColor =color
            return this
        }

        fun setAppIcon(drawable: Drawable): Builder {
            mAppIcon = drawable
            return this
        }

        fun setNew_NativeAdsLayout(int: Int): Builder {
            mNew_NativeAdsLayout = int
            return this
        }
        fun setOld_NativeAdsLayout(int: Int): Builder {
            mOld_NativeAdsLayout = int
            return this
        }fun setIsRevenuCat(boolean: Boolean): Builder {
            mIsRevenuCat = boolean
            return this
        }

        abstract fun Subcall(): Builder
//        abstract fun Adsliber(int: Int,life: LifecycleOwner): Builder
    }
}