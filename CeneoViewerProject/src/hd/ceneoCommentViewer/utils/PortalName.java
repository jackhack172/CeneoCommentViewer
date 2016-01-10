package hd.ceneoCommentViewer.utils;

/**
 * 
 * Obsługiwane portale internetowe.
 *
 */
public enum PortalName {
	CENEO ("ceneo"),
    MORELE ("morele");

	/**
	 * Nazwa portalu.
	 */
    private final String name;       

    private PortalName(String name) {
        this.name = name;
    }

    public String toString() {
       return this.name;
    }
}
