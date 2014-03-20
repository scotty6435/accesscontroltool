package biz.netcentric.cq.tools.actool.authorizableutils;

import java.util.HashSet;
import java.util.Iterator;
import java.util.Set;

import javax.jcr.RepositoryException;

import org.apache.jackrabbit.api.security.user.Authorizable;
import org.apache.jackrabbit.api.security.user.Group;

public class AuthorizableWrapper {

	Authorizable authorizable;

	public AuthorizableWrapper(Authorizable authorizable) {
		this.authorizable = authorizable;
	}
	public Authorizable getAuthorizable() {
		return authorizable;
	}

	public void setAuthorizable(Authorizable authorizable) {
		this.authorizable = authorizable;
	}

	public Set<Authorizable> getMembers() throws RepositoryException{
		Set<Authorizable> authorizables = new HashSet<Authorizable>();

		if(this.authorizable.isGroup()){
			Group group = (Group) this.authorizable;
			Iterator<Authorizable>  it = group.getDeclaredMembers();
			while(it.hasNext()){
				authorizables.add(it.next());
			}
		}
		return authorizables;
	}
	public Set<Group> getMembershipGroups() throws RepositoryException{
		Set<Group> authorizables = new HashSet<Group>();
		Iterator<Group>  it = this.authorizable.declaredMemberOf();
		while(it.hasNext()){
			authorizables.add(it.next());
		}
		return authorizables;
	}
}
