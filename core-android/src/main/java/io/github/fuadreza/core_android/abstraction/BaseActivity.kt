package io.github.fuadreza.core_android.abstraction

import android.os.Bundle
import android.view.View
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.databinding.DataBindingUtil
import androidx.databinding.ViewDataBinding
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.google.android.material.snackbar.BaseTransientBottomBar
import com.google.android.material.snackbar.Snackbar

abstract class BaseActivity<B : ViewDataBinding, V : ViewModel> : AppCompatActivity(), IBaseView {
    private lateinit var mViewDataBinding: B
    private lateinit var mViewModel: V
    private var snackbar: Snackbar? = null
    private var toast: Toast? = null

    val binding: B
        get() = mViewDataBinding
    val vm: V
        get() = mViewModel

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        mViewDataBinding = DataBindingUtil.setContentView(this, getLayoutResourceId())
        mViewDataBinding.lifecycleOwner = this

        mViewModel = ViewModelProvider(this).get(getViewModelClass())

        initViews()
        initObservers()
    }

    override fun onStop() {
        snackbar?.dismiss()
        toast?.cancel()
        super.onStop()
    }

    protected fun showSnackbar(
        text: String, @BaseTransientBottomBar.Duration duration: Int = Snackbar.LENGTH_LONG
        , actionText: String? = null, actionClick: View.OnClickListener? = null
    ) {
        snackbar?.dismiss()
        snackbar = Snackbar.make(findViewById(android.R.id.content), text, duration)
        if (actionText != null && actionClick != null) {
            snackbar!!.setAction(actionText, actionClick)
        }
        snackbar!!.show()
    }

    protected fun showToast(text: String, duration: Int = Toast.LENGTH_LONG) {
        toast?.cancel()
        toast = Toast.makeText(this, text, duration)
        toast!!.show()
    }

    abstract fun getViewModelClass(): Class<V>
}