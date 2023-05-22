package cn.kizzzy.sghero.vfs.pack;

import cn.kizzzy.sghero.RdfFile;
import cn.kizzzy.vfs.IStreamGetterFactory;
import cn.kizzzy.vfs.ITree;
import cn.kizzzy.vfs.pack.LeafPackage;
import cn.kizzzy.vfs.stream.FileStreamGetterFactory;

public class RdfPackage extends LeafPackage<RdfFile.Entry> {
    
    public RdfPackage(String root, ITree tree) {
        this(tree, new FileStreamGetterFactory(root, tree));
    }
    
    public RdfPackage(ITree tree, IStreamGetterFactory factory) {
        super(tree, factory, RdfFile.Entry.class, item -> item.pack);
    }
}
