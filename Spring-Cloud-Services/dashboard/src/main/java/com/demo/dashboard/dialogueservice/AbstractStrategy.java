package com.demo.dashboard.dialogueservice;

import java.io.IOException;

public interface AbstractStrategy {

    CommonMessageModel generateMessage() throws IOException;
}
