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
   private int finishedFlag;

    public ComponentEntity(int itemId, String componentType, String componentText, int finishedFlag) {
        this.itemId = itemId;
        this.componentType = componentType;
        this.componentText = componentText;
        this.finishedFlag = finishedFlag;
    }
    
    public ComponentEntity(int itemId, String componentType, String componentText) {
        this.itemId = itemId;
        this.componentType = componentType;
        this.componentText = componentText;
    }

    public ComponentEntity(int componentId, int itemId, String componentType, String componentText, int finishedFlag) {
        this.componentId = componentId;
        this.itemId = itemId;
        this.componentType = componentType;
        this.componentText = componentText;
        this.finishedFlag = finishedFlag;
    }

    public ComponentEntity() {
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

    public int getFinishedFlag() {
        return finishedFlag;
    }

    public void setFinishedFlag(int finishedFlag) {
        this.finishedFlag = finishedFlag;
    }

    public int getComponentId() {
        return componentId;
    }
    
    
}
