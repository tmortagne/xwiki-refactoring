package org.xwiki.refactoring.internal;

import java.util.Arrays;
import java.util.List;

import javax.inject.Named;

import org.xwiki.bridge.event.DocumentCreatedEvent;
import org.xwiki.bridge.event.DocumentDeletedEvent;
import org.xwiki.bridge.event.DocumentUpdatedEvent;
import org.xwiki.observation.EventListener;
import org.xwiki.observation.event.Event;
import org.xwiki.refactoring.RefactoringConfiguration;

@Named("WikiRelationRefactoring")
public class WikiRelationRefactoring implements EventListener
{
    private static final List<Event> EVENTS = Arrays.<Event> asList(new DocumentDeletedEvent(),
        new DocumentCreatedEvent(), new DocumentUpdatedEvent());
    
    private RefactoringConfiguration configuration;

    public String getName()
    {
        return "WikiRelationRefactoring";
    }

    public List<Event> getEvents()
    {
        return EVENTS;
    }

    public void onEvent(Event event, Object source, Object data)
    {
        
    }
}
