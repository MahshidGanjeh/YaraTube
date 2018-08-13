package com.yaratech.yaratube.home.store;

import android.content.Context;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;

import com.yaratech.yaratube.R;
import com.yaratech.yaratube.data.model.Headeritem;

import java.util.List;

public class HeaderContainerViewHolder extends RecyclerView.ViewHolder {

    private RecyclerView recyclerView;

    public HeaderContainerViewHolder(View itemView) {
        super(itemView);
        recyclerView = itemView.findViewById(R.id.header_recycler);
    }

    public void onBind(Context context, List<Headeritem> list) {
        recyclerView.setLayoutManager(new LinearLayoutManager(context, LinearLayoutManager.HORIZONTAL, false));
        HeaderAdapter adapter = new HeaderAdapter(context);
        recyclerView.setAdapter(adapter);
        adapter.setHeaderList(list);
    }
}
