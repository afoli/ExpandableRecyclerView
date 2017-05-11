package ir.thebigbang.expandablerecyclerviewlibrary.models;

public class ChildItem {

    private int id;
    private String text;
    private int image;
    private String imageIcon;

    public ChildItem() {
    }

    public ChildItem(String text) {
        this.text = text;
    }

    public ChildItem(String text, int image) {
        this.text = text;
        this.image = image;
    }

    public ChildItem(String text, String imageIcon) {
        this.text = text;
        this.imageIcon = imageIcon;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getText() {
        return text;
    }

    public void setText(String text) {
        this.text = text;
    }

    public int getImage() {
        return image;
    }

    public void setImage(int image) {
        this.image = image;
    }

    public String getImageIcon() {
        return imageIcon;
    }

    public void setImageIcon(String imageIcon) {
        this.imageIcon = imageIcon;
    }
}
