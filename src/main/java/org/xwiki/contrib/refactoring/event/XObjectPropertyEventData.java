package org.xwiki.contrib.refactoring.event;

import com.xpn.xwiki.doc.XWikiDocument;
import com.xpn.xwiki.objects.BaseObject;
import com.xpn.xwiki.objects.BaseProperty;

public class XObjectPropertyEventData extends XObjectEventData
{
    private BaseProperty property;

    public XObjectPropertyEventData(XWikiDocument document, BaseObject object, BaseProperty property)
    {
        super(document, object);

        this.property = property;
    }
}
