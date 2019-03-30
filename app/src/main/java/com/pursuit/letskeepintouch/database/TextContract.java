package com.pursuit.letskeepintouch.database;

import android.provider.BaseColumns;

public class TextContract {

    private TextContract(){

    }

    public static final class TextEntry implements BaseColumns {
        public static final String TABLE_NAME = "textList";
        public static final String COLUMN_NAME = "TextDatabase";
        public static final String COLUMN_TIMESTAMP = "timestamp";
    }
}
