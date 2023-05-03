package core;

import java.time.LocalDateTime;

public class FileNode extends Node {

    public FileNode(String name, int size, String owner, LocalDateTime timestamp, Node parent) {
        super(false, name, size, owner, timestamp);
        this.parent = parent;
    }
}
