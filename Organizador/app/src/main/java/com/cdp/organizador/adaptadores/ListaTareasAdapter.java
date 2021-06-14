package com.cdp.organizador.adaptadores;

import android.content.Context;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.cdp.organizador.R;
import com.cdp.organizador.VerActivity;
import com.cdp.organizador.entidades.Tareas;

import java.util.ArrayList;

public class ListaTareasAdapter extends RecyclerView.Adapter<ListaTareasAdapter.ContactoViewHolder> {

    ArrayList<Tareas> listaTareas;

    public ListaTareasAdapter(ArrayList<Tareas> listaTareas){
        this.listaTareas = listaTareas;
    }

    @NonNull
    @Override
    public ContactoViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.lista_item_tarea, null, false);
        return new ContactoViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ContactoViewHolder holder, int position) {
        holder.viewTitulo.setText(listaTareas.get(position).getTitulo());
        holder.viewDescripcion.setText(listaTareas.get(position).getDescripcion() + " - " + listaTareas.get(position).getClasificacion().getNombre());
    }

    @Override
    public int getItemCount() {
        return listaTareas.size();
    }

    public class ContactoViewHolder extends RecyclerView.ViewHolder {

        TextView viewTitulo, viewDescripcion;

        public ContactoViewHolder(@NonNull View itemView) {
            super(itemView);

            viewTitulo = itemView.findViewById(R.id.viewTitulo);
            viewDescripcion = itemView.findViewById(R.id.viewDescripcion);

            itemView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    Context context = view.getContext();
                    Intent intent = new Intent(context, VerActivity.class);
                    intent.putExtra("ID", listaTareas.get(getAdapterPosition()).getId());
                    context.startActivity(intent);
                }
            });
        }
    }
}
