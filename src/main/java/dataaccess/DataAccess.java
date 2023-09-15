package dataaccess;

import java.util.HashMap;
import java.util.List;

import business.Book;
import business.CheckoutRecord;
import business.CheckoutRecordEntry;
import business.LibraryMember;
import dataaccess.DataAccessFacade.StorageType;

public interface DataAccess { 
	HashMap<String,Book> readBooksMap();
	HashMap<String,User> readUserMap();
	HashMap<String, LibraryMember> readMemberMap();
//	public HashMap<String, CheckoutRecordEntry> readCheckoutRecordMap();

	void saveNewCheckoutRecordEntry(CheckoutRecordEntry entry);

	HashMap<String, CheckoutRecordEntry> readCheckoutRecordEntryMap();



	void saveNewMember(LibraryMember member);
	void saveAllMember(List<LibraryMember> memberList);
	LibraryMember searchMember(String memberId);
	Book searchBook(String isbnStr);
	void saveBook(Book book);
}
