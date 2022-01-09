package cn.kizzzy.sghero;

import cn.kizzzy.vfs.IStreamable;

import java.util.LinkedList;
import java.util.List;

public class RdfFile implements IStreamable {
    
    public String pkg;
    
    public final List<RdfFileItem> headers
        = new LinkedList<>();
    
    private IStreamable source;
    
    @Override
    public IStreamable getSource() {
        return source;
    }
    
    @Override
    public void setSource(IStreamable source) {
        this.source = source;
    }
}
