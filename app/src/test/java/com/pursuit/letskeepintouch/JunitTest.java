package com.pursuit.letskeepintouch;

import com.pursuit.letskeepintouch.database.TextDatabase;

import org.junit.Assert;
import org.junit.Test;

public class JunitTest {

    private TextDatabase textDatabase;
    @Test
    public void is_there_db() {

      TextDatabase textDatabaseExpected = textDatabase.getInstance();
      Assert.assertEquals(textDatabaseExpected, TextDatabase.getInstance());

    }

}
