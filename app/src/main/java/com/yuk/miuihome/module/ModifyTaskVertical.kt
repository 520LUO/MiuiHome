package com.yuk.miuihome.module

import android.content.Context
import android.graphics.RectF
import com.yuk.miuihome.view.utils.Config
import com.yuk.miuihome.view.utils.OwnSP
import com.yuk.miuihome.view.utils.ktx.callMethod
import com.yuk.miuihome.view.utils.ktx.callStaticMethod
import com.yuk.miuihome.view.utils.ktx.getObjectField
import com.yuk.miuihome.view.utils.ktx.replaceMethod

class ModifyTaskVertical {

    fun init() {
        val value = OwnSP.ownSP.getFloat("task_vertical", 1000f) / 1000f
        if (value == 1f) return
        "com.miui.home.recents.views.TaskStackViewsAlgorithmVertical".replaceMethod("scaleTaskView", RectF::class.java
        ) {
            val context = it.thisObject.getObjectField("mContext") as Context
            "com.miui.home.recents.util.Utilities".callStaticMethod("scaleRectAboutCenter", it.args[0], "com.miui.home.recents.util.Utilities".callStaticMethod("getTaskViewScale", context))
            "com.miui.home.recents.util.Utilities".callStaticMethod("scaleRectAboutCenter", it.args[0], value - (context.resources.getDimensionPixelSize(context.resources.getIdentifier("recents_task_view_padding", "dimen", Config.hookPackage)) * value / it.args[0].callMethod("width") as Float))
        }
    }
}