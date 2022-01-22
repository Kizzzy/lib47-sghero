package cn.kizzzy.vfs.tree;

import cn.kizzzy.sghero.RdfFile;
import cn.kizzzy.sghero.RdfFileItem;
import cn.kizzzy.vfs.ITree;
import cn.kizzzy.vfs.Separator;

public class RdfTreeBuilder extends TreeBuilder {
    
    private final RdfFile rdfFile;
    
    public RdfTreeBuilder(RdfFile rdfFile) {
        this(rdfFile, new IdGenerator());
    }
    
    public RdfTreeBuilder(RdfFile rdfFile, IdGenerator idGenerator) {
        super(Separator.BACKSLASH_SEPARATOR_LOWERCASE, idGenerator);
        this.rdfFile = rdfFile;
    }
    
    @Override
    public ITree build() {
        Root root = new Root(idGenerator.getId(), rdfFile.pkg);
        
        for (RdfFileItem file : rdfFile.headers) {
            listImpl(root, root, file);
        }
        
        return new Tree(root, separator);
    }
    
    private void listImpl(Root root, Node parent, RdfFileItem file) {
        String[] names = separator.split(file.path);
        for (String name : names) {
            Node child = parent.children.get(name);
            if (child == null) {
                if (name.contains(".")) {
                    Leaf leaf = new Leaf(idGenerator.getId(), name, root.name, file.path, file);
                    root.fileKvs.put(file.path, leaf);
                    child = leaf;
                } else {
                    child = new Node(idGenerator.getId(), name);
                }
                root.folderKvs.put(child.id, child);
                parent.children.put(name, child);
            }
            parent = child;
        }
    }
}
