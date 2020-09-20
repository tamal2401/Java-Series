package spring.security.jwt.security;

public enum UsePermissions {
    STUDENT_READ("student:read"),
    STUDENT_WRITE("student:write"),
    COURSE_READ("course:read"),
    COURSE_WRITE("course:write");

    private final String permission;


    UsePermissions(String permission) {
        this.permission = permission;
    }

    public String getPermission() {
        return permission;
    }
}
