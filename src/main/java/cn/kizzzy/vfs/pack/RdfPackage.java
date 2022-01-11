package cn.kizzzy.vfs.pack;

import cn.kizzzy.io.FullyReader;
import cn.kizzzy.sghero.RdfFileItem;
import cn.kizzzy.vfs.IFileLoader;
import cn.kizzzy.vfs.IFileSaver;
import cn.kizzzy.vfs.IStreamable;
import cn.kizzzy.vfs.ITree;
import cn.kizzzy.vfs.streamable.FileStreamable;
import cn.kizzzy.vfs.tree.Leaf;

public class RdfPackage extends PackageAdapter {
    
    private final ITree<RdfFileItem> tree;
    
    public RdfPackage(String root, ITree<RdfFileItem> tree) {
        super(root);
        this.tree = tree;
    }
    
    @Override
    public boolean exist(String path) {
        return tree.getLeaf(path) != null;
    }
    
    @Override
    protected Object loadImpl(String path, IFileLoader<?> loader) throws Exception {
        Leaf<RdfFileItem> leaf = tree.getLeaf(path);
        if (leaf == null) {
            return null;
        }
        
        RdfFileItem file = leaf.item;
        if (file == null) {
            return null;
        }
        
        String fullPath = FILE_SEPARATOR.combine(root, file.pkg);
        if (file.getSource() == null) {
            file.setSource(new FileStreamable(fullPath));
        }
        
        try (FullyReader reader = file.OpenStream()) {
            Object obj = loader.load(this, path, reader, reader.length());
            if (obj instanceof IStreamable) {
                ((IStreamable) obj).setSource(file);
            }
            return obj;
        }
    }
    
    @Override
    protected <T> boolean saveImpl(String path, T data, IFileSaver<T> saver) throws Exception {
        return false;
    }
}
