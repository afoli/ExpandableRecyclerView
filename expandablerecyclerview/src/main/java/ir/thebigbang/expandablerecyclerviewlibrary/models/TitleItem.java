package ir.thebigbang.expandablerecyclerviewlibrary.models;

import java.util.List;

public class TitleItem {
    private int id;
    private String name;
    private List<ChildItem> sublist;
    private String subTitleList;
    private int imageList;
    private List<String> iconList;
    private int iconImage;
    private boolean open = false;

    public TitleItem() {
    }

    public TitleItem(String name, List<ChildItem> sublist, int icon) {
        this.name = name;
        this.sublist = sublist;
        this.iconImage = icon;
    }

    public TitleItem(int id, String name, List<ChildItem> sublist, int icon) {
        this.id = id;
        this.name = name;
        this.sublist = sublist;
        this.iconImage = icon;
    }

    public TitleItem(String name, List<ChildItem> sublist, boolean open) {
        this.name = name;
        this.sublist = sublist;
        this.open = open;
    }

    //title_subtitle
    public TitleItem(List<ChildItem> sublist, boolean open, String subTitleList, String name) {
        this.name = name;
        this.sublist = sublist;
        this.subTitleList = subTitleList;
        this.open = open;
    }

    //title_image
    public TitleItem(String name, List<ChildItem> sublist, boolean open, int imageList) {
        this.name = name;
        this.sublist = sublist;
        this.imageList = imageList;
        this.open = open;
    }

    //title_subtitle_image
    public TitleItem(String name, List<ChildItem> sublist, String subTitleList, boolean open, int imageList) {
        this.name = name;
        this.sublist = sublist;
        this.subTitleList = subTitleList;
        this.imageList = imageList;
        this.open = open;
    }

    //title_subtitle_iconlist
    public TitleItem(String name, List<ChildItem> sublist, String subTitleList, List<String> iconList, boolean open) {
        this.name = name;
        this.sublist = sublist;
        this.subTitleList = subTitleList;
        this.iconList = iconList;
        this.open = open;
    }

    //title_iconlist
    public TitleItem(String name, List<ChildItem> sublist, List<String> iconList, boolean open) {
        this.name = name;
        this.sublist = sublist;
        this.iconList = iconList;
        this.open = open;
    }

    public TitleItem(String name, boolean open) {
        this.name = name;
        this.open = open;
    }

    public TitleItem(String name, int imageList, boolean open) {
        this.name = name;
        this.imageList = imageList;
        this.open = open;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public List<ChildItem> getSublist() {
        return sublist;
    }

    public void setSublist(List<ChildItem> sublist) {
        this.sublist = sublist;
    }

    public int getIcon() {
        return iconImage;
    }

    public void setIcon(int icon) {
        this.iconImage = icon;
    }

    public boolean isOpen() {
        return open;
    }

    public void setOpen(boolean open) {
        this.open = open;
    }

    public int getImageList() {
        return imageList;
    }

    public void setImageList(int imageList) {
        this.imageList = imageList;
    }

    public List<String> getIconlist() {
        return iconList;
    }

    public void setIconlist(List<String> iconlist) {
        this.iconList = iconlist;
    }

    public String getSubTitleList() {
        return subTitleList;
    }

    public void setSubTitleList(String subTitleList) {
        this.subTitleList = subTitleList;
    }

    public List<String> getIconList() {
        return iconList;
    }

    public void setIconList(List<String> iconList) {
        this.iconList = iconList;
    }
}
