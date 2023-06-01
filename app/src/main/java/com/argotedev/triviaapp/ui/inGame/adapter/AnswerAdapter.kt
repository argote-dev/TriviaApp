package com.argotedev.triviaapp.ui.inGame.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.core.content.ContextCompat
import androidx.recyclerview.widget.RecyclerView
import com.argotedev.triviaapp.R
import com.argotedev.triviaapp.databinding.ItemAnswerBinding
import com.argotedev.triviaapp.model.Answer

class AnswerAdapter(
    private val answers: List<Answer>,
    private val listener: AnswerListener
) : RecyclerView.Adapter<AnswerAdapter.ViewHolder>() {

    private var selectedItemIndex: Int = -1

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val view = ItemAnswerBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return ViewHolder(view)
    }

    override fun getItemCount() = answers.size

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        val answer = answers[position]
        holder.bind(answer)
        if (position == selectedItemIndex) {
            holder.changeButtonColor(R.color.blue_200)
        } else {
            holder.changeButtonColor(R.color.white)
        }
    }

    inner class ViewHolder(private val binding: ItemAnswerBinding) :
        RecyclerView.ViewHolder(binding.root) {
        fun bind(answer: Answer) {
            binding.btnItemAnswer.text = answer.title
            binding.btnItemAnswer.setOnClickListener {
                val previousSelectedItemIndex = selectedItemIndex
                selectedItemIndex = adapterPosition

                notifyItemChanged(previousSelectedItemIndex)
                notifyItemChanged(selectedItemIndex)

                listener.clickItem(answer)
            }
        }

        fun changeButtonColor(color: Int) {
            binding.btnItemAnswer.setBackgroundColor(
                ContextCompat.getColor(
                    binding.root.context,
                    color
                )
            )
        }
    }

}