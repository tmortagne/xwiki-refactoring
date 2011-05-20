package org.xwiki.refactoring;

import java.util.List;

import org.xwiki.model.reference.DocumentReference;
import org.xwiki.refactoring.relation.WikiRelation;

public interface RefactoringConfiguration
{
    List<WikiRelation> getRelations();

    List<WikiRelation> getRelationsBySource(DocumentReference sourceClass);

    List<WikiRelation> getRelationsBySource(DocumentReference sourceClass, String sourceProperty);
}
