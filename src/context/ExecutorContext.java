package context;

import core.Node;

import java.util.HashMap;

/**
 * The context when bash executing
 * which contains
 * the user now logging, the current path, the root of the system, the current working directory and the environment variable
 */
public class ExecutorContext {
    public String user;
    public String path;
    public Node root;
    public Node currentNode;
    public HashMap<String, String> environment = new HashMap<>();

    public ExecutorContext(String user, String path, Node root, Node currentNode) {
        this.user = user;
        this.path = path;
        this.currentNode = currentNode;
        this.root = root;
    }

    public ExecutorContext(String user, String path) {
        this.user = user;
        this.path = path;
    }
}
