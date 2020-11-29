package com.demo.dashboard.dialogueservice;

import java.io.IOException;

public interface AbstractStrategy {

    Object generateMessage() throws IOException;
}
