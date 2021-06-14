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
import com.cdp.organizador.entidades.Clasificacion;

import java.util.ArrayList;

public class ListaClasificacionAdapter  extends RecyclerView.Adapter<ListaClasificacionAdapter.ClasificacionViewHolder>{
    ArrayList<Clasificacion> listaClasificacion;

    public ListaClasificacionAdapter(ArrayList<Clasificacion> listaClasificacion){
        this.listaClasificacion = listaClasificacion;
    }

    @NonNull
    @Override
    public ListaClasificacionAdapter.ClasificacionViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.lista_item_clasificacion, null, false);
        return new ListaClasificacionAdapter.ClasificacionViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ListaClasificacionAdapter.ClasificacionViewHolder holder, int position) {
        holder.viewName.setText(listaClasificacion.get(position).getNombre());
    }

    @Override
    public int getItemCount() {
        return listaClasificacion.size();
    }

    public class ClasificacionViewHolder extends RecyclerView.ViewHolder {

        TextView viewName;

        public ClasificacionViewHolder(@NonNull View itemView) {
            super(itemView);

            viewName = itemView.findViewById(R.id.nombreClasificacion);

            itemView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    Context context = view.getContext();
                    Intent intent = new Intent(context, VerActivity.class);
                    intent.putExtra("ID", listaClasificacion.get(getAdapterPosition()).getId());
                    context.startActivity(intent);
                }
            });
        }
    }
}
