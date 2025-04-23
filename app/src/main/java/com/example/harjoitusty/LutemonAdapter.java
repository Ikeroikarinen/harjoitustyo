package com.example.harjoitusty;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.ProgressBar;
import android.widget.TextView;
import androidx.annotation.NonNull;
import androidx.recyclerview.widget.DiffUtil;
import androidx.recyclerview.widget.ListAdapter;
import androidx.recyclerview.widget.RecyclerView;

public class LutemonAdapter
        extends ListAdapter<Lutemon, LutemonAdapter.ViewHolder> {

    public static final DiffUtil.ItemCallback<Lutemon> DIFF_CALLBACK =
            new DiffUtil.ItemCallback<Lutemon>() {
                @Override
                public boolean areItemsTheSame(@NonNull Lutemon a, @NonNull Lutemon b) {
                    return a.getId() == b.getId();
                }
                @Override
                public boolean areContentsTheSame(@NonNull Lutemon a, @NonNull Lutemon b) {
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
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        Lutemon l = getItem(position);
        holder.name.setText(l.getName());
        holder.stats.setText(
                "ATK:" + l.getAttack() +
                        " DEF:" + l.getDefense() +
                        " EXP:" + l.getExperience()
        );
        holder.pb.setMax(l.getMaxHp());
        holder.pb.setProgress(l.getCurrentHealth());

        // Kuvan nimi drawable-resurssissa: lutemon_<id tai nimi>, muokkaa tarpeen mukaan
        int resId = ctx.getResources().getIdentifier(
                "lutemon_" + l.getId(),
                "drawable",
                ctx.getPackageName()
        );
        holder.img.setImageResource(resId != 0 ? resId : android.R.drawable.ic_menu_help);
    }

    static class ViewHolder extends RecyclerView.ViewHolder {
        ImageView img;
        TextView name, stats;
        ProgressBar pb;
        ViewHolder(View itemView) {
            super(itemView);
            img    = itemView.findViewById(R.id.imgLutemon);
            name   = itemView.findViewById(R.id.txtName);
            stats  = itemView.findViewById(R.id.txtStats);
            pb     = itemView.findViewById(R.id.progressBar);
        }
    }
}
