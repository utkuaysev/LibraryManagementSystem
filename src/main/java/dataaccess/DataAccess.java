package dataaccess;

import java.util.HashMap;
import java.util.List;

import business.Book;
import business.CheckoutRecordEntry;
import business.LibraryMember;

public interface DataAccess { 
	HashMap<String,Book> readBooksMap();
	HashMap<String,User> readUserMap();
	HashMap<String, LibraryMember> readMemberMap();
	void saveNewMember(LibraryMember member);
	void saveAllMember(List<LibraryMember> memberList);
	LibraryMember searchMember(String memberId);
	Book searchBook(String isbnStr);
	void saveNewBook(Book book);
}
