package rest.response;

import model.HalLink;

public class Option {
    private String name;
    private String description;
    private HalLink link;
    private String schema;

    public Option() {}
    public Option(String name, String description, HalLink link, String schema) {
        this.name = name;
        this.description = description;
        this.link = link;
        this.schema = schema;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public HalLink getLink() {
        return link;
    }

    public void setLink(HalLink link) {
        this.link = link;
    }

    public String getSchema() {
        return schema;
    }

    public void setSchema(String schema) {
        this.schema = schema;
    }
}
