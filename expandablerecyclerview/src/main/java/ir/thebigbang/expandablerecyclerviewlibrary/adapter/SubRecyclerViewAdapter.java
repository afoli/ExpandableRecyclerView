package ir.thebigbang.expandablerecyclerviewlibrary.adapter;

import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import java.util.List;

import ir.thebigbang.expandablerecyclerviewlibrary.ConstDefault;
import ir.thebigbang.expandablerecyclerviewlibrary.R;
import ir.thebigbang.expandablerecyclerviewlibrary.customview.TvIcon;
import ir.thebigbang.expandablerecyclerviewlibrary.models.AttrItem;
import ir.thebigbang.expandablerecyclerviewlibrary.models.ChildItem;
import ir.thebigbang.expandablerecyclerviewlibrary.processes.OnRecyclerItemClickListener;

public class SubRecyclerViewAdapter extends RecyclerView.Adapter<SubRecyclerViewAdapter.MyViewHolder> {

    private int lineOfSubListColor;
    private int textOfSubListColor;
    private int backgroundOfSubListColor;
    private int visibilityLineOfSubList;

    private List<AttrItem> attrList;
    private List<ChildItem> subTitleList;

    private int subListItemLayout;
    private String typeOfSubLayout;

    private OnRecyclerItemClickListener clickListener;

    public SubRecyclerViewAdapter(List<ChildItem> subTitleList,
                                  int subListItemLayout,
                                  String typeOfSubLayout,
                                  List<AttrItem> attrList,
                                  OnRecyclerItemClickListener clickListener) {
        this.subTitleList = subTitleList;
        this.subListItemLayout = subListItemLayout;
        this.typeOfSubLayout = typeOfSubLayout;
        this.attrList = attrList;
        this.clickListener = clickListener;
    }


    @Override
    public SubRecyclerViewAdapter.MyViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {

        return new MyViewHolder(LayoutInflater.from(parent.getContext())
                .inflate(subListItemLayout, parent, false));
    }

    @Override
    public void onBindViewHolder(SubRecyclerViewAdapter.MyViewHolder holder, int position) {
        setAttrs();
        setDataView(holder, position);
    }

    @Override
    public int getItemCount() {
        return subTitleList.size();
    }

    public class MyViewHolder extends RecyclerView.ViewHolder {

        LinearLayout layout;
        TextView text;
        ImageView image;
        TvIcon imageIcon;
        View bottomLine;

        public MyViewHolder(View itemView) {
            super(itemView);

            switch (typeOfSubLayout) {
                case ConstDefault.SubLayout.SUB_TEXT_LIST:

                    layout = (LinearLayout) itemView.findViewById(R.id.layout_sub_list);
                    text = (TextView) itemView.findViewById(R.id.sub_text);
                    image = (ImageView) itemView.findViewById(R.id.sub_image);
                    imageIcon = (TvIcon) itemView.findViewById(R.id.sub_image_icon);
                    bottomLine = itemView.findViewById(R.id.line_close_item);

                    break;

                case ConstDefault.SubLayout.SUB_TEXT_ICON_LIST:

                    layout = (LinearLayout) itemView.findViewById(R.id.layout_sub_list);
                    text = (TextView) itemView.findViewById(R.id.sub_text);
                    image = (ImageView) itemView.findViewById(R.id.sub_image);
                    imageIcon = (TvIcon) itemView.findViewById(R.id.sub_image_icon);
                    bottomLine = itemView.findViewById(R.id.line_close_item);

                    break;

                case ConstDefault.SubLayout.SUB_TEXT_CIRCLE_IMAGE_LIST:

                    layout = (LinearLayout) itemView.findViewById(R.id.layout_sub_list);
                    text = (TextView) itemView.findViewById(R.id.sub_text);
                    image = (ImageView) itemView.findViewById(R.id.sub_image);
                    imageIcon = (TvIcon) itemView.findViewById(R.id.sub_image_icon);
                    bottomLine = itemView.findViewById(R.id.line_close_item);

                    break;

                case ConstDefault.SubLayout.SUB_TEXT_RECTANGLE_IMAGE_LIST:

                    layout = (LinearLayout) itemView.findViewById(R.id.layout_sub_list);
                    text = (TextView) itemView.findViewById(R.id.sub_text);
                    image = (ImageView) itemView.findViewById(R.id.sub_image);
                    imageIcon = (TvIcon) itemView.findViewById(R.id.sub_image_icon);
                    bottomLine = itemView.findViewById(R.id.line_close_item);

                    break;

                case ConstDefault.SubLayout.SUB_TEXT_ROUNDED_IMAGE_LIST:

                    layout = (LinearLayout) itemView.findViewById(R.id.layout_sub_list);
                    text = (TextView) itemView.findViewById(R.id.sub_text);
                    image = (ImageView) itemView.findViewById(R.id.sub_image);
                    imageIcon = (TvIcon) itemView.findViewById(R.id.sub_image_icon);
                    bottomLine = itemView.findViewById(R.id.line_close_item);

                    break;

                case ConstDefault.SubLayout.SUB_IMAGE_LIST:

                    layout = (LinearLayout) itemView.findViewById(R.id.layout_sub_list);
                    text = (TextView) itemView.findViewById(R.id.sub_text);
                    image = (ImageView) itemView.findViewById(R.id.sub_image);
                    imageIcon = (TvIcon) itemView.findViewById(R.id.sub_image_icon);
                    bottomLine = itemView.findViewById(R.id.line_close_item);

                    break;

                case ConstDefault.SubLayout.SUB_TEXT_IMAGE_GRID:

                    layout = (LinearLayout) itemView.findViewById(R.id.layout_sub_list);
                    text = (TextView) itemView.findViewById(R.id.sub_text);
                    image = (ImageView) itemView.findViewById(R.id.sub_image);
                    imageIcon = (TvIcon) itemView.findViewById(R.id.sub_image_icon);
                    //without bottom line

                    break;

                case ConstDefault.SubLayout.SUB_IMAGE_GRID:

                    layout = (LinearLayout) itemView.findViewById(R.id.layout_sub_list);
                    text = (TextView) itemView.findViewById(R.id.sub_text);
                    image = (ImageView) itemView.findViewById(R.id.sub_image);
                    imageIcon = (TvIcon) itemView.findViewById(R.id.sub_image_icon);
                    //without bottom line

                    break;
            }

            //click for items
            itemView.findViewById(R.id.layout_sub_list).setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    clickListener.onClick(view, getAdapterPosition());
                }
            });
        }
    }

    private void setDataView(MyViewHolder holder, int position) {

        switch (typeOfSubLayout) {
            case ConstDefault.SubLayout.SUB_TEXT_LIST:
                holder.text.setText(subTitleList.get(position).getText());
                break;

            case ConstDefault.SubLayout.SUB_TEXT_ICON_LIST:
                holder.text.setText(subTitleList.get(position).getText());
                holder.imageIcon.setText(subTitleList.get(position).getImageIcon());
                break;

            case ConstDefault.SubLayout.SUB_TEXT_CIRCLE_IMAGE_LIST:
                holder.text.setText(subTitleList.get(position).getText());
                holder.image.setImageResource(subTitleList.get(position).getImage());
                break;

            case ConstDefault.SubLayout.SUB_TEXT_RECTANGLE_IMAGE_LIST:
                holder.text.setText(subTitleList.get(position).getText());
                holder.image.setImageResource(subTitleList.get(position).getImage());
                break;

            case ConstDefault.SubLayout.SUB_TEXT_ROUNDED_IMAGE_LIST:
                holder.text.setText(subTitleList.get(position).getText());
                holder.image.setImageResource(subTitleList.get(position).getImage());
                break;

            case ConstDefault.SubLayout.SUB_IMAGE_LIST:
                holder.image.setImageResource(subTitleList.get(position).getImage());
                break;

            case ConstDefault.SubLayout.SUB_TEXT_IMAGE_GRID:
                holder.text.setText(subTitleList.get(position).getText());
                holder.image.setImageResource(subTitleList.get(position).getImage());
                //without bottom line
                break;

            case ConstDefault.SubLayout.SUB_IMAGE_GRID:
                holder.image.setImageResource(subTitleList.get(position).getImage());
                //without bottom line
                break;
        }

        if (textOfSubListColor != 0) {
            holder.text.setTextColor(textOfSubListColor);
        }
        if (lineOfSubListColor != 0) {
            holder.bottomLine.setBackgroundColor(lineOfSubListColor);
        }
        if (backgroundOfSubListColor != 0) {
            holder.layout.setBackgroundColor(backgroundOfSubListColor);
        }
        if (visibilityLineOfSubList != 0) {
            switch (visibilityLineOfSubList) {
                case 1:
                    holder.bottomLine.setVisibility(View.VISIBLE);
                    break;
                case 2:
                    holder.bottomLine.setVisibility(View.GONE);
                    break;
            }
        }

    }

    private void setAttrs() {

        for (int i = 0; i < attrList.size(); i++) {
            switch (i) {
                case 4:
                    lineOfSubListColor = attrList.get(i).getValue();
                    break;
                case 5:
                    textOfSubListColor = attrList.get(i).getValue();
                    break;
                case 6:
                    backgroundOfSubListColor = attrList.get(i).getValue();
                    break;
                case 9:
                    visibilityLineOfSubList = attrList.get(i).getValue();
                    break;
            }
        }
    }
}
