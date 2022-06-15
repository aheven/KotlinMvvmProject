@file:Suppress("unused")

package heven.holt.library.viewbinding.nonreflection

import android.view.View
import androidx.viewbinding.ViewBinding
import heven.holt.library.R

@Suppress("UNCHECKED_CAST")
fun <VB : ViewBinding> View.getBinding(bind: (View) -> VB): VB =
  getTag(R.id.tag_view_binding) as? VB ?: bind(this).also { setTag(R.id.tag_view_binding, it) }

