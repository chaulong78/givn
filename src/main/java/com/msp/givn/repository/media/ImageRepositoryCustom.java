package com.msp.givn.repository.media;

import java.util.List;

public interface ImageRepositoryCustom {

    void saveAllImage(List<String> urlList);

    String findUrlById(int id);
}
