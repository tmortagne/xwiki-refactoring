package org.xwiki.refactoring.internal;

import java.util.Collections;
import java.util.List;

import javax.inject.Named;

import org.xwiki.observation.EventListener;
import org.xwiki.observation.event.Event;
import org.xwiki.refactoring.RefactoringConfiguration;

@Named("WikiRelationRefactoring")
public class WikiRelationRefactoring implements EventListener
{
    private RefactoringConfiguration configuration;

    public String getName()
    {
        return "WikiRelationRefactoring";
    }

    public List<Event> getEvents()
    {
        return Collections.emptyList();
    }

    public void onEvent(Event event, Object source, Object data)
    {

    }
}
