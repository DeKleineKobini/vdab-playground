package world.inetum.realdolmen.playground.service.persistance.revision;

import org.hibernate.envers.RevisionListener;

public class RevisionListenerImpl implements RevisionListener {

    @Override
    public void newRevision(Object entity) {
        Revision revision = (Revision) entity;

        revision.setUser("some_logged_in_user");
    }

}
