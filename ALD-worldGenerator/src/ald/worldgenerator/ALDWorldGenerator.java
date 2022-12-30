/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Main.java to edit this template
 */
package ald.worldgenerator;

import java.awt.Graphics;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import javax.imageio.ImageIO;
import javax.swing.*;

/**
 *
 * @author siror
 */
public class ALDWorldGenerator {
    
    //Cely program vlastne funguje tak, ze mam matici v mainu, kterou postupne projizdim a na kazdou pozici vybiram nahodny obrazek a pak kontroluju jestli tam sedi, 
    //pokud tam nesedi tak zas nahodne vyberu dalsi obrazek a zkousim jestli tam sedi, to opakuju do te doby nez tam ten obrazek sedi a jdu na dalsi pozici matice
    //matici plnim po radcich
    
    private static final String path = "Obrazky";
    //V poli stringu jsou nazvy jednotlivych obrázků v souboru
    private static final String[] files = {"cerna-dvojitaZatacka1.png", "cerna-dvojitaZatacka2.png", "cerna-konec1.png", "cerna-konec2.png", "cerna-konec3.png", "cerna-konec4.png", "cerna-rovna1.png", 
        "cerna-rovna2.png", "cerna-zatacka1.png", "cerna-zatacka2.png", "cerna-zatacka3.png", "cerna-zatacka4.png", "mix-dvojitaZatacka1.png", "mix-dvojitaZatacka2.png", 
        "mix-dvojitaZatacka3.png", "mix-dvojitaZatacka4.png", "mix-kriz1.png", "mix-kriz2.png", "seda-dvojitaZatacka1.png", "seda-dvojitaZatacka2.png", "seda-konec1.png", "seda-konec2.png", 
        "seda-konec3.png", "seda-konec4.png", "seda-rovna1.png", "seda-rovna2.png", "seda-zatacka1.png", "seda-zatacka2.png", "seda-zatacka3.png", "seda-zatacka4.png"};
    //V poli strany jsou pro každý obrázek(slopec) udaje o jednotlivych stranach 
    //prazdna = 0, cerna = 1, seda = 2 ---- cisluje se od horni strany po smeru hod rucicek
    private static final int strany[][] = {
        {1, 1, 0, 0, 1, 0, 0, 1, 0, 1, 1, 0, 2, 2, 1, 1, 1, 2, 2, 2, 0, 0, 2, 0, 2, 0, 0, 2, 2, 0},
        {1, 1, 1, 0, 0, 0, 1, 0, 1, 0, 1, 1, 1, 2, 2, 1, 2, 1, 2, 2, 2, 0, 0, 0, 0, 2, 2, 0, 2, 2},
        {1, 1, 0, 0, 0, 1, 0, 1, 1, 0, 0, 1, 1, 1, 2, 2, 1, 2, 2, 2, 0, 0, 0, 2, 2, 0, 2, 0, 0, 2},
        {1, 1, 0, 1, 0, 0, 1, 0, 0, 1, 0, 0, 2, 1, 1, 2, 2, 1, 2, 2, 0, 2, 0, 0, 0, 2, 0, 2, 0, 0},
    };
    //V poli images jsou pak nacteny obrazky
    private static BufferedImage[] images = new BufferedImage[files.length];
    
    //Ve funkci inicializeImages nacteme obrazky ze souboru do pole images
    public static void inicializeImages () throws IOException{
        for (int i = 0; i < images.length; i++) {
            images[i] = ImageIO.read(new File(path + "\\" + files[i]));
        }
    }
    
    //Funce vykresleni slouzi k vykresleni obrazku z matice in
    public static void vykresleni (int[][] in) throws IOException{
        int width = in[0].length * 64;  //zjisti sirku matice a vynasobi ji 64 (sirka obrazku)
        int height = in.length * 64;    //zjisti vysku matice a vynasobi ji 64 (vyska obrazku)
        
        BufferedImage out = new BufferedImage(width, height, BufferedImage.TYPE_INT_ARGB);
        Graphics g = out.getGraphics();
        
        int pomw = 0;
        int pomh = 0;
        //pres dva fory projizdime matici, kazde cislo v matici je jeden z obrazku z pole images a pridavame obrazky do finalniho
        //pridavame vzdy 64 abychom posunuli kde zacina dalsi obrazek
        for (int i = 0; i < in.length; i++) {
            for (int j = 0; j < in[0].length; j++) {
                g.drawImage(images[in[i][j]], pomw, pomh, null);
                pomw += 64;
            }
            pomw = 0;
            pomh += 64;
        }
        displayImage(out);
        ImageIO.write(out, "PNG", new File("Obrazky\\vysledek.png"));   //ulozi obrazek do slozky
    }
    
    //Funkce kontrola vraci false pokud se obrazek do pole nehodi a true pokud ano
    public static boolean kontrola (int[][] in, int cisloRadku, int cisloSloupce, int cislo){
        int cisloNad = -1;
        int cisloLeve = -1;
        if(cisloRadku > 0){
            cisloNad = strany[2][in[cisloRadku - 1][cisloSloupce]]; //pokud radek neni nulty zjisti spodni stranu obrazku nad nim
        }
        if(cisloSloupce > 0){
            cisloLeve = strany[1][in[cisloRadku][cisloSloupce - 1]]; //pokud sloupec neni nulty zjisti pravou stranu obrazku v levo
        }
        
        if(cisloNad < 0 && cisloLeve < 0){  //kdyz je na pozici 0,0 je mi jedno jakej obrazek tam je
            return true;
        }else if(cisloNad < 0 && cisloLeve >= 0){ //kdyz je radek 0(prvni radek), tak kontoluju pouze pravou stranu obrazku na levo a vracim true pokud tam obrazek sedi a false pokud ne
            return strany[3][cislo] == cisloLeve;
        }else if(cisloNad >= 0 && cisloLeve < 0){   //kdyz je sloupec 0(prvni sloupec), tak kontroluju pouze spodni stranu obrazku nad nim a vracim true pokud tam obazek sedi a false pokud ne
            return strany[0][cislo] == cisloNad;
        }else{  //kdyz ani sloupec ani radek nejsou 0 musim kontrolovat jak spodni stranu obrazku nad nim, tak pravou stranu obrazku v levo, opet vracim true pokud tam obrazek sedi a false pokud ne
            return strany[3][cislo] == cisloLeve && strany[0][cislo] == cisloNad;
        }
    }
    
    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) throws IOException {
        inicializeImages();
        int[][] a = new int[10][10];    //matice s cislama obrazku
        for (int i = 0; i < a[0].length; i++) { //fory na projizdeni a plneni matice nahodnyma cislama a jejich nasledna kontrola pomoci fce kontrola
            for (int j = 0; j < a.length; j++) {
                boolean ok = false;
                while(!ok){
                    a[i][j] = (int)(Math.random()*(images.length-0)+0);
                    ok = kontrola(a, i, j, a[i][j]);
                }
            }
        }
        vykresleni(a);
    }
    
    //Funkce na display obrazku
    public static void displayImage(final BufferedImage image) {
        displayImage("", image);
    }/*w  w  w.j  a v a 2  s .c  om*/

    public static void displayImage(final String windowTitle,
            final BufferedImage image) {
        new JFrame(windowTitle) {
            {
                final JLabel label = new JLabel("", new ImageIcon(image), 0);
                add(label);
                pack();
                setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
                setVisible(true);
            }
        };
    }
}
