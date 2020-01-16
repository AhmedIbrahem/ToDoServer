package todolistserver.model.entities;

/**
 *
 * @author Ibrahim
 */
public class ComponentEntity {
    private int componentId;
    private int itemId;
    private String componentType;
    private String componentText;
    private int finisheFlag;

    public ComponentEntity() {
    }

    public ComponentEntity(int componentId, int itemId, String componentType, String componentText, int finisheFlag) {
        this.componentId = componentId;
        this.itemId = itemId;
        this.componentType = componentType;
        this.componentText = componentText;
        this.finisheFlag = finisheFlag;
    }

    public ComponentEntity(int itemId, String componentType, String componentText, int finisheFlag) {
        this.itemId = itemId;
        this.componentType = componentType;
        this.componentText = componentText;
        this.finisheFlag = finisheFlag;
    }

    public int getComponentId() {
        return componentId;
    }
    
    public int getItemId() {
        return itemId;
    }

    public void setItemId(int itemId) {
        this.itemId = itemId;
    }

    public String getComponentType() {
        return componentType;
    }

    public void setComponentType(String componentType) {
        this.componentType = componentType;
    }

    public String getComponentText() {
        return componentText;
    }

    public void setComponentText(String componentText) {
        this.componentText = componentText;
    }

    public int getFinisheFlag() {
        return finisheFlag;
    }

    public void setFinisheFlag(int finisheFlag) {
        this.finisheFlag = finisheFlag;
    }
    
}
