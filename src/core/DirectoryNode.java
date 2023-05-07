package core;

import java.time.LocalDateTime;
import java.util.HashMap;

public class DirectoryNode extends Node {

    public HashMap<String, Node> children;

    public DirectoryNode() {
        super(true);
        children = new HashMap<>();
    }

    public DirectoryNode(String name, int size, String owner, LocalDateTime timestamp, Node parent) {
        super(true, name, size, owner, timestamp);
        children = new HashMap<>();
        this.parent = parent;
    }

}