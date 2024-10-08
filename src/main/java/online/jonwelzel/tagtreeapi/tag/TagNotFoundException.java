package online.jonwelzel.tagtreeapi.tag;

public class TagNotFoundException extends RuntimeException {
    public TagNotFoundException( Long tagId) {
        super("Could not find tag with id " + tagId);
    }
}
