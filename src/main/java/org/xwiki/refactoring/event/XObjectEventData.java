package org.xwiki.refactoring.event;

import com.xpn.xwiki.doc.XWikiDocument;
import com.xpn.xwiki.objects.BaseObject;

public class XObjectEventData
{
    private XWikiDocument document;

    private BaseObject object;

    public XObjectEventData(XWikiDocument document, BaseObject object)
    {
        this.document = document;
        this.object = object;
    }
}
