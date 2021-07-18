package io.github.fuadreza.core_android.abstraction

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.databinding.DataBindingUtil
import androidx.databinding.ViewDataBinding
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.google.android.material.snackbar.BaseTransientBottomBar
import com.google.android.material.snackbar.Snackbar

abstract class BaseFragment<B : ViewDataBinding, V : ViewModel> : Fragment(), IBaseView {
    private lateinit var mViewDataBinding: B
    private lateinit var mViewModel: V

    private var toast: Toast? = null
    private var snackbar: Snackbar? = null

    val binding: B
        get() = mViewDataBinding
    val vm: V
        get() = mViewModel

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        mViewDataBinding =
            DataBindingUtil.inflate(inflater, getLayoutResourceId(), container, false)

        mViewModel = ViewModelProvider(this).get(getViewModelClass())
        mViewDataBinding.lifecycleOwner = viewLifecycleOwner
        mViewDataBinding.executePendingBindings()

        return mViewDataBinding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        initViews()
    }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)
        initObservers()
    }

    override fun onDestroyView() {
        snackbar?.dismiss()
        toast?.cancel()
        super.onDestroyView()
    }

    protected fun showSnackbar(text: String, @BaseTransientBottomBar.Duration duration: Int = Snackbar.LENGTH_LONG
                               , actionText: String? = null, actionClick: View.OnClickListener? = null) {
        snackbar?.dismiss()
        snackbar = Snackbar.make(requireActivity().findViewById(android.R.id.content), text, duration)
        if (actionText != null && actionClick != null) {
            snackbar!!.setAction(actionText, actionClick)
        }
        snackbar!!.show()
    }

    protected fun showToast(text: String,duration: Int = Toast.LENGTH_LONG) {
        toast?.cancel()
        toast = Toast.makeText(requireContext(),text,duration)
        toast!!.show()
    }

    abstract fun getViewModelClass(): Class<V>
}