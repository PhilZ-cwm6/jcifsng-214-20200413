/* jcifs smb client library in Java
 * Copyright (C) 2007  "Michael B. Allen" <jcifs at samba dot org>
 * 
 * This library is free software; you can redistribute it and/or
 * modify it under the terms of the GNU Lesser General Public
 * License as published by the Free Software Foundation; either
 * version 2.1 of the License, or (at your option) any later version.
 * 
 * This library is distributed in the hope that it will be useful,
 * but WITHOUT ANY WARRANTY; without even the implied warranty of
 * MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the GNU
 * Lesser General Public License for more details.
 * 
 * You should have received a copy of the GNU Lesser General Public
 * License along with this library; if not, write to the Free Software
 * Foundation, Inc., 59 Temple Place, Suite 330, Boston, MA  02111-1307  USA
 */

package jcifsng214.internal.smb1.net;


import java.util.Objects;

import jcifsng214.SmbConstants;
import jcifsng214.smb.FileEntry;
import jcifsng214.util.Hexdump;


/**
 * Internal use only
 * 
 * @internal
 */
public class SmbShareInfo implements FileEntry {

    protected String netName;
    protected int type;
    protected String remark;


    /**
     * 
     */
    public SmbShareInfo () {}


    /**
     * 
     * @param netName
     * @param type
     * @param remark
     */
    public SmbShareInfo ( String netName, int type, String remark ) {
        this.netName = netName;
        this.type = type;
        this.remark = remark;
    }


    @Override
    public String getName () {
        return this.netName;
    }


    /**
     * {@inheritDoc}
     *
     * @see jcifsng214.smb.FileEntry#getFileIndex()
     */
    @Override
    public int getFileIndex () {
        return 0;
    }


    @Override
    public int getType () {
        /*
         * 0x80000000 means hidden but SmbFile.isHidden() checks for $ at end
         */
        switch ( this.type & 0xFFFF ) {
        case 1:
            return SmbConstants.TYPE_PRINTER;
        case 3:
            return SmbConstants.TYPE_NAMED_PIPE;
        }
        return SmbConstants.TYPE_SHARE;
    }


    @Override
    public int getAttributes () {
        return SmbConstants.ATTR_READONLY | SmbConstants.ATTR_DIRECTORY;
    }


    @Override
    public long createTime () {
        return 0L;
    }


    @Override
    public long lastModified () {
        return 0L;
    }


    /**
     * {@inheritDoc}
     *
     * @see jcifsng214.smb.FileEntry#lastAccess()
     */
    @Override
    public long lastAccess () {
        return 0L;
    }


    @Override
    public long length () {
        return 0L;
    }


    @Override
    public boolean equals ( Object obj ) {
        if ( obj instanceof SmbShareInfo ) {
            SmbShareInfo si = (SmbShareInfo) obj;
            return Objects.equals(this.netName, si.netName);
        }
        return false;
    }


    @Override
    public int hashCode () {
        return Objects.hashCode(this.netName);
    }


    @Override
    public String toString () {
        return new String(
            "SmbShareInfo[" + "netName=" + this.netName + ",type=0x" + Hexdump.toHexString(this.type, 8) + ",remark=" + this.remark + "]");
    }
}
