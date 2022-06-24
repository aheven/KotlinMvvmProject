package heven.holt.library.loadingstateview.delegate

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import heven.holt.library.databinding.LayoutToolbarBinding
import heven.holt.library.loadingstateview.BaseToolbarViewDelegate
import heven.holt.library.loadingstateview.NavBtnType
import heven.holt.library.loadingstateview.ToolbarConfig
import heven.holt.library.loadingstateview.toolbarExtras

var ToolbarConfig.rightTextColor: Int? by toolbarExtras()

class ToolbarViewDelegate : BaseToolbarViewDelegate() {
  private lateinit var binding: LayoutToolbarBinding

  override fun onCreateToolbar(inflater: LayoutInflater, parent: ViewGroup): View {
    binding = LayoutToolbarBinding.inflate(inflater, parent, false)
    return binding.root
  }

  override fun onBindToolbar(config: ToolbarConfig) {
    binding.apply {
      tvTitle.text = config.title

      when (config.navBtnType) {
        NavBtnType.ICON -> {
          config.navIcon?.let { ivLeft.setImageResource(it) }
          ivLeft.setOnClickListener(config.onNavClickListener)
          tvLeft.visibility = View.GONE
          ivLeft.visibility = View.VISIBLE
        }
        NavBtnType.TEXT -> {
          tvLeft.text = config.navText
          tvLeft.setOnClickListener(config.onNavClickListener)
          tvLeft.visibility = View.VISIBLE
          ivLeft.visibility = View.GONE
        }
        NavBtnType.ICON_TEXT -> {
          config.navIcon?.let { ivLeft.setImageResource(it) }
          tvLeft.text = config.navText
          ivLeft.setOnClickListener(config.onNavClickListener)
          tvLeft.setOnClickListener(config.onNavClickListener)
          tvLeft.visibility = View.VISIBLE
          ivLeft.visibility = View.VISIBLE
        }
        NavBtnType.NONE -> {
          ivLeft.visibility = View.GONE
          tvLeft.visibility = View.GONE
        }
      }

      if (config.rightText != null) {
        tvRight.text = config.rightText
        tvRight.setOnClickListener(config.onRightClickListener)
        tvRight.visibility = View.VISIBLE
        config.rightTextColor?.let { tvRight.setTextColor(it) }
      } else {
        tvRight.visibility = View.GONE
      }

      if (config.rightIcon != null) {
        ivRight.setImageResource(config.rightIcon!!)
        ivRight.setOnClickListener(config.onRightClickListener)
        ivRight.visibility = View.VISIBLE
      } else {
        ivRight.visibility = View.GONE
      }
    }
  }
}