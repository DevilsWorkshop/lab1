package sandro;

import org.junit.Test;
import sandro.entity.Source;
import sandro.entity.User;
import sandro.logic.*;
import sandro.service.SourceService;

import static org.mockito.ArgumentMatchers.anyString;
import static org.mockito.Mockito.*;

/**
 * Created by Eugenia Bronfman on 06.11.16.
 */
public class SourceHandlerTest {

    @Test
    public void testCreateSourceFromFeedAddress () {
        User user = mock(User.class);
        FeedParser parser = mock(FeedParser.class);
        SourceService sourceService = mock(SourceService.class);

        SourceHandler sourceHandler = new SourceHandler(user);
        sourceHandler.setFeedParser(parser);
        sourceHandler.setSourceService(sourceService);

        String url = "roman-shmarakov.livejournal.com/data/rss";
        Source source = new Source("Acta Diurna", url, user);
        when(parser.getSourceFromFeedAddress(url)).thenReturn(source);
        sourceHandler.createSourceFromFeedAddress(url);
        verify(sourceService).add(source);
    }
}
