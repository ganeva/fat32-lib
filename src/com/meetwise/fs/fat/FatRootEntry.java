/*
 * $Id: FatRootEntry.java 4975 2009-02-02 08:30:52Z lsantha $
 *
 * Copyright (C) 2003-2009 JNode.org
 *
 * This library is free software; you can redistribute it and/or modify it
 * under the terms of the GNU Lesser General Public License as published
 * by the Free Software Foundation; either version 2.1 of the License, or
 * (at your option) any later version.
 *
 * This library is distributed in the hope that it will be useful, but 
 * WITHOUT ANY WARRANTY; without even the implied warranty of MERCHANTABILITY
 * or FITNESS FOR A PARTICULAR PURPOSE. See the GNU Lesser General Public 
 * License for more details.
 *
 * You should have received a copy of the GNU Lesser General Public License
 * along with this library; If not, write to the Free Software Foundation, Inc., 
 * 51 Franklin Street, Fifth Floor, Boston, MA 02110-1301 USA.
 */
 
package com.meetwise.fs.fat;

import java.io.IOException;

import com.meetwise.fs.FSDirectory;
import com.meetwise.fs.FSEntry;
import com.meetwise.fs.FSFile;

/**
 * @author Ewout Prangsma &lt; epr at jnode.org&gt;
 */
public class FatRootEntry extends FatObject implements FSEntry {

    /** The actual root directory */
    private final FatDirectory rootDir;

    public FatRootEntry(FatDirectory rootDir) {
        super(rootDir.getFatFileSystem());
        this.rootDir = rootDir;
    }

    /**
     * Gets the name of this entry.
     */
    public String getName() {
        return "";
    }

    /**
     * Gets the directory this entry is a part of.
     */
    public FSDirectory getParent() {
        return null;
    }

    public long getLastModified() {
        return 0;
    }

    /**
     * Is this entry refering to a file?
     */
    public boolean isFile() {
        return false;
    }

    /**
     * Is this entry refering to a (sub-)directory?
     */
    public boolean isDirectory() {
        return true;
    }

    /**
     * Sets the name of this entry.
     */
    public void setName(String newName) throws IOException {
        throw new IOException("Cannot change name of root directory");
    }

    /**
     * Sets the last modification time of this entry.
     * 
     * @throws IOException
     */
    public void setLastModified(long lastModified) throws IOException {
        throw new IOException("Cannot change last modified of root directory");
    }

    /**
     * Gets the file this entry refers to. This method can only be called if
     * <code>isFile</code> returns true.
     */
    public FSFile getFile() throws IOException {
        throw new IOException("Not a file");
    }

    /**
     * Gets the directory this entry refers to. This method can only be called
     * if <code>isDirectory</code> returns true.
     */
    public FSDirectory getDirectory() {
        return rootDir;
    }
    
    /**
     * Indicate if the entry has been modified in memory (ie need to be saved)
     * 
     * @return true if the entry need to be saved
     */
    public boolean isDirty() {
        return true;
    }

    public int compareTo(FSEntry e) {
        if (e.isDirectory() == this.isDirectory()) {
            /* compare names */
            return this.getName().compareTo(e.getName());
        } else {
            if (e.isDirectory()) return 1;
            else return -1;
        }
    }
}