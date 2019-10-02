package etudiants;

import java.util.ArrayList;
import java.util.List;

public class RemplirLaClasse
{
    
    private static int NOMBRE_ETUDIANTS = 160;
    private static int NOMBRE_PLACES = 20;
    private static int ITERATIONS = 0;
    private static int FIRST_ITERATION = 0;
    private static Combinaison combinaisonInitiale;
    private static long DEBUT = System.currentTimeMillis();
    private static long FIN = 0;
    private static int NOMBRE_PAIRES_MAX = 0;
    private static int NOMBRE_PAIRES_MIN = 2000;
    
    private static List<Combinaison> resultats = new ArrayList<Combinaison>();

    public static void main(String[] args)
    {
        Combinaison combinaison = initialiser();
        System.out.println("Nombre de paires : "+combinaison.getPaires().size());
        //combinaison.afficher();
        
        System.out.println();
        
      /*  Combinaison combinaison2 = combinaison.separate();
        
        combinaison.afficher();
        
        System.out.println();
        
        combinaison2.afficher();*/
        rechercher(combinaison);
        System.out.println("Nombre d'itérations : "+ITERATIONS);
        System.out.println("Nombre de solutions : "+ resultats.size());
        FIN = System.currentTimeMillis();
        long duree = (FIN - DEBUT)/1000;
        System.out.println("duree en secondes : "+ duree + " s");

    }
    
    private static Combinaison initialiser() {
        
        PairesGenerateur generateur = new PairesGenerateur(NOMBRE_ETUDIANTS, NOMBRE_PLACES);
        
        combinaisonInitiale = generateur.genererCombinaisonWithMaxAndMin(NOMBRE_PAIRES_MAX, NOMBRE_PAIRES_MIN);
        
        combinaisonInitiale.reOrderList();
        
        return combinaisonInitiale.clone();
        
    }
    
    private static boolean rechercher(Combinaison combinaison) {
        if(combinaison.isValid()) {
            if(FIRST_ITERATION==0) {
                System.out.println("Nombre d'itérations : "+ITERATIONS);
                combinaison.afficher();
                FIRST_ITERATION = ITERATIONS;
                if(combinaison.checkCombinaison(combinaisonInitiale)) {
                    System.out.println("solution vérifiée");
                } else {
                    System.out.println("solution invalidée");
                }
            }
            resultats.add(combinaison);
            return true;
        }
        if(combinaison.isInvalid()) {
            return false;
        }
        ITERATIONS++;
        Combinaison combinaison2 = combinaison.clone();
        combinaison.retirerPremierLeft();
        combinaison.reOrderList();
        if(rechercher(combinaison)) {
            return true;
        }
        combinaison2.confirmerPremierLeft();
        combinaison2.reOrderList();
        return rechercher(combinaison2);
    }

}
