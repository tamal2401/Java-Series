package com.cloud.zuul.client;

import com.cloud.zuul.model.FallbackModel;

public interface FallbackService {

    String call(FallbackModel model);
}
