package waleid.your.otloob.ViewHolders;


import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import waleid.your.otloob.Interfaces.OnItemClickListner;
import waleid.your.otloob.R;

public class CategoriesViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener
{
    public ImageView categoryImg;
    public TextView categoryName;

    private OnItemClickListner onItemClickListner;

    public CategoriesViewHolder(View itemView) {
        super(itemView);
        categoryName = itemView.findViewById(R.id.category_name);
        categoryImg = itemView.findViewById(R.id.category_img);

        //ba2olo n elitemview d bthandel elclicklistener
        itemView.setOnClickListener(this);

    }

    //ha3ml fun set llinterface elly 3rafto fo2
    public void setOnItemClickListner(OnItemClickListner onItemClickListner)
    {

        this.onItemClickListner = onItemClickListner;
    }

    @Override
    public void onClick(View v)
    {
        onItemClickListner.onClick(v,getAdapterPosition(),false);

    }


}
