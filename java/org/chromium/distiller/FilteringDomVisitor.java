// Copyright 2014 The Chromium Authors. All rights reserved.
// Use of this source code is governed by a BSD-style license that can be
// found in the LICENSE file.

package org.chromium.distiller;

import com.google.gwt.dom.client.Element;
import com.google.gwt.dom.client.Node;
import com.google.gwt.dom.client.Style;
import com.google.gwt.dom.client.TableElement;

import java.util.HashSet;
import java.util.Set;

/**
 * Filters out elements that are to be ignored from the DOM tree, and passes other nodes and
 * elements on to the actual DOM visitor for processing.
 */
public class FilteringDomVisitor implements DomWalker.Visitor {
    private final DomWalker.Visitor domVisitor;
    private final Set<Node> hiddenElements;
    private final Set<Node> dataTables;

    FilteringDomVisitor(DomWalker.Visitor v) {
        domVisitor = v;
        hiddenElements = new HashSet<Node>();
        dataTables = new HashSet<Node>();
    }

    public final Set<Node> getHiddenElements() {
        return hiddenElements;
    }

    public final Set<Node> getDataTables() {
        return dataTables;
    }

    @Override
    public void skip(Element e) {
        domVisitor.skip(e);
    }

    @Override
    public boolean visit(Node n) {
        if (n.getNodeType() == Node.ELEMENT_NODE) {
            Element e = Element.as(n);

            // Skip invisible or uninteresting elements.
            boolean visible = DomUtil.isVisible(e);
            logVisibilityInfo(e, visible);
            if (!visible) {
                hiddenElements.add(e);
                return false;
            }

            switch (e.getTagName()) {
                // Skip data tables, keep track of them to be extracted by RelevantElementsFinder
                // later.
                case "TABLE":
                    TableClassifier.Type type = TableClassifier.table(TableElement.as(e));
                    logTableInfo(e, type);
                    if (type == TableClassifier.Type.DATA) {
                        dataTables.add(e);
                        return false;
                    }
                    break;

                // Some components are revisited later in context as they break text-flow of a
                // document.  e.g. <video> can contain text if format is unsupported.
                case "FIGURE":
                case "VIDEO":
                    if (LogUtil.isLoggable(LogUtil.DEBUG_LEVEL_VISIBILITY_INFO)) {
                        LogUtil.logToConsole("SKIP " + e.getTagName() + " from processing. " +
                                "It may be restored later.");
                    }
                    // TODO(cjhopman): These should probably call domVisitor.skip();
                    return false;


                // These element types are all skipped (but may affect document construction).
                case "OPTION":
                case "OBJECT":
                case "EMBED":
                case "APPLET":
                    skip(e);
                    return false;

                // These types are skipped and don't affect document construction.
                case "HEAD":
                case "STYLE":
                case "SCRIPT":
                case "LINK":
                case "NOSCRIPT":
                    return false;

            }
        }
        return domVisitor.visit(n);
    }

    @Override
    public void exit(Node n) {
        domVisitor.exit(n);
    }

    private static void logVisibilityInfo(Element e, boolean visible) {
        if (!LogUtil.isLoggable(LogUtil.DEBUG_LEVEL_VISIBILITY_INFO)) return;
        Style style = DomUtil.getComputedStyle(e);
        LogUtil.logToConsole((visible ? "KEEP " : "SKIP ") + e.getTagName() +
                ": id=" + e.getId() +
                ", dsp=" + style.getDisplay() +
                ", vis=" + style.getVisibility() +
                ", opaq=" + style.getOpacity());
    }

    private static void logTableInfo(Element e, TableClassifier.Type type) {
        if (!LogUtil.isLoggable(LogUtil.DEBUG_LEVEL_VISIBILITY_INFO)) return;
        Element parent = e.getParentElement();
        LogUtil.logToConsole("TABLE: " + type +
                ", id=" + e.getId() +
                ", class=" + e.getClassName() +
                ", parent=[" + parent.getTagName() +
                ", id=" + parent.getId() +
                ", class=" + parent.getClassName() +
                "]");
    }
}