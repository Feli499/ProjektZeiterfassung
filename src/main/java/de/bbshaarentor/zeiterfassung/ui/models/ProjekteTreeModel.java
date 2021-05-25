package de.bbshaarentor.zeiterfassung.ui.models;

import java.util.ArrayList;
import java.util.List;

import javax.swing.event.TreeModelListener;
import javax.swing.tree.TreeModel;
import javax.swing.tree.TreePath;

import de.bbshaarentor.zeiterfassung.projekte.Projekt;
import de.bbshaarentor.zeiterfassung.projekte.ProjektBereich;
import de.bbshaarentor.zeiterfassung.projekte.ProjektContainer;
import de.bbshaarentor.zeiterfassung.projekte.ZeitErfassung;

public class ProjekteTreeModel implements TreeModel {

    private final ProjektContainer projektContainer;

    public ProjekteTreeModel(ProjektContainer projektContainer) {

        this.projektContainer = projektContainer;
    }

    @Override
    public Object getRoot() {
        return "Projekte";
    }

    @Override
    public Object getChild(Object parent, int index) {
        return this.getChildren(parent).get(index);
    }

    @Override
    public int getChildCount(Object parent) {
        return this.getChildren(parent).size();
    }

    @Override
    public boolean isLeaf(Object node) {

        if (node instanceof ZeitErfassung) {
            return true;
        }
        return false;
    }

    @Override
    public void valueForPathChanged(TreePath path, Object newValue) {

    }

    @Override
    public int getIndexOfChild(Object parent, Object child) {

        int i = 0;
        for (Object obj : this.getChildren(parent)) {
            if (obj.equals(child)) {
                return i;
            }
            i++;
        }

        return 0;
    }

    @Override
    public void addTreeModelListener(TreeModelListener l) {

    }

    @Override
    public void removeTreeModelListener(TreeModelListener l) {

    }

    private List<Object> getChildren(Object parent) {

        List<Projekt> projekte = new ArrayList<>(this.projektContainer.getProjekte());

        if (parent.equals(this.getRoot())) {
            return new ArrayList<>(projekte);
        }

        for (Projekt projekt : projekte) {

            if (projekt.equals(parent)) {
                return new ArrayList<>(projekt.getProjektBereiche());
            }

            if (parent instanceof ProjektBereich) {

                List<ProjektBereich> projektBereiche = new ArrayList<>(projekt.getProjektBereiche());
                for (ProjektBereich projektBereich : projektBereiche) {
                    if (projektBereich.equals(parent)) {
                        return new ArrayList<>(projektBereich.getZeitErfassungen());
                    }
                }
            }
        }

        return new ArrayList<>();
    }
}
