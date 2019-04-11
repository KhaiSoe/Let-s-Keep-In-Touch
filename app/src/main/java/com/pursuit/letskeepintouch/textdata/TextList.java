package com.pursuit.letskeepintouch.textdata;

import java.util.List;

public class TextList {

    private List<String> croppedText;

    public TextList(List<String> croppedText) {
        this.croppedText = croppedText;
    }

    public List<String> getCroppedText() {
        return croppedText;
    }
}
