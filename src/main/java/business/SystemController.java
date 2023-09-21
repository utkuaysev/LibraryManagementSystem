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
	private static SystemController systemController;

	public static SystemController getInstance(){
		if(systemController == null){
			systemController = new SystemController();
		}
		return systemController;
	}

	public static Auth currentAuth = null;

	private SystemController(){

	}
	public void login(String id, String password) throws LoginException {
		DataAccess da = DataAccessFacade.getInstance();
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
		DataAccess da = DataAccessFacade.getInstance();
		List<String> retval = new ArrayList<>();
		retval.addAll(da.readMemberMap().keySet());
		return retval;
	}
	
	@Override
	public List<String> allBookIds() {
		DataAccess da = DataAccessFacade.getInstance();
		List<String> retval = new ArrayList<>();
		retval.addAll(da.readBooksMap().keySet());
		return retval;
	}

	@Override
	public List<LibraryMember> allMembers() {
		DataAccessFacade dataAccessFacade = DataAccessFacade.getInstance();
		HashMap<String, LibraryMember> memberHashMap = dataAccessFacade.readMemberMap();
		List<LibraryMember> members = new ArrayList<>(memberHashMap.values());
		members.sort(Comparator.comparing(LibraryMember::getMemberId));
		return members;
	}

	@Override
	public HashMap<String, LibraryMember> allMemberMap() {
		return DataAccessFacade.getInstance().readMemberMap();
	}

	@Override
	public void saveNewMember(LibraryMember member) {
		DataAccessFacade.getInstance().saveNewMember(member);
	}

	@Override
	public void saveAllMember(List<LibraryMember> memberList) {
		DataAccessFacade.getInstance().saveAllMember(memberList);
	}

	@Override
	public List<Book> allBooks() {
		HashMap<String, Book> booksMap = DataAccessFacade.getInstance().readBooksMap();
		return booksMap.values().stream().toList();
	}

	@Override
	public void saveNewBook(Book book) {
		DataAccessFacade.getInstance().saveNewBook(book);
	}

	@Override
	public LibraryMember searchMember(String memberIdStr) {
		return DataAccessFacade.getInstance().searchMember(memberIdStr);
	}

	@Override
	public Book searchBook(String isbn) {
		return DataAccessFacade.getInstance().searchBook(isbn);
	}

}
