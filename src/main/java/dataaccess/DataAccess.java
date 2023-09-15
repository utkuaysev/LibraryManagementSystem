package dataaccess;

import java.util.HashMap;
import java.util.List;

import business.Book;
import business.LibraryMember;
import dataaccess.DataAccessFacade.StorageType;

public interface DataAccess { 
	HashMap<String,Book> readBooksMap();
	HashMap<String,User> readUserMap();
	HashMap<String, LibraryMember> readMemberMap();
	void saveNewMember(LibraryMember member);
	void saveAllMember(List<LibraryMember> memberList);
}
