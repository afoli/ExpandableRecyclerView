package ir.thebigbang.expandablerecyclerviewlibrary;

import android.content.Context;
import android.content.res.TypedArray;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.AttributeSet;
import android.view.View;
import android.widget.LinearLayout;

import java.util.ArrayList;
import java.util.List;

import ir.thebigbang.expandablerecyclerviewlibrary.adapter.ParentListAdapter;
import ir.thebigbang.expandablerecyclerviewlibrary.models.AttrItem;
import ir.thebigbang.expandablerecyclerviewlibrary.models.ChildItem;
import ir.thebigbang.expandablerecyclerviewlibrary.models.TitleItem;
import ir.thebigbang.expandablerecyclerviewlibrary.processes.OnRecyclerItemClickListener;

public class RecyclerViewCustom extends LinearLayout {

    private OnRecyclerItemClickListener SubItemClickListener;

    private List<TitleItem> titleList = null;
    private List<ChildItem> subTitleList = null;
    private Context mContext;
    private String TypeOfTitleLayout = ConstDefault.Title.TITLE;
    private String TypeOfSubLayout = ConstDefault.SubLayout.SUB_TEXT_LIST;

    private int parentLayout;
    private int childLayout;
    private int subListItemLayout;

    private AttributeSet attrs;

    private List<AttrItem> attrList;

    private int iconClose = R.string.icon_down;
    private int iconOpen = R.string.icon_up;

    private int imageClose;
    private int imageOpen;

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

    private int subTitleListSize = 0;

    private int grid = 0;

    public RecyclerViewCustom(Context context) {
        super(context);
        this.mContext = context;
        initialize(mContext);
    }

    public RecyclerViewCustom(Context context, AttributeSet attrs) {
        super(context, attrs);
        this.mContext = context;
        this.attrs = attrs;

        TypedArray a = mContext.obtainStyledAttributes(attrs, R.styleable.RecyclerViewCustom);
        setAttrsInView(a);

        initialize(mContext);
    }

    public RecyclerViewCustom(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        this.mContext = context;
        this.attrs = attrs;

        TypedArray a = mContext.obtainStyledAttributes(attrs, R.styleable.RecyclerViewCustom);
        setAttrsInView(a);

        initialize(mContext);
    }

    public RecyclerViewCustom(Context context, AttributeSet attrs, int defStyleAttr, int defStyleRes) {
        super(context, attrs, defStyleAttr, defStyleRes);
        this.mContext = context;
        this.attrs = attrs;

        TypedArray a = mContext.obtainStyledAttributes(attrs, R.styleable.RecyclerViewCustom);
        setAttrsInView(a);

        initialize(mContext);
    }

    private void initialize(final Context context) {
        inflate(context, R.layout.recycler_view_custom, this);

        RecyclerView recyclerView = (RecyclerView) findViewById(R.id.recycler_view);
        recyclerView.setLayoutManager(new LinearLayoutManager(context));

        setLayoutForTitle();
        if (subTitleList == null) {
            subTitleListSize = 0;
        } else {
            subTitleListSize = subTitleList.size();
        }

        if (getImageClose() == 0) {

            ParentListAdapter parentListAdapter = new ParentListAdapter(getTitleList(),
                    getSubTitleList(), context, parentLayout,
                    childLayout, subListItemLayout, getTypeOfTitleLayout(),
                    getTypeOfSubLayout(), attrList,
                    getIconClose(), getIconOpen(), getImageClose(),
                    getImageOpen(), 0, subTitleListSize, grid, new OnRecyclerItemClickListener() {
                @Override
                public void onClick(View v, int pos) {
                    // set click for Items
                    SubItemClickListener.onClick(v, pos);
                }
            });

            recyclerView.setAdapter(parentListAdapter);

        } else {

            ParentListAdapter parentListAdapter = new ParentListAdapter(getTitleList(),
                    getSubTitleList(), context, parentLayout,
                    childLayout, subListItemLayout, getTypeOfTitleLayout(),
                    getTypeOfSubLayout(), attrList,
                    getIconClose(), getIconOpen(), getImageClose(),
                    getImageOpen(), 1, subTitleListSize, grid, new OnRecyclerItemClickListener() {
                @Override
                public void onClick(View v, int pos) {
                    // set click for Items
                    SubItemClickListener.onClick(v, pos);
                }
            });

            recyclerView.setAdapter(parentListAdapter);
        }
    }

    public List<TitleItem> getTitleList() {
        return titleList;
    }

    public void setTitleList(List<TitleItem> titleList) {
        this.titleList = titleList;
        initialize(mContext);
    }

    public List<ChildItem> getSubTitleList() {
        return subTitleList;
    }

    public void setSubTitleList(List<ChildItem> subTitleList) {
        this.subTitleList = subTitleList;
        initialize(mContext);
    }

    public Context getmContext() {
        return mContext;
    }

    public void setmContext(Context mContext) {
        this.mContext = mContext;
        initialize(mContext);
    }

    public String getTypeOfTitleLayout() {
        return TypeOfTitleLayout;
    }

    public void setTypeOfTitleLayout(String typeOfTitleLayout) {
        TypeOfTitleLayout = typeOfTitleLayout;
        initialize(mContext);
    }

    public String getTypeOfSubLayout() {
        return TypeOfSubLayout;
    }

    public void setTypeOfSubLayout(String typeOfSubLayout) {
        TypeOfSubLayout = typeOfSubLayout;
        initialize(mContext);
    }

    public int getIconClose() {
        return iconClose;
    }

    public void setIconClose(int iconClose) {
        this.iconClose = iconClose;
        initialize(mContext);
    }

    public int getIconOpen() {
        return iconOpen;
    }

    public void setIconOpen(int iconOpen) {
        this.iconOpen = iconOpen;
        initialize(mContext);
    }

    public int getImageClose() {
        return imageClose;
    }

    public void setImageClose(int imageClose) {
        this.imageClose = imageClose;
        initialize(mContext);
    }

    public int getImageOpen() {
        return imageOpen;
    }

    public void setImageOpen(int imageOpen) {
        this.imageOpen = imageOpen;
        initialize(mContext);
    }

    public OnRecyclerItemClickListener getSubItemClickListener() {
        return SubItemClickListener;
    }

    public void setSubItemClickListener(OnRecyclerItemClickListener subItemClickListener) {
        SubItemClickListener = subItemClickListener;
        initialize(mContext);
    }

    private void setLayoutForTitle() {

        switch (getTypeOfTitleLayout()) {

            case ConstDefault.Title.TITLE:
                parentLayout = R.layout.collapse_title;

                switch (TypeOfSubLayout) {
                    case ConstDefault.SubLayout.SUB_TEXT_LIST:
                        childLayout = R.layout.expand_list;
                        subListItemLayout = R.layout.expand_text_list_item;
                        break;

                    case ConstDefault.SubLayout.SUB_TEXT_ICON_LIST:
                        childLayout = R.layout.expand_list;
                        subListItemLayout = R.layout.expand_text_icon_list_item;
                        break;

                    case ConstDefault.SubLayout.SUB_TEXT_CIRCLE_IMAGE_LIST:
                        childLayout = R.layout.expand_list;
                        subListItemLayout = R.layout.expand_text_image_circle_list_item;
                        break;

                    case ConstDefault.SubLayout.SUB_TEXT_RECTANGLE_IMAGE_LIST:
                        childLayout = R.layout.expand_list;
                        subListItemLayout = R.layout.expand_text_image_rectangle_list_item;
                        break;

                    case ConstDefault.SubLayout.SUB_TEXT_ROUNDED_IMAGE_LIST:
                        childLayout = R.layout.expand_list;
                        subListItemLayout = R.layout.expand_text_image_rounded_list_item;
                        break;

                    case ConstDefault.SubLayout.SUB_IMAGE_LIST:
                        childLayout = R.layout.expand_list;
                        subListItemLayout = R.layout.expand_image_grid_item;
                        break;

                    case ConstDefault.SubLayout.SUB_SINGLE_TEXT:
                        childLayout = R.layout.expand_without_list;
                        subListItemLayout = R.layout.expand_single_text;
                        break;

                    case ConstDefault.SubLayout.SUB_SINGLE_IMAGE:
                        childLayout = R.layout.expand_without_list;
                        subListItemLayout = R.layout.expand_single_image;
                        break;

                    case ConstDefault.SubLayout.SUB_TEXT_IMAGE_GRID:
                        childLayout = R.layout.expand_list;
                        subListItemLayout = R.layout.expand_text_image_grid_item;
                        grid = 1;
                        break;

                    case ConstDefault.SubLayout.SUB_IMAGE_GRID:
                        childLayout = R.layout.expand_list;
                        subListItemLayout = R.layout.expand_image_grid_item;
                        grid = 1;
                        break;

                }

                break;

            case ConstDefault.Title.TITLE_ROUNDED_IMAGE:
                parentLayout = R.layout.collapse_title_rounded_image;
                switch (TypeOfSubLayout) {
                    case ConstDefault.SubLayout.SUB_TEXT_LIST:
                        childLayout = R.layout.expand_list;
                        subListItemLayout = R.layout.expand_text_list_item;
                        break;

                    case ConstDefault.SubLayout.SUB_TEXT_ICON_LIST:
                        childLayout = R.layout.expand_list;
                        subListItemLayout = R.layout.expand_text_icon_list_item;
                        break;

                    case ConstDefault.SubLayout.SUB_TEXT_CIRCLE_IMAGE_LIST:
                        childLayout = R.layout.expand_list;
                        subListItemLayout = R.layout.expand_text_image_circle_list_item;
                        break;

                    case ConstDefault.SubLayout.SUB_TEXT_RECTANGLE_IMAGE_LIST:
                        childLayout = R.layout.expand_list;
                        subListItemLayout = R.layout.expand_text_image_rectangle_list_item;
                        break;

                    case ConstDefault.SubLayout.SUB_TEXT_ROUNDED_IMAGE_LIST:
                        childLayout = R.layout.expand_list;
                        subListItemLayout = R.layout.expand_text_image_rounded_list_item;
                        break;

                    case ConstDefault.SubLayout.SUB_IMAGE_LIST:
                        childLayout = R.layout.expand_list;
                        subListItemLayout = R.layout.expand_image_grid_item;
                        break;

                    case ConstDefault.SubLayout.SUB_SINGLE_TEXT:
                        childLayout = R.layout.expand_without_list;
                        subListItemLayout = R.layout.expand_single_text;
                        break;

                    case ConstDefault.SubLayout.SUB_SINGLE_IMAGE:
                        childLayout = R.layout.expand_without_list;
                        subListItemLayout = R.layout.expand_single_image;
                        break;

                    case ConstDefault.SubLayout.SUB_TEXT_IMAGE_GRID:
                        childLayout = R.layout.expand_list;
                        subListItemLayout = R.layout.expand_text_image_grid_item;
                        grid = 1;
                        break;

                    case ConstDefault.SubLayout.SUB_IMAGE_GRID:
                        childLayout = R.layout.expand_list;
                        subListItemLayout = R.layout.expand_image_grid_item;
                        grid = 1;
                        break;

                }
                break;

            case ConstDefault.Title.TITLE_RECTANGLE_IMAGE:
                parentLayout = R.layout.collapse_title_rectangle_image;
                switch (TypeOfSubLayout) {
                    case ConstDefault.SubLayout.SUB_TEXT_LIST:
                        childLayout = R.layout.expand_list;
                        subListItemLayout = R.layout.expand_text_list_item;
                        break;

                    case ConstDefault.SubLayout.SUB_TEXT_ICON_LIST:
                        childLayout = R.layout.expand_list;
                        subListItemLayout = R.layout.expand_text_icon_list_item;
                        break;

                    case ConstDefault.SubLayout.SUB_TEXT_CIRCLE_IMAGE_LIST:
                        childLayout = R.layout.expand_list;
                        subListItemLayout = R.layout.expand_text_image_circle_list_item;
                        break;

                    case ConstDefault.SubLayout.SUB_TEXT_RECTANGLE_IMAGE_LIST:
                        childLayout = R.layout.expand_list;
                        subListItemLayout = R.layout.expand_text_image_rectangle_list_item;
                        break;

                    case ConstDefault.SubLayout.SUB_TEXT_ROUNDED_IMAGE_LIST:
                        childLayout = R.layout.expand_list;
                        subListItemLayout = R.layout.expand_text_image_rounded_list_item;
                        break;

                    case ConstDefault.SubLayout.SUB_IMAGE_LIST:
                        childLayout = R.layout.expand_list;
                        subListItemLayout = R.layout.expand_image_grid_item;
                        break;

                    case ConstDefault.SubLayout.SUB_SINGLE_TEXT:
                        childLayout = R.layout.expand_without_list;
                        subListItemLayout = R.layout.expand_single_text;
                        break;

                    case ConstDefault.SubLayout.SUB_SINGLE_IMAGE:
                        childLayout = R.layout.expand_without_list;
                        subListItemLayout = R.layout.expand_single_image;
                        break;

                    case ConstDefault.SubLayout.SUB_TEXT_IMAGE_GRID:
                        childLayout = R.layout.expand_list;
                        subListItemLayout = R.layout.expand_text_image_grid_item;
                        grid = 1;
                        break;

                    case ConstDefault.SubLayout.SUB_IMAGE_GRID:
                        childLayout = R.layout.expand_list;
                        subListItemLayout = R.layout.expand_image_grid_item;
                        grid = 1;
                        break;

                }
                break;

            case ConstDefault.Title.TITLE_CIRCLE_IMAGE:
                parentLayout = R.layout.collapse_title_circle_image;
                switch (TypeOfSubLayout) {
                    case ConstDefault.SubLayout.SUB_TEXT_LIST:
                        childLayout = R.layout.expand_list;
                        subListItemLayout = R.layout.expand_text_list_item;
                        break;

                    case ConstDefault.SubLayout.SUB_TEXT_ICON_LIST:
                        childLayout = R.layout.expand_list;
                        subListItemLayout = R.layout.expand_text_icon_list_item;
                        break;

                    case ConstDefault.SubLayout.SUB_TEXT_CIRCLE_IMAGE_LIST:
                        childLayout = R.layout.expand_list;
                        subListItemLayout = R.layout.expand_text_image_circle_list_item;
                        break;

                    case ConstDefault.SubLayout.SUB_TEXT_RECTANGLE_IMAGE_LIST:
                        childLayout = R.layout.expand_list;
                        subListItemLayout = R.layout.expand_text_image_rectangle_list_item;
                        break;

                    case ConstDefault.SubLayout.SUB_TEXT_ROUNDED_IMAGE_LIST:
                        childLayout = R.layout.expand_list;
                        subListItemLayout = R.layout.expand_text_image_rounded_list_item;
                        break;

                    case ConstDefault.SubLayout.SUB_IMAGE_LIST:
                        childLayout = R.layout.expand_list;
                        subListItemLayout = R.layout.expand_image_grid_item;
                        break;

                    case ConstDefault.SubLayout.SUB_SINGLE_TEXT:
                        childLayout = R.layout.expand_without_list;
                        subListItemLayout = R.layout.expand_single_text;
                        break;

                    case ConstDefault.SubLayout.SUB_SINGLE_IMAGE:
                        childLayout = R.layout.expand_without_list;
                        subListItemLayout = R.layout.expand_single_image;
                        break;

                    case ConstDefault.SubLayout.SUB_TEXT_IMAGE_GRID:
                        childLayout = R.layout.expand_list;
                        subListItemLayout = R.layout.expand_text_image_grid_item;
                        grid = 1;
                        break;

                    case ConstDefault.SubLayout.SUB_IMAGE_GRID:
                        childLayout = R.layout.expand_list;
                        subListItemLayout = R.layout.expand_image_grid_item;
                        grid = 1;
                        break;

                }
                break;

            case ConstDefault.Title.TITLE_SUBTITLE:
                parentLayout = R.layout.collapse_title_subtitle;
                switch (TypeOfSubLayout) {
                    case ConstDefault.SubLayout.SUB_TEXT_LIST:
                        childLayout = R.layout.expand_list;
                        subListItemLayout = R.layout.expand_text_list_item;
                        break;

                    case ConstDefault.SubLayout.SUB_TEXT_ICON_LIST:
                        childLayout = R.layout.expand_list;
                        subListItemLayout = R.layout.expand_text_icon_list_item;
                        break;

                    case ConstDefault.SubLayout.SUB_TEXT_CIRCLE_IMAGE_LIST:
                        childLayout = R.layout.expand_list;
                        subListItemLayout = R.layout.expand_text_image_circle_list_item;
                        break;

                    case ConstDefault.SubLayout.SUB_TEXT_RECTANGLE_IMAGE_LIST:
                        childLayout = R.layout.expand_list;
                        subListItemLayout = R.layout.expand_text_image_rectangle_list_item;
                        break;

                    case ConstDefault.SubLayout.SUB_TEXT_ROUNDED_IMAGE_LIST:
                        childLayout = R.layout.expand_list;
                        subListItemLayout = R.layout.expand_text_image_rounded_list_item;
                        break;

                    case ConstDefault.SubLayout.SUB_IMAGE_LIST:
                        childLayout = R.layout.expand_list;
                        subListItemLayout = R.layout.expand_image_grid_item;
                        break;

                    case ConstDefault.SubLayout.SUB_SINGLE_TEXT:
                        childLayout = R.layout.expand_without_list;
                        subListItemLayout = R.layout.expand_single_text;
                        break;

                    case ConstDefault.SubLayout.SUB_SINGLE_IMAGE:
                        childLayout = R.layout.expand_without_list;
                        subListItemLayout = R.layout.expand_single_image;
                        break;

                    case ConstDefault.SubLayout.SUB_TEXT_IMAGE_GRID:
                        childLayout = R.layout.expand_list;
                        subListItemLayout = R.layout.expand_text_image_grid_item;
                        grid = 1;
                        break;

                    case ConstDefault.SubLayout.SUB_IMAGE_GRID:
                        childLayout = R.layout.expand_list;
                        subListItemLayout = R.layout.expand_image_grid_item;
                        grid = 1;
                        break;

                }
                break;

            case ConstDefault.Title.TITLE_SUBTITLE_ROUNDED_IMAGE:
                parentLayout = R.layout.collapse_title_subtitle_rounded_image;
                switch (TypeOfSubLayout) {
                    case ConstDefault.SubLayout.SUB_TEXT_LIST:
                        childLayout = R.layout.expand_list;
                        subListItemLayout = R.layout.expand_text_list_item;
                        break;

                    case ConstDefault.SubLayout.SUB_TEXT_ICON_LIST:
                        childLayout = R.layout.expand_list;
                        subListItemLayout = R.layout.expand_text_icon_list_item;
                        break;

                    case ConstDefault.SubLayout.SUB_TEXT_CIRCLE_IMAGE_LIST:
                        childLayout = R.layout.expand_list;
                        subListItemLayout = R.layout.expand_text_image_circle_list_item;
                        break;

                    case ConstDefault.SubLayout.SUB_TEXT_RECTANGLE_IMAGE_LIST:
                        childLayout = R.layout.expand_list;
                        subListItemLayout = R.layout.expand_text_image_rectangle_list_item;
                        break;

                    case ConstDefault.SubLayout.SUB_TEXT_ROUNDED_IMAGE_LIST:
                        childLayout = R.layout.expand_list;
                        subListItemLayout = R.layout.expand_text_image_rounded_list_item;
                        break;

                    case ConstDefault.SubLayout.SUB_IMAGE_LIST:
                        childLayout = R.layout.expand_list;
                        subListItemLayout = R.layout.expand_image_grid_item;
                        break;

                    case ConstDefault.SubLayout.SUB_SINGLE_TEXT:
                        childLayout = R.layout.expand_without_list;
                        subListItemLayout = R.layout.expand_single_text;
                        break;

                    case ConstDefault.SubLayout.SUB_SINGLE_IMAGE:
                        childLayout = R.layout.expand_without_list;
                        subListItemLayout = R.layout.expand_single_image;
                        break;

                    case ConstDefault.SubLayout.SUB_TEXT_IMAGE_GRID:
                        childLayout = R.layout.expand_list;
                        subListItemLayout = R.layout.expand_text_image_grid_item;
                        grid = 1;
                        break;

                    case ConstDefault.SubLayout.SUB_IMAGE_GRID:
                        childLayout = R.layout.expand_list;
                        subListItemLayout = R.layout.expand_image_grid_item;
                        grid = 1;
                        break;

                }
                break;

            case ConstDefault.Title.TITLE_SUBTITLE_RECTANGLE_IMAGE:
                parentLayout = R.layout.collapse_title_subtitle_rectangle_image;
                switch (TypeOfSubLayout) {
                    case ConstDefault.SubLayout.SUB_TEXT_LIST:
                        childLayout = R.layout.expand_list;
                        subListItemLayout = R.layout.expand_text_list_item;
                        break;

                    case ConstDefault.SubLayout.SUB_TEXT_ICON_LIST:
                        childLayout = R.layout.expand_list;
                        subListItemLayout = R.layout.expand_text_icon_list_item;
                        break;

                    case ConstDefault.SubLayout.SUB_TEXT_CIRCLE_IMAGE_LIST:
                        childLayout = R.layout.expand_list;
                        subListItemLayout = R.layout.expand_text_image_circle_list_item;
                        break;

                    case ConstDefault.SubLayout.SUB_TEXT_RECTANGLE_IMAGE_LIST:
                        childLayout = R.layout.expand_list;
                        subListItemLayout = R.layout.expand_text_image_rectangle_list_item;
                        break;

                    case ConstDefault.SubLayout.SUB_TEXT_ROUNDED_IMAGE_LIST:
                        childLayout = R.layout.expand_list;
                        subListItemLayout = R.layout.expand_text_image_rounded_list_item;
                        break;

                    case ConstDefault.SubLayout.SUB_IMAGE_LIST:
                        childLayout = R.layout.expand_list;
                        subListItemLayout = R.layout.expand_image_grid_item;
                        break;

                    case ConstDefault.SubLayout.SUB_SINGLE_TEXT:
                        childLayout = R.layout.expand_without_list;
                        subListItemLayout = R.layout.expand_single_text;
                        break;

                    case ConstDefault.SubLayout.SUB_SINGLE_IMAGE:
                        childLayout = R.layout.expand_without_list;
                        subListItemLayout = R.layout.expand_single_image;
                        break;

                    case ConstDefault.SubLayout.SUB_TEXT_IMAGE_GRID:
                        childLayout = R.layout.expand_list;
                        subListItemLayout = R.layout.expand_text_image_grid_item;
                        grid = 1;
                        break;

                    case ConstDefault.SubLayout.SUB_IMAGE_GRID:
                        childLayout = R.layout.expand_list;
                        subListItemLayout = R.layout.expand_image_grid_item;
                        grid = 1;
                        break;

                }
                break;

            case ConstDefault.Title.TITLE_SUBTITLE_CIRCLE_IMAGE:
                parentLayout = R.layout.collapse_title_subtitle_circle_image;
                switch (TypeOfSubLayout) {
                    case ConstDefault.SubLayout.SUB_TEXT_LIST:
                        childLayout = R.layout.expand_list;
                        subListItemLayout = R.layout.expand_text_list_item;
                        break;

                    case ConstDefault.SubLayout.SUB_TEXT_ICON_LIST:
                        childLayout = R.layout.expand_list;
                        subListItemLayout = R.layout.expand_text_icon_list_item;
                        break;

                    case ConstDefault.SubLayout.SUB_TEXT_CIRCLE_IMAGE_LIST:
                        childLayout = R.layout.expand_list;
                        subListItemLayout = R.layout.expand_text_image_circle_list_item;
                        break;

                    case ConstDefault.SubLayout.SUB_TEXT_RECTANGLE_IMAGE_LIST:
                        childLayout = R.layout.expand_list;
                        subListItemLayout = R.layout.expand_text_image_rectangle_list_item;
                        break;

                    case ConstDefault.SubLayout.SUB_TEXT_ROUNDED_IMAGE_LIST:
                        childLayout = R.layout.expand_list;
                        subListItemLayout = R.layout.expand_text_image_rounded_list_item;
                        break;

                    case ConstDefault.SubLayout.SUB_IMAGE_LIST:
                        childLayout = R.layout.expand_list;
                        subListItemLayout = R.layout.expand_image_grid_item;
                        break;

                    case ConstDefault.SubLayout.SUB_SINGLE_TEXT:
                        childLayout = R.layout.expand_without_list;
                        subListItemLayout = R.layout.expand_single_text;
                        break;

                    case ConstDefault.SubLayout.SUB_SINGLE_IMAGE:
                        childLayout = R.layout.expand_without_list;
                        subListItemLayout = R.layout.expand_single_image;
                        break;

                    case ConstDefault.SubLayout.SUB_TEXT_IMAGE_GRID:
                        childLayout = R.layout.expand_list;
                        subListItemLayout = R.layout.expand_text_image_grid_item;
                        grid = 1;
                        break;

                    case ConstDefault.SubLayout.SUB_IMAGE_GRID:
                        childLayout = R.layout.expand_list;
                        subListItemLayout = R.layout.expand_image_grid_item;
                        grid = 1;
                        break;

                }
                break;

            case ConstDefault.Title.TITLE_ICON:
                parentLayout = R.layout.collapse_title_icon_image;
                switch (TypeOfSubLayout) {
                    case ConstDefault.SubLayout.SUB_TEXT_LIST:
                        childLayout = R.layout.expand_list;
                        subListItemLayout = R.layout.expand_text_list_item;
                        break;

                    case ConstDefault.SubLayout.SUB_TEXT_ICON_LIST:
                        childLayout = R.layout.expand_list;
                        subListItemLayout = R.layout.expand_text_icon_list_item;
                        break;

                    case ConstDefault.SubLayout.SUB_TEXT_CIRCLE_IMAGE_LIST:
                        childLayout = R.layout.expand_list;
                        subListItemLayout = R.layout.expand_text_image_circle_list_item;
                        break;

                    case ConstDefault.SubLayout.SUB_TEXT_RECTANGLE_IMAGE_LIST:
                        childLayout = R.layout.expand_list;
                        subListItemLayout = R.layout.expand_text_image_rectangle_list_item;
                        break;

                    case ConstDefault.SubLayout.SUB_TEXT_ROUNDED_IMAGE_LIST:
                        childLayout = R.layout.expand_list;
                        subListItemLayout = R.layout.expand_text_image_rounded_list_item;
                        break;

                    case ConstDefault.SubLayout.SUB_IMAGE_LIST:
                        childLayout = R.layout.expand_list;
                        subListItemLayout = R.layout.expand_image_grid_item;
                        break;

                    case ConstDefault.SubLayout.SUB_SINGLE_TEXT:
                        childLayout = R.layout.expand_without_list;
                        subListItemLayout = R.layout.expand_single_text;
                        break;

                    case ConstDefault.SubLayout.SUB_SINGLE_IMAGE:
                        childLayout = R.layout.expand_without_list;
                        subListItemLayout = R.layout.expand_single_image;
                        break;

                    case ConstDefault.SubLayout.SUB_TEXT_IMAGE_GRID:
                        childLayout = R.layout.expand_list;
                        subListItemLayout = R.layout.expand_text_image_grid_item;
                        grid = 1;
                        break;

                    case ConstDefault.SubLayout.SUB_IMAGE_GRID:
                        childLayout = R.layout.expand_list;
                        subListItemLayout = R.layout.expand_image_grid_item;
                        grid = 1;
                        break;

                }
                break;

            case ConstDefault.Title.TITLE_SUBTITLE_ICON:
                parentLayout = R.layout.collapse_title_subtitle_icon_image;
                switch (TypeOfSubLayout) {
                    case ConstDefault.SubLayout.SUB_TEXT_LIST:
                        childLayout = R.layout.expand_list;
                        subListItemLayout = R.layout.expand_text_list_item;
                        break;

                    case ConstDefault.SubLayout.SUB_TEXT_ICON_LIST:
                        childLayout = R.layout.expand_list;
                        subListItemLayout = R.layout.expand_text_icon_list_item;
                        break;

                    case ConstDefault.SubLayout.SUB_TEXT_CIRCLE_IMAGE_LIST:
                        childLayout = R.layout.expand_list;
                        subListItemLayout = R.layout.expand_text_image_circle_list_item;
                        break;

                    case ConstDefault.SubLayout.SUB_TEXT_RECTANGLE_IMAGE_LIST:
                        childLayout = R.layout.expand_list;
                        subListItemLayout = R.layout.expand_text_image_rectangle_list_item;
                        break;

                    case ConstDefault.SubLayout.SUB_TEXT_ROUNDED_IMAGE_LIST:
                        childLayout = R.layout.expand_list;
                        subListItemLayout = R.layout.expand_text_image_rounded_list_item;
                        break;

                    case ConstDefault.SubLayout.SUB_IMAGE_LIST:
                        childLayout = R.layout.expand_list;
                        subListItemLayout = R.layout.expand_image_grid_item;
                        break;

                    case ConstDefault.SubLayout.SUB_SINGLE_TEXT:
                        childLayout = R.layout.expand_without_list;
                        subListItemLayout = R.layout.expand_single_text;
                        break;

                    case ConstDefault.SubLayout.SUB_SINGLE_IMAGE:
                        childLayout = R.layout.expand_without_list;
                        subListItemLayout = R.layout.expand_single_image;
                        break;

                    case ConstDefault.SubLayout.SUB_TEXT_IMAGE_GRID:
                        childLayout = R.layout.expand_list;
                        subListItemLayout = R.layout.expand_text_image_grid_item;
                        grid = 1;
                        break;

                    case ConstDefault.SubLayout.SUB_IMAGE_GRID:
                        childLayout = R.layout.expand_list;
                        subListItemLayout = R.layout.expand_image_grid_item;
                        grid = 1;
                        break;
                }
                break;
        }
    }

    private void setAttrsInView(TypedArray a) {

        titleColor = a.getColor(R.styleable.RecyclerViewCustom_titleColor, 0);
        subtitleColor = a.getColor(R.styleable.RecyclerViewCustom_subtitleColor, 0);
        iconColor = a.getColor(R.styleable.RecyclerViewCustom_iconColor, 0);
        lineOfTitleListColor = a.getColor(R.styleable.RecyclerViewCustom_lineOfTitleListColor, 0);
        lineOfSubListColor = a.getColor(R.styleable.RecyclerViewCustom_lineOfSubListColor, 0);
        textOfSubListColor = a.getColor(R.styleable.RecyclerViewCustom_textOfSubListColor, 0);
        backgroundOfSubListColor = a.getColor(R.styleable.RecyclerViewCustom_backgroundOfSubListColor, 0);
        backgroundOfTitleListColor = a.getColor(R.styleable.RecyclerViewCustom_backgroundOfTitleListColor, 0);
        visibilityLineOfTitleList = a.getInteger(R.styleable.RecyclerViewCustom_VisibilityLineOfTitleList, 0);
        visibilityLineOfSubList = a.getInteger(R.styleable.RecyclerViewCustom_VisibilityLineOfSubList, 0);
        imageIconColor = a.getColor(R.styleable.RecyclerViewCustom_imageIconColor, 0);
        a.recycle();

        attrList = new ArrayList<>();
        attrList.add(new AttrItem
                (ConstDefault.Attrs.TITLE_COLOR, titleColor));
        attrList.add(new AttrItem
                (ConstDefault.Attrs.SUB_TITEL_COLOR, subtitleColor));
        attrList.add(new AttrItem
                (ConstDefault.Attrs.ICON_COLOR, iconColor));
        attrList.add(new AttrItem
                (ConstDefault.Attrs.LINE_OF_TITLE_LIST_COLOR, lineOfTitleListColor));
        attrList.add(new AttrItem
                (ConstDefault.Attrs.LINE_OF_SUB_LIST_COLOR, lineOfSubListColor));
        attrList.add(new AttrItem
                (ConstDefault.Attrs.TEXT_OF_SUB_LIST_COLOR, textOfSubListColor));
        attrList.add(new AttrItem
                (ConstDefault.Attrs.BACKGROUND_OF_SUBLIST_COLOR, backgroundOfSubListColor));
        attrList.add(new AttrItem
                (ConstDefault.Attrs.BACKGROUND_OF_TITLE_LIST_COLOR, backgroundOfTitleListColor));
        attrList.add(new AttrItem
                (ConstDefault.Attrs.VISIBILITY_LINE_OF_TITLE_LIST, visibilityLineOfTitleList));
        attrList.add(new AttrItem
                (ConstDefault.Attrs.VISIBILITY_LINE_OF_SUB_LIST, visibilityLineOfSubList));
        attrList.add(new AttrItem
                (ConstDefault.Attrs.IMAGE_ICON_COLOR, imageIconColor));
    }

}
