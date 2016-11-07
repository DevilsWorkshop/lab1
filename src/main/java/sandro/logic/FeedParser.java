package sandro.logic;

import sandro.entity.Source;
import sandro.entity.User;

import javax.xml.stream.XMLEventReader;
import javax.xml.stream.XMLInputFactory;
import javax.xml.stream.XMLStreamException;
import javax.xml.stream.events.Characters;
import javax.xml.stream.events.XMLEvent;
import java.io.IOException;
import java.io.InputStream;
import java.net.MalformedURLException;
import java.net.URL;

/**
 * Created by Eugenia Bronfman on 06.11.16.
 */
public class FeedParser {
    static final String CHANNEL = "channel";
    static final String TITLE = "title";
    static final String DESCRIPTION = "description";
    static final String LINK = "link";
    static final String AUTHOR = "author";
    static final String ITEM = "item";
    static final String PUB_DATE = "pubDate";

    private User user;

    public FeedParser(User user) {
        this.user = user;
    }

    public Source getSourceFromFeedAddress(String feedURL) throws RuntimeException {
        URL url;
        try {
            url = new URL(feedURL);
        } catch (MalformedURLException e) {
            throw new RuntimeException(e);
        }

        boolean isFeedHeader = true;
        String title = "";
        String link = "";
        String description = "";
        String author = "";
        String category = "";
        String pubDate = "";
        Source source = null;

        XMLInputFactory inputFactory = XMLInputFactory.newInstance();
        InputStream in = read(url);
        XMLEventReader eventReader;
        try {
            eventReader = inputFactory.createXMLEventReader(in);
        } catch (XMLStreamException e) {
            throw new RuntimeException(e);
        }
        while (eventReader.hasNext()) {
            XMLEvent event = null;
            try {
                event = eventReader.nextEvent();
            } catch (XMLStreamException e) {
                throw new RuntimeException(e);
            }
            if (event.isStartElement()) {
                String localPart = event.asStartElement().getName().getLocalPart();
                switch (localPart) {
                    case ITEM:
                        if (isFeedHeader) {
                            isFeedHeader = false;
                            source = new Source(title, feedURL, user); //TODO: add link, possibly description and image
                            title = "";
                            link = "";
                            description = "";
                            category = "";
                            pubDate = "";
                        }
                        break;
                    case TITLE:
                        try {
                            title = getCharacterData(event, eventReader);
                        } catch (XMLStreamException e) {
                            e.printStackTrace();
                        }
                        break;
                    case DESCRIPTION:
                        try {
                            description = getCharacterData(event, eventReader);
                        } catch (XMLStreamException e) {
                            e.printStackTrace();
                        }
                        break;
                    case LINK:
                        try {
                            link = getCharacterData(event, eventReader);
                        } catch (XMLStreamException e) {
                            e.printStackTrace();
                        }
                        break;
                    case AUTHOR:
                        try {
                            author = getCharacterData(event, eventReader);
                        } catch (XMLStreamException e) {
                            e.printStackTrace();
                        }
                        break;
                    case PUB_DATE:
                        try {
                            pubDate = getCharacterData(event, eventReader);
                        } catch (XMLStreamException e) {
                            e.printStackTrace();
                        }
                        break;
                    default:
                        break;
                }
            } else if (event.isEndElement()) {
                if (event.asEndElement().getName().getLocalPart() == (ITEM)) {
                    //TODO: add article
                    title = "";
                    link = "";
                    description = "";
                    author = "";
                    category = "";
                    pubDate = "";
                    continue;
                }
            }
        }
    return source;
    }

    public void getSourceFromPage(String pageURL) throws RuntimeException {
        try {
            URL url = new URL(pageURL);
        } catch (MalformedURLException e) {
            throw new RuntimeException(e);
        }
        //TODO: check if HTML page exists
        String feedURL = "";
        //TODO: get feed URL from page
        getSourceFromFeedAddress(feedURL);
    }

    private InputStream read(URL url) {
        try {
            return url.openStream();
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    private String getCharacterData(XMLEvent event, XMLEventReader eventReader)
            throws XMLStreamException {
        String result = "";
        event = eventReader.nextEvent();
        if (event instanceof Characters) {
            result = event.asCharacters().getData();
        }
        return result;
    }

}

