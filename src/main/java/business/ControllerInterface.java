package business;

import java.util.HashMap;
import java.util.List;

import business.Book;
import dataaccess.DataAccess;
import dataaccess.DataAccessFacade;

public interface ControllerInterface {
	void login(String id, String password) throws LoginException;
	List<String> allMemberIds();
	List<String> allBookIds();
	List<LibraryMember> allMembers();
	HashMap<String, LibraryMember> allMemberMap();
	void saveNewMember(LibraryMember member);
	void saveAllMember(List<LibraryMember> memberList);
	List<Book> allBooks();
	void saveNewBook(Book book);
}
