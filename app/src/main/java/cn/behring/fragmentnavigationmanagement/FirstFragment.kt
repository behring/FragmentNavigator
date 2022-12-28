package cn.behring.fragmentnavigationmanagement

import android.os.Bundle
import androidx.fragment.app.Fragment
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


class FirstFragment : Fragment() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
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

}