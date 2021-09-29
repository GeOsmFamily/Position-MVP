package com.sogefi.position.ui

import android.content.Context
import android.util.AttributeSet
import android.util.TypedValue
import android.view.View
import android.widget.FrameLayout
import android.widget.ImageView
import android.widget.TextView
import androidx.core.content.ContextCompat
import androidx.recyclerview.widget.RecyclerView
import butterknife.BindView
import com.sogefi.position.R

class TopIconButton @JvmOverloads constructor(
    ctx: Context,
    attrs: AttributeSet? = null,
    defStyle: Int = 0
): FrameLayout(ctx, attrs, defStyle) {

    @BindView(R.id.textView)
    var textView: TextView? = null

    @BindView(R.id.imageView)
    var imageView: ImageView? = null

    init {
        isClickable = true
        isFocusable = true
        background = ContextCompat.getDrawable(
            ctx,
            TypedValue().apply {
                context.theme.resolveAttribute(android.R.attr.selectableItemBackgroundBorderless, this, true)
            }.resourceId
        )

        View.inflate(ctx, R.layout.top_icon_button, this)
        ctx.theme.obtainStyledAttributes(
            attrs,
            R.styleable.TopIconButton,
            0, 0).apply {

            try {
                val text = getString(R.styleable.TopIconButton_android_text)
                if (text?.isNotEmpty() == true) {
                    textView?.text  = text
                }
                val icon = getResourceId(R.styleable.TopIconButton_icon, -1)
                if (icon != -1) {
                    imageView?.setImageResource(icon)
                }
            } finally {
                recycle()
            }
        }
    }
}