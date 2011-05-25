package org.xwiki.contrib.refactoring.event;

import org.xwiki.model.reference.DocumentReference;

public class XObjectModifiedEvent extends AbstractXObjectEvent
{
    public XObjectModifiedEvent()
    {
    }

    public XObjectModifiedEvent(DocumentReference documentReference, DocumentReference classReference, Integer identifier)
    {
        super(documentReference, classReference, identifier);
    }
}
