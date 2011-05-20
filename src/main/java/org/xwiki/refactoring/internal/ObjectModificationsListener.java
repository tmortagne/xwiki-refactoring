package org.xwiki.refactoring.internal;

import java.util.Arrays;
import java.util.List;

import javax.inject.Inject;
import javax.inject.Named;

import org.apache.commons.lang.StringUtils;
import org.xwiki.bridge.event.DocumentCreatedEvent;
import org.xwiki.bridge.event.DocumentDeletedEvent;
import org.xwiki.bridge.event.DocumentUpdatedEvent;
import org.xwiki.component.annotation.Component;
import org.xwiki.observation.EventListener;
import org.xwiki.observation.ObservationManager;
import org.xwiki.observation.event.Event;
import org.xwiki.refactoring.RefactoringConfiguration;

import com.xpn.xwiki.XWikiContext;
import com.xpn.xwiki.doc.XWikiDocument;
import com.xpn.xwiki.internal.event.CommentAddedEvent;
import com.xpn.xwiki.internal.event.CommentDeletedEvent;
import com.xpn.xwiki.internal.event.CommentUpdatedEvent;
import com.xpn.xwiki.objects.ObjectDiff;
import com.xpn.xwiki.web.Utils;

@Component
@Named("ObjectModificationsListener")
public class ObjectModificationsListener implements EventListener
{
    private static final List<Event> EVENTS = Arrays.<Event> asList(new DocumentDeletedEvent(),
        new DocumentCreatedEvent(), new DocumentUpdatedEvent());
    
    @Inject
    private RefactoringConfiguration configuration;

    public String getName()
    {
        return "ObjectModificationsListener";
    }

    public List<Event> getEvents()
    {
        return EVENTS;
    }

    public void onEvent(Event event, Object source, Object data)
    {
        XWikiDocument doc = (XWikiDocument) source;
        XWikiDocument originalDoc = doc.getOriginalDocument();
        XWikiContext context = (XWikiContext) data;
        
        ObservationManager om = Utils.getComponent(ObservationManager.class);
        
        for (List<ObjectDiff> objectChanges : doc.getObjectDiff(originalDoc, doc, context)) {
            for (ObjectDiff diff : objectChanges) {
                if (StringUtils.equals(diff.getClassName(), "XWiki.XWikiComments")) {
                    if (StringUtils.equals(diff.getAction(), "object-removed")) {
                        om.notify(new CommentDeletedEvent(reference, diff.getNumber() + ""), source, data);
                    } else if (StringUtils.equals(diff.getAction(), "object-added")) {
                        om.notify(new CommentAddedEvent(reference, diff.getNumber() + ""), source, data);
                    } else {
                        om.notify(new CommentUpdatedEvent(reference, diff.getNumber() + ""), source, data);
                    }
                }
                break;
            }
        }
    }
}
