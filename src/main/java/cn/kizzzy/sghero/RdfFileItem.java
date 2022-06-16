package cn.kizzzy.sghero;

import cn.kizzzy.io.IFullyReader;
import cn.kizzzy.io.SliceFullReader;
import cn.kizzzy.vfs.IInputStreamGetter;

public class RdfFileItem implements IInputStreamGetter {
    public int pathLength;
    public String path;
    public int type;
    public int size;
    
    public int index;
    public long offset;
    public String pkg;
    
    private IInputStreamGetter source;
    
    @Override
    public IInputStreamGetter getSource() {
        return source;
    }
    
    @Override
    public void setSource(IInputStreamGetter source) {
        this.source = source;
    }
    
    @Override
    public IFullyReader getInput() throws Exception {
        if (getSource() == null) {
            throw new NullPointerException("source is null");
        }
        return new SliceFullReader(getSource().getInput(), offset, size);
    }
}
