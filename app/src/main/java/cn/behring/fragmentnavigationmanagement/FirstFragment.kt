package cn.behring.fragmentnavigationmanagement

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.material3.Button
import androidx.compose.material3.Text
import androidx.compose.ui.Alignment.Companion.CenterHorizontally
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.ComposeView
import androidx.navigation.findNavController
import cn.behring.fragmentnavigationmanagement.navigator.NavigationHandlerFragment


class FirstFragment : NavigationHandlerFragment() {
    companion object {
      const val  REQUEST_KEY_REFRESH_A_LIST = "refresh A list"
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        return ComposeView(requireContext()).apply {
            setContent {

                Column(modifier = Modifier.fillMaxWidth(), horizontalAlignment = CenterHorizontally) {
                    Text(text = "first fragment")

                    Button(onClick = {
                        FirstFragmentDirections.actionFirstFragmentToSecondFragment(message = "this is a message from first fragment")
                            .run {
                                findNavController().navigate(this)
                            }
                    }) {
                        Text(text = "go to second fragment")
                    }
                }
            }
        }
    }

    override fun registerFragmentResultHandlers(): List<FragmentResultHandler> {
        return listOf(
            FragmentResultHandler(REQUEST_KEY_REFRESH_A_LIST) {
                println("A list has been refreshed.")
            }
        )
    }
}