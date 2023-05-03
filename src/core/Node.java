package core;

import java.time.LocalDateTime;

public abstract class Node {
    public final boolean isDirectory;
    public String name;
    public int size;
    public int[] permission;
    public String owner;
    public LocalDateTime timestamp;

    public Node parent;

    public Node(boolean isDirectory) {
        this.isDirectory = isDirectory;
    }

    public Node(boolean isDirectory, String name, int size, String owner, LocalDateTime timestamp) {
        this.isDirectory = isDirectory;
        this.name = name;
        this.size = size;
        this.owner = owner;
        this.timestamp = timestamp;
        permission = new int[]{6, 6, 4};
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setSize(int size) {
        this.size = size;
    }

    public void setPermission(int[] permission) {
        this.permission = permission;
    }

    public void setOwner(String owner) {
        this.owner = owner;
    }

    public void setTimestamp(LocalDateTime timestamp) {
        this.timestamp = timestamp;
    }

    public boolean getIsDirectory() {
        return isDirectory;
    }

    public String getName() {
        return name;
    }

    public int getSize() {
        return size;
    }

    public int[] getPermission() {
        return permission;
    }

    public String getOwner() {
        return owner;
    }

    public LocalDateTime getTimestamp() {
        return timestamp;
    }
}
