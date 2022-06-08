package cn.kizzzy.sghero.vfs.handler;

import cn.kizzzy.io.IFullyReader;
import cn.kizzzy.io.IFullyWriter;
import cn.kizzzy.io.SeekType;
import cn.kizzzy.sghero.RdfFile;
import cn.kizzzy.sghero.RdfFileItem;
import cn.kizzzy.vfs.IFileHandler;
import cn.kizzzy.vfs.IPackage;

import java.nio.charset.Charset;

public class RdfFileHandler implements IFileHandler<RdfFile> {
    
    @Override
    public RdfFile load(IPackage vfs, String path, IFullyReader reader, long size) throws Exception {
        RdfFile file = new RdfFile();
        file.pkg = path;
        
        int index = 0;
        while (true) {
            try {
                RdfFileItem item = new RdfFileItem();
                item.index = index++;
                item.pkg = file.pkg;
                item.setSource(file);
                
                item.pathLength = reader.readIntEx();
                item.path = reader.readString(item.pathLength, Charset.forName("GB2312"));
                item.type = reader.readIntEx();
                item.size = reader.readIntEx();
                item.offset = reader.position();
                
                reader.seek(item.size, SeekType.CURRENT);
                
                file.headers.add(item);
            } catch (Exception e) {
                break;
            }
        }
        return file;
    }
    
    @Override
    public boolean save(IPackage vfs, String path, IFullyWriter writer, RdfFile data) throws Exception {
        return false;
    }
}
