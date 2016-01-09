package hd.ceneoCommentViewer.utils;

public enum PortalName {
	CENEO ("ceneo"),
    MORELE ("morele");

    private final String name;       

    private PortalName(String name) {
        this.name = name;
    }

    public String toString() {
       return this.name;
    }
}
