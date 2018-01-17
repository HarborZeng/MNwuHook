package cn.tellyouwhat.mhook

import android.util.Log
import android.widget.Button
import de.robv.android.xposed.IXposedHookLoadPackage
import de.robv.android.xposed.XC_MethodHook
import de.robv.android.xposed.XposedBridge
import de.robv.android.xposed.XposedHelpers
import de.robv.android.xposed.callbacks.XC_LoadPackage

/**
 * Created by harbo on 2018/1/16.
 * Email: harbourzeng@gmail.com
 */
class Main : IXposedHookLoadPackage {
    override fun handleLoadPackage(lpparam: XC_LoadPackage.LoadPackageParam?) {
        if (lpparam!!.packageName != "com.murpcn.student.u10697")
            return

        XposedHelpers.findAndHookMethod(StringBuilder::class.java,
                "toString",
                object : XC_MethodHook() {
                    override fun beforeHookedMethod(param: MethodHookParam?) {
                        Log.d("xposed抓到了", "before")
                        XposedBridge.log("before")
                    }

                    override fun afterHookedMethod(param: MethodHookParam?) {
                        Log.d("xposed抓到了", "after")
                        XposedBridge.log("after")
                        val result = param!!.resultOrThrowable
                        Log.d("xposed抓到了", result.toString())
                        XposedBridge.log(result.toString())
                    }
                })
    }

}