package cn.behring.fragmentnavigationmanagement.navigator

import android.os.Bundle
import androidx.fragment.app.Fragment
import androidx.activity.OnBackPressedCallback
import androidx.fragment.app.setFragmentResult
import androidx.fragment.app.setFragmentResultListener


open class NavigationHandlerFragment : Fragment() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        requireActivity().onBackPressedDispatcher.addCallback(
            this,
            object : OnBackPressedCallback(true) {
                override fun handleOnBackPressed() {
                    onBackPressed()
                }
            })

        registerFragmentResultHandlers().forEach {
            setFragmentResultListener(it.requestKey) { _, result ->
                it.onHandle(result)
            }
        }
    }

    open fun registerFragmentResultHandlers(): List<FragmentResultHandler> {
        return emptyList()
    }

    open fun recordFragmentResults(): List<FragmentResult> {
        return emptyList()
    }

    open fun onBackPressed() {}

    protected fun notifyFragmentResult(requestKey: String) {
        recordFragmentResults().firstOrNull { it.requestKey == requestKey }?.let {
            setFragmentResult(it.requestKey, it.result ?: Bundle())
        }
    }

    data class FragmentResultHandler(val requestKey: String, val onHandle: (Bundle) -> Unit)


    data class FragmentResult(val requestKey: String, val result: Bundle? = null)

}