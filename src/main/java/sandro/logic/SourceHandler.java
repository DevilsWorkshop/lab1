package sandro.logic;

import sandro.entity.Source;
import sandro.entity.User;
import sandro.service.SourceService;

/**
 * Created by Eugenia Bronfman on 06.11.16.
 */
public class SourceHandler {
    private User user;
    private FeedParser feedParser;
    private SourceService sourceService;

    public SourceHandler(User user) {
        this.user = user;
        feedParser = new FeedParser(user);
        sourceService = new SourceService();
    }

    public Source createSourceFromFeedAddress(String feedURL) {
        return sourceService.add(feedParser.getSourceFromFeedAddress(feedURL));
    }

    public void deleteSource(Source source) {
        sourceService.delete(source.getId());
    }

    public void updateSource(Source source) {
        sourceService.update(source);
    }

    public void setFeedParser(FeedParser feedParser) {
        this.feedParser = feedParser;
    }

    public void setSourceService(SourceService sourceService) {
        this.sourceService = sourceService;
    }
}
