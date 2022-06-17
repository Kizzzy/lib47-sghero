package cn.kizzzy.sghero.vfs.tree;

import cn.kizzzy.sghero.RdfFile;
import cn.kizzzy.vfs.ITree;
import cn.kizzzy.vfs.Separator;
import cn.kizzzy.vfs.tree.IdGenerator;
import cn.kizzzy.vfs.tree.TreeBuilderAdapter;

public class RdfTreeBuilder extends TreeBuilderAdapter<RdfFile, RdfFile.Entry> {
    
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
        return buildImpl(rdfFile, new Helper<RdfFile, RdfFile.Entry>() {
            
            @Override
            public String idxPath(RdfFile idxFile) {
                return idxFile.path;
            }
            
            @Override
            public Iterable<RdfFile.Entry> entries(RdfFile idxFile) {
                return idxFile.entries;
            }
            
            @Override
            public String itemPath(RdfFile.Entry item) {
                return item.path;
            }
        });
    }
}
