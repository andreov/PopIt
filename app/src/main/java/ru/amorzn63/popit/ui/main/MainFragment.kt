package ru.amorzn63.popit.ui.main

import androidx.lifecycle.ViewModelProvider
import android.os.Bundle
import android.view.HapticFeedbackConstants
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import androidx.appcompat.widget.AppCompatImageView
import androidx.fragment.app.viewModels
import androidx.gridlayout.widget.GridLayout
import ru.amorzn63.popit.R
import ru.amorzn63.popit.databinding.MainFragmentBinding

class MainFragment : Fragment() {

    companion object {
        fun newInstance() = MainFragment()
    }

    private lateinit var binding: MainFragmentBinding // отложенная инициализация binding
    private val viewModel: MainViewModel by viewModels()  // делегат для работы с ViewModel

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding = MainFragmentBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        viewModel.state.observe(viewLifecycleOwner) {
            binding.field.removeAllViews()
            it.forEachIndexed { index, state ->
                val row = index / 5
                val colum = index % 5
                val params = GridLayout.LayoutParams(  //задаются координаты пузыря для View
                    GridLayout.spec(row, 1f),
                    GridLayout.spec(colum, 1f)
                )
                // отрисовка элемента
                val item = ImageView(requireContext()).apply {
                    setImageResource(R.drawable.ic_selected)
                    setBackgroundResource(R.drawable.backgraud_item)
                    isEnabled = state                   // состояние вкл выкл
                    isHapticFeedbackEnabled = true   // вкл. вибрации
                    setOnClickListener { v ->
                        v.performHapticFeedback(HapticFeedbackConstants.VIRTUAL_KEY)
                        viewModel.onPopClick(index)

                    }
                }
                binding.field.addView(item, params)
            }
        }
    }


}