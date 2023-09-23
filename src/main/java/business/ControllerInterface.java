package business;

import java.util.HashMap;
import java.util.List;

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
	LibraryMember searchMember(String memberIdStr);
	Book searchBook(String isbn);
}
