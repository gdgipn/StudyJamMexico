package materialdesign.gdg.androidtitlan.androidtitlan_materialdesign.models;


import materialdesign.gdg.androidtitlan.androidtitlan_materialdesign.interfaces.RecycleViewItemInterface;

/**
 * Created by erik Jhordan on 21/01/15.
 */
public class ItemNavigationDrawer implements RecycleViewItemInterface {

    private String contentTitle;
    private int icon;
    private int textColor;
    private int backgroundContentSection;
    private int contentTitleFromResources;

    public ItemNavigationDrawer() {

    }

    public ItemNavigationDrawer(String contentTitle, int icon, int textColor, int backgroundContentSection) {
        this.contentTitle = contentTitle;
        this.icon = icon;
        this.textColor = textColor;
        this.backgroundContentSection = backgroundContentSection;
    }

    public ItemNavigationDrawer(int icon, int textColor, int backgroundContentSection, int contentTitleFromResources) {
        this.icon = icon;
        this.textColor = textColor;
        this.backgroundContentSection = backgroundContentSection;
        this.contentTitleFromResources = contentTitleFromResources;
    }

    public ItemNavigationDrawer(int icon, int textColor, int contentTitleFromResources) {
        this.icon = icon;
        this.textColor = textColor;
        this.contentTitleFromResources = contentTitleFromResources;
    }


    public int getBackgroundContentSection() {
        return backgroundContentSection;
    }

    public void setBackgroundContentSection(int backgroundContentSection) {
        this.backgroundContentSection = backgroundContentSection;
    }

    public int getTextColor() {
        return textColor;
    }

    public void setTextColor(int textColor) {
        this.textColor = textColor;
    }

    public int getIcon() {
        return icon;
    }

    public void setIcon(int icon) {
        this.icon = icon;
    }

    public String getContentTitle() {
        return contentTitle;
    }

    public void setContentTitle(String contentTitle) {
        this.contentTitle = contentTitle;
    }

    public int getContentTitleFromResources() {
        return contentTitleFromResources;
    }

    public void setContentTitleFromResources(int contentTitleFromResources) {
        this.contentTitleFromResources = contentTitleFromResources;
    }

    @Override
    public boolean isSection() {
        return false;
    }
}
