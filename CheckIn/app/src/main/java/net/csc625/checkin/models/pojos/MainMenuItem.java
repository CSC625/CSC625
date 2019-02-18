package net.csc625.checkin.models.pojos;

public class MainMenuItem
{
    private int label;
    private int title;
    private int image;
    public MainMenuItem()
    {

    }

    public MainMenuItem(int label, int title, int image)
    {
        this.image = image;
        this.label = label;
        this.title = title;
    }

    public void setLabel(int label) {
        this.label = label;
    }

    public void setTitle(int  title) { this.title = title; }

    public void setImage(int image) {
        this.image = image;
    }

    public int getLabel() {
        return label;
    }

    public int getTitle() {
        return title;
    }

    public int getImage() {
        return image;
    }


}
