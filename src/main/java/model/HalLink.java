package model;

public class HalLink {

    public static final String SELF = "self";
    public static final String GET = "GET";
    public static final String PUT = "PUT";

    private String href;
    private String rel;
    private String type;

    public HalLink() {}

    public static HalLink generate(String href, String rel, String type) {
        HalLink halLink = generate();
        halLink.setHref(href);
        halLink.setRel(rel);
        halLink.setType(type);
        return halLink;
    }

    private static HalLink generate() {
        return new HalLink();
    }

    public String getHref() {
        return href;
    }

    public void setHref(String href) {
        this.href = href;
    }

    public String getRel() {
        return rel;
    }

    public void setRel(String rel) {
        this.rel = rel;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }
}
