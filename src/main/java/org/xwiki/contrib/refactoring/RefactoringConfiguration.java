package org.xwiki.contrib.refactoring;

import java.util.List;

import org.xwiki.contrib.refactoring.relation.WikiRelation;
import org.xwiki.model.reference.DocumentReference;

public interface RefactoringConfiguration
{
    List<WikiRelation> getRelations();

    List<WikiRelation> getRelationsBySource(DocumentReference sourceClass);

    List<WikiRelation> getRelationsBySource(DocumentReference sourceClass, String sourceProperty);
}
