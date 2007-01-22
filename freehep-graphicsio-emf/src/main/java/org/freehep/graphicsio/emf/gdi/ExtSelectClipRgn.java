// Copyright 2002, FreeHEP.
package org.freehep.graphicsio.emf.gdi;

import java.io.IOException;

import org.freehep.graphicsio.emf.EMFConstants;
import org.freehep.graphicsio.emf.EMFInputStream;
import org.freehep.graphicsio.emf.EMFOutputStream;
import org.freehep.graphicsio.emf.EMFTag;

/**
 * ExtSelectClipRgn TAG.
 * 
 * @author Mark Donszelmann
 * @version $Id: freehep-graphicsio-emf/src/main/java/org/freehep/graphicsio/emf/gdi/ExtSelectClipRgn.java c0f15e7696d3 2007/01/22 19:26:48 duns $
 */
public class ExtSelectClipRgn extends EMFTag implements EMFConstants {

    private int mode;

    private Region rgn;

    public ExtSelectClipRgn() {
        super(75, 1);
    }

    public ExtSelectClipRgn(int mode, Region rgn) {
        this();
        this.mode = mode;
        this.rgn = rgn;
    }

    public EMFTag read(int tagID, EMFInputStream emf, int len)
            throws IOException {

        int length = emf.readDWORD();
        int mode = emf.readDWORD();
        return new ExtSelectClipRgn(
            mode,
            length > 8 ? new Region(emf) : null);
    }

    public void write(int tagID, EMFOutputStream emf) throws IOException {
        emf.writeDWORD(rgn.length());
        emf.writeDWORD(mode);
        rgn.write(emf);
    }

    public String toString() {
        return super.toString() +
            "\n  mode: " + mode +
            "\n" + rgn;
    }
}
