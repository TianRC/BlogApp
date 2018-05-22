package com.trc.shift.activity.home.fragment.tag;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.trc.shift.R;
import com.trc.shift.resultBean.ArticleTagListBean;
import com.trc.shift.unit.MyToast;
import com.trc.shift.unit.SoundPoolUtil;
import com.trc.shift.unit.clickAnim.DefaultClickEffectScaleAnimate;
import com.trc.shift.unit.OnClickEffectTouchListener;
import com.trc.shift.unit.clickAnim.OnClickAnimUtil;
import com.trycatch.mysnackbar.Prompt;
import com.trycatch.mysnackbar.TSnackbar;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

/**
 * Created by trc on 2018/5/4.
 */

public class TagRvAdapter extends RecyclerView.Adapter<TagRvAdapter.ViewHolder> {
    private Context context;
    private List<ArticleTagListBean.TagsBean> list = new ArrayList<>();

    public void setList(List<ArticleTagListBean.TagsBean> list) {
        this.list = list;
        notifyDataSetChanged();
    }

    public TagRvAdapter(Context context) {
        this.context = context;
    }


    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(context).inflate(R.layout.item_tag_tv, parent, false);
        ViewHolder holder = new ViewHolder(view);
        return holder;
    }

    @Override
    public void onBindViewHolder(ViewHolder holder, int position) {
        holder.bg.setBackgroundResource(getBg(position));
        holder.textView.setText(list.get(position).getName());
        holder.itemView.setOnClickListener(v -> {
            SoundPoolUtil.getInstance(context).play(1);
            TSnackbar.make(holder.itemView,
                    list.get(position).getName(),
                    TSnackbar.LENGTH_SHORT,
                    TSnackbar.APPEAR_FROM_TOP_TO_DOWN)
                    .setPromptThemBackground(Prompt.SUCCESS).show();
        });
        OnClickAnimUtil.clickAnim(holder.itemView);
        RelativeLayout.LayoutParams lp = new RelativeLayout.LayoutParams(holder.itemView.getLayoutParams());
        lp.setMargins(position % 2 == 0 ? dip2px(10) : 0, 0, 0, 0);
        holder.itemView.setLayoutParams(lp);
    }

    @Override
    public int getItemCount() {
        return list.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {
        LinearLayout bg;
        TextView textView;

        public ViewHolder(View itemView) {
            super(itemView);
            bg = itemView.findViewById(R.id.item_bg);
            textView = itemView.findViewById(R.id.item_tag_tv);
        }
    }

    private int getBg(int position) {
        Random rand = new Random();
        int i = rand.nextInt(10);
        switch (position % 10) {
            case 0:
                return R.drawable.shape_tag_item1;
            case 1:
                return R.drawable.shape_tag_item2;
            case 2:
                return R.drawable.shape_tag_item3;
            case 3:
                return R.drawable.shape_tag_item4;
            case 4:
                return R.drawable.shape_tag_item5;
            case 5:
                return R.drawable.shape_tag_item6;
            case 6:
                return R.drawable.shape_tag_item7;
            case 7:
                return R.drawable.shape_tag_item8;
            case 8:
                return R.drawable.shape_tag_item9;
            case 9:
                return R.drawable.shape_tag_item10;
            case 10:
                return R.drawable.shape_tag_item7;
            default:
                return R.drawable.shape_tag_item6;
        }

    }

    public int dip2px(int dp) {
        float density = context.getResources().getDisplayMetrics().density;
        return (int) (dp * density + 0.5);
    }

}
