package com.yuk.miuihome.view.base

import android.content.Context
import android.graphics.Typeface
import android.util.TypedValue
import android.view.View
import android.widget.TextView
import com.yuk.miuihome.R
import com.yuk.miuihome.view.data.Padding
import com.yuk.miuihome.view.utils.ktx.dp2px

class TextV(
    val text: String? = null,
    val resId: Int? = null,
    val textSize: Float? = null,
    val textColor: Int? = null,
    private val padding: Padding? = null,
    private val typeface: Typeface? = null,
    val onClickListener: View.OnClickListener? = null
) : BaseView() {

    override fun getType(): BaseView = this

    override fun create(context: Context): View {
        return TextView(context).also { view ->
            text?.let { view.text = it }
            resId?.let { view.setText(it) }
            if (textSize == null)
                view.setTextSize(TypedValue.COMPLEX_UNIT_SP,18f)
            else
                view.setTextSize(TypedValue.COMPLEX_UNIT_SP,textSize)
            if (typeface == null)
                view.paint.typeface = Typeface.defaultFromStyle(Typeface.BOLD)
            else
                view.paint.typeface = typeface
            textColor?.let { view.setTextColor(it) }
            view.setPadding(dp2px(25f), dp2px(16f), dp2px(25f), dp2px(16f))
            padding?.let { view.setPadding(it.left, it.top, it.right, it.bottom) }
            onClickListener?.let { view.setOnClickListener(it); view.background = context.getDrawable(R.drawable.ic_click_check) }
        }
    }
}