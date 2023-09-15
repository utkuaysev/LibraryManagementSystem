package business;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.HashMap;
import java.util.List;

import dataaccess.Auth;
import dataaccess.DataAccess;
import dataaccess.DataAccessFacade;
import dataaccess.User;

public class SystemController implements ControllerInterface {
	public static Auth currentAuth = null;
	
	public void login(String id, String password) throws LoginException {
		DataAccess da = new DataAccessFacade();
		HashMap<String, User> map = da.readUserMap();
		if(!map.containsKey(id)) {
			throw new LoginException("ID " + id + " not found");
		}
		String passwordFound = map.get(id).getPassword();
		if(!passwordFound.equals(password)) {
			throw new LoginException("Password incorrect");
		}
		currentAuth = map.get(id).getAuthorization();
		
	}
	@Override
	public List<String> allMemberIds() {
		DataAccess da = new DataAccessFacade();
		List<String> retval = new ArrayList<>();
		retval.addAll(da.readMemberMap().keySet());
		return retval;
	}
	
	@Override
	public List<String> allBookIds() {
		DataAccess da = new DataAccessFacade();
		List<String> retval = new ArrayList<>();
		retval.addAll(da.readBooksMap().keySet());
		return retval;
	}

	@Override
	public List<LibraryMember> allMembers() {
		DataAccessFacade dataAccessFacade = new DataAccessFacade();
		HashMap<String, LibraryMember> memberHashMap = dataAccessFacade.readMemberMap();
		List<LibraryMember> members = new ArrayList<>(memberHashMap.values());
		members.sort(Comparator.comparing(LibraryMember::getMemberId));
		return members;
	}

	@Override
	public HashMap<String, LibraryMember> allMemberMap() {
		return new DataAccessFacade().readMemberMap();
	}

	@Override
	public void saveNewMember(LibraryMember member) {
		new DataAccessFacade().saveNewMember(member);
	}

	@Override
	public void saveAllMember(List<LibraryMember> memberList) {
		new DataAccessFacade().saveAllMember(memberList);
	}

	
}
