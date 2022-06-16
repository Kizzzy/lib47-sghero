package cn.kizzzy.sghero;

import cn.kizzzy.vfs.IInputStreamGetter;

import java.util.LinkedList;
import java.util.List;

public class RdfFile implements IInputStreamGetter {
    
    public String pkg;
    
    public final List<RdfFileItem> headers
        = new LinkedList<>();
    
    private IInputStreamGetter source;
    
    @Override
    public IInputStreamGetter getSource() {
        return source;
    }
    
    @Override
    public void setSource(IInputStreamGetter source) {
        this.source = source;
    }
}
