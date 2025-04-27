package com.example.harjoitusty;

import android.content.Context;
import android.view.*;
import android.widget.ImageView;
import android.widget.ProgressBar;
import android.widget.TextView;
import androidx.annotation.*;
import androidx.recyclerview.widget.DiffUtil;
import androidx.recyclerview.widget.ListAdapter;
import androidx.recyclerview.widget.RecyclerView;

public class LutemonAdapter
        extends ListAdapter<Lutemon, LutemonAdapter.ViewHolder> {

    public static final DiffUtil.ItemCallback<Lutemon> DIFF_CALLBACK =
            new DiffUtil.ItemCallback<Lutemon>() {
                @Override public boolean areItemsTheSame(@NonNull Lutemon a, @NonNull Lutemon b) {
                    return a.getId()==b.getId();
                }
                @Override public boolean areContentsTheSame(@NonNull Lutemon a, @NonNull Lutemon b) {
                    return a.equals(b);
                }
            };

    private final Context ctx;
    public LutemonAdapter(Context context) {
        super(DIFF_CALLBACK);
        this.ctx = context;
    }

    @NonNull @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View v = LayoutInflater.from(ctx)
                .inflate(R.layout.item_lutemon, parent, false);
        return new ViewHolder(v);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder h, int pos) {
        Lutemon l = getItem(pos);
        h.name.setText(l.getName());
        h.stats.setText(
                "ATK:"+l.getAttack()+
                        " DEF:"+l.getDefense()+
                        " EXP:"+l.getExperience()
        );
        h.pb.setMax(l.getMaxHp());
        h.pb.setProgress(l.getCurrentHealth());
        int resId = ctx.getResources().getIdentifier(
                "lutemon_"+l.getId(), "drawable", ctx.getPackageName()
        );
        h.img.setImageResource(resId!=0?resId:android.R.drawable.ic_menu_help);
    }

    static class ViewHolder extends RecyclerView.ViewHolder {
        ImageView img;
        TextView name, stats;
        ProgressBar pb;
        ViewHolder(View v) {
            super(v);
            img    = v.findViewById(R.id.imgLutemon);
            name   = v.findViewById(R.id.txtName);
            stats  = v.findViewById(R.id.txtStats);
            pb     = v.findViewById(R.id.progressBar);
        }
    }
}
