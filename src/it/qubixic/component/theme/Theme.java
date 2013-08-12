package it.qubixic.component.theme;

import javax.microedition.lcdui.Font;

public class Theme {

    /*
    public static final int BORDER_NONE = 0;
    public static final int BORDER_TOUCH_AND_TYPE = 1;
    protected boolean drawBackground = true;
    protected int borderType = BORDER_NONE;
    protected Font font = Font.getFont(Font.FACE_SYSTEM, Font.STYLE_PLAIN,
            Font.SIZE_LARGE);
    
    protected Font titlebarTitleFont = Font.getFont(Font.FACE_SYSTEM, Font.STYLE_PLAIN,
            Font.SIZE_SMALL);
    protected Font titlebarMetadataFont = Compatibility.getFont(Font.FACE_SYSTEM, 
            Font.STYLE_PLAIN, 10) ;    
    */
    protected static int backgroundThumbnailColor = 0xffffff;
    protected static int backgroundColorFocused = 0x1090c8;
    protected static Font thumbnailFont = Font.getFont(Font.FACE_SYSTEM, Font.STYLE_PLAIN,
            Font.SIZE_SMALL);
    protected static int textMarginLeftAndRight = 8;
    protected static int textMarginTopAndBottom = 2;
    protected static int thumbnailBackgroundCaptionColor = 0x202020 ;
    protected static int thumbnailCaptionTitleColor = 0xffffff ;
    /*
    protected int textColor = 0xd8dcd8;
    protected int textColorFocused = 0xf8fcf8;
    protected int borderColorLight = 0x00000000;
    protected int borderColorDark = 0x00000000;
    protected int textOnlyMarginLeft = 12;
    protected int imageMarginLeft = 6;
    
    
    protected int backgroundMarginTopAndBottom = 1;
    protected int selectedImageMarginRight = 6;
    protected int elementMinHeight = 38;
    protected int selectedImageWidth = 40;
    protected int currentlyEditingButtonStatusColor = 0x993300 ; 
    protected int currentlyNotEditingButtonStatusColor = 0x6699ff ; 
   
    
    protected int thumbnailCaptionTextColor = 0x535853 ;
    protected int panelBackgroundColor = 0xffffff ;
    protected int titlebarBackgroundColor = 0x202020 ;
    protected int titleBarTitleColor = 0xffff00 ;
    protected int titleBarMetadataColor = 0xcccccc ;
    protected String toggleExclusiveImage = "/toggle_disabled.png";
    protected String toggleExclusiveImageSelected =
            "/toggle_exclusive_active.png";
    protected String toggleMultipleImage = "/toggle_disabled.png";
    protected String toggleMultipleImageSelected =
            "/toggle_multiple_active.png";    
    */
    
    private Theme() {}

    public static int getThumbnailBackgroundColorFocused() {
        return backgroundColorFocused;
    }

    public static int getThumbnailBackgroundColor() {
        return backgroundThumbnailColor;
    }

    public static Font getThumbnailFont() {
        return thumbnailFont ;
    }
        
    public static int getTextMarginLeftAndRight() {
        return textMarginLeftAndRight;
    }
    
    public static int getTextMarginTopAndBottom() {
        return textMarginTopAndBottom;
    }
    
    
    public static int getThumbnailBackgroundCaptionColor() {
        return thumbnailBackgroundCaptionColor;
    }
    
    public static int getThumbnailCaptionTitleColor() { 
        return thumbnailCaptionTitleColor ;
    }
    /*
    public void setBackgroundColorFocused(int backgroundColorFocused) {
        this.backgroundColorFocused = backgroundColorFocused;
    }
       
    public boolean drawBackground() {
        return drawBackground ;
    }
        
    public void setThumbnailBackgroundColor(int backgroundThumbnailColor) {
        this.backgroundThumbnailColor = backgroundThumbnailColor;
    }   

    public int getBackgroundMarginTopAndBottom() {
        return backgroundMarginTopAndBottom;
    }

    public void setBackgroundMarginTopAndBottom(
            int backgroundMarginTopAndBottom) {
        this.backgroundMarginTopAndBottom = backgroundMarginTopAndBottom;
    }

    public int getBorderColorDark() {
        return borderColorDark;
    }

    public void setBorderColorDark(int borderColorDark) {
        this.borderColorDark = borderColorDark;
    }

    public int getBorderColorLight() {
        return borderColorLight;
    }

    public void setBorderColorLight(int borderColorLight) {
        this.borderColorLight = borderColorLight;
    }

    public int getCurrentlyEditingButtonColorStatus() {
        return currentlyEditingButtonStatusColor ;
    }
    
    public void setThumbnailCaptionTitleColor(int thumbnailCaptionTitleColor) {
        this.thumbnailCaptionTitleColor = thumbnailCaptionTitleColor ;
    }
    
    
    
    public void setThumbnailCaptionTextColor(int thumbnailCaptionTextColor) {
        this.thumbnailCaptionTextColor = thumbnailCaptionTextColor ;
    }
    
    public int getThumbnailCaptionTextColor() { 
        return thumbnailCaptionTextColor ;
    }
    
    public void setCurrentlyEditingButtonColorStatus(int currentlyEditingButtonStatusColor) {
        this.currentlyEditingButtonStatusColor = currentlyEditingButtonStatusColor ;
    }
    
    public int getCurrentlyNotEditingButtonColorStatus() {
        return currentlyNotEditingButtonStatusColor ;
    }
    
    public void setCurrentlyNotEditingButtonColorStatus(int currentlyNotEditingButtonStatusColor) {
        this.currentlyNotEditingButtonStatusColor = currentlyNotEditingButtonStatusColor ;
    }    
    
    public int getBorderType() {
        return borderType;
    }
    
    public void setPanelBackgroundColor(int panelBackgroundColor) {
        this.panelBackgroundColor = panelBackgroundColor ;
    }

    public int getPanelBackgroundColor() {
        return panelBackgroundColor ;
    }
    
    public void setBorderType(int borderType) {
        this.borderType = borderType;
    }

    public boolean isDrawBackground() {
        return drawBackground;
    }

    public void setDrawBackground(boolean drawBackground) {
        this.drawBackground = drawBackground;
    }

    public int getElementMinHeight() {
        return elementMinHeight;
    }

    public void setElementMinHeight(int elementMinHeight) {
        this.elementMinHeight = elementMinHeight;
    }

     
    public void setThumbnailBackgroundCaptionColor(int thumbnailBackgroundCaptionColor) {
        this.thumbnailBackgroundCaptionColor = thumbnailBackgroundCaptionColor ;
    }
    
    public int getTitlebarBackgroundColor() {
        return titlebarBackgroundColor ;
    }
    
    public void setTitlebarBackgroundColor(int titlebarBackgroundColor) {
        this.titlebarBackgroundColor = titlebarBackgroundColor ;
    }
    
    public int getTitleBarTitleColor() {
        return titleBarTitleColor ;
    }
    
    public void setTitleBarTitleColor(int titleBarTitleColor) {
        this.titleBarTitleColor = titleBarTitleColor ;
    }
    
    public int getTitleBarMetadataColor() {
        return this.titleBarMetadataColor ;
    }
    
    public void setTitleBarMetadataColor(int titleBarMetadataColor) {
        this.titleBarMetadataColor = titleBarMetadataColor ;
    }
    
    public Font getFont() {
        return font;
    }

    public void setFont(Font font) {
        this.font = font;
    }

    public void setThumbnailfont(Font thumbnailTitlefont) {
        this.thumbnailfont = thumbnailTitlefont ;
    }
    
    
    
    public Font getTitlebarTitleFont() {
        return titlebarTitleFont ;
    }
    
    public void setTitlebarTitleFont(Font titlebarTitleFont) {
        this.titlebarTitleFont = titlebarTitleFont ;
    }
    
    public Font getTitlebarMetadataFont() {
        return titlebarMetadataFont ;
    }
    
    public void setTitlebarMetadataFont(Font titlebarMetadataFont) {
        this.titlebarMetadataFont = titlebarMetadataFont ;
    }
    
    public int getImageMarginLeft() {
        return imageMarginLeft;
    }

    public void setImageMarginLeft(int imageMarginLeft) {
        this.imageMarginLeft = imageMarginLeft;
    }

    public int getSelectedImageMarginRight() {
        return selectedImageMarginRight;
    }

    public void setSelectedImageMarginRight(int selectedImageMarginRight) {
        this.selectedImageMarginRight = selectedImageMarginRight;
    }

    public int getSelectedImageWidth() {
        return selectedImageWidth;
    }

    public void setSelectedImageWidth(int selectedImageWidth) {
        this.selectedImageWidth = selectedImageWidth;
    }

    public int getTextColor() {
        return textColor;
    }

    public void setTextColor(int textColor) {
        this.textColor = textColor;
    }

    public int getTextColorFocused() {
        return textColorFocused;
    }

    public void setTextColorFocused(int textColorFocused) {
        this.textColorFocused = textColorFocused;
    }


    public void setTextMarginLeftAndRight(int textMarginLeftAndRight) {
        this.textMarginLeftAndRight = textMarginLeftAndRight;
    }

    

    public void setTextMarginTopAndBottom(int textMarginTopAndBottom) {
        this.textMarginTopAndBottom = textMarginTopAndBottom;
    }

    public int getTextOnlyMarginLeft() {
        return textOnlyMarginLeft;
    }

    public void setTextOnlyMarginLeft(int textOnlyMarginLeft) {
        this.textOnlyMarginLeft = textOnlyMarginLeft;
    }

    public String getToggleExclusiveImage() {
        return toggleExclusiveImage;
    }

    public void setToggleExclusiveImage(String toggleExclusiveImage) {
        this.toggleExclusiveImage = toggleExclusiveImage;
    }

    public String getToggleExclusiveImageSelected() {
        return toggleExclusiveImageSelected;
    }

    public void setToggleExclusiveImageSelected(
            String toggleExclusiveImageSelected) {
        this.toggleExclusiveImageSelected = toggleExclusiveImageSelected;
    }

    public String getToggleMultipleImage() {
        return toggleMultipleImage;
    }

    public void setToggleMultipleImage(String toggleMultipleImage) {
        this.toggleMultipleImage = toggleMultipleImage;
    }

    public String getToggleMultipleImageSelected() {
        return toggleMultipleImageSelected;
    }

    public void setToggleMultipleImageSelected(
            String toggleMultipleImageSelected) {
        this.toggleMultipleImageSelected = toggleMultipleImageSelected;
    }
    */
}