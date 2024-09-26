package online.jonwelzel.tagtreeapi.tag;

public class TagNotFoundException extends RuntimeException {
    public TagNotFoundException(Long userId, Long tagId) {
        super("Could not find tag with id " + tagId + " for user " + userId);
    }
}
