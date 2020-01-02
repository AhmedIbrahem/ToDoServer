package todolistserver.model.entities;

/**
 * @author Ibrahim
 */
public class RequestEntity {

    private String className;
    private String operation;    
    private Object entity;

    public RequestEntity() {
    }

    public RequestEntity(String className,String operation, Object entity) {
        this.operation = operation;
        this.className = className;
        this.entity = entity;
    }

    public String getOperation() {
        return operation;
    }

    public void setOperation(String operation) {
        this.operation = operation;
    }

    public String getClassName() {
        return className;
    }

    public void setClassName(String className) {
        this.className = className;
    }

    public Object getEntity() {
        return entity;
    }

    public void setEntity(Object entity) {
        this.entity = entity;
    }

}
