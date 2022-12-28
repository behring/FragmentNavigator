package cn.behring.fragmentnavigationmanagement

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.compose.material3.Text
import androidx.compose.ui.platform.ComposeView
import androidx.navigation.fragment.findNavController
import androidx.navigation.fragment.navArgs
import cn.behring.fragmentnavigationmanagement.navigator.NavigationHandlerFragment


class SecondFragment : NavigationHandlerFragment() {
    private val args: SecondFragmentArgs by navArgs()

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        return ComposeView(requireContext()).apply {
            setContent {
                println(args.message)
                Text(text = "second fragment")
            }
        }
    }

    override fun onBackPressed() {
       notifyFragmentResult(FirstFragment.REQUEST_KEY_REFRESH_A_LIST)
        findNavController().popBackStack()
    }

    override fun recordFragmentResults(): List<FragmentResult> {
        return listOf(
            FragmentResult(FirstFragment.REQUEST_KEY_REFRESH_A_LIST)
        )
    }
}