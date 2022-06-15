package heven.holt.mvvm.widget

import android.content.Context
import android.util.AttributeSet
import androidx.constraintlayout.widget.ConstraintLayout
import heven.holt.mvvm.R
import heven.holt.mvvm.databinding.LayoutViewBindingCustomViewBinding
import heven.holt.library.viewbinding.ktx.inflate

class ViewBindingCustomView @JvmOverloads constructor(
    context: Context,
    attr: AttributeSet? = null,
    defStyleAttr: Int = 0
) : ConstraintLayout(context, attr, defStyleAttr) {
    val binding = inflate<LayoutViewBindingCustomViewBinding>()

    init {
        binding.tvTitle.setText(R.string.show_loading_dialog)
    }
}