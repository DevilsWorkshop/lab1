package sandro;

import org.junit.Test;
import sandro.entity.Source;
import sandro.entity.User;
import sandro.logic.*;
import sandro.service.SourceService;

import java.io.IOException;

import static org.mockito.ArgumentMatchers.anyString;
import static org.mockito.Mockito.*;

/**
 * Created by alex__000 on 11/6/2016.
 */
public class FeedParserTest {

    @Test (expected=RuntimeException.class)
    public void testGetSourceFromFeedAddress () {
        User user = mock(User.class);
        FeedParser parser = new FeedParser(user);
        String url = "not an address";
        parser.getSourceFromFeedAddress(url);
    }
}
