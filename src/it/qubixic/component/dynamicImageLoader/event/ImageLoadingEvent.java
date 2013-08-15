package it.qubixic.component.dynamicImageLoader.event;

public class ImageLoadingEvent {
    
    private String imageName ;
    private int loadedStatus ;
    
    /**
     * Creates an Image loaded event
     * @param imageName the name of the image loaded
     * @param loadingStatus the loading status of the image
     */
    public ImageLoadingEvent(String iName, int lStatus) {
        setImageName(iName);
        setLoadedStatus(lStatus);
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
     * @return the loadedStatus
     */
    public int getLoadedStatus() {
        return loadedStatus;
    }

    /**
     * @param loadedStatus the loadedStatus to set
     */
    public void setLoadedStatus(int loadedStatus) {
        this.loadedStatus = loadedStatus;
    }
}
