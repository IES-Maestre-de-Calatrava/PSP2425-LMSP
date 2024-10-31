/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.maestre.ej6;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author Usuario
 */
public class WordFileReader extends Thread{
    private File nombreArchivo;
    private String palabraBuscada;
    private int contador;
    public WordFileReader(File nombreArchivo,String palabraBuscada){
        this.nombreArchivo = nombreArchivo;
        this.palabraBuscada = palabraBuscada;
        this.contador = 0;
    }
    public File getNombreArchivo(){
        return this.nombreArchivo;
    }
    public String getPalabraBuscada(){
        return this.palabraBuscada;
    }
    public int getContador(){
        return this.contador;
    }
    public void setContador(int contador){
        this.contador = contador;
    }
    @Override
    public void run() {
        try(BufferedReader lector = new BufferedReader(new FileReader(this.getNombreArchivo()))){
            String linea;
            while((linea= lector.readLine()) != null){
                String[] arrayLinea = linea.split(" ");
                for(String palabra:arrayLinea){
                   if (palabra.equalsIgnoreCase(this.getPalabraBuscada())){
                       this.setContador(this.getContador()+1);
                   }
                }
            }
            if(this.getContador()>0){
                System.out.println(this.getContador()+" ocurrencias en el fichero "+this.getNombreArchivo());
            }
        } catch (FileNotFoundException ex) {
            Logger.getLogger(WordFileReader.class.getName()).log(Level.SEVERE, null, ex);
        } catch (IOException ex) {
            Logger.getLogger(WordFileReader.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    
}
