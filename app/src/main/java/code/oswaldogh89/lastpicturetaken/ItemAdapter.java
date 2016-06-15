package code.oswaldogh89.lastpicturetaken;

/**
 * Created by oswaldogh89 on 14/06/16.
 */

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;

import com.bumptech.glide.Glide;

import java.io.File;
import java.util.ArrayList;

public class ItemAdapter extends RecyclerView.Adapter<ItemAdapter.ItemRowHolder> {
    Context context;
    private final ArrayList<Image> item;

    public ItemAdapter(Context ctx, ArrayList<Image> bmp) {
        this.item = bmp;
        this.context = ctx;
    }

    @Override
    public ItemRowHolder onCreateViewHolder(ViewGroup viewGroup, int i) {
        View v = LayoutInflater.from(viewGroup.getContext()).inflate(R.layout.list_item, null);
        return new ItemRowHolder(v);
    }

    @Override
    public void onBindViewHolder(ItemRowHolder view, int i) {
        Glide.with(context).load(new File(item.get(i).getImgPath().getAbsolutePath())).into(view._imagenItem);
    }


    public class ItemRowHolder extends RecyclerView.ViewHolder {
        protected ImageView _imagenItem;

        public ItemRowHolder(View view) {
            super(view);
            this._imagenItem = (ImageView) view.findViewById(R.id.imgThumb);
        }
    }

    @Override
    public int getItemCount() {
        return (null != item ? item.size() : 0);
    }

}