package org.xwiki.contrib.refactoring.internal;

import java.util.Arrays;
import java.util.List;

import javax.inject.Inject;
import javax.inject.Named;

import org.slf4j.Logger;
import org.xwiki.bridge.event.DocumentCreatedEvent;
import org.xwiki.bridge.event.DocumentDeletedEvent;
import org.xwiki.bridge.event.DocumentUpdatedEvent;
import org.xwiki.component.annotation.Component;
import org.xwiki.contrib.refactoring.event.XObjectAddedEvent;
import org.xwiki.contrib.refactoring.event.XObjectDeletedEvent;
import org.xwiki.contrib.refactoring.event.XObjectEventData;
import org.xwiki.contrib.refactoring.event.XObjectModifiedEvent;
import org.xwiki.contrib.refactoring.event.XObjectPropertyAddedEvent;
import org.xwiki.contrib.refactoring.event.XObjectPropertyDeletedEvent;
import org.xwiki.contrib.refactoring.event.XObjectPropertyEventData;
import org.xwiki.contrib.refactoring.event.XObjectPropertyModifiedEvent;
import org.xwiki.observation.EventListener;
import org.xwiki.observation.ObservationManager;
import org.xwiki.observation.event.Event;

import com.xpn.xwiki.XWikiContext;
import com.xpn.xwiki.XWikiException;
import com.xpn.xwiki.doc.XWikiDocument;
import com.xpn.xwiki.objects.BaseObject;
import com.xpn.xwiki.objects.BaseProperty;
import com.xpn.xwiki.objects.ObjectDiff;
import com.xpn.xwiki.web.Utils;

@Component
@Named("XObjectModificationsListener")
public class XObjectModificationsListener implements EventListener
{
    private static final List<Event> EVENTS = Arrays.<Event> asList(new DocumentDeletedEvent(),
        new DocumentCreatedEvent(), new DocumentUpdatedEvent());

    @Inject
    private Logger logger;

    public String getName()
    {
        return "XObjectModificationsListener";
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

        ObservationManager observation = Utils.getComponent(ObservationManager.class);

        try {
            for (List<ObjectDiff> objectChanges : doc.getObjectDiff(originalDoc, doc, context)) {
                boolean modified = false;
                for (ObjectDiff diff : objectChanges) {
                    if (ObjectDiff.ACTION_OBJECTREMOVED.equals(diff.getAction())) {
                        observation.notify(
                            new XObjectDeletedEvent(doc.getDocumentReference(), diff.getXClassReference(), diff
                                .getNumber()),
                            new XObjectEventData(doc, originalDoc.getXObject(diff.getXClassReference(),
                                diff.getNumber())), context);
                    } else {
                        BaseObject object = doc.getXObject(diff.getXClassReference(), diff.getNumber());
                        if (ObjectDiff.ACTION_OBJECTADDED.equals(diff.getAction())) {
                            observation.notify(
                                new XObjectAddedEvent(object.getDocumentReference(), object.getXClassReference(),
                                    object.getNumber()), new XObjectEventData(doc, object), context);
                        } else {
                            if (!modified) {
                                observation.notify(
                                    new XObjectModifiedEvent(object.getDocumentReference(),
                                        object.getXClassReference(), object.getNumber()), new XObjectEventData(doc,
                                        object), context);
                                modified = true;
                            }

                            onObjectPropertyModified(observation, originalDoc, diff, context);
                        }
                    }
                }
            }
        } catch (XWikiException e) {
            this.logger.error("Failed to diff documents [" + originalDoc + "] and [" + doc + "]");
        }
    }

    private void onObjectPropertyModified(ObservationManager observation, XWikiDocument doc, ObjectDiff diff,
        XWikiContext context)
    {
        if (ObjectDiff.ACTION_PROPERTYREMOVED.equals(diff.getAction())) {
            BaseObject object = doc.getOriginalDocument().getXObject(diff.getXClassReference(), diff.getNumber());
            BaseProperty property = (BaseProperty) object.getField(diff.getPropName());
            observation.notify(
                new XObjectPropertyDeletedEvent(object.getDocumentReference(), object.getXClassReference(), object
                    .getNumber(), property.getName()), new XObjectPropertyEventData(doc, object, property), context);
        } else {
            BaseObject object = doc.getXObject(diff.getXClassReference(), diff.getNumber());
            BaseProperty property = (BaseProperty) object.getField(diff.getPropName());
            if (ObjectDiff.ACTION_PROPERTYADDED.equals(diff.getAction())) {
                observation
                    .notify(new XObjectPropertyAddedEvent(object.getDocumentReference(), object.getXClassReference(),
                        object.getNumber(), property.getName()), new XObjectPropertyEventData(doc, object, property),
                        context);
            } else if (ObjectDiff.ACTION_PROPERTYCHANGED.equals(diff.getAction())) {
                observation.notify(
                    new XObjectPropertyModifiedEvent(object.getDocumentReference(), object.getXClassReference(), object
                        .getNumber(), property.getName(), diff), new XObjectPropertyEventData(doc, object, property),
                    context);
            }
        }
    }
}
