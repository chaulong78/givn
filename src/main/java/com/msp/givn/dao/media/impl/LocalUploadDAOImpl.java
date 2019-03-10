package com.msp.givn.dao.media.impl;

import com.msp.givn.dao.media.LocalUploadDAO;
import org.springframework.mock.web.MockMultipartFile;
import org.springframework.stereotype.Repository;
import org.springframework.web.multipart.MultipartFile;

import javax.imageio.IIOImage;
import javax.imageio.ImageIO;
import javax.imageio.ImageWriteParam;
import javax.imageio.ImageWriter;
import javax.imageio.stream.ImageOutputStream;
import javax.servlet.http.HttpServletRequest;
import java.awt.image.BufferedImage;
import java.io.*;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.HashMap;
import java.util.Iterator;

@Repository
public class LocalUploadDAOImpl implements LocalUploadDAO {

    @Override
    public InputStream doLocalOne(HttpServletRequest request, MultipartFile file) {
        String uploadRootPath = request.getServletContext().getRealPath("media");

        File newFile = convertMultiToFile(file, uploadRootPath);
        try {
            newFile = compressPhoto(newFile, uploadRootPath);
        } catch (IOException e) {
            e.printStackTrace();
        }
        file = convertFileToMulti(newFile);

        File uploadRootDir = new File(uploadRootPath);
        if (!uploadRootDir.exists()) {
            uploadRootDir.mkdirs();
        }

        String name = file.getOriginalFilename();
        String localFileUrl = "";

        if (name != null && name.length() > 0) {
            try {
                File serverFile = new File(uploadRootDir.getAbsolutePath() + File.separator + name);
                BufferedOutputStream stream = new BufferedOutputStream(new FileOutputStream(serverFile));
                stream.write(file.getBytes());
                stream.close();

                localFileUrl = serverFile.toString();
            } catch (Exception e) {
                e.printStackTrace();
            }
        }

        InputStream stream = null;
        try {
            stream = new FileInputStream(localFileUrl);
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }

        return stream;
    }

    @Override
    public HashMap<String, InputStream> doLocalMulti(HttpServletRequest request, MultipartFile[] files) {
        String uploadRootPath = request.getServletContext().getRealPath("media");

        File uploadRootDir = new File(uploadRootPath);
        if (!uploadRootDir.exists()) {
            uploadRootDir.mkdirs();
        }

        String name = "";
        String localFileUrl = "";

        HashMap<String, InputStream> streamList = new HashMap<>();
        InputStream inputStream = null;

        File serverFile = null;
        BufferedOutputStream stream = null;
        File newFile = null;

        for (MultipartFile file : files) {
            name = file.getOriginalFilename();

            newFile = convertMultiToFile(file, uploadRootPath);
            try {
                newFile = compressPhoto(newFile, uploadRootPath);
            } catch (IOException e) {
                e.printStackTrace();
            }
            file = convertFileToMulti(newFile);

            if (name != null && name.length() > 0) {
                try {
                    serverFile = new File(uploadRootDir.getAbsolutePath() + File.separator + name);
                    stream = new BufferedOutputStream(new FileOutputStream(serverFile));
                    stream.write(file.getBytes());
                    stream.close();

                    localFileUrl = serverFile.toString();
                    try {
                        inputStream = new FileInputStream(localFileUrl);
                    } catch (FileNotFoundException e) {
                        e.printStackTrace();
                    }

                    streamList.put(localFileUrl, inputStream);
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }
        }

        return streamList;
    }

    private File compressPhoto(File inputFile, String uploadRootPath) throws IOException {
        File compressFile = new File(uploadRootPath + "\\comp_" + inputFile.getName());

        InputStream inputStream = new FileInputStream(inputFile);
        OutputStream outputStream = new FileOutputStream(compressFile);

        float imageQuality = 0.3f;

        BufferedImage bufferedImage = ImageIO.read(inputStream);

        Iterator<ImageWriter> imageWriters = ImageIO.getImageWritersByFormatName("jpg");

        if (!imageWriters.hasNext()) {
            throw new IllegalStateException("Writers not found!");
        }

        ImageWriter imageWriter = imageWriters.next();
        ImageOutputStream imageOutputStream = ImageIO.createImageOutputStream(outputStream);
        imageWriter.setOutput(imageOutputStream);

        ImageWriteParam imageWriteParam = imageWriter.getDefaultWriteParam();
        imageWriteParam.setCompressionMode(ImageWriteParam.MODE_EXPLICIT);
        imageWriteParam.setCompressionQuality(imageQuality);

        imageWriter.write(null, new IIOImage(bufferedImage, null, null), imageWriteParam);

        inputStream.close();
        outputStream.close();
        imageOutputStream.close();
        imageWriter.dispose();

        return compressFile;
    }

    private File convertMultiToFile(MultipartFile file, String uploadRootPath) {
        File convFile = new File(uploadRootPath + "\\" + file.getOriginalFilename());
        try {
            convFile.createNewFile();
            FileOutputStream fos = new FileOutputStream(convFile);
            fos.write(file.getBytes());
            fos.close();
        } catch (IOException e) {
            e.printStackTrace();
        }

        return convFile;
    }

    private MultipartFile convertFileToMulti(File file) {
        Path path = Paths.get(file.getAbsolutePath());
        String name = file.getName();
        String contentType = "text/plain";
        byte[] content = null;

        try {
            content = Files.readAllBytes(path);
        } catch (Exception e) {
            e.printStackTrace();
        }

        MultipartFile multipartFile =
                new MockMultipartFile
                        (name, name, contentType, content);

        return multipartFile;
    }
}
