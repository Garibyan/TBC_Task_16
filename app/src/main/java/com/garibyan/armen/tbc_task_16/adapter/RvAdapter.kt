package com.garibyan.armen.tbc_task_16.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.paging.PagingDataAdapter
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.RecyclerView
import coil.load
import coil.transform.CircleCropTransformation
import com.garibyan.armen.tbc_task_16.databinding.RvItemBinding
import com.garibyan.armen.tbc_task_16.network.ResponseModel

class RvAdapter: PagingDataAdapter<ResponseModel.Person, RvAdapter.PersonViewHolder>(PersonCallBack()) {

    inner class PersonViewHolder(private val binding:RvItemBinding): RecyclerView.ViewHolder(binding.root){
        fun bind(person: ResponseModel.Person) = with(binding){
            imgAvatar.load(person.avatar){
                transformations(CircleCropTransformation())
            }
            txtEmail.text = person.email
            txtName.text = person.firstname + " " + person.lastname
        }
    }

    class PersonCallBack: DiffUtil.ItemCallback<ResponseModel.Person>() {
        override fun areItemsTheSame(
            oldItem: ResponseModel.Person,
            newItem: ResponseModel.Person
        ) = oldItem.id == newItem.id
        override fun areContentsTheSame(
            oldItem: ResponseModel.Person,
            newItem: ResponseModel.Person
        ) = oldItem == newItem
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int) =
        PersonViewHolder(RvItemBinding.inflate(LayoutInflater.from(parent.context), parent, false))

    override fun onBindViewHolder(holder: PersonViewHolder, position: Int) {
        holder.bind(getItem(position)!!)
    }
}