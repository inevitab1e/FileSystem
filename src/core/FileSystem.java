package core;

import context.ExecutorContext;

import java.time.LocalDateTime;

public class FileSystem {

    public ExecutorContext context;
    public final Node root;

    public FileSystem(String user) {
        root = new DirectoryNode("/", 0, user, LocalDateTime.now(), null);
        context = new ExecutorContext(user, "/", root, root);
    }
}
