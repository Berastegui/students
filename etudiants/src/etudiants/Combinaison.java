package etudiants;

import java.util.ArrayList;
import java.util.List;

public class Combinaison
{
    private PairList paires;
    private List<Integer> etudiants;
    private int nbPlaces;
    
    
    
    public Combinaison(PairList paires, List<Integer> etudiants, int nbPlaces)
    {
        this.paires = paires;
        this.etudiants = etudiants;
        this.nbPlaces = nbPlaces;
    }

    public List<Integer> getEtudiants()
    {
        return etudiants;
    }
    
    public void setEtudiants(List<Integer> etudiants)
    {
        this.etudiants = etudiants;
    }
    
    public PairList getPaires()
    {
        return paires;
    }
    
    public void setPaires(PairList paires)
    {
        this.paires = paires;
    }
    
    public void retirerEtudiant(int i) {
        etudiants.remove(Integer.valueOf(i));
        paires.removeAll(i);
    }
    
    public void retirerPremierLeft() {
        int i = paires.getFirstLeft();
        retirerEtudiant(i);
    }
    
    public void confirmerEtudiant(int i) {
        int j = paires.removeFirst(i);
        while (j!=i) {
            retirerEtudiant(j);
            j = paires.removeFirst(i);
        }
    }
    
    public void confirmerPremierLeft() {
        int i = paires.getFirstLeft();
        confirmerEtudiant(i);
    }
    
    public void afficher() {
        //paires.afficher();
        System.out.println(paires.size());
        for(Integer i : etudiants) {
            System.out.print(i+"; ");
        }
        System.out.println();
    }
    
    public Combinaison clone() {
        return new Combinaison(paires.clone(), cloneEtudiants(),nbPlaces);
    }
    
    public List<Integer> cloneEtudiants(){
        List<Integer> clone = new ArrayList<Integer>();
        for(Integer etudiant : etudiants) {
            clone.add(etudiant);
        }
        return clone;
    }
    
    public boolean isValid() {
        return paires.isEmpty() && !isInvalid();
    }
    
    public boolean isInvalid() {
        return (etudiants.size()<nbPlaces);
    }
    
    public boolean checkCombinaison(Combinaison combinaisonInitiale) {
        for(Pair paire : combinaisonInitiale.getPaires().getListDePaires()) {
            for(Integer etudiant : etudiants) {
                if(paire.getLeft()==etudiant.intValue()) {
                    for(Integer etudiant2 : etudiants) {
                        if(paire.getRight()==etudiant2.intValue()) {
                            return false;
                        }
                    }
                }
                if(paire.getRight()==etudiant.intValue()) {
                    for(Integer etudiant2 : etudiants) {
                        if(paire.getLeft()==etudiant2.intValue()) {
                            return false;
                        }
                    }
                }
            }
        }
        return true;
    }
    
    public int getMaxPresence() {
        int i = 0;
        int max = 0;
        int nbOccurences = 0;
        
        for(Integer etudiant: etudiants) {
            nbOccurences = paires.getNbOccurences(etudiant);
            if(nbOccurences>max) {
                max = nbOccurences;
                i = etudiant.intValue();
            }
        }
        
        
        return i;
        
    }
    
    public void reOrderList() {
        int i = getMaxPresence();
        if (i>0) {
            paires.putPairsFirst(i);
        }
    }
    
    public Combinaison separate() {
        
        List<Integer> etudiantsNew = new ArrayList<Integer>();
        
        Pair paire = paires.get(0);
        
        PairList listNew =  new PairList();
        
        int etudiant = paire.getLeft();
        
        etudiantsNew.add(Integer.valueOf(etudiant));
        
        List<Integer> etudiantsToAdd = paires.separateAll(etudiant, listNew);
        etudiantsNew.addAll(etudiantsToAdd);
        retirerEtudiant(etudiant);
        
        while(etudiantsToAdd!=null && etudiantsToAdd.size()>0) {

            etudiantsToAdd = new ArrayList<Integer>();
            for(Integer etudiant2 : etudiantsNew) {
                etudiantsToAdd.addAll(paires.separateAll(etudiant2,listNew));
                retirerEtudiant(etudiant2);
            }
            etudiantsNew.addAll(etudiantsToAdd);
        }
        
        return new Combinaison(listNew, etudiantsNew, nbPlaces);
    }
}
