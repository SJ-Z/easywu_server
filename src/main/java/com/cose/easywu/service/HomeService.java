package com.cose.easywu.service;

import com.cose.easywu.po.FindData;
import com.cose.easywu.po.HomeData;

public interface HomeService {

    HomeData getHomeData(String u_id);
    FindData getFindHomeData(String u_id);
}
