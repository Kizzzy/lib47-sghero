package cn.kizzzy.sghero.vfs.pack;

import cn.kizzzy.sghero.RdfFile;
import cn.kizzzy.vfs.ITree;
import cn.kizzzy.vfs.pack.LeafPackage;
import cn.kizzzy.vfs.stream.FileStreamGetterFactory;

public class RdfPackage extends LeafPackage<RdfFile.Entry> {
    
    public RdfPackage(String root, ITree tree) {
        this(tree, new FileStreamGetterFactory(root));
    }
    
    public RdfPackage(ITree tree, FileStreamGetterFactory factory) {
        super(tree, factory, RdfFile.Entry.class, item -> item.pack);
    }
}
