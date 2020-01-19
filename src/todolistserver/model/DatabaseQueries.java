package todolistserver.model;

/**
 *
 * @author Ibrahim
 */
public interface DatabaseQueries {
    //user
    String LOGIN_USER_QUERY = "SELECT * FROM USERS where username=? and password= ?";
    String REGISTER_USER_QUERY = "INSERT INTO USERS VALUES(?, ?, ?, ?)";
    String RETRIEVE_USERS_QUERY = "SELECT * FROM USERS";
    String RETRIEVE_ONLINE_USERS_QUERY = "SELECT * FROM USERS WHERE onlineFlag =1";
    String RETRIEVE_OFFLINE_USERS_QUERY = "SELECT * FROM USERS WHERE onlineFlag =0";
    String RETRIEVE_USER_FRIENDS = "SELECT * FROM FRIENDLIST WHERE USERID = ?";
    String UPDATE_ONLINE_FLAG = "UPDATE USERS SET onLineFlag = ? where username = ?";
    String RETRIEVE_All_USERS_EXPICTED_LOFIN_USER = " select * from users where userID<>? and users.userID  not in (select userID from users where users.userID in(select friendID from friendList where userID=?  UNION select userID from friendList where friendID=?))";
    String LOGOUT_QUARY="update users set onlineFlag=0 where userID =?";
    String GET_ALL_FRIENDS_ASSIGNED_TO_TODO = "select users.* from users, todoList, toDoListUsers where todoList.todoID = toDoListUsers.todoID and users.userID = toDoListUsers.userID and todoList.todoID = ?";

    //friend
    String RETRIEVE_FRINDLIST_QUERY = "select * from users where users.userID in(select friendID from friendList where userID=?  UNION select userID from friendList where friendID= ?)";
    String RETRIEVE_COLLABROTOR_QUERY = "select * from users where users.userID in(select userID from toDoListUsers where todoID = ?)";
    String RETRIEVE_USerID_ifExist_QUERY = "select * from users where username = ?";
    String ADD_FRIND_QUERY = "INSERT INTO friendList VALUES(?,?)";
    String RETRIEVE_ITEM_COLLABROTOR_QUERY = " select * from users where users.userID in(select userID from itemAssignedUsers where itemID =?)";
    String RETRIEVE_COLLABROTOR_QUERY_V2 = "select * from users where users.userID in(select userID from toDoListUsers where todoID = ?) union select users.* from users, todoList where users.userID = todoList.creatorID and todoID = ?";

    
    
    //todos
    String INSERT_TODO_LIST_QUERY = "INSERT INTO TODOLIST VALUES(?, ?, ?, ?, ?, ?, ?)";
    String UPDATE_TODO_LIST_QUERY = "UPDATE TODOLIST SET TITLE = ?, DESCRIPTION = ?, DEADLINEDATE = ?, ASSIGNINGDATE = ?, BACKGROUNDCOLOR = ?, STATUS = ? WHERE TODOID = ?";
    String DELETE_TODO_LIST_QUERY = "DELETE FROM TODOLIST WHERE TODOID = ?";
    String RETRIEVE_ALL_TODO_LISTS_QUERY = "SELECT * FROM TODOLIST WHERE CREATORID = ? UNION select todoList.* from toDoListUsers, todoList where todoList.todoID = toDoListUsers.todoID and  toDoListUsers.userID  = ?";
    String Retrieve_todo_by_itemid = "select todoList.* from todoList, item where todoList.todoID = item.todoID and itemID = ?";
    String RETRIEVE_TODO_DATA_BY_TODOID = "SELECT * FROM TODOLIST WHERE todoID = ?";
    
    //items
    String INSERT_ITEM_QUERY = "INSERT INTO ITEM VALUES(?, ?, ?, ?, ?)";
    String UPDATE_ITEM_QUERY = "UPDATE ITEM SET TITLE = ?, DESCRIPTION = ?, TODOID = ?, CREATORID = ?, DEADLINEDATE= ? WHERE ITEMID = ?";
    String DELETE_ITEM_QUERY = "DELETE FROM ITEM WHERE ITEMID = ?";
    String RETRIEVE_ALL_ITEMS_QUERY = "SELECT I.* FROM TODOLIST T, ITEM I WHERE T.TITLE = ? AND T.TODOID = I.TODOID";
    String RETRIEVE_ALL_ITEMS_BY_TODOID = "SELECT * FROM ITEM WHERE ITEM.TODOID = ?";
    String RETRIEVE_ALL_ITEMS_QUERY_BY_TODO_ID = "SELECT I.* FROM TODOLIST T, ITEM I WHERE T.TODOID = I.TODOID and T.TODOID = ?";
    
    //collaborator 
    String ASSIGN_FRIEND_TO_iTEM = "INSERT INTO ITEMASSIGNEDUSERS VALUES(?, ?)";
    String RERIEVE_ALL_FRIENDS_ASSIGNED_TO_iTEM = "SELECT U.* FROM USERS AS U, TODOList AS T, TODOLISTUSERS AS TU, ITEM AS I, ITEMASSIGNEDUSERS AS IAU WHERE  IAU.userID = U.USERID and TU.userID = U.userID and Tu.todoID = T.todoID and I.todoID = T.todoID  and  IAU.itemID = I.itemID  and T.todoID=? and I.itemID =?";
    String ASSIGN_FRIEND_TO_TODOLIST = "INSERT INTO TODOLISTUSERS VALUES(?, ?)";
    String RETRIEVE_ALL_FRIEND_ASSIGNED_TO_TODOLIST = "SELECT u.* FROM USERS AS U, TODOList AS T, TODOLISTUSERS AS TU WHERE  TU.userID = U.userID and Tu.todoID = T.todoID  and  T.todoID=?";
    String DELETE_FRIEND_ON_ITEM="DELETE FROM itemAssignedUsers WHERE ITEMID= ?";
    String GET_USERID_BY_USERNAME = "SELECT * FROM USERS WHERE USERNAME = ?";
    String GET_USER_DATA_BY_USERID = "SELECT * FROM USERS WHERE USERID = ?";
    String CHECK_IF_USER_FRIEND = "SELECT * FROM FRIENDLIST WHERE FRIENDID = ? AND USERID = ?";
    String DELETE_FRIEND_ON_TODO="DELETE FROM toDoListUsers WHERE todoID= ?";
    String REMOVE_TODO_COLLABORATOR="delete from toDoListUsers where todoID = ? and userID = ?";
    //String REMOVE_ITEM_COLLABORATOR = "delete from itemA";


    //notification
   String INSERT_NOTIFICATION_QUERY = "INSERT INTO NOTIFICATIONS VALUES(?, ?, ?, ?)";
   String INSERT_NOTIFICATION_RECIEVERS_QUERY = "INSERT INTO notificationReceivers VALUES(?, ?, null, null)";
   String GET_NOTIFICATION_ID_BY_NOTIFICATION_HEADER = "select top 1 * from notifications where header = ?  and senderID= ? order by notificationID desc";
   String RETRIEVE_USER_NOTIFICATIONS="select n.* from notifications as n , notificationReceivers as nr where n.notificationID = nr.notificationID and nr.acceptanceFlag is null  and (nr.readFlag != 1 or nr.readFlag is null) and nr.receiverID = ? order by nr.notificationID desc";
   String RETRIEVE_NOTIFICATION_RECEIVERS="select nr.receiverID from notifications as n , notificationReceivers as nr where n.notificationID = nr.notificationID and nr.acceptanceFlag is null  and (nr.readFlag != 1 or nr.readFlag is null) and nr.receiverID = ? and n.notificationID =?";
   String UPDATE_NOTIFICATION_ACCEPTANCE_STATUS = "update notificationReceivers set readFlag = 1 , acceptanceFlag = 1 where notificationID = ? ";
   String UPDATE_NOTIFICATION_REJECTION_STATUS = "update notificationReceivers set readFlag = 1 , acceptanceFlag = 0 where notificationID = ? ";
   String GET_LAST_NOTIFICATION_BY_NOFITICATION_TYPE="select top 1 n.* from notifications n , notificationReceivers nr where n.notificationID=nr.notificationID and (nr.acceptanceFlag != 1 or nr.acceptanceFlag is null) and (nr.readFlag != 1 or nr.readFlag is null) and n.notificationType = ? and n.notificationType!='Acceptance' and n.notificationType!='Rejection'";
   //Component
   String INSERT_COMPONENT_QUERY = "INSERT INTO ITEMCOMPONENTS VALUES(?, ?, ?, ?)";
   String UPDATE_COMPONENT_QUERY = "UPDATE ITEMCOMPONENTS SET finishedFlag = ? where componentId = ?";
   String DELETE_COMPONENT_QUERY = "DELETE FROM ITEMCOMPONENTS WHERE ITEMID = ? AND COMPONENTID = ?";
   String DELETE_ALL_ITEM_COMPONENT_QUERY = "DELETE FROM ITEMCOMPONENTS WHERE ITEMID = ?";
   String RETRIEVE_ALL_COMPONENT_BY_ITEMID_QUERY = "SELECT * FROM ITEMCOMPONENTS WHERE ITEMID = ?";
   String RETRIEVE_ALL_COMPONENT_BY_TODOID_QUERY="select ic.* from itemComponents ic , todoList t , item i where t.todoID = i.todoID and i.itemID = ic.itemID and t.todoID = ?  and ic.componentType='checkboxComponent'";
   
}

