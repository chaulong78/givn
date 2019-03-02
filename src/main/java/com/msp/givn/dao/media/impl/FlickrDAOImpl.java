package com.msp.givn.dao.media.impl;

import com.flickr4java.flickr.Flickr;
import com.flickr4java.flickr.FlickrException;
import com.flickr4java.flickr.REST;
import com.flickr4java.flickr.RequestContext;
import com.flickr4java.flickr.auth.Auth;
import com.flickr4java.flickr.auth.AuthInterface;
import com.flickr4java.flickr.auth.Permission;
import com.flickr4java.flickr.uploader.UploadMetaData;
import com.msp.givn.dao.media.FlickrDAO;
import org.scribe.model.Token;
import org.scribe.model.Verifier;
import org.springframework.stereotype.Repository;

import javax.swing.*;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Repository
public class FlickrDAOImpl implements FlickrDAO {

    private Flickr flickr;

    private UploadMetaData uploadMetaData = new UploadMetaData();

    private String apiKey = "b08d3e7107f4a2b1241427c8c4caf0f9";

    private String sharedSecret = "40da62c893eb1f4a";

    private void connect() {
        flickr = new Flickr(apiKey, sharedSecret, new REST());
        Auth auth = new Auth();
        auth.setPermission(Permission.READ);
        auth.setToken("72157703719349592-4e425a67a8539423");
        auth.setTokenSecret("56d5da008f4ae8b8");
        RequestContext requestContext = RequestContext.getRequestContext();
        requestContext.setAuth(auth);
        flickr.setAuth(auth);
    }

    public void auth() {
        flickr = new Flickr(apiKey, sharedSecret, new REST());
        AuthInterface authInterface = flickr.getAuthInterface();

        Token token = authInterface.getRequestToken();
        System.out.println("TOKEN: " + token);

        String url = authInterface.getAuthorizationUrl(token, Permission.DELETE);
        System.out.println("Follow this url to authorize yourself on Flickr: " + url);
        System.out.println("Paste in the token it gives your");
        System.out.println(">>");

        String tokenKey = JOptionPane.showInputDialog(null);

        Token requestToken = authInterface.getAccessToken(token, new Verifier(tokenKey));
        System.out.println("Authentication success");

        Auth auth = null;
        try {
            auth = authInterface.checkToken(requestToken);
        } catch (FlickrException e) {
            e.printStackTrace();
        }

        /*This token can be used until the user revokes it*/
        System.out.println("Token: " + requestToken.getToken());
        System.out.println("Secret: " + requestToken.getSecret());
        System.out.println("nsid: " + auth.getUser().getId());
        System.out.println("Real name: " + auth.getUser().getRealName());
        System.out.println("Username: " + auth.getUser().getUsername());
        System.out.println("Permission: " + auth.getPermission());
    }

    @Override
    public String savePhoto(InputStream photo, String fileName) {
        connect();
        uploadMetaData.setTitle(fileName);
        try {
            String photoId = flickr.getUploader().upload(photo, uploadMetaData);
            return flickr.getPhotosInterface().getPhoto(photoId).getMedium640Url();
        } catch (FlickrException e) {
            e.printStackTrace();
        }
        return null;
    }

    @Override
    public List<String> savePhotoMulti(HashMap<String, InputStream> photoList) {
        connect();
        List<String> urlList = new ArrayList<>();
        String photoId = null;

        for (Map.Entry<String, InputStream> photo : photoList.entrySet()) {
            uploadMetaData.setTitle(photo.getKey());

            try {
                photoId = flickr.getUploader().upload(photo.getValue(), uploadMetaData);
                urlList.add(flickr.getPhotosInterface().getPhoto(photoId).getMedium640Url());

            } catch (FlickrException e) {
                e.printStackTrace();
            }
        }

        return urlList;
    }

    @Override
    public void delete(String photoId) {
        connect();
        try {
            flickr.getPhotosInterface().delete(photoId);
        } catch (FlickrException e) {
            e.printStackTrace();
        }
    }
}
