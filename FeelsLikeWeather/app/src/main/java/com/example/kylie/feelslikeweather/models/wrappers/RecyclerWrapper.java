package com.example.kylie.feelslikeweather.models.wrappers;

import com.example.kylie.feelslikeweather.utils.RecyclerComponentEnum;

/**
 * Created by Kylie on 2017-08-31.
 */

public class RecyclerWrapper {
    private RecyclerComponentEnum recyclerType = RecyclerComponentEnum.BANNER;

    public RecyclerComponentEnum getRecyclerType(){
        return recyclerType;
    }

    public void setRecyclerType(RecyclerComponentEnum typeEnum){
        recyclerType = typeEnum;
    }
}
