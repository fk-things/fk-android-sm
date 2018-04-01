package com.things.fk.library.recyler.adapter;

import android.content.Context;
import android.support.annotation.LayoutRes;
import android.support.annotation.Nullable;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.things.fk.library.recyler.RecyclerViews;
import com.things.fk.library.utils.TextViews;

import java.util.List;
import java.util.Map;

/**
 * Simple Adapter for RecyclerView
 * Created by tic on 18-1-3.
 */
public class SimpleRecyclerAdapter<K, V> extends RecyclerView.Adapter<SimpleRecyclerAdapter.ViewHolder> {

    private int layout;
    private int[] bindIds;
    private final K[] keys;

    private List<Map<K, V>> data;

    private Context context;
    private LayoutInflater inflater;
    private RecyclerViews.OnItemClickListener onItemClickListener;

    public SimpleRecyclerAdapter(Context context, List<Map<K, V>> data, K[] keys, @LayoutRes int layout, int[] bindIds,
                                 @Nullable RecyclerViews.OnItemClickListener onItemClickListener) {
        this.context = context;
        this.data = data;
        this.keys = keys;
        this.layout = layout;
        this.bindIds = bindIds;
        this.onItemClickListener = onItemClickListener;
        this.inflater = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        validResIds(this.bindIds);
    }

    private void validResIds(int[] bindIds) {
        if (bindIds.length <= 0) {
            throw new IllegalArgumentException("Please declare the bind id of TextView");
        }
    }

    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View itemView = inflater.inflate(layout, parent, false);
        ViewHolder vh = new ViewHolder(itemView, bindIds);
        vh.setOnItemClickListener(onItemClickListener);
        return vh;
    }

    @Override
    public void onBindViewHolder(ViewHolder holder, int position) {
        Map<K, V> item = data.get(position);
        TextViews.setTextSafely(holder.textView1, item.get(keys[0]).toString());
        if (bindIds.length > 1) {
            TextViews.setTextSafely(holder.textView2, item.get(keys[1]).toString());
        }
    }


    @Override
    public int getItemCount() {
        return data == null ? 0 : data.size();
    }

    static class ViewHolder extends RecyclerView.ViewHolder {
        TextView textView1;
        TextView textView2;

        ViewHolder(final View itemView, int... id) {
            super(itemView);
            textView1 = itemView.findViewById(id[0]);
            if (id.length > 1) {
                textView2 = itemView.findViewById(id[1]);
            }
        }

        void setOnItemClickListener(final RecyclerViews.OnItemClickListener onItemClickListener) {
            if (onItemClickListener != null) {
                itemView.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View view) {
                        onItemClickListener.onItemClick(view, itemView, getAdapterPosition());
                    }
                });
            }
        }
    }
}
