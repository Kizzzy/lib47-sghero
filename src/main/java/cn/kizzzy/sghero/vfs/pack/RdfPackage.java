package cn.kizzzy.sghero.vfs.pack;

import cn.kizzzy.sghero.RdfFileItem;
import cn.kizzzy.vfs.IFileSaver;
import cn.kizzzy.vfs.IStreamable;
import cn.kizzzy.vfs.ITree;
import cn.kizzzy.vfs.Separator;
import cn.kizzzy.vfs.pack.AbstractPackage;
import cn.kizzzy.vfs.streamable.FileStreamable;
import cn.kizzzy.vfs.tree.Leaf;

public class RdfPackage extends AbstractPackage {
    
    public RdfPackage(String root, ITree tree) {
        super(root, tree);
    }
    
    @Override
    public boolean exist(String path) {
        return tree.getLeaf(path) != null;
    }
    
    @Override
    protected IStreamable getStreamableImpl(String path) {
        Leaf leaf = tree.getLeaf(path);
        if (leaf == null || !(leaf.item instanceof RdfFileItem)) {
            return null;
        }
        
        RdfFileItem file = (RdfFileItem) leaf.item;
        
        String fullPath = Separator.FILE_SEPARATOR.combine(root, file.pkg);
        if (file.getSource() == null) {
            file.setSource(new FileStreamable(fullPath));
        }
        
        return file;
    }
    
    @Override
    protected <T> boolean saveImpl(String path, T data, IFileSaver<T> saver) throws Exception {
        return false;
    }
}
