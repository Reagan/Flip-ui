package it.qubixic.component.dynamicImageLoader;

import java.util.Vector;
import javax.microedition.lcdui.Image;

public class ImageCache {
    
    private Vector cachedImageEntities = new Vector() ;
    
    /**
     * Creates an instance of an image-cache
     */
    public ImageCache() {}
    
    /**
     * Adds an imageEntity to the cache
     * @param imageEntity 
     */
    public void addImageEntity(ImageEntity imageEntity) throws OutOfMemoryError {
        if (!cachedImageEntities.contains(imageEntity)){
            try {
                cachedImageEntities.addElement(imageEntity);
            } catch (OutOfMemoryError e) {
                e.printStackTrace();
                clear();
            }
        }
    }
    
    /**
     * Adds a set of imageEntities to the cache
     * @param imageEntities the set of imageEntities to be 
     * added to the cache
     */
    public void addImageEntities(Vector imageEntities) {
        if (imageEntities != null && imageEntities.size() > 0) {
            for (int imagesEntitiesCounter = 0 ; imagesEntitiesCounter < imageEntities.size();
                    imagesEntitiesCounter++) {
                try {
                    addImageEntity((ImageEntity) imageEntities
                            .elementAt(imagesEntitiesCounter));
                } catch (OutOfMemoryError e) {
                    e.printStackTrace();
                    clear();
                    break ;
                }
            }
        }
    }
    
    /**
     * Clears the entire cache of stored images
     */
    public void clear() {
        cachedImageEntities.removeAllElements();
    }
    
    /**
     * removes an imageEntity from the cache
     * @param imageEntity 
     */
    public void removeImageEntity(ImageEntity imageEntity) {
        cachedImageEntities.removeElement(imageEntity);
    }
    
    /**
     * removes a set of imageEntities from the cache
     * @param imageEntities 
     */
    public void removeImageEntities (Vector imageEntities) {
        if(imageEntities != null && imageEntities.size() > 0) {
            for (int imagesEntitiesCounter = 0 ; 
                    imagesEntitiesCounter < imageEntities.size();
                    imagesEntitiesCounter++) {
                removeImageEntity((ImageEntity)imageEntities.elementAt(imagesEntitiesCounter));
            }
        }
    }
    
    /**
     * Determines if the image cache contains an image 
     * with a specific name
     * @param imageName name of the image to be searched
     * @return whether an image with the particular name has been 
     * found in the cache
     */
    public boolean contains (String imageName) {
        for (int imagesEntitiesCounter = 0 ; 
                    imagesEntitiesCounter < cachedImageEntities.size();
                    imagesEntitiesCounter++) {
            String currEntityName 
                    = ((ImageEntity) cachedImageEntities.elementAt(imagesEntitiesCounter)).getImageName() ;
            if(imageName.equals(currEntityName)) {
                return true ;
            }
        }
        return false ;
    }
    
    /**
     * Determines if an image has been found in the cache
     * @param image image being searched within the cache
     * @return  whether or not the image has been found in
     * in the cache
     */
    public boolean contains (Image image) {
        for (int imagesEntitiesCounter = 0 ; 
                    imagesEntitiesCounter < cachedImageEntities.size();
                    imagesEntitiesCounter++) {
            Image currEntityImage 
                    = ((ImageEntity) cachedImageEntities.elementAt(imagesEntitiesCounter)).getImage() ;
            if(image.equals(currEntityImage)) {
                return true ;
            }
        }
        return false ;
    }
    
    /**
     * @param imageName the image name for the searched entity
     * @return an image entity with a particular image name
     */
    public ImageEntity get(String imageName) {
        ImageEntity imageEntity = null ;
        for (int imagesEntitiesCounter = 0;
                imagesEntitiesCounter < cachedImageEntities.size();
                imagesEntitiesCounter++) {
            ImageEntity currImageEntity = (ImageEntity) cachedImageEntities
                    .elementAt(imagesEntitiesCounter);
            String currEntityImageName = currImageEntity.getImageName();
            if (imageName.equals(currEntityImageName)) {
               imageEntity = currImageEntity;
            }
        }   
        return imageEntity ;
    }
    
    /**
     * 
     * @param image the image whose entity is being searched for
     * @return an image entity for the particular image
     */
    public ImageEntity get(Image image) {
        ImageEntity imageEntity = null ;
        for (int imagesEntitiesCounter = 0;
                imagesEntitiesCounter < cachedImageEntities.size();
                imagesEntitiesCounter++) {
            ImageEntity currImageEntity = (ImageEntity) cachedImageEntities
                    .elementAt(imagesEntitiesCounter);
            Image currEntityImageName = currImageEntity.getImage();
            if (image.equals(currEntityImageName)) {
               imageEntity = currImageEntity;
            }
        }   
        return imageEntity ;
    }
}
