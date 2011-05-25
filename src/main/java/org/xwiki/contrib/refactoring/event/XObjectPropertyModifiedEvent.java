package org.xwiki.contrib.refactoring.event;

import org.xwiki.model.reference.DocumentReference;

import com.xpn.xwiki.objects.ObjectDiff;

public class XObjectPropertyModifiedEvent extends AbstractXObjectPropertyEvent
{
    private ObjectDiff diff;

    public XObjectPropertyModifiedEvent()
    {
    }

    public XObjectPropertyModifiedEvent(DocumentReference documentReference, DocumentReference classReference,
        Integer identifier, String property, ObjectDiff diff)
    {
        super(documentReference, classReference, identifier, property);

        this.diff = diff;
    }
}
