package com.msp.givn.dao.media;

import java.io.InputStream;
import java.util.HashMap;
import java.util.List;

public interface FlickrDAO {

    String savePhoto(InputStream stream, String fileName);

    List<String> savePhotoMulti(HashMap<String, InputStream> photoList);

    void delete(String photoId);
}
