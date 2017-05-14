package ir.thebigbang.expandablerecyclerviewlibrary.adapter;

import android.content.Context;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.FrameLayout;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import java.util.List;

import ir.thebigbang.expandablerecyclerviewlibrary.ConstDefault;
import ir.thebigbang.expandablerecyclerviewlibrary.R;
import ir.thebigbang.expandablerecyclerviewlibrary.customview.TvIcon;
import ir.thebigbang.expandablerecyclerviewlibrary.models.AttrItem;
import ir.thebigbang.expandablerecyclerviewlibrary.models.ChildItem;
import ir.thebigbang.expandablerecyclerviewlibrary.models.TitleItem;
import ir.thebigbang.expandablerecyclerviewlibrary.processes.OnRecyclerItemClickListener;

public class ParentListAdapter extends RecyclerView.Adapter<RecyclerView.ViewHolder> {

    private OnRecyclerItemClickListener clickListener;

    private List<TitleItem> titleList;
    private List<ChildItem> subTitleList;
    private Context context;

    private int parentLayout;
    private int childLayout;
    private int subListItemLayout;

    private View viewCollapse;
    private View viewExpand;
    private View viewExpandSingleMode;

    private String TypeOfTitleLayout;
    private String TypeOfSubLayout;
    private List<AttrItem> attrList;

    private int titleColor;
    private int subtitleColor;
    private int iconColor;
    private int lineOfTitleListColor;
    private int lineOfSubListColor;
    private int textOfSubListColor;
    private int backgroundOfSubListColor;
    private int backgroundOfTitleListColor;
    private int visibilityLineOfTitleList;
    private int visibilityLineOfSubList;
    private int imageIconColor;

    private int iconCloseFont;
    private int iconOpenFont;

    private int imageClose;
    private int imageOpen;

    private int statusIcon;

    private int subTitleListSize;

    private int grid;

    private LinearLayout layoutSingle;
    private ImageView imageSingle;
    private View bottomLineSingle;
    private TextView textSingle;

    public ParentListAdapter(List<TitleItem> titleList, List<ChildItem> subTitleList, Context context,
                             int parentLayout, int childLayout, int subListItemLayout,
                             String typeOfTitleLayout, String typeOfSubLayout, List<AttrItem> attrList,
                             int iconCloseFont, int iconOpenFont, int imageClose,
                             int imageOpen, int statusIcon, int subTitleListSize, int grid,
                             OnRecyclerItemClickListener clickListener) {
        this.titleList = titleList;
        this.subTitleList = subTitleList;
        this.context = context;
        this.parentLayout = parentLayout;
        this.childLayout = childLayout;
        this.TypeOfTitleLayout = typeOfTitleLayout;
        this.attrList = attrList;
        this.iconCloseFont = iconCloseFont;
        this.iconOpenFont = iconOpenFont;
        this.imageClose = imageClose;
        this.imageOpen = imageOpen;
        this.statusIcon = statusIcon;
        this.subTitleListSize = subTitleListSize;
        this.subListItemLayout = subListItemLayout;
        this.TypeOfSubLayout = typeOfSubLayout;
        this.grid = grid;
        this.clickListener = clickListener;
    }

    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {

        setLayouts(parent);

        switch (viewType) {
            case 0:
                return new parentHolder(viewCollapse);
            case 1:
                SubSingleHolder(viewExpandSingleMode);
                return new childHolder(viewExpand);
        }
        return null;
    }

    @Override
    public void onBindViewHolder(RecyclerView.ViewHolder holder, int position) {

        setAttrs();

        if (holder instanceof parentHolder) {
            parentHolder parentHolder = (ParentListAdapter.parentHolder) holder;
            setDataParent(parentHolder, position);

        } else {
            childHolder childHolder = (ParentListAdapter.childHolder) holder;
            setDataChild(childHolder, position);

            childHolder.title.setText(titleList.get(position).getName());

            if (subTitleListSize != 0) {
                if (grid != 0) { // is grid
                    childHolder.recyclerView.setLayoutManager(new GridLayoutManager(context, 2));
                } else {
                    childHolder.recyclerView.setLayoutManager(new LinearLayoutManager(context));
                }

                SubRecyclerViewAdapter adapter = new SubRecyclerViewAdapter
                        (subTitleList, subListItemLayout, TypeOfSubLayout, attrList, new OnRecyclerItemClickListener() {
                            @Override
                            public void onClick(View v, int pos) {
                                clickListener.onClick(v, pos);
                            }
                        });

                childHolder.recyclerView.setAdapter(adapter);
            } else {
                // without list //1.single text //2.single image
                switch (TypeOfSubLayout) {
                    case ConstDefault.SubLayout.SUB_SINGLE_IMAGE:
                        imageSingle.setImageResource(titleList.get(position).getImageList());
                        if (backgroundOfSubListColor != 0) {
                            layoutSingle.setBackgroundColor(backgroundOfSubListColor);
                        }
                        if (lineOfSubListColor != 0) {
                            bottomLineSingle.setBackgroundColor(lineOfSubListColor);
                        }
                        if (visibilityLineOfSubList != 0) {
                            switch (visibilityLineOfSubList) {
                                case 1:
                                    bottomLineSingle.setVisibility(View.INVISIBLE);
                                    break;
                                case 2:
                                    bottomLineSingle.setVisibility(View.GONE);
                                    break;
                            }
                        }
                        break;

                    case ConstDefault.SubLayout.SUB_SINGLE_TEXT:
                        textSingle.setText(titleList.get(position).getName());
                        if (backgroundOfSubListColor != 0) {
                            layoutSingle.setBackgroundColor(backgroundOfSubListColor);
                        }
                        if (lineOfSubListColor != 0) {
                            bottomLineSingle.setBackgroundColor(lineOfSubListColor);
                        }
                        if (visibilityLineOfSubList != 0) {
                            switch (visibilityLineOfSubList) {
                                case 1:
                                    bottomLineSingle.setVisibility(View.INVISIBLE);
                                    break;
                                case 2:
                                    bottomLineSingle.setVisibility(View.GONE);
                                    break;
                            }
                        }
                        if (textOfSubListColor != 0) {
                            textSingle.setTextColor(textOfSubListColor);
                        }
                        break;
                }
            }
        }
    }

    @Override
    public int getItemCount() {
        return titleList.size();
    }

    public class parentHolder extends RecyclerView.ViewHolder implements View.OnClickListener {

        LinearLayout layout;
        TextView title;
        TextView subtitle;
        ImageView image;
        TvIcon iconText;// open close icon
        ImageView iconImage;// open close image
        TvIcon imageIcon;// for image in list
        RecyclerView recyclerView;
        View lineCloseItem;

        public parentHolder(View itemView) {
            super(itemView);

            switch (TypeOfTitleLayout) {

                case ConstDefault.Title.TITLE:

                    layout = (LinearLayout) itemView.findViewById(R.id.layout_title);
                    title = (TextView) itemView.findViewById(R.id.title);
                    iconText = (TvIcon) itemView.findViewById(R.id.icon_text);
                    lineCloseItem = itemView.findViewById(R.id.line_close_item);
                    iconImage = (ImageView) itemView.findViewById(R.id.icon_image);

                    recyclerView = (RecyclerView) itemView.findViewById(R.id.expand_title_subtitle_list);

                    break;

                case ConstDefault.Title.TITLE_ROUNDED_IMAGE:

                    layout = (LinearLayout) itemView.findViewById(R.id.layout_title);
                    title = (TextView) itemView.findViewById(R.id.title);
                    iconText = (TvIcon) itemView.findViewById(R.id.icon_text);
                    image = (ImageView) itemView.findViewById(R.id.image);
                    lineCloseItem = itemView.findViewById(R.id.line_close_item);
                    iconImage = (ImageView) itemView.findViewById(R.id.icon_image);

                    recyclerView = (RecyclerView) itemView.findViewById(R.id.expand_title_subtitle_list);

                    break;

                case ConstDefault.Title.TITLE_RECTANGLE_IMAGE:

                    layout = (LinearLayout) itemView.findViewById(R.id.layout_title);
                    title = (TextView) itemView.findViewById(R.id.title);
                    iconText = (TvIcon) itemView.findViewById(R.id.icon_text);
                    image = (ImageView) itemView.findViewById(R.id.image);
                    lineCloseItem = itemView.findViewById(R.id.line_close_item);
                    iconImage = (ImageView) itemView.findViewById(R.id.icon_image);

                    recyclerView = (RecyclerView) itemView.findViewById(R.id.expand_title_subtitle_list);

                    break;

                case ConstDefault.Title.TITLE_CIRCLE_IMAGE:

                    layout = (LinearLayout) itemView.findViewById(R.id.layout_title);
                    title = (TextView) itemView.findViewById(R.id.title);
                    iconText = (TvIcon) itemView.findViewById(R.id.icon_text);
                    image = (ImageView) itemView.findViewById(R.id.image);
                    lineCloseItem = itemView.findViewById(R.id.line_close_item);
                    iconImage = (ImageView) itemView.findViewById(R.id.icon_image);

                    recyclerView = (RecyclerView) itemView.findViewById(R.id.expand_title_subtitle_list);

                    break;

                case ConstDefault.Title.TITLE_SUBTITLE:

                    layout = (LinearLayout) itemView.findViewById(R.id.layout_title);
                    title = (TextView) itemView.findViewById(R.id.title);
                    subtitle = (TextView) itemView.findViewById(R.id.sub_title);
                    iconText = (TvIcon) itemView.findViewById(R.id.icon_text);
                    lineCloseItem = itemView.findViewById(R.id.line_close_item);
                    iconImage = (ImageView) itemView.findViewById(R.id.icon_image);

                    recyclerView = (RecyclerView) itemView.findViewById(R.id.expand_title_subtitle_list);

                    break;

                case ConstDefault.Title.TITLE_SUBTITLE_ROUNDED_IMAGE:

                    layout = (LinearLayout) itemView.findViewById(R.id.layout_title);
                    title = (TextView) itemView.findViewById(R.id.title);
                    subtitle = (TextView) itemView.findViewById(R.id.sub_title);
                    iconText = (TvIcon) itemView.findViewById(R.id.icon_text);
                    image = (ImageView) itemView.findViewById(R.id.image);
                    lineCloseItem = itemView.findViewById(R.id.line_close_item);
                    iconImage = (ImageView) itemView.findViewById(R.id.icon_image);

                    recyclerView = (RecyclerView) itemView.findViewById(R.id.expand_title_subtitle_list);

                    break;

                case ConstDefault.Title.TITLE_SUBTITLE_RECTANGLE_IMAGE:

                    layout = (LinearLayout) itemView.findViewById(R.id.layout_title);
                    title = (TextView) itemView.findViewById(R.id.title);
                    subtitle = (TextView) itemView.findViewById(R.id.sub_title);
                    iconText = (TvIcon) itemView.findViewById(R.id.icon_text);
                    image = (ImageView) itemView.findViewById(R.id.image);
                    lineCloseItem = itemView.findViewById(R.id.line_close_item);
                    iconImage = (ImageView) itemView.findViewById(R.id.icon_image);

                    recyclerView = (RecyclerView) itemView.findViewById(R.id.expand_title_subtitle_list);

                    break;

                case ConstDefault.Title.TITLE_SUBTITLE_CIRCLE_IMAGE:

                    layout = (LinearLayout) itemView.findViewById(R.id.layout_title);
                    title = (TextView) itemView.findViewById(R.id.title);
                    subtitle = (TextView) itemView.findViewById(R.id.sub_title);
                    iconText = (TvIcon) itemView.findViewById(R.id.icon_text);
                    image = (ImageView) itemView.findViewById(R.id.image);
                    lineCloseItem = itemView.findViewById(R.id.line_close_item);
                    iconImage = (ImageView) itemView.findViewById(R.id.icon_image);

                    recyclerView = (RecyclerView) itemView.findViewById(R.id.expand_title_subtitle_list);

                    break;

                case ConstDefault.Title.TITLE_ICON:

                    layout = (LinearLayout) itemView.findViewById(R.id.layout_title);
                    title = (TextView) itemView.findViewById(R.id.title);
                    iconText = (TvIcon) itemView.findViewById(R.id.icon_text);
                    imageIcon = (TvIcon) itemView.findViewById(R.id.icon);
                    lineCloseItem = itemView.findViewById(R.id.line_close_item);
                    iconImage = (ImageView) itemView.findViewById(R.id.icon_image);

                    recyclerView = (RecyclerView) itemView.findViewById(R.id.expand_title_subtitle_list);

                    break;

                case ConstDefault.Title.TITLE_SUBTITLE_ICON:

                    layout = (LinearLayout) itemView.findViewById(R.id.layout_title);
                    title = (TextView) itemView.findViewById(R.id.title);
                    subtitle = (TextView) itemView.findViewById(R.id.sub_title);
                    iconText = (TvIcon) itemView.findViewById(R.id.icon_text);
                    imageIcon = (TvIcon) itemView.findViewById(R.id.icon);
                    lineCloseItem = itemView.findViewById(R.id.line_close_item);
                    iconImage = (ImageView) itemView.findViewById(R.id.icon_image);

                    recyclerView = (RecyclerView) itemView.findViewById(R.id.expand_title_subtitle_list);

                    break;
            }


            layout.setOnClickListener(this);
        }

        @Override
        public void onClick(View view) {
            titleList.get(getAdapterPosition()).setOpen(true);
            notifyItemChanged(getAdapterPosition());
        }
    }

    public class childHolder extends RecyclerView.ViewHolder implements View.OnClickListener {

        LinearLayout layout;
        TextView title;
        TextView subtitle;
        ImageView image;
        TvIcon iconText;// open close icon
        ImageView iconImage;// open close image
        TvIcon imageIcon;// for image in list
        RecyclerView recyclerView;
        View lineSublistItem;
        View lineCloseItem;

        public childHolder(View itemView) {
            super(itemView);

            switch (TypeOfTitleLayout) {

                case ConstDefault.Title.TITLE:

                    layout = (LinearLayout) itemView.findViewById(R.id.layout_title);
                    title = (TextView) itemView.findViewById(R.id.title);
                    iconText = (TvIcon) itemView.findViewById(R.id.icon_text);
                    lineSublistItem = itemView.findViewById(R.id.line_sublist_item);
                    lineCloseItem = itemView.findViewById(R.id.line_close_item);
                    iconImage = (ImageView) itemView.findViewById(R.id.icon_image);

                    recyclerView = (RecyclerView) itemView.findViewById(R.id.expand_title_subtitle_list);

                    break;

                case ConstDefault.Title.TITLE_ROUNDED_IMAGE:

                    layout = (LinearLayout) itemView.findViewById(R.id.layout_title);
                    title = (TextView) itemView.findViewById(R.id.title);
                    iconText = (TvIcon) itemView.findViewById(R.id.icon_text);
                    image = (ImageView) itemView.findViewById(R.id.image);
                    lineSublistItem = itemView.findViewById(R.id.line_sublist_item);
                    lineCloseItem = itemView.findViewById(R.id.line_close_item);
                    iconImage = (ImageView) itemView.findViewById(R.id.icon_image);

                    recyclerView = (RecyclerView) itemView.findViewById(R.id.expand_title_subtitle_list);

                    break;

                case ConstDefault.Title.TITLE_RECTANGLE_IMAGE:

                    layout = (LinearLayout) itemView.findViewById(R.id.layout_title);
                    title = (TextView) itemView.findViewById(R.id.title);
                    iconText = (TvIcon) itemView.findViewById(R.id.icon_text);
                    image = (ImageView) itemView.findViewById(R.id.image);
                    lineSublistItem = itemView.findViewById(R.id.line_sublist_item);
                    lineCloseItem = itemView.findViewById(R.id.line_close_item);
                    iconImage = (ImageView) itemView.findViewById(R.id.icon_image);

                    recyclerView = (RecyclerView) itemView.findViewById(R.id.expand_title_subtitle_list);

                    break;

                case ConstDefault.Title.TITLE_CIRCLE_IMAGE:

                    layout = (LinearLayout) itemView.findViewById(R.id.layout_title);
                    title = (TextView) itemView.findViewById(R.id.title);
                    iconText = (TvIcon) itemView.findViewById(R.id.icon_text);
                    image = (ImageView) itemView.findViewById(R.id.image);
                    lineSublistItem = itemView.findViewById(R.id.line_sublist_item);
                    lineCloseItem = itemView.findViewById(R.id.line_close_item);
                    iconImage = (ImageView) itemView.findViewById(R.id.icon_image);

                    recyclerView = (RecyclerView) itemView.findViewById(R.id.expand_title_subtitle_list);

                    break;

                case ConstDefault.Title.TITLE_SUBTITLE:

                    layout = (LinearLayout) itemView.findViewById(R.id.layout_title);
                    title = (TextView) itemView.findViewById(R.id.title);
                    subtitle = (TextView) itemView.findViewById(R.id.sub_title);
                    iconText = (TvIcon) itemView.findViewById(R.id.icon_text);
                    lineSublistItem = itemView.findViewById(R.id.line_sublist_item);
                    lineCloseItem = itemView.findViewById(R.id.line_close_item);
                    iconImage = (ImageView) itemView.findViewById(R.id.icon_image);

                    recyclerView = (RecyclerView) itemView.findViewById(R.id.expand_title_subtitle_list);

                    break;

                case ConstDefault.Title.TITLE_SUBTITLE_ROUNDED_IMAGE:

                    layout = (LinearLayout) itemView.findViewById(R.id.layout_title);
                    title = (TextView) itemView.findViewById(R.id.title);
                    subtitle = (TextView) itemView.findViewById(R.id.sub_title);
                    iconText = (TvIcon) itemView.findViewById(R.id.icon_text);
                    image = (ImageView) itemView.findViewById(R.id.image);
                    lineSublistItem = itemView.findViewById(R.id.line_sublist_item);
                    lineCloseItem = itemView.findViewById(R.id.line_close_item);
                    iconImage = (ImageView) itemView.findViewById(R.id.icon_image);

                    recyclerView = (RecyclerView) itemView.findViewById(R.id.expand_title_subtitle_list);

                    break;

                case ConstDefault.Title.TITLE_SUBTITLE_RECTANGLE_IMAGE:

                    layout = (LinearLayout) itemView.findViewById(R.id.layout_title);
                    title = (TextView) itemView.findViewById(R.id.title);
                    subtitle = (TextView) itemView.findViewById(R.id.sub_title);
                    iconText = (TvIcon) itemView.findViewById(R.id.icon_text);
                    image = (ImageView) itemView.findViewById(R.id.image);
                    lineSublistItem = itemView.findViewById(R.id.line_sublist_item);
                    lineCloseItem = itemView.findViewById(R.id.line_close_item);
                    iconImage = (ImageView) itemView.findViewById(R.id.icon_image);

                    recyclerView = (RecyclerView) itemView.findViewById(R.id.expand_title_subtitle_list);

                    break;

                case ConstDefault.Title.TITLE_SUBTITLE_CIRCLE_IMAGE:

                    layout = (LinearLayout) itemView.findViewById(R.id.layout_title);
                    title = (TextView) itemView.findViewById(R.id.title);
                    subtitle = (TextView) itemView.findViewById(R.id.sub_title);
                    iconText = (TvIcon) itemView.findViewById(R.id.icon_text);
                    image = (ImageView) itemView.findViewById(R.id.image);
                    lineSublistItem = itemView.findViewById(R.id.line_sublist_item);
                    lineCloseItem = itemView.findViewById(R.id.line_close_item);
                    iconImage = (ImageView) itemView.findViewById(R.id.icon_image);

                    recyclerView = (RecyclerView) itemView.findViewById(R.id.expand_title_subtitle_list);

                    break;

                case ConstDefault.Title.TITLE_ICON:

                    layout = (LinearLayout) itemView.findViewById(R.id.layout_title);
                    title = (TextView) itemView.findViewById(R.id.title);
                    iconText = (TvIcon) itemView.findViewById(R.id.icon_text);
                    imageIcon = (TvIcon) itemView.findViewById(R.id.icon);
                    lineSublistItem = itemView.findViewById(R.id.line_sublist_item);
                    lineCloseItem = itemView.findViewById(R.id.line_close_item);
                    iconImage = (ImageView) itemView.findViewById(R.id.icon_image);

                    recyclerView = (RecyclerView) itemView.findViewById(R.id.expand_title_subtitle_list);

                    break;

                case ConstDefault.Title.TITLE_SUBTITLE_ICON:

                    layout = (LinearLayout) itemView.findViewById(R.id.layout_title);
                    title = (TextView) itemView.findViewById(R.id.title);
                    subtitle = (TextView) itemView.findViewById(R.id.sub_title);
                    iconText = (TvIcon) itemView.findViewById(R.id.icon_text);
                    imageIcon = (TvIcon) itemView.findViewById(R.id.icon);
                    lineSublistItem = itemView.findViewById(R.id.line_sublist_item);
                    lineCloseItem = itemView.findViewById(R.id.line_close_item);
                    iconImage = (ImageView) itemView.findViewById(R.id.icon_image);

                    recyclerView = (RecyclerView) itemView.findViewById(R.id.expand_title_subtitle_list);

                    break;
            }
            layout.setOnClickListener(this);

        }

        @Override
        public void onClick(View view) {
            titleList.get(getAdapterPosition()).setOpen(false);
            notifyItemChanged(getAdapterPosition());
        }
    }

    private void SubSingleHolder(View itemView) {

        if (subTitleListSize == 0) {

            switch (TypeOfSubLayout) {
                case ConstDefault.SubLayout.SUB_SINGLE_IMAGE:

                    layoutSingle = (LinearLayout) itemView.findViewById(R.id.layout_sub_list);
                    imageSingle = (ImageView) itemView.findViewById(R.id.sub_image);
                    bottomLineSingle = itemView.findViewById(R.id.line_close_item);
                    break;
                case ConstDefault.SubLayout.SUB_SINGLE_TEXT:

                    layoutSingle = (LinearLayout) itemView.findViewById(R.id.layout_sub_list);
                    textSingle = (TextView) itemView.findViewById(R.id.sub_text);
                    bottomLineSingle = itemView.findViewById(R.id.line_close_item);
                    break;
            }
        }

    }

    @Override
    public int getItemViewType(int position) {

        boolean stateOpen = titleList.get(position).isOpen();
        if (!stateOpen) {
            return 0;
        } else {
            return 1;
        }
    }

    private void setLayouts(ViewGroup parent) {

        viewCollapse = LayoutInflater.from(parent.getContext()).inflate(R.layout.collapse, parent, false);
        View viewCollapseParentLayout = LayoutInflater.from(parent.getContext()).inflate(parentLayout, parent, false);

        FrameLayout frameLayout = (FrameLayout) viewCollapse.findViewById(R.id.collaps_layout);
        frameLayout.addView(viewCollapseParentLayout);

        viewExpand = LayoutInflater.from(parent.getContext()).inflate(R.layout.expand, parent, false);
        View viewExpandParentLayout = LayoutInflater.from(parent.getContext()).inflate(parentLayout, parent, false);
        View viewExpandChildLayout = LayoutInflater.from(parent.getContext()).inflate(childLayout, parent, false);

        FrameLayout frameLayout1 = (FrameLayout) viewExpand.findViewById(R.id.expand_layout);
        FrameLayout frameLayout2 = (FrameLayout) viewExpand.findViewById(R.id.expand_sublist_layout);
        frameLayout1.addView(viewExpandParentLayout);

        if (subTitleListSize == 0) {
            viewExpandSingleMode = LayoutInflater.from(parent.getContext()).inflate(subListItemLayout, parent, false);
            frameLayout2.addView(viewExpandSingleMode);
        } else {
            frameLayout2.addView(viewExpandChildLayout);
        }
    }

    private void setAttrs() {

        for (int i = 0; i < attrList.size(); i++) {
            switch (i) {
                case 0:
                    titleColor = attrList.get(i).getValue();
                    break;
                case 1:
                    subtitleColor = attrList.get(i).getValue();
                    break;
                case 2:
                    iconColor = attrList.get(i).getValue();
                    break;
                case 3:
                    lineOfTitleListColor = attrList.get(i).getValue();
                    break;
                case 4:
                    lineOfSubListColor = attrList.get(i).getValue();
                    break;
                case 5:
                    textOfSubListColor = attrList.get(i).getValue();
                    break;
                case 6:
                    backgroundOfSubListColor = attrList.get(i).getValue();
                    break;
                case 7:
                    backgroundOfTitleListColor = attrList.get(i).getValue();
                    break;
                case 8:
                    visibilityLineOfTitleList = attrList.get(i).getValue();
                    break;
                case 9:
                    visibilityLineOfSubList = attrList.get(i).getValue();
                    break;
                case 10:
                    imageIconColor = attrList.get(i).getValue();
                    break;
            }
        }
    }

    private void setDataParent(parentHolder parentHolder, int position) {

        switch (TypeOfTitleLayout) {

            case ConstDefault.Title.TITLE:
                parentHolder.title.setText(titleList.get(position).getName());

                switch (statusIcon) {
                    case 0:
                        parentHolder.iconText.setText(iconCloseFont);
                        parentHolder.iconText.setTextColor(iconColor);

                        parentHolder.iconImage.setVisibility(View.GONE);
                        break;
                    case 1:
                        parentHolder.iconImage.setImageResource(imageClose);

                        parentHolder.iconText.setVisibility(View.GONE);
                        break;
                }

                if (titleColor != 0) {
                    parentHolder.title.setTextColor(titleColor);
                }
                if (iconColor != 0) {
                    parentHolder.iconText.setTextColor(iconColor);
                }
                if (lineOfTitleListColor != 0) {
                    parentHolder.lineCloseItem.setBackgroundColor(lineOfTitleListColor);
                }
                if (backgroundOfTitleListColor != 0) {
                    parentHolder.layout.setBackgroundColor(backgroundOfTitleListColor);
                }
                if (visibilityLineOfTitleList != 0) {
                    switch (visibilityLineOfTitleList) {
                        case 1:
                            parentHolder.lineCloseItem.setVisibility(View.INVISIBLE);
                            break;
                        case 2:
                            parentHolder.lineCloseItem.setVisibility(View.GONE);
                            break;
                    }
                }
                break;

            case ConstDefault.Title.TITLE_ROUNDED_IMAGE:
                parentHolder.title.setText(titleList.get(position).getName());
                parentHolder.image.setImageResource(titleList.get(position).getImageList());

                switch (statusIcon) {
                    case 0:
                        parentHolder.iconText.setText(iconCloseFont);
                        parentHolder.iconText.setTextColor(iconColor);

                        parentHolder.iconImage.setVisibility(View.GONE);
                        break;
                    case 1:
                        parentHolder.iconImage.setImageResource(imageClose);

                        parentHolder.iconText.setVisibility(View.GONE);
                        break;
                }

                if (titleColor != 0) {
                    parentHolder.title.setTextColor(titleColor);
                }
                if (iconColor != 0) {
                    parentHolder.iconText.setTextColor(iconColor);
                }
                if (lineOfTitleListColor != 0) {
                    parentHolder.lineCloseItem.setBackgroundColor(lineOfTitleListColor);
                }
                if (backgroundOfTitleListColor != 0) {
                    parentHolder.layout.setBackgroundColor(backgroundOfTitleListColor);
                }
                if (visibilityLineOfTitleList != 0) {
                    switch (visibilityLineOfTitleList) {
                        case 1:
                            parentHolder.lineCloseItem.setVisibility(View.INVISIBLE);
                            break;
                        case 2:
                            parentHolder.lineCloseItem.setVisibility(View.GONE);
                            break;
                    }
                }
                break;

            case ConstDefault.Title.TITLE_RECTANGLE_IMAGE:
                parentHolder.title.setText(titleList.get(position).getName());
                parentHolder.image.setImageResource(titleList.get(position).getImageList());

                switch (statusIcon) {
                    case 0:
                        parentHolder.iconText.setText(iconCloseFont);
                        parentHolder.iconText.setTextColor(iconColor);

                        parentHolder.iconImage.setVisibility(View.GONE);
                        break;
                    case 1:
                        parentHolder.iconImage.setImageResource(imageClose);

                        parentHolder.iconText.setVisibility(View.GONE);
                        break;
                }

                if (titleColor != 0) {
                    parentHolder.title.setTextColor(titleColor);
                }
                if (iconColor != 0) {
                    parentHolder.iconText.setTextColor(iconColor);
                }
                if (lineOfTitleListColor != 0) {
                    parentHolder.lineCloseItem.setBackgroundColor(lineOfTitleListColor);
                }
                if (backgroundOfTitleListColor != 0) {
                    parentHolder.layout.setBackgroundColor(backgroundOfTitleListColor);
                }
                if (visibilityLineOfTitleList != 0) {
                    switch (visibilityLineOfTitleList) {
                        case 1:
                            parentHolder.lineCloseItem.setVisibility(View.INVISIBLE);
                            break;
                        case 2:
                            parentHolder.lineCloseItem.setVisibility(View.GONE);
                            break;
                    }
                }

                break;

            case ConstDefault.Title.TITLE_CIRCLE_IMAGE:
                parentHolder.title.setText(titleList.get(position).getName());
                parentHolder.image.setImageResource(titleList.get(position).getImageList());

                switch (statusIcon) {
                    case 0:
                        parentHolder.iconText.setText(iconCloseFont);
                        parentHolder.iconText.setTextColor(iconColor);

                        parentHolder.iconImage.setVisibility(View.GONE);
                        break;
                    case 1:
                        parentHolder.iconImage.setImageResource(imageClose);

                        parentHolder.iconText.setVisibility(View.GONE);
                        break;
                }

                if (titleColor != 0) {
                    parentHolder.title.setTextColor(titleColor);
                }
                if (iconColor != 0) {
                    parentHolder.iconText.setTextColor(iconColor);
                }
                if (lineOfTitleListColor != 0) {
                    parentHolder.lineCloseItem.setBackgroundColor(lineOfTitleListColor);
                }
                if (backgroundOfTitleListColor != 0) {
                    parentHolder.layout.setBackgroundColor(backgroundOfTitleListColor);
                }
                if (visibilityLineOfTitleList != 0) {
                    switch (visibilityLineOfTitleList) {
                        case 1:
                            parentHolder.lineCloseItem.setVisibility(View.INVISIBLE);
                            break;
                        case 2:
                            parentHolder.lineCloseItem.setVisibility(View.GONE);
                            break;
                    }
                }

                break;

            case ConstDefault.Title.TITLE_SUBTITLE:
                parentHolder.title.setText(titleList.get(position).getName());
                parentHolder.subtitle.setText(titleList.get(position).getSubTitleList());

                switch (statusIcon) {
                    case 0:
                        parentHolder.iconText.setText(iconCloseFont);
                        parentHolder.iconText.setTextColor(iconColor);

                        parentHolder.iconImage.setVisibility(View.GONE);
                        break;
                    case 1:
                        parentHolder.iconImage.setImageResource(imageClose);

                        parentHolder.iconText.setVisibility(View.GONE);
                        break;
                }

                if (titleColor != 0) {
                    parentHolder.title.setTextColor(titleColor);
                }
                if (iconColor != 0) {
                    parentHolder.iconText.setTextColor(iconColor);
                }
                if (lineOfTitleListColor != 0) {
                    parentHolder.lineCloseItem.setBackgroundColor(lineOfTitleListColor);
                }
                if (backgroundOfTitleListColor != 0) {
                    parentHolder.layout.setBackgroundColor(backgroundOfTitleListColor);
                }
                if (visibilityLineOfTitleList != 0) {
                    switch (visibilityLineOfTitleList) {
                        case 1:
                            parentHolder.lineCloseItem.setVisibility(View.INVISIBLE);
                            break;
                        case 2:
                            parentHolder.lineCloseItem.setVisibility(View.GONE);
                            break;
                    }
                }

                break;

            case ConstDefault.Title.TITLE_SUBTITLE_ROUNDED_IMAGE:
                parentHolder.title.setText(titleList.get(position).getName());
                parentHolder.subtitle.setText(titleList.get(position).getSubTitleList());
                parentHolder.image.setImageResource(titleList.get(position).getImageList());

                switch (statusIcon) {
                    case 0:
                        parentHolder.iconText.setText(iconCloseFont);
                        parentHolder.iconText.setTextColor(iconColor);

                        parentHolder.iconImage.setVisibility(View.GONE);
                        break;
                    case 1:
                        parentHolder.iconImage.setImageResource(imageClose);

                        parentHolder.iconText.setVisibility(View.GONE);
                        break;
                }

                if (titleColor != 0) {
                    parentHolder.title.setTextColor(titleColor);
                }
                if (iconColor != 0) {
                    parentHolder.iconText.setTextColor(iconColor);
                }
                if (subtitleColor != 0) {
                    parentHolder.subtitle.setTextColor(subtitleColor);
                }
                if (lineOfTitleListColor != 0) {
                    parentHolder.lineCloseItem.setBackgroundColor(lineOfTitleListColor);
                }
                if (backgroundOfTitleListColor != 0) {
                    parentHolder.layout.setBackgroundColor(backgroundOfTitleListColor);
                }
                if (visibilityLineOfTitleList != 0) {
                    switch (visibilityLineOfTitleList) {
                        case 1:
                            parentHolder.lineCloseItem.setVisibility(View.INVISIBLE);
                            break;
                        case 2:
                            parentHolder.lineCloseItem.setVisibility(View.GONE);
                            break;
                    }
                }


                break;

            case ConstDefault.Title.TITLE_SUBTITLE_RECTANGLE_IMAGE:
                parentHolder.title.setText(titleList.get(position).getName());
                parentHolder.subtitle.setText(titleList.get(position).getSubTitleList());
                parentHolder.image.setImageResource(titleList.get(position).getImageList());

                switch (statusIcon) {
                    case 0:
                        parentHolder.iconText.setText(iconCloseFont);
                        parentHolder.iconText.setTextColor(iconColor);

                        parentHolder.iconImage.setVisibility(View.GONE);
                        break;
                    case 1:
                        parentHolder.iconImage.setImageResource(imageClose);

                        parentHolder.iconText.setVisibility(View.GONE);
                        break;
                }

                if (titleColor != 0) {
                    parentHolder.title.setTextColor(titleColor);
                }
                if (iconColor != 0) {
                    parentHolder.iconText.setTextColor(iconColor);
                }
                if (subtitleColor != 0) {
                    parentHolder.subtitle.setTextColor(subtitleColor);
                }
                if (lineOfTitleListColor != 0) {
                    parentHolder.lineCloseItem.setBackgroundColor(lineOfTitleListColor);
                }
                if (backgroundOfTitleListColor != 0) {
                    parentHolder.layout.setBackgroundColor(backgroundOfTitleListColor);
                }
                if (visibilityLineOfTitleList != 0) {
                    switch (visibilityLineOfTitleList) {
                        case 1:
                            parentHolder.lineCloseItem.setVisibility(View.INVISIBLE);
                            break;
                        case 2:
                            parentHolder.lineCloseItem.setVisibility(View.GONE);
                            break;
                    }
                }

                break;

            case ConstDefault.Title.TITLE_SUBTITLE_CIRCLE_IMAGE:
                parentHolder.title.setText(titleList.get(position).getName());
                parentHolder.subtitle.setText(titleList.get(position).getSubTitleList());
                parentHolder.image.setImageResource(titleList.get(position).getImageList());

                switch (statusIcon) {
                    case 0:
                        parentHolder.iconText.setText(iconCloseFont);
                        parentHolder.iconText.setTextColor(iconColor);

                        parentHolder.iconImage.setVisibility(View.GONE);
                        break;
                    case 1:
                        parentHolder.iconImage.setImageResource(imageClose);

                        parentHolder.iconText.setVisibility(View.GONE);
                        break;
                }

                if (titleColor != 0) {
                    parentHolder.title.setTextColor(titleColor);
                }
                if (iconColor != 0) {
                    parentHolder.iconText.setTextColor(iconColor);
                }
                if (subtitleColor != 0) {
                    parentHolder.subtitle.setTextColor(subtitleColor);
                }
                if (lineOfTitleListColor != 0) {
                    parentHolder.lineCloseItem.setBackgroundColor(lineOfTitleListColor);
                }
                if (backgroundOfTitleListColor != 0) {
                    parentHolder.layout.setBackgroundColor(backgroundOfTitleListColor);
                }
                if (visibilityLineOfTitleList != 0) {
                    switch (visibilityLineOfTitleList) {
                        case 1:
                            parentHolder.lineCloseItem.setVisibility(View.INVISIBLE);
                            break;
                        case 2:
                            parentHolder.lineCloseItem.setVisibility(View.GONE);
                            break;
                    }
                }

                break;

            case ConstDefault.Title.TITLE_ICON:
                parentHolder.title.setText(titleList.get(position).getName());
                parentHolder.imageIcon.setText(titleList.get(position).getIcon());
                parentHolder.imageIcon.setTextColor(imageIconColor);

                switch (statusIcon) {
                    case 0:
                        parentHolder.iconText.setText(iconCloseFont);
                        parentHolder.iconText.setTextColor(iconColor);

                        parentHolder.iconImage.setVisibility(View.GONE);
                        break;
                    case 1:
                        parentHolder.iconImage.setImageResource(imageClose);

                        parentHolder.iconText.setVisibility(View.GONE);
                        break;
                }

                if (titleColor != 0) {
                    parentHolder.title.setTextColor(titleColor);
                }
                if (iconColor != 0) {
                    parentHolder.iconText.setTextColor(iconColor);
                }
                if (lineOfTitleListColor != 0) {
                    parentHolder.lineCloseItem.setBackgroundColor(lineOfTitleListColor);
                }
                if (backgroundOfTitleListColor != 0) {
                    parentHolder.layout.setBackgroundColor(backgroundOfTitleListColor);
                }
                if (visibilityLineOfTitleList != 0) {
                    switch (visibilityLineOfTitleList) {
                        case 1:
                            parentHolder.lineCloseItem.setVisibility(View.INVISIBLE);
                            break;
                        case 2:
                            parentHolder.lineCloseItem.setVisibility(View.GONE);
                            break;
                    }
                }

                break;

            case ConstDefault.Title.TITLE_SUBTITLE_ICON:
                parentHolder.title.setText(titleList.get(position).getName());
                parentHolder.subtitle.setText(titleList.get(position).getSubTitleList());
                parentHolder.imageIcon.setText(titleList.get(position).getIcon());
                parentHolder.imageIcon.setTextColor(imageIconColor);

                switch (statusIcon) {
                    case 0:
                        parentHolder.iconText.setText(iconCloseFont);
                        parentHolder.iconText.setTextColor(iconColor);

                        parentHolder.iconImage.setVisibility(View.GONE);
                        break;
                    case 1:
                        parentHolder.iconImage.setImageResource(imageClose);

                        parentHolder.iconText.setVisibility(View.GONE);
                        break;
                }

                if (titleColor != 0) {
                    parentHolder.title.setTextColor(titleColor);
                }
                if (iconColor != 0) {
                    parentHolder.iconText.setTextColor(iconColor);
                }
                if (subtitleColor != 0) {
                    parentHolder.subtitle.setTextColor(subtitleColor);
                }
                if (lineOfTitleListColor != 0) {
                    parentHolder.lineCloseItem.setBackgroundColor(lineOfTitleListColor);
                }
                if (backgroundOfTitleListColor != 0) {
                    parentHolder.layout.setBackgroundColor(backgroundOfTitleListColor);
                }
                if (visibilityLineOfTitleList != 0) {
                    switch (visibilityLineOfTitleList) {
                        case 1:
                            parentHolder.lineCloseItem.setVisibility(View.INVISIBLE);
                            break;
                        case 2:
                            parentHolder.lineCloseItem.setVisibility(View.GONE);
                            break;
                    }
                }

                break;
        }
    }

    private void setDataChild(childHolder childHolder, int position) {

        switch (TypeOfTitleLayout) {

            case ConstDefault.Title.TITLE:
                childHolder.title.setText(titleList.get(position).getName());

                switch (statusIcon) {
                    case 0:
                        childHolder.iconText.setText(iconOpenFont);
                        childHolder.iconText.setTextColor(iconColor);

                        childHolder.iconImage.setVisibility(View.GONE);
                        break;
                    case 1:
                        childHolder.iconImage.setImageResource(imageOpen);

                        childHolder.iconText.setVisibility(View.GONE);
                        break;
                }

                //set header items
                if (titleColor != 0) {
                    childHolder.title.setTextColor(titleColor);
                }
                if (iconColor != 0) {
                    childHolder.iconText.setTextColor(iconColor);
                }
                if (lineOfTitleListColor != 0) {
                    childHolder.lineCloseItem.setBackgroundColor(lineOfTitleListColor);
                }
                if (lineOfSubListColor != 0) {
                    childHolder.lineSublistItem.setBackgroundColor(lineOfSubListColor);
                }
                if (backgroundOfTitleListColor != 0) {
                    childHolder.layout.setBackgroundColor(backgroundOfTitleListColor);
                }
                if (visibilityLineOfTitleList != 0) {
                    switch (visibilityLineOfTitleList) {
                        case 1:
                            childHolder.lineCloseItem.setVisibility(View.INVISIBLE);
                            break;
                        case 2:
                            childHolder.lineCloseItem.setVisibility(View.GONE);
                            break;
                    }
                }

                break;

            case ConstDefault.Title.TITLE_ROUNDED_IMAGE:
                childHolder.title.setText(titleList.get(position).getName());
                childHolder.image.setImageResource(titleList.get(position).getImageList());

                switch (statusIcon) {
                    case 0:
                        childHolder.iconText.setText(iconOpenFont);
                        childHolder.iconText.setTextColor(iconColor);

                        childHolder.iconImage.setVisibility(View.GONE);
                        break;
                    case 1:
                        childHolder.iconImage.setImageResource(imageOpen);

                        childHolder.iconText.setVisibility(View.GONE);
                        break;
                }

                //set header items
                if (titleColor != 0) {
                    childHolder.title.setTextColor(titleColor);
                }
                if (iconColor != 0) {
                    childHolder.iconText.setTextColor(iconColor);
                }
                if (lineOfTitleListColor != 0) {
                    childHolder.lineCloseItem.setBackgroundColor(lineOfTitleListColor);
                }
                if (lineOfSubListColor != 0) {
                    childHolder.lineSublistItem.setBackgroundColor(lineOfSubListColor);
                }
                if (backgroundOfTitleListColor != 0) {
                    childHolder.layout.setBackgroundColor(backgroundOfTitleListColor);
                }
                if (visibilityLineOfTitleList != 0) {
                    switch (visibilityLineOfTitleList) {
                        case 1:
                            childHolder.lineCloseItem.setVisibility(View.INVISIBLE);
                            break;
                        case 2:
                            childHolder.lineCloseItem.setVisibility(View.GONE);
                            break;
                    }
                }

                break;

            case ConstDefault.Title.TITLE_RECTANGLE_IMAGE:
                childHolder.title.setText(titleList.get(position).getName());
                childHolder.image.setImageResource(titleList.get(position).getImageList());

                switch (statusIcon) {
                    case 0:
                        childHolder.iconText.setText(iconOpenFont);
                        childHolder.iconText.setTextColor(iconColor);

                        childHolder.iconImage.setVisibility(View.GONE);
                        break;
                    case 1:
                        childHolder.iconImage.setImageResource(imageOpen);

                        childHolder.iconText.setVisibility(View.GONE);
                        break;
                }

                //set header items
                if (titleColor != 0) {
                    childHolder.title.setTextColor(titleColor);
                }
                if (iconColor != 0) {
                    childHolder.iconText.setTextColor(iconColor);
                }
                if (lineOfTitleListColor != 0) {
                    childHolder.lineCloseItem.setBackgroundColor(lineOfTitleListColor);
                }
                if (lineOfSubListColor != 0) {
                    childHolder.lineSublistItem.setBackgroundColor(lineOfSubListColor);
                }
                if (backgroundOfTitleListColor != 0) {
                    childHolder.layout.setBackgroundColor(backgroundOfTitleListColor);
                }
                if (visibilityLineOfTitleList != 0) {
                    switch (visibilityLineOfTitleList) {
                        case 1:
                            childHolder.lineCloseItem.setVisibility(View.INVISIBLE);
                            break;
                        case 2:
                            childHolder.lineCloseItem.setVisibility(View.GONE);
                            break;
                    }
                }

                break;

            case ConstDefault.Title.TITLE_CIRCLE_IMAGE:
                childHolder.title.setText(titleList.get(position).getName());
                childHolder.image.setImageResource(titleList.get(position).getImageList());

                switch (statusIcon) {
                    case 0:
                        childHolder.iconText.setText(iconOpenFont);
                        childHolder.iconText.setTextColor(iconColor);

                        childHolder.iconImage.setVisibility(View.GONE);
                        break;
                    case 1:
                        childHolder.iconImage.setImageResource(imageOpen);

                        childHolder.iconText.setVisibility(View.GONE);
                        break;
                }

                //set header items
                if (titleColor != 0) {
                    childHolder.title.setTextColor(titleColor);
                }
                if (iconColor != 0) {
                    childHolder.iconText.setTextColor(iconColor);
                }
                if (lineOfTitleListColor != 0) {
                    childHolder.lineCloseItem.setBackgroundColor(lineOfTitleListColor);
                }
                if (lineOfSubListColor != 0) {
                    childHolder.lineSublistItem.setBackgroundColor(lineOfSubListColor);
                }
                if (backgroundOfTitleListColor != 0) {
                    childHolder.layout.setBackgroundColor(backgroundOfTitleListColor);
                }
                if (visibilityLineOfTitleList != 0) {
                    switch (visibilityLineOfTitleList) {
                        case 1:
                            childHolder.lineCloseItem.setVisibility(View.INVISIBLE);
                            break;
                        case 2:
                            childHolder.lineCloseItem.setVisibility(View.GONE);
                            break;
                    }
                }

                break;

            case ConstDefault.Title.TITLE_SUBTITLE:
                childHolder.title.setText(titleList.get(position).getName());
                childHolder.subtitle.setText(titleList.get(position).getSubTitleList());

                switch (statusIcon) {
                    case 0:
                        childHolder.iconText.setText(iconOpenFont);
                        childHolder.iconText.setTextColor(iconColor);

                        childHolder.iconImage.setVisibility(View.GONE);
                        break;
                    case 1:
                        childHolder.iconImage.setImageResource(imageOpen);

                        childHolder.iconText.setVisibility(View.GONE);
                        break;
                }

                //set header items
                if (titleColor != 0) {
                    childHolder.title.setTextColor(titleColor);
                }
                if (iconColor != 0) {
                    childHolder.iconText.setTextColor(iconColor);
                }
                if (lineOfTitleListColor != 0) {
                    childHolder.lineCloseItem.setBackgroundColor(lineOfTitleListColor);
                }
                if (lineOfSubListColor != 0) {
                    childHolder.lineSublistItem.setBackgroundColor(lineOfSubListColor);
                }
                if (backgroundOfTitleListColor != 0) {
                    childHolder.layout.setBackgroundColor(backgroundOfTitleListColor);
                }
                if (visibilityLineOfTitleList != 0) {
                    switch (visibilityLineOfTitleList) {
                        case 1:
                            childHolder.lineCloseItem.setVisibility(View.INVISIBLE);
                            break;
                        case 2:
                            childHolder.lineCloseItem.setVisibility(View.GONE);
                            break;
                    }
                }

                break;

            case ConstDefault.Title.TITLE_SUBTITLE_ROUNDED_IMAGE:
                childHolder.title.setText(titleList.get(position).getName());
                childHolder.subtitle.setText(titleList.get(position).getSubTitleList());
                childHolder.image.setImageResource(titleList.get(position).getImageList());

                switch (statusIcon) {
                    case 0:
                        childHolder.iconText.setText(iconOpenFont);
                        childHolder.iconText.setTextColor(iconColor);

                        childHolder.iconImage.setVisibility(View.GONE);
                        break;
                    case 1:
                        childHolder.iconImage.setImageResource(imageOpen);

                        childHolder.iconText.setVisibility(View.GONE);
                        break;
                }

                //set header items
                if (titleColor != 0) {
                    childHolder.title.setTextColor(titleColor);
                }
                if (iconColor != 0) {
                    childHolder.iconText.setTextColor(iconColor);
                }
                if (subtitleColor != 0) {
                    childHolder.subtitle.setTextColor(subtitleColor);
                }
                if (lineOfTitleListColor != 0) {
                    childHolder.lineCloseItem.setBackgroundColor(lineOfTitleListColor);
                }
                if (lineOfSubListColor != 0) {
                    childHolder.lineSublistItem.setBackgroundColor(lineOfSubListColor);
                }
                if (backgroundOfTitleListColor != 0) {
                    childHolder.layout.setBackgroundColor(backgroundOfTitleListColor);
                }
                if (visibilityLineOfTitleList != 0) {
                    switch (visibilityLineOfTitleList) {
                        case 1:
                            childHolder.lineCloseItem.setVisibility(View.INVISIBLE);
                            break;
                        case 2:
                            childHolder.lineCloseItem.setVisibility(View.GONE);
                            break;
                    }
                }

                break;

            case ConstDefault.Title.TITLE_SUBTITLE_RECTANGLE_IMAGE:
                childHolder.title.setText(titleList.get(position).getName());
                childHolder.subtitle.setText(titleList.get(position).getSubTitleList());
                childHolder.image.setImageResource(titleList.get(position).getImageList());

                switch (statusIcon) {
                    case 0:
                        childHolder.iconText.setText(iconOpenFont);
                        childHolder.iconText.setTextColor(iconColor);

                        childHolder.iconImage.setVisibility(View.GONE);
                        break;
                    case 1:
                        childHolder.iconImage.setImageResource(imageOpen);

                        childHolder.iconText.setVisibility(View.GONE);
                        break;
                }

                //set header items
                if (titleColor != 0) {
                    childHolder.title.setTextColor(titleColor);
                }
                if (iconColor != 0) {
                    childHolder.iconText.setTextColor(iconColor);
                }
                if (subtitleColor != 0) {
                    childHolder.subtitle.setTextColor(subtitleColor);
                }
                if (lineOfTitleListColor != 0) {
                    childHolder.lineCloseItem.setBackgroundColor(lineOfTitleListColor);
                }
                if (lineOfSubListColor != 0) {
                    childHolder.lineSublistItem.setBackgroundColor(lineOfSubListColor);
                }
                if (backgroundOfTitleListColor != 0) {
                    childHolder.layout.setBackgroundColor(backgroundOfTitleListColor);
                }
                if (visibilityLineOfTitleList != 0) {
                    switch (visibilityLineOfTitleList) {
                        case 1:
                            childHolder.lineCloseItem.setVisibility(View.INVISIBLE);
                            break;
                        case 2:
                            childHolder.lineCloseItem.setVisibility(View.GONE);
                            break;
                    }
                }

                break;

            case ConstDefault.Title.TITLE_SUBTITLE_CIRCLE_IMAGE:
                childHolder.title.setText(titleList.get(position).getName());
                childHolder.subtitle.setText(titleList.get(position).getSubTitleList());
                childHolder.image.setImageResource(titleList.get(position).getImageList());

                switch (statusIcon) {
                    case 0:
                        childHolder.iconText.setText(iconOpenFont);
                        childHolder.iconText.setTextColor(iconColor);

                        childHolder.iconImage.setVisibility(View.GONE);
                        break;
                    case 1:
                        childHolder.iconImage.setImageResource(imageOpen);

                        childHolder.iconText.setVisibility(View.GONE);
                        break;
                }

                //set header items
                if (titleColor != 0) {
                    childHolder.title.setTextColor(titleColor);
                }
                if (iconColor != 0) {
                    childHolder.iconText.setTextColor(iconColor);
                }
                if (subtitleColor != 0) {
                    childHolder.subtitle.setTextColor(subtitleColor);
                }
                if (lineOfTitleListColor != 0) {
                    childHolder.lineCloseItem.setBackgroundColor(lineOfTitleListColor);
                }
                if (lineOfSubListColor != 0) {
                    childHolder.lineSublistItem.setBackgroundColor(lineOfSubListColor);
                }
                if (backgroundOfTitleListColor != 0) {
                    childHolder.layout.setBackgroundColor(backgroundOfTitleListColor);
                }
                if (visibilityLineOfTitleList != 0) {
                    switch (visibilityLineOfTitleList) {
                        case 1:
                            childHolder.lineCloseItem.setVisibility(View.INVISIBLE);
                            break;
                        case 2:
                            childHolder.lineCloseItem.setVisibility(View.GONE);
                            break;
                    }
                }

                break;

            case ConstDefault.Title.TITLE_ICON:
                childHolder.title.setText(titleList.get(position).getName());
                childHolder.imageIcon.setText(titleList.get(position).getIcon());

                switch (statusIcon) {
                    case 0:
                        childHolder.iconText.setText(iconOpenFont);
                        childHolder.iconText.setTextColor(iconColor);

                        childHolder.iconImage.setVisibility(View.GONE);
                        break;
                    case 1:
                        childHolder.iconImage.setImageResource(imageOpen);

                        childHolder.iconText.setVisibility(View.GONE);
                        break;
                }

                //set header items
                if (titleColor != 0) {
                    childHolder.title.setTextColor(titleColor);
                }
                if (iconColor != 0) {
                    childHolder.iconText.setTextColor(iconColor);
                }
                if (lineOfTitleListColor != 0) {
                    childHolder.lineCloseItem.setBackgroundColor(lineOfTitleListColor);
                }
                if (lineOfSubListColor != 0) {
                    childHolder.lineSublistItem.setBackgroundColor(lineOfSubListColor);
                }
                if (backgroundOfTitleListColor != 0) {
                    childHolder.layout.setBackgroundColor(backgroundOfTitleListColor);
                }
                if (visibilityLineOfTitleList != 0) {
                    switch (visibilityLineOfTitleList) {
                        case 1:
                            childHolder.lineCloseItem.setVisibility(View.INVISIBLE);
                            break;
                        case 2:
                            childHolder.lineCloseItem.setVisibility(View.GONE);
                            break;
                    }
                }

                break;

            case ConstDefault.Title.TITLE_SUBTITLE_ICON:
                childHolder.title.setText(titleList.get(position).getName());
                childHolder.subtitle.setText(titleList.get(position).getSubTitleList());
                childHolder.imageIcon.setText(titleList.get(position).getIcon());
                childHolder.imageIcon.setTextColor(imageIconColor);

                switch (statusIcon) {
                    case 0:
                        childHolder.iconText.setText(iconOpenFont);
                        childHolder.iconText.setTextColor(iconColor);

                        childHolder.iconImage.setVisibility(View.GONE);
                        break;
                    case 1:
                        childHolder.iconImage.setImageResource(imageOpen);

                        childHolder.iconText.setVisibility(View.GONE);
                        break;
                }

                //set header items
                if (titleColor != 0) {
                    childHolder.title.setTextColor(titleColor);
                }
                if (iconColor != 0) {
                    childHolder.iconText.setTextColor(iconColor);
                }
                if (subtitleColor != 0) {
                    childHolder.subtitle.setTextColor(subtitleColor);
                }
                if (lineOfTitleListColor != 0) {
                    childHolder.lineCloseItem.setBackgroundColor(lineOfTitleListColor);
                }
                if (lineOfSubListColor != 0) {
                    childHolder.lineSublistItem.setBackgroundColor(lineOfSubListColor);
                }
                if (backgroundOfTitleListColor != 0) {
                    childHolder.layout.setBackgroundColor(backgroundOfTitleListColor);
                }
                if (visibilityLineOfTitleList != 0) {
                    switch (visibilityLineOfTitleList) {
                        case 1:
                            childHolder.lineCloseItem.setVisibility(View.INVISIBLE);
                            break;
                        case 2:
                            childHolder.lineCloseItem.setVisibility(View.GONE);
                            break;
                    }
                }

                break;
        }
    }
}
