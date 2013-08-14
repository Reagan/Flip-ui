package it.qubixic.component.dynamicImageLoader;

import javax.microedition.lcdui.Image;

public class ImageEntity {
    
    private String imageName ; 
    private Image image ;
    
    public ImageEntity (String imageName, Image image) {
        setImageName(imageName);
        setImage(image);
    }

    /**
     * @return the imageName
     */
    public String getImageName() {
        return imageName;
    }

    /**
     * @param imageName the imageName to set
     */
    public void setImageName(String imageName) {
        this.imageName = imageName;
    }

    /**
     * @return the image
     */
    public Image getImage() {
        return image;
    }

    /**
     * @param image the image to set
     */
    public void setImage(Image image) {
        this.image = image;
    }
}
