package todolistserver.model.entities;

public class UserEntity {
    
    private int id;
    private String username;
    private String password;
    private String email;
    private int onlineFlag;

    public UserEntity() {
    }

    public UserEntity(int id, String username, String password, String email, int onlineFlag) {
        this.id = id;
        this.username = username;
        this.password = password;
        this.email = email;
        this.onlineFlag = onlineFlag;
    }

   public UserEntity(int id, String username) {
        this.id = id;
        this.username = username;
      
    }    
    
    public int getId() {
        return id;
    }

    public String getUsername() {
        return username;
    }

    public String getEmail() {
        return email;
    }

    public int getOnlineFlag() {
        return onlineFlag;
    }

    public String getPassword() {
        return password;
    }

    public void setId(int id) {
        this.id = id;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public void setOnlineFlag(int onlineFlag) {
        this.onlineFlag = onlineFlag;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public void setUsername(String username) {
        this.username = username;
    }
    
	
}
