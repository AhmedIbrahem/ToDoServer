package todolistserver.model;

/**
 *
 * @author Ibrahim
 */
public interface DatabaseQueries {
    //user
    String LOGIN_USER_QUERY = "SELECT * FROM USERS WHERE USERNAME = ? AND PASSWORD = ?";
    String REGISTER_USER_QUERY = "NSERT INTO USERS VALUES(?, ?, ?, ?)";
    String RETRIEVE_USERS_QUERY = "SELECT * FROM USERS";
    String RETRIEVE_USER_FRIENDS = "SELECT * FROM FRIENDLIST WHERE USERID = ?";
    
    //todos
    String INSERT_TODO_LIST_QUERY = "INSERT INTO TODOLIST VALUES(?, ?, ?, ?, ?, ?, ?)";
    String UPDATE_TODO_LIST_QUERY = "UPDATE TODOLIST SET TITLE = ?, DESCRIPTION = ?, DEADLINEDATE = ?, ASSIGNINGDATE = ?, BACKGROUNDCOLOR = ?, STATUS = ? WHERE TODOID = ?";
    String DELETE_TODO_LIST_QUERY = "DELETE FROM TODOLIST WHERE TODOID = ?";
    String RETRIEVE_ALL_TODO_LISTS_QUERY = "SELECT * FROM TODOLIST WHERE CREATORID = ?";
    
    //items
    String INSERT_ITEM_QUERY = "INSERT INTO ITEM VALUES(?, ?, ?, ?, ?)";
    String UPDATE_ITEM_QUERY = "UPDATE ITEM SET TITLE = ?, DESCRIPTION = ? TODOID = ?, CREATORID = ?, DEADLINE = ? WHERE ITEMID = ?";
    String DELETE_ITEM_QUERY = "DELETE FROM ITEM WHERE ITEMID = ? and CREATORID = ?";
    String RETRIEVE_ALL_ITEMS_QUERY = "SELECT * FROM ITEM WHERE TODOID = ?"; 
    
    //collaborator 
    //String ASSIGN_FRIEND_TO_iTEM = "";
    //String ASSIGN_FRIEND_TO_iTEM = "";
    //String ASSIGN_FRIEND_TO_iTEM = "";
    //String ASSIGN_FRIEND_TO_iTEM = "";
}
